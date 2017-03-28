<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/infosub/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryName" value="${queryName}" />
	<input type="hidden" name="queryCode" value="${queryCode}" />
	<input type="hidden" name="queryLinkMan" value="${queryLinkMan}" />
	<input type="hidden" name="queryPhone" value="${queryPhone}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/infosub/list.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						供应商名称：<input type="text" name="queryName" value="${queryName}"/>
					</td>
					<td>
						供应商编号：<input type="text" name="queryCode" value="${queryCode}"/>
					</td>
					<td>
						联系人：<input type="text" name="queryLinkMan" value="${queryLinkMan}"/>
					</td>
					<td>
						联系电话：<input type="text" name="queryPhone" value="${queryPhone}"/>
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
			<tag:auth code="infosub.add">
				<li><a class="add" href="${pageContext.request.contextPath}/infosub/add.do" target="dialog"  width="600" height="650" mask="true"><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="infosub.delete">
				<li><a class="delete" href="${pageContext.request.contextPath}/infosub/delete.do" target="selectedTodo" title="确定要删除吗?"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th width="50"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>供应商名称</th>
				<th>简称</th>
				<th>供应商编号</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>开户银行</th>
				<th>税号</th>
				<th>银行账号</th>
				<th>财务编码</th>
				<th>注册资本</th>
				<th>评级</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infosub" varStatus="s">
					<tr align="center">
						<td><input type="checkbox" name="ids" value="${infosub.id}"></td>
						<td>${infosub.name}</td>
						<td>${infosub.shortName}</td>
						<td>${infosub.code}</td>
						<td>${infosub.linkMan}</td>
						<td>${infosub.phone}</td>
						<td>${infosub.bank}</td>
						<td>${infosub.taxNo}</td>
						<td>${infosub.bankNo}</td>
						<td>${infosub.financeNo}</td>
						<td>${infosub.regCapital}&nbsp;万元</td>
						<td>${infosub.grade}</td>
						<td>
							<tag:auth code="infosub.delete">
								 <a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/infosub/delete.do?ids=${infosub.id}" class="btn btn-delete"></a>
							</tag:auth>
							<tag:auth code="infosub.edit">
								 <a title="修改" target="dialog"  width="600" height="650" mask="true"  href="${pageContext.request.contextPath}/infosub/edit.do?id=${infosub.id}" class="btn btn-edit"></a>
							</tag:auth>
							<tag:auth code="infosub.view">
								 <a title="查看" target="dialog"  width="600" height="650" mask="true" href="${pageContext.request.contextPath}/infosub/view.do?id=${infosub.id}" class="btn btn-ck"></a>
							</tag:auth>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
