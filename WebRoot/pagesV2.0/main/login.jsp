<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
	System.out.println("url=" + url + ",context:" + context);
	url += context;
	application.setAttribute("ctx", url);
%>
<%-- <%@ include file="/common/jquery_common.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据中心自动化运维管理</title>


	<!-- 公共的文件 start-->
	<script type="text/javascript" src="<%=context%>/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=context%>/jquery/js/grid.locale-zh_CN.js"></script>
	<script type="text/javascript" src="<%=context%>/jquery/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=context%>/jquery/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=context%>/jquery/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=context%>/jquery/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=context%>/jquery/js/common.js"></script>
	<script type="text/javascript" src="<%=context%>/jquery/js/jquery.jbox.js"></script>
	<script type="text/javascript" src="<%=context%>/scripts/jquery_global.js"></script>
	<script type="text/javascript" src="<%=context%>/scripts/jquery.open-layer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=context%>/jquery/css/jbox.css"></link>
	<link rel="stylesheet" type="text/css" href="<%=context%>/pagesV2.0/main/css/login.css"></link>
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
		
		if(!(un != null && un != "")){
			showTip("用户名不能为空!");
			return false;
		}
		
		if(!(pwd != null && pwd != "")){
			showTip("密码不能为空!");
			return false;
		}
		
		if(!(ic != null && ic !="")){
			showTip("验证码不能为空!");
			return false;
		}
		
		$.ajax({
			type : "POST",
			url : "<%=context%>/shiro/login.action",
			data : {
				"username" : un,
				"password" : pwd,
				"idCode" : ic
			},
			async : true,
			cache : false,
			beforeSend:function(XMLHttpRequest){
				showTip("load");
			},
			success : function(data) {
				if(data.success){
					//新版跳转;
					location.href = "<%=context%>/shiro/index.action";
				} else {
					if (data.msg == "1" || data.msg == "2") {
						var msg = (data.msg == "1") ? "是否导入证书？"
								: "证书过期，是否重新导入证书？";
						showTip(msg, function() {
							$("#certificateDiv").dialog({
								width : 500,
								autoOpen : true,
								modal : true,
								height : 200,
								title : '证书导入'
							});
						});
					} else {
						showTip(data.msg);
						refreshCode();
					}
				}
				closeTip();
			},
			error : function(e) {
				showTip("登录请求异常!");
				closeTip();
			}
		});
	}

	//重置;
	function reset() {
		$("#username").attr("value", "");
		$("#password").attr("value", "");
		$("#idCode").attr("value", "");
	}

	//上传证书文件;
	function fileLoad() {
		var fileValues = document.getElementById("myFile").value.split("\\");
		var frontName = fileValues[fileValues.length - 1].split(".");
		if (frontName[1] == "lic" || frontName[1] == "LIC") {
			importform.submit();
		} else {
			showTip("请上传正确的证书文件！");
		}
	}
	
	//回车事件;
	document.onkeydown = function (e) {
	    if (!e) e = window.event; //火狐中是 window.event
	    if ((e.keyCode || e.which) == 13) {
	    	login();
	    }
	}
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


<body >
	<form action="<%=context%>/shiro/login.action" method="post"
		name="form1">
			<img alt="" src="<%=context%>/pagesV2.0/main/images/login/logoIn3_bg.jpg" height="100%" width="100%" id="Photo">
		<div class="logininfo">
		<div class="login_content">
		<div class="login_logo"></div>
		<div class="login_inputbox">
		<div class="logoIn_inputWrap input-m1">
		<i class="icon-username"></i>
			<input type="text" name="username" id="username" placeholder="请输入用户名" />
		</div>
		<div class="logoIn_inputWrap input-m2">
		<i class="icon-password"></i>
			<input type="password" class="password" name="password" id="password"
				placeholder="密码" />
		</div>
		<div class="logoIn_inputWrap input-w25" style="float:left">
			<input type="text" placeholder="验证码" id="idCode"/>
		</div>
		<div class="logoIn_code"><span class="yanZen"><img
				id="Image1" title="验证码" src="<%=context%>/pagesV2.0/common/rand.jsp"
				align="absbottom" style="border-style: none; border-width: 0px;" /></span><a
				href="javascript:refreshCode();"><img
				src="<%=context%>/pagesV2.0/main/images/login/Refresh.png"
				width="100%" height="100%" title="刷新验证码"/></a></div>
				<div style="clear:left"></div>
		<div class="logoIn_inputWrap4">
			<a href="javascript:login();" class="login-btn btnTrue">确定</a>
			<a href="javascript:reset();" class="login-btn btnReset">重置</a>
		  </div>
	  </div>
  </div>
</div>
	</form>

	<!-- 上传证书 -->
	<div id="certificateDiv"
		style="display: none; background-color: white;">
		<form action="<%=context%>/shiro/fileUpload.action" method="post"
			enctype="multipart/form-data" name="importform" id="importform">
			<table align="center" width="90%">
				<tr>
					<td width="20%">请选择证书：</td>
					<td style="padding-top: 15px;"><input type="file" id="myFile"
						name="myFile"
						style="border: 1px solid #ABABAB; width: 80%; height: 30px; background-color: white; padding-left: 5px;" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button"
						class="btn_ok" onclick="fileLoad()" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 上传证书 -->

</body>
</html>
