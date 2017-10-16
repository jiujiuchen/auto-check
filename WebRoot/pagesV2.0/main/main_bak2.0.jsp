<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.git.cloud.sys.model.po.SysUserPo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

<%
    SysUserPo loginUser = (SysUserPo) SecurityUtils.getSubject().getPrincipal();
	boolean isManager = loginUser.getIsManager();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=path%>/common/css/newTop.css"
	type="text/css"></link>
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/yanue.pop.js"></script>
<title>系统主页</title>
<script>
	$(function() {
		//加载例行待办事件;
		maintenance();
		
		//加载巡检待办数据;
		check();
		
		//加载软件维护事件;
		software();
		
		//加载公告信息
		initNoticeInit()
		

	});
	
	//加载例行待办事件;
	function maintenance(){
		var url = "<%=path%>/maintenance/mainPlanToDo.do";
		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			dataType : 'json',
			url : url,
			error : function() {
				alert("请求失败");
			},
			success : function(data) {
				initMainPlan(data);
			}
		});
	}
	
	//加载例行待办事件;
	function check(){
		var url = "<%=path%>/check/task/initSystemIndexCheck.do";
		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			dataType : 'json',
			url : url,
			error : function() {
				alert("请求失败");
			},
			success : function(data) {
				initCheck(data);
			}
		});
	}
	
	//加载软件维护事件;
	function software(){
		var url = "<%=path%>/softwareMaintenance/software_initMain.do";
		$.ajax({
			async : true,
			cache : false,
			type : 'POST',
			dataType : 'json',
			url : url,
			error : function() {
				alert("请求失败");
			},
			success : function(data) {
				initSoft(data);
			}
		});
	}
	
	// 格式化日期
	function formatDate(ns) {
		if (ns) {
			var d = new Date(parseInt(ns.time + ""));
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			if (month <= 9) {
				month = "0" + month;
			}
			var date = d.getDate();
			if (date <= 9) {
				date = "0" + date;
			}
			return year + "-" + month + "-" + date;
		}
	}
	
	function initMainPlan(data){
		var htmlStr = "";
		 if(data.length>0){
			 $.each(data, function(i, plan) {
			     htmlStr=htmlStr+"<li><span class=\"newDat\">"+formatDate(plan.createDatetime)+"</span>"+plan.maintPlanName+"</li>";
			 });
			 if(data.length==3){
				 $("#daiban_more").show();
			 }
		 }else{
			 htmlStr = "暂无相关数据!";
		 }
		
		 document.getElementById("daiban").innerHTML = htmlStr;
	}
	
	//初始化巡检待办事件;
	function initCheck(data){
		 var htmlStr = "";
		 if(data.length>0){
			 $.each(data, function(i, check) {
			     htmlStr=htmlStr+"<li><span class=\"newDat\">"+formatDate(check.createDatetime)+"</span>"+check.taskName+"</li>";
			 });
			 if(data.length==6){
				 $("#xunjian_more").show();
			 }
		 }else{
			 htmlStr = "暂无相关数据!";
		 }
		 document.getElementById("xunjian").innerHTML = htmlStr;
	}
	
	function initSoft(data){
		 var htmlStr = "";
		 if(data.length>0){
			 $.each(data, function(i, soft) {
			     htmlStr=htmlStr+"<li><span class=\"newDat\">"+formatDate(soft.createDatetime)+"</span>"+soft.aliasName+"</li>";
			 });
			 if(data.length==6){
				 $("#software_more").show();
			 }
		 }else{
			 htmlStr = "暂无相关数据!";
		 }
		 document.getElementById("software").innerHTML = htmlStr;
	}

	//加载公告信息
	function initNoticeInit(){
		var url = "<%=path%>/system/notice_findNoticeSize.action";

		$.ajax({
			async : false,
			cache : false,
			url : url,
			data : {size:10},
			type : 'post',
			dataType : "json",
			error : function() {
				showTip(exception_info);//系统出错
			},
			success : function(datas) {
				$("#noticeUl").append("");
				if(datas.length>0){
					for(var i=0; i<datas.length; i++) {
						$("#noticeUl").append("<li value="+datas[i].id+"><span>"+datas[i].noticeName+"</span><strong>["+formatDate(datas[i].createDatetime)+"]</strong></li><br>");
					}
				}else{
					$("#noticeUl").append("<li value=''><span>无数据</span></li>");
				}
			}
		});

	}
	// 格式化日期
	function formatDate(ns) {
		if (ns) {
			var d = new Date(parseInt(ns.time + ""));
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			if (month <= 9) {
				month = "0" + month;
			}
			var date = d.getDate();
			if (date <= 9) {
				date = "0" + date;
			}
			return year + "-" + month + "-" + date;
		}
	}
	

	
</script>

<script>
function checkNewMsg() {
	var url = "<%=path%>/system/notice_findSysNews.action";
	$.ajax({
		async : true,
		cache : false,
		url : url,
		data : {
			size : 1
		},
		type : 'post',
		dataType : "json",
		error : function() {
			showTip(exception_info);// 系统出错
		},
		success : function(datas) {
			if (datas.length > 0) {
				var pop = new Pop(datas[0].newsTitle, datas[0].newsHerf,
						datas[0].newsContents);
				// 更新消息状态;
				updateNewMsg(datas[0].id);
			}
		}
	});
}

function updateNewMsg(newsId) {
	var url = "<%=path%>/system/notice_updateSysNews.action?noticePo.id="
			+ newsId;
	$.ajax({
		async : false,
		cache : false,
		url : url,
		type : 'post',
		dataType : "json",
		error : function() {
			showTip(exception_info);// 系统出错
		}
	});
}

window.onload = function() {
	window.setInterval("checkNewMsg()", 20000);
}

</script>
</head>
<body>
	<div id="nav">
	<div class="home_left"><i class="home"></i><span class="homeNext">当前位置 /</span><span class="active">我的主页</span></div>
		<!-- <div class="home_left"><i class="home"></i><span class="homeNext">当前位置</span><span class="active">我的主页</span></div> -->
		<!-- <div class="home_right">
			<P class="welC">&nbsp;尊敬的【<strong><shiro:user><shiro:principal /></shiro:user></strong>】欢迎您，登陆数据中心自动化运维管理平台！</P>
	        <P class="welT"><strong>意见反馈</strong>&nbsp;&nbsp;<span class="btn-info" id="server_time_span"/></P>
		</div> -->
	</div>
	<div class="conWrap">
		<div class="item">
			<div class="quickKey left">
				<h2 class="mainBt">快捷操作</h2>
				<div class="keyBox">
					<ul class="keyBox_main">
						<li>
							<p class="tu">
								<a href="<%=path%>/maintenance/planIndex.action"><img src="images/mainIcon1.jpg" width="66"
									height="70" /></a>
							</p>
							<p>
								<a href="<%=path%>/maintenance/planIndex.action">例行任务</a>
							</p>
						</li>
						<li>
							<p class="tu">
								<a href="<%=path%>/softwareMaintenance/history_softwareInstallHistory.action"><img src="images/mainIcon2.jpg" width="66"
									height="70" /></a>
							</p>
							<p>
								<a href="<%=path%>/softwareMaintenance/history_softwareInstallHistory.action">软件任务</a>
							</p>
						</li>
						<li>
							<p class="tu">
								<a href="<%=path%>/check/task/init.action"><img src="images/mainIcon3.jpg" width="66"
									height="70" /></a>
							</p>
							<p>
								<a href="<%=path%>/check/task/init.action">巡检任务</a>
							</p>
						</li>
						<li>
							<p class="tu">
								<a href="<%=path%>/gatherlog/gatherLogListView.action"><img src="images/mainIcon4.jpg" width="66"
									height="70" /></a>
							</p>
							<p>
								<a href="<%=path%>/gatherlog/gatherLogListView.action">日志采集</a>
							</p>
						</li>
						<li>
							<p class="tu">
								<a href="<%=path%>/system/gatherdevicemgt/gatherDeviceListView.action"><img src="images/mainIcon5.jpg" width="66"
									height="70" /></a>
							</p>
							<p>
								<a href="<%=path%>/system/gatherdevicemgt/gatherDeviceListView.action">主机采集</a>
							</p>
						</li>
					</ul>
				</div>
			</div>
			<div class="quickNew right" title="系统公告">
				<ul class="newBox" id="">
				<marquee id="noticeUl" onMouseOut="this.start()" onMouseOver="this.stop()" scrollAmount="2" direction="up" height="80">
				</marquee>
				</ul>
			</div>
		</div>
		<div class="item">
			<div class="numberTu left">
				<h2 class="mainBt">平台运维概况</h2>
				<div class="photo">
					<!-- <img src="images/tu1.jpg" width="481" height="234" /> -->
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="481" height="234" id="ReportColumnMain">
                <param name="movie" value="./flex/ReportColumnMain.swf" />
                <param name="quality" value="high" />
                <param name="bgcolor" value="#ffffff" />
                <param name="allowScriptAccess" value="sameDomain" />
                <param name="allowFullScreen" value="true" />
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="./flex/ReportColumnMain.swf" width="481" height="234">
                    <param name="quality" value="high" />
                    <param name="bgcolor" value="#ffffff" />
                    <param name="allowScriptAccess" value="sameDomain" />
                    <param name="allowFullScreen" value="true" />
                <!--<![endif]-->
                <!--[if gte IE 6]>-->
                    <p> 
                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                        11.1.0 or greater is not installed.
                    </p>
                <!--<![endif]-->
                    <a href="http://www.adobe.com/go/getflashplayer">
                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                    </a>
                <!--[if !IE]>-->
                </object>
                <!--<![endif]-->
            </object>
				</div>
			</div>
			<div class="numberTu right">
				<h2 class="mainBt">平台纳管统计</h2>
				<div class="photo">
					<!-- <img src="images/tu2.jpg" width="481" height="234" /> -->
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="481" height="234" id="ReportColumnMain">
                <param name="movie" value="./flex/ReportPieMain.swf" />
                <param name="quality" value="high" />
                <param name="bgcolor" value="#ffffff" />
                <param name="allowScriptAccess" value="sameDomain" />
                <param name="allowFullScreen" value="true" />
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="./flex/ReportPieMain.swf" width="481" height="234">
                    <param name="quality" value="high" />
                    <param name="bgcolor" value="#ffffff" />
                    <param name="allowScriptAccess" value="sameDomain" />
                    <param name="allowFullScreen" value="true" />
                <!--<![endif]-->
                <!--[if gte IE 6]>-->
                    <p> 
                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                        11.1.0 or greater is not installed.
                    </p>
                <!--<![endif]-->
                    <a href="http://www.adobe.com/go/getflashplayer">
                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                    </a>
                <!--[if !IE]>-->
                </object>
                <!--<![endif]-->
            </object>
				</div>
			</div>
		</div>
		<div class="item">
			<div class="newWrap1">
				<h2 class="mainBt">
					<span class="newBt">例行维护</span>
				</h2>
				<div class="newText">
					<%-- <div style="width:100%; hieght:66px;">
						<ul class="newIcon">
							<li class="newIcon1"><a
								href="<%=path%>/maintenance/planIndex.action">维护任务</a></li>
							<li class="newIcon2"><a
								href="<%=path%>/maintenance/taskIndex.action">维护任务</a></li>
							<li class="newIcon3"><a
								href="<%=path%>/pages/maintenance/executeResultShow.jsp">维护历史</a></li>
							<li class="newIcon4"><a
								href="<%=path%>/maintenance/templateIndex.action">模板管理</a></li>
						</ul>
					</div> --%>
					<ul class="newList" id="daiban">
					</ul>
					<div class="newMore" style="padding: 10px 0;display: none;" id="daiban_more">
						<a href="<%=path%>/maintenance/planIndex.action">更多消息</a>
					</div>
				</div>
			</div>
			<div class="newWrap2">
				<h2 class="mainBt">
					<span class="newBt">运行巡检</span>
				</h2>
				<div class="newText">
					<ul class="newList" id="xunjian">
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的成本本提高最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高的最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
					</ul>
					<div class="newMore" style="padding: 10px 0;display: none;" id="xunjian_more">
						<a href="<%=path%>/check/task/init.action">更多消息</a>
					</div>
				</div>
			</div>
			<div class="newWrap3" style="margin: 0; float: right">
				<h2 class="mainBt">
					<span class="newBt">软件维护</span>
				</h2>
				<div class="newText">
					<ul class="newList" id="software">
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的好的最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的成本提成本提高最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高的成本提最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的成本的成本提高好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提最好的最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高的成本提最好的务</a></li>
					</ul>
					<div class="newMore" style="padding: 10px 0;display: none;" id="software_more">
						<a
							href="<%=path%>/softwareMaintenance/history_softwareInstallHistory.action">更多消息</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="pop" style="display:none;">
	<style type="text/css">
	*{margin:0;padding:0;}
	#pop{background:#fff;width:260px;border:1px solid #e0e0e0;font-size:12px;position: fixed;right:10px;bottom:10px;}
	#popHead{line-height:32px;background:#f6f0f3;border-bottom:1px solid #e0e0e0;position:relative;font-size:12px;padding:0 0 0 10px;}
	#popHead h2{font-size:14px;color:#666;line-height:32px;height:32px;}
	#popHead #popClose{position:absolute;right:10px;top:1px;}
	#popHead a#popClose:hover{color:#f00;cursor:pointer;}
	#popContent{padding:5px 10px;}
	#popTitle a{line-height:24px;font-size:14px;font-family:'微软雅黑';color:#333;font-weight:bold;text-decoration:none;}
	#popTitle a:hover{color:#f60;}
	#popIntro{text-indent:24px;line-height:160%;margin:5px 0;color:#666;}
	#popMore{text-align:right;border-top:1px dotted #ccc;line-height:24px;margin:8px 0 0 0;}
	#popMore a{color:#f60;}
	#popMore a:hover{color:#f00;}
	</style>
	<div id="popHead">
	<a id="popClose" title="关闭">关闭</a>
	<h2>系统提示</h2>
	</div>
	<div id="popContent">
	<dl>
		<dt id="popTitle"><a href="http://yanue.info/" target="_blank">这里是参数</a></dt>
		<dd id="popIntro">这里是内容简介</dd>
	</dl>
	<p id="popMore"><a href="http://yanue.info/" target="_blank">查看 »</a></p>
	</div>
</div>
</body>
</html>