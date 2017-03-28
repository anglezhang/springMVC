<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/depot/summarylist.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryAllocationNo" value="${queryAllocationNo}" />
	<input type="hidden" name="queryEmpName" value="${queryEmpName}" />
	<input type="hidden" name="queryStartDate" value="${queryStartDate}" />
	<input type="hidden" name="queryEndDate" value="${queryEndDate}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/depot/summarylist.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						 盘点单号：<input type="text" name="queryAllocationNo" value="${queryAllocationNo}"/>
					</td>
					<%-- <td>
						创建人：<input type="text" name="queryEmpName" value="${queryEmpName}"/>
					</td> --%>
					<td>
						创建时间：<input type="text" class="date" name="queryStartDate" value="${queryStartDate}"/>-<input type="text" class="date"  name="queryEndDate" value="${queryEndDate}"/>
					</td>
					<!-- <td>
						处理状态：<select name="status">
									<option value="">全部</option>
									<option value="1">已创建</option>
									<option value="2">已清点</option>
									<option value="3">已同步</option>
								</select>
					</td> -->
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th>盘点单号</th>
				<!-- <th>创建人</th> -->
				<th>盘点仓库</th>
				<th>盘点库位</th>
				<th>创建时间</th>
				<!-- <th>处理状态</th> -->
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="summaryList" varStatus="s">
					<tr align="center">
						<td>${summaryList.BNo}</td>
						<!-- <td></td> -->
						<td>${summaryList.deptName}</td>
						<td>${summaryList.positionName}</td>
						<td>${fn:substring(summaryList.checkDate,0,19)}</td>
						<!-- <td>已创建</td> -->
						<td>
							<tag:auth code="check.summary.view">
								 <a title="查看详细" target="dialog" width="1300" height="600" mask="true" rel="order_edit" href="${pageContext.request.contextPath}/depot/checkentering.do?checkNo=${summaryList.BNo}&lookType=1" class="btn btn-ck"></a>
							</tag:auth>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../../include/page.jsp" %>
</div>
