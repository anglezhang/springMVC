<%@ page language="java" pageEncoding="UTF-8"%>
<div id="leftside">
	<div id="sidebar_s">
		<div class="collapse">
			<div class="toggleCollapse"><div></div></div>
		</div>
	</div>
	<div id="sidebar">
		<div class="toggleCollapse"><h2>主菜单&nbsp;[<a href="javascript:reAsyncChildNodes();">刷新</a>]</h2><div>收缩</div></div>
		<div class="accordion" fillSpace="sidebar">
			<div class="accordionHeader">
			</div>
			<div class="accordionContent">
				<ul id="usertree" class="ztree"></ul>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
<!--
	var usertree;
	var setting = {
		async: {
			enable: true,
			url:"${pageContext.request.contextPath}/usertree.do",
			autoParam:["id"]
		},
		drag: {
			isCopy: false,
			isMove: false
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess
			
		}
	};
	
	$(document).ready(function(){
		usertree = $.fn.zTree.init($("#usertree"), setting);
	});
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode,json) {
		var jsons=eval("("+json+")");
		if (jsons.statusCode ==  DWZ.statusCode.timeout){
			if(alertMsg) alertMsg.error(json[DWZ.keys.message] || DWZ.msg("sessionTimout"), {okCall:DWZ.loadLogin});
			else DWZ.loadLogin();
		}else{
			initUI($('#'+treeId));//可直接调用initUI()；但考虑性能则直接找到tree的DOM对象
		}
	}
	
	function reAsyncChildNodes(){
		usertree.reAsyncChildNodes(null, "refresh");
	}
//-->
</script>