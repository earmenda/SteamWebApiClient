package com.wilson.client.user;

public abstract class SteamInterfaceRequest implements SteamRequest {

	private static final String STEAM_ROUTE = "/ISteamUser";

	@Override
	public String getSteamRoute() {
		return STEAM_ROUTE;
	}
	
}
