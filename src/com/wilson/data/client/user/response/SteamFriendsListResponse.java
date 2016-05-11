package com.wilson.data.client.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wilson.data.shared.MatchHistoryResults;


public class SteamFriendsListResponse {
	
	private SteamFriendsList friendsList;
	
	public SteamFriendsListResponse(){
		
	}
	
	@JsonProperty("friendslist")
	public SteamFriendsList getFriendsList() {
		return friendsList;
	}
	
	@JsonProperty("friendslist")
	public void setFriendsList(SteamFriendsList friendsList) {
		this.friendsList = friendsList;
	}
	
	
}

