package com.wilson.client.dota.response;

import java.util.List;

public class MatchDetailPlayer {
	private int accountId;
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

	
	//Constructor
	public MatchDetailPlayer(){
		
	}
	//Getters
	public int getAccountId() {
		return accountId;
	}
	public int getPlayerSlot() {
		return playerSlot;
	}
	public int getHeroId(){
		return heroId;
	}
	public int getItem0(){
		return item0;
		}
	public int getItem1(){
		return item1;
	}
	public int getItem2(){
		return item2;
	}
	public int getItem3(){
		return item3;
	}
	public int getItem4(){
		return item4;
	}
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
	public int getLeaverStatus(){
		return leaverStatus;
	}
	public int getGold(){
		return gold;
	}
	public int getLastHits(){
		return lastHits;
	}
	public int getDenies(){
		return denies;
	}
	public int getGoldPerMinute(){
		return goldPerMin;
	}
	public int getXpPerMin(){
		return xpPerMin;
	}
	public int getGoldSpent(){
		return assists;
	}
	public int getHeroDamage(){
		return heroDamage;
	}
	public int getTowerDamage(){
		return towerDamage;
	}
	public int getHeroHealing(){
		return heroHealing;
	}
	public int getLevels(){
		return denies;
	}
	public List<MatchDetailAbilityUpgrades> getAbilityUpgrades(){
		return abilityUpgrades;
	}
	
	
	//Setters
	public void setAccountId(int accountId){
		this.accountId = accountId;
	}
	public void setPlayerSlot(int playerSlot){
		this.playerSlot = playerSlot;
	}
	public void setHeroId(int heroId){
		this.heroId = heroId;
	}
	public void setItem0(int item0){
		this.item0 = item0;
	}
	public void setItem1(int item1){
		this.item1 = item1;
	}
	public void setItem2(int item2){
		this.item2 = item2;
	}
	public void setItem3(int item3){
		this.item3 = item3;
	}
	public void setItem4(int item4){
		this.item4 = item4;
	}
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
	public void setLeaverStatus(int leaverStatus){
		this.leaverStatus = leaverStatus;
	}
	public void setGold(int gold){
		this.gold = gold;
	}
	public void setLastHits(int lastHits){
		this.lastHits = lastHits;
	}
	public void setDenies(int denies){
		this.denies = denies;
	}
	public void setGoldPerMin(int goldPerMin){
		this.goldPerMin = goldPerMin;
	}
	public void setXpPerMin(int xpPerMin){
		this.xpPerMin = xpPerMin;
	}
	public void setGoldSpent(int goldSpent){
		this.goldSpent = goldSpent;
	}
	public void setHeroDamage(int heroDamage){
		this.heroDamage = heroDamage;
	}
	public void setTowerDamage(int towerDamage){
		this.towerDamage = towerDamage;
	}
	public void setHeroHealing(int heroHealing){
		this.heroHealing = heroHealing;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public void setAbiltiyUpgrades(List<MatchDetailAbilityUpgrades> abilityUpgrades){
		this.abilityUpgrades = abilityUpgrades;
	}
}

