<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/infosub/lookup.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						供应商名称：<input type="text" name="queryName" value="${queryName}"/>
					</td>
					<td>
						供应商编号：<input type="text" name="queryCode" value="${queryCode}"/>
					</td>
					<td><div class="buttonActive"><div class="buttonContent"><button id="infosub_s_b" type="submit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="infosub.add">
				<li><a class="add" href="${pageContext.request.contextPath}/infosub/add.do" target="dialog" width="600" height="650" mask="true" rel="infosubadd"><span>添加</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="84" >
		<thead>
			<th align="center">供应商名称</th>
			<th align="center">供应商编号</th>
			<th align="center">联系人</th>
			<th align="center">联系电话</th>
			<th align="center">注册资本</th>
			<th align="center">评级</th>
			<th align="center">操作</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infosub" varStatus="status">
				<tr>
					<td align="center">${infosub.name}</td>
					<td align="center">${infosub.code}</td>
					<td align="center">${infosub.linkMan}</td>
					<td align="center">${infosub.phone}</td>
					<td align="center">${infosub.regCapital}</td>
					<td align="center">${infosub.grade}</td>
					<td align="center">
						<a class="btn btn-xzdh" href="javascript:$.bringBack({id:'${infosub.id}',name:'${infosub.name}'})"  multLookup="infoSup"  title="选择带回"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>