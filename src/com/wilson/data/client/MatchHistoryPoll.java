package com.wilson.data.client;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Primary Executor for history polls
 */


public class MatchHistoryPoll implements Runnable {
	

	public MatchHistoryPoll(){};
	private static final int FREQUENCY_IN_SECONDS = Config.HISTORYTHREADTIMEOUT;
	
	//Running both sequence polls will slow historic polling 
	public void run() {
		ScheduledThreadPoolExecutor taskExecutor = new ScheduledThreadPoolExecutor(1, new CustomThreadFactory("MatchHistoryPoll"), new ThreadPoolExecutor.DiscardPolicy());
        taskExecutor.scheduleAtFixedRate(new MatchHistoryBySequencePoll(), 0,   FREQUENCY_IN_SECONDS, TimeUnit.SECONDS);
        taskExecutor.scheduleAtFixedRate(new MatchHistoryBySequenceTailPoll(), 0,   FREQUENCY_IN_SECONDS, TimeUnit.SECONDS);		

	}

}
