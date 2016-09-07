package com.wilson.data.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;

import com.wilson.data.client.dota.DotaGetMatchHistoryRequest;
import com.wilson.data.client.dota.response.MatchHistoryResponse;
import com.wilson.data.client.user.response.SteamPlayer;
import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.shared.MatchDetail;
import com.wilson.data.shared.MatchHistory;

/**
 * @deprecated Obsolete - Removed in favor of using MatchHistoryBySequence workflow 
 * Retrieves Match histories for each player and sends to Match consumer
 */

public class MatchHistoryConsumer implements Runnable {
	private String accountId;
	private Future task;
	private int threadCount;
	List<Future> futures = new ArrayList<Future>();
	SteamApi api = new SteamApi(SteamKeys.getSteamKey());

	
	
	public void setAccountId(String accountId) {

		this.accountId = accountId;
	}

	public MatchHistoryConsumer(int threads) {
		threadCount = threads;
	}

	public void setSteamId(String steamId) {
		try {
			this.accountId = (Long.parseLong(steamId) - 76561197960265728L)
					+ "";
		} catch (NumberFormatException e) {
			// TODO
		}

	}
	
	

	
	public void run(){
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(threadCount, threadCount, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchHistoryConsumer-MatchConsumer"));
		List<MatchDetail> matchDetails;
		try {
				try {

					checkInterruptedStatus();  
					DotaGetMatchHistoryRequest request = new DotaGetMatchHistoryRequest();
					request.setAccountId(accountId);     //Set the account Id if this is for a player's history
					MatchHistoryResponse matchHistoryResponse = (MatchHistoryResponse) api
							.execute(request);
					List<MatchHistory> matches = matchHistoryResponse
							.getResult().getMatches();    //Return list of matches
					for (MatchHistory match : matches) {
						if (!MatchIdCache.getInstance().checkMatchId( //Check if Match exists in the MatchIdCache
								match.getMatchId())) {  


//Re-did Match consumer (now takes a list). This line no longer works
//							futures.add(taskExecutor.submit(new MatchConsumer( //Consume matches 
//									match.getMatchId())));

						}

					}
					for (Future future : futures) {
						future.get(); 						//check that all matches consumed succcessfully 


					}
					
					if(accountId != null){       //If accountId (steamId) was set, we need to set the lastUpdated timestamp in the db for the player
						Session session = HibernateUtil.getSessionFactory().openSession();
						SteamPlayer mergeUser = session.load(SteamPlayer.class, (Long.parseLong(accountId) + 76561197960265728L + ""));
						mergeUser.setLastUpdated(new Timestamp(System.currentTimeMillis()));
						session.beginTransaction();
						session.update(mergeUser);
						session.getTransaction().commit();
					}
				} catch (InterruptedException e) {
					throw e;
				}
//			}
		} catch (InterruptedException e1) {
			taskExecutor.shutdown();
			System.out
					.println("MatchHistoryPoll Task interrupter executor shutdown");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			api.close();
			taskExecutor.shutdown();
			System.out.println("MatchHistoryPoll Task executor shutdown");

		}
	}

	public static void checkInterruptedStatus() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
	}

}
