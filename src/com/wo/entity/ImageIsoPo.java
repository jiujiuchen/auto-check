package com.wo.entity;

public class ImageIsoPo {
	private String resoucePoolName;
	private String imageName;
	private String osName;
	private String bitNum;
	private String format;
	private String size;


	public String getResoucePoolName() {
		return resoucePoolName;
	}

	public void setResoucePoolName(String resoucePoolName) {
		this.resoucePoolName = resoucePoolName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getBitNum() {
		return bitNum;
	}

	public void setBitNum(String bitNum) {
		this.bitNum = bitNum;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ImageIsoPo [getResoucePoolName()=" + getResoucePoolName()
				+ ", getImageName()=" + getImageName() + ", getOsName()="
				+ getOsName() + ", getBitNum()=" + getBitNum()
				+ ", getFormat()=" + getFormat() + ", getSize()=" + getSize()
				+ "]";
	}

}
