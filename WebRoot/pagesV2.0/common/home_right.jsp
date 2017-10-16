<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript">

	$(function() {
		getServerTime();
	});
	
	function getServerTime(){
		setInterval(function() {
			$("#server_time_span").text("系统时间：    " + getNowFormatDate());
		}, 1000);
	}
	
	//获取当前系统时间;
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    
	    var hours = date.getHours();
	    if (hours <= 9) {
	    	hours = "0" + hours;
		}
	    
	    var minutes = date.getMinutes();
	    if (minutes <= 9) {
	    	minutes = "0" + minutes;
		}
	    
	    var seconds = date.getSeconds();
	    if (seconds <= 9) {
	    	seconds = "0" + seconds;
		}
	    
	    
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + hours + seperator2 + minutes
	            + seperator2 + seconds;
	    return currentdate;
	}
</script>
<P class="welC" style="line-height:64px;float:left;color: #aaabb6;">&nbsp;欢迎<strong>【<shiro:user><shiro:principal /></shiro:user>】</strong>用户，登陆后台管理平台！
<strong>系统消息</strong>&nbsp;&nbsp;<span class="homeTime"
		id="server_time_span">系统时间： 2015-08-08&nbsp;&nbsp;08:08:08</span>
</P>