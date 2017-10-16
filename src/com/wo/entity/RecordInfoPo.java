package com.wo.entity;

import java.util.Date;

public class RecordInfoPo {
	// 记录的id
	private String recordId;

	// 项目负责人
	private String projectLeader;

	// 负责人电话
	private String projectTel;

	// 负责人邮箱
	private String projectMail;

	// 上传校验日期
	private Date projectDate;

	// 资源池名称
	private String resPoolName;
	
	//资源池类型
	private String resPoolType;

	// 资源池信息excel表名称
	private String resPoolExcelName;

	// 部署环境信息表名称
	private String deployExcelName;

	// 信息表校验状态 0 表示校验失败，1表示校验成功
	private int uploadStatus = 0;

	//错误信息
	private String errorInfo;

	// 表存储路径
	private String excelPath;

	
	
	
	/**
	 * @return the resPoolType
	 */
	public String getResPoolType() {
		return resPoolType;
	}

	/**
	 * @param resPoolType the resPoolType to set
	 */
	public void setResPoolType(String resPoolType) {
		this.resPoolType = resPoolType;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @param errorInfo the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}

	/**
	 * @param recordId
	 *            the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	/**
	 * @return the projectLeader
	 */
	public String getProjectLeader() {
		return projectLeader;
	}

	/**
	 * @param projectLeader
	 *            the projectLeader to set
	 */
	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	/**
	 * @return the projectTel
	 */
	public String getProjectTel() {
		return projectTel;
	}

	/**
	 * @param projectTel
	 *            the projectTel to set
	 */
	public void setProjectTel(String projectTel) {
		this.projectTel = projectTel;
	}

	/**
	 * @return the projectMail
	 */
	public String getProjectMail() {
		return projectMail;
	}

	/**
	 * @param projectMail
	 *            the projectMail to set
	 */
	public void setProjectMail(String projectMail) {
		this.projectMail = projectMail;
	}

	/**
	 * @return the projectDate
	 */
	public Date getProjectDate() {
		return projectDate;
	}

	/**
	 * @param projectDate
	 *            the projectDate to set
	 */
	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}

	/**
	 * @return the resPoolName
	 */
	public String getResPoolName() {
		return resPoolName;
	}

	/**
	 * @param resPoolName
	 *            the resPoolName to set
	 */
	public void setResPoolName(String resPoolName) {
		this.resPoolName = resPoolName;
	}

	/**
	 * @return the resPoolExcelName
	 */
	public String getResPoolExcelName() {
		return resPoolExcelName;
	}

	/**
	 * @param resPoolExcelName
	 *            the resPoolExcelName to set
	 */
	public void setResPoolExcelName(String resPoolExcelName) {
		this.resPoolExcelName = resPoolExcelName;
	}

	/**
	 * @return the deployExcelName
	 */
	public String getDeployExcelName() {
		return deployExcelName;
	}

	/**
	 * @param deployExcelName
	 *            the deployExcelName to set
	 */
	public void setDeployExcelName(String deployExcelName) {
		this.deployExcelName = deployExcelName;
	}



	/**
	 * @return the uploadStatus
	 */
	public int getUploadStatus() {
		return uploadStatus;
	}

	/**
	 * @param uploadStatus the uploadStatus to set
	 */
	public void setUploadStatus(int uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	/**
	 * @return the excelPath
	 */
	public String getExcelPath() {
		return excelPath;
	}

	/**
	 * @param excelPath
	 *            the excelPath to set
	 */
	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

}
