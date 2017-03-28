<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/finance/cusenlist.do">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/finance/cusenlist.do" method="post">
			<input type="hidden" name="pageNum" value="${pager.pageNum}" />
			<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							客户姓名：<input type="text" name="queryCarInf" value="${queryCarInf}"/>
						</td>
						<td>
							车牌号：<input type="text" name="queryPlatenum" value="${queryPlatenum}"/>
						</td>
						<td>
							结算状态：<select name="queryStatu"  style="width:152px;height:21px">
										<option value=""  <c:if test="${empty queryStatu}" > selected="selected"</c:if> >全部</option>
										<option value="1" <c:if test="${queryStatu==1}" > selected="selected"</c:if> >结算(封单)</option>
										<option value="2" <c:if test="${queryStatu==2}" > selected="selected"</c:if> >挂账</option>
										<option value="3" <c:if test="${queryStatu==3}" > selected="selected"</c:if> >已结清</option>
									</select>
						</td>
						<td>
							服务位置：<select name="queryFixplace"  style="width:152px;height:21px">
											<option value="0" <c:if test="${queryFixplace eq 0}" > selected="selected"  </c:if> >${user.infoEmp.infoDept.name}</option>
											<c:forEach items="${addrList}" var="addr" varStatus="s">
												<option value="${addr.addrId}" <c:if test="${queryFixplace==addr.addrId}" > selected="selected" </c:if> >${addr.address} </option>
											</c:forEach>
										</select>
						</td>
						<td>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
						</td>
					</tr>
					<tr>
						<!-- <td>
							服务类型：<select style="width:152px;height:21px" name="serviceType">
										<option value="" <c:if test="${empty serviceType}">selected="selected"</c:if> >全部</option>
										<c:forEach items="${services}" var="service" varStatus="s">
											<option value="${service.servicetypeId}" <c:if test="${serviceType==service.servicetypeId}" >selected="selected"</c:if>>${service.servicename}</option>
										</c:forEach>
									</select>
						</td>
						<td>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
						</td> -->
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="85">
		<thead>
			<th align="center">结算单号</th>
			<th align="center">服务订单号</th>
			<th align="center">开单时间</th>
			<th align="center">车牌号</th>
			<th align="center">客户姓名</th>
			<th align="center">客户电话</th>
			<th align="center">服务接待人</th>
			<th align="center">订单金额</th>
			<th align="center">实收金额</th>
			<th align="center">结算状态</th>
			<th align="center">操作</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="customer" varStatus="s">
				<tr>
					<td>${customer.settlement}</td>
					<td>${customer.cusEnstrustNum}</td>
					<td><fmt:formatDate value="${customer.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${customer.carCusMap.infoCar.platenum}</td>
					<td>${customer.carCusMap.infoCustome.customerName}</td>
					<td>${customer.carCusMap.infoCustome.tel}</td>
					<td>
						<c:forEach items="${emps}" var="infoEmp" varStatus="s">
							<c:if test="${customer.receptionist eq infoEmp.empId}" >${infoEmp.empName}</c:if> 
						</c:forEach>
					</td>
					<td><fmt:formatNumber value="${customer.amount}" type="currency"/></td>
					<td><fmt:formatNumber value="${customer.reallymoney}" type="currency"/></td>
					<td>
						<c:if test="${customer.cusEntats==0}" >进行中</c:if>
						<c:if test="${customer.cusEntats==1}" >结算(封单)</c:if>
						<c:if test="${customer.cusEntats==2}" >挂账</c:if>
						<c:if test="${customer.cusEntats==3}" >已结清</c:if>
					</td>
					<td>
						<c:if test="${customer.cusEntats==1}" >
							<tag:auth code="settlement.bill.do">
								<a title="设置挂账" target="dialog" width="800" height="600" mask="true" href="${pageContext.request.contextPath}/settlement/bill.do?entrustId=${customer.entrustId}" rel="financebill" class="btn btn-qr"></a>
							</tag:auth>
							<tag:auth code="settlement.settle.do">
								<a title="设置结清" target="dialog" width="800" height="600" mask="true" href="${pageContext.request.contextPath}/settlement/settle.do?entrustId=${customer.entrustId}" rel="financesetle" class="btn btn-sljg"></a>
							</tag:auth>
						</c:if>
						<c:if test="${customer.cusEntats==2}" >
							<tag:auth code="settlement.billsettle">
								<a title="设置挂账结清" target="dialog" width="800" height="600" mask="true" href="${pageContext.request.contextPath}/settlement/billsettle.do?entrustId=${customer.entrustId}" rel="financesetle" class="btn btn-tjjgxm"></a>
							</tag:auth>
						</c:if>
						<tag:auth code="cusentrust.order.view">
							<a title="查看" target="dialog" width="1200" height="600" mask="true" href="${pageContext.request.contextPath}/service/customerorder/scan.do?entrustId=${customer.entrustId}" rel="customerentrusscan" class="btn btn-ck"></a>
						</tag:auth>	
						<tag:auth code="settlement.billsetprint">
							<a title="打印" href="${pageContext.request.contextPath}/service/finance/print.do?entrustId=${customer.entrustId}"target="view_window" class="btn btn-tj" ></a>
						</tag:auth>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>