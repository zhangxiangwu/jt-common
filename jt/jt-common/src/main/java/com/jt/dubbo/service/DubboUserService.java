package com.jt.dubbo.service;

import com.jt.pojo.User;

public interface  DubboUserService {

	int insertUser(User user);

	String doLogin(User user);

}
