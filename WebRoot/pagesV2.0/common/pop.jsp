<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" >
function checkNewMsg(){
	var url = "<%=path%>/system/notice_findSysNews.action";
	$.ajax({
		async : true,
		cache : false,
		url : url,
		data : {size:1},
		type : 'post',
		dataType : "json",
		error : function() {
			showTip(exception_info);//系统出错
		},
		success : function(datas) {
			if(datas.length>0){
				var pop=new Pop(datas[0].newsTitle,datas[0].newsHerf,datas[0].newsContents);
				//更新消息状态;
				updateNewMsg(datas[0].id);
			}
		}
	});
}

function updateNewMsg(newsId){
	var url = "<%=path%>/system/notice_updateSysNews.action?noticePo.id="+newsId;
	$.ajax({
		async : false,
		cache : false,
		url : url,
		type : 'post',
		dataType : "json",
		error : function() {
			showTip(exception_info);//系统出错
		}
	});
}


window.onload=function(){
	window.setInterval("checkNewMsg()", 10000);
}
</script>

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
