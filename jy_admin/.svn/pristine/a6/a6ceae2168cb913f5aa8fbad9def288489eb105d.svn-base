<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/sysauth/list.do">
<input value="${sysAuth.authId }" type="hidden" id="backId"  name="backId"/>
</form>
<div class="pageContent"  style="overflow-y:auto;height:680px;">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="sys.auth.add">
				<li><a id="auth_add" class="add" ><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="sys.auth.edit">
				<li><a id="auth_edit" class="edit" warn="请选择权限节点"><span>修改</span></a><li>
			</tag:auth>
			<tag:auth code="sys.auth.delete">
				<li><a id="auth_delete" class="delete"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<ul id="systree_auth_list" class="ztree"></ul>
</div>
 
 
<script type="text/javascript">
	var systree_auth_list;
	var setting = {
		view: {
			selectedMulti: false
		},
		data : {
			simpleData : {
				enable : true
			}
		}
		 <tag:auth code="sys.auth.move"> 
		,edit: {
                enable: true,
                showRemoveBtn: false,//节点不显示参数按钮
                showRenameBtn: false,//节点不显示编辑按钮
                drag: {
               
                    prev: true,//允许向上拖动
                    next: true,//不允许向下拖动
                    inner: true//允许当前层次内进行拖动
              
                }
        },
        callback:{
        	beforeDrop:beforeDrop
        }
       </tag:auth>    
	};
	
	function beforeDrop(treeId,treeNodes,targetNode,moveType){
		var url = "${pageContext.request.contextPath}/sysauth/move.do";
		$("#backId").val(treeNodes[0].id);
		alertMsg.confirm("确定要移动吗?", {
			okCall: function(){
				$.post(url, {sourceId: treeNodes[0].id,targetId:targetNode.id,moveType:moveType}, navTabAjaxDone, "json");
			}
		});
		
		return false;
	}
	var zNodes = ${sysAuthRoot};
	$(document).ready(function() {
		systree_auth_list = $.fn.zTree.init($("#systree_auth_list"), setting, zNodes);
		//systree_auth_list.expandAll(true);
		var bId=$("#backId").val();
		if(null!=bId&&bId!=""){
			var node = systree_auth_list.getNodeByParam("id",bId);//treeObj是tree对象
			systree_auth_list.expandNode(node, true, true, true,true);
			systree_auth_list.selectNode(node);
		}
		$("#auth_add").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			
			var url = "${pageContext.request.contextPath}/sysauth/add.do";
			
			if(nodes.length > 0){
				url = url + "?parentAuthId="+ nodes[0].id;
				//$("#backId").val(nodes[0].id);
			}
			
			$.pdialog.open(url, "systree_auth_list_add_dialog", "添加", {width: 550, height: 300, mask:true});
		});
		
		$("#auth_edit").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/sysauth/edit.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				url = url + "?authId="+ nodes[0].id;
				$("#backId").val(nodes[0].id);
				$.pdialog.open(url, "systree_auth_list_edit_dialog", "修改", {width: 550, height: 300, mask:true});
			}else{
				alertMsg.warn("请选择权限节点");
			}
		});
		
		$("#auth_delete").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/sysauth/delete.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				$("#backId").val(nodes[0].pId);
				if(nodes[0].id > 10){
					if(nodes[0].isParent){
						alertMsg.warn("该节点下有子节点不能删除！");
				}else{
						alertMsg.confirm("确定要删除吗?", {
							okCall: function(){
								$.post(url, {ids: nodes[0].id}, navTabAjaxDone, "json");
							}
						});
					}
				}else{
					alertMsg.warn("不能删除系统权限");
				}
			}else{
				alertMsg.warn("请选择权限节点");
			}
		});
	});
</script>