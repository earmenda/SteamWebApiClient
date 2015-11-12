package com.wilson.data.client;

import java.util.Set;


public class MatchIdCache {
	private static MatchIdCache mCache;
	private static Set<Long> matchIdCache;
	protected int test;
	
	
	private static MatchIdCache matchCache = new MatchIdCache();

	private MatchIdCache() {
	}

	public static MatchIdCache getInstance() {
		return matchCache;
	}

     
	protected static Set<Long> getMatchIdCache(){
		return matchIdCache;
	}

	protected void setMatchIdCache(Set<Long> matchIdSet){
		matchIdCache = matchIdSet;
	}
	


}
