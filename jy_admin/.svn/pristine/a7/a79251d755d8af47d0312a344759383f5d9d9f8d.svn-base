<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" id="form_role" action="${pageContext.request.contextPath}/paramconfig/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>类型：</label>
				<select name="paramType" id="paramType" onchange="goCheck()">
					<option value="1" selected="selected">配件单位</option>
					<option value="2">职位名称</option>
					<option value="3">职业技能</option>
					<option value="4">付款方式</option>
					<option value="5">驾照类型</option>
					<option value="6">排量</option>
				</select>
				
			</div>
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="name" id="name"   maxlength="100" size="30" class="required" remote="${pageContext.request.contextPath}/paramconfig/check.do?paramType=1"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="config.save">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit" >保存</button></div></div>
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
function goCheck(){
	var paramType=$("#paramType").val();
	$("#name").attr("remote","/paramconfig/check.do?paramType="+paramType);
	$("#name").focus();
	$("#name").val("");
}
</script>