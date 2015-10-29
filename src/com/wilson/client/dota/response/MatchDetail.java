package com.wilson.client.dota.response;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



public class MatchDetail implements java.io.Serializable{

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
