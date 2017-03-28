<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/serviceItem/lookup.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="itemIdS" value="${itemIdS}" />
	<input type="hidden" name="carId" value="${carId}" />
</form>
<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/serviceItem/lookup.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="itemIdS" value="${itemIdS}" />
		<input type="hidden" name="carId" value="${carId}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						项目编码：<input type="text" name="queryItemCode" value="${queryItemCode}" />
					</td>
					<td>
						项目名称：<input type="text" name="queryItemName" value="${queryItemName}" />
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="reset">重置</button>
							</div>
						</div>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table  class="table" width="100%" layoutH="142">
		<thead align="center">
			<tr>
				<th width="50">服务类型</th>
				<th width="80">项目编码</th>
				<th>项目名称</th>
				<th>工时费工时费(￥元)</th>
				<th>折扣系数</th>
				<th>积分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="serviceitem" varStatus="status">
				<tr align="center">
					<td align="center">${serviceitem.servicetype.servicename}</td>
					<td>${serviceitem.itemcode}</td>
					<td>${serviceitem.itemname}</td>
					<td><fmt:formatNumber value="${serviceitem.workhourmoney}" type="currency"/></td>
					<td>${serviceitem.discount}</td>
					<td>${serviceitem.intergral}</td>
					<td>
						<a class="btn btn-xzdh" href="javascript:$.bringBack({itemId:'${serviceitem.itemId}',servicename:'${serviceitem.servicetype.servicename}',servicetypeId:'${serviceitem.servicetype.servicetypeId}',itemname:'${serviceitem.itemname}',itemcode:'${serviceitem.itemcode}',workhourmoney:'${serviceitem.workhourmoney}',intergral:'${serviceitem.intergral}',discount:'${serviceitem.discount}'}),createTable({itemId:'${serviceitem.itemId}',servicename:'${serviceitem.servicetype.servicename}',servicetypeId:'${serviceitem.servicetype.servicetypeId}',itemname:'${serviceitem.itemname}',itemcode:'${serviceitem.itemcode}',workhourmoney:'${serviceitem.workhourmoney}',intergral:'${serviceitem.intergral}',discount:'${serviceitem.discount}'})" title="选择带回"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>