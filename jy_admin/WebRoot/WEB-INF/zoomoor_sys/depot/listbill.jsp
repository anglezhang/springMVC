<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader" style="font-size: 20px; text-align: center;">
	<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/service/servicegoodslookup.do">
	</form>
	<c:if test="${summaryId==7  }">
	当前调拨数量<span  style="color: red;font-size: 20px;" id="pnum"><fmt:formatNumber value="${num}"  type="currency" pattern="#.#"/></span>,已选择<span style="color: red;font-size: 20px;" id="check_num">0</span>
	</c:if>
</div>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/depot/saveposition.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" value="${tagId}" name="tagId"/>
		<input type="hidden" value="${goodsId}" name="goodsId"/>
		<input type="hidden" value="${iswx}" name="iswx"/>
		<input type="hidden" value="${summaryId}" name="summaryId"/>
		<input type="hidden" value="${inx}" name="inx"/>
		<div class="pageFormContent" layoutH="85">
			<table class="list" width="100%" layoutH="88" >
		<thead>
			<tr>
				<th>配件编号</th>
				<th>配件名称</th>
				<th>最小单位</th>
				<th>配件品牌</th>
				<th>配件规格</th>
				<th>当前库存</th>
				<th>所在店铺</th>
				<th>库位</th>
				<th>选择数量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="s" items="${list}" var="depot">
					<input name="infoDepotPosition[${s.index }].rowId" type="hidden" value="0">
					<input name="infoDepotPosition[${s.index }].id" type="hidden" value="${ depot.positionId}">
					<input name="infoDepotPosition[${s.index }].comeId" type="hidden" value="${ depot.comeId}">
				<tr>
					<td>${depot.infoGoods.goodsCode}</td>
					<td>${depot.infoGoods.name}</td>
					<td>${depot.infoGoods.paramConfig.name}</td>
					<td>${depot.infoGoods.goodsBrand}</td>
					<td>${depot.infoGoods.standard}</td>
					<td>${depot.balance}</td>
					<td>${depot.infoDept.name}</td>
					<td>${depot.position}</td>
					<td><input name="infoDepotPosition[${s.index }].num" check-data type="text" size="5" onkeyup="getNum(this,'${depot.balance}')" class=" number"  value=""/></td>
				</tr>
			</c:forEach>
			<c:if test="${empty list }">
			<tr><td colspan="8" style="font-size: 20px; text-align: center;color: red;">该配件暂无库存</td></tr>
			</c:if>
		</tbody>
	</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="depot.saveposition,depot.saveposition.wx">
					<li>
						<div class="buttonActive" id="submitBtn" <c:if test="${summaryId!=11&&summaryId!=7 && !empty summaryId}">style="display: none"</c:if>><div class="buttonContent"><button type="submit" >保存</button></div></div>
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

