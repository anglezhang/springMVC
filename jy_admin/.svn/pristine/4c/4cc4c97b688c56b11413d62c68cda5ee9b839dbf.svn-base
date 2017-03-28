<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/checklookup/checkno.ajax?isenter=${isenter}" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						盘点单号：<input type="text" name="checkNo" value="${checkNo}"/>
					</td>
					<td><div class="buttonActive"><div class="buttonContent"><button id="depot_check_look" type="submit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="list" width="100%" layoutH="84" >
		<thead>
			<th align="center">盘点单号</th>
			<th align="center">店铺名称</th>
			<th align="center">时间</th>
			<th align="center">操作</th>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="checklist" varStatus="status">
				<tr>
					<td align="center">${checklist.BNo}</td>
					<td align="center">${checklist.deptName}</td>
					<td align="center">${fn:substring(checklist.checkDate,0,19)}</td>
					<td align="center">
						<a class="btn btn-xzdh" href="javascript:$.bringBack({checkNo:'${checklist.BNo}'})"  multLookup="depotCheckVo"  title="选择带回"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>