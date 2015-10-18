package com.wilson.client.dota.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchDetailResult {
	
	private List<MatchDetailPlayer> players;
	private boolean radiantWin;
	private Long duration; //time in seconds
	private Long startTime; //date in UTC seconds since Jan 1, 1970 (unix time format)
	private Long matchId;
	private Long matchSeqNum; //the match's sequence number - the order in which matches are recorded
	private int towerStatusRadiant;
	private int towerStatusDire;
	private int barracksStatusRadiant;
	private int barracksStatusDire;
	private int cluster;
	private Long firstBloodTime;
	private int lobbyType;
	private int humanPlayers;
	private int leagueId;
	private int positiveVotes;
	private int negativeVotes;
	private int gameMode;
	private int engine;

	//Constructor
	public MatchDetailResult(){
		
	}
	
	//Getter
	
	public List<MatchDetailPlayer> getPlayers(){
		return players;
	}
	@JsonProperty("account_id")
	public boolean getRadiantWin(){
		return radiantWin;
	}
	public Long getDuration(){
		return duration;
	}	
	@JsonProperty("start_time")
	public Long getStartTime(){
		return startTime;
	}	
	@JsonProperty("match_id")
	public Long getMatchId(){
		return matchId;
	}	
	@JsonProperty("match_seq_num")
	public Long getMatchSeqNum(){
		return matchSeqNum;
	}	
	@JsonProperty("tower_status_radiant")
	public int getTowerStatusRadiant(){
		return towerStatusRadiant;
	}	
	@JsonProperty("tower_status_dire")
	public int getTowerStatusDire(){
		return towerStatusDire;
	}	
	@JsonProperty("barracks_status_radiant")
	public int getBarracksStatusRadiant(){
		return barracksStatusRadiant;
	}
	@JsonProperty("barracks_status_dire")
	public int getBarracksStatusDire(){
		return barracksStatusDire;
	}
	public int getCluster(){
		return cluster;
	}
	@JsonProperty("first_blood_time")
	public Long getFirstBloodTime(){
		return firstBloodTime;
	}
	@JsonProperty("lobby_type")
	public int getLobbyType(){
		return lobbyType;
	}
	@JsonProperty("human_players")
	public int getHumanPlayers(){
		return humanPlayers;
	}
	@JsonProperty("leagueid")
	public int getLeagueId(){
		return leagueId;
	}
	@JsonProperty("positive_votes")
	public int getPositiveVotes(){
		return positiveVotes;
	}
	@JsonProperty("negative_votes")
	public int getNegativeVotes(){
		return negativeVotes;
	}
	@JsonProperty("game_mode")
	public int getGameMode(){
		return gameMode;
	}
	public int getEngine(){
		return engine;
	}
	//Setter
	public void setPlayers(List<MatchDetailPlayer> players){
		this.players = players;
	}
	@JsonProperty("radiant_win")
	public void setrRadiantWin(boolean radiantWin){
		this.radiantWin = radiantWin;
	}
	public void setDuration(Long duration){
		this.duration = duration;
	}
	@JsonProperty("start_time")
	public void setStartTime(Long startTime){
		this.startTime = startTime;
	}
	@JsonProperty("match_id")
	public void setMatchId(Long matchId){
		this.matchId = matchId;
	}
	@JsonProperty("match_seq_num")
	public void setMatchSeqNum(Long matchSeqNum){
		this.matchSeqNum = matchSeqNum;
	}
	@JsonProperty("tower_status_radiant")
	public void setTowerStatusRadiant(int towerStatusRadiant){
		this.towerStatusRadiant = towerStatusRadiant;
	}
	@JsonProperty("tower_status_dire")
	public void setTowerStatusDire(int towerStatusDire){
		this.towerStatusDire = towerStatusDire;
	}
	@JsonProperty("barracks_status_radiant")
	public void setBarracksStatusRadiant(int barracksStatusRadiant){
		this.barracksStatusRadiant = barracksStatusRadiant;
	}
	@JsonProperty("barracks_status_dire")
	public void setBarracksStatusDire(int barracksStatusDire){
		this.barracksStatusDire = barracksStatusDire;
	}
	public void setcluster(int cluster){
		this.cluster = cluster;
	}
	@JsonProperty("first_blood_time")
	public void setFirstBloodTime(Long firstBloodTime){
		this.firstBloodTime = firstBloodTime;
	}
	@JsonProperty("lobby_type")
	public void setLobbyType(int lobbyType){
		this.lobbyType = lobbyType;
	}
	@JsonProperty("human_players")
	public void setHumanPlayers(int humanPlayers){
		this.humanPlayers = humanPlayers;
	}
	@JsonProperty("leagueid")
	public void setLeagueId(int leagueId){
		this.leagueId = leagueId;
	}
	@JsonProperty("positive_votes")
	public void setPositiveVotes(int positiveVotes){
		this.positiveVotes = positiveVotes;
	}
	@JsonProperty("negative_votes")
	public void setNegativeVotes(int negativeVotes){
		this.negativeVotes = negativeVotes;
	}
	@JsonProperty("game_mode")
	public void setGameMode(int gameMode){
		this.gameMode = gameMode;
	}
	
}
