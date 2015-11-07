package com.wilson.shared;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "match_detail_player")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetailPlayer {
	private String steamId; // accountid
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
	private int id;
	private List<MatchDetailAbilityUpgrades> abilityUpgrades;
	private List<MatchDetailAdditionalUnits> additionalUnits;

	// Constructor
	public MatchDetailPlayer() {
	}

	// Getters
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "match_id", nullable = false)
//	public MatchDetailResult getMatchDetailResult() {
//		System.out.println("MATCH DETAIL RESULT " + matchDetailResult);
//		return this.matchDetailResult;
//	}

	@Column(name = "steam_id")
	@JsonProperty("account_id")
	
	public String getSteamId() {
		return steamId;
	}

	@Column(name = "player_slot")
	@JsonProperty("player_slot")
	public int getPlayerSlot() {
		return playerSlot;
	}

	@Column(name = "hero_id")
	@JsonProperty("hero_id")
	public int getHeroId() {
		return heroId;
	}

	@Column(name = "item0")
	@JsonProperty("item_0")
	public int getItem0() {
		return item0;
	}

	@Column(name = "item1")
	@JsonProperty("item_1")
	public int getItem1() {
		return item1;
	}

	@Column(name = "item2")
	@JsonProperty("item_2")
	public int getItem2() {
		return item2;
	}

	@Column(name = "item3")
	@JsonProperty("item_3")
	public int getItem3() {
		return item3;
	}

	@Column(name = "item4")
	@JsonProperty("item_4")
	public int getItem4() {
		return item4;
	}

	@Column(name = "item5")
	@JsonProperty("item_5")
	public int getItem5() {
		return item5;
	}

	@Column(name = "kills")
	public int getKills() {
		return kills;
	}

	@Column(name = "deaths")
	public int getDeaths() {
		return deaths;
	}

	@Column(name = "assists")
	public int getAssists() {
		return assists;
	}

	@Column(name = "leaver_status")
	@JsonProperty("leaver_status")
	public int getLeaverStatus() {
		return leaverStatus;
	}

	@Column(name = "gold")
	public int getGold() {
		return gold;
	}

	@Column(name = "last_hits")
	@JsonProperty("last_hits")
	public int getLastHits() {
		return lastHits;
	}

	@Column(name = "denies")
	public int getDenies() {
		return denies;
	}

	@Column(name = "gold_per_min")
	@JsonProperty("gold_per_min")
	public int getGoldPerMin() {
		return goldPerMin;
	}

	@Column(name = "xp_per_min")
	@JsonProperty("xp_per_min")
	public int getXpPerMin() {
		return xpPerMin;
	}

	@Column(name = "gold_spent")
	@JsonProperty("gold_spent")
	public int getGoldSpent() {
		return goldSpent;
	}

	@Column(name = "hero_damage")
	@JsonProperty("hero_damage")
	public int getHeroDamage() {
		return heroDamage;
	}

	@Column(name = "tower_damage")
	@JsonProperty("tower_damage")
	public int getTowerDamage() {
		return towerDamage;
	}

	@Column(name = "hero_healing")
	@JsonProperty("hero_healing")
	public int getHeroHealing() {
		return heroHealing;
	}

	@Column(name = "level")
	public int getLevel() {
		return level;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	@JsonProperty("ability_upgrades")
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_player_id", referencedColumnName = "id")
	@Cascade({ CascadeType.ALL })
	 public List<MatchDetailAbilityUpgrades> getAbilityUpgrades(){
	 return abilityUpgrades;
	 }

	@JsonProperty("additional_units")
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_player_id", referencedColumnName = "id")
	@Cascade({ CascadeType.ALL })
	public List<MatchDetailAdditionalUnits> getAdditionalUnits() {
		return additionalUnits;
	}

	// Setters


	@Column(name = "steam_id")
	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}
	
	@JsonProperty("account_id")
	public void setSteamId(Long accountId) {
		if (accountId == Long.parseLong("4294967295")) {
			this.steamId = "0";
		} else {
			long steam64 = accountId + (Long.parseLong("76561197960265728"));
			this.steamId = steam64 + "";
		}
	}

	@Column(name = "player_slot")
	@JsonProperty("player_slot")
	public void setPlayerSlot(int playerSlot) {
		this.playerSlot = playerSlot;
	}

	@Column(name = "hero_id")
	@JsonProperty("hero_id")
	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	@Column(name = "item0")
	@JsonProperty("item_0")
	public void setItem0(int item0) {
		this.item0 = item0;
	}

	@Column(name = "item1")
	@JsonProperty("item_1")
	public void setItem1(int item1) {
		this.item1 = item1;
	}

	@Column(name = "item2")
	@JsonProperty("item_2")
	public void setItem2(int item2) {
		this.item2 = item2;
	}

	@Column(name = "item3")
	@JsonProperty("item_3")
	public void setItem3(int item3) {
		this.item3 = item3;
	}

	@Column(name = "item4")
	@JsonProperty("item_4")
	public void setItem4(int item4) {
		this.item4 = item4;
	}

	@Column(name = "item5")
	@JsonProperty("item_5")
	public void setItem5(int item5) {
		this.item5 = item5;
	}

	@Column(name = "kills")
	public void setKills(int kills) {
		this.kills = kills;
	}

	@Column(name = "deaths")
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	@Column(name = "assists")
	public void setAssists(int assists) {
		this.assists = assists;
	}

	@Column(name = "leaver_status")
	@JsonProperty("leaver_status")
	public void setLeaverStatus(int leaverStatus) {
		this.leaverStatus = leaverStatus;
	}

	@Column(name = "gold")
	public void setGold(int gold) {
		this.gold = gold;
	}

	@Column(name = "last_hits")
	@JsonProperty("last_hits")
	public void setLastHits(int lastHits) {
		this.lastHits = lastHits;
	}

	@Column(name = "denies")
	public void setDenies(int denies) {
		this.denies = denies;
	}

	@Column(name = "gold_per_min")
	@JsonProperty("gold_per_min")
	public void setGoldPerMin(int goldPerMin) {
		this.goldPerMin = goldPerMin;
	}

	@Column(name = "xp_per_min")
	@JsonProperty("xp_per_min")
	public void setXpPerMin(int xpPerMin) {
		this.xpPerMin = xpPerMin;
	}

	@Column(name = "gold_spent")
	@JsonProperty("gold_spent")
	public void setGoldSpent(int goldSpent) {
		this.goldSpent = goldSpent;
	}

	@Column(name = "hero_damage")
	@JsonProperty("hero_damage")
	public void setHeroDamage(int heroDamage) {
		this.heroDamage = heroDamage;
	}

	@Column(name = "tower_damage")
	@JsonProperty("tower_damage")
	public void setTowerDamage(int towerDamage) {
		this.towerDamage = towerDamage;
	}

	@Column(name = "hero_healing")
	@JsonProperty("hero_healing")
	public void setHeroHealing(int heroHealing) {
		this.heroHealing = heroHealing;
	}

	@Column(name = "level")
	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("ability_upgrades")
	public void setAbilityUpgrades(
			List<MatchDetailAbilityUpgrades> abilityUpgrades) {
		this.abilityUpgrades = abilityUpgrades;
	}

	@JsonProperty("additional_units")
	public void setAdditionalUnits(
			List<MatchDetailAdditionalUnits> additionalUnits) {
		this.additionalUnits = additionalUnits;
	}
}
