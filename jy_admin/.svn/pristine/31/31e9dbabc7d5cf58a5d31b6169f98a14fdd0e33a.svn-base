<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent"  style="overflow-y:auto;height:480px;">
	<input value="${goodsType.groupId }" type="hidden" id="lookup_backId"  name="backId"/>
	<ul id="lookup_goodstype_list" class="ztree"></ul>
</div>
<div style="display: none">
	<input type="checkbox"  id="lookbrand" name="infoGoodsType"/>
</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" id="buttonlook" multLookup="infoGoodsType" >选择带回</button></div></div>
			</li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
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
	};
	var zNodes = ${goodsTypeRoot};
	$(document).ready(function() {
		systree_auth_list = $.fn.zTree.init($("#lookup_goodstype_list"), setting, zNodes);
		var bId=$("#lookup_backId").val();
		if(null!=bId&&bId!=""){
			var node = systree_auth_list.getNodeByParam("id",bId);//treeObj是tree对象
			systree_auth_list.expandNode(node, true, true, true,true);
			systree_auth_list.selectNode(node);
		}
			$("#buttonlook").click(function() {
			var nodes = systree_auth_list.getSelectedNodes();
			if(nodes.length > 0 && nodes[0].id > 0){
				if(nodes[0].isParent){
					$("#buttonlook").attr("warn","请选择到最底节点");
				}else{
					var tns = "";
					tns=checkAllParents(nodes[0],tns);
					var typeName=checkReplace(tns);	
					var s="{groupId:'"+nodes[0].id+"',typeName:'"+typeName+nodes[0].name+"'}"
					$("#lookbrand").val(s);
					$("#lookbrand").attr("checked","checked");
				}
			}else{
				$("#lookbrand").attr("checked","");
				$("#buttonlook").attr("warn","请选择配件类别");
			}	
		});
	});
	//取所有的父节点
	function checkAllParents(treeNode,s){
			var rStr = s;
			if (treeNode==null || treeNode.pId==null) {
				return rStr;
			}
			else
			{
				rStr+=treeNode.getParentNode().name+"-";
				return checkAllParents(treeNode.getParentNode(),rStr);
			}
		}
	function checkReplace(typeName){
		var ts=typeName.substring(0,typeName.length-1).split("-");
		var rstr="";
		for(var i=ts.length-1;i>0;i--){
			rstr+=ts[i-1]+"-";
		}		
		return rstr;
	}	
			
</script>