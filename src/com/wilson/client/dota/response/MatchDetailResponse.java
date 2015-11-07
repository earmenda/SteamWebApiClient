package com.wilson.client.dota.response;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.wilson.shared.MatchDetail;



public class MatchDetailResponse implements java.io.Serializable{

	private MatchDetail result;

	
	//constructor
	public MatchDetailResponse(){
		
	}
	
	
	//setter
	public void setResult(MatchDetail result){
		this.result = result;
	}
	
		
	
	//getter
	public MatchDetail getResult(){
		return result;
	}
}
