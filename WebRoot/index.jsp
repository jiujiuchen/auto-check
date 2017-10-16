<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>沃云自动化部署信息检查界面</title>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}

#head {
	height: 40px;
	margin: 0;
}

#content {
	height: 250px;
	margin: 0;
}

#left {
	padding-left: 150px;
	height: 250px;
	float: left;
	margin: 0;
}

#right {
	width: 10px;
	height: 250px;
	margin: 0;
}

#foot {
	width: 800px;
	height: 100px;
	margin: 0;
}
</style>
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
<script>
	$(function(){
		$("#dg").datagrid({
			method : 'get',
			loadMsg : '数据加载中......',
			url:"ShowDetailServlet.do",
            pageSize:10,//分页大小  
			pagination : "true",
			rownumbers : "true",
			singleSelect:"true",
            columns : [[
						{
							field : 'recordId',
							title : '记录ID',
							hidden : 'hidden'
						
						},{
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
						
						},{
							field : 'projectTel',
							title : '负责人电话',
							width : 100,
							align : 'center'
						
						},{
							field : 'projectMail',
							title : '负责人邮箱',
							width : 200,
							align : 'center'
						
						},{
							field : 'projectDate',
							title : '上传时间',
							width : 150,
							align : 'center'
						
						},{
							field : 'resPoolExcelName',
							title : '资源池信息表',
							width : 250,
							align : 'center'
						
						},{
							field : 'deployExcelName',
							title : '部署信息表',
							width : 250,
							align : 'center'
						
						},{
							field : 'uploadStatus',
							title : '校验状态',
							width : 60,
							align : 'center',
			                formatter : function(value, row, index) {  
			                    if (value==0) {  
			                        return '<span style="color:red;">异常</span>';  
			                    } else {  
			                        return "正常";  
			                    }  
			                }  
						
						},{
							field : 'excelPath',
							title : '存储路径',
							width : 200,
							align : 'center'
						
						},{
							field : 'find',
							title : '其他操作',
							width : 100,
							align : 'center',
							 formatter : function(value, row, index) {  
								 if(row.uploadStatus=="1"){
									 var str = "<a href='javascript:void(0)' onclick = 'showSuccess()' >查看</a>";
								 }else{
									 var str = "<a href='javascript:void(0)'onclick='showError(&apos;"
											+ index
											+ "&apos;)'>查看</a>";
								 }
								
								return str;
				                } 
							
						
						},{
							field : 'errorInfo',
							title : '错误信息',
							width : 300,
							align : 'center',
							hidden : 'hidden'
						}
                        ]]
		});
	});
	function showSuccess(){
		alert("已经通过检查，请联系部署人员。。。");
	}
	function showError(index){
		var rows = $('#dg').datagrid('getRows');
		var row = rows[index];
		alert(row.errorInfo);
	}
	function check(id) {
		var str=$("#"+id).val();
		if(str==""){
			$.messager.alert("系统提示","信息表不能为空，请重选择！！！");
			return false;
		}
		var file = document.getElementById(id).files[0];
		//文件名称
		var fileName = file.name;
		//文件类型
		var file_typename = fileName.substring(fileName.lastIndexOf('.'),
				fileName.length);
		if (file_typename == '.xlsx') {
			//计算文件大小
			var fileSize = 0;
			//如果文件大小大于1024字节X1024字节，则显示文件大小单位为MB，否则为KB
			if (file.size > 1024 * 1024) {
				fileSize = Math.round(file.size * 100 / (1024 * 1024)) / 100;
				if (fileSize > 10) {
					$.messager.alert("系统提示","文件超过10MB，禁止上传！");
					return false;
				}
			}else{
				return true;
			}
		} else {
			$.messager.alert("系统提示","文件名称错误，请重新选择正确的以.xlsx文件！！！");
			return false;

		}
	}
	function isEmail(strMail){
		 var myreg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		 if(myreg.test(strMail)){
			 return true;
		 }else{
			 return false;
		 }
	}
	function isTel(strTel){
		var mytel=/^[1][3,4,5,7,8][0-9]{9}$/;
		if(mytel.test(strTel)){
			return true;
		}else{
			return false;
		}
	}
	
	
	function upload() {
		alert("开始上传文件，请等待。。。。。。。");
		if(!check("filepath")||!check("filepath1")){
			return
		}
		if($("#resPoolName").val()==""||$("#resPoolName").val()==null){
			$.messager.alert("系统提示","资源池名称不能为空，请输入！！！");
			return
		}
		if($("#projectLeader").val()==""||$("#projectLeader").val()==null){
			$.messager.alert("系统提示","项目负责人不能为空，请输入！！！");
			return
		}
		if($("#projectTel").val()==""||$("#projectTel").val()==null){
			$.messager.alert("系统提示","手机号不能为空，请输入！！！");
			return
		}else{
			if(!isTel($("#projectTel").val())){
				$.messager.alert("系统提示","您输入的电话号码有误，请重新检查输入！！！");
				return
			}
		}
		if($("#projectMail").val()==""){
			$.messager.alert("系统提示","邮箱不能为空，请输入！！！");
			return
		}else{
			if(!isEmail($("#projectMail").val())){
				$.messager.alert("系统提示","您输入的邮箱有误，请重新检查输入！！！");
				return
			}
		}
		//获取form数据
		var formData = new FormData($("#ff")[0]);
		$.ajax({
			url : "CheckEnvironmentServlet.do",
			type : 'POST',
			data : formData,
			encType : "multipart/form-data",
			async : true,
			cache : false,
			contentType : false,
			processData : false,
			success : function(result) {
				$("#dg").datagrid('reload');
				result = eval("(" + result + ")");
				if (result.errorMsg) {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				} else {
					$.messager.alert("系统提示", "信息校验上传成功,请及时联系部署人员。。。。");
					resetValue();
				}
				;
			}
		});
	}
	function resetValue() {
		$("#filepath").val("");
		$("#filepath1").val("");
		$("#resPoolName").val("");
		$("#projectLeader").val("");
		$("#projectTel").val("");
		$("#projectMail").val("");
	}
