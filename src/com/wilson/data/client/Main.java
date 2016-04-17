package com.wilson.data.client;

import org.hibernate.Session;

import com.wilson.data.persistence.HibernateUtil;
import com.wilson.dota.server.QueryTest;

public class Main {
	public static Session session = HibernateUtil.getSessionFactory().openSession();

	public static void main(String[] args) throws Exception {

//		String steamKey = "029021F53D5F974DA73A60F9300C3CF5";
//		DotaGetMatchHistoryRequest request1 = new DotaGetMatchHistoryRequest();
//		SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
//		MatchHistoryResponse matchHistoryResponse = (MatchHistoryResponse) api
//				.execute(request1);
//
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		MatchHistory match = matchHistoryResponse.getResult().getMatches()
//				.get(0);
//
//		for (MatchHistoryPlayer player : match.getPlayers()) {
//			
//			Long id = player.getAccountId();
//			System.out.println(id);
//			String steamId = "0";
//
//			if (!(id == Long.parseLong("4294967295"))) {
//				steamId = (id + Long.parseLong("76561197960265728")) + "";
//			}
//			System.out.println("Account ID= " + id);
//			System.out.println("SteamID = " + steamId);
//			fetchAndCreateSteamUser(steamId, api);
//		}

		// Make call to get MatchResults object
//		long detailId = match.getMatchId();
//		System.out.println(detailId + "MATCH ID");
//		DotaGetMatchDetailsRequest request = new DotaGetMatchDetailsRequest();
//		request.setMatchId(detailId + "");
//		MatchDetailResponse matchDetailResponse = (MatchDetailResponse) api.execute(request);
//		MatchDetail matchResults = matchDetailResponse.getResult();
//		
//		session.beginTransaction();
//		
//		try {
//			for (MatchDetailPlayer playerResponse : matchResults.getPlayers()) {
//				fetchAndCreateSteamUser(playerResponse.getSteamId(), api);
//			}
//			try {
//
//				session.save(matchResults);
//			} catch (ConstraintViolationException e) {
//				e.printStackTrace();
//			}
//
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			session.getTransaction().rollback();//rolls back previous queries if subsequent queries fail in one transaction...(this is to attempt to use same session)
//			e.printStackTrace();
//		} finally {
//			session.clear();
//		}
//
//
//		api.close();
//		session.close();
		QueryTest a = new QueryTest();
		a.query();

		
//		PlayerIdCache.getInstance().init();
//		MatchIdCache.getInstance().init();
//		MatchHistoryPoll runner = new MatchHistoryPoll();
//		PlayerPopulationPoll playerRunner = new PlayerPopulationPoll();
//		Thread playerPopTest = new Thread(playerRunner);
//		
//		Thread matchTest = new Thread(runner);
//		matchTest.start();
//		playerPopTest.start();



	}




}
