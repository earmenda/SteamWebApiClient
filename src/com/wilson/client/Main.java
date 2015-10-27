package com.wilson.client;

import java.util.ArrayList;
import java.util.List;

import com.wilson.client.dota.DotaGetMatchDetailsRequest;
import com.wilson.client.dota.DotaGetMatchHistoryRequest;
import com.wilson.client.user.SteamGetPlayerSummaryRequest;

public class Main {
	
	public static void main(String[] args) throws Exception {
	
	String steamKey = "029021F53D5F974DA73A60F9300C3CF5";

	List<String> partyList = new ArrayList<String>();
	
	DotaGetMatchDetailsRequest request = new DotaGetMatchDetailsRequest();
	//SteamGetPlayerSummaryRequest request = new SteamGetPlayerSummaryRequest();
	//DotaGetMatchHistoryRequest request = new DotaGetMatchHistoryRequest();
//	request.setSteamId("76561197999636832");
	request.setMatchId("1848756405");

	//request.setMatchId("1848756405");

	SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
	api.execute(request);
	}

	
	
//	
//	jdeaMatch.getGold()	;
//	jdeaMatch.getExp();
//	
	
////	HttpGet userNameGet = new HttpGet("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=" + steamKey + "&vanityurl=jdea");


}
