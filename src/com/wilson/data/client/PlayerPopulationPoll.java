package com.wilson.data.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.wilson.data.client.user.response.SteamPlayer;
import com.wilson.data.persistence.HibernateUtil;
import com.wilson.data.shared.MatchDetail;

public class PlayerPopulationPoll implements Runnable{

	ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(4	, 4, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new CustomThreadFactory("PopulationPlayerPoll"));
	MatchHistoryConsumer mHConsumer = new MatchHistoryConsumer();
	public PlayerPopulationPoll(){
		
	}
	Session session = HibernateUtil.getSessionFactory().openSession();

	public void run(){
		while (true){
			try{
			
			Criteria criteria = session.createCriteria(SteamPlayer.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("steamId"),"steamId"));
//			criteria.add(Restrictions.or(Restrictions.gt("lastUpdated", new Timestamp(0)), Restrictions.isNull("lastUpdated")));
			criteria.add(Restrictions.lt("lastUpdated", new Timestamp(System.currentTimeMillis() - 1500000)));
			criteria.setResultTransformer(Transformers.aliasToBean(SteamPlayer.class));
			
			List <SteamPlayer> criteriaList= (List <SteamPlayer>)criteria.list();
			List <SteamPlayer> workingList = new ArrayList<SteamPlayer>();
			for (SteamPlayer steamPlayer : criteriaList){
				if (steamPlayer.getSteamId().equals("0")){
					continue;
				}
				else{
				Long steamId = (Long.parseLong(steamPlayer.getSteamId()) - Long.parseLong("76561197960265728"));
				mHConsumer.setAccountId(steamId +"");
				}
				
				if(workingList.size()<4){
					taskExecutor.submit(mHConsumer);
				}
				else{
					continue;
				}
			}
			
		
			System.out.println("Last Updated list size: " + criteriaList.size() + " current Millis: " + System.currentTimeMillis());
			Thread.sleep(30000);
			}
			catch(Exception e){
				e.printStackTrace();
				taskExecutor.shutdown();
			}
		}
		
//			HibernateUtil.shutdown();
//			pull first five items from cache
//			find all players
//			get player history
//			store each match using match consumer
//			move down player list
//			get player history
//			store each match using match consumer
//	}
		
	}
	
}
