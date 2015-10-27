package com.wilson.client.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SteamPlayerSummaryResponse {
		
		private String steamId;
		private int communityVisibilityState;
		private int profileState;
		private String personaName;
		private Long lastLogoff; //The last time the user was online, in unix time.
		private String profileUrl;
		private String avatar;
		private String avatarMedium;
		private String avatarFull;
		private int personaState; 
		private String primaryClanId;
		private int timeCreated;
		private int personaStateFlags;
		private String gameExtraInfo;
		private String gameId;
		
		public SteamPlayerSummaryResponse() {
		}

		//Getters
		@JsonProperty("steamid")
		public String getSteamId() {
			return steamId;
		}
		@JsonProperty("communityvisibilitystate")
		public int getCommunityVisibilityState(){
			return communityVisibilityState;
		}		
		@JsonProperty("profilestate")
		public int getProfileState(){
			return profileState;
			}
		@JsonProperty("personaname")
		public String getPersonaName(){
			return personaName;
		}
		@JsonProperty("lastlogoff")
		public Long getLastLogoff(){
			return lastLogoff;
		}
		@JsonProperty("profileurl")
		public String getProfileUrl(){
			return profileUrl;
		}
		public String getAvatar(){
			return avatar;
		}
		@JsonProperty("avatarmedium")
		public String getAvatarMedium(){
			return avatarMedium;
		}
		@JsonProperty("avatarfull")
		public String getAvatarFull(){
			return avatarFull;
		}
		@JsonProperty("personastate")
		public int getPersonaState(){
			return personaState;
		}
		@JsonProperty("primaryclanid")
		public String getPrimaryClanId(){
			return primaryClanId;
		}
		@JsonProperty("timecreated")
		public int getTimeCreated(){
			return timeCreated;
		}
		@JsonProperty("personastateflags")
		public int getPersonaStateFlags(){
			return personaStateFlags;
		}
		@JsonProperty("gameextrainfo")
		public String getGameExtraInfo(){
			return gameExtraInfo;
		}
		@JsonProperty("gameid")
		public String getGameId(){
			return gameId;
		}

		
		
		
		//Setters
		@JsonProperty("steamid")
		public void setSteamId(String steamId ){
			this.steamId = steamId;
		}
		@JsonProperty("communityvisibilitystate")
		public void setCommunityVisibilityState(int communityVisibilityState){
			this.communityVisibilityState = communityVisibilityState;
		}
		@JsonProperty("profilestate")
		public void setProfileState(int profileState){
			this.profileState = profileState;
		}
		@JsonProperty("personaname")
		public void setPersonaName(String personaName){
			this.personaName = personaName;
		}
		@JsonProperty("lastlogoff")
		public void setLastLogoff(Long lastLogoff){
			this.lastLogoff = lastLogoff;
		}
		@JsonProperty("profileurl")
		public void setProfileUrl(String profileUrl){
			this.profileUrl = profileUrl;
		}
		
		public void setAvatar(String avatar){
			this.avatar = avatar;
		}
		@JsonProperty("avatarmedium")
		public void setAvatarMedium(String avatarMedium){
			this.avatarMedium = avatarMedium;
		}
		@JsonProperty("avatarfull")
		public void setAvatarFull(String avatarFull){
			this.avatarFull = avatarFull;
		}
		@JsonProperty("personastate")
		public void setPersonaState(int personaState){
			this.personaState = personaState;
		}
		@JsonProperty("primaryclanid")
		public void setPrimaryClanId(String primaryClanId){
			this.primaryClanId = primaryClanId;
		}
		@JsonProperty("timecreated")
		public void setTimeCreated(int timeCreated){
			this.timeCreated = timeCreated;
		}
		@JsonProperty("personastateflags")
		public void setPersonaStateFlags(int personaStateFlags){
			this.personaStateFlags = personaStateFlags;
		}
		@JsonProperty("gameextrainfo")
		public void setGameExtraInfo(String gameExtraInfo){
			this.gameExtraInfo = gameExtraInfo;
		}
		@JsonProperty("gameid")
		public void setGameId(String gameId){
			this.gameId = gameId;
		}
		

	}

