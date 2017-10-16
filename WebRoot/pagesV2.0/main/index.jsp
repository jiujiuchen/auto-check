<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据中心自动化运维管理平台</title>
</head>
    <frameset rows="64,*,23" frameborder="0"  framespacing="0" border="0" >
        <frame src="<%=path%>/pagesV2.0/main/top.jsp" id="top" name="top" scrolling="no"/>
        <frameset cols="217,*" frameborder="0" >
            <frame src="<%=path%>/pagesV2.0/main/menu.jsp" name="menu" scrolling="no"/>
            <frame src="<%=path%>/pagesV2.0/main/main.jsp" name="main"/>
        </frameset>
       <%--  <framsest frameborder="0" framespacing="0" border="0">
        	  <frame src="<%=path%>/pagesV2.0/main/footer.html" name="menu" />
        </frameset> --%>
    </frameset>

    <noframes>
    	您的浏览器不支持frame框架，请使用IE浏览器！
    </noframes>
</html>
