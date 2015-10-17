package com.wilson.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	
	public static void main(String[] args) throws Exception {
	
	String steamKey = "029021F53D5F974DA73A60F9300C3CF5";
	Object accountID;
	Object matchID;
	List<String> partyList = new ArrayList<String>();
	
	//is there a way to do this without using setSteamUser()? Since I have to use the API to grab my settings.
	SteamUser jdea = new SteamUser ("76561197999636832", steamKey);
	jdea.setSteamUser();
	jdea.getlastMatch();
	
	
	Party friends = new Party();
	friends.add("76561197999636832");
	friends.add("76561197999636832");
	friends.add("76561197999636832");
	friends.add("76561197999636832");
	friends.add("76561197999636832");
	friends.add("76561197999636832");

	partyList = friends.get();
	System.out.println(partyList);

	Party friends1 = new Party();
	friends1.add("76561197999636832");
	friends1.add("asdf");
	friends1.add("7656119asdfd7999636832");
	friends1.add("7656119asdf7999636832");
	friends1.add("asdf");
	friends1.add("7asdfasdf");
	partyList = friends1.get();

	System.out.println(partyList);
	
	
//	SteamUser jdeaMatch = jdea.new Match("1848756405");
//	
//	jdeaMatch.getGold()	;
//	jdeaMatch.getExp();
//	
	
////	HttpGet userNameGet = new HttpGet("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key=" + steamKey + "&vanityurl=jdea");


}
}