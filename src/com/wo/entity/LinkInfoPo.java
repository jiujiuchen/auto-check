package com.wo.entity;

public class LinkInfoPo{
	private String resoucePoolName;//   资源池名称
	private String hostName;//   主机名
	private String funcRole;//   功能角色
	private String networkRole;//   网络角色
	private String networkCardName;//   网卡名称
	private String bond;//   bond
	private String vlan;//   VLAN
	private String homeTerminalInterfaceIp;//   本端接口IP
	private String gateway;//   网关
	private String maskCode;//   掩码
	private String topAlliedSwitchName;//   上联交换机名称
	private String endDeviceCabinet;//   对端设备机柜
	private String endDevicePosition;//   对端设备U位
	private String endDevicePhysicalInterface;//   对端设备物理接口
	
	
	public String getHostName() {
	    return this.hostName;
	}
	public void setHostName(String hostName) {
	    this.hostName=hostName;
	}
	public String getFuncRole() {
	    return this.funcRole;
	}
	public void setFuncRole(String funcRole) {
	    this.funcRole=funcRole;
	}
	public String getNetworkRole() {
	    return this.networkRole;
	}
	public void setNetworkRole(String networkRole) {
	    this.networkRole=networkRole;
	}
	public String getNetworkCardName() {
	    return this.networkCardName;
	}
	public void setNetworkCardName(String networkCardName) {
	    this.networkCardName=networkCardName;
	}
	public String getBond() {
	    return this.bond;
	}
	public void setBond(String bond) {
	    this.bond=bond;
	}
	public String getVlan() {
	    return this.vlan;
	}
	public void setVlan(String vlan) {
	    this.vlan=vlan;
	}
	public String getHomeTerminalInterfaceIp() {
	    return this.homeTerminalInterfaceIp;
	}
	public void setHomeTerminalInterfaceIp(String homeTerminalInterfaceIp) {
	    this.homeTerminalInterfaceIp=homeTerminalInterfaceIp;
	}
	public String getGateway() {
	    return this.gateway;
	}
	public void setGateway(String gateway) {
	    this.gateway=gateway;
	}
	public String getMaskCode() {
	    return this.maskCode;
	}
	public void setMaskCode(String maskCode) {
	    this.maskCode=maskCode;
	}
	public String getTopAlliedSwitchName() {
	    return this.topAlliedSwitchName;
	}
	public void setTopAlliedSwitchName(String topAlliedSwitchName) {
	    this.topAlliedSwitchName=topAlliedSwitchName;
	}
	public String getEndDeviceCabinet() {
	    return this.endDeviceCabinet;
	}
	public void setEndDeviceCabinet(String endDeviceCabinet) {
	    this.endDeviceCabinet=endDeviceCabinet;
	}
	public String getEndDevicePosition() {
	    return this.endDevicePosition;
	}
	public void setEndDevicePosition(String endDevicePosition) {
	    this.endDevicePosition=endDevicePosition;
	}
	public String getEndDevicePhysicalInterface() {
	    return this.endDevicePhysicalInterface;
	}
	public void setEndDevicePhysicalInterface(String endDevicePhysicalInterface) {
	    this.endDevicePhysicalInterface=endDevicePhysicalInterface;
	}
	public String getResoucePoolName() {
		return resoucePoolName;
	}
	public void setResoucePoolName(String resoucePoolName) {
		this.resoucePoolName = resoucePoolName;
	}

}
