<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/service/servicetypesave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" name="dialogId" maxlength="30" value="${dialogId}" />
		<div class="pageFormContent" layoutH="66">
			<div class="unit">
				<label>服务类型名称：</label>
				<input id="servicename" type="text" name="servicename" maxlength="200" class="required"  size="30" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="service.itemtype.save">
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
<script>
	function dialogAjaxDone2(json){
		DWZ.ajaxDone(json);
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
			/*if (json.navTabId){
				navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
			} else {
				var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
				var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {};
				navTabPageBreak(args, json.rel);
			 	//$.pdialog.reload(json.forwardUrl, {dialogId: json.dialogId});
			}
			if ("closeCurrent" == json.callbackType) {
				$.pdialog.closeCurrent();
				//navTab.open("main");
			}*/
			$.pdialog.closeCurrent();
			$("#servicelookupsubmit").trigger('click');
		}
	}
</script>