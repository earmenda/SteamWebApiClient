package com.wilson.data.client;

public class Runner implements Runnable {
	
	MatchIdCache cache;
	public Runner(){};
	public Runner(MatchIdCache cache){
		this.cache = cache;
	}

	public synchronized void run(){
		System.out.println("Cache current state:" + cache.test);
		for (int i = 0; i<10; i++ ){
			cache.test++;
			System.out.println("cachetest: " + cache.test);

		}
		System.out.println("cachetotals: " + cache.test);
	}
}
