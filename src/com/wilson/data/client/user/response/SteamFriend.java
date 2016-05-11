package com.wilson.data.client.user.response;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "friends")
public class SteamFriend  {

	public SteamFriend(){
	}
	
	private String steamId;
	private String relationship;
	private Long friendSince;
	private String steamName;
	private Long gamesWon;
	private Long totalGames;
	private BigDecimal winPercentage;
	
	@JsonProperty("steamid")
	@Column(name = "steam_id")
	public String getSteamId() {
		return steamId;
	}
	@Column(name = "steam_id")
	@JsonProperty("steamid")
	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}
	@Column(name = "relationship")
	@JsonProperty("relationship")
	public String getRelationship() {
		return relationship;
	}
	@Column(name = "relationship")
	@JsonProperty("relationship")
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Column(name = "friend_since")
	@JsonProperty("friend_since")
	public Long getFriendSince() {
		return friendSince;
	}
	@Column(name = "friend_since")
	@JsonProperty("friend_since")
	public void setFriendSince(Long friendSince) {
		this.friendSince = friendSince;
	}

	public void setFriendAlias(String steamName){
		this.steamName = steamName;
	}
	public String getFriendAlias(){
		return steamName;
	}
	
	public Long getTotalGames() {
		return totalGames;
	}
	public void setTotalGames(Long totalGames) {
		this.totalGames = totalGames;
	}
	public BigDecimal getWinPercentage() {
		return winPercentage;
	}
	public void setWinPercentage(BigDecimal winPercentage) {
		this.winPercentage = winPercentage;
	}
	public Long getGamesWon() {
		return gamesWon;
	}
	public void setGamesWon(Long gamesWon) {
		this.gamesWon = gamesWon;
	}
	
}
