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
import com.wilson.data.shared.MatchHistory;

public class MatchHistoryConsumer implements Runnable {
	private String accountId;
	private Future task;
	private int threadCount;
	List<Future> futures = new ArrayList<Future>();
	SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");

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

		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(threadCount, threadCount, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));

		try {
//			while (true) {
				try {
					checkInterruptedStatus();
					DotaGetMatchHistoryRequest request = new DotaGetMatchHistoryRequest();
					request.setAccountId(accountId);
					MatchHistoryResponse matchHistoryResponse = (MatchHistoryResponse) api
							.execute(request);
					List<MatchHistory> matches = matchHistoryResponse
							.getResult().getMatches();
					for (MatchHistory match : matches) {
						if (!MatchIdCache.getInstance().checkMatchId(
								match.getMatchId())) {

							futures.add(taskExecutor.submit(new MatchConsumer(
									match.getMatchId())));

						}

					}
					for (Future future : futures) {
						//check that all matches consumed succcessfully 
						future.get();

					}
					
					if(accountId != null){
						Session session = HibernateUtil.getSessionFactory().openSession();
						SteamPlayer mergeUser = new SteamPlayer();
						mergeUser.setSteamId( (Long.parseLong(accountId) + 76561197960265728L) +""); 
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
			System.out.println("MatchHistoryConsumer");
		}
	}

	public static void checkInterruptedStatus() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
	}

}
