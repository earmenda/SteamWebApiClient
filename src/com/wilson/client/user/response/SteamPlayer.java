package com.wilson.client.user.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "player_summary")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamPlayer implements Serializable {
		
		private String steamId;
		private int communityVisibilityState;
		private int profileState;
		private String personaName;
		private Long lastLogoff; //The last time the user was online, in unix time.
		private int commentPermission;
		private String profileUrl;
		private String avatar;
		private String avatarMedium;
		private String avatarFull;
		private int personaState; 
		private String realName;
		private String primaryClanId;
		private int timeCreated;
		private int personaStateFlags;
		private String gameExtraInfo;
		private String gameId;
		private String locCountryCode;
		
		public SteamPlayer() {
		}

		//Getters
		@Id
		@Column(name = "steam_id", unique = true, nullable = false)
		@JsonProperty("steamid")
		public String getSteamId() {
			return steamId;
		}
		@Column(name = "community_visibility_state")
		@JsonProperty("communityvisibilitystate")
		public int getCommunityVisibilityState(){
			return communityVisibilityState;
		}	
		@Column(name = "profile_state")
		@JsonProperty("profilestate")
		public int getProfileState(){
			return profileState;
			}
		@Column(name = "persona_name")
		@JsonProperty("personaname")
		public String getPersonaName(){
			return personaName;
		}
		@Column(name = "last_logoff")
		@JsonProperty("lastlogoff")
		public Long getLastLogoff(){
			return lastLogoff;
		}
		
		@JsonProperty("commentpermission")
		@Column(name = "comment_permission")
		public int getCommentPermission(){
			return commentPermission;
		}

		@Column(name = "profile_url")
		@JsonProperty("profileurl")
		public String getProfileUrl(){
			return profileUrl;
		}
		@Column(name = "avatar")
		public String getAvatar(){
			return avatar;
		}
		@Column(name = "avatar_medium")
		@JsonProperty("avatarmedium")
		public String getAvatarMedium(){
			return avatarMedium;
		}
		@Column(name = "avatar_full")
		@JsonProperty("avatarfull")
		public String getAvatarFull(){
			return avatarFull;
		}
		@Column(name = "persona_state")
		@JsonProperty("personastate")
		public int getPersonaState(){
			return personaState;
		}
		@Column(name = "real_name")
		@JsonProperty("realname")
		public String getRealName() {
			return realName;
		}
		@Column(name = "primary_clan_id")
		@JsonProperty("primaryclanid")
		public String getPrimaryClanId(){
			return primaryClanId;
		}
		@Column(name = "time_created")
		@JsonProperty("timecreated")
		public int getTimeCreated(){
			return timeCreated;
		}
		@Column(name = "persona_state_flags")
		@JsonProperty("personastateflags")
		public int getPersonaStateFlags(){
			return personaStateFlags;
		}
		@Column(name = "game_extra_info")
		@JsonProperty("gameextrainfo")
		public String getGameExtraInfo(){
			return gameExtraInfo;
		}
		@Column(name = "game_id")
		@JsonProperty("gameid")
		public String getGameId(){
			return gameId;
		}
		@Column(name = "loc_country_code")
		@JsonProperty("loccountrycode")
		public void setLocCountryCode(String locCountryCode) {
			this.locCountryCode = locCountryCode;
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
		@JsonProperty("commentpermission")
		@Column(name = "comment_permission")	
		public void setCommentPermission(int commentPermission){
			this.commentPermission = commentPermission;
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

		@Column(name = "real_name")
		@JsonProperty("realname")
		public void setRealName(String realName) {
			this.realName = realName;
		}
		@Column(name = "loc_country_code")
		@JsonProperty("loccountrycode")
		public String getLocCountryCode() {
			return locCountryCode;
		}

		

	}

