package com.wilson.data.shared;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchHistoryBySequence {
	private int status;
	private String statusDetail;
	private List<MatchDetail> matches;
	
	//constructor
	MatchHistoryBySequence(){}
	
	
	//setter

	public void setStatus(int status){
		this.status = status;
	}
	@JsonProperty("status_detail")
	public void setStatusDetail(String statusDetail){
		this.statusDetail = statusDetail;
	}
	public void setMatches(List<MatchDetail> matches){
		this.matches = matches;
		
	}

	
	
	
	//getter
	
	public int getStatus(){
		return status;
	}
	@JsonProperty("status_detail")
	public String getStatusDetail(){
		return statusDetail;
	}

	public List<MatchDetail> getMatches(){
		return matches;
		
	}
}
