package com.wilson.dota.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.data.client.PlayerConsumer;
import com.wilson.data.client.SteamApi;
import com.wilson.data.client.SteamKeys;
import com.wilson.data.client.user.SteamGetFriendsListRequest;
import com.wilson.data.client.user.response.SteamFriend;
import com.wilson.data.client.user.response.SteamFriendsList;
import com.wilson.data.client.user.response.SteamFriendsListResponse;
import com.wilson.data.persistence.HibernateUtil;

public class TestServlet extends HttpServlet {

	private String message;
	

	public void init() throws ServletException {
		// Do required initialization
		message = "Hello World";
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		List<SteamFriend> nonDotaFriends = new ArrayList<SteamFriend>();
		SteamApi api = new SteamApi(SteamKeys.getSteamKey());
		SteamGetFriendsListRequest apiRequest = new SteamGetFriendsListRequest();
		String steamId = request.getParameter("steamId1");
		apiRequest.setSteamId(steamId);

		SteamFriendsListResponse steamFriendsListResponse = (SteamFriendsListResponse) api
				.execute(apiRequest);
		SteamFriendsList friends = steamFriendsListResponse.getFriendsList();
		Session session = HibernateUtil.getSessionFactory().openSession();

		

		//friends list is grabbed in realtime..but persona names might be old
		try{
		for(SteamFriend friend : friends.getSteamFriends()){
			
			SQLQuery winPercentageResult = session
					.createSQLQuery("select  winrate.steamid, winrate.steamid2, count(case won when true then 1 else null end) as games_won, count(*) as total_games, cast(count(case won when true then 1 else null end) as numeric(4,2)) / count(won) as win_percentage from (select steamid, steamid2, test1.hero1id, test1.hero2id, test1.match_id_2, test1.playerslot, radiant_win , case when test1.playerslot < 10 and radiant_win = true then true when test1.playerslot > 100 and radiant_win = true then false when test1.playerslot < 10 and radiant_win = false then false else true end as won from (select * from (select  t1.match_id as match_id, t1.steam_id as steamid, t1.hero_id AS Hero1ID, t1.player_slot as playerslot, t2.match_id as match_id_2, t2.steam_id as steamid2, t2.hero_id AS Hero2ID from match_detail_player t1, match_detail_player t2 where t1.match_id = t2.match_id and ((((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=100 and t1.player_slot<=1000)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=100 and t2.player_slot<=1000))) or (((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=0 and t1.player_slot<=4)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=0 and t2.player_slot<=4))) )) a join match_detail b on a.match_id = b.match_id) test1 join (select hero1id, hero2id from (select * from (select  t1.match_id as match_id, t1.steam_id as steamid, t1.hero_id AS Hero1ID, t1.player_slot as playerslot, t2.match_id as match_id_2, t2.steam_id as steamid2, t2.hero_id AS Hero2ID from match_detail_player t1, match_detail_player t2 where t1.match_id = t2.match_id and ((((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=100 and t1.player_slot<=1000)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=100 and t2.player_slot<=1000))) or (((t1.steam_id = " + "'" + steamId + "'" + ") and (t1.player_slot >=0 and t1.player_slot<=4)) and ((t2.steam_id = " + "'" + friend.getSteamId() + "'" + ")  and (t2.player_slot >=0 and t2.player_slot<=4))) )) a join match_detail b on a.match_id = b.match_id) as herocombos group by hero1id, hero2id )test2 on test1.hero1id = test2.hero1id and test1.hero2id = test2.hero2id order by test1.hero1id, test1.hero2id desc) as winrate group by steamid, steamid2;");
			winPercentageResult.addScalar("steamid", StandardBasicTypes.STRING);
			winPercentageResult.addScalar("steamid2", StandardBasicTypes.STRING);
			winPercentageResult.addScalar("games_won", StandardBasicTypes.LONG);
			winPercentageResult.addScalar("total_games", StandardBasicTypes.LONG);
			winPercentageResult.addScalar("win_percentage", StandardBasicTypes.BIG_DECIMAL);
			System.out.println("cast dammit." + friend.getSteamId());
			
			winPercentageResult.setResultTransformer(new QueryModelResultTransformer());
			List<QueryModel> winPercentageResultModelList = (List<QueryModel>) winPercentageResult.list();
			if (winPercentageResultModelList.size() > 0){
				friend.setWinPercentage(winPercentageResultModelList.get(0).getWinPercentage());
				friend.setTotalGames(winPercentageResultModelList.get(0).getTotalGames());
				friend.setGamesWon(winPercentageResultModelList.get(0).getGamesWon());
			}
			else{
				nonDotaFriends.add(friend);
			}
			
			
			SQLQuery friendSearchResult = session
					.createSQLQuery("select persona_name from player_summary where steam_id = " + "'" + friend.getSteamId() + "'");
		//Need to set parameters here at some poitn
			List<String> resultQuery = friendSearchResult.list();
		//If persona name for this player doesn't exist, populate the player summary table	
			if(resultQuery.size() == 0){
				PlayerConsumer steamPlayer = new PlayerConsumer(friend.getSteamId(), api);
				steamPlayer.call();
//				SQLQuery result1 = session
//						.createSQLQuery("select persona_name from player_summary where steam_id = " + "'" + friend.getSteamId() + "'");
//				//Need to set parameters here at some poitn
//				List<String> resultQuery1 = result.list();
			}
			
			else{
			friend.setFriendAlias(resultQuery.get(0));
			}
			
			
		}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		friends.getSteamFriends().removeAll(nonDotaFriends);
		// Actual logic goes here.
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			
			//Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(friends);
			out.println(jsonInString);
			
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		// do nothing.
	}
}