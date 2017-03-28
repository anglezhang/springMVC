<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/depotposition/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" name="upId" value="${depotposition.id == null ? 0 : depotposition.id}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="name" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/depotposition/check.ajax?upId=${depotposition.id}" />
			</div>
			<div class="unit">
				<label>描述：</label>
				<input type="text" name="content" class="" maxlength="30" size="30" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="depotposition.save">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
					</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	function dialogAjaxDone2(json){
	$("#backId_position").val(json.backId);
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		if (json.navTabId){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
	}
}

</script>

