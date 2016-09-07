package com.wilson.data.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.shared.MatchDetail;

/**
 * Singleton
 * Queries for all existing Matches in DB and stores as a list
 * 
 */

public class MatchIdCache {
	
	private Set<Long> matchIdCache;
	private Long workingSeqNumber;
		
	private static MatchIdCache matchCache = new MatchIdCache();

	private MatchIdCache() {
	}

	public static MatchIdCache getInstance() {
		return matchCache;
	}

 
	
	public synchronized boolean  checkMatchId(Long matchId){
		return matchIdCache.contains(matchId);
	}
	
	
	public synchronized void addMatchId(Long matchId){
		this.matchIdCache.add(matchId);
	}

	public synchronized void setWorkingSeqNumber(Long seqNumber){
		this.workingSeqNumber = seqNumber;
	}
	public synchronized Long getWorkingSeqNumber(){
		return this.workingSeqNumber;
	}
	
	
	public synchronized void init() {
		
		if (matchIdCache == null) {
			System.out.println("Filling the Match ID cache...");
			matchIdCache = new HashSet<Long>();
			Session session = Main.session.getSessionFactory().openSession();
			List <MatchDetail> matchDetails= (List <MatchDetail>)session.createCriteria(MatchDetail.class)
					.setProjection(
							Projections.projectionList()
									.add(Projections.property("matchId"),
											"matchId"))
					.setResultTransformer(
							Transformers.aliasToBean(MatchDetail.class))
							.list();
			
			for(MatchDetail matchDetail : matchDetails){
				matchIdCache.add(matchDetail.getMatchId());
			}

			session.close();

			System.out.println("Match ID Cache size:" + matchIdCache.size());
			
	}
}
}


