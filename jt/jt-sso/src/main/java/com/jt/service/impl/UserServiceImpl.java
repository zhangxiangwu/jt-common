package com.jt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.dubbo.service.DubboUserService;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean checkUser(String param, Integer type) {
		// TODO Auto-generated method stub
		
		Map<Integer,String> map = new HashMap<>();
		
	    	map.put(1, "username");
	    	map.put(2, "phone");
	    	map.put(3, "email");
		
	    	String column = map.get(type);
	    	
	        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
	    	
	        queryWrapper.eq(column, param);
	        
	    	User user = userMapper.selectOne(queryWrapper);
	    	
	 	   return user == null ? false : true ;
	}

}
