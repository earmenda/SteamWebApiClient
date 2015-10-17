package com.wilson.client;

import java.util.List;

import org.apache.http.NameValuePair;

public interface SteamRequest {

	public String getSteamRoute();

	public String getSteamMethod();

	public String getSteamMethodVersion();

	public List<NameValuePair> getSteamParameters();
}
