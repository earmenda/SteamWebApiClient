package com.wilson.dota.server;

import java.math.BigDecimal;


public class QueryModel {

	public String steamId;
	private String steamId2;
	private int hero1Id; // date in UTC seconds since Jan 1, 1970 (unix time format)
	private int hero2Id;
	private Long gamesWon;
	private Long totalGames;
	private BigDecimal winPercentage;
	
	public QueryModel(){
		
	}

	//Setters
	
	public void setSteamId(String steamId){
		this.steamId = steamId;
	}
	public void setSteamId2(String steamId2){
		this.steamId2 = steamId2;
	}
	public void setHero1Id(int hero1Id){
		this.hero1Id = hero1Id;
	}
	public void setHero2Id( int hero2Id){
		this.hero2Id = hero2Id;
	}
	public void setGamesWon(Long gamesWon){
		this.gamesWon = gamesWon;
	}
	public void setTotalGames(Long totalGames){
		this.totalGames = totalGames;
	}
	public void setWinPercentage(BigDecimal winPercentage){
		this.winPercentage = winPercentage;
	}
	
	
	//Getters
	public String getsteamId(){
		return steamId;
	}
	public String getSteamId2(){
		return steamId2;
	}
	public int getHero1Id(){
		return hero1Id;
	}
	public int getHero2Id(){
		return hero2Id;
	}
	public Long getGamesWon(){
		return gamesWon;
	}
	public Long getTotalGames(){
		return totalGames;
	}
	public BigDecimal getWinPercentage(){
		return winPercentage;
	}
}
