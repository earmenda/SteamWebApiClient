package com.wilson.data.client.dota.response;

import com.wilson.data.shared.MatchHistoryResults;

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
