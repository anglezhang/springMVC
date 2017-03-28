<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/goodsType/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" name="upId" value="${goodsType.groupId == null ? 0 : goodsType.groupId}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="typeName" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/goodsType/check.ajax?upId=${goodsType.groupId}" />
			</div>
			<div class="unit">
				<label>排序顺序：</label>
				<input type="text" name="sort" class="required digits" maxlength="4" size="30" value="10"/>&nbsp;&nbsp;&nbsp;   数字越小越靠前
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="goodstype.save">
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
	$("#backId_goodstype").val(json.backId);
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

