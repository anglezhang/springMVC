<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div style="height: 20px;" class="unit"></div>
<div class="unit">
	<table class="table" width="100%">
		<tr>
			<td>调拨单编号</td>
			<td>${allocation.allocationNo }</td>
			<td>调入部门</td>
			<td>${todeptName }</td>
			<td>操作员</td>
			<td>${userName }</td>
		</tr>
		<tr>
		<td>创建时间</td>
		<td>${fn:substring(allocation.swDate,0,19) }</td>
		<td>备注</td>
		<td colspan="3">${allocation.memo }</td>
		</tr>
		
	</table>
</div>
<div style="height: 20px;" class="unit"></div>

<div class="pageContent">
	<table class="table" width="100%" layoutH="95">
		<thead>
			<tr align="center">
				<th>配件编号</th>
				<th>配件名称</th>
				<th>销售价</th>
				<th>申请数量</th>
				<th>调拨数量</th>
				<th>最小单位</th>
				<th>配件品牌</th>
				<th>配件规格</th>
				<th>来源</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allocationList}" var="allocationlist" varStatus="s">
					<tr align="center">
						<td>${allocationlist.infoGoods.goodsCode}</td>
						<td>${allocationlist.infoGoods.name}</td>
						<td>${allocationlist.infoGoods.price}</td>
						<td>${allocationlist.applyNum }</td>
						<td>${allocationlist.num}</td>
						<td>${allocationlist.infoGoods.paramConfig.name}</td>
						<td>${allocationlist.infoGoods.goodsBrand}</td>
						<td>${allocationlist.infoGoods.standard}</td>
						<td>${allocationlist.fromDeptName}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
</div>
