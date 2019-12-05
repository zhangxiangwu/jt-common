package com.jt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.service.DubboUserService;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;


@Controller
@RequestMapping("/user")
public class UserController {

	@Reference(check = false)
	private DubboUserService userService;

	//http://www.jt.com/user/login.html

	@Autowired
	private JedisCluster jedisCluster;

	@RequestMapping("/{moduleName}")
	public String login(@PathVariable String moduleName) {

		return moduleName;
	}

	//http://www.jt.com/user/doRegister

	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {


		int rows = userService.insertUser(user);

		if(rows == 1) {
			return SysResult.fail();
		}

		return SysResult.fail();
	}

	//http://www.jt.com/user/doLogin?r=0.8368744031021926

	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletResponse response) {

		String ticket = userService.doLogin(user);

		if(StringUtils.isEmpty(ticket)) {
			return SysResult.fail();
		}
		
		Cookie cookie = new Cookie("JT_TICKET", ticket);

		cookie.setPath("/");
		cookie.setDomain("jt.com");
		cookie.setMaxAge(7*24*3600);

		response.addCookie(cookie);

		return SysResult.success();
	}

	//http://www.jt.com/user/logout.html

	@RequestMapping("/logout")
	public String doLogout(HttpServletRequest req, HttpServletResponse res ) {

		Cookie[] cookies = req.getCookies();

		String ticket = null;

		if(cookies.length>0) {

			for (Cookie cookie : cookies) {

				if(cookie.getName().equals("JT_TICKET")) {
					ticket = cookie.getValue();
				}

			}
		}

		Cookie cookie = new Cookie("JT_TICKET", "");
          
		 jedisCluster.del(ticket);
		 cookie.setMaxAge(0);
		 cookie.setPath("/");
		 cookie.setDomain("jt.com");

		 res.addCookie(cookie);

		return  "redirect:/";
	}

}


























