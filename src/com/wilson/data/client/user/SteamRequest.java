package com.wilson.data.client.user;

import java.util.List;

import org.apache.http.NameValuePair;

public interface SteamRequest {

	public String getSteamRoute();

	public String getSteamMethod();

	public String getSteamMethodVersion();
	
	public Class getResponseType();

	public List<NameValuePair> getSteamParameters();
}
