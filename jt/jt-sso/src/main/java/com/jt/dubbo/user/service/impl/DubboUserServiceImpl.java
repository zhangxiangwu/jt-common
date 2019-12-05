package com.jt.dubbo.user.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.dubbo.service.DubboUserService;
import com.jt.json.ObjectJsonUtil;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Service
public class DubboUserServiceImpl implements DubboUserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub

		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

		user.setPassword(md5Pass)
		.setEmail(user.getPhone())
		.setCreated(new Date())
		.setUpdated(user.getCreated());

		int rows = userMapper.insert(user);

		return rows;
	}

	@Override
	public String doLogin(User user) {
		// TODO Auto-generated method stub

		String md5Passwrod = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

		user.setPassword(md5Passwrod);

		QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);

		User userDB = userMapper.selectOne(queryWrapper);

		if(userDB!=null) {

			String uuid = UUID.randomUUID().toString();

			String ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());

			userDB.setPassword("********");

           String jsonUser = ObjectJsonUtil.toJSON(userDB);
           
           jedisCluster.setex(ticket, 7*24*3600, jsonUser);
           
           return ticket;

		}

		return null;
	}


}


















