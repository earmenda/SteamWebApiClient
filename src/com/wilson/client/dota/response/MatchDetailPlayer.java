package com.wilson.client.dota.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetailPlayer {
	private Long accountId;
	private int playerSlot;
	private int heroId;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int kills;
	private int deaths;
	private int assists;
	private int leaverStatus;
	private int gold;
	private int lastHits;
	private int denies;
	private int goldPerMin;
	private int xpPerMin;
	private int goldSpent;
	private int heroDamage;
	private int towerDamage;
	private int heroHealing;
	private int level;
	private List<MatchDetailAbilityUpgrades> abilityUpgrades;
	private List<MatchDetailAdditionalUnits> additionalUnits;

	
	//Constructor
	public MatchDetailPlayer(){
		
	}
	//Getters
	@JsonProperty("account_id")
	public Long getAccountId() {
		return accountId;
	}
	@JsonProperty("player_slot")
	public int getPlayer_slot() {
		return playerSlot;
	}
	@JsonProperty("hero_id")
	public int getHeroId(){
		return heroId;
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
	public int getKills(){
		return kills;
	}
	public int getDeaths(){
		return deaths;
	}
	public int getAssists(){
		return assists;
	}
	@JsonProperty("leaver_status")
	public int getLeaverStatus(){
		return leaverStatus;
	}
	public int getGold(){
		return gold;
	}
	@JsonProperty("last_hits")
	public int getLastHits(){
		return lastHits;
	}
	public int getDenies(){
		return denies;
	}
	@JsonProperty("gold_per_min")
	public int getGoldPerMinute(){
		return goldPerMin;
	}
	@JsonProperty("xp_per_min")
	public int getXpPerMin(){
		return xpPerMin;
	}
	@JsonProperty("gold_spent")
	public int getGoldSpent(){
		return goldSpent;
	}
	@JsonProperty("hero_damage")
	public int getHeroDamage(){
		return heroDamage;
	}
	@JsonProperty("tower_damage")
	public int getTowerDamage(){
		return towerDamage;
	}
	@JsonProperty("hero_healing")
	public int getHeroHealing(){
		return heroHealing;
	}
	public int getLevels(){
		return level;
	}	
	@JsonProperty("ability_upgrades")
	public List<MatchDetailAbilityUpgrades> getAbilityUpgrades(){
		return abilityUpgrades;
	}
	@JsonProperty("additional_units")
	public List<MatchDetailAdditionalUnits> getAdditionalUnits(){
		return additionalUnits;
	}


	
	//Setters
	@JsonProperty("account_id")
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	@JsonProperty("player_slot")
	public void setPlayerSlot(int playerSlot){
		this.playerSlot = playerSlot;
	}
	@JsonProperty("hero_id")
	public void setHeroId(int heroId){
		this.heroId = heroId;
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
	public void setKills(int kills){
		this.kills = kills;
	}
	public void setDeaths(int deaths){
		this.deaths = deaths;
	}
	public void setAssists(int assists){
		this.assists = assists;
	}
	@JsonProperty("leaver_status")
	public void setLeaverStatus(int leaverStatus){
		this.leaverStatus = leaverStatus;
	}
	public void setGold(int gold){
		this.gold = gold;
	}
	@JsonProperty("last_hits")
	public void setLastHits(int lastHits){
		this.lastHits = lastHits;
	}
	public void setDenies(int denies){
		this.denies = denies;
	}
	@JsonProperty("gold_per_min")
	public void setGoldPerMin(int goldPerMin){
		this.goldPerMin = goldPerMin;
	}
	@JsonProperty("xp_per_min")
	public void setXpPerMin(int xpPerMin){
		this.xpPerMin = xpPerMin;
	}
	@JsonProperty("gold_spent")
	public void setGoldSpent(int goldSpent){
		this.goldSpent = goldSpent;
	}
	@JsonProperty("hero_damage")
	public void setHeroDamage(int heroDamage){
		this.heroDamage = heroDamage;
	}@JsonProperty("tower_damage")
	public void setTowerDamage(int towerDamage){
		this.towerDamage = towerDamage;
	}
	@JsonProperty("hero_healing")
	public void setHeroHealing(int heroHealing){
		this.heroHealing = heroHealing;
	}
	public void setLevel(int level){
		this.level = level;
	}
	@JsonProperty("ability_upgrades")
	public void setAbiltiyUpgrades(List<MatchDetailAbilityUpgrades> abilityUpgrades){
		this.abilityUpgrades = abilityUpgrades;
	}
	@JsonProperty("additional_units")
	public void setAdditionalUnits(List<MatchDetailAdditionalUnits> additionalUnits){
		this.additionalUnits = additionalUnits;
	}
}

