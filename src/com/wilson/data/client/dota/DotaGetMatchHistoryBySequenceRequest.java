package com.wilson.data.client.dota;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import com.wilson.data.client.UriUtils;
import com.wilson.data.client.dota.response.MatchDetailResponse;
import com.wilson.data.client.dota.response.MatchHistoryBySequenceResponse;

public class DotaGetMatchHistoryBySequenceRequest extends DotaRequest{
	private static final String STEAM_METHOD = "/GetMatchHistoryBySequenceNum";
	private static final String STEAM_METHOD_VERSION = "/v1";
	private static final Class RESPONSE_TYPE = MatchHistoryBySequenceResponse.class;
	private Map<String, String> parameters;
	private static final String DEFAULT_NUM_MATCHES = "100";

	public DotaGetMatchHistoryBySequenceRequest() {
		parameters = new HashMap<String, String>();
		parameters.put("matches_requested", DEFAULT_NUM_MATCHES);
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

	public void setMatchRequestNumber(int matchRequestNumber){
		parameters.put("matches_requested", String.valueOf(matchRequestNumber));
	}
	
	public void setSequenceNumber(String sequenceNumber) {
		parameters.put("start_at_match_seq_num", sequenceNumber);
	}
	
	@Override
	public Class<MatchDetailResponse> getResponseType(){
		return RESPONSE_TYPE;
	}
}
