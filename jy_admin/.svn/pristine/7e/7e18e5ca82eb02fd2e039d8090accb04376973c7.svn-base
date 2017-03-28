<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/goodsType/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="groupId" value="${goodsType.groupId}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>权限编码：</label>
				<input type="text" name="typeName" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/goodsType/check.ajax?groupId=${goodsType.groupId}" value="${goodsType.typeName}" />
			</div>
			<div class="unit">
				<label>排序顺序：</label>
				<input type="text" name="sort" class="required digits"   maxlength="4" size="30" value="${goodsType.sort}"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="goodstype.update">
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