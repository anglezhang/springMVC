<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/sysrole/update.do" id="form_re" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="roleId" value="${sysRole.roleId}">
		<div id="hiddenBox"></div>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>角色名：</label>
				<input type="text" name="roleName" class="required" maxlength="20" remote="${pageContext.request.contextPath}/sysrole/check.ajax?roleId=${sysRole.roleId}" size="30" value="${sysRole.roleName}"/>
			</div>
			
			<div class="unit">
				<label>审批金额权限：</label>
				<input type="radio"  value="-1" <c:if test="${ empty mappingM }">checked</c:if>  name="moneyAuth_M" />无此权限
				<c:forEach items="${moneyAuthListm }" var="moneyauth_m" varStatus="c">
					<input type="radio"  value="${moneyauth_m.id }" <c:if test="${moneyauth_m.id==mappingM.moneyAuth.id }">checked</c:if>  name="moneyAuth_M" />${moneyauth_m.quota } &nbsp; 万元
					<c:if test="${c.count%5==0}"><br/></c:if>
				</c:forEach>
			</div>
			<div class="unit">
				<label>折扣权限：</label>
				<c:forEach items="${moneyAuthListr }" var="moneyauth_r" varStatus="s">
					<input type="checkbox" <c:if test="${moneyauth_r.isChecked==1 }">checked</c:if>  value="${moneyauth_r.id }" name="moneyAuth_R" />
					<c:if test="${moneyauth_r.quota*10>0 }">${moneyauth_r.quota*10 }  &nbsp;折</c:if>
					<c:if test="${moneyauth_r.quota*10==0 }">免单</c:if>
					<c:if test="${ s.count%7==0}"><br/></c:if>
				</c:forEach>
			</div>
			<div class="unit">
				<label>拥有所有操作权限：</label>
				<input type="radio" name="isSuper" value="1" <c:if test="${sysRole.isSuper == 1}" > checked="checked"</c:if> />是<input type="radio" name="isSuper" value="0" <c:if test="${sysRole.isSuper == 0}" > checked="checked"</c:if> />否 
			</div>
			<div class="divider"></div>
			<div class="unit" id="auth_tree" <c:if test="${sysRole.isSuper == 1}" > style="display: none;" </c:if> >
				<ul id="systree_role_edit" class="ztree"></ul>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="sys.role.update">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="gosubmit()" >保存</button></div></div>
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
	var systree_role_edit;
	function disableAuths() {
		if($("input[name=isSuper]:checked").val()== 1) {
			$("#auth_tree").hide();
		} else {
			$("#auth_tree").show();
		}
	}
	$(function() {
		$("input[name=isSuper]").bind("click",function(){
			disableAuths();
		});
	});
	
	var setting = {
		check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "p", "N": "s" }
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};
	var zNodes = ${sysAuthRoot};
	$(document).ready(function() {
		systree_role_edit = $.fn.zTree.init($("#systree_role_edit"), setting, zNodes);
	});
	
	function gosubmit(){
		$("#hiddenBox").html("");
		if($("input[name=isSuper]:checked").val() == 0) {
			var nodes = systree_role_edit.getCheckedNodes(true);
	       	if(nodes.length>0){
		       	for(var i=0;i<nodes.length;i++){
		       		if(nodes[i].id > 0){
			       		appendHidden(nodes[i].id);
		       		}
		        }
	       	}else{
		       	var errorMsg = new Object();
				errorMsg.statusCode = 300;
				errorMsg.message = "请选择权限";
				dialogAjaxDone(errorMsg);
				return ;
	       	}
	       
		}
		$("#form_re").submit();
	}
	
	function appendHidden(id){
		var hiddenString = '<input type="hidden" name="authIds" value="'+id+'">';
		$("#hiddenBox").append(hiddenString);
	}
</script>