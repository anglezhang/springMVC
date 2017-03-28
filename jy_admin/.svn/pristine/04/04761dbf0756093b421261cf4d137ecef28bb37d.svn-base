<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/sysauth/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" name="parentAuthId" value="${parentSysAuth.authId == null ? 0 : parentSysAuth.authId}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>权限名称：</label>
				<input type="text" name="authName" class="required" maxlength="20" size="30"/>
			</div>
			<div class="unit">
				<label>权限编码：</label>
				<input type="text" name="authCode" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/sysauth/check.ajax" />
			</div>
			<div class="unit">
				<label>权限URL：</label>
				<input type="text" name="authUrl" maxlength="100" size="30"/>
			</div>
			<div class="unit">
				<label>排序顺序：</label>
				<input type="text" name="priority" class="required digits" maxlength="4" size="30" value="10"/>
			</div>
			<div class="unit">
				<label>权限类型：</label>
				<input type="radio" name="authType" value="1" checked="checked"/>菜单级<input type="radio" name="authType" value="2" />功能级
			</div>
			<div class="unit">
				<label>权限描述：</label>
				<textarea name="authDesc" cols="30" rows="3"></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="sys.auth.save">
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
	$("#backId").val(json.backId);
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

