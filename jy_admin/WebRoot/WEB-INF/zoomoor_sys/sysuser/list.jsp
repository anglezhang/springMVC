<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/sysuser/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryUsername" value="${queryUsername}" />
	<input type="hidden" name="queryRealname" value="${queryRealname}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/sysuser/list.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						用户名：<input type="text" name="queryUsername" value="${queryUsername}"/>
					</td>
					<td>
						真实姓名：<input type="text" name="queryRealname" value="${queryRealname}"/>
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
			<tag:auth code="sys.user.add">
				<li><a class="add" href="${pageContext.request.contextPath}/sysuser/add.do" target="dialog"  width="600" height="480" mask="true"><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="sys.user.delete">
				<li><a class="delete" href="${pageContext.request.contextPath}/sysuser/delete.do" target="selectedTodo" postType="string" title="确定要删除吗?"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th width="50"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="50">ID</th>
				<th>用户名</th>
				<th>用户角色</th>
				<th>真实姓名</th>
				<th>部门</th>
				<th>职位</th>
				<th>最后登录</th>
				<th>登录次数</th>
				<th>禁用</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="sysuser" varStatus="s">
			<c:if test="${sessionScope.auth_user.userId==1}">
					<tr align="center">
						<td><c:if test="${sysuser.userId != 1}"><input type="checkbox" name="ids" value="${sysuser.userId}"></c:if></td>
						<td>${sysuser.userId}</td>
						<td>${sysuser.username}</td>
						<td>
							<c:forEach items="${sysuser.sysRoles}" var="sysrole">
								${sysrole.roleName}
							</c:forEach>
						</td>
						<td>${sysuser.infoEmp.empName}</td>
						<td>${sysuser.infoEmp.infoDept.name}</td>
						<td>${sysuser.infoEmp.position}</td>
						<td><fmt:formatDate value="${sysuser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/> - ${sysuser.lastLoginIp}</td>
						<td>${sysuser.loginCount}</td>
						<td>${sysuser.isDisabled == 0 ? "否":"<font color='red'>是</font>"}</td>
						<td>
							<tag:auth code="sys.user.edit">
								<a title="编辑" target="dialog" width="600" height="480" mask="true" href="${pageContext.request.contextPath}/sysuser/edit.do?id=${sysuser.userId}" class="btn btn-edit"></a>
							</tag:auth>
							<tag:auth code="sys.user.delete">
								<c:if test="${sysuser.userId != 1}">
									<a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/sysuser/delete.do?ids=${sysuser.userId}" class="btn btn-delete"></a>
								</c:if>
							</tag:auth>
						</td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.auth_user.userId!=1}">
					<tr align="center" <c:if test="${sysuser.userId == 1}">id='tr_1'</c:if>>
						<td><c:if test="${sysuser.userId != 1}"><input type="checkbox" name="ids" value="${sysuser.userId}"></c:if></td>
						<td>${sysuser.userId}</td>
						<td>${sysuser.username}</td>
						<td>
							<c:forEach items="${sysuser.sysRoles}" var="sysrole">
								${sysrole.roleName}
							</c:forEach>
						</td>
						<td>${sysuser.infoEmp.empName}</td>
						<td>${sysuser.infoEmp.infoDept.name}</td>
						<td>${sysuser.infoEmp.paramConfig.name}</td>
						<td><fmt:formatDate value="${sysuser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/> - ${sysuser.lastLoginIp}</td>
						<td>${sysuser.loginCount}</td>
						<td>${sysuser.isDisabled == 0 ? "否":"<font color='red'>是</font>"}</td>
						<td>
							<tag:auth code="sys.user.edit">
								<a title="编辑" target="dialog" width="600" height="480" mask="true" href="${pageContext.request.contextPath}/sysuser/edit.do?id=${sysuser.userId}" class="btn btn-edit "></a>
							</tag:auth>
							<tag:auth code="sys.user.delete">
								<c:if test="${sysuser.userId != 1}">
									<a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/sysuser/delete.do?ids=${sysuser.userId}" class="btn btn-delete"></a>
								</c:if>
							</tag:auth>
						</td>
					</tr>
					</c:if>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
<script type="text/javascript">
$(function(){
	$("#tr_1").remove();
})
</script>
