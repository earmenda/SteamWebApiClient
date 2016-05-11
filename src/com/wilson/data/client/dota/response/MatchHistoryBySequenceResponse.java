package com.wilson.data.client.dota.response;

import com.wilson.data.shared.MatchHistoryBySequence;
import com.wilson.data.shared.MatchHistoryResults;

public class MatchHistoryBySequenceResponse {
	private MatchHistoryBySequence result;

	
	public MatchHistoryBySequenceResponse(){
		
	}
	
	//Setter
	public void setResult(MatchHistoryBySequence result){
		this.result = result;
	}
	
	//Getter
	public MatchHistoryBySequence getResult(){
		return result;
	}
}
