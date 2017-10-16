package com.wo.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;

import com.wo.entity.User;
import com.wo.mapper.CheckEnvironmentMapper;
import com.wo.mapper.LoginMapper;
import com.wo.service.LoginService;

public class LoginServiceImpl implements LoginService {
	private static SqlSessionManager manager;
	private LoginMapper loginMapper;
	{
		// 配置文件的路径
		String resource = "conf.xml";
		try {
			// 将配置文件读取进来
			Reader reader = Resources.getResourceAsReader(resource);
			// 根据读取进来的配置文件创建SqlSessionManager对象
			manager = SqlSessionManager.newInstance(reader);
			// 创建映射关系
			loginMapper = manager.getMapper(LoginMapper.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUserByName(Map<String, Object> map) {
		
		return loginMapper.findUserByName(map);
	}

}
