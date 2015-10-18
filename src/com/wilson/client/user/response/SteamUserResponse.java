package com.wilson.client.user.response;

import java.util.List;

public class SteamUserResponse {
	private List<SteamUserPlayer> players;
	
	public SteamUserResponse(){
		
	}
	//Setter
	public void setPlayers(List<SteamUserPlayer> players){
		this.players = players;
	}
	//Getter
	public List<SteamUserPlayer> getPlayers(){
		return players;
		}
	}
