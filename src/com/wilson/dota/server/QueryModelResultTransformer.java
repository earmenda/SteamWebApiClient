package com.wilson.dota.server;

import java.math.BigDecimal;
import java.util.List;

public class QueryModelResultTransformer implements org.hibernate.transform.ResultTransformer{
	   
	@Override
	    public Object transformTuple(Object[] objects, String[] strings) {

	        QueryModel result = new QueryModel();

	        for (int i = 0; i < objects.length; i++) {
	            setField(result, strings[i], objects[i]);
	        }

	        return result;
	    }

	    private void setField(QueryModel result, String string, Object object) {

	        if (string.equalsIgnoreCase("steamid")) {
	            result.setSteamId((String) object);
	        } else if (string.equalsIgnoreCase("steamid2")) {
	            result.setSteamId2((String) object);
	        } else if (string.equalsIgnoreCase("hero1id")) {
	            result.setHero1Id((int) object);
	        } else if (string.equalsIgnoreCase("hero2id")) {
	            result.setHero2Id((int) object);
	        } else if (string.equalsIgnoreCase("games_won")) {
	            result.setGamesWon((Long) object);
	        } else if (string.equalsIgnoreCase("total_games")) {
	            result.setTotalGames((Long) object);
	        } else if (string.equalsIgnoreCase("win_percentage")) {
	            result.setWinPercentage((BigDecimal) object);
	        } else {
	            throw new RuntimeException("unknown field");
	        }

	    }



		@Override
		public List transformList(List list) {
			// TODO Auto-generated method stub
			return list;
		}
	}

