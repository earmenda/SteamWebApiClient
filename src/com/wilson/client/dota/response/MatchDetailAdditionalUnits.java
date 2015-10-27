package com.wilson.client.dota.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchDetailAdditionalUnits {

	private String unitName;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	
	public MatchDetailAdditionalUnits(){
	}
	
	//Getter
	public String getUnitName(){
		return unitName;
	}
	@JsonProperty("item_0")
	public int getItem0(){
		return item0;
		}
	@JsonProperty("item_1")
	public int getItem1(){
		return item1;
	}
	@JsonProperty("item_2")
	public int getItem2(){
		return item2;
	}
	@JsonProperty("item_3")
	public int getItem3(){
		return item3;
	}
	@JsonProperty("item_4")
	public int getItem4(){
		return item4;
	}
	@JsonProperty("item_5")
	public int getItem5(){
		return item5;
	}
	
	//Setter
	public void setUnitName(String unitName){
		this.unitName = unitName;
	}
	@JsonProperty("item_0")
	public void setItem0(int item0){
		this.item0 = item0;
	}
	@JsonProperty("item_1")
	public void setItem1(int item1){
		this.item1 = item1;
	}
	@JsonProperty("item_2")
	public void setItem2(int item2){
		this.item2 = item2;
	}
	@JsonProperty("item_3")
	public void setItem3(int item3){
		this.item3 = item3;
	}
	@JsonProperty("item_4")
	public void setItem4(int item4){
		this.item4 = item4;
	}
	@JsonProperty("item_5")
	public void setItem5(int item5){
		this.item5 = item5;
	}
}
