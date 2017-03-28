<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent"  style="overflow-y:auto;height:480px;">
	<input value="${infobrand.id }" type="hidden" id="lookup_backId_brand"  name="backId"/>
	<ul id="lookup_brand_list" class="ztree"></ul>
</div>
<div style="display: none">
	<input type="checkbox"  id="lookbrand" name="infoBrand"/>
</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" id="buttonlook" bringBackFun="setlookhref" multLookup="infoBrand" >选择带回</button></div></div>
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
	var zNodes = ${brandRoot};
	$(document).ready(function() {
			var prentObj = $("#lookup_backId_brand").parent().parent().parent()
			prentObj.css("z-index",1700);
			systree_auth_list = $.fn.zTree.init($("#lookup_brand_list"), setting, zNodes);
			var bId=$("#lookup_backId_brand").val();
			if(null!=bId&&bId!=""){
			var node = systree_auth_list.getNodeByParam("id",bId);//treeObj是tree对象
				systree_auth_list.expandNode(node, true, true, true,true);
				systree_auth_list.selectNode(node);
			}
			$("#buttonlook").click(function() {
				prentObj.css("z-index",1000);
			var nodes = systree_auth_list.getSelectedNodes();
			if(nodes.length > 0 && nodes[0].id > 0){
				if(nodes[0].isParent){
					$(".shadow").css("z-index","999");
					$("#buttonlook").attr("warn","请选择到最底节点");
				}else{
					var s="{id:'"+nodes[0].id+"',name:'"+nodes[0].getParentNode().name+"-"+nodes[0].name+"'}"
					$("#lookbrand").val(s);
					$("#lookbrand").attr("checked","checked");
				}
			}else{
				$(".shadow").css("z-index","999");
				$("#lookbrand").attr("checked","");
				$("#buttonlook").attr("warn","请选择品牌车系");
			}	
		});
	});
</script>