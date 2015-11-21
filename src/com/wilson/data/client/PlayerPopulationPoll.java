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

public class PlayerPopulationPoll implements Runnable {

	Set<String> playersToIgnore = Sets.newConcurrentHashSet();

	private final static int DAYS_TIL_STALE = 1;

	public PlayerPopulationPoll() {

	}

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
	//				taskExecutor.submit(mH1);
					CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(mH1, taskExecutor);
					playersToIgnore.add(steamPlayer.getSteamId());
					System.out.println("SUBMITTED MATCH HISTORY CONSUMER FOR PLAYER " +steamPlayer.getSteamId());
	//				completableFuture.thenApplyAsync((test) -> {
	//					System.out.println("SHIT SHIT SHIT SHIT " +test);
	//					return null;
	//				});
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
//			global variable synchoronzied working list singelton cache
//			
//			while loop()
//			criteria list is the query :[1,2,3] [45] [67]
//				(check if creiteria list is in working list, then pass remainder to working list)
//					working list is things we need to put into task execttor : [1,2,3]  [1,2]
//							for each in working list
//							task execute, (picked up) remove current item from working list -> updates the last_updated
//							if exception or something, add item back into list
//							loop around
//						
					
			//can't pick up bot / 0
			//can't pick up things currently worked on
//			Thread.sleep(30000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
			
//			HibernateUtil.shutdown();
//			pull first five items from cache
//			find all players
//			get player history
//			store each match using match consumer
//			move down player list
//			get player history
		// store each match using match consumer
		// }

	}
}
