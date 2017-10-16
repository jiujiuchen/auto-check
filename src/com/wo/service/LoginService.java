package com.wo.service;

import java.util.Map;

import com.wo.entity.User;

public interface LoginService {
	public User findUserByName(Map<String,Object> map);

}
