package com.wilson.client.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.wilson.client.UriUtils;
import com.wilson.client.user.response.SteamUser;

public class SteamGetPlayerSummaryRequest extends SteamInterfaceRequest {

	private static final String STEAM_METHOD = "/GetPlayerSummaries";
	private static final String STEAM_METHOD_VERSION = "/V002";
	private static final Class RESPONSE_TYPE = SteamUser.class;

	private Map<String, String> parameters;

	public SteamGetPlayerSummaryRequest() {
		parameters = new HashMap<String, String>();
	}

	@Override
	public String getSteamMethod() {
		return STEAM_METHOD;
	}

	@Override
	public String getSteamMethodVersion() {
		return STEAM_METHOD_VERSION;
	}

	@Override
	public List<NameValuePair> getSteamParameters() {
		return UriUtils.stringMapToNameValuePairs(parameters);
	}

	public void setSteamId(String steamId) {
		parameters.put("steamids", steamId);
	}
	@Override
	public Class<SteamUser> getResponseType(){
		return RESPONSE_TYPE;
	}	
}