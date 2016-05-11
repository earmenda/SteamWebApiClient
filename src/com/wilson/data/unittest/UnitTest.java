package com.wilson.data.unittest;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UnitTest {
	ObjectMapper mapper = new ObjectMapper();
//	MatchHistory MatchHistory = mapper.readValue(response, MatchHistory.class);
	responseObject = mapper.readValue(response, MatchDetailResponse);
}
