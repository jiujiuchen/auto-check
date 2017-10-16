package com.wo.mapper;

import java.util.List;
import java.util.Map;

import com.wo.entity.RecordInfoPo;

public interface CheckEnvironmentMapper {
	//插入记录
	public int insertRecord(RecordInfoPo recordInfoPo);

	
	public List<RecordInfoPo> findRecord(Map<String, Object> map);

	public int countRecord(Map<String, Object> map);
}
