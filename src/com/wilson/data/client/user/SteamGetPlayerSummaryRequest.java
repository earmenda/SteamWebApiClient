package com.wilson.data.client.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import com.wilson.data.client.UriUtils;
import com.wilson.data.client.user.response.SteamPlayerSummary;

public class SteamGetPlayerSummaryRequest extends SteamInterfaceRequest {

	private static final String STEAM_METHOD = "/GetPlayerSummaries";
	private static final String STEAM_METHOD_VERSION = "/V002";
	private static final Class RESPONSE_TYPE = SteamPlayerSummary.class;

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
	public void setSteamIds(Collection<String> steamIds) {
        parameters.put("steamids", StringUtils.join(steamIds, " "));

    }
	
	public void setSteamParameters(Map<String,String> steamParameters){
		this.parameters = steamParameters;
	}
	
	@Override
	public Class<SteamPlayerSummary> getResponseType(){
		return RESPONSE_TYPE;
	}	
}