package com.wilson.data.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.data.client.user.SteamGetFriendsListRequest;
import com.wilson.data.client.user.response.SteamFriend;
import com.wilson.data.client.user.response.SteamFriendsList;
import com.wilson.data.client.user.response.SteamFriendsListResponse;
import com.wilson.data.persistence.HibernateUtil;
import com.wilson.dota.server.QueryModel;
import com.wilson.dota.server.QueryModelResultTransformer;

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
//		QueryTest a = new QueryTest();
		//a.query();
		
		PlayerIdCache.getInstance().init();
		MatchIdCache.getInstance().init();
		
		

//		
//		List<SteamFriend> nonDotaFriends = new ArrayList<SteamFriend>();
//		SteamApi api = new SteamApi(SteamKeys.getSteamKey());
//		SteamGetFriendsListRequest apiRequest = new SteamGetFriendsListRequest();
//		apiRequest.setSteamId("76561198021016937");
//		String steamId = "76561198021016937";
//		SteamFriendsListResponse steamFriendsListResponse = (SteamFriendsListResponse) api
//				.execute(apiRequest);
//		SteamFriendsList friends = steamFriendsListResponse.getFriendsList();
//		Session session = HibernateUtil.getSessionFactory().openSession();
//
//		
//
//		//friends list is grabbed in realtime..but persona names might be old
//		try{
//		for(SteamFriend friend : friends.getSteamFriends()){
//			
//			SQLQuery winPercentageResult = session
//					.createSQLQuery("select  winrate.steamid, winrate.steamid2, count(case won when true then 1 else null end) as games_won, count(*) as total_games, cast(count(case won when true then 1 else null end) as numeric(4,2)) / count(won) as win_percentage from (select steamid, steamid2, test1.hero1id, test1.hero2id, test1.match_id_2, test1.playerslot, radiant_win , case when test1.playerslot < 10 and radiant_win = true then true when test1.playerslot > 100 and radiant_win = true then false when test1.playerslot < 10 and radiant_win = false then false else true end as won from (select * from (select  t1.match_id as match_id, t1.steam_id as steamid, t1.hero_id AS Hero1ID, t1.player_slot as playerslot, t2.match_id as match_id_2, t2.steam_id as steamid2, t2.hero_id AS Hero2ID from match_detail_player t1, match_detail_player t2 where t1.match_id = t2.match_id and ((((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=100 and t1.player_slot<=1000)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=100 and t2.player_slot<=1000))) or (((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=0 and t1.player_slot<=4)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=0 and t2.player_slot<=4))) )) a join match_detail b on a.match_id = b.match_id) test1 join (select hero1id, hero2id from (select * from (select  t1.match_id as match_id, t1.steam_id as steamid, t1.hero_id AS Hero1ID, t1.player_slot as playerslot, t2.match_id as match_id_2, t2.steam_id as steamid2, t2.hero_id AS Hero2ID from match_detail_player t1, match_detail_player t2 where t1.match_id = t2.match_id and ((((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=100 and t1.player_slot<=1000)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=100 and t2.player_slot<=1000))) or (((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=0 and t1.player_slot<=4)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=0 and t2.player_slot<=4))) )) a join match_detail b on a.match_id = b.match_id) as herocombos group by hero1id, hero2id )test2 on test1.hero1id = test2.hero1id and test1.hero2id = test2.hero2id order by test1.hero1id, test1.hero2id desc) as winrate group by steamid, steamid2;");
//			winPercentageResult.addScalar("steamid", StandardBasicTypes.STRING);
//			winPercentageResult.addScalar("steamid2", StandardBasicTypes.STRING);
//			winPercentageResult.addScalar("games_won", StandardBasicTypes.LONG);
//			winPercentageResult.addScalar("total_games", StandardBasicTypes.LONG);
//			winPercentageResult.addScalar("win_percentage", StandardBasicTypes.BIG_DECIMAL);
//			System.out.println("cast dammit." + friend.getSteamId());
//			
//			winPercentageResult.setResultTransformer(new QueryModelResultTransformer());
//			List<QueryModel> winPercentageResultModelList = (List<QueryModel>) winPercentageResult.list();
//			if (winPercentageResultModelList.size() > 0){
//				friend.setWinPercentage(winPercentageResultModelList.get(0).getWinPercentage());
//				friend.setTotalGames(winPercentageResultModelList.get(0).getTotalGames());
//				friend.setGamesWon(winPercentageResultModelList.get(0).getGamesWon());
//			}
//			else{
//				nonDotaFriends.add(friend);
//			}
//			
//			
//			SQLQuery friendSearchResult = session
//					.createSQLQuery("select persona_name from player_summary where steam_id = " + "'" + friend.getSteamId() + "'");
//		//Need to set parameters here at some poitn
//			List<String> resultQuery = friendSearchResult.list();
//		//If persona name for this player doesn't exist, populate the player summary table	
//			if(resultQuery.size() == 0){
//				PlayerConsumer steamPlayer = new PlayerConsumer(friend.getSteamId(), api);
//				steamPlayer.populate();
////				SQLQuery result1 = session
////						.createSQLQuery("select persona_name from player_summary where steam_id = " + "'" + friend.getSteamId() + "'");
////				//Need to set parameters here at some poitn
////				List<String> resultQuery1 = result.list();
//			}
//			
//			else{
//			friend.setFriendAlias(resultQuery.get(0));
//			}
//			
//			
//		}
//		}
//		catch(Exception e){
//			System.out.println(e.toString());
//		}
//		friends.getSteamFriends().removeAll(nonDotaFriends);
//		// Actual logic goes here.
//		
//		ObjectMapper mapper = new ObjectMapper();
//
//		try {
//			
//			//Convert object to JSON string
//			String jsonInString = mapper.writeValueAsString(friends);
//			System.out.println(jsonInString);
//			
//			
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
		
		//MatchHistoryPoll runner = new MatchHistoryPoll();
		MatchHistoryPoll historyPoll = new MatchHistoryPoll();
//		PlayerPopulationHistoryPoll playerRunner = new PlayerPopulationHistoryPoll();
//		Thread playerPopTest = new Thread(playerRunner);
		
		Thread matchTest = new Thread(historyPoll);
		matchTest.start();
//		playerPopTest.start();



	}




}
