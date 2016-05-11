package com.wilson.data.client.dota;

import com.wilson.data.client.user.SteamRequest;

public abstract class DotaRequest implements SteamRequest {

	private static final String STEAM_ROUTE = "/IDOTA2Match_570";

	@Override
	public String getSteamRoute() {
		return STEAM_ROUTE;
	}
	


}
