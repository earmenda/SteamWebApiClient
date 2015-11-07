package com.wilson.data.client.user.response;

import java.util.List;

public class SteamPlayerSummaryDetails {
	private List<SteamPlayer> players;
	
	public SteamPlayerSummaryDetails(){
		
	}
	//Setter
	public void setPlayers(List<SteamPlayer> players){
		this.players = players;
	}
	//Getter
	public List<SteamPlayer> getPlayers(){
		return players;
		}
	}
