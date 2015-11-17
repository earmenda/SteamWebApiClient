package com.wilson.data.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.wilson.data.client.dota.DotaGetMatchHistoryRequest;
import com.wilson.data.client.dota.response.MatchHistoryResponse;
import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.shared.MatchHistory;

public class MatchHistoryConsumer implements Runnable {
	private String accountId;
	private Future task;
	List < Future> futures = new ArrayList<Future>();
	ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));
	SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
	
	public void setAccountId(String accountId){
		this.accountId = accountId;
	}
	public void MatchHistoryConsumer(){
		
	}
	
	public void run() {
		try {
			while (true) {
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
						future.get();

					}
				} catch (InterruptedException e) {
					throw e;
				}
			}
		} catch (InterruptedException e1) {
			taskExecutor.shutdown();
			System.out
					.println("MatchHistoryPoll Task interrupter executor shutdown");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			api.close();
			taskExecutor.shutdown();
			HibernateUtil.shutdown();
			System.out.println("MatchHistoryPoll Task executor shutdown");
			System.out.println("MatchHistoryPollEnd");
		}
	}

	public static void checkInterruptedStatus() throws InterruptedException {
		if (Thread.currentThread().isInterrupted()) {
			throw new InterruptedException();
		}
	}
}
