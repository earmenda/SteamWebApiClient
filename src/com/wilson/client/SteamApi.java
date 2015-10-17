package com.wilson.client;

import java.net.URI;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.client.dota.DotaGetMatchDetailsRequest;
import com.wilson.client.dota.response.MatchDetail;

public class SteamApi {

	private String steamKey;
	private static String apiUrl = "api.steampowered.com";
	private static String apiProtocol = "http";
	private HttpClient client;

	public static void main(String args[]) {
		DotaGetMatchDetailsRequest request = new DotaGetMatchDetailsRequest();
		request.setMatchId("1848756405");

		SteamApi api = new SteamApi("029021F53D5F974DA73A60F9300C3CF5");
		api.execute(request);
	}

	public SteamApi(String steamKey) {
		this.steamKey = steamKey;
		client = HttpClients.createDefault();
	}

	public SteamApi(String steamKey, HttpClient client) {
		this.steamKey = steamKey;
		this.client = client;
	}

	public String execute(SteamRequest request) {
		// HttpGet getRequest = new
		// HttpGet("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key="
		// + steamKey + "&steamids=" + steamId + "&format=json");

		String response = null;
		try {

			URIBuilder builder = new URIBuilder();
			builder.setScheme(apiProtocol).setHost(apiUrl);
			builder.setPath(request.getSteamRoute() + request.getSteamMethod()
					+ request.getSteamMethodVersion());

			for (NameValuePair nvp : request.getSteamParameters()) {
				builder.setParameter(nvp.getName(), nvp.getValue());
			}

			builder.setParameter("key", this.steamKey);
			builder.setParameter("format", "json");
			URI uri = builder.build();

			System.out.println(uri.toASCIIString());
			HttpGet getRequest = new HttpGet(uri);
			;
			
			response = EntityUtils.toString(client.execute(getRequest).getEntity());
			ObjectMapper mapper = new ObjectMapper();

			MatchDetail MatchDetail = mapper.readValue(response, MatchDetail.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(response);
		return "test";
	}

	public String getSteamKey() {
		return steamKey;
	}

	public void setSteamKey(String steamKey) {
		this.steamKey = steamKey;
	}

}
