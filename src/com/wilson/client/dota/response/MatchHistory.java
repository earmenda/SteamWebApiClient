package com.wilson.client.dota.response;

public class MatchHistory {
	private MatchHistoryResult result;
	
	public MatchHistory(){
		
	}
	
	//Setter
	public void setResult(MatchHistoryResult result){
		this.result = result;
	}
	
	//Getter
	public MatchHistoryResult getResult(){
		return result;
	}
}
