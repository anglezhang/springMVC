<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/infoemp/lookup.do" method="post">
		<input name="islook" value="${islook }" type="hidden"/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						姓名：<input type="text" name="queryName" value="${queryName}"/>
					</td>
					<%-- <td>
						身份证号码：<input type="text" name="queryCode" value="${queryIdCode}"/>
					</td> --%>
					<td><div class="buttonActive"><div class="buttonContent"><button id="infosub_s_b" type="submit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="list" width="100%" layoutH="64" >
		<thead>
			<th>员工编号</th>
			<th>姓名</th>
			<th>部门</th>
			<th>身份证号</th>
			<th>选择带回</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infoEmp" varStatus="s">
				<tr>
					<td>${infoEmp.empCode}</td>
					<td>${infoEmp.empName}</td>
					<td>${infoEmp.infoDept.name}</td>
					<td>${infoEmp.idCard}</td>
					<td>
						<a class="btn btn-xzdh" href="javascript:$.bringBack({emptId:'${infoEmp.empId}',empName:'${infoEmp.empName}',email:'${infoEmp.email}'}),setLookVal({emptId:'${infoEmp.empId}',empName:'${infoEmp.empName}',email:'${infoEmp.email}',deptName:'${infoEmp.infoDept.name}',postName:'${ infoEmp.position}'})" title="选择带回"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>