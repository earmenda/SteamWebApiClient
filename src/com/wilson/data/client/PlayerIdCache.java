package com.wilson.data.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.client.user.response.SteamPlayer;


public class PlayerIdCache {
	
	private Set<String> playerIdCache;
		
	private static PlayerIdCache playerCache = new PlayerIdCache();

	private PlayerIdCache() {
	}

	public static PlayerIdCache getInstance() {
		return playerCache;
	}

 
	
	public synchronized boolean  checkPlayerId(String PlayerId){
		return playerIdCache.contains(PlayerId);
	}
	
	public synchronized void addPlayerId(String PlayerId){
		this.playerIdCache.add(PlayerId);
	}

	
	public synchronized void init() {
		
		if (playerIdCache == null) {
			playerIdCache = new HashSet<String>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			List <SteamPlayer> PlayerDetails= (List <SteamPlayer>)session.createCriteria(SteamPlayer.class)
					.setProjection(
							Projections.projectionList()
									.add(Projections.property("steamId"),
											"steamId"))
					.setResultTransformer(
							Transformers.aliasToBean(SteamPlayer.class))
							.list();
			
			for(SteamPlayer PlayerDetail : PlayerDetails){
				playerIdCache.add(PlayerDetail.getSteamId());
			}
			session.close();
//			HibernateUtil.shutdown();
		}

	}
}


