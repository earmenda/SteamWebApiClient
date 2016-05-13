package com.wilson.data.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.hibernate.Session;

import com.wilson.data.client.user.SteamGetPlayerSummaryRequest;
import com.wilson.data.client.user.response.SteamPlayer;
import com.wilson.data.client.user.response.SteamPlayerSummary;
import com.wilson.data.persistence.HibernateUtil;

public class PlayerConsumer implements Callable {
	SteamApi api;
	List<PlayerConsumerStatus> playerConsumerStatusList;
	SteamGetPlayerSummaryRequest request;

	// public PlayerConsumer(String steamId, SteamApi api){
	// this.steamId = steamId;
	// this.api = api;
	// }

	public PlayerConsumer(List<PlayerConsumerStatus> playerConsumerStatusList) {
		this.playerConsumerStatusList = playerConsumerStatusList;
	}

	public List<PlayerConsumerStatus> call() {
		Set<String> steamIds = new HashSet<String>();

		for (PlayerConsumerStatus status : playerConsumerStatusList) {
			if (!PlayerIdCache.getInstance().checkPlayerId(status.getSteamId())) {

				steamIds.add(status.getSteamId());
			}

			// list of playerConsumerstatus

		}
		System.out.println("playerConsumer player list size (filtered):" + steamIds.size());
		try {
			SteamApi api = new SteamApi(SteamKeys.getSteamKey());
			request = new SteamGetPlayerSummaryRequest();
			request.setSteamIds(steamIds);

			SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) api
					.execute(request);

			for (SteamPlayer player : playerSummaryResponse.getResponse()
					.getPlayers()) {
				Session session = null;
				try {
					session = HibernateUtil.getSessionFactory().openSession();

					System.out.println("PlayerSummary = "
							+ player.getPersonaName() + " Player Steam ID: "
							+ player.getSteamId());
					session.beginTransaction();
					session.saveOrUpdate(player);
					session.getTransaction().commit();
					PlayerIdCache.getInstance()
							.addPlayerId(player.getSteamId());

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					if (session != null) {
						session.close();
					}

				}
			}

			for (PlayerConsumerStatus status : playerConsumerStatusList) {
				status.setSuccess(PlayerIdCache.getInstance().checkPlayerId(
						status.getSteamId()));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return playerConsumerStatusList;
	}

	public static class PlayerConsumerStatus {
		private String steamId;
		private boolean success;
		private Long matchId;

		public PlayerConsumerStatus(String steamId, Long matchId,
				boolean success) {
			this.steamId = steamId;
			this.success = success;
			this.matchId = matchId;
		}

		public PlayerConsumerStatus(String steamId, Long matchId) {
			this.steamId = steamId;
			this.matchId = matchId;
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

		public Long getMatchId() {
			return matchId;
		}

		public void setMatchId(Long matchId) {
			this.matchId = matchId;
		}

	}
}
