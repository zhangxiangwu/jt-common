package com.jt.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {

	private String host = "192.168.91.188";
	
	private Integer port = 6379;
	
	@Test
	public void redisTest() {
		
		Jedis jedis = new Jedis(host, port);
		
		jedis.setex("abc", 10, "abc");
		
		jedis.setnx("aaa", "abca");
		
		System.out.println(jedis.get("abc"));
		System.out.println(jedis.get("aaa"));
	}
	
}
