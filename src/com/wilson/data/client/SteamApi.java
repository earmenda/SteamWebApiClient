package com.wilson.data.client;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.data.client.user.SteamRequest;

public class SteamApi {

	protected String steamKey;
	protected static String apiUrl = "api.steampowered.com";
	protected static String apiProtocol = "http";
	protected HttpClient client;
	protected HttpEntity entity;


	public SteamApi(String steamKey) {
		this.steamKey = steamKey;
		client = HttpClients.createDefault();
	}

	public SteamApi(String steamKey, HttpClient client) {
		this.steamKey = steamKey;
		this.client = client;
	}

	
	
	public <T> Object execute(SteamRequest request) {
		// HttpGet getRequest = new
		// HttpGet("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key="
		// + steamKey + "&steamids=" + steamId + "&format=json");
		Object responseObject = null;
		HttpGet getRequest = null;
		Class responseType = request.getResponseType();
		String response = null;
		try {

			URIBuilder builder = new URIBuilder();
			builder.setScheme(apiProtocol).setHost(apiUrl);
			builder.setPath(request.getSteamRoute() + request.getSteamMethod()
					+ request.getSteamMethodVersion());
			builder.setParameter("key", this.steamKey);

			for (NameValuePair nvp : request.getSteamParameters()) {
				builder.setParameter(nvp.getName(), nvp.getValue());

			}

			builder.setParameter("format", "json");
			URI uri = builder.build();
			
//			System.out.println(uri.toASCIIString());
			getRequest = new HttpGet(uri);
			
			entity = client.execute(getRequest).getEntity();
			
			//check if entity is null
			if (entity == null ){
				System.out.println("Entity null");
			}
			else{
			response = EntityUtils.toString(entity);
//			System.out.println("URL" + uri.toString() + "response: " + response);
			ObjectMapper mapper = new ObjectMapper();
//			MatchHistory MatchHistory = mapper.readValue(response, MatchHistory.class);
 			responseObject = mapper.readValue(response, responseType);
//			System.out.println(MatchHistory.getResult().getMatches().get(2));
//			System.out.println(((MatchDetail) steamUser).getResult().getPlayers().get(2).getAdditionalUnits());
 			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getRequest.releaseConnection();	
		}
		
		//System.out.println(response);
		return responseObject;
	}

	public String getSteamKey() {
		return steamKey;
	}

	public void setSteamKey(String steamKey) {
		this.steamKey = steamKey;
	}

	public void close(){
		client.getConnectionManager().closeExpiredConnections();
	}
}
