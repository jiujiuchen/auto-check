/**
 * jquery ajax调用封装
 */
var ajaxResult;// 全局变量，保存success响应结果

/* jBox 全局设置 */
var _jBoxConfig = {};
_jBoxConfig.defaults = {
	id : null, /* 在页面中的唯一ID，如果为null则自动为随机ID,一个ID只会显示一个jBox */
	top : '15%', /* 窗口离顶部的距离,可以是百分比或像素(如 '100px') */
	border : 2, /* 窗口的外边框像素大小,必须是0以上的整数 */
	opacity : 0.2, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
	timeout : 0, /* 窗口显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
	showType : 'fade', /* 窗口显示的类型,可选值有:show、fade、slide */
	showSpeed : 'fast', /* 窗口显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
	showIcon : true, /* 是否显示窗口标题的图标，true显示，false不显示，或自定义的CSS样式类名（以为图标为背景） */
	showClose : true, /* 是否显示窗口右上角的关闭按钮 */
	draggable : true, /* 是否可以拖动窗口 */
	dragLimit : true, /* 在可以拖动窗口的情况下，是否限制在可视范围 */
	dragClone : false, /* 在可以拖动窗口的情况下，鼠标按下时窗口是否克隆窗口 */
	persistent : false, /* 在显示隔离层的情况下，点击隔离层时，是否坚持窗口不关闭 */
	showScrolling : false, /* 是否显示浏览的滚动条 */
	ajaxData : {}, /* 在窗口内容使用post:前缀标识的情况下，ajax post的数据，例如：{ id: 1 } 或 "id=1" */
	iframeScrolling : 'auto', /* 在窗口内容使用iframe:前缀标识的情况下，iframe的scrolling属性值，可选值有：'auto'、'yes'、'no' */

	title : 'jBox', /* 窗口的标题 */
	width : 350, /* 窗口的宽度，值为'auto'或表示像素的整数 */
	height : 'auto', /* 窗口的高度，值为'auto'或表示像素的整数 */
	bottomText : '', /* 窗口的按钮左边的内容，当没有按钮时此设置无效 */
	buttons : {
		'确定' : 'ok'
	}, /* 窗口的按钮 */
	buttonsFocus : 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
	loaded : function(h) {
	}, /* 窗口加载完成后执行的函数，需要注意的是，如果是ajax或iframe也是要等加载完http请求才算窗口加载完成，参数h表示窗口内容的jQuery对象 */
	submit : function(v, h, f) {
		return true;
	}, /* 点击窗口按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
	closed : function() {
	} /* 窗口关闭后执行的函数 */
};
_jBoxConfig.stateDefaults = {
	content : '', /* 状态的内容，不支持前缀标识 */
	buttons : {
		'确定' : 'ok'
	}, /* 状态的按钮 */
	buttonsFocus : 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
	submit : function(v, h, f) {
		return true;
	} /* 点击状态按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
};
_jBoxConfig.tipDefaults = {
	content : '', /* 提示的内容，不支持前缀标识 */
	icon : 'info', /* 提示的图标，可选值有'info'、'success'、'warning'、'error' */
	top : '40%', /* 提示离顶部的距离,可以是百分比或像素(如 '100px') */
	width : 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
	height : 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
	opacity : 0, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
	timeout : 2000, /* 提示显示多少毫秒后自动关闭,必须是大于0的整数 */
	border : 2,
	showScrolling : false,
	closed : function() {
	} /* 提示关闭后执行的函数 */
};
_jBoxConfig.messagerDefaults = {
	content : '', /* 信息的内容，不支持前缀标识 */
	title : 'jBox', /* 信息的标题 */
	icon : 'none', /* 信息图标，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error' */
	width : 350, /* 信息的高度，值为'auto'或表示像素的整数 */
	height : 'auto', /* 信息的高度，值为'auto'或表示像素的整数 */
	timeout : 3000, /* 信息显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
	showType : 'slide', /* 信息显示的类型,可选值有:show、fade、slide */
	showSpeed : 600, /* 信息显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
	border : 2, /* 信息的外边框像素大小,必须是0以上的整数 */
	buttons : {}, /* 信息的按钮 */
	buttonsFocus : 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
	loaded : function(h) {
	}, /* 窗口加载完成后执行的函数，参数h表示窗口内容的jQuery对象 */
	submit : function(v, h, f) {
		return true;
	}, /* 点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
	closed : function() {
	} /* 信息关闭后执行的函数 */
};
_jBoxConfig.languageDefaults = {
	close : '关闭', /* 窗口右上角关闭按钮提示 */
	ok : '确定', /* $.jBox.prompt() 系列方法的“确定”按钮文字 */
	yes : '是', /* $.jBox.warning() 方法的“是”按钮文字 */
	no : '否', /* $.jBox.warning() 方法的“否”按钮文字 */
	cancel : '取消' /* $.jBox.confirm() 和 $.jBox.warning() 方法的“取消”按钮文字 */
};

