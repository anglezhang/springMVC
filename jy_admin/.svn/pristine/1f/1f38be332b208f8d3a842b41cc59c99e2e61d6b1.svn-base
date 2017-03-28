<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/depotposition/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${depotposition.id}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>仓位名称：</label>
				<input type="text" name="name" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/depotposition/check.ajax?groupId=${depotposition.id}" value="${depotposition.name}" />
			</div>
			<div class="unit">
				<label>描述：</label>
				<input type="text" name="content" class=""   maxlength="30" size="30" value="${depotposition.content}"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="depotposition.update">
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