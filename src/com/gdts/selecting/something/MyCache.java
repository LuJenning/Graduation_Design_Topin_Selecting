package com.gdts.selecting.something;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class MyCache {
	public static void main(String[] args) throws Exception{
		final CacheManager cacheManager = new CacheManager();
		
		final Cache cache = cacheManager.getCache("hellocache");
		
		final String key = "greeting";
		
		final Element putGreeting = new Element(key,"Hello,World!");
		
		cache.put(putGreeting);
		
		final Element geGreeting = cache.get(key);
		
		System.out.println(geGreeting.getObjectValue());
	}
}
