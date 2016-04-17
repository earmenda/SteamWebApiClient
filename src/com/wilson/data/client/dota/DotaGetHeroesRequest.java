package com.wilson.data.client.dota;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.wilson.data.client.UriUtils;
import com.wilson.data.client.user.SteamRequest;
import com.wilson.data.shared.HeroesList;

public class DotaGetHeroesRequest implements SteamRequest{

	private static final String STEAM_METHOD = "/GetHeroes";
	private static final String STEAM_METHOD_VERSION = "/V001";
	private static final String HERO_ROUTE = "/IEconDOTA2_570";
	private static final Class RESPONSE_TYPE = HeroesList.class;
	private Map<String, String> parameters;

	public DotaGetHeroesRequest() {
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

	@Override
	public Class<HeroesList> getResponseType(){
		return RESPONSE_TYPE;
	}

	@Override
	public String getSteamRoute() {
		return HERO_ROUTE;
	}
	
}

