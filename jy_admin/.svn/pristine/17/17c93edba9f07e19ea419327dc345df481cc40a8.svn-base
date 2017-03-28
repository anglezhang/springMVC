<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/infosub/save.do?dialogId=${dialogId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="name" class="required" maxlength="20" size="50"/>
			</div>
			<div class="unit">
				<label>编码：</label>
				<input type="text" name="code" class="required" maxlength="25" size="30" remote="${pageContext.request.contextPath}/infosub/check.ajax" />
			</div>
			<div class="unit">
				<label>简称：</label>
				<input type="text" name="shortName" maxlength="40" size="30"/>
			</div>
			<div class="unit">
				<label>法人代表：</label>
				<input type="text" name="legalPerson" class="required" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>邮政编码：</label>
				<input type="text" name="zipCode" class="digits" maxlength="9" size="30"  />
			</div>
			<div class="unit">
				<label>联系电话：</label>
				<input type="text" name="phone" class="required phone" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>联系地址：</label>
				<input type="text" name="address" class="required" maxlength="50" size="30"  />
			</div>
			<div class="unit">
				<label>传真号码：</label>
				<input type="text" name="faxNum" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>联系人：</label>
				<input type="text" name="linkMan" class="required" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>开户银行：</label>
				<input type="text" name="bank" class="" maxlength="30" size="30"  />
			</div>
			<div class="unit">
				<label>银行账号：</label>
				<input type="text" name="bankNo" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>税号：</label>
				<input type="text" name="taxNo" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>财务编码：</label>
				<input type="text" name="financeNo" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>注册资本：</label>
				<input type="text" name="regCapital" class="required number" maxlength="15" size="30"  />&nbsp; 万元
			</div>
			<div class="unit">
				<label>评级：</label>
				<input type="text" name="grade" class="required" maxlength="10" size="30"  />
			</div>
			<div class="unit">
				<label>经营范围：</label>
				<textarea name="businessScope" cols="60" rows="8" maxlength="200"></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="infosub.save">
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
		if (json.navTabId){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
			$("#infosub_s_b").click();
		}
	}
}
</script>
