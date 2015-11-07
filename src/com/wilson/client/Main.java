package com.wilson.client;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.wilson.client.dota.DotaGetMatchDetailsRequest;
import com.wilson.client.dota.DotaGetMatchHistoryRequest;
import com.wilson.client.dota.response.MatchDetailResponse;
import com.wilson.client.dota.response.MatchHistoryResponse;
import com.wilson.client.dota.response.MatchHistoryPlayer;
import com.wilson.client.user.SteamGetPlayerSummaryRequest;
import com.wilson.client.user.response.SteamPlayer;
import com.wilson.client.user.response.SteamPlayerSummary;
import com.wilson.persistence.HibernateUtil;
import com.wilson.shared.MatchDetail;
import com.wilson.shared.MatchDetailPlayer;
import com.wilson.shared.MatchHistory;

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
		MatchHistoryResponse matchHistoryResponse = (MatchHistoryResponse) api
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

		// Get Player Summary and store in DB
		MatchHistory match = matchHistoryResponse.getResult().getMatches()
				.get(0);
		
		for (MatchHistoryPlayer player : match.getPlayers()) {
			
			Long id = player.getAccountId();
			System.out.println(id);
			String steamId = "0";

			if (!(id == Long.parseLong("4294967295"))) {
				steamId = (id + Long.parseLong("76561197960265728")) + "";
			}
			System.out.println("Account ID= " + id);
			System.out.println("SteamID = " + steamId);
			fetchAndCreateSteamUser(steamId, api);
		}

		// Make call to get MatchResults object
		long detailId = match.getMatchId();
		System.out.println(detailId + "MATCH ID");
		DotaGetMatchDetailsRequest request = new DotaGetMatchDetailsRequest();
		request.setMatchId(detailId + "");
		MatchDetailResponse matchDetailResponse = (MatchDetailResponse) api.execute(request);
		MatchDetail matchResults = matchDetailResponse.getResult();

		// Sets matchResults for MatchDetailResults to access
		// for (MatchDetailPlayer playerResponse : matchResults.getPlayers()) {
		// playerResponse.setMatchDetailResult(matchResults);
		//
		// }

		// Store Match Results into DB
		
		
		session.beginTransaction();
		
		try {
			// List<MatchDetailPlayer> players = new
			// ArrayList<MatchDetailPlayer>();
			// MatchDetailPlayer player = new MatchDetailPlayer();
			// player.setMatchDetailResult(matchResults);
			// players = matchResults.getPlayers();


			for (MatchDetailPlayer playerResponse : matchResults.getPlayers()) {
				fetchAndCreateSteamUser(playerResponse.getSteamId(), api);
//				playerResponse.setMatchDetailResult(matchResults);
//				session.saveOrUpdate(playerResponse);
			}
			try{
			
			session.save(matchResults);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
		}

		// //Store first Match Player from latest match into DB
		// session.beginTransaction();
		// try {
		// MatchDetailPlayer player = new MatchDetailPlayer();
		// player = matchResults.getPlayers().get(0);
		// session.save(player);
		// session.getTransaction().commit();
		// } catch (Exception e) {
		// // e.printStackTrace();
		// } finally {
		// session.clear();
		// }

		api.close();
		session.close();
		HibernateUtil.shutdown();

	}

	//
	// jdeaMatch.getGold() ;
	// jdeaMatch.getExp();
	//

	// // HttpGet userNameGet = new
	// HttpGet("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key="
	// + steamKey + "&vanityurl=jdea");

	private static void fetchAndCreateSteamUser(String steamId, SteamApi api) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SteamPlayer playerSummary;

		if (steamId == "0") {
			playerSummary = new SteamPlayer();
			playerSummary.setSteamId("0");
		} else {
			SteamGetPlayerSummaryRequest request = new SteamGetPlayerSummaryRequest();
			request.setSteamId(steamId);
			SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) api
					.execute(request);

			playerSummary = playerSummaryResponse.getResponse().getPlayers()
					.get(0);
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
	}

}
