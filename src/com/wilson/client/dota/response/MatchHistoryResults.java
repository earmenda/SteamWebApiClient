package com.wilson.client.dota.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wilson.shared.MatchHistory;

public class MatchHistoryResults {
	private int status;
	private int numResults;
	private int totalResults;
	private int resultsRemaining;
	private List<MatchHistory> matches;
	
	//constructor
	MatchHistoryResults(){}
	
	
	//setter

	public void setStatus(int status){
		this.status = status;
	}
	@JsonProperty("num_results")
	public void setnumResults(int numResults){
		this.numResults = numResults;
	}
	@JsonProperty("total_results")
	public void settotalResults(int totalResults){
		this.totalResults = totalResults;
	}
	@JsonProperty("results_remaining")
	public void setResultsRemaining(int resultsRemaining){
		this.resultsRemaining = resultsRemaining;
	}

	public void setMatches(List<MatchHistory> matches){
		this.matches = matches;
		
	}

	
	
	
	//getter
	
	public int getStatus(){
		return status;
	}
	@JsonProperty("num_results")
	public int getnumResults(){
		return numResults;
	}
	@JsonProperty("total_results")
	public int getTotalResults(){
		return totalResults;
	}
	@JsonProperty("results_remaining")
	public int getResultsRemaining(){
		return resultsRemaining;
	}

	public List<MatchHistory> getMatches(){
		return matches;
		
	}
}

