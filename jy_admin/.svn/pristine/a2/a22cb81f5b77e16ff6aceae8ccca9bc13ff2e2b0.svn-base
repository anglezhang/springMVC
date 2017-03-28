<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/sysuser/editpassword.do">
</form>
<div class="pageContent">
	<form method="post" id="form_role" action="${pageContext.request.contextPath}/sysuser/updatepassword.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" name="userId" value="${sysUser.userId}">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>原始密码：</label>
				<input id="o_validation_pwd" type="password" name="oldpassword" class="required"  maxlength="20" size="30" />
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input id="w_validation_pwd" type="password" name="password" class="required alphanumeric" minlength="6" maxlength="20" size="30" />
			</div>
			<div class="unit">
				<label>确认密码：</label>
				<input type="password" name="repassword" equalto="#w_validation_pwd" class="required" size="30" />
			</div>
			<div class="unit">
				<div class="buttonActive"><div class="buttonContent"><button type="submit">确认</button></div></div>
			</div>
			
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
	}
}
</script>