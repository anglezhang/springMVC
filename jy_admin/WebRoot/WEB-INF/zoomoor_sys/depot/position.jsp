<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader" style="font-size: 20px; text-align: center;">
	<c:if test="${summaryId==4}">盘点</c:if><c:if test="${summaryId==6}">出库</c:if><c:if test="${summaryId==3}">调拨</c:if><c:if test="${summaryId==1}">采购</c:if>数量：<span  style="color: red;font-size: 20px;"><fmt:formatNumber value="${num }"  type="currency" pattern="#.#"/></span>
</div>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/depot/saveposition.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" value="${tagId}" name="tagId"/>
		<input type="hidden" value="${inx}" name="inx"/>
		<input type="hidden" value="${summaryId}" name="summaryId" id="summid"/>
		<input type="hidden" value="${num}" id="num" name="num"/>
		<input type="hidden" value="${comeId}" name="comeId"/>
		<div class="pageFormContent" layoutH="83">
			<table class="list" style="width: 100%" layoutH="112" id="position_table">
				<tr id="depot_tj_0">
				<td><span style="float:left; margin-top: 5px;">仓位：</span>
					<input name="infoDepotPosition[0].rowId" type="hidden" value="0">
					<input name="infoDepotPosition[0].id" type="hidden" value="">
					<input name="infoDepotPosition[0].name" type="text" size="20" readonly="readonly" class='required' />
					<a title="选择仓位" class="btnLook" href="${pageContext.request.contextPath}/lookup/depotposition.do"  id="look[0]_gt" width="600" height="550" lookupGroup="infoDepotPosition[0]">仓位</a>
				</td>
				<td>
					<span style="float:left; margin-top: 5px;">数量：</span><input type="text"  class="required number" check-data onkeyup="getNum(this,'${summaryId}')"  name="infoDepotPosition[0].num" maxlength="10"   size="20"/>
					<a title='添加仓位' href='javascript:void(0);' onclick="addInput('${summaryId}');" class='btn btn-add'></a>
					<a title='删除仓位' href='javascript:void(0);' onclick="removeInput();" class='btn btn-delete'></a>
				</td>
				</tr>
				
				
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="depot.saveposition">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
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
	function dialogAjaxDone2(json){
	$("#backId").val(json.backId);
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		if (json.navTabId){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
			var array = json.arr;
			var html_aav="";
			var obj = new Object();
				obj.billrow = array;
				//obj.countNum = json.countNum;
			var positionVal=JSON.stringify(obj).replaceAll("\"","\'");
			$("#position_"+json.tagId).val(positionVal);
			$("#num_"+json.tagId).val(json.countNum);
		}
	}
}
function addInput(summid){
	var tr_length=$("#position_table tr").length; 
	var i=tr_length;
	var summid=$("#summid").val();
	var html="";
	html+="<tr id='depot_tj_"+i+"'>";
	html+="<td><span style='float:left; margin-top: 5px;'>仓位：</span><input name='infoDepotPosition["+i+"].id' value='' type='hidden'>";
	html+="<input name='infoDepotPosition["+i+"].rowId' value='0' type='hidden'><input style='float:left;'  name='infoDepotPosition["+i+"].name' type='text' size='20' class='required' readonly='readonly' />";
	html+="<span style='float:left; margin-top: 5px;'><a title='选择仓位' class='btnLook' href='${pageContext.request.contextPath}/lookup/depotposition.do' id='look["+i+"]_gt' width='600' height='550' lookupGroup='infoDepotPosition["+i+"]'>仓位</a></span>";
	/* if(summid!=null&&summid==6){
		html+="</td><td><span style='float:left; margin-top: 5px;'>数量：</span><input type='text' check-data onkeyup='getNum(this)'  name='infoDepotPosition["+i+"].num' class='required number' maxlength='10' value=''  size='20'/>";			
	}else{
		html+="</td><td><span style='float:left; margin-top: 5px;'>数量：</span><input type='text'  name='infoDepotPosition["+i+"].num' class='required number' maxlength='10' value=''  size='20'/>";			
	
	} */
	html+="</td><td><span style='float:left; margin-top: 5px;'>数量：</span><input type='text' check-data onkeyup=\"getNum(this,'"+summid+"')\"  name='infoDepotPosition["+i+"].num' class='required number' maxlength='10' value=''  size='20'/>";			
	html+="</td></tr>";			
	//$(obj).parent().parent().parent().parent().append(html);	
	$("#depot_tj_"+(i-1)).parent().append(html);
	$("#position_table").find(".btnLook").each(function(index){
		$(this).lookup();
	})
	
	
				
}
function removeInput(){
	var tr_length=$("#position_table tr").length; 
	var i=tr_length;
	if(i>1){
		$("#depot_tj_"+(i-1)).remove();
	}else{
		var errorMsg = new Object();
		errorMsg.statusCode = 300;
		errorMsg.message = "至少选择一个仓位";
		dialogAjaxDone(errorMsg);
	}
	
	
	
}

function getNum(obj,summid){
	var msg="";
	if(summid==1){
		msg="不能超过采购数量";
	}else if(summid==3){
		msg="不能超过调拨数量";
	}else if(summid==6){
		msg="不能超过出库数量";
	}
	


	countDapplay('check-data',msg);
}

/**
* 统计数量 check-data
*/
function countDapplay(attrName,msg)
{
	var pObj = $("#num").val();
	if(!isEmptyObj(pObj))
	{
		var checkNum = parseInt(pObj);
		var countCheckNum = 0;
		$("[" + attrName + "]").each(function(index, el) {
			var thisObj = $(el);
			var objVal = thisObj.val();
			if(!isEmptyObj(objVal))
			{
				countCheckNum += parseInt(objVal);
			}
		});

		
		if(countCheckNum>checkNum)
		{
			var errorMsg = new Object();
			errorMsg.statusCode = 300;
			errorMsg.message = msg;
			dialogAjaxDone(errorMsg);
		}else
		{
			$("#check_num").html(countCheckNum);
		}
	}
	
}

</script>

