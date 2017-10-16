package com.wo.entity;

public class ServersInfoPo {
	private String resoucePoolName;// 资源池名称
	private String hostName;// 主机名
	private String snNumber;// s/n序列号
	private String useType;// 用途
	private String mgmtIp;// 管理IP
	private String mgmtMask;// 管理掩码
	private String mgmtGateway;// 管理地址网关
	private String mgmtLan1;// 管理1口
	private String mgmtLan2;// 管理2口
	private String mgmtBondName;// 管理bond名
	private String virtLan1;// 业务1口
	private String virtLan2;// 业务2口
	private String virtBondName;// 业务bond名
	private String storageIp;// 存储IP
	private String storageMask;// 存储掩码
	private String storageGateway;// 存储网关
	private String ipmiIp;// IPMI地址
	private String avaliableZoneName;// 所属az
	private String rootPassword;// root密码
	private String cellVip;// Cell虚拟ip
	private String childCellName;// child_cell名称
	private String roomNumber;// 机房编号
	private String rackNumber;// 机柜编号
	private String brandName;// 品牌
	private String deviceModel;// 型号
	private String deviceLocation;// U位
	private String accessRegion;


	public String getResoucePoolName() {
		return resoucePoolName;
	}

	public void setResoucePoolName(String resoucePoolName) {
		this.resoucePoolName = resoucePoolName;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSnNumber() {
		return this.snNumber;
	}

	public void setSnNumber(String snNumber) {
		this.snNumber = snNumber;
	}

	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getMgmtIp() {
		return this.mgmtIp;
	}

	public void setMgmtIp(String mgmtIp) {
		this.mgmtIp = mgmtIp;
	}

	public String getMgmtMask() {
		return this.mgmtMask;
	}

	public void setMgmtMask(String mgmtMask) {
		this.mgmtMask = mgmtMask;
	}

	public String getMgmtGateway() {
		return this.mgmtGateway;
	}

	public void setMgmtGateway(String mgmtGateway) {
		this.mgmtGateway = mgmtGateway;
	}

	public String getMgmtLan1() {
		return this.mgmtLan1;
	}

	public void setMgmtLan1(String mgmtLan1) {
		this.mgmtLan1 = mgmtLan1;
	}

	public String getMgmtLan2() {
		return this.mgmtLan2;
	}

	public void setMgmtLan2(String mgmtLan2) {
		this.mgmtLan2 = mgmtLan2;
	}

	public String getMgmtBondName() {
		return this.mgmtBondName;
	}

	public void setMgmtBondName(String mgmtBondName) {
		this.mgmtBondName = mgmtBondName;
	}

	public String getVirtLan1() {
		return this.virtLan1;
	}

	public void setVirtLan1(String virtLan1) {
		this.virtLan1 = virtLan1;
	}

	public String getVirtLan2() {
		return this.virtLan2;
	}

	public void setVirtLan2(String virtLan2) {
		this.virtLan2 = virtLan2;
	}

	public String getVirtBondName() {
		return this.virtBondName;
	}

	public void setVirtBondName(String virtBondName) {
		this.virtBondName = virtBondName;
	}

	public String getStorageIp() {
		return this.storageIp;
	}

	public void setStorageIp(String storageIp) {
		this.storageIp = storageIp;
	}

	public String getStorageMask() {
		return this.storageMask;
	}

	public void setStorageMask(String storageMask) {
		this.storageMask = storageMask;
	}

	public String getStorageGateway() {
		return this.storageGateway;
	}

	public void setStorageGateway(String storageGateway) {
		this.storageGateway = storageGateway;
	}

	public String getIpmiIp() {
		return this.ipmiIp;
	}

	public void setIpmiIp(String ipmiIp) {
		this.ipmiIp = ipmiIp;
	}

	public String getAvaliableZoneName() {
		return this.avaliableZoneName;
	}

	public void setAvaliableZoneName(String avaliableZoneName) {
		this.avaliableZoneName = avaliableZoneName;
	}

	public String getRootPassword() {
		return this.rootPassword;
	}

	public void setRootPassword(String rootPassword) {
		this.rootPassword = rootPassword;
	}

	public String getCellVip() {
		return this.cellVip;
	}

	public void setCellVip(String cellVip) {
		this.cellVip = cellVip;
	}

	public String getChildCellName() {
		return this.childCellName;
	}

	public void setChildCellName(String childCellName) {
		this.childCellName = childCellName;
	}

	public String getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRackNumber() {
		return this.rackNumber;
	}

	public void setRackNumber(String rackNumber) {
		this.rackNumber = rackNumber;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceLocation() {
		return this.deviceLocation;
	}

	public void setDeviceLocation(String deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

	public String getAccessRegion() {
		return accessRegion;
	}

	public void setAccessRegion(String accessRegion) {
		this.accessRegion = accessRegion;
	}

	@Override
	public String toString() {
		return resoucePoolName + ", hostName=" + hostName + ", snNumber=" + snNumber + ", useType=" + useType + ", mgmtIp=" + mgmtIp + ", mgmtMask=" + mgmtMask + ", mgmtGateway=" + mgmtGateway + ", mgmtLan1=" + mgmtLan1 + ", mgmtLan2=" + mgmtLan2 + ", mgmtBondName=" + mgmtBondName + ", virtLan1=" + virtLan1 + ", virtLan2=" + virtLan2 + ", virtBondName=" + virtBondName + ", storageIp=" + storageIp + ", storageMask=" + storageMask + ", storageGateway="
				+ storageGateway + ", ipmiIp=" + ipmiIp + ", avaliableZoneName=" + avaliableZoneName + ", rootPassword=" + rootPassword + ", cellVip=" + cellVip + ", childCellName=" + childCellName + ", roomNumber=" + roomNumber + ", rackNumber=" + rackNumber + ", brandName=" + brandName + ", deviceModel=" + deviceModel + ", deviceLocation=" + deviceLocation + ", accessRegion=" + accessRegion + "]";
	}
	

}
