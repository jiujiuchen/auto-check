package com.wo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.wo.entity.PageBean;
import com.wo.entity.RecordInfoPo;
import com.wo.service.CheckEnvironmentService;
import com.wo.service.impl.CheckEnvironmentServiceImpl;
import com.wo.util.DateJsonValueProcessor;
import com.wo.util.ResponseUtil;

public class ShowDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求回送编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("star", pageBean.getStart());
		map.put("rows", pageBean.getRows());
		CheckEnvironmentService ces = new CheckEnvironmentServiceImpl();
		int total;
		List<RecordInfoPo> recordList = new ArrayList<RecordInfoPo>();
		recordList = ces.findRecord(map);
		total = ces.countRecord(map);
		JSONObject result = new JSONObject();
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.registerJsonValueProcessor(java.sql.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.registerJsonValueProcessor(java.sql.Timestamp.class,
				new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = JSONArray.fromObject(recordList, config);
		result.put("total", total);
		result.put("rows", jsonArray);
		try {
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
