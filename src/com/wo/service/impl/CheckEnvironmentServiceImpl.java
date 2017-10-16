package com.wo.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.List;





import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;

import com.wo.entity.RecordInfoPo;
import com.wo.mapper.CheckEnvironmentMapper;
import com.wo.service.CheckEnvironmentService;

public class CheckEnvironmentServiceImpl implements CheckEnvironmentService {
	private static SqlSessionManager manager;
	private CheckEnvironmentMapper cem;
	{
		// 配置文件的路径
		String resource = "conf.xml";
		try {
			// 将配置文件读取进来
			Reader reader = Resources.getResourceAsReader(resource);
			// 根据读取进来的配置文件创建SqlSessionManager对象
			manager = SqlSessionManager.newInstance(reader);
			// 创建映射关系
			cem = manager.getMapper(CheckEnvironmentMapper.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertRecord(RecordInfoPo recordInfoPo) {
		// TODO Auto-generated method stub
		return cem.insertRecord(recordInfoPo);
	}

	@Override
	public List<RecordInfoPo> findRecord(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cem.findRecord(map);
	}

	@Override
	public int countRecord(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cem.countRecord(map);
	}


}
