package com.wilson.data.client;

import java.util.Arrays;
import java.util.List;

public class SteamKeys {
	public static int instances = -1;
	public static final String NICOLE = "97A90EAF21659EEAC4D12B55BD3181E8";
	public static final String EDUARDO = "029021F53D5F974DA73A60F9300C3CF5";
	public static final String WILSON = "D08428FBD2BB7EA50DD2556E42FCED1B";
	public static final List<String> LIST = Arrays.asList(new String[] {NICOLE, EDUARDO, 
			WILSON});;
	
	public SteamKeys(){

	}
	public static synchronized String getSteamKey(){
		instances ++;
		if (instances == 3){
			instances = 0;
		}
		return LIST.get(instances);

	}
}
