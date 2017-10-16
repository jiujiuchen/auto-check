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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/jquery_common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据中心自动化运维管理平台</title>
<link type="text/css" rel="stylesheet"
	href="<%=context%>/pagesV2.0/main/css/index.css" />

<!--  -->

<%-- <script type="text/javascript" src="<%=context%>/scripts/jquery-1.8.0.min.js"></script> --%>

<script type="text/javascript" src="<%=context%>/jquery/js/jquery.jbox.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/jquery_global.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/jquery.open-layer.js"></script>
<script type="text/javascript" src="<%=context%>/common/javascript/main.js"></script>
<!--  -->

<!--  -->
<link rel="stylesheet" type="text/css" href="<%=context%>/jquery/css/jbox.css"></link>
<!--  -->

<style type="">
.btn_ok {
	width:51px;
	height: 26px;
	background:url(./images/auto/searchBtn8.jpg) no-repeat;
	font-size: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	cursor: pointer;
	margin-right: 10px;
}
</style>

<script type="text/javascript">
	
	
    $(document).ready(function(){ 
    	//输入提示;
		$("#username").blur(function(){
			if($("#username").val() == ""){
				$(this).attr("value","请输入用户名");
			}
		});
    	
		$("#username").focus(function(){
			if($("#username").val() == "请输入用户名"){
				$(this).attr("value","");
			}
		});
		
		$("#password").blur(function(){
			if($("#password").val() == ""){
				$(this).attr("value","111111");
			}
		});
    	
		$("#password").focus(function(){
			if($("#password").val() == "111111"){
				$(this).attr("value","");
			}
		});
		
		$("#idCode").blur(function(){
			if($("#idCode").val() == ""){
				$(this).attr("value","验证码");
			}
		});
    	
		$("#idCode").focus(function(){
			if($("#idCode").val() == "验证码"){
				$(this).attr("value","");
			}
		});
    	
    }); 

	function refresh() {
		document.getElementById("Image1").src = "./pagesV2.0/common/rand.jsp?"
				+ Math.random();
	}

	//平台用户登录;
	function login() {
		var un = $("#username").val();
		var pwd = $("#password").val();
		var ic = $("#idCode").val();
		
		if(un == "请输入用户名"){
			un = "";
		}
		
		if(pwd == "111111"){
			pwd = "";
		}
		
		if(ic == "验证码"){
			ic = "";
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
				auto_load_info = "";
				showTip("load");
			},
			success : function(data) {
				closeTip();
				if(data.success){
					//新版跳转;
					location.href = "<%=context%>/pagesV2.0/main/index.jsp";
				}else {
					if(data.msg == "1" || data.msg == "2") { // 1、没找到证书 2、证书过期
						var msg = (data.msg == "1") ? "是否导入证书？" : "证书过期，是否重新导入证书？";
						showTip(msg, function() {
							$("#certificateDiv").dialog({
								width : 500,
								autoOpen : true,
								modal : true,
								height : 200,
								title : '证书导入'
							});
						});//是否导入证书
					} else {
						showTip(data.msg);
						//alert(data.msg);
						refresh();
					}
				}
			},
			error : function(e) {
				showTip(exception_info);
			}
		});
	}
	
	//重置;
	function reset(){
		$("#username").attr("value","请输入用户名");
		$("#password").attr("value","111111");
		$("#idCode").attr("value","验证码");
	}
	
	
	//回车事件;
	document.onkeydown = function (e) {
	    if (!e) e = window.event; //火狐中是 window.event
	    if ((e.keyCode || e.which) == 13) {
	    	login();
	    }
	}
	function fileLoad() {
		var fileValues = document.getElementById("myFile").value.split("\\");
		var frontName = fileValues[fileValues.length - 1].split(".");
		if (frontName[1] == "lic" || frontName[1] == "LIC") {
			// document.getElementById("importform").submit();
			importform.submit();
		} else {
			alert("请上传正确的证书文件！");
		}
  }
</script>


<style>
.inFooter{
	width:733px; 
	height:140px;
	margin-left:20px;
	background:url(./pagesV2.0/main/images/inFooter_bg.png) no-repeat;
	}
</style>

</head>

<body style="overflow: -Scroll; overflow-y: hidden">
	<div class="Wrap">
		<div class="WrapCon">
			<h2 class="logo">数据中心自动化运维管理平台</h2>
			<div class="Loginbar">
				<form action="<%=context%>/shiro/login.action" method="post" name="form1">
					<div class="Loginbar_left left">
						<input type="text" value="请输入用户名" name="username" id="username" />
						<input type="password" class="password" value="111111" name="password"
							id="password" />
						<div class="Validate_code">
							<input type="text" class="left" value="验证码" name="idCode" id="idCode"/><span class="left"><img
								id="Image1" title="验证码"
								src="<%=context%>/pagesV2.0/common/rand.jsp" align="absbottom"
								style="border-style: none; border-width: 0px;" /></span><span
								class="float">看不清</span> <a href="javascript:refresh();">换一张</a>
						</div>
						<div class="Btn_wrap">
							<a href="javascript:login();" class="Btn_true">确
								定</a><a href="javascript:reset();" class="Btn_false">重 置</a>
						</div>
					</div>
				</form>
				<div class="Loginbar_right right">
					<img src="<%=context%>/pagesV2.0/main/images/photo1.jpg"
						width="387" height="166" />
				</div>
			</div>
			<div class="inFooter"></div>
			<!-- 
			<ul class="IconWrap">
				<li class="icon1">
					<h3>标准化</h3>
					<p>标准化的互联网IT自动化运维方式。</p>
				</li>
				<li class="icon2">
					<h3>高效率</h3>
					<p>颠覆传统数据中心运维模式。</p>
				</li>
				<li class="icon3">
					<h3>低成本</h3>
					<p>以极低成本提高运维服务级别,降低时间。</p>
				</li>
			</ul>
			 -->
		</div>
<!-- 上传证书 -->
	</div>
		<div id="certificateDiv" style="display: none; background-color: white;">
		<form action="<%=context%>/shiro/fileUpload.action" method="post" enctype="multipart/form-data" name="importform" id="importform">
			<table align="center" width="90%">
				<tr>
					<td width="20%">请选择证书：</td>
					<td style="padding-top: 15px;"><input type="file" id="myFile" name="myFile" style="border: 1px solid #ABABAB;width: 80%; height: 30px; background-color: white;padding-left: 5px;"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="button" class="btn_ok"  value="" onclick="fileLoad()"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
<!-- 上传证书 -->

<!-- 等待特效 start-->


<!-- 等待特效 end-->

</body>
</html>
