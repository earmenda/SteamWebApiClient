package com.wilson.data.client.user.response;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SteamFriendsList {

	public SteamFriendsList(){}
	
	private List<SteamFriend> steamFriends;

	
	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "steam_id", referencedColumnName = "steam_id")
	@Cascade({CascadeType.ALL})
	@JsonProperty("friends")
	public List<SteamFriend> getSteamFriends() {
		return steamFriends;
	}

	@JsonProperty("friends")
	public void setSteamFriends(List<SteamFriend> steamFriends) {
		this.steamFriends = steamFriends;
	}
	
	
}
