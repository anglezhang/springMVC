<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<table class="table" width="100%" layoutH="171">
		<thead>
			<th align="center">服务订单编号</th>
			<th align="center">开单时间</th>
			<th align="center">车主姓名</th>
			<th align="center">车主电话</th>
			<th align="center">车牌号</th>
			<th align="center">接待人</th>
			<th align="center">服务位置</th>
			<th align="center">维修人</th>
			<th align="center">结算人</th>
			<th align="center">订单金额</th>
			<th align="center">服务进度</th>
			<th align="center">操作</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="customerEn" varStatus="s">
				<tr>
					<td align="center">${customerEn.cusEnstrustNum}</td>
					<td align="center"><fmt:formatDate value="${customerEn.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="center">${customerEn.carCusMap.infoCar.platenum}</td>
					<td align="center">${customerEn.carCusMap.infoCustome.customerName}</td>
					<td align="center">${customerEn.carCusMap.infoCustome.tel}</td>
					<td align="center">
						<c:forEach items="${emps}" var="infoEmp" varStatus="s">
							<c:if test="${customerEn.receptionist eq infoEmp.empId}" >${infoEmp.empName}(${infoEmp.paramConfig.name})</c:if> 
						</c:forEach>
					</td>
					<td align="center">${customerEn.infoDept.name}</td>
					<td align="center">
						<c:forEach items="${emps}" var="infoEmp" varStatus="s">
							<c:if test="${customerEn.receptionist eq infoEmp.empId}" >${infoEmp.empName}(${infoEmp.paramConfig.name})</c:if> 
						</c:forEach>
					</td>
					<td align="center">${customerEn.accountman.empName}</td>
					<td align="center"><fmt:formatNumber value="${customerEn.amount}" type="currency"/></td>
					<td align="center">
						<c:if test="${customerEn.cusEntats==0}" >进行中</c:if>
						<c:if test="${customerEn.cusEntats==1}" >结算(封单)</c:if>
						<c:if test="${customerEn.cusEntats==2}" >挂账</c:if>
						<c:if test="${customerEn.cusEntats==3}" >已结清</c:if>
					</td>
					<td align="center">
						<c:if test="${customerEn.cusEntats==0 && customerEn.fixplace==0}" >
							<tag:auth code="cusentrust.order.edit">
								<a title="修改" target="dialog" width="1200" height="600" mask="true" href="${pageContext.request.contextPath}/service/customerorder/edit.do?entrustId=${customerEn.entrustId}" rel="customerentrusedit" class="btn btn-edit"></a>
							</tag:auth>
							<tag:auth code="cusentrust.order.delete">
								<a title="删除" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/service/customerorder/delete.do?entrustId=${customerEn.entrustId}" class="btn btn-delete"></a>
							</tag:auth>
							<tag:auth code="cusentrust.order.amout">
								<a title="结算" target="dialog" width="500" height="300" mask="true" href="${pageContext.request.contextPath}/service/customerorder/amout.do?entrustId=${customerEn.entrustId}" rel="customerentrusedit" class="btn btn-qr"></a>
							</tag:auth>
						</c:if>
						<c:if test="${customerEn.cusEntats==1 && customerEn.fixplace==0}" >
							<tag:auth code="cusentrust.order.setdiscount">
								<a title="设置折扣系数" target="dialog" width="500" height="300" mask="true" href="${pageContext.request.contextPath}/service/customerorder/setdiscount.do?entrustId=${customerEn.entrustId}" rel="customersetnumber" class="btn btn-jx"></a>
							</tag:auth>
						</c:if>
						<tag:auth code="cusentrust.order.view">
							<a title="查看" target="dialog" width="1200" height="600" mask="true" href="${pageContext.request.contextPath}/service/customerorder/scan.do?entrustId=${customerEn.entrustId}" rel="customerentrusscan" class="btn btn-ck"></a>
						</tag:auth>	
						<tag:auth code="cusentrust.order.print">
							<a title="打印" href="${pageContext.request.contextPath}/service/customerorder/print.do?entrustId=${customerEn.entrustId}"target="view_window" class="btn btn-tj" ></a>
						</tag:auth>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>