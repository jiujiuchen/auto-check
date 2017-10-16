package com.wo.entity;

import java.util.Date;

public class ResourcesPool {
	private String resoucePoolId;// 资源池名称
	private String resoucePoolName;// 资源池名称
	private String resoucePoolCode;// 资源池编码
	private String yumIp;// yum源IP
	private String networkVlanRanges;// 业务vlan段
	private String publicVlan;// 公网vlan号
	private String apiVip;// 管理VIP
	private Integer storageVlanNumber;// 存储vlan
	private String novncproxyPublicUrl;// VNC域名
	private String vpnIp;// VPN地址
	private String vpnUser;// VPN用户名
	private String vpnPassword;// VPN密码
	private String ipmiUser;// ipmi用户
	private String ipmiPassword;// ipmi密码
	private String portalIp;// 门户地址
	private Integer apiVirtualRouterId;
	private Integer cell01VirtualRouterId;
	private Integer cell02VirtualRouterId;
	private Integer cell03VirtualRouterId;
	private String publicIp;
	private String softVersions;
	private String resoucePoolType;
	private Date createTime;// 升级时间
	
	

	public String getResoucePoolId() {
		return resoucePoolId;
	}

	public void setResoucePoolId(String resoucePoolId) {
		this.resoucePoolId = resoucePoolId;
	}

	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getResoucePoolName() {
		return this.resoucePoolName;
	}

	public void setResoucePoolName(String resoucePoolName) {
		this.resoucePoolName = resoucePoolName;
	}

	public String getResoucePoolCode() {
		return this.resoucePoolCode;
	}

	public void setResoucePoolCode(String resoucePoolCode) {
		this.resoucePoolCode = resoucePoolCode;
	}

	public String getYumIp() {
		return this.yumIp;
	}

	public void setYumIp(String yumIp) {
		this.yumIp = yumIp;
	}

	public String getNetworkVlanRanges() {
		return this.networkVlanRanges;
	}

	public void setNetworkVlanRanges(String networkVlanRanges) {
		this.networkVlanRanges = networkVlanRanges;
	}

	public String getPublicVlan() {
		return publicVlan;
	}

	public void setPublicVlan(String publicVlan) {
		this.publicVlan = publicVlan;
	}

	public String getApiVip() {
		return this.apiVip;
	}

	public void setApiVip(String apiVip) {
		this.apiVip = apiVip;
	}

	public Integer getStorageVlanNumber() {
		return this.storageVlanNumber;
	}

	public void setStorageVlanNumber(Integer storageVlanNumber) {
		this.storageVlanNumber = storageVlanNumber;
	}

	public String getNovncproxyPublicUrl() {
		return this.novncproxyPublicUrl;
	}

	public void setNovncproxyPublicUrl(String novncproxyPublicUrl) {
		this.novncproxyPublicUrl = novncproxyPublicUrl;
	}

	public String getVpnIp() {
		return this.vpnIp;
	}

	public void setVpnIp(String vpnIp) {
		this.vpnIp = vpnIp;
	}

	public String getVpnUser() {
		return this.vpnUser;
	}

	public void setVpnUser(String vpnUser) {
		this.vpnUser = vpnUser;
	}

	public String getVpnPassword() {
		return this.vpnPassword;
	}

	public void setVpnPassword(String vpnPassword) {
		this.vpnPassword = vpnPassword;
	}

	public String getIpmiUser() {
		return this.ipmiUser;
	}

	public void setIpmiUser(String ipmiUser) {
		this.ipmiUser = ipmiUser;
	}

	public String getIpmiPassword() {
		return this.ipmiPassword;
	}

	public void setIpmiPassword(String ipmiPassword) {
		this.ipmiPassword = ipmiPassword;
	}

	public String getPortalIp() {
		return this.portalIp;
	}

	public void setPortalIp(String portalIp) {
		this.portalIp = portalIp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getApiVirtualRouterId() {
		return apiVirtualRouterId;
	}

	public void setApiVirtualRouterId(Integer apiVirtualRouterId) {
		this.apiVirtualRouterId = apiVirtualRouterId;
	}

	public Integer getCell01VirtualRouterId() {
		return cell01VirtualRouterId;
	}

	public void setCell01VirtualRouterId(Integer cell01VirtualRouterId) {
		this.cell01VirtualRouterId = cell01VirtualRouterId;
	}

	public Integer getCell02VirtualRouterId() {
		return cell02VirtualRouterId;
	}

	public void setCell02VirtualRouterId(Integer cell02VirtualRouterId) {
		this.cell02VirtualRouterId = cell02VirtualRouterId;
	}

	public Integer getCell03VirtualRouterId() {
		return cell03VirtualRouterId;
	}

	public void setCell03VirtualRouterId(Integer cell03VirtualRouterId) {
		this.cell03VirtualRouterId = cell03VirtualRouterId;
	}

	public String getSoftVersions() {
		return softVersions;
	}

	public void setSoftVersions(String softVersions) {
		this.softVersions = softVersions;
	}

	public String getResoucePoolType() {
		return resoucePoolType;
	}

	public void setResoucePoolType(String resoucePoolType) {
		this.resoucePoolType = resoucePoolType;
	}

	@Override
	public String toString() {
		return  resoucePoolName + "=====" + resoucePoolCode + ", yumIp=" + yumIp + ", networkVlanRanges=" + networkVlanRanges + ", publicVlan=" + publicVlan + ", apiVip=" + apiVip + ", storageVlanNumber=" + storageVlanNumber + ", novncproxyPublicUrl=" + novncproxyPublicUrl + ", vpnIp=" + vpnIp + ", vpnUser=" + vpnUser + ", vpnPassword=" + vpnPassword + ", ipmiUser=" + ipmiUser + ", ipmiPassword=" + ipmiPassword + ", portalIp=" + portalIp
				+ ", apiVirtualRouterId=" + apiVirtualRouterId + ", cell01VirtualRouterId=" + cell01VirtualRouterId + ", cell02VirtualRouterId=" + cell02VirtualRouterId + ", cell03VirtualRouterId=" + cell03VirtualRouterId + ", publicIp=" + publicIp + ", softVersions=" + softVersions + ", resoucePoolType=" + resoucePoolType + ", createTime=" + createTime + "]";
	}

}
