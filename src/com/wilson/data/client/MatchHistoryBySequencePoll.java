package com.wilson.data.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wilson.data.client.dota.DotaGetMatchHistoryBySequenceRequest;
import com.wilson.data.client.dota.response.MatchHistoryBySequenceResponse;
import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.shared.MatchDetail;
import com.wilson.data.shared.MatchSeq;

/**
 * Polls 100 dota matches at a time by the SeqNum in Steam API
 */

public class MatchHistoryBySequencePoll implements Runnable{

	
	public void run() {
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		SteamApi api = new SteamApi(SteamKeys.getSteamKey());
		Long workingSequenceNumber = (long) 0;

		
		try{
			
		
		if (MatchIdCache.getInstance().getWorkingSeqNumber() == null){
			SQLQuery matchSeqQuery = session
					.createSQLQuery("select * from match_seq where time_updated is not null order by time_updated desc limit 1;")
					.addEntity(MatchSeq.class);
			List matchSeqQueryResult = matchSeqQuery.list();
//			MatchSeq matchSequence = (MatchSeq) matchSeqQueryResult.get(0);
//			workingSequenceNumber = matchSequence.getMatchSeqId();
	   for (Iterator iterator = 
					   matchSeqQueryResult.iterator(); iterator.hasNext();){
	    MatchSeq matchSequence = (MatchSeq) iterator.next(); 
	    System.out.print("Match Sequence : " + matchSequence.getMatchSeqId()); 
	    workingSequenceNumber = matchSequence.getMatchSeqId();
	 }
//			
		}
		else{
			workingSequenceNumber = MatchIdCache.getInstance().getWorkingSeqNumber();
		}
		
		DotaGetMatchHistoryBySequenceRequest request = new DotaGetMatchHistoryBySequenceRequest();
		//Grab latest sequence number from db
   
		request.setSequenceNumber(String.valueOf(workingSequenceNumber)); //How does the app know when to start sequence?
		
		MatchHistoryBySequenceResponse matchHistorySequenceResponse = (MatchHistoryBySequenceResponse) api
				.execute(request);
		if(matchHistorySequenceResponse.getResult() != null){
			
		List<MatchDetail> matchResults = matchHistorySequenceResponse.getResult().getMatches();
		taskExecutor.submit(new MatchConsumer(matchResults));
		MatchDetail lastMatch = matchResults.get(matchResults.size() - 1);
		MatchIdCache.getInstance().setWorkingSeqNumber(lastMatch.getMatchSeqNum());
		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		   System.out.println(dateFormat.format(date) + ": First Match Seq Number: " +matchResults.get(0).getMatchSeqNum() + " Last Match Seq Number:" + lastMatch.getMatchSeqNum());
		}
		else{
		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    Date date = new Date();
			System.out.println(dateFormat.format(date) + ": Empty Response");
		}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			taskExecutor.shutdown();
			
		}
		}

}