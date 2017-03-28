<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/paramconfig/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="config.add">
				<li><a class="add" href="${pageContext.request.contextPath}/paramconfig/add.do" target="dialog"  width="600" height="200" mask="true"><span>添加</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr align="center">
				<th width="50">ID</th>
				<th>类型</th>
				<th>名称</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="config" varStatus="s">
					<tr align="center">
						<td>${config.unitId}</td>
						<td>
							<c:if test="${ config.paramType==1}">配件单位</c:if>
							<c:if test="${ config.paramType==2}">职位名称</c:if>
							<c:if test="${ config.paramType==3}">职业技能</c:if>
							<c:if test="${ config.paramType==4}">付款方式</c:if>
							<c:if test="${ config.paramType==5}">驾照类型</c:if>
							<c:if test="${ config.paramType==6}">排量</c:if>
						</td>
						<td>${config.name}</td>
						<td>
							<tag:auth code="config.delete">
								 <a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/paramconfig/delete.do?id=${config.unitId}" class="btn btn-delete"></a>
							</tag:auth>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
