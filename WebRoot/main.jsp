<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="jquery-easyui-1.5/jquery.min.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="jquery-easyui-1.5/jquery.easyui.min.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5/themes/icon.css"></link>

<script language="JavaScript" type="text/javascript"
	src="jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<title>欢迎来到主界面</title>

<script type="text/javascript">
	$(function() {
		$("#ck")
				.datagrid(
						{
							method : 'get',
							loadMsg : '数据加载中......',
							url : "ShowDetailServlet.do",
							pageSize : 20,//分页大小  
							pagination : "true",
							rownumbers : "true",
							singleSelect : "true",
							columns : [ [
									{
										field : 'recordId',
										title : '记录ID',
										hidden : 'hidden'

									},
									{
										field : 'resPoolName',
										title : '资源池名称',
										width : 80,
										align : 'center'
									},{
										field : 'resPoolType',
										title : '资源池类型',
										width : 80,
										align : 'center',
										formatter : function(value, row, index){
											if(value=="pri"){
												return "私有云";
											}else{
												return "公有云";
												
											}
										}
									},{
										field : 'projectLeader',
										title : '项目负责人',
										width : 80,
										align : 'center'

									},
									{
										field : 'projectTel',
										title : '负责人电话',
										width : 100,
										align : 'center'

									},
									{
										field : 'projectMail',
										title : '负责人邮箱',
										width : 200,
										align : 'center'

									},
									{
										field : 'projectDate',
										title : '上传时间',
										width : 150,
										align : 'center'

									},
									{
										field : 'resPoolExcelName',
										title : '资源池信息表',
										width : 250,
										align : 'center'

									},
									{
										field : 'deployExcelName',
										title : '部署信息表',
										width : 250,
										align : 'center'

									},
									{
										field : 'uploadStatus',
										title : '校验状态',
										width : 60,
										align : 'center',
										formatter : function(value, row, index) {
											if (value == 0) {
												return '<span style="color:red;">异常</span>';
											} else {
												return "正常";
											}
										}

									},
									{
										field : 'excelPath',
										title : '存储路径',
										width : 200,
										align : 'center'

									},
									{
										field : 'find',
										title : '其他操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											if (row.uploadStatus == "1") {
												var str = "<a href='tmpExcel/"+row.resPoolExcelName+"'>下载1</a>";
												var str1 = "<a href='tmpExcel/"+row.deployExcelName+"'>下载2</a>";
												str = str + "  " + str1;
											} else {
												var str = "<a href='javascript:void(0)'onclick='showError(&apos;"
														+ index
														+ "&apos;)'>查看</a>";
											}
											return str;
										}

									}, {
										field : 'errorInfo',
										title : '错误信息',
										width : 300,
										align : 'center',
										hidden : 'hidden'
									} ] ]
						});
	});
	function showError(index) {
		var rows = $('#ck').datagrid('getRows');
		var row = rows[index];
		alert(row.errorInfo);
	}
</script>
</head>
<body>
	<div id="head" align="center">
		<h1>沃云自动化部署信息检查界面</h1>
	</div>
	<div>
		<a href=""></a>
		<table id="ck" class="easyui-datagrid"
			style="background-color: #6D8EAD"></table>
	</div>
</body>
</html>