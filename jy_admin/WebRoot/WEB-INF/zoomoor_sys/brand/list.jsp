<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/brand/list.do">
<input value="${brand.id }" type="hidden" id="backId_brand"  name="backId"/>
</form>
<div class="pageContent"  style="overflow-y:auto;height:610px;">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="brand.add">
				<li><a id="brand_add" class="add" ><span>添加</span></a></li>
				<li><a id="brand_add_ty" class="add" ><span>添加通用</span></a></li>
			</tag:auth>
			<tag:auth code="brand.edit">
				<li><a id="brand_edit" class="edit"><span>修改</span></a><li>
			</tag:auth>
			<tag:auth code="brand.delete">
				<li><a id="brand_delete" class="delete"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<ul id="brand_list" class="ztree"></ul>
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
		 <tag:auth code="brand.move"> 
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
		var url = "${pageContext.request.contextPath}/brand/move.do";
		$("#backId_brand").val(treeNodes[0].id);
		alertMsg.confirm("确定要移动吗?", {
			okCall: function(){
				$.post(url, {sourceId: treeNodes[0].id,targetId:targetNode.id,moveType:moveType}, navTabAjaxDone, "json");
			}
		});
		
		return false;
	}
	var zNodes = ${brandRoot};
	$(document).ready(function() {
		systree_auth_list = $.fn.zTree.init($("#brand_list"), setting, zNodes);
		//systree_auth_list.expandAll(true);
		var bId=$("#backId_brand").val();
		if(null!=bId&&bId!=""){
			var node = systree_auth_list.getNodeByParam("id",bId);//treeObj是tree对象
			systree_auth_list.expandNode(node, true, true, true,true);
			systree_auth_list.selectNode(node);
		}
		$("#brand_add").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			
			var url = "${pageContext.request.contextPath}/brand/add.do";
			
			if(nodes.length > 0){
				url = url + "?upId="+ nodes[0].id;
			}
			$.pdialog.open(url, "brand_add_dialog", "添加", {width: 550, height:350, mask:true});
		});
		//添加通用
		$("#brand_add_ty").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/brand/addty.do";
			$("#backId_brand").val(nodes[0].pId);
			if(nodes.length > 0){
				url = url + "?upId="+ nodes[0].id;
				$.post(url, {id: nodes[0].id}, navTabAjaxDone, "json");
			}
		});
		$("#brand_edit").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/brand/edit.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				url = url + "?groupId="+ nodes[0].id;
				$("#backId_brand").val(nodes[0].id);
				$.pdialog.open(url, "brand_edit_dialog", "修改", {width: 550, height:350, mask:true});
			}else{
				alertMsg.warn("请选择权限节点");
			}
		});
		
		$("#brand_delete").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/brand/delete.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				$("#backId_brand").val(nodes[0].pId);
				if(nodes[0].isParent){
						alertMsg.warn("该节点下有子节点不能删除！");
				}else{
						alertMsg.confirm("确定要删除吗?", {
						okCall: function(){
							$.post(url, {id: nodes[0].id}, navTabAjaxDone, "json");
						}
					});
				}
			}else{
				alertMsg.warn("请选择类别节点");
			}
		});
	});
</script>