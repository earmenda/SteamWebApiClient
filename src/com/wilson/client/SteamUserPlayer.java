package com.wilson.client;

public class SteamUserPlayer {
		
		private String steamId;
		private String success;
		private String communityVisibilityState;
		private String profileState;
		private String personaName;
		private int lastLogoff;
		private String profileUrl;
		private String avatar;
		private String avatarMedium;
		private String avatarFull;
		private String personaState; 
		private String primaryClanId;
		private int timeCreated;
		private int personaStateFlags;
		private String gameExtraInfo;
		private String gameId;
		
		public SteamUserPlayer() {
		}

		public String getSteamId() {
			return steamId;
		}
		
		public String getSuccess() {
			return success;
		}
				
		public String getCommunityVisibilityState(){
			return communityVisibilityState;
		}
		
		public String getProfileState(){
			return profileState;
			}
		public String getPersonaName(){
			return personaName;
		}
		public int getLastLogoff(){
			return lastLogoff;
		}
		public String getProfileUrl(){
			return profileUrl;
		}
		public String getAvatar(){
			return avatar;
		}
		public String getAvatarMedium(){
			return avatarMedium;
		}
		public String getAvatarFull(){
			return avatarFull;
		}
		public String getPersonaState(){
			return personaState;
		}
		public String getPrimaryClanId(){
			return primaryClanId;
		}
		public int getTimeCreated(){
			return timeCreated;
		}
		public int getPersonaStateFlags(){
			return personaStateFlags;
		}
		public String getGameExtraInfo(){
			return gameExtraInfo;
		}
		public String getGameId(){
			return gameId;
		}
		
		public void setSteamid(String steamid){
			this.steamId = steamid;
		}
		
		public void setSuccess(String success){
			this.success = success;
		}
	}

