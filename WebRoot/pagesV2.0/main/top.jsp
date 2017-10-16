<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String _path = request.getContextPath();
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>头部页面</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <link href="css/public.css" rel="stylesheet" type="text/css">
   <!--  <link href="css/main.css" rel="stylesheet" type="text/css"> -->
    <%@ include file="/common/jquery_common.jsp"%>
<script type="text/javascript">
	$(function() {
		setInterval(function() {
			$("#server_time_span").text("系统时间:" + new Date().toLocaleString());
		}, 1000);
	});
	function goto2(obj,url){
		$(".menu2 > li").each(function(){
			$(this).attr("class","");
		});
		$(obj).attr("class","menu2Active");
		parent.frames["main"].location.href = url;
	}
	
	//退出系统平台;
	function exitSystem(){
		 if(confirm("您确定要退出系统平台吗？")){
			 window.top.location='<%=_path%>/shiro/logout.action';
		 }
	}
</script>
</head>

<body>
<div class="header">
	<div class="headerTop">
			<div class="logo left"></div>
			 <div class="home_right">
				<!-- 引入导航界面 start -->
				<jsp:include page="/pagesV2.0/common/home_right.jsp"></jsp:include>
				<!-- 引入导航界面 end -->
			</div> 
			<ul class="loginMsg right">
           <li><a href="#" onclick="parent.location.reload();"><img src="images/Msg2-1.png" width="27" height="30" title="我的主页"></a></li>
           <li><a href="#" onclick="parent.location.reload();"><img src="images/Msg2-2.png" width="27" height="30" title="刷新页面"></a></li> 
           <li><a href="#" onclick="exitSystem()"><img src="images/Msg2-3.png" width="27" height="30" title="退出系统"></a></li> 
        </ul>
    </div>
</div>
</body>
</html>
