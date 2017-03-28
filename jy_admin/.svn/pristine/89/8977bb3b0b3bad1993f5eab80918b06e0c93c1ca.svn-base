<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent"  style="overflow-y:auto;height:480px;">
	<input value="${position.id }" type="hidden" id="lookup_backId"  name="backId"/>
	<ul id="lookup_position_list" class="ztree"></ul>
</div>
<div style="display: none">
	<input type="checkbox"  id="lookbrand" name="depotCheck"/>
</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" id="buttonlook" multLookup="depotCheck" >选择带回</button></div></div>
			</li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
<script type="text/javascript">
	var systree_auth_list;
	var setting = {
		check: {
                enable: true,
                chkboxType : {"Y": "s", "N": "s"}

        },
		view: {
			selectedMulti: true
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};
	var zNodes = ${depotpositionRoot};
	$(document).ready(function() {
			systree_auth_list = $.fn.zTree.init($("#lookup_position_list"), setting, zNodes);
			$("#buttonlook").click(function() {
			var nodes = systree_auth_list.getCheckedNodes(true);
			if(nodes.length > 0 && nodes[0].id > 0){
				//if(nodes[0].isParent){
					//$("#buttonlook").attr("warn","请选择到最底节点");
				//}else{
					var tns = "";
					var sname="";
					var sid="";
					for(var i=0;i<nodes.length;i++){
						tns=nodes[i].getParentNode().name;
						var typeName=checkReplace(tns);	
						sname+=typeName+nodes[i].name+"|";
						sid+=nodes[i].id+",";
					}
					var s="{positions:'"+sid.substring(0, sid.length-1)+"',posName:'"+sname+"'}"
					$("#lookbrand").val(s);
					$("#lookbrand").attr("checked","checked");
				//}
			}else{
				$("#lookbrand").attr("checked","");
				$("#buttonlook").attr("warn","请选择仓位");
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
		//ajax 查询仓位全名称
		/* var rstr;
		var url="${pageContext.request.contextPath}/depotposition/getpositionfullname.ajax"
		$.post(url,{pid:pid},function(data){
			rstr=data.fullName;
		}) */
		var ts=typeName.substring(0,typeName.length-1).split("-");
		var rstr="";
		for(var i=ts.length-1;i>0;i--){
			rstr+=ts[i-1]+"-";
		}		
		return rstr;
	}	
			
</script>