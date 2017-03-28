<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/sysauth/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="authId" value="${sysAuth.authId}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>权限名称：</label>
				<input type="text" name="authName" class="required" maxlength="20" size="30" value="${sysAuth.authName}"/>
			</div>
			<div class="unit">
				<label>权限编码：</label>
				<input type="text" name="authCode" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/sysauth/check.ajax?authId=${sysAuth.authId}" value="${sysAuth.authCode}" />
			</div>
			<div class="unit">
				<label>权限URL：</label>
				<input type="text" name="authUrl" maxlength="100" size="30" value="${sysAuth.authUrl}"/>
			</div>
			<div class="unit">
				<label>排序顺序：</label>
				<input type="text" name="priority" class="required digits" maxlength="4" size="30" value="${sysAuth.priority}"/>
			</div>
			<div class="unit">
				<label>权限类型：</label>
				<input type="radio" name="authType" value="1" <c:if test="${sysAuth.authType == 1}" > checked="checked"</c:if> />菜单级<input type="radio" name="authType" value="2" <c:if test="${sysAuth.authType == 2}" > checked="checked"</c:if> />功能级
			</div>
			<div class="unit">
				<label>权限描述：</label>
				<textarea name="authDesc" cols="30" rows="3">${sysAuth.authDesc}</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="sys.auth.update">
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