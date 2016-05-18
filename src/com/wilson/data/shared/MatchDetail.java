package com.wilson.data.shared;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "match_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetail {
	
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
	private Boolean practiceMatch;

	//Constructor
	public MatchDetail(){
		
	}
	
	public MatchDetail(Long matchId){
		this.matchId = matchId;
	}
	
	//Getter
	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "match_id")
	@Cascade({CascadeType.ALL})
	public List<MatchDetailPlayer> getPlayers(){
		return players;
	}

	@Column(name = "radiant_win")
	@JsonProperty("radiant_win")
	public boolean getRadiantWin(){
		return radiantWin;
	}
	@Column(name = "duration")
	public Long getDuration(){
		return duration;
	}	
	@Column(name = "start_time")
	@JsonProperty("start_time")
	public Long getStartTime(){
		return startTime;
	}	
	@Id
	@Column(name = "match_id")
	@JsonProperty("match_id")
	public Long getMatchId(){
		return matchId;
	}	
	@Column(name = "match_seq_num")
	@JsonProperty("match_seq_num")
	public Long getMatchSeqNum(){
		return matchSeqNum;
	}	
	@Column(name = "tower_status_radiant")
	@JsonProperty("tower_status_radiant")
	public int getTowerStatusRadiant(){
		return towerStatusRadiant;
	}	
	@Column(name = "tower_status_dire")
	@JsonProperty("tower_status_dire")
	public int getTowerStatusDire(){
		return towerStatusDire;
	}	
	@Column(name = "barracks_status_radiant")
	@JsonProperty("barracks_status_radiant")
	public int getBarracksStatusRadiant(){
		return barracksStatusRadiant;
	}
	@Column(name = "barracks_status_dire")
	@JsonProperty("barracks_status_dire")
	public int getBarracksStatusDire(){
		return barracksStatusDire;
	}
	@Column(name = "cluster")
	public int getCluster(){
		return cluster;
	}
	@Column(name = "first_blood_time")
	@JsonProperty("first_blood_time")
	public Long getFirstBloodTime(){
		return firstBloodTime;
	}
	@Column(name = "lobby_type")
	@JsonProperty("lobby_type")
	public int getLobbyType(){
		return lobbyType;
	}
	@Column(name = "human_players")
	@JsonProperty("human_players")
	public int getHumanPlayers(){
		return humanPlayers;
	}
	@Column(name = "league_id")
	@JsonProperty("leagueid")
	public int getLeagueId(){
		return leagueId;
	}
	@Column(name = "positive_votes")
	@JsonProperty("positive_votes")
	public int getPositiveVotes(){
		return positiveVotes;
	}
	@Column(name = "negative_votes")
	@JsonProperty("negative_votes")
	public int getNegativeVotes(){
		return negativeVotes;
	}
	@Column(name = "game_mode")
	@JsonProperty("game_mode")
	public int getGameMode(){
		return gameMode;
	}
	@Column(name = "engine")
	public int getEngine(){
		return engine;
	}

	//Setter
	public void setPlayers(List<MatchDetailPlayer> players){
		this.players = players;
	}
	@Column(name = "radiant_win")
	@JsonProperty("radiant_win")
	public void setRadiantWin(boolean radiantWin){
		this.radiantWin = radiantWin;
	}
	@Column(name = "duration")
	public void setDuration(Long duration){
		this.duration = duration;
	}
	@Column(name = "start_time")
	@JsonProperty("start_time")
	public void setStartTime(Long startTime){
		this.startTime = startTime;
	}
	@Column(name = "match_id")
	@JsonProperty("match_id")
	public void setMatchId(Long matchId){
		this.matchId = matchId;
	}
	@Column(name = "match_seq_num")
	@JsonProperty("match_seq_num")
	public void setMatchSeqNum(Long matchSeqNum){
		this.matchSeqNum = matchSeqNum;
	}
	@Column(name = "tower_status_radiant")
	@JsonProperty("tower_status_radiant")
	public void setTowerStatusRadiant(int towerStatusRadiant){
		this.towerStatusRadiant = towerStatusRadiant;
	}
	@Column(name = "tower_status_dire")
	@JsonProperty("tower_status_dire")
	public void setTowerStatusDire(int towerStatusDire){
		this.towerStatusDire = towerStatusDire;
	}
	@Column(name = "barracks_status_radiant")
	@JsonProperty("barracks_status_radiant")
	public void setBarracksStatusRadiant(int barracksStatusRadiant){
		this.barracksStatusRadiant = barracksStatusRadiant;
	}
	@Column(name = "barracks_status_dire")
	@JsonProperty("barracks_status_dire")
	public void setBarracksStatusDire(int barracksStatusDire){
		this.barracksStatusDire = barracksStatusDire;
	}
	@Column(name = "cluster")
	public void setcluster(int cluster){
		this.cluster = cluster;
	}
	@Column(name = "first_blood_time")
	@JsonProperty("first_blood_time")
	public void setFirstBloodTime(Long firstBloodTime){
		this.firstBloodTime = firstBloodTime;
	}
	@Column(name = "lobby_type")
	@JsonProperty("lobby_type")
	public void setLobbyType(int lobbyType){
		this.lobbyType = lobbyType;
	}
	@Column(name = "human_players")
	@JsonProperty("human_players")
	public void setHumanPlayers(int humanPlayers){
		this.humanPlayers = humanPlayers;
	}
	@Column(name = "league_id")
	@JsonProperty("leagueid")
	public void setLeagueId(int leagueId){
		this.leagueId = leagueId;
	}
	@Column(name = "positive_votes")
	@JsonProperty("positive_votes")
	public void setPositiveVotes(int positiveVotes){
		this.positiveVotes = positiveVotes;
	}
	@Column(name = "negative_votes")
	@JsonProperty("negative_votes")
	public void setNegativeVotes(int negativeVotes){
		this.negativeVotes = negativeVotes;
	}
	@Column(name = "game_mode")
	@JsonProperty("game_mode")
	public void setGameMode(int gameMode){
		this.gameMode = gameMode;
	}
	@Column(name = "engine")
	public void setEngine(int engine){
		this.engine = engine;
		
		
	}

	
}
