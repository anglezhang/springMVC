<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/servicetype/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/servicetype/list.do" method="post">
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
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="servicetype.add.do">
				<li><a class="add" href="${pageContext.request.contextPath}/servicetype/add.do" target="dialog" width="400" height="300" mask="true" rel="serviceadd"><span>添加</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table  class="table" width="100%" layoutH="112">
		<thead align="center">
			<tr>
				<th>序号</th>
				<th>服务类型名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="servicetype" varStatus="status">
				<tr>
					<td align="center">${status.index + 1}</td>
					<td align="center">${servicetype.servicename}</td>
					<td align="center">
						<tag:auth code="servicetype.delete.do">
							<a title="删除" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/servicetype/delete.do?servicetypeId=${servicetype.servicetypeId}" class="btn btn-delete"></a>
						</tag:auth>	
						<tag:auth code="servicetype.edit.do">
							<a title="编辑" target="dialog" rel="serviceTypeEdit" width="400" height="300" mask="true" href="${pageContext.request.contextPath}/servicetype/edit.do?servicetypeId=${servicetype.servicetypeId}" class="btn btn-edit"></a>
						</tag:auth>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../../include/page.jsp" %>
</div>