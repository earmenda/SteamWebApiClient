package com.wilson.dota.server;

import java.math.BigDecimal;

public class WinPercentageModel {
	
	private String steamId;
	private String steamId2;
	private Long gamesWon;
	private Long totalGames;
	private BigDecimal winPercentage;

	public WinPercentageModel(){
		
	}
	
	//Setters
	public void setSteamId(String steamId){
		this.steamId = steamId;
	}
	public void setSteamId2(String steamId2){
		this.steamId2 = steamId2;
	}
	public void setgamesWon(Long gamesWon){
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
	public Long getgamesWon(){
		return gamesWon;
	}
	public Long gettotalGames(){
		return totalGames;
	}
	public BigDecimal getWinPercentage(){
		return winPercentage;
	}
	
}
