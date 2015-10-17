package com.wilson.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SteamUser {
	
	CloseableHttpClient httpclient = HttpClients.createDefault();

    private String steamId;
    private String steamKey;
    private int matchID;
    private List<String> last25Matches;
    
    
    //Constructor
    public SteamUser(){
    
    }
    
   //Custom Constructor
    public SteamUser(String steamId, String steamKey){
    		this.steamKey = steamKey;
    		this.steamId = steamId;
    	    
    }
    
    //Populates SteamUser object
	public void steamUserPopulate() throws ClientProtocolException, IOException{
//		HttpGet last25MatchGet = new HttpGet("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key=029021F53D5F974DA73A60F9300C3CF5&steamids=76561197999636832&format=json");
		HttpGet playerSummary = new HttpGet("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key=" + steamKey + "&steamids=" + steamId + "&format=json");

		CloseableHttpResponse playerSummaryResponse = httpclient.execute(playerSummary);
		HttpEntity playerSummaryEntity = playerSummaryResponse.getEntity();
		String playerSummaryEntityString = EntityUtils.toString(playerSummaryEntity);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
		System.out.println(playerSummaryEntityString);
		try {
			JsonNode jsonNode = mapper.readTree(playerSummaryEntityString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Response matchList = mapper.readValue(playerSummaryEntityString, Response.class);
		
		System.out.println(matchList.getResponse().getPersonaName());

	    EntityUtils.consume(playerSummaryEntity);
	    playerSummaryResponse.close();
	}
	
    public static class Response {
		private SteamUserPlayer player;
		
		public void setPlayers(SteamUserPlayer player){
			this.player = player;
		}
		
		public SteamUserPlayer getPlayer(){
			return this.player;
		}

	}

    
    




  
	


//    	
//    	public Match(int matchID){
//    	HttpGet matchGet = new HttpGet("http://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=" + steamKey + "&account_id=" + steamId);
//    	CloseableHttpResponse matchGetResponse = httpclient.execute(matchGet);
//	    HttpEntity matchGetEntity = matchGetResponse.getEntity();
//    	}




    

    
    //Getter
    public String getSteamId() {
        return steamId;
    }


    
    //Setter, Right now, it sets 
//    public void setSteamUser() throws ClientProtocolException, IOException {
//        HttpGet last25MatchGet = new HttpGet("http://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=" + steamKey + "&account_id=" + steamId + "&matches_requested=25");
//    	
//    	CloseableHttpResponse last25MatchResponse = httpclient.execute(last25MatchGet);
//        HttpEntity Entity = last25MatchResponse.getEntity();
//
//        
//        if (Entity == null) {
//            System.out.println("throw some error or something");
//       
//        } else {
//    	    
//        	String test = EntityUtils.toString(Entity);
//        	ObjectMapper mapper = new ObjectMapper();
//        	
//        	MatchHistoryResult matchList = mapper.readValue(test, MatchHistoryResult.class);
//        	System.out.println(matchList);
//        	
//
//
//    	    System.out.println(test);
//    	    EntityUtils.consume(Entity);
//    	    last25MatchResponse.close();
//
//
//        }
//        
//
//        
//    }
    
    
}


