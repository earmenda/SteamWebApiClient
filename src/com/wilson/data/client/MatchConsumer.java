package com.wilson.data.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

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
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));
		Session session = HibernateUtil.getSessionFactory().openSession();

		try{
		SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
		DotaGetMatchDetailsRequest request = new DotaGetMatchDetailsRequest();
		request.setMatchId(matchId + "");
		MatchDetailResponse matchDetailResponse = (MatchDetailResponse) api
				.execute(request);
		MatchDetail matchResults = matchDetailResponse.getResult();
		List < Future> futures = new ArrayList<Future>();
		for (MatchDetailPlayer player : matchResults.getPlayers()){
			String steamId = player.getSteamId() + "";
			if(!PlayerIdCache.getInstance().checkPlayerId(steamId)){
				futures.add(taskExecutor.submit(new PlayerConsumer(steamId, api)));
				
			}
			
		}
		for (Future future : futures){
			future.get();
		}
		
		session.beginTransaction();

		try {
			session.save(matchResults);
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();

		api.close();
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			System.out.println("MatchConsumerEnd");
		}
		taskExecutor.shutdown();
		System.out.println("Shutdown");
	}
}
