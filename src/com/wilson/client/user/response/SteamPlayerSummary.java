package com.wilson.client.user.response;


public class SteamPlayerSummary {
	
	private SteamPlayerSummaryDetails response;
	
	public SteamPlayerSummary(){
	}
	
	//Setter
	public void setResponse(SteamPlayerSummaryDetails response){
		this.response = response;
	}
	//Getter
	public SteamPlayerSummaryDetails getResponse(){
		return response;
	}
	}
