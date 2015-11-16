package com.wilson.data.client;

import org.hibernate.Session;

import com.wilson.data.client.user.SteamGetPlayerSummaryRequest;
import com.wilson.data.client.user.response.SteamPlayer;
import com.wilson.data.client.user.response.SteamPlayerSummary;
import com.wilson.data.persistence.HibernateUtil;

public class PlayerConsumer implements Runnable {
		String steamId;
		SteamApi api;
		
		public PlayerConsumer(String steamId, SteamApi api){
			this.steamId = steamId;
			this.api = api;
		}
		
		public void run(){
		

//		Session session = HibernateUtil.getSessionFactory().openSession();
			Session session = Main.session.getSessionFactory().openSession();

		SteamPlayer playerSummary;
		if (steamId == "0") {
			playerSummary = new SteamPlayer();
			playerSummary.setSteamId("0");
		} else {
			SteamGetPlayerSummaryRequest request = new SteamGetPlayerSummaryRequest();
			request.setSteamId(steamId);
			SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) api
					.execute(request);
			
			playerSummary = playerSummaryResponse.getResponse().getPlayers().get(0);
			System.out.println("PlayerSummary = " + playerSummary.getPersonaName());
		}

		session.beginTransaction();
		try {
			session.saveOrUpdate(playerSummary);
			session.getTransaction().commit();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			session.clear();
		}
		session.close();
		}
}
