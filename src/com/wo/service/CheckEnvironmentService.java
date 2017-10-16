package com.wo.service;

import java.util.List;
import java.util.Map;

import com.wo.entity.RecordInfoPo;

public interface CheckEnvironmentService {
	//插入记录
	int insertRecord(RecordInfoPo recordInfoPo);
	
	//查询记录
	List<RecordInfoPo> findRecord(Map<String, Object> map);

	//查询总的记录数
	int countRecord(Map<String, Object> map);

}
