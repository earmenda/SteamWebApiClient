package com.wilson.data.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.wilson.data.client.dota.DotaGetMatchHistoryRequest;
import com.wilson.data.client.dota.response.MatchHistoryResponse;
import com.wilson.data.shared.MatchHistory;

public class MatchHistoryPoll implements Runnable {
	

	public MatchHistoryPoll(){};

	
	public void run() {
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchHistoryPoll"));
//		taskExecutor.allowCoreThreadTimeOut(true);
		try {
			while (true) {
				checkInterruptedStatus();
				System.out.println("check");
				DotaGetMatchHistoryRequest request = new DotaGetMatchHistoryRequest();
				SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
				MatchHistoryResponse matchHistoryResponse = (MatchHistoryResponse) api
						.execute(request);
				List <MatchHistory> matches = matchHistoryResponse.getResult().getMatches();
				for (MatchHistory match : matches){
					if (!MatchIdCache.getInstance().checkMatchId(match.getMatchId())){
				
						taskExecutor.submit(new MatchConsumer(match.getMatchId()));
						
						System.out.println("new matches:" + match.getMatchId());

					}
				
				}
				
				break;
				//make sure jobs (db storages, cache updates) are done then re loop
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
    
	public static void checkInterruptedStatus() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
	}
}
