package com.wilson.data.client;

import java.sql.Timestamp;
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
	public PlayerPopulationPoll(){
		
	}
	Session session = HibernateUtil.getSessionFactory().openSession();

	public void run(){
//		while (true){
			
			Criteria criteria = session.createCriteria(SteamPlayer.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("steamId"),"steamId"));
//			criteria.add(Restrictions.or(Restrictions.gt("lastUpdated", new Timestamp(0)), Restrictions.isNull("lastUpdated")));
			criteria.add(Restrictions.gt("lastUpdated", new Timestamp(604800)));
			criteria.setResultTransformer(Transformers.aliasToBean(SteamPlayer.class));
			
			List <SteamPlayer> criteriaList= (List <SteamPlayer>)criteria.list();
			
			System.out.println(criteriaList.size());

			
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
