<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader">
	<form id="lookuppagerForm" method="post" action="${pageContext.request.contextPath}/apporder/shopLookup.do">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	</form>
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/apporder/shopLookup.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						部门名称：<input type="text" name="queryDeptName" value="${queryDeptName}"/>
					</td>
					<td>
						上级部门名称：<input type="text" name="queryUpDeptName" value="${queryUpDeptName}"/>
					</td>
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit" id="customerlookupsubmit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="100" >
		<thead>
			<th>部门名称</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infoDept" varStatus="status">
				<tr>
					<td>
					${infoDept.name}
					</td>
					<td>
						<a class="btn btn-xzdh" href="javascript:$.bringBack({deptId:'${infoDept.deptId}',deptname:'${infoDept.name}'}),setLookVal({deptId:'${infoDept.deptId}',deptname:'${infoDept.name}'})" title="选择带回"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>