package com.wo.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wo.common.GetResourcePool;
import com.wo.entity.RecordInfoPo;
import com.wo.service.CheckEnvironmentService;
import com.wo.service.impl.CheckEnvironmentServiceImpl;
import com.wo.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@MultipartConfig()
public class CheckEnvironmentServlet extends HttpServlet {
	// 警告信息
	List<String> warningMessageList;
	// 错误信息
	HashSet<String> errorMessageList;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 获取上传文件的名称
	private String getFileName(Part part) {
		if (part == null) {
			return null;
		}
		String fileName = part.getHeader("content-disposition");
		return substringBetween(fileName, "filename=\"", "\"");
	}

	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null)
			return null;
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1)
				return str.substring(start + open.length(), end);
		}
		return null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		System.out.println("开始进行传值验证。。。。。。。。。。");

		String path = this.getServletConfig().getServletContext()
				.getRealPath("/");

		// 获取前台传入的值
		String projectLeader = request.getParameter("projectLeader");
		String projectTel = request.getParameter("projectTel");
		String projcetMail = request.getParameter("projectMail");
		String resPoolName = request.getParameter("resPoolName");
		String resPoolType = request.getParameter("resPoolType");
		Date date = new Date();

		// 获取当前的时间
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String dateStr = df.format(date);// new Date()为获取当前系统时间

		// 获取前台传入的文件
		Part part = request.getPart("filepath");
		Part part1 = request.getPart("filepath1");

		// 获取文件的名称
		String fileName = getFileName(part).split("\\.")[0] + "-"
				+ projectLeader + "-" + dateStr + ".xlsx";
		String fileName1 = getFileName(part1).split("\\.")[0] + "-"
				+ projectLeader + "-" + dateStr + ".xlsx";

		// 将文件写入固定的目录
		part.write(path + "/tmpExcel/" + fileName);
		part1.write(path + "/tmpExcel/" + fileName1);

		// 读取EXCEL文档
		FileInputStream fis = new FileInputStream(new File(path + "/tmpExcel/"
				+ fileName));// 读取文件的路径
		XSSFWorkbook workbook = new XSSFWorkbook(new BufferedInputStream(fis));
		

		// 读取EXCEL文档
		FileInputStream fis1 = new FileInputStream(new File(path + "/tmpExcel/"
				+ fileName1));// 读取文件的路径
		XSSFWorkbook workbook1 = new XSSFWorkbook(new BufferedInputStream(fis1));

		
		GetResourcePool getResource = new GetResourcePool();
		// 错误信息列表
		List<String> list = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			list = getResource.checkAutoDeployXls(workbook,resPoolType);
			list.addAll(getResource.checkAutoEnvironment(workbook1));

			RecordInfoPo re = new RecordInfoPo();
			// 设置记录的id
			re.setRecordId(UUID.randomUUID().toString());

			// 设置校验的时间
			re.setProjectDate(new Date());

			// 设置项目的负责人
			re.setProjectLeader(projectLeader.trim());
			// 设置项目负责人的邮箱
			re.setProjectMail(projcetMail.trim());

			// 设置项目负责人的电话
			re.setProjectTel(projectTel.trim());

			// 设置资源池的名称
			re.setResPoolName(resPoolName.trim());
			
			//设置资源池的类型
			re.setResPoolType(resPoolType);

			// 设置错误的信息以及上传的状态
			if (list.size() > 0) {
				re.setErrorInfo(list.toString());
			} else {
				re.setUploadStatus(1);
			}
			// 设置保存的路径
			re.setExcelPath(path + "tmpExcel");

			// 设置资源池信息的保存名称
			re.setResPoolExcelName(fileName);
			// 设置部署环境信息标的保存名称
			re.setDeployExcelName(fileName1);

			// 将记录插入数据库
			CheckEnvironmentService ces = new CheckEnvironmentServiceImpl();
			ces.insertRecord(re);

			if (list.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(list);
				result.put("success", "true");
				result.put("errorMsg", jsonArray);
			} else {
				result.put("success", "true");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
