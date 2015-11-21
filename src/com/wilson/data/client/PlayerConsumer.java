package com.wilson.data.client;

import java.util.concurrent.Callable;

import org.hibernate.Session;

import com.wilson.data.client.user.SteamGetPlayerSummaryRequest;
import com.wilson.data.client.user.response.SteamPlayer;
import com.wilson.data.client.user.response.SteamPlayerSummary;
import com.wilson.data.persistence.HibernateUtil;

public class PlayerConsumer implements Callable {
		String steamId;
		SteamApi api;
		
		public PlayerConsumer(String steamId, SteamApi api){
			this.steamId = steamId;
			this.api = api;
		}
		
		
	public PlayerConsumerStatus call() {
		Session session = null;
		try {
			// Session session =
			// HibernateUtil.getSessionFactory().openSession();
			session = Main.session.getSessionFactory().openSession();
			SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");

			SteamPlayer playerSummary;
			if (steamId.equals("0")) {
				playerSummary = new SteamPlayer();
				playerSummary.setSteamId("0");
			}else if (steamId.equals("Bot")){
				playerSummary = new SteamPlayer();
				playerSummary.setSteamId("Bot");
			}
			else {
				SteamGetPlayerSummaryRequest request = new SteamGetPlayerSummaryRequest();
				request.setSteamId(steamId);
				SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) api
						.execute(request);
				try{
				playerSummary = playerSummaryResponse.getResponse()
						.getPlayers().get(0);
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("Could not find player with Id of " + steamId);
					throw e;
				}
				System.out.println("PlayerSummary = "
						+ playerSummary.getPersonaName() + " Player Steam ID: " +playerSummary.getSteamId());
			}

			session.beginTransaction();

			session.saveOrUpdate(playerSummary);
			session.getTransaction().commit();
			PlayerIdCache.getInstance().addPlayerId(steamId);
			
		} catch (Exception e) {
			e.printStackTrace();
			 return new PlayerConsumerStatus(steamId, false);

		}
		finally{
			if (session != null){
			session.close();
			}
		}
		return new PlayerConsumerStatus(steamId, true);
	}
	
	class PlayerConsumerStatus{
		private String steamId;
		private boolean success;
		public PlayerConsumerStatus(String steamId, boolean success){
			this.steamId = steamId;
			this.success = success;
		}

		public String getSteamId() {
			return steamId;
		}
		public void setSteamId(String steamId) {
			this.steamId = steamId;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
	}
}
