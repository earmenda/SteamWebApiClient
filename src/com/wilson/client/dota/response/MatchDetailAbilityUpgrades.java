package com.wilson.client.dota.response;

public class MatchDetailAbilityUpgrades {
	private int ability;
	private int time;
	private int level;
	
	//Constructor 
	public MatchDetailAbilityUpgrades(){
		
	}
	
	//Setter
	public void setAbility(int ability){
		this.ability = ability;
	}
	public void setTime(int time){
		this.time = time;
	}
	public void setLevel(int level){
		this.level = level;
	}
	
	//Getter
	public int getAbility(){
		return ability;
	}
	public int getTime(){
		return time;
	}
	public int getLevel(){
		return level;
	}
}

