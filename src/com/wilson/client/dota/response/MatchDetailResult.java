package com.wilson.client.dota.response;

import java.util.List;

public class MatchDetailResult {
	
	private List<MatchDetailPlayer> players;
	
	//Constructor
	public MatchDetailResult(){
		
	}
	
	//Getter
	public List<MatchDetailPlayer> getPlayers(){
		return players;
	}
	
	//Setter
	public void setPlayers(List<MatchDetailPlayer> players){
		this.players = players;
	}
}
