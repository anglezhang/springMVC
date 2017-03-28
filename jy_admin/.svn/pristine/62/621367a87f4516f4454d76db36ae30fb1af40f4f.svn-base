<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" id="form_role" action="${pageContext.request.contextPath}/sysuser/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="userId" value="${sysUser.userId}">
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>用户名：</label>
				<span class="info">${sysUser.username}</span>
			</div>
			<div class="unit">
				<label>密码：</label>
				<input id="w_validation_pwd" type="password" name="password" minlength="6" maxlength="20" size="30" />
				<span class="inputInfo">不修改请留空</span>
			</div>
			<div class="unit">
				<label>确认密码：</label>
				<input type="password" name="repassword" equalto="#w_validation_pwd" size="30" />
				<span class="inputInfo">不修改请留空</span>
			</div>
			<div class="unit">
				<label>真实姓名：</label>
				<a id="emplyeerlookup" title="员工选择" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/infoemp/lookup.do?islook=1" lookupGroup="emplyeerlookup" style="margin-right:210px;float:right" rel="emplyeerlookup" lookupPk="emplyeerlookup"></a>
				<input id="empName" name="realname" onclick="openLookUp()" type="text" value="${sysUser.infoEmp.empName }"  maxlength="30" class="required" readonly="readonly" size="30" />
				<input id="emptId" name="infoEmp.empId" value="${sysUser.infoEmp.empId }" type="hidden" >
			</div>
			<div class="unit">
				<label>邮箱：</label>
				<input id="email" type="text" readonly="readonly" value="${sysUser.infoEmp.email}" id="email" class="email" maxlength="100" size="30" value=""/>
			</div>
			<div class="unit">
				<label>部门：</label>
				<input type="text" id="deptName" readonly="readonly" maxlength="20" size="30" value="${sysUser.infoEmp.infoDept.name}"/>
			</div>
			<div class="unit">
				<label>职位：</label>
				<input type="text" id="postName" readonly="readonly" maxlength="20" size="30" value="${sysUser.infoEmp.position}"/>
			</div>
			<div class="unit">
				<label>禁用：</label>
				<input type="radio" name="isDisabled" value="0" <c:if test="${sysUser.isDisabled == 0}" > checked="checked"</c:if> />否 <input type="radio" name="isDisabled" value="1" <c:if test="${sysUser.isDisabled == 1}" > checked="checked"</c:if> />是
			</div>
			<div class="unit">
				<label>角色：</label>
				<c:forEach items="${sysRoleList}" var="sysrole">
					<input type="checkbox" name="roleIds" value="${sysrole.roleId}" <c:forEach items="${sysUser.sysRoles}" var="role"><c:if test="${role.roleId == sysrole.roleId}">checked="checked"</c:if></c:forEach> >${sysrole.roleName}
				</c:forEach>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="sys.user.update">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="gosubmit()">保存</button></div></div>
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
	function gosubmit(){
		if($("input[name='roleIds']:checked").attr("checked")=="checked"){
			$("#form_role").submit();
		}else{
			var errorMsg = new Object();
			errorMsg.statusCode = 300;
			errorMsg.message = "请选择角色";
			dialogAjaxDone(errorMsg);
		}
	}

	function setLookVal (infoEmp)
	{
		for(k in infoEmp)
		{
			$("#" + k).val(infoEmp[k]);
		}
	}
	function openLookUp(){
	$("#emplyeerlookup").click();
}
</script>