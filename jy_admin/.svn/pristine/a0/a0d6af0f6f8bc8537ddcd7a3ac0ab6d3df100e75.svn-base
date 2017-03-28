<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" id="form_r" action="${pageContext.request.contextPath}/sysrole/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div id="hiddenBox"></div>
		<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>角色名：</label>
				<input type="text" name="roleName" class="required" maxlength="20" remote="${pageContext.request.contextPath}/sysrole/check.ajax" size="30"/>
			</div>
			<div class="unit">
				<label>审批金额权限：</label>
				<c:forEach items="${moneyAuthListm }" var="moneyauth_m" varStatus="c">
					<input type="radio"  value="${moneyauth_m.id }" name="moneyAuth_M" />${moneyauth_m.quota }&nbsp;万元
					<c:if test="${c.count%5==0}"><br/></c:if>
				</c:forEach>
			</div>
			<div class="unit">
				<label>折扣权限：</label>
				<c:forEach items="${moneyAuthListr }" var="moneyauth_r" varStatus="s">
					<input type="checkbox"  value="${moneyauth_r.id }" name="moneyAuth_R" />
					<c:if test="${moneyauth_r.quota*10>0 }">${moneyauth_r.quota*10 }  &nbsp;折</c:if>
					<c:if test="${moneyauth_r.quota*10==0 }">免单</c:if>
					<c:if test="${ s.count%7==0}"><br/></c:if>
				</c:forEach>
			</div>
			
			<div class="unit">
				<label>拥有所有操作权限：</label>
				<input type="radio" name="isSuper" value="1" />是<input type="radio" name="isSuper" value="0" checked="checked" />否 
			</div>
			<div class="divider">divider</div>
			<div class="unit" id="auth_tree">
				<ul id="systree_role_add" class="ztree"></ul>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="sys.role.save">
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
<script type="text/javascript">
	var systree_role_add;
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
	/* if(zNodes.length>0){
	for(var i=0;i<zNodes.length;i++){
		if(zNodes[i].name=="所有权限"){
		zNodes[i].nocheck=true;
		}
		}
	} */
	$(document).ready(function() {
		systree_role_add = $.fn.zTree.init($("#systree_role_add"), setting, zNodes);
	});
	
	function gosubmit(){
		$("#hiddenBox").html("");
		if($("input[name=isSuper]:checked").val() == 0) {
			var nodes = systree_role_add.getCheckedNodes(true);
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
		$("#form_r").submit();
	}
	function appendHidden(id){
		var hiddenString = '<input type="hidden" name="authIds" value="'+id+'">';
		$("#hiddenBox").append(hiddenString);
	}
</script>