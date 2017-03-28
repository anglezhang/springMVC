<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="unit">
	<table class="table" width="100%" >
		<c:forEach items="${list }" var="headlist" varStatus="s" begin="0" end="0">
		<tr>
		<td >店铺名称</td>
		<td colspan="3">${deptName}</td>
		</tr>
		<tr>
			<td>上次月结期段</td>
			<td><input type="hidden" value="${headlist.maxMonth }" id="max"/>${headlist.maxMonth }</td>
			<td>本次月结期段</td>
			<td >${headlist.curMonth }<input type="hidden" value="${headlist.curMonth }" id="cur"/></td>
		</tr>
		 </c:forEach>
	</table>
</div>
<h3 align="center" style="font-size: 30px;">本期月结</h3>
<div class="divider"></div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr align="center">
				<th>数量</th>
				<th>单位</th>
				<th>期初数量</th>
				<th>期初销售金额</th>
				<th>期初成本金额</th>
				<th>入库数量</th>
				<th>入库销售金额</th>
				<th>入库成本金额</th>
				<th>出库数量</th>
				<th>出库销售金额</th>
				<th>出库成本金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list" varStatus="s">
					<tr align="center">
						<td>${list.n}</td>
						<td>${list.unit}</td>
						<td>${list.d_f}</td>
						<td>${list.d_f_m }</td>
						<td>${list.d_f_m_cost}</td>
						<td>${list.d_i}</td>
						<td>${list.d_i_m}</td>
						<td>${list.d_i_m_cost}</td>
						<td>${list.d_o}</td>
						<td>${list.d_o_m}</td>
						<td>${list.d_o_m_cost}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button"  onclick="getSecond()">下一步</button></div></div>
				</li>
			</ul>
		</div>
</div>
<script>
	function getSecond(){
		var maxMonth=$("#max").val();
		var curMonth=$("#cur").val();	
		navTab.closeCurrentTab();
		navTab.openTab("571","${pageContext.request.contextPath}/depot/monthcheck.action?maxMonth="+maxMonth+"&curMonth="+curMonth,{title:"月结"})	;
	}
</script>