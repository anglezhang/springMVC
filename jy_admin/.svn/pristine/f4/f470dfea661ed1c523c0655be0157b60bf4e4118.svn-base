<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/goodsType/list.do">
<input value="${goodsType.groupId }" type="hidden" id="backId_goodstype"  name="backId"/>
</form>
<div class="pageContent"  style="overflow-y:auto;height:610px;">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="goodstype.add">
				<li><a id="goodstype_add" class="add" ><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="goodstype.edit">
				<li><a id="goodstype_edit" class="edit"><span>修改</span></a><li>
			</tag:auth>
			<tag:auth code="goodstype.delete">
				<li><a id="goodstype_delete" class="delete"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<ul id="goods_type_list" class="ztree"></ul>
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
		 <tag:auth code="goodstype.move"> 
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
		var url = "${pageContext.request.contextPath}/goodsType/move.do";
		$("#backId_goodstype").val(treeNodes[0].id);
		alertMsg.confirm("确定要移动吗?", {
			okCall: function(){
				$.post(url, {sourceId: treeNodes[0].id,targetId:targetNode.id,moveType:moveType}, navTabAjaxDone, "json");
			}
		});
		
		return false;
	}
	var zNodes = ${goodsTypeRoot};
	$(document).ready(function() {
		systree_auth_list = $.fn.zTree.init($("#goods_type_list"), setting, zNodes);
		//systree_auth_list.expandAll(true);
		var bId=$("#backId_goodstype").val();
		if(null!=bId&&bId!=""){
			var node = systree_auth_list.getNodeByParam("id",bId);//treeObj是tree对象
			systree_auth_list.expandNode(node, true, true, true,true);
			systree_auth_list.selectNode(node);
		}
		$("#goodstype_add").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			
			var url = "${pageContext.request.contextPath}/goodsType/add.do";
			
			if(nodes.length > 0){
				url = url + "?upId="+ nodes[0].id;
				//$("#backId_goodstype").val(nodes[0].id);
			}
			
			$.pdialog.open(url, "goodstype_add_dialog", "添加", {width: 550, height:200, mask:true});
		});
		
		$("#goodstype_edit").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/goodsType/edit.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				url = url + "?groupId="+ nodes[0].id;
				$("#backId_goodstype").val(nodes[0].id);
				$.pdialog.open(url, "goodstype_edit_dialog", "修改", {width: 550, height:200, mask:true});
			}else{
				alertMsg.warn("请选择类别节点");
			}
		});
		
		$("#goodstype_delete").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			var url = "${pageContext.request.contextPath}/goodsType/delete.do";
			if(nodes.length > 0 && nodes[0].id > 0){
				$("#backId_goodstype").val(nodes[0].pId);
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