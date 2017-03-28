<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="">
	<table class="table" width="100%">
		<tr>
		<td>店铺名称</td>
		<td colspan="3">${deptName}</td>
		</tr>
		<tr>
			<td>上次月结期段</td>
			<td>${maxMonth }</td>
			<td>本次月结期段</td>
			<td>${curMonth }</td>
		</tr>
	</table>
</div>
<h3 align="center" style="font-size: 30px;">本期帐实不符明细</h3>
<div class="divider"></div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr align="center">
				<th>单据号</th>
				<th>配件类型</th>
				<th>配件编号</th>
				<th>货号</th>
				<th>配件名称</th>
				<th>配件规格</th>
				<th>配件品牌</th>
				<th>最小单位</th>
				<th>单据入库数量</th>
				<th>明细入库数量</th>
				<th>单据出库数量</th>
				<th>明细出库数量</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list" varStatus="s">
					<tr align="center">
						<td>${list.b_no}</td>
						<td>${list.type_name}</td>
						<td>${list.goods_code}</td>
						<td>${list.art_no}</td>
						<td>${list.goods_name}</td>
						<td>${list.standard}</td>
						<td>${list.goods_brand}</td>
						<td>${list.unit}</td>
						<td>${list.list_in_num}</td>
						<td>${list.row_in_num}</td>
						<td>${list.list_out_num }</td>
						<td>${list.row_out_num}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<form onsubmit="return validateCallback(this, dialogAjaxDone_add);" action="${pageContext.request.contextPath}/depot/checkbalance.action" method="post">
	<div class="formBar">
			<ul>
				<c:if test="${fn:length(list)<=0  || empty list}">
				<li>
					<div class="button"><div class="buttonContent"><button type="submit" >结账</button></div></div>
				</li>
				</c:if>
			</ul>
		</div>
	</form>	
</div>
<script>
function dialogAjaxDone_add(json){
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		navTab.closeCurrentTab();
		navTab.openTab("571","${pageContext.request.contextPath}/depot/monthlist.action",{title:"月结"})	;
	
	}
}

</script>