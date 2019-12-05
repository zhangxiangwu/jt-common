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
@PropertySource("classpath:properties/clusterredis.properties")
public class RedisConfig {

	@Value("${cluster.nodes}")
	private String nodes;
	
	@Bean
	public JedisCluster jedisCluster() {
		
		String[] hostAndPorts = nodes.split(",");
		
		Set<HostAndPort> list = new HashSet<>();
		
		if(hostAndPorts.length != 0) {
			
			for (String hostAndPort : hostAndPorts) {
				
			   String	host = hostAndPort.split(":")[0];
			   int	port = Integer.parseInt(hostAndPort.split(":")[1]);	
				
			   list.add(new HostAndPort(host, port));
			  
			}
			
		}
		
		return new JedisCluster(list);
	}
}
