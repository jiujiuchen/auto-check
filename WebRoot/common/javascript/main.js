var info = "( * 项为必填项!)";
var delInfo = "您确定要删除吗?";

var add_success = "添加成功!"; // 添加成功提示
var add_fail = "添加失败!"; // 添加失败提示

var update_success = "修改成功"; // 修改成功提示
var update_fail = "修改失败"; // 修改失败提示

var delete_success = "删除成功!"; // 删除成功提示
var delete_fail = "删除失败!"; // 删除失败提示

var install_success = "安装成功!"; // 软件安装成功提示
var install_fail = "安装失败!"; // 软件安装失败提示

var exception_info = "系统出错!"; // 异常/出错提示信息

var root_classifyId = "0"; // 执行分类根目录;
var software_classifyId = "1000"; // 软件维护分类主键编号
var maintenance_classifyId = "2000"; // 例行巡检分类主键编号
var check_classifyId = "3000"; // 运行巡检分类主键编号

var search_button_title = "搜索"; // 搜索按钮title提示语;
var add_button_title = "添加"; // 添加按钮title提示语;
var refresh_button_title = "刷新"; // 刷新按钮title提示语;
var update_button_title = "修改"; // 修改按钮title提示语;
var delete_button_title = "删除"; // 删除按钮title提示语;
var start_button_title = "运行"; // 运行/启动按钮title提示语;
var stop_button_title = "停止"; // 停止按钮title提示语;
var running_button_title = "运行中..."; // 运行中提示语;
var close_button_title = "关闭"; // 关闭按钮title 提示语;

var auto_load_info = "系统数据正在努力加载中,请稍候......";
var auto_save_info = "正在保存数据,请稍候......";
var auto_xf_info = "正在分发数据,请稍候......";
var auto_run_info = "正在运行,请稍候......";

// 格式化日期
function formatDate(ns) {
	if (ns != null && ns != '') {
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
	}else{
		return "";
	}
}

// 格式化时间;
function formatTime(ns) {
	if (ns != null && ns != '') {
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
		var hour = d.getHours();
		if (hour <= 9) {
			hour = "0" + hour;
		}
		var minute = d.getMinutes();
		if (minute <= 9) {
			minute = "0" + minute;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute;
	} else {
		return "";
	}
}
function jqGridReload(tableId, params) {
	var jTable = $("#" + tableId);
	var postData = jTable.jqGrid("getGridParam", "postData");
	params.reloadFlag = "true";
	$.extend(postData, params);
	jTable.jqGrid("setGridParam", {
		datatype : "json",
		search : true,
		mtype : "post"
	}).trigger("reloadGrid");

	params.reloadFlag = "false";
	$.extend(postData, params);
	$("#gridTable").jqGrid("setGridParam", {
		datatype : "json",
		search : true,
		mtype : "post"
	});

}

/**
 * 正则检测
 * 
 * @param value
 *            字符串值
 * @param regx
 *            正则表达式;
 * @returns {Boolean}
 */
function regxCheck(value, regx) {
	if (regx.test(value)) {
		return true;
	} else {
		return false;
	}
}
