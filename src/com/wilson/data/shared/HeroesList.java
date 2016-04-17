package com.wilson.data.shared;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeroesList {
	
	public HeroesList(){}
	
	private String name;
	private int id;
	private String localizedName; 
	
	
	public String getName(){
		return name;
	}
	public int getId(){
		return id;
	}
	public String localizedName(){
		return localizedName;
	}
	
	@JsonProperty("name")
	public void setName(String name){
		this.name = name;
	}
	@JsonProperty("id")
	public void setId(int id){
		this.id = id;
	}
	@JsonProperty("localized_name")
	public void setLocalizedName(String localizedName){
		this.localizedName = localizedName;
	}
	
}
