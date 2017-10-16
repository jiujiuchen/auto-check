package com.wo.mapper;

import java.util.Map;

import com.wo.entity.User;

public interface LoginMapper {
	public User findUserByName(Map<String,Object> map);

}
