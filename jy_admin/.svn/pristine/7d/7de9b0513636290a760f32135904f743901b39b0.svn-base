<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/order/list.do?status=${status}&auditType=${auditType}">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryOrderNo" value="${queryOrderNo}" />
	<input type="hidden" name="queryEmpName" value="${queryEmpName}" />
	<input type="hidden" name="querySubName" value="${querySubName}" />
	<input type="hidden" name="queryStatus" value="${queryStatus}" />
	<input type="hidden" name="queryStartDate" value="${queryStartDate}" />
	<input type="hidden" name="queryEndDate" value="${queryEndDate}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/order/list.do?status=${status}&auditType=${auditType}" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						采购单号：<input type="text" name="queryOrderNo" value="${queryOrderNo}"/>
					</td>
					<td>
						操作员：<input type="text" name="queryEmpName" value="${queryEmpName}"/>
					</td>
					<td>
						供应商名称：<input type="text" name="querySubName" value="${querySubName}"/>
					</td>
					<td>
						采购状态：
						<select name="queryStatus">
							
							
							<option value="">全部状态</option>
							<option value="0" <c:if test="${queryStatus==0 }">selected</c:if>>未审核</option>
							<option value="1" <c:if test="${queryStatus==1 }">selected</c:if>>通过</option>
							<option value="2" <c:if test="${queryStatus==2 }">selected</c:if>>不通过</option>
							
						</select>
					</td>
					<td>
						开单日期：<input type="text" class="date" name="queryStartDate" value="${queryStartDate}"/>-<input type="text" class="date"  name="queryEndDate" value="${queryEndDate}"/>
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
			<c:if test="${status!=1|| empty status}">
			<tag:auth code="order.add">
				<li><a class="add" href="${pageContext.request.contextPath}/order/add.do" target="dialog"  width="1300" height="600" mask="true" rel="order_add"><span>创建采购单</span></a></li>
			</tag:auth>
			</c:if>
			<li style="display: none"><a class="add" href="${pageContext.request.contextPath}/order/export.do" target="dwzExport" targetType="dialog"  title="确实要导出这些记录吗?"><span>导出excel</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th>采购单号</th>
				<th>操作人</th>
				<th>开单日期</th>
				<th>供应商名称</th>
				<th>不含税总金额</th>
				<th>含税总金额</th>
				<th>采购状态</th>
				<th>实际入库总金额</th>
				<th>备注</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="order" varStatus="s">
					<tr align="center">
						<td>${order.purOrderNo}</td>
						<td>${order.infoEmp.empName}</td>
						<td>${fn:substring(order.purOrderDate,0,19)}</td>
						<td>${order.infoSup.name}</td>
						<td><fmt:formatNumber value="${order.countPrice }" type="currency"/></td>
						<td><fmt:formatNumber value="${order.countTaxPrice }" type="currency"/></td>
						<td>
							<c:if test="${ order.auditType==1}">采购审核</c:if>
							<c:if test="${ order.auditType==2}">财务审核</c:if>
							<c:if test="${ order.auditType==3}">总经理审核</c:if>
							<c:if test="${ order.status==0}">未审核</c:if>
							<c:if test="${ order.status==1}">通过</c:if>
							<c:if test="${ order.status==2}">不通过</c:if>
						</td>
						<td><c:if test="${order.depotNum>0 }"><fmt:formatNumber value="${order.depotNum}" type="currency"/></c:if><c:if test="${order.depotNum<=0 }">暂未入库</c:if></td>
						<td>${order.memo}</td>
						<td>
							<c:if test="${status!=1|| empty status}">
							<c:if test="${order.status==0 }">
								<c:if test="${order.infoEmp.empId==sysUser.infoEmp.empId }">
									<tag:auth code="order.delete">
										 <a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/order/delete.do?orderId=${order.purOrderId}" class="btn btn-delete"></a>
									</tag:auth>
									<tag:auth code="order.edit">
										 <a title="修改" target="dialog" width="1300" height="600" mask="true" rel="order_edit" href="${pageContext.request.contextPath}/order/edit.do?orderId=${order.purOrderId}" class="btn btn-edit"></a>
									</tag:auth>
								</c:if>
							</c:if>
							<tag:auth code="order.view">
								 <a title="查看详细" target="dialog" width="1300" height="600" mask="true" rel="order_view" href="${pageContext.request.contextPath}/order/view.do?orderId=${order.purOrderId}&status=${status}" class="btn btn-ck"></a>
							</tag:auth>
							</c:if>
							<c:if test="${status==1}">
								<c:if test="${order.status==0 || order.auditType==(auditType-1)}">
									<a title="审核" target="dialog" width="1300" height="600" mask="true" rel="order_edit" href="${pageContext.request.contextPath}/order/view.do?orderId=${order.purOrderId}&status=${status}&auditType=${auditType}" class="btn btn-sh"></a>
								</c:if>
								<c:if test="${order.status!=0&& order.auditType!=(auditType-1) }">
									<tag:auth code="order.view">
								 		<a title="查看详细" target="dialog" width="1300" height="600" mask="true" rel="order_view" href="${pageContext.request.contextPath}/order/view.do?orderId=${order.purOrderId}&status=${status}" class="btn btn-ck"></a>
									</tag:auth>
								</c:if>
							</c:if>
							<tag:auth code="order.print">
								<a title="打印" target="view_window"  rel="order_price" href="${pageContext.request.contextPath}/order/print/${order.purOrderId}" class="btn btn-tj"></a>
							</tag:auth>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
