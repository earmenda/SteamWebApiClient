package com.wilson.data.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.hibernate.Session;

import com.wilson.data.client.user.SteamGetPlayerSummaryRequest;
import com.wilson.data.client.user.response.SteamPlayer;
import com.wilson.data.client.user.response.SteamPlayerSummary;
import com.wilson.data.persistence.HibernateUtil;

/**
 * Filters based on existing players in cache and persists remaining data
 */

public class PlayerConsumer implements Callable {
	SteamApi api;
	List<PlayerConsumerStatus> playerConsumerStatusList;
	SteamGetPlayerSummaryRequest request;

	public PlayerConsumer(List<PlayerConsumerStatus> playerConsumerStatusList) {
		this.playerConsumerStatusList = playerConsumerStatusList;
	}

	public List<PlayerConsumerStatus> call() {
		Set<String> steamIds = new HashSet<String>();
		for (PlayerConsumerStatus status : playerConsumerStatusList) {


			if (!PlayerIdCache.getInstance().checkPlayerId(status.getSteamId())) {

				steamIds.add(status.getSteamId());
			}

		}
		List<String> steamIdsList = new ArrayList<String>(steamIds);
		System.out.println("playerConsumer player list size (filtered):"
				+ steamIdsList.size());
		if (!steamIdsList.isEmpty()) {
			try {
				List<String> apiSenderList = new ArrayList<String>();
				apiSenderList.add(steamIdsList.get(0));
				// Consume 100 steamIds at a time

				for (int i = 1; i < steamIdsList.size(); i++) {

					if (i % 100 > 0) {
						apiSenderList.add(steamIdsList.get(i));
					}

					else {
						SteamApi api = new SteamApi(SteamKeys.getSteamKey());
						request = new SteamGetPlayerSummaryRequest();
						request.setSteamIds(apiSenderList);
						SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) api
								.execute(request);

						for (SteamPlayer player : playerSummaryResponse
								.getResponse().getPlayers()) {
							Session session = null;
							try {
								session = HibernateUtil.getSessionFactory()
										.openSession();

								session.beginTransaction();
								session.saveOrUpdate(player);
								session.getTransaction().commit();
								PlayerIdCache.getInstance().addPlayerId(
										player.getSteamId());


							} catch (Exception e) {
								e.printStackTrace();
							} finally {

								if (session != null) {
									session.close();
								}

							}
						}
						apiSenderList.clear();
						apiSenderList.add(steamIdsList.get(i));
					}
				}
					SteamApi api = new SteamApi(SteamKeys.getSteamKey());
					request = new SteamGetPlayerSummaryRequest();
					request.setSteamIds(apiSenderList);
					SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) api
							.execute(request);

					for (SteamPlayer player : playerSummaryResponse
							.getResponse().getPlayers()) {
						Session session = null;
						try {
							session = HibernateUtil.getSessionFactory()
									.openSession();

							session.beginTransaction();
							session.saveOrUpdate(player);
							session.getTransaction().commit();
							PlayerIdCache.getInstance().addPlayerId(
									player.getSteamId());
						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (session != null) {
								session.close();
							}
						}
					}

				
				for (PlayerConsumerStatus status : playerConsumerStatusList) {
					status.setSuccess(PlayerIdCache.getInstance()
							.checkPlayerId(status.getSteamId()));


				}
			} catch (Exception e) {
				e.printStackTrace();

			}
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
