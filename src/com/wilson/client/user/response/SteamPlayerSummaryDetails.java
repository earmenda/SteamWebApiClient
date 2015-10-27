package com.wilson.client.user.response;

import java.util.List;

public class SteamPlayerSummaryDetails {
	private List<SteamPlayerSummaryResponse> players;
	
	public SteamPlayerSummaryDetails(){
		
	}
	//Setter
	public void setPlayers(List<SteamPlayerSummaryResponse> players){
		this.players = players;
	}
	//Getter
	public List<SteamPlayerSummaryResponse> getPlayers(){
		return players;
		}
	}
