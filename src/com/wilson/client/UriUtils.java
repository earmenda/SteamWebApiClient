package com.wilson.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class UriUtils {

	public static List<NameValuePair> stringMapToNameValuePairs(Map<String, String> map) {
		List<NameValuePair> nvpList = new ArrayList<>(map.size());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			nvpList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return nvpList;
	}
}
