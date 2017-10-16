package com.wo.entity;

import java.util.Date;


public class RoomInfoPo {
	private String resoucePoolName;//   资源池名称
	private String roomName;//   机房名称
	private String roomAddress;//   机房位置
	private Date originalCreateTime; //原始数据创建时间
	private Date originalUpdateTime; //原始数据修改时间
	private Date originalDeleteTime; //原始数据删除时间
	
	public String getResoucePoolName() {
		return resoucePoolName;
	}
	public void setResoucePoolName(String resoucePoolName) {
		this.resoucePoolName = resoucePoolName;
	}
	public String getRoomName() {
	    return this.roomName;
	}
	public void setRoomName(String roomName) {
	    this.roomName=roomName;
	}
	public String getRoomAddress() {
	    return this.roomAddress;
	}
	public void setRoomAddress(String roomAddress) {
	    this.roomAddress=roomAddress;
	}

	public Date getOriginalCreateTime() {
		return originalCreateTime;
	}
	public void setOriginalCreateTime(Date originalCreateTime) {
		this.originalCreateTime = originalCreateTime;
	}
	public Date getOriginalUpdateTime() {
		return originalUpdateTime;
	}
	public void setOriginalUpdateTime(Date originalUpdateTime) {
		this.originalUpdateTime = originalUpdateTime;
	}
	public Date getOriginalDeleteTime() {
		return originalDeleteTime;
	}
	public void setOriginalDeleteTime(Date originalDeleteTime) {
		this.originalDeleteTime = originalDeleteTime;
	}

}
