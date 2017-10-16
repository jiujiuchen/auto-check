function checkNewMsg() {
	var url = ctx+"/system/notice_findSysNews.action";
	$.ajax({
		async : true,
		cache : false,
		url : url,
		data : {
			size : 1
		},
		type : 'post',
		dataType : "json",
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
	var url = ctx+"/system/notice_updateSysNews.action?noticePo.id="
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