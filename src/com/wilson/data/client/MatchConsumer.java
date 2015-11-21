package com.wilson.data.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.wilson.data.client.PlayerConsumer.PlayerConsumerStatus;
import com.wilson.data.client.dota.DotaGetMatchDetailsRequest;
import com.wilson.data.client.dota.response.MatchDetailResponse;
import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.shared.MatchDetail;
import com.wilson.data.shared.MatchDetailPlayer;

public class MatchConsumer implements Runnable{
	private Long matchId;
	
	public MatchConsumer(Long matchId){
		this.matchId = matchId;
	}
	

	public void run() {
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");


		try{
		if (MatchIdCache.getInstance().checkMatchId(this.matchId)){
			throw new MatchAlreadyExistsException("Match: " + matchId + " already exists");
			
		}
		DotaGetMatchDetailsRequest request = new DotaGetMatchDetailsRequest();
		request.setMatchId(matchId + "");

		MatchDetailResponse matchDetailResponse = (MatchDetailResponse) api
				.execute(request);
		MatchDetail matchResults = matchDetailResponse.getResult();
		List < Future> futures = new ArrayList<Future>();
		for (MatchDetailPlayer player : matchResults.getPlayers()){
			try{
			if (player.getSteamId() == null){
				System.out.println("Match ID of null steamId: " + matchId);
			}
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
			
			String steamId = player.getSteamId() + "";



			if(!PlayerIdCache.getInstance().checkPlayerId(steamId)){
				futures.add(taskExecutor.submit(new PlayerConsumer(steamId, api)));
				Thread.sleep(3000);
				
			}
			
		}
		for (Future<PlayerConsumerStatus> future : futures){
			PlayerConsumerStatus status = future.get();
			if (!status.isSuccess()){
				
				throw new FailedToConsumePlayerExceptionForMatch("Could not consume SteamId: " + status.getSteamId() + " in MatchConsumer");
			}
		}
		
		session.beginTransaction();

		try {
			session.save(matchResults);
			session.getTransaction().commit();
			MatchIdCache.getInstance().addMatchId(matchId);
			System.out.println("MatchId = " + matchId);
		} catch (ConstraintViolationException e) {
			System.out.println("FAILED TO INSERT MATCH ID " +this.matchId);
			e.printStackTrace(); 
		}




		}
		catch(MatchAlreadyExistsException  | FailedToConsumePlayerExceptionForMatch e){
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			api.close();
			session.close();
			taskExecutor.shutdown();

		}
	}
	
	private static class MatchAlreadyExistsException extends Exception{
		public MatchAlreadyExistsException(String message){
			super(message);
			
		}
		
	}
	private static class FailedToConsumePlayerExceptionForMatch extends Exception{
		public FailedToConsumePlayerExceptionForMatch(String message){
			super(message);
		}
	}
}

