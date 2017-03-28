<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader" style="font-size: 20px; text-align: center;">
	<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/service/servicegoodslookup.do">
	</form>
	<c:if test="${ empty zdapplyId }">
	当前申请数量：<span  style="color: red;font-size: 20px;" id="pnum"><fmt:formatNumber value="${applySource.num }"  type="currency" pattern="#.#"/></span>,已选择：<span style="color: red;font-size: 20px;" id="check_num">0</span>
	</c:if>
</div>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/allocation/saveapply.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
<input type="hidden" value="${applySource.applyId }" name="applyId"/>
<input type="hidden" value="${zdapplyId}" name="zdapplyId" id=""/>
<input type="hidden" value="${goodsId}" name="goodsId"/>
<div class="pageContent">
	<table class="list" width="100%" layoutH="88" >
		<thead>
			<tr>
				<th>所在店铺</th>
				<th>配件编号</th>
				<th>配件名称</th>
				<th>最小单位</th>
				<th>成本价</th>
				<th>配件品牌</th>
				<th>配件规格</th>
				<th>当前库存</th>
				<th>供应商</th>
				<th>选择数量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="s" items="${depotList}" var="depot">
				<input name="allocationApplyVo[${s.index }].deptId" type="hidden" value="${depot.dep_id }"/>
				<tr>
					<td>${depot.dept_name}</td>
					<td>${depot.goods_code}</td>
					<td>${depot.goods_name}</td>
					<td>${depot.unit}</td>
					<td>${depot.cost_price}</td>
					<td>${depot.goods_brand}</td>
					<td>${depot.standard}</td>
					<td>${depot.balance}</td>
					<td>${depot.sup_name}</td>
					<td>
					<c:if test="${deptId!=depot.dep_id }">
						<input name="allocationApplyVo[${s.index }].num"  class="number" check-data  size="5" type="text" onkeyup="getNum(this,'${depot.balance}')"     value=""/>
					</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty depotList }">
			<tr><td colspan="8" style="font-size: 20px; text-align: center;color: red;">该配件暂无库存</td></tr>
			</c:if>
		</tbody>
	</table>
</div>
<div class="formBar">
	<ul>
		<tag:auth code="allocation.saveapply">
		<c:if test="${!empty depotList }">
		<li>
			<div class="buttonActive" id="submitBtn" <c:if test="${ empty zdapplyId }"> style="display:none"</c:if>><div class="buttonContent"><button  type="submit">保存</button></div></div>
		</li>
		</c:if>
		</tag:auth>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
		</li>
	</ul>
</div>
</form>		
<script>
	function dialogAjaxDone2(json){
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
			$("#dept_ref").click();
			$("#source_"+json.applyId).html(json.deptName);
			$("#num_"+json.applyId).html("<input type='text' value='"+json.num+"' class='required' size='10'   readonly='readonly' />");
			
			var array = json.arr;
			var html_aav="";
			var obj = new Object();
				obj.child = array;
				obj.applyId = json.applyId;
				obj.goodsId = json.goodsId;
				obj.deptName=json.deptName;
			var aavVal=JSON.stringify(obj).replaceAll("\"","\'");
			$("#aav_"+json.applyId).val(aavVal);
		}
	}
}
function getNum(obj,bnum){
	var objval=parseInt($(obj).val());
	var balance=parseInt(bnum);
	if(objval>balance){
		$(obj).val(balance);
	}
	countDapplay('check-data',"不能超过当前数量");
}

/**
* 统计数量 check-data
*/
function countDapplay(attrName,msg)
{
	var pObj = $("#pnum");
	if(!isEmptyObj(pObj.html()))
	{
		var checkNum = parseInt(pObj.html());
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
			$("#submitBtn").hide();
		}else
		{
			$("#submitBtn").show();
			$("#check_num").html(countCheckNum);
		}
	}
	
}

</script>
		
		
		
		
		
		