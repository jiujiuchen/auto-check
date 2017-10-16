package com.wo.entity;

import java.util.Date;

public class CabinetInfoPo {
	private String resoucePoolName; // 资源池名称
	private String roomName;//   所属机房
	private String cabinetCode;//   机柜编号
	private Date createTime;//   创建时间
	private Date updateTime;//   修改时间
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
	public String getCabinetCode() {
	    return this.cabinetCode;
	}
	public void setCabinetCode(String cabinetCode) {
	    this.cabinetCode=cabinetCode;
	}
	public Date getCreateTime() {
	    return this.createTime;
	}
	public void setCreateTime(Date createTime) {
	    this.createTime=createTime;
	}
	public Date getUpdateTime() {
	    return this.updateTime;
	}
	public void setUpdateTime(Date updateTime) {
	    this.updateTime=updateTime;
	}
	
}
