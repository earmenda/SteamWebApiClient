package com.wilson.client.dota.response;

import java.util.List;

public class MatchDetail {
	
	private MatchDetailResult result;

	
	//constructor
	public MatchDetail(){
		
	}
	
	
	//setter
	public void setResult(MatchDetailResult result){
		this.result = result;
	}
		
	
	//getter
	public MatchDetailResult getResult(){
		return result;
	}
}
