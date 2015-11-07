package com.wilson.data.client;

import java.util.ArrayList;
import java.util.List;

public class Party {
	
	//public String steamID;
	public List<String> members = new ArrayList<String>();
	
	
	//Constructor
	public Party(){
		
	}
	
	//Setter
	public void add(String steamId){
		this.members.add(steamId);
	}
	
	//getter
	public List<String> get(){
		return members;
	}
	
	
	public String getUser(String steamId){
		String temp = "";
		for (int i =0; i<this.members.size(); i++){
			if (this.members.get(i) == steamId){
				temp = members.get(i).toString();
				break;
			}
		}
		return temp;

	}
	
}
