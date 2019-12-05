package com.jt.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
//@PropertySource("/properties/redisshards.properties")
//@PropertySource("/properties/sentinelredis.properties")
@PropertySource("properties/clusterredis.properties")
public class RedisConfig {

	
	//	
	//  第一种：单台redis	
	//	
	//	@Value("${redis.host}")
	//	private  String host;
	//	@Value("${redis.port}")
	//	private  Integer port;
	//	
	//	@Bean
	//	public  Jedis jedis() {
	//		
	//		Jedis jedis = new Jedis(host,port);
	//		
	//		return jedis;
	//		
	//	}

	// =====================================================================================================
	//第二种：redis shards

	//	@Value("${redis.nodes}")
	//	private String nodes;
	//
	//	@Bean
	//	public ShardedJedis shardsJedis() {
	//
	//		List<JedisShardInfo> list = getJedisShardInfo();
	//		
	//		System.out.println("我是往ShardedJedis里存储的");
	//		
	//		return new ShardedJedis(list);
	//
	//	}
	//
	//	@Bean
	//	public List<JedisShardInfo> getJedisShardInfo(){
	//		
	//		List<JedisShardInfo> list = new ArrayList<>();
	//		
	//		String[] node = nodes.split(",");
	//		//192.168.91.188:6379
	//		//192.168.91.188:6380
	//		
	//		for (String hostPort : node) {
	//			System.out.println("hostPort="+hostPort);
	//			
	//			String[] hostPorts = hostPort.split(":");
	//			String host = hostPorts[0]; 
	//			Integer port =Integer.parseInt(hostPorts[1]);
	//			list.add(new JedisShardInfo(host, port));
	//			
	//		}
	//		System.out.println(list);
	//		return list;
	//	}


	// =====================================================================================================	

	//第三种sentinel

//	@Value("${sentinel.nodes}")
//	private String nodes;
//
//	@Bean
//	@Scope("prototype")
//	public Jedis jedis() {
//
//		JedisSentinelPool jedisPool = new JedisSentinelPool("mymaster", getSet());
//		return jedisPool.getResource();
//
//	}
//
//	@Bean
//	@Scope("singleton")
//	public Set<String> getSet(){
//
//		Set<String> sentinel = new HashSet<>();
//
//		sentinel.add(nodes);
//        System.out.println(nodes);
//        System.out.println("=============");
//		return sentinel;
//	}

	// =====================================================================================================	
	
	//第四种 cluster
	
	@Value("${cluster.nodes}")
	private String nodes;
	
	@Bean
	public JedisCluster jedis() {
		
		String[] node = nodes.split(",");
		
		Set<HostAndPort> list = new HashSet<>();
		
        for (String hostAndPort : node) {
			
        	String[] hostPort = hostAndPort.split(":");
        	
        	String host = hostPort[0];
        	System.out.println("host="+host);
        	Integer port = Integer.parseInt(hostPort[1]);
        	System.out.println("port="+port);
        	list.add(new HostAndPort(host, port));
        	
		}	
        
        JedisCluster jedisCluster = new JedisCluster(list);
		return jedisCluster;
	}
	
}

























































