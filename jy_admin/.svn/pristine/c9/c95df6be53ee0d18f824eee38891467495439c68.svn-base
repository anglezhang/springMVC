<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/customer/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryName" value="${queryName}" />
	<input type="hidden" name="querytelephone" value="${querytelephone}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/customer/list.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						客户姓名：<input type="text" name="queryName" value="${queryName}" placeholder="客户姓名"/>
					</td>
					<td>
						客户电话：<input type="text" name="querytelephone" value="${querytelephone}" placeholder="客户电话"/>
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
	<table class="table" width="100%" layoutH="85" >
		<thead>
			<th align="center">客户姓名</th>
			<th align="center">客户电话</th>
			<th align="center">性别</th>
			<!-- <th align="center">微信Id</th>
			<th align="center">pc端用户名</th>
			<th align="center">邮箱</th>
			<th align="center">客户积分</th> -->
			<th align="center">客户类型</th>
			<th align="center">收入水平</th>
			<th align="center">所在省</th>
			<th align="center">所在市</th>
			<th align="center">详细地址</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infoCustome" varStatus="status">
				<tr>
					<td align="center">${infoCustome.customerName}</td>
					<td align="center">${infoCustome.tel}</td>
					<td align="center">
						<c:if test="${!infoCustome.gender}">
							女
						</c:if>
						<c:if test="${infoCustome.gender}">
							男
						</c:if>
					</td>
					<!-- <td align="center">${infoCustome.weixin}</td>
					<td align="center">${infoCustome.pcUsername}</td>
					<td align="center">${infoCustome.eamil}</td>
					<td align="center">${infoCustome.intergral}</td> -->
					<td align="center">
						<c:if test="${infoCustome.custype==0}">个人</c:if>
						<c:if test="${infoCustome.custype==1}">机构</c:if>
					</td>
					<td align="center">
						<c:if test="${infoCustome.income==0}">1000~3000</c:if>
						<c:if test="${infoCustome.income==1}">3000~5000</c:if>
						<c:if test="${infoCustome.income==2}">5000~8000</c:if>
						<c:if test="${infoCustome.income==3}">8000以上</c:if>
					</td>
					<td align="center">${infoCustome.province}</td>
					<td align="center">${infoCustome.city}</td>
					<td align="center">${infoCustome.detaladdress}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../../include/page.jsp" %>
</div>