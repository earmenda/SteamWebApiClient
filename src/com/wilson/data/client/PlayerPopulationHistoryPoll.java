package com.wilson.data.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;

import com.google.common.collect.Sets;
import com.wilson.data.client.user.response.SteamPlayer;
import com.wilson.data.persistence.HibernateUtil;

public class PlayerPopulationHistoryPoll implements Runnable {

	Set<String> playersToIgnore = Sets.newConcurrentHashSet();

	private final static int DAYS_TIL_STALE = 1;

	public PlayerPopulationHistoryPoll() {

	}


    /**
     * Deprecated in favor of using MatchHistoryBySequence
     */
	
	public void run(){
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(2	, 2, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new CustomThreadFactory("PopulationPlayerPoll"));
		Session session = HibernateUtil.getSessionFactory().openSession();

			try{
			String [] ignore = {"0", "Bot", "test"};
			playersToIgnore.addAll(Arrays.asList(ignore));
			
			
			while(true){
				try{
				Criteria criteria = session.createCriteria(SteamPlayer.class);
				criteria.setProjection(Projections.projectionList().add(Projections.property("steamId"),"steamId"));
	//			criteria.add(Restrictions.or(Restrictions.gt("lastUpdated", new Timestamp(0)), Restrictions.isNull("lastUpdated")));
				SimpleExpression timeConstraint = Restrictions.lt("lastUpdated", new Timestamp(System.currentTimeMillis() - (DAYS_TIL_STALE * 24 * 60 * 60 * 1000)));
				Criterion timeNotNull = Restrictions.isNull("lastUpdated");
				criteria.add(Restrictions.or(timeConstraint,timeNotNull));
//				criteria.add(Restrictions.lt("lastUpdated", new Timestamp(System.currentTimeMillis() - (DAYS_TIL_STALE * 24 * 60 * 60 * 1000))));
				criteria.setMaxResults(10);
				for(String pIgnore : playersToIgnore){
					criteria.add(Restrictions.ne("steamId", pIgnore));
				}
				
				criteria.setResultTransformer(Transformers.aliasToBean(SteamPlayer.class));
				
				List <SteamPlayer> criteriaList= (List <SteamPlayer>)criteria.list();
				
				//do a bunch of filters on that list
				//pass list to working list
				List <SteamPlayer> workingList = new ArrayList<SteamPlayer>();
				for (SteamPlayer steamPlayer : criteriaList){
	
					MatchHistoryConsumer mH1 = new MatchHistoryConsumer(2);
					mH1.setSteamId(steamPlayer.getSteamId());
					CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(mH1, taskExecutor);
					playersToIgnore.add(steamPlayer.getSteamId());
					System.out.println("SUBMITTED MATCH HISTORY CONSUMER FOR PLAYER " +steamPlayer.getSteamId());

					completableFuture.thenRunAsync(() -> {
						System.out.println("SHIT SHIT SHIT SHIT" +steamPlayer.getSteamId());
						playersToIgnore.remove(steamPlayer.getSteamId());
					});
					
					
				}
				}catch(RejectedExecutionException e) {
					System.out.println("ATTEMPTED TO SUBMIT PLAYER BUT EXECUTOR IS FULL");
				}
				catch(Exception e){
					e.printStackTrace();
				} finally{
					Thread.sleep(60000);
				}
				
				
			
			}

			}
			catch(Exception e){
				e.printStackTrace();
			}
		
			


	}
}
