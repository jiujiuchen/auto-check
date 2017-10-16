package com.wo.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wo.entity.User;
import com.wo.service.LoginService;
import com.wo.service.impl.LoginServiceImpl;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String idCode = request.getParameter("idCode");

		Object idCodeStr = request.getSession().getAttribute("rand");

		if (idCodeStr.equals(idCode)) {
			LoginService loginService = new LoginServiceImpl();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", userName);
			map.put("passWord", password);
			User user = loginService.findUserByName(map);
			if (user != null) {
				if (user.getState() == 0) {
					request.getRequestDispatcher("/main.jsp").forward(request,
							response);

				} else {
					request.getRequestDispatcher("/index.jsp").forward(request,
							response);
				}
			} else {
				request.setAttribute("username", userName);
				request.setAttribute("password", password);
				request.setAttribute("msg", "您输入的用户名或者密码错误！！！请重试");
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}

		} else {
			request.setAttribute("username", userName);
			request.setAttribute("password", password);
			request.setAttribute("msg", "您输入验证码不正确！！！请重试");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