$.jBox.setDefaults(_jBoxConfig);

/**
 * ajax请求
 * 
 * @param url
 * @param data
 * @param successCallback
 * @param errorCallback
 */
function ajaxCall(url, data, successCallback) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : ctx + url,
		data : data,
		error : function() {
			alert("请求失败");
		},
		success : function(data) {
			ajaxResult = data;
			successCallback();
		}
	});
}

/**
 * ajax表单提交:需要引入jquery.form.js文件才可以使用
 */
function AjaxSubmitForm(form_id, url, callback) {
	$("#" + form_id).ajaxSubmit({
		url : url,
		dataType : 'json',
		success : function(data) {
			ajaxResult = data;
			callback();
		}
	});
}
/**
 * 将后台的json对象直接转为属性
 * 
 * @param divId
 * @param jsonObj
 */
function jsonToDiv(divId, jsonObj) {
	$.each(jsonObj, function(key, value) {
		$("#" + divId + "_" + key).html(value);
	});
}

/**
 * 弹层提示
 * 分三种调用方式：showTip("load");showTip("任意内容");showTip("任意内容",function(){//这里是点击确定后执行的方法});
 * 
 * @param tipContent
 *            弹层提示的内容
 * @param confirm_callback
 *            confirm框点击确定的回调方法
 */
function showTip(tipContent, confirm_callback) {

	if (tipContent == "load") {
		jBox.tip(auto_load_info, 'loading', {
			opacity : 0,
			showType : 'normal'
		});
	} else if (tipContent != "load" && confirm_callback) {
		var submit = function(v, h, f) {
			if (v == true) {
				confirm_callback();
			}
			if (v == false) {
				$.jBox.close();
			}
			return true;
		};
		jBox.confirm(tipContent, "系统提示", submit, {
			id : 'confirm',
			top : $(window).height() / 2 - 100,
			showType : 'slide',
			showScrolling : false,
			buttons : {
				'确定' : true,
				'取消' : false
			}
		});
	} else if (tipContent && !confirm_callback) {
		jBox.alert(tipContent, "系统提示", {
			opacity : 0.01,
			top : $(window).height() / 2 - 100,
			width : 250,
			showType : 'fade',
			icon : 'info'
		});
	}
}

/**
 * 弹出confirm窗口，请使用异步提交方式,并带有等待加载提示框;
 * 
 * @param tipContent
 *            提示内容;
 * @param confirm_callback
 *            回调函数;
 */
function showConfirm(tipContent, confirm_callback) {

	if (tipContent != null && tipContent != "" && confirm_callback) {
		var submit = function(v, h, f) {
			if (v == true) {
				jBox.tip(auto_load_info, 'loading', {
					opacity : 0,
					showType : 'normal'
				});
				confirm_callback();
			}
			if (v == false) {
				$.jBox.close();
			}
			return true;
		};
		jBox.confirm(tipContent, "系统提示", submit, {
			id : 'confirm',
			top : $(window).height() / 2 - 100,
			showType : 'slide',
			showScrolling : false,
			submit : submit,
			buttons : {
				'确定' : true,
				'取消' : false
			}
		});
	}
}

/**
 * 弹出confirm窗口，请使用异步提交方式,并带有等待加载提示框;
 * 
 * @param tipContent
 *            提示内容;
 * @param confirm_callback
 *            回调函数;
 */
function showConfirm1(tipContent, confirm_callback) {

	if (tipContent != null && tipContent != "" && confirm_callback) {
		var submit = function(v, h, f) {
			if (v == true) {
				jBox.tip(auto_load_info, 'loading', {
					opacity : 0,
					showType : 'normal'
				});
				confirm_callback();
			}
			if (v == false) {
				$.jBox.close();
			}
			return true;
		};
		jBox.confirm(tipContent, "系统提示", submit, {
			id : 'confirm',
			top : $(window).height() / 2 - 100,
			showType : 'fade',
			width : 250,
			icon : 'info',
			showScrolling : false,
			submit : submit,
			buttons : {
				'确定' : true
			}
		});
	}
}

/**
 * 关闭提示层
 */
function closeTip() {
	$.jBox.close(false, 'tip');
}

/**
 * 关闭alert效果窗口方法
 */
function closeAlertBox() {
	$("#windown-box3").slideUp("slow");
}

/**
 * js前端抓取异常后的提示框
 * 
 * @param tipContent
 */
function showError(tipContent) {
	jBox.error(tipContent, "系统提示", {
		opacity : 0.01,
		top : $(window).height() / 2 - 100,
		width : 250,
		showType : 'fade'
	});
}
