package com.wilson.client.dota.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "match_detail_player_ability")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetailAbilityUpgrades {
	private int ability;
	private int time;
	private int level;
	private int id;
	
	//Constructor 
	public MatchDetailAbilityUpgrades(){
		
	}
	//Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	@Column(name = "ability")
	public int getAbility(){
		return ability;
	}
	@Column(name = "time")
	public int getTime(){
		return time;
	}
	@Column(name = "level")
	public int getLevel(){
		return level;
	}
	
	//Setter
	@Column(name = "id")
	public void setId(int id){
		this.id = id;
	}
	@Column(name = "ability")
	public void setAbility(int ability){
		this.ability = ability;
	}
	@Column(name = "time")
	public void setTime(int time){
		this.time = time;
	}
	@Column(name = "level")
	public void setLevel(int level){
		this.level = level;
	}


	

}

