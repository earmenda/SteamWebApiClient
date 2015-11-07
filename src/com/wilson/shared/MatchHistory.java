package com.wilson.shared;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wilson.client.dota.response.MatchHistoryPlayer;

public class MatchHistory {
	
	
	private Long matchId;
	private Long matchSeqNum;
	private Long startTime; // date in UTC seconds since Jan 1, 1970 (unix time format)
	private int lobbyType;
	private int radiantTeamId;
	private int direTeamId;
	private List<MatchHistoryPlayer> players;
	
	public MatchHistory(){
		
	}
	
	//Setters
	
	@JsonProperty("match_id")
	public void setMatchId(Long matchId){
		this.matchId = matchId;
	}
	@JsonProperty("match_seq_num")
	public void setMatchSeqNum(Long matchSeqNum){
		this.matchSeqNum = matchSeqNum;
	}
	@JsonProperty("start_time")
	public void setStartTime(Long startTime){
		this.startTime = startTime;
	}
	@JsonProperty("lobby_type")
	public void setLobbyType( int lobbyType){
		this.lobbyType = lobbyType;
	}
	@JsonProperty("radiant_team_id")
	public void setRadiantTeamId(int radiantTeamId){
		this.radiantTeamId = radiantTeamId;
	}
	@JsonProperty("dire_team_id")
	public void setDireTeamId(int direTeamId){
		this.direTeamId = direTeamId;
	}
	public void setPlayers(List<MatchHistoryPlayer> players){
		this.players = players;
	}
	
	
	//Getters
	@JsonProperty("match_id")
	public Long getMatchId(){
		return matchId;
	}
	@JsonProperty("match_seq_num")
	public Long getMatchSeqNum(){
		return matchSeqNum;
	}
	@JsonProperty("start_time")
	public Long getStartTime(){
		return startTime;
	}
	@JsonProperty("lobby_type")
	public int getLobbyType(){
		return lobbyType;
	}
	@JsonProperty("radiant_team_id")
	public int getRadiantTeamId(){
		return radiantTeamId;
	}
	@JsonProperty("dire_team_id")
	public int getDireTeamId(){
		return direTeamId;
	}
	public List<MatchHistoryPlayer> getPlayers(){
		return players;
	}
}
