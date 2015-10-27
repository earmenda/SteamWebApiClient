package com.wilson.client;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.client.user.SteamRequest;

public class SteamApi {

	private String steamKey;
	private static String apiUrl = "api.steampowered.com";
	private static String apiProtocol = "http";
	private HttpClient client;
	private HttpEntity entity;


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
		Class responseType = request.getResponseType();
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

			//System.out.println(uri.toASCIIString());
			HttpGet getRequest = new HttpGet(uri);
			
			entity = client.execute(getRequest).getEntity();
			
			//check if entity is null
			if (entity == null){
				System.out.println("Entity null");
			}
			else{
			response = EntityUtils.toString(entity);
			ObjectMapper mapper = new ObjectMapper();
//			MatchHistory MatchHistory = mapper.readValue(response, MatchHistory.class);
 			Object steamUser = responseType.cast(mapper.readValue(response, responseType));
//			System.out.println(MatchHistory.getResult().getMatches().get(2));
//			System.out.println(((MatchDetail) steamUser).getResult().getPlayers().get(2).getAdditionalUnits());
			EntityUtils.consume(entity);
			getRequest.releaseConnection();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(response);
		return "test";
	}

	public String getSteamKey() {
		return steamKey;
	}

	public void setSteamKey(String steamKey) {
		this.steamKey = steamKey;
	}

}
