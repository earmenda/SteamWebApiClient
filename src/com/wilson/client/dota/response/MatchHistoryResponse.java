package com.wilson.client.dota.response;

public class MatchHistoryResponse {
	private MatchHistoryResults result;
	
	public MatchHistoryResponse(){
		
	}
	
	//Setter
	public void setResult(MatchHistoryResults result){
		this.result = result;
	}
	
	//Getter
	public MatchHistoryResults getResult(){
		return result;
	}
}
