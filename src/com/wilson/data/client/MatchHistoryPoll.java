package com.wilson.data.client;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MatchHistoryPoll implements Runnable {
	
	private Future task;
	private Future task1;
	public MatchHistoryPoll(){};
	private static final int FREQUENCY_IN_SECONDS = Config.HISTORYTHREADTIMEOUT;

	
    /**
     * Creates a new TaskExecutor 
     * Submits new MatchHistoryConsumers at a fixed rate
     */
	
	
	
	public void run() {
		ScheduledThreadPoolExecutor taskExecutor = new ScheduledThreadPoolExecutor(1, new CustomThreadFactory("MatchHistoryPoll"), new ThreadPoolExecutor.DiscardPolicy());
       taskExecutor.scheduleAtFixedRate(new MatchHistoryBySequencePoll(), 0,   FREQUENCY_IN_SECONDS, TimeUnit.SECONDS);
    //   taskExecutor.scheduleAtFixedRate(new MatchHistoryBySequenceTailPoll(), 0,   FREQUENCY_IN_SECONDS, TimeUnit.SECONDS);



		
		

	}
//		SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
//		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchHistoryPoll"));
//		List < Future> futures = new ArrayList<Future>();
//
////		taskExecutor.allowCoreThreadTimeOut(true);
//		try {
//			while (true) {
//				try{
//				checkInterruptedStatus();
//				DotaGetMatchHistoryRequest request = new DotaGetMatchHistoryRequest();
//				MatchHistoryResponse matchHistoryResponse = (MatchHistoryResponse) api
//						.execute(request);
//				List <MatchHistory> matches = matchHistoryResponse.getResult().getMatches();
//				for (MatchHistory match : matches){
//					if (!MatchIdCache.getInstance().checkMatchId(match.getMatchId())){
//						
//						futures.add(taskExecutor.submit(new MatchConsumer(match.getMatchId())));
//						
//					}
//
//				}
//				for (Future future : futures){
//						future.get();
//
//				}
//				
//				}
//				catch(InterruptedException e){
//					throw e;
//				}
//
////                while (!taskExecutor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
////                    System.out.println("Task Executor did not shutdown within timeout, will check again");
////                }
////				break;
//				//make sure jobs (db storages, cache updates) are done then re loop
////				Thread.currentThread().interrupt();
//				System.out.println("======================================================================== ");
//				Thread.sleep(30000);
//			}
//		}catch(InterruptedException e1){
//			taskExecutor.shutdown();
//			System.out.println("MatchHistoryPoll Task interrupter executor shutdown");
//
//			}
//		 catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally{
//			api.close();
//			taskExecutor.shutdown();
//			HibernateUtil.shutdown();
//			System.out.println("MatchHistoryPoll Task executor shutdown");
//			System.out.println("MatchHistoryPollEnd");
//		}
//	}
//	
//    
//	public static void checkInterruptedStatus() throws InterruptedException {
//        if (Thread.currentThread().isInterrupted()) {
//            throw new InterruptedException();
//        }
//	}
}
