package com.wo.common;

import java.util.HashSet;
import java.util.List;

import com.wo.entity.CabinetInfoPo;
import com.wo.entity.ImageIsoPo;
import com.wo.entity.LinkInfoPo;
import com.wo.entity.NetworkDevicePo;
import com.wo.entity.ResourcesPool;
import com.wo.entity.RoomInfoPo;
import com.wo.entity.ServersInfoPo;

public class ResManagementExcelVo {
	// 资源池列表
	ResourcesPool resPoolList;
	// 服务器列表
	List<ServersInfoPo> serverList;
	// 网络设备列表
	List<NetworkDevicePo> networkEquipList;
	// 链路
	List<LinkInfoPo> linkInfoList;
	// 机房信息列表
	List<RoomInfoPo> roomInfoList;
	// 机柜信息列表
	List<CabinetInfoPo> cabinetList;
	// 镜像信息列表
	List<ImageIsoPo> imageIso;
	HashSet<String> errorList;
	// 警告信息
	List<String> warningList;
	// 是否成功
	boolean isSuccess;

	public HashSet<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(HashSet<String> errorList) {
		this.errorList = errorList;
	}
	public List<String> getWarningList() {
		return warningList;
	}
	public void setWarningList(List<String> warningList) {
		this.warningList = warningList;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public ResourcesPool getResPoolList() {
		return resPoolList;
	}
	public void setResPoolList(ResourcesPool resPoolList) {
		this.resPoolList = resPoolList;
	}
	public List<ServersInfoPo> getServerList() {
		return serverList;
	}
	public void setServerList(List<ServersInfoPo> serverList) {
		this.serverList = serverList;
	}
	public List<NetworkDevicePo> getNetworkEquipList() {
		return networkEquipList;
	}
	public void setNetworkEquipList(List<NetworkDevicePo> networkEquipList) {
		this.networkEquipList = networkEquipList;
	}
	public List<LinkInfoPo> getLinkInfoList() {
		return linkInfoList;
	}
	public void setLinkInfoList(List<LinkInfoPo> linkInfoList) {
		this.linkInfoList = linkInfoList;
	}
	public List<RoomInfoPo> getRoomInfoList() {
		return roomInfoList;
	}
	public void setRoomInfoList(List<RoomInfoPo> roomInfoList) {
		this.roomInfoList = roomInfoList;
	}
	public List<CabinetInfoPo> getCabinetList() {
		return cabinetList;
	}
	public void setCabinetList(List<CabinetInfoPo> cabinetList) {
		this.cabinetList = cabinetList;
	}
	public List<ImageIsoPo> getImageIso() {
		return imageIso;
	}
	public void setImageIso(List<ImageIsoPo> imageIso) {
		this.imageIso = imageIso;
	}


}
