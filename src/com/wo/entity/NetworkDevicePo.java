package com.wo.entity;

public class NetworkDevicePo {

	private String resoucePoolName;// 资源池名称
	private String deviceName; // 设备名称
	private String deviceType;// 设备类型
	private String deviceModel;// 设备型号
	private String deviceSn;// SN
	private String brandName;// 品牌&厂家
	// 配置信息
	private String configInfo;
	// 固件版本号
	private String firmwareVer;
	// 应用版本号
	private String appVer;
	// 立项信息
	private String projectInfo;
	// 合同编号
	private String contractNum;
	// 签收单
	private String receipt;
	// 资产编号
	private String assetNum;
	private String roomNumber;// 机房编号
	private String rackNumber;// 机柜编号
	private String deviceLocation;// U位
	// 故障联系人
	private String contacts;
	// 故障联系电话
	private String tel;
	// 厂家联系人
	private String manufacturersContacts;
	// 厂家联系电话
	private String manufacturersTel;


	public String getResoucePoolName() {
		return resoucePoolName;
	}

	public void setResoucePoolName(String resoucePoolName) {
		this.resoucePoolName = resoucePoolName;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceSn() {
		return this.deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
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

	public String getDeviceLocation() {
		return this.deviceLocation;
	}

	public void setDeviceLocation(String deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(String configInfo) {
		this.configInfo = configInfo;
	}

	public String getFirmwareVer() {
		return firmwareVer;
	}

	public void setFirmwareVer(String firmwareVer) {
		this.firmwareVer = firmwareVer;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getAssetNum() {
		return assetNum;
	}

	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getManufacturersContacts() {
		return manufacturersContacts;
	}

	public void setManufacturersContacts(String manufacturersContacts) {
		this.manufacturersContacts = manufacturersContacts;
	}

	public String getManufacturersTel() {
		return manufacturersTel;
	}

	public void setManufacturersTel(String manufacturersTel) {
		this.manufacturersTel = manufacturersTel;
	}

}