</script>


</head>

<body background="">

	<div id="head" align="center">
		<h1>沃云自动化部署信息检查界面</h1>
	</div>
	<div id="content" title="导入资源池信息表">
		<div id="left">
			<form id="ff">
				<table id="grid" style="width: 600px;" border="1">
					<tr>
						<th><label>资源池信息EXCEL表:</label></th>
						<th><input type="file" name="filepath" id="filepath"
							data-options="prompt:'选择文件',buttonText:'&nbsp;选&nbsp;择&nbsp;',required:true"
							style="width:300px"></input></th>
					</tr>
					<tr>
						<th><label>部署环境信息EXCEL表:</label></th>
						<th><input type="file" name="filepath1" id="filepath1"
							data-options="prompt:'选择文件',buttonText:'&nbsp;选&nbsp;择&nbsp;',required:true"
							style="width:300px"></input></th>
					</tr>
					<tr>
						<th><label>资源池名称:</label></th>
						<th><input type="text" name="resPoolName" id="resPoolName"
							style="width: 150px;" required="required" /></th>
					</tr>
					<tr>
						<th><label>资源池类型:</label></th>
						<th><input type="radio" name="resPoolType" value="pub">公有云</input>
							<input type="radio" name="resPoolType" value="pri"
							checked="checked">私有云</input></th>
					</tr>
					<tr>
						<th><label>项目负责人:</label></th>
						<th><input type="text" name="projectLeader"
							id="projectLeader" style="width: 150px;" required="required" /></th>
					</tr>
					<tr>
						<th><label>负责人电话:</label></th>
						<th><input type="text" name="projectTel" id="projectTel"
							style="width: 150px;" required="required" /></th>
					</tr>
					<tr>
						<th><label>负责人邮箱:</label></th>
						<th><input type="text" name="projectMail" id="projectMail"
							style="width: 150px;" required="required" /></th>
					</tr>
					<tr>
						<th colspan="2"><a href="javascript:upload()"
							class="easyui-linkbutton" style="width:100;">上传</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
							href="javascript:resetValue()" class="easyui-linkbutton"
							style="width:100;">重置</a></th>
					</tr>

				</table>
			</form>
		</div>

		<div class="right" align="center">
			<table>
				<tr>
					<td>
						<div class="easyui-panel" style="margin-left: 30px ;border: 0">
							<h2 style="color: red;">自动化部署信息表填写培训手册v1.0</h2>
							</br> <a href="templates/自动化部署信息表填写培训v1.0.pptx"
								class="easyui-linkbutton">下载</a></br>
							<h2 style="color: red;">部署信息模板列表：</h2>
							<span> <label style="margin-left: 30px;">表1：</label> <span>沃云软件部署环境(模板).xlsx</span>
							</span><br> <span><label style="margin-left: 30px;">表2：</label>
								<span>自动化部署导入信息(模板).xlsx</span> </span> <br> <br> <span>
								<a href="templates/templates.zip" class="easyui-linkbutton">一键下载</a>
							</span>
						</div>
					</td>
					<td>
						<div>
							<img alt="技术支持群" src="images/woyun.jpg">
						</div>
					</td>
				</tr>

			</table>


		</div>
	</div>
	<div id="list">
		<table id="dg" class="easyui-datagrid"
			style="background-color: #6D8EAD"></table>
	</div>
</body>
</html>