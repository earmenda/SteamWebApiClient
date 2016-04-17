package com.wilson.dota.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.wilson.data.client.Main;

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
		Session session = Main.session.getSessionFactory().openSession();

		SQLQuery result = session
				.createSQLQuery("select winrate.steamid, winrate.steamid2, winrate.hero1id, winrate.hero2id, count(case won when true then 1 else null end) as games_won, count(*) as total_games, cast(count(case won when true then 1 else null end) as numeric(4,2)) / count(won) as win_percentage from (select steamid, steamid2, test1.hero1id, test1.hero2id, test1.match_id_2, test1.playerslot, radiant_win, case when test1.playerslot < 10 and radiant_win = true then true when test1.playerslot > 100 and radiant_win = true then false when test1.playerslot < 10 and radiant_win = false then false else true end as won from (select * from (select  t1.match_id as match_id, t1.steam_id as steamid, t1.hero_id AS Hero1ID, t1.player_slot as playerslot, t2.match_id as match_id_2, t2.steam_id as steamid2, t2.hero_id AS Hero2ID from match_detail_player t1, match_detail_player t2 where t1.match_id = t2.match_id and ((((t1.steam_id = '76561198021016937') and (t1.player_slot >=100 and t1.player_slot<=1000)) and ((t2.steam_id = '76561198000302345')  and (t2.player_slot >=100 and t2.player_slot<=1000))) or (((t1.steam_id = '76561198021016937') and (t1.player_slot >=0 and t1.player_slot<=4)) and ((t2.steam_id = '76561198000302345')  and (t2.player_slot >=0 and t2.player_slot<=4))) )) a join match_detail b on a.match_id = b.match_id) test1 join (select hero1id, hero2id from (select * from (select  t1.match_id as match_id, t1.steam_id as steamid, t1.hero_id AS Hero1ID, t1.player_slot as playerslot, t2.match_id as match_id_2, t2.steam_id as steamid2, t2.hero_id AS Hero2ID from match_detail_player t1, match_detail_player t2 where t1.match_id = t2.match_id and ((((t1.steam_id = '76561198021016937') and (t1.player_slot >=100 and t1.player_slot<=1000)) and ((t2.steam_id = '76561198000302345')  and (t2.player_slot >=100 and t2.player_slot<=1000))) or (((t1.steam_id = '76561198021016937') and (t1.player_slot >=0 and t1.player_slot<=4)) and ((t2.steam_id = '76561198000302345')  and (t2.player_slot >=0 and t2.player_slot<=4))) )) a join match_detail b on a.match_id = b.match_id) as herocombos group by hero1id, hero2id having count(*)>1) test2 on test1.hero1id = test2.hero1id and test1.hero2id = test2.hero2id order by test1.hero1id, test1.hero2id desc) as winrate group by steamid, steamid2, hero1id, hero2id order by win_percentage desc;");
		result.addScalar("steamid", StandardBasicTypes.STRING);
		result.addScalar("steamid2", StandardBasicTypes.STRING);
		result.addScalar("hero1id", StandardBasicTypes.INTEGER);
		result.addScalar("hero2id", StandardBasicTypes.INTEGER);
		result.addScalar("games_won", StandardBasicTypes.LONG);
		result.addScalar("total_games", StandardBasicTypes.LONG);
		result.addScalar("win_percentage", StandardBasicTypes.BIG_DECIMAL);

		result.setResultTransformer(new QueryModelResultTransformer());
		List<QueryModel> finalResult = (List<QueryModel>) result.list();

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("Hero1  Hero2  Winrate <br>");
		for (QueryModel model : finalResult) {
			out.println(model.getHero1Id() + "    " + model.getHero2Id() + "       " + model.getWinPercentage() + "  <br>");

		}		
		
		
	}

	public void destroy() {
		// do nothing.
	}
}