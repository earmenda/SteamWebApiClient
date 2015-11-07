package com.wilson.data.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchHistoryPlayer {
	private Long accountId;
	private int playerSlot;
	private int heroId;
	
	public MatchHistoryPlayer(){
		
	}
	
	//Setters
	@JsonProperty("account_id")
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	@JsonProperty("player_slot")
	public void setPlayerSlot(int playerSlot){
		this.playerSlot = playerSlot;
	}
	@JsonProperty("hero_id")
	public void setHeroId(int heroId){
		this.heroId = heroId;
	}
	
	//Getters
	@JsonProperty("account_id")
	public Long getAccountId(){
		return accountId;
	}
	@JsonProperty("player_slot")
	public int getPlayerSlot(){
		return playerSlot;
	}
	@JsonProperty("hero_id")
	public int getHeroId(){
		return heroId;
	}
}
