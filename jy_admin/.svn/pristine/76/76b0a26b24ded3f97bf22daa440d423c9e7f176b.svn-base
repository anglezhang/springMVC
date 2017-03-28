<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/allocationapply/savefeed.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
			<input type="hidden" name="ids" value="${ids}">
		<div class="pageFormContent" layoutH="56">
			<textarea name="feedback" cols="80" rows="13" class="required" maxlength="140"></textarea>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="allocationapply.savefeed">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">确认</button></div></div>
					</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script>
function dialogAjaxDone2(json){
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
			$("#dept_ref").click();
		}
	}
}
</script>