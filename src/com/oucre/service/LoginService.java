package com.oucre.service;

import java.util.Map;

public interface LoginService {
	/**
	 * 
	 * @date 2015年3月30日 上午11:12:08
	 * @author haoli_jun
	 * @param name   用户登录帐号
	 * @param password  用户密码
	 * @return  
	 */
	public Map<String, Object> loginUser(String name, String password);
	
	
}
