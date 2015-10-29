package com.wilson.client;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.wilson.client.dota.DotaGetMatchHistoryRequest;
import com.wilson.client.dota.response.MatchHistory;
import com.wilson.client.dota.response.MatchHistoryMatch;
import com.wilson.client.dota.response.MatchHistoryPlayer;
import com.wilson.client.user.SteamGetPlayerSummaryRequest;
import com.wilson.client.user.response.SteamPlayer;
import com.wilson.client.user.response.SteamPlayerSummary;

public class Main {

	public static void main(String[] args) throws Exception {

		String steamKey = "029021F53D5F974DA73A60F9300C3CF5";

		List<String> partyList = new ArrayList<String>();

		// DotaGetMatchDetailsRequest request = new
		// DotaGetMatchDetailsRequest();
		// SteamGetPlayerSummaryRequest request = new
		// SteamGetPlayerSummaryRequest();
		DotaGetMatchHistoryRequest request1 = new DotaGetMatchHistoryRequest();
		// request.setSteamId("76561197999636832");
		// request.setMatchId("1848756405");

		// request.setMatchId("1848756405");

		SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
		MatchHistory matchHistoryResponse = (MatchHistory) api
				.execute(request1);
		// SteamPlayerSummary playerSummaryResponse =
		// (SteamPlayerSummary)api.execute(request);

		Session session = HibernateUtil.getSessionFactory().openSession();
		//
		// session.beginTransaction();
		//
		//
		//
		// session.save(playerSummaryResponse.getResponse().getPlayers().get(0));
		// session.getTransaction().commit();

		MatchHistoryMatch match = matchHistoryResponse.getResult().getMatches()
				.get(0);
		for (MatchHistoryPlayer player : match.getPlayers()) {
			Long id = player.getAccountId();
			SteamPlayer playerSummary = null;

			if (id == Long.parseLong("4294967295")) {
				playerSummary = new SteamPlayer();
				playerSummary.setSteamId("0");
			} else {
				String steamId = (id + Long.parseLong("76561197960265728"))
						+ "";
				SteamGetPlayerSummaryRequest request = new SteamGetPlayerSummaryRequest();
				request.setSteamId(steamId);
				SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) api
						.execute(request);
				playerSummary = playerSummaryResponse.getResponse()
						.getPlayers().get(0);
			}

			session.beginTransaction();
			try {
				session.save(playerSummary);
				session.getTransaction().commit();
			} catch (Exception e) {
//				e.printStackTrace();
			}
			

		}
		
		
	}

	//
	// jdeaMatch.getGold() ;
	// jdeaMatch.getExp();
	//

	// // HttpGet userNameGet = new
	// HttpGet("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key="
	// + steamKey + "&vanityurl=jdea");

}
