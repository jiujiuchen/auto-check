<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
	/* 	System.out.println("url=" + url + ",context:" + context); */
	url += context;
	application.setAttribute("ctx", url);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>沃云自动化部署信息检查平台</title>
<!-- 公共的文件 start-->
<script type="text/javascript"
	src="<%=context%>/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=context%>/jquery/js/grid.locale-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=context%>/jquery/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%=context%>/jquery/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=context%>/jquery/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=context%>/jquery/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=context%>/jquery/js/common.js"></script>
<script type="text/javascript"
	src="<%=context%>/jquery/js/jquery.jbox.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/jquery_global.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/jquery.open-layer.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=context%>/jquery/css/jbox.css"></link>
<link rel="stylesheet" type="text/css"
	href="<%=context%>/pagesV2.0/main/css/login.css"></link>
<!-- 公共的文件 end -->
<script type="text/javascript"
	src="<%=context%>/jquery/js/jquery.jbox.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/jquery_global.js"></script>
<script type="text/javascript"
	src="<%=context%>/scripts/jquery.open-layer.js"></script>
<script type="text/javascript"
	src="<%=context%>/common/javascript/main.js"></script>

<script type="text/javascript">
	
	//刷新验证码;
	function refreshCode() {
		document.getElementById("Image1").src = "<%=context%>/pagesV2.0/common/rand.jsp?"
				+ Math.random();
	}

	//平台用户登录;
	function login() {
		var un = $("#username").val();
		var pwd = $("#password").val();
		var ic = $("#idCode").val();

		if (!(un != null && un != "")) {
			alert("用户名不能为空");
			return;
		}

		if (!(pwd != null && pwd != "")) {
			alert("密码不能为空");
			return;
		}
		if (!(ic != null && ic != "")) {
			alert("验证码不能为空");
			return;
		}

		$("#ff").submit();
	}

	//重置;
	function reset() {
		$("#username").attr("value", "");
		$("#password").attr("value", "");
		$("#idCode").attr("value", "");
	};
</script>
<style type="text/css">
#Photo {
	position: fixed;
	left: 0;
	top: 0;
	z-index: -1;
}
</style>
</head>

<body>
	<form id="ff" action="<%=context%>/LoginServlet.do?action=login"
		method="post" name="form1">
		<img alt="" src="pagesV2.0/main/images/login/logoIn3_bg.jpg"
			height="100%" width="100%" id="Photo">
			<div class="logininfo">
				<div class="login_content">
					<div class="login_logo"></div>
					<div class="login_inputbox">
						<div class="logoIn_inputWrap input-m1">
							<i class="icon-username"></i> <input type="text" name="username"
								id="username" placeholder="请输入用户名" value="${username }" />
						</div>
						<div class="logoIn_inputWrap input-m2">
							<i class="icon-password"></i> <input type="password"
								class="password" name="password" id="password" placeholder="密码" value="${password }" />
						</div>
						<div class="logoIn_inputWrap input-w25" style="float:left">
							<input type="text" placeholder="验证码" id="idCode" name="idCode"/>
						</div>
						<div class="logoIn_code">
							<span class="yanZen"><img id="Image1" title="验证码"
								src="<%=context%>/pagesV2.0/common/rand.jsp" align="absbottom"
								style="border-style: none; border-width: 0px;" /></span><a
								href="javascript:refreshCode();"><img
								src="<%=context%>/pagesV2.0/main/images/login/Refresh.png"
								width="100%" height="100%" title="刷新验证码" /></a>
						</div>
						<div style="clear:left"></div>
						<div class="logoIn_inputWrap4">
							<a href="javascript:login();" class="login-btn btnTrue">确定</a> <a
								href="javascript:reset();" class="login-btn btnReset">重置</a>
						</div><br /><br />
						<div>
						<h2 style="color: red;">${msg }</h2>
						</div>
					</div>
				</div>
			</div>
	</form>
</body>
</html>
