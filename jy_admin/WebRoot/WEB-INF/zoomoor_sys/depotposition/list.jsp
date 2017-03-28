<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/depotposition/list.do">
<input value="${depotposition.id }" type="hidden" id="backId_position"  name="backId_position"/>
</form>
<div class="pageContent"  style="overflow-y:auto;height:610px;">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="depotposition.add">
				<li><a id="depotposition_add" class="add" ><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="depotposition.edit">
				<li><a id="depotposition_edit" class="edit"><span>修改</span></a><li>
			</tag:auth>
			<tag:auth code="depotposition.delete">
				<li><a id="depotposition_delete" class="delete"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<ul id="depot_position_list" class="ztree"></ul>
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
		 <tag:auth code="depotposition.move"> 
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
		var url = "${pageContext.request.contextPath}/depotposition/move.do";
		$("#backId_position").val(treeNodes[0].id);
		alertMsg.confirm("确定要移动吗?", {
			okCall: function(){
				$.post(url, {sourceId: treeNodes[0].id,targetId:targetNode.id,moveType:moveType}, navTabAjaxDone, "json");
			}
		});
		
		return false;
	}
	var zNodes = ${depotpositionRoot};
	$(document).ready(function() {
		systree_auth_list = $.fn.zTree.init($("#depot_position_list"), setting, zNodes);
		//systree_auth_list.expandAll(true);
		var bId=$("#backId_position").val();
		if(null!=bId&&bId!=""){
			var node = systree_auth_list.getNodeByParam("id",bId);//treeObj是tree对象
			systree_auth_list.expandNode(node, true, true, true,true);
			systree_auth_list.selectNode(node);
		}
		$("#depotposition_add").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			
			var url = "${pageContext.request.contextPath}/depotposition/add.do";
			
			if(nodes.length > 0){
				url = url + "?upId="+ nodes[0].id;
				//$("#backId_position").val(nodes[0].id);
			}
			
			$.pdialog.open(url, "depotposition_add_dialog", "添加", {width: 550, height:200, mask:true});
		});
		
		$("#depotposition_edit").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/depotposition/edit.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				url = url + "?groupId="+ nodes[0].id;
				$("#backId_position").val(nodes[0].id);
				$.pdialog.open(url, "depotposition_edit_dialog", "修改", {width: 550, height:200, mask:true});
			}else{
				alertMsg.warn("请选择仓位节点");
			}
		});
		
		$("#depotposition_delete").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/depotposition/delete.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				$("#backId_position").val(nodes[0].pId);
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
				alertMsg.warn("请选择仓位节点");
			}
		});
	});
</script>