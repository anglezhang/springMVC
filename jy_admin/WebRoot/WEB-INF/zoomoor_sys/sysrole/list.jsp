<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/sysrole/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="sys.role.add">
				<li><a class="add" href="${pageContext.request.contextPath}/sysrole/add.do" target="dialog" width="600" height="480" mask="true"><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="sys.role.delete">
				<li><a class="delete" href="${pageContext.request.contextPath}/sysrole/delete.do" target="selectedTodo" postType="string" title="确定要删除吗?"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr align="center">
				<th width="50"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="50">ID</th>
				<th>角色名</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="sysrole">
					<tr align="center">
						<td><c:if test="${sysrole.roleId != 1}"><input type="checkbox" name="ids" value="${sysrole.roleId}"></c:if></td>
						<td>${sysrole.roleId}</td>
						<td>${sysrole.roleName}</td>
						<td>
							<tag:auth code="sys.role.edit">
								<a title="编辑" target="dialog" width="700" height="480" mask="true" href="${pageContext.request.contextPath}/sysrole/edit.do?id=${sysrole.roleId}" class="btn btn-edit "></a>
							</tag:auth>
							<tag:auth code="sys.role.delete">
								<c:if test="${sysrole.roleId != 1}">
									<a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/sysrole/delete.do?ids=${sysrole.roleId}" class="btn btn-delete"></a>
								</c:if>
							</tag:auth>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
