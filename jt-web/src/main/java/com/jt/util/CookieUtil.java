package com.jt.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.jt.vo.SysResult;

public class CookieUtil {

	public static String getCookieUtil(HttpServletRequest req,String cookieName) {
		
		Cookie[] cookies = req.getCookies();
		
		if(cookies == null || cookies.length == 0) {
			return null;
		}else {
			for (Cookie cookie : cookies) {
				System.out.println("CookieUtil.getCookieUtil()====="+cookie.getName());
				if(cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
				
			}
		}
		return null;
	}
	
}
