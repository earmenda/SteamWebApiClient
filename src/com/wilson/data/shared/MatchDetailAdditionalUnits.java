package com.wilson.data.shared;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "match_detail_additional_units")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetailAdditionalUnits {

	private String unitName;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int id;
	
	public MatchDetailAdditionalUnits(){
	}
	
	//Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	@Column(name = "unit_name")
	public String getUnitName(){
		return unitName;
	}
	@Column(name = "item0")
	@JsonProperty("item_0")
	public int getItem0(){
		return item0;
		}
	@Column(name = "item1")
	@JsonProperty("item_1")
	public int getItem1(){
		return item1;
	}
	@Column(name = "item2")
	@JsonProperty("item_2")
	public int getItem2(){
		return item2;
	}
	@Column(name = "item3")
	@JsonProperty("item_3")
	public int getItem3(){
		return item3;
	}
	@Column(name = "item4")
	@JsonProperty("item_4")
	public int getItem4(){
		return item4;
	}
	@Column(name = "item5")
	@JsonProperty("item_5")
	public int getItem5(){
		return item5;
	}
	
	//Setter
	@JsonProperty("unitname")
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
	@Column(name = "id")
	public void setId(int id){
		this.id = id;
	}
	
}
