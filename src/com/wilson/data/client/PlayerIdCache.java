package com.wilson.data.client;

import java.util.Set;

public class PlayerIdCache {
	private static Set<String> playerIdCache;
	
	public static Set<String> getPlayerIdCache(){
		return playerIdCache;
	}

	public void setPlayerIdCache(Set<String> playerIdSet){
		playerIdCache = playerIdSet;
	}
}
