<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/emplyee/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryEmpCode" value="${queryEmpCode}" />
	<input type="hidden" name="queryEmpName" value="${queryEmpName}" />
	<input type="hidden" name="queryPosition" value="${queryPosition}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/emplyee/list.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						所在部门：<select style="width:152px;height:21px" name="queryDept">
							<option value="-1" <c:if test="${queryDept==-1}">selected="selected"</c:if> >全部</option>
							<c:forEach items="${deptS}" var="infDept" varStatus="s">
								<option value="${infDept.deptId}" <c:if test="${queryDept==infDept.deptId}">selected="selected"</c:if> >${infDept.name}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						员工编号：<input type="text" name="queryEmpCode" value="${queryEmpCode}"/>
					</td>
					<td>
						员工姓名：<input type="text" name="queryEmpName" value="${queryEmpName}"/>
					</td>
					<td>
						性别：<input type="radio" name="queryGender" value="1" <c:if test="${queryGender==1}">checked="checked" </c:if> />男
							<input type="radio" name="queryGender"  value="0" <c:if test="${queryGender==0}">checked="checked" </c:if>/>女
							<input type="radio" name="queryGender"   value="" <c:if test="${empty queryGender}">checked="checked" </c:if>/>全部
					</td>
					<td>
						职位：<input type="text" name="queryPosition" value="${queryPosition}"/>
					</td>
					<td>
						状态：<input type="radio" name="queryStatus" value="1" <c:if test="${queryStatus==1}">checked="checked" </c:if> />在职
							<input type="radio" name="queryStatus" value="2"  <c:if test="${queryStatus==2}">checked="checked" </c:if> />离职
							<input type="radio" name="queryStatus" value="" <c:if test="${empty queryStatus}">checked="checked" </c:if>/>全部
					</td>
					<td>
						 
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="info.emplyee.add">
				<li><a class="add" rel="emplyee_add" href="${pageContext.request.contextPath}/emplyee/add.do" target="dialog"  width="1140" height="550" mask="true"><span>添加</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<th align="center">员工编号</th>
			<th align="center">员工姓名</th>
			<th align="center">性别</th>
			<th align="center">职位</th>
			<th align="center">所在部门</th>
			<th align="center">联系电话</th>
			<th align="center">紧急联系人电话</th>
			<th align="center">创建时间</th>
			<th align="center">状态</th>
			<th align="center">操作</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infEmp" varStatus="s">
				<tr>
					<td>${infEmp.empCode}</td>
					<td>${infEmp.empName}</td>
					<td>
						<c:if test="${infEmp.gender}">男</c:if>
						<c:if test="${!infEmp.gender}">女</c:if>
					</td>
					<td>${infEmp.position}</td>
					<td>${infEmp.infoDept.name}</td>
					<td>${infEmp.mobile}</td>
					<td>${infEmp.attnTel}</td>
					<td><fmt:formatDate value="${infEmp.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:if test="${infEmp.status==1}">在职</c:if>
						<c:if test="${infEmp.status==2}">离职</c:if>
					</td>
					<td>
						<tag:auth code="info.emplyee.delete">
							<a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/emplyee/delete.do?empId=${infEmp.empId}" class="btn btn-delete"></a>
						</tag:auth>
						<tag:auth code="info.emplyee.edit">
							<a title="编辑" target="dialog" rel="emplyeeredit" width="1140" height="550"  mask="true" href="${pageContext.request.contextPath}/emplyee/edit.do?empId=${infEmp.empId}" class="btn btn-edit"></a>
						</tag:auth>
						<tag:auth code="info.emplyee.edit">
							<a title="查看" target="dialog" rel="emplyeerview" width="1140" height="550"  mask="true" href="${pageContext.request.contextPath}/emplyee/scan.do?empId=${infEmp.empId}" class="btn btn-ck"></a>
						</tag:auth>
						<c:if test="${infEmp.status==1}" >
							<tag:auth code="info.emplyee.out">
								<a title="离职" target="ajaxTodo" href="${pageContext.request.contextPath}/emplyee/out.do?empId=${infEmp.empId}" class="btn btn-hmd"></a>
							</tag:auth>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>