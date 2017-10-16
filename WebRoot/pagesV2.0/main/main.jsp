<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>easyaom平台首页</title>
<link href="<%=path %>/pagesV2.0/main/css/main.css" rel="stylesheet" type="text/css" />

<!-- ECharts单文件引入 -->
<script src="<%=path %>/common/javascript/echarts-2.2.7/build/dist/echarts.js"></script>
<script src="<%=path %>/pagesV2.0/main/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">

	var successResult = [2, 4, 7, 23];//成功的数量;
	var failResult = [2, 5, 9, 26];  //失败的数量;
	
	var linuxValue = "400";  //信息采集值;
	var aixValue = "310";
	var hpuxValue = "200";
	var windowsValue = "135";
	
	var pieResult;
	

	$(function() {
		
		// 路径配置
	    require.config({
	        paths: {
	            echarts: '<%=path %>/common/javascript/echarts-2.2.7/build/dist'
	        }
	    });
		
		//获取平台统计数据;
		easyAomCount();
		
		//获取平台的柱状图;
		getAomBar();
		
		//获取平台饼图;
		getAomPie();
		
		//加载例行维护数据;
		maintenance();
		
		//加载运行巡检数据;
		check();
		
		//加载软件维护数据;
		software();
		
		//获取系统信息信息;
		initNoticeInit();

		
		//系统公告;
		setInterval('autoScroll("#noticeContent")',3000); 
		
	});

</script>


<script type="text/javascript">

	//自动化平台数据统计;
	function easyAomCount(){
		var url = "<%=path%>/parameter/management/easyAomCount.do";
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : 'json',
			url : url,
			error : function() {
				alert("请求失败");
			},
			success : function(data) {
				successResult = data.obj.barVo.countTotal;
				failResult = data.obj.barVo.countFail;
				
				//饼图赋值;
				linuxValue = data.obj.pieVo.linuxValue;
				aixValue = data.obj.pieVo.aixValue;
				hpuxValue = data.obj.pieVo.hpuxValue;
				windowsValue = data.obj.pieVo.windowsValue;
				
				pieResult = [{value:linuxValue, name:'Linux'},{value:aixValue, name:'Aix'},{value:hpuxValue, name:'Hp-Ux'},{value:windowsValue, name:'Windows'}];
				
				
			}
		});
	}
</script>

<script type="text/javascript">
    
    //获取平台的柱状图;
    function getAomBar(){
    	
    	// 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('aomBar')); 
                
                var option = {
                	    tooltip : {
                	        trigger: 'axis'
                	    },  
                	   
                	    legend: {
                	    	orient: 'horizontal',
                	    	x:'right',
                	    	y:'top',
                	    	textStyle: {
               	                color: '#f2f2f2'          // 图例文字颜色
               	            },
                	        data:['总数','失败']
                	 },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'category',
                	            data : ['日志采集','例行维护','软件维护','运行巡检']
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value'
                	        }
                	    ],
               	 		// 网格
                	    grid: {
                	        x: 28,
                	        y: 40,
                	        x2: 28,
                	        y2: 40,
                	        backgroundColor: 'rgba(0,0,0,0)',
                	        borderWidth: 1,
                	        borderColor: '#ccc'
                	    },
                	    series : [
                	        {
                	            name:'总数',
                	            type:'bar',
                	            itemStyle:{
                	            	normal:{
                	            		color:'#f1945f',
                	            		barBorderColor:'#f1945f'
                	            	}
                	            },
                	            
                	             barCategoryGap:'43%',
                	            barWidth:27,
                	            data:successResult
                	        },
                	        {
                	            name:'失败',
                	            type:'bar',
                	            itemStyle:{
                	            	normal:{
                	            		color:'#707c90',
                	            		barBorderColor:'#707c90'
                	            	}
                	            },
                	            barCategoryGap:'43%',
                	            barWidth:27,
                	            data:failResult
                	        }
                	    ]
                	};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    }
    
    //获取饼图;
    function getAomPie(){
    	// 使用
        require(
            [
                'echarts',
                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('aomPie')); 
                
                var option = {
                		tooltip : {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                	    },
                	    legend: {
                	        orient : 'vertical',
                	        x : 'right',
                            textStyle: {
            	                color: '#f2f2f2'          // 文字颜色
            	            },
                	        data:['Linux','Aix','Hp-Ux','Windows']
                	    },
                	    calculable : false,
                	    series : [
                	        {
                	            name:'纳管数量',
                	            type:'pie',
                	            radius : [50, 60],
                	            x: '60%',
                	            width: '35%',
                	            funnelAlign: 'left',
                	            max: 1048,
                	            data:pieResult
                	        }
                	    ]
                	};
                	
                	myChart.setOption(option); 
            }
        );
    }
    
</script>

<script type="text/javascript">
	
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
	
	//拼接字符串数据;
	function initMainPlan(data){
		 var htmlStr = "";
		 if(data.length>0){
			 $.each(data, function(i, plan) {
				 if(i % 2 == 0){
					 htmlStr=htmlStr+"<tr class=\"TabcolorBg\"><td>"+plan.maintPlanName+"</td><td>"+formatDate(plan.createDatetime)+"</td></tr>";
				 }else{
					 htmlStr=htmlStr+"<tr class=\"\"><td>"+plan.maintPlanName+"</td><td>"+formatDate(plan.createDatetime)+"</td></tr>";
				 }
			 });
		 }else{
			 htmlStr = "暂无相关数据!";
		 }
		 document.getElementById("daiban").innerHTML = htmlStr;
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
	
	//初始化巡检待办事件;
	function initCheck(data){
		 var htmlStr = "";
		 if(data.length>0){
			 
			 $.each(data, function(i, check) {
				 if(i % 2 == 0){
					 htmlStr=htmlStr+"<tr class=\"TabcolorBg\"><td>"+check.taskName+"</td><td>"+formatDate(check.createDatetime)+"</td></tr>";
				 }else{
					 htmlStr=htmlStr+"<tr class=\"\"><td>"+check.taskName+"</td><td>"+formatDate(check.createDatetime)+"</td></tr>";
				 }
			 });
		 }else{
			 htmlStr = "暂无相关数据!";
		 }
		 document.getElementById("xunjian").innerHTML = htmlStr;
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
	
	function initSoft(data){
		 var htmlStr = "";
		 if(data.length>0){
			 $.each(data, function(i, soft) {
				 
				 if(i % 2 == 0){
					 htmlStr=htmlStr+"<tr class=\"TabcolorBg\"><td>"+soft.aliasName+"</td><td>"+formatDate(soft.createDatetime)+"</td></tr>";
				 }else{
					 htmlStr=htmlStr+"<tr><td>"+soft.aliasName+"</td><td>"+formatDate(soft.createDatetime)+"</td></tr>";
				 }

			 });
		 }else{
			 htmlStr = "暂无相关数据!";
		 }
		 document.getElementById("software").innerHTML = htmlStr;
	}
	
	
	//加载系统公告;
	function initNoticeInit(){
		var url = "<%=path%>/system/notice_findNoticeSize.action";

		$.ajax({
			async : true,
			cache : false,
			url : url,
			data : {size:10},
			type : 'post',
			dataType : "json",
			error : function() {
				showTip(exception_info);//系统出错
			},
			success : function(datas) {
				var noticeStr = "";
				noticeStr +="<ul>";
				if(datas.length>0){
					for(var i=0; i<datas.length; i++) {
						noticeStr += "<li value="+datas[i].id+"><span>"+datas[i].noticeName+"</span>&nbsp;&nbsp;["+formatDate(datas[i].createDatetime)+"]</li>";
					}
				}else{
					noticeStr += "<li value=''><span>无数据</span></li>";
				}
				noticeStr += "</ul>";
				document.getElementById("noticeContent").innerHTML = noticeStr;
			}
		});

	}
	
	//系统公告向上滚动;
	function autoScroll(obj){ 
		$(obj).find("ul:first").animate({ 
			marginTop:"-10px" 
		},500,function(){ 
			$(this).css({marginTop:"0px"}).find("li:first").appendTo(this); 
		}); 
	} 
	
</script>

</head>
<body>
	<div id="nav">
<!-- 			<p class="nav_line"></p> -->
	<div class="home_left"><i class="home"></i><span class="homeNext">当前位置 /</span><span class="active">我的主页</span></div>
			<%-- <div class="home_right">
				<!-- 引入导航界面 start -->
				<jsp:include page="/pagesV2.0/common/home_right.jsp"></jsp:include>
				<!-- 引入导航界面 end -->
			</div> --%>
	</div>
	<div class="mainWrap">
		<div class="home_layOut1 home_layOut">
		    <h2>系统公告</h2>
			<div class="newTxt" id="noticeContent" style="height:31px;line-height:31px;overflow:hidden;">
				<ul>
					<li>系统公告列表</li>
				</ul>
			</div>
		</div>
		<div class="home_layOut2 home_layOut" style="margin-bottom:16px;">
			<div class="left">
				<h3 class="Bt">
					<span class="bt_left">平台运维情况</span>
					<span class="bt_right"></span>
				</h3>
				<div class="leftCon" id="aomBar" style="width:534px;height: 194px;"></div>
			</div>
			<div class="right">
				<h3 class="Bt">
					<span class="bt_left">平台纳管统计</span>
					<span class="bt_right"></span>
				</h3>
				<div class="rightCon" id="aomPie" style="width: 500px;height: 194px;"></div>
			</div>
		</div>
		<ul class="home_layOut3 home_layOut">
			<li class="home_tab1 homeTab">
				<h3 class="home_tabBt">例行维护</h3>
				<div class="home_tabBorder">
					<table class="Table" cellpadding="0" cellspacing="0" id="daiban">
						
					</table>
				</div>
			</li>
			<li class="home_tab2 homeTab">
				<h3 class="home_tabBt">运行巡检</h3>
				<div class="home_tabBorder">
					<table class="Table" cellpadding="0" cellspacing="0" id="xunjian">
						
					</table>
				</div>
			</li>
			<li class="home_tab3 homeTab">
				<h3 class="home_tabBt">软件维护</h3>
				<div class="home_tabBorder">
					<table class="Table last_Table" cellpadding="0" cellspacing="0" id="software">
	
					</table>
				</div>
			</li>
		</ul>
	</div>
	<div class="mainBg"></div>
</body>
</html>