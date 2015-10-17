package com.wilson.client.dota.response;

import java.util.List;

public class MatchHistoryResult {
	private int status;
	private int numResults;
	private int totalResults;
	private int resultsRemaining;
	private List<MatchHistoryMatch> matches;
	
	//constructor
	MatchHistoryResult(){}
	
	
	//setter

	public void setStatus(int status){
		this.status = status;
	}
	
	public void setnumResults(int numResults){
		this.numResults = numResults;
	}
	public void settotalResults(int totalResults){
		this.totalResults = totalResults;
	}
	public void setResultsRemaining(int resultsRemaining){
		this.resultsRemaining = resultsRemaining;
	}

	public void setMatches(List<MatchHistoryMatch> matches){
		this.matches = matches;
		
	}

	
	
	
	//getter
	
	public int getStatus(){
		return status;
	}
	
	public int getnumResults(){
		return numResults;
	}
	public int gettotalResults(){
		return totalResults;
	}
	public int getResultsRemaining(){
		return resultsRemaining;
	}

	public List<MatchHistoryMatch> getMatches(){
		return matches;
		
	}
}

