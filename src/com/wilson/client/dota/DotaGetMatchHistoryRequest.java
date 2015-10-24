package com.wilson.client.dota;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.wilson.client.UriUtils;
import com.wilson.client.dota.response.MatchHistory;

public class DotaGetMatchHistoryRequest extends DotaRequest{
	private static final String STEAM_METHOD = "/GetMatchHistory";
	private static final String STEAM_METHOD_VERSION = "/V001";
	private static final Class RESPONSE_TYPE = MatchHistory.class;
	private Map<String, String> parameters;

	public DotaGetMatchHistoryRequest() {
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

	public void setAccountId(String accountId) {
		parameters.put("account_id", accountId);
	}
	
	@Override
	public Class<MatchHistory> getResponseType(){
		return RESPONSE_TYPE;
	}
	
}

