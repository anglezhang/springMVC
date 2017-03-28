<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/infodept/list.do">
<input value="${infodept.deptId }" type="hidden" id="backId_dept"  name="backId"/>
</form>
<div class="pageContent"  style="overflow-y:auto;height:610px;">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="infodept.add">
				<li><a id="infodept_add" class="add" ><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="infodept.edit">
				<li><a id="infodept_edit" class="edit"><span>修改</span></a><li>
			</tag:auth>
			<tag:auth code="infodept.delete">
				<li><a id="infodept_delete" class="delete"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<ul id="info_dept_list" class="ztree"></ul>
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
		 <tag:auth code="infodept.move"> 
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
		var url = "${pageContext.request.contextPath}/infodept/move.do";
		$("#backId_dept").val(treeNodes[0].id);
		alertMsg.confirm("确定要移动吗?", {
			okCall: function(){
				$.post(url, {sourceId: treeNodes[0].id,targetId:targetNode.id,moveType:moveType}, navTabAjaxDone, "json");
			}
		});
		
		return false;
	}
	var zNodes = ${infoDeptRoot};
	$(document).ready(function() {
		systree_auth_list = $.fn.zTree.init($("#info_dept_list"), setting, zNodes);
		//systree_auth_list.expandAll(true);
		var bId=$("#backId_dept").val();
		if(null!=bId&&bId!=""){
			var node = systree_auth_list.getNodeByParam("id",bId);//treeObj是tree对象
			systree_auth_list.expandNode(node, true, true, true,true);
			systree_auth_list.selectNode(node);
		}
		$("#infodept_add").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			
			var url = "${pageContext.request.contextPath}/infodept/add.do";
			
			if(nodes.length > 0){
				url = url + "?upId="+ nodes[0].id;
				//$("#backId_dept").val(nodes[0].id);
			}
			
			$.pdialog.open(url, "infodept_add_dialog", "添加", {width: 960, height:650, mask:true});
		});
		
		$("#infodept_edit").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/infodept/edit.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				url = url + "?deptId="+ nodes[0].id;
				$("#backId_dept").val(nodes[0].id);
				$.pdialog.open(url, "infodept_edit_dialog", "修改", {width: 960, height:650, mask:true});
			}else{
				alertMsg.warn("请选择类别节点");
			}
		});
		
		$("#infodept_delete").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/infodept/delete.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				$("#backId_dept").val(nodes[0].pId);
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