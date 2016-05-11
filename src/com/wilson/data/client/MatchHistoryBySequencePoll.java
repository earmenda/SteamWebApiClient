package com.wilson.data.client;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;

import com.wilson.data.client.MatchConsumer.MatchAlreadyExistsException;
import com.wilson.data.client.dota.DotaGetMatchDetailsRequest;
import com.wilson.data.client.dota.response.MatchDetailResponse;
import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.shared.MatchDetail;

public class MatchHistoryBySequencePoll implements Runnable{

	
	public void run() {
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		SteamApi api = new SteamApi(SteamKeys.getSteamKey());
		

		try{
		if (MatchIdCache.getInstance().checkMatchId(this.matchId)){
			throw new MatchAlreadyExistsException("Match: " + matchId + " already exists");
			
		}
		DotaGetMatchHistoryBySequenceRequest request = new DotaGetMatchHistoryBySequenceRequest();
		request.setMatchId(matchId + "");

		MatchDetailResponse matchDetailResponse = (MatchDetailResponse) api
				.execute(request);
		
		MatchDetail matchResults = matchDetailResponse.getResult();
}
