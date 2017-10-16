package com.wo.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wo.util.CommonUtil;


public class ReadAutoDeployExcel {
	// 警告信息
	List<String> warningMessageList;
	// 错误信息
	HashSet<String> errorMessageList;

	/**
	 * 校验文件中的数据
	 * 
	 * @param xlBook
	 */

	private boolean checkAutoEnvironment(XSSFWorkbook xl) throws Exception {
		boolean res = true;
		for (int numSheet = 0; numSheet < xl.getNumberOfSheets(); numSheet++) {
			XSSFSheet sheet = xl.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			if (sheet.getSheetName().equals("部署环境确认表")) {
				for (int rowNum = 2; rowNum < sheet.getLastRowNum(); rowNum++) {
					if (rowNum == 2 || rowNum == 3 || rowNum == 8
							|| rowNum == 9 || rowNum == 17 || rowNum == 19
							|| rowNum == 20 || rowNum == 21) {
						continue;
					}
					XSSFRow row = sheet.getRow(rowNum);
					if (row.getCell(5).toString().trim().equals("")) {
						errorMessageList.add("Sheet:" + row.getCell(3).toString()
								+ ",单元格的实际值不能为空!");
						res = false;
					}
				}

			}
		}
		return res;
	}

	private boolean checkAutoDeployXls(XSSFWorkbook xlBook) throws Exception {
		boolean res = true;
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xlBook.getNumberOfSheets(); numSheet++) {
			XSSFSheet sheet = xlBook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			if (sheet.getSheetName().equals("01.资源池信息")) {
				// 循环数据行
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null) {
						break;
					}
					int lastCellNum = row.getLastCellNum();
					for (int cellNum = 0; cellNum <= 19; cellNum++) {
						if (row.getCell(cellNum) == null) {
							row.createCell(cellNum);
						}
					}
					if (lastCellNum == -1) {
						break;
					}
					if (isRowEmpty(row, 19)) {
						break;
					}
					// 名称
					String resPoolName = row.getCell(0).toString();
					if (resPoolName.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 1
								+ "]:资源池名称不能为空!");
						res = false;
					}
					// 编码
					String resPoolCode = row.getCell(1).toString();
					if (resPoolCode.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 2
								+ "]:资源池编码不能为空!");
						res = false;
					}
					// yum源IP
					String yumIp = row.getCell(2).toString();
					if (resPoolCode.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 3
								+ "]:yum源IP不能为空!");
						res = false;
					} else if (!CommonUtil.isIP(yumIp)) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 3
								+ "]:yum源IP格式不正确!");
						res = false;
					}
					// 业务vlan端
					String networkVlanRanges = (row.getCell(3).toString());
					if (networkVlanRanges.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 4
								+ "]:业务vlan段不能为空!");
						res = false;
					}
					// 公网vlan号
					String publicVlan = row.getCell(4).toString();
					if (publicVlan.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 5
								+ "]:公网vlan号不能为空!");
						res = false;
					} else if (!CommonUtil.isNumber(publicVlan)) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 5
								+ "]:公网vlan号必须为数字!");
						res = false;
					}
					// 管理VIP
					String apiVip = row.getCell(5).toString();
					if (apiVip.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 6
								+ "]:管理VIP不能为空!");
						res = false;
					} else if (!CommonUtil.isIP(apiVip)) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 6
								+ "]:管理VIP的IP格式不正确!");
						res = false;
					}
					// 存储vlan
					String storageVlanNumber = row.getCell(6).toString();
					if (storageVlanNumber.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 7
								+ "]:存储vlan不能为空!");
						res = false;
					} else if (!CommonUtil.isNumber(storageVlanNumber)) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 7
								+ "]:存储vlan必须为数字!");
						res = false;
					}
					// VNC域名（运维复用）
					String novncproxyPublicUrl = row.getCell(7).toString();
					if (novncproxyPublicUrl.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 8
								+ "]:VNC域名（运维复用）不能为空!");
						res = false;
					}
					// VPN地址（运维复用）
					String vpnIp = row.getCell(8).toString();
					if (vpnIp.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 9
								+ "]:VPN地址（运维复用）不能为空!");
						res = false;
					}
					// VPN用户名
					String vpnUser = row.getCell(9).toString();
					if (vpnUser.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 10
								+ "]:VPN用户名（运维复用）不能为空!");
						res = false;
					}
					// VPN密码
					String vpnPassword = row.getCell(10).toString();
					if (vpnPassword.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 11
								+ "]:VPN密码（运维复用）不能为空!");
						res = false;
					}
					// IPMI用户
					String ipmiUser = row.getCell(11).toString();
					if (ipmiUser.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 12
								+ "]:IPMI用户不能为空!");
						res = false;
					}
					// IPMI用户密码
					String ipmiPassword = row.getCell(12).toString();
					if (ipmiPassword.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 13
								+ "]:IPMI用户密码不能为空!");
						res = false;
					}
					// 门户地址
					String portalIp = row.getCell(13).toString();
					if (portalIp.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 14
								+ "]:门户地址不能为空!");
						res = false;
					}
					// api_virtual_router_id
					String apiVirtualRouterId = row.getCell(14).toString();
					if (apiVirtualRouterId.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 15
								+ "]:api_virtual_router_id不能为空!");
						res = false;
					}
					// public_ip
					String publicIp = row.getCell(18).toString();
					if (publicIp.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 19
								+ "]:public_ip不能为空!");
						res = false;
					}
					// 软件版本
					String softVersion = row.getCell(19).toString();
					if (softVersion.trim().equals("")) {
						errorMessageList.add("Sheet:" + sheet.getSheetName()
								+ ",单元格" + "[" + (rowNum + 1) + "," + 20
								+ "]:软件版本不能为空!");
						res = false;
					}
				}
			} else if (sheet.getSheetName().equals("02.服务器信息（运维复用）")) {

				// 获取静态列的名称
				Map<Integer, String> staticTitleList = getStaticTitle(
						sheet.getRow(0), 26);

				// 循环数据行
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null) {
						break;
					}
					int lastCellNum = row.getLastCellNum();
					for (int cellNum = 0; cellNum <= 26; cellNum++) {
						if (row.getCell(cellNum) == null) {
							row.createCell(cellNum);
						}
					}
					if (lastCellNum == -1) {
						break;
					}
					if (isRowEmpty(row, 26)) {
						break;
					}
					// 判断非空
					for (Integer cellNum : staticTitleList.keySet()) {
						if (cellNum == 19 || cellNum == 20) {
							if (row.getCell(3).toString().equals("CELL节点")) {
								String cellValue = row.getCell(cellNum)
										.toString();
								if (cellValue.trim().equals("")) {
									errorMessageList.add("Sheet:"
											+ sheet.getSheetName() + ",单元格"
											+ "[" + (rowNum + 1) + ","
											+ (cellNum + 1) + "]:CELL节点的"
											+ staticTitleList.get(cellNum)
											+ "不能为空!");
									res = false;
								}
							}
						}

						// 跳过非必填项
						if (cellNum == 13 || cellNum == 14 || cellNum == 15
								|| cellNum == 17 || cellNum == 19
								|| cellNum == 20 || cellNum == 26) {
							continue;
						}

						String cellValue = row.getCell(cellNum).toString();
						if (cellNum == 4 || cellNum == 5 || cellNum == 6
								|| cellNum == 16) {
							if (!CommonUtil.isIP(cellValue)) {
								errorMessageList.add("Sheet:"
										+ sheet.getSheetName() + ",单元格" + "["
										+ (rowNum + 1) + "," + (cellNum + 1)
										+ "]:" + staticTitleList.get(cellNum)
										+ "格式不正确!");
								res = false;
							}
						}
						if (cellValue.trim().equals("")) {
							errorMessageList.add("Sheet:"
									+ sheet.getSheetName() + ",单元格" + "["
									+ (rowNum + 1) + "," + (cellNum + 1) + "]:"
									+ staticTitleList.get(cellNum) + "不能为空!");
							res = false;
						}
					}
				}

			} else if (sheet.getSheetName().equals("03.网络设备信息（运维复用）")) {

				// 获取静态列的名称
				Map<Integer, String> staticTitleList = getStaticTitle(
						sheet.getRow(0), 19);
				// 循环数据行
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null) {
						break;
					}
					int lastCellNum = row.getLastCellNum();
					for (int cellNum = 0; cellNum <= 19; cellNum++) {
						if (row.getCell(cellNum) == null) {
							row.createCell(cellNum);
						}
					}
					if (lastCellNum == -1) {
						break;
					}
					if (isRowEmpty(row, 19)) {
						break;
					}
					// 判断非空
					for (Integer cellNum : staticTitleList.keySet()) {
						String cellValue = row.getCell(cellNum).toString();
						if (cellValue.trim().equals("")) {
							errorMessageList.add("Sheet:"
									+ sheet.getSheetName() + ",单元格" + "["
									+ (rowNum + 1) + "," + (cellNum + 1) + "]:"
									+ staticTitleList.get(cellNum) + "不能为空!");
							res = false;
						}
					}
				}
			} else if (sheet.getSheetName().equals("04.链路信息（运维复用）")) {
				// 获取静态列的名称
				Map<Integer, String> staticTitleList = getStaticTitle(
						sheet.getRow(0), 13);
				// 循环数据行
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null) {
						break;
					}
					int lastCellNum = row.getLastCellNum();
					for (int cellNum = 0; cellNum <= 13; cellNum++) {
						if (row.getCell(cellNum) == null) {
							row.createCell(cellNum);
						}
					}
					if (lastCellNum == -1) {
						break;
					}
					if (isRowEmpty(row, 13)) {
						break;
					}
					// 判断非空
					for (Integer cellNum : staticTitleList.keySet()) {
						// 跳过非必填项
						if (cellNum == 5 || cellNum == 7 || cellNum == 8) {
							continue;
						}
						String cellValue = row.getCell(cellNum).toString();
						if (cellValue.trim().equals("")) {
							errorMessageList.add("Sheet:"
									+ sheet.getSheetName() + ",单元格" + "["
									+ (rowNum + 1) + "," + (cellNum + 1) + "]:"
									+ staticTitleList.get(cellNum) + "不能为空!");
							res = false;
						}
					}
				}
			} else if (sheet.getSheetName().equals("05.机房信息表（运维用）")) {
				// 获取静态列的名称
				Map<Integer, String> staticTitleList = getStaticTitle(
						sheet.getRow(0), 5);
				// 循环数据行
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null) {
						break;
					}
					int lastCellNum = row.getLastCellNum();
					for (int cellNum = 0; cellNum <= 5; cellNum++) {
						if (row.getCell(cellNum) == null) {
							row.createCell(cellNum);
						}
					}
					if (lastCellNum == -1) {
						break;
					}
					if (isRowEmpty(row, 5)) {
						break;
					}
					// 判断非空
					for (Integer cellNum : staticTitleList.keySet()) {
						// 跳过非必填项
						if (cellNum == 4 || cellNum == 5) {
							continue;
						}
						String cellValue = row.getCell(cellNum).toString();
						if (cellValue.trim().equals("")) {
							errorMessageList.add("Sheet:"
									+ sheet.getSheetName() + ",单元格" + "["
									+ (rowNum + 1) + "," + (cellNum + 1) + "]:"
									+ staticTitleList.get(cellNum) + "不能为空!");
							res = false;
						}
					}
				}
			} else if (sheet.getSheetName().equals("06.机柜信息表（运维用）")) {
				// 获取静态列的名称
				Map<Integer, String> staticTitleList = getStaticTitle(
						sheet.getRow(0), 5);
				// 循环数据行
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null) {
						break;
					}
					int lastCellNum = row.getLastCellNum();
					for (int cellNum = 0; cellNum <= 5; cellNum++) {
						if (row.getCell(cellNum) == null) {
							row.createCell(cellNum);
						}
					}
					if (lastCellNum == -1) {
						break;
					}
					if (isRowEmpty(row, 5)) {
						break;
					}
					// 判断非空
					for (Integer cellNum : staticTitleList.keySet()) {
						// 跳过非必填项
						if (cellNum == 4 || cellNum == 5) {
							continue;
						}
						String cellValue = row.getCell(cellNum).toString();
						if (cellValue.trim().equals("")) {
							errorMessageList.add("Sheet:"
									+ sheet.getSheetName() + ",单元格" + "["
									+ (rowNum + 1) + "," + (cellNum + 1) + "]:"
									+ staticTitleList.get(cellNum) + "不能为空!");
							res = false;
						}
					}
				}
			} else if (sheet.getSheetName().equals("07.镜像信息（运维复用）")) {
				// 获取静态列的名称
				Map<Integer, String> staticTitleList = getStaticTitle(
						sheet.getRow(0), 5);
				// 循环数据行
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);
					if (row == null) {
						break;
					}
					int lastCellNum = row.getLastCellNum();
					for (int cellNum = 0; cellNum <= 5; cellNum++) {
						if (row.getCell(cellNum) == null) {
							row.createCell(cellNum);
						}
					}
					if (lastCellNum == -1) {
						break;
					}
					if (isRowEmpty(row, 5)) {
						break;
					}
					// 判断非空
					for (Integer cellNum : staticTitleList.keySet()) {
						String cellValue = row.getCell(cellNum).toString();
						if (cellValue.trim().equals("")) {
							errorMessageList.add("Sheet:"
									+ sheet.getSheetName() + ",单元格" + "["
									+ (rowNum + 1) + "," + (cellNum + 1) + "]:"
									+ staticTitleList.get(cellNum) + "不能为空!");
							res = false;
						}
					}
				}
			}
		}
		return res;
	}

	private boolean isRowEmpty(XSSFRow row, int cellSize) throws Exception {
		for (int cellNum = 0; cellNum <= cellSize; cellNum++) {
			if (row.getCell(cellNum) == null) {
				continue;
			}
			if (!row.getCell(cellNum).toString().trim().equals("")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取静态列的名称
	 */
	private Map<Integer, String> getStaticTitle(XSSFRow titleRow, int lastRowNum)
			throws Exception {
		Map<Integer, String> titleList = new HashMap<Integer, String>();
		for (int cellNum = 0; cellNum < lastRowNum; cellNum++) {
			if (titleRow.getCell(cellNum) == null) {
				break;
			}
			String tmpTitle = titleRow.getCell(cellNum).toString();
			if (tmpTitle.trim().equals("")) {
				break;
			}
			titleList.put(cellNum, tmpTitle);
		}
		return titleList;
	}

}
