package com.wo.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadExcelTempletServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("开始下载文件。。。。");
		String path = this.getServletConfig().getServletContext()
				.getRealPath("/templates/自动化部署导入信息（模版）.xlsx");
		System.out.println(path);
		FileInputStream fis=new FileInputStream(new File(path));
		
		
		System.out.println(fis);
		BufferedInputStream bis = new BufferedInputStream(fis);
		FileOutputStream outputStream = new FileOutputStream(new File(
				"D:/tmp1.xlsx"));
		BufferedOutputStream bos = new BufferedOutputStream(outputStream);

		byte[] buffer = new byte[1024];
		int i = -1;
		while ((i = bis.read(buffer)) != -1) {
			bos.write(buffer, 0, i);
		}
		System.out.println("文件下载成功。。。。。");
		bos.flush();
		bos.close();
		outputStream.close();
		fis.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
