<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/forushop/list.html">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" tabCurrenIndex name="currenIndex"  value="${currenIndex}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/forushop/list.html" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						服务单号：<input type="text" name="queryOrderNO" value="${queryOrderNO}"/>
					</td>
					<td>
						客户姓名：<input type="text" name="queryCusName" value="${queryCusName}"/>
					</td>
					<td>
						车牌号：<input type="text" name="queryPlatenum" value="${queryPlatenum}"/>
					</td>
				<tr>
				</tr>
					<td>
						服务类型：<select style="width:120px;" name="queryAppType">
									<option value="">全部</option>
									<c:forEach items="${serviceItem}" var="serviceType" varStatus="s">
										<option value="${serviceType.servicetypeId}" <c:if test="${queryAppType==serviceType.servicetypeId}" >selected="selected"</c:if>>${serviceType.servicename}</option>
									</c:forEach>
								</select>
					</td>
					<td>
						开单时间：<input type="text" name="queryOrderBeginTime" value="${queryOrderBeginTime}" class="date" datefmt="yyyy-MM-dd HH:mm:ss" />~<input type="text" name="queryOrderEndTime" value="${queryOrderEndTime}" class="date" datefmt="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						服务经度：<select name="queryStatus"  style="width:120px;">
									<option value="-1"  <c:if test="${empty queryStatus}" > selected="selected"</c:if> >全部</option>
									<option value="0"  <c:if test="${queryStatus==0}" > selected="selected"</c:if> >进行中</option>
									<option value="1" <c:if test="${queryStatus==1}" > selected="selected"</c:if> >结算(封单)</option>
									<option value="2" <c:if test="${queryStatus==2}" > selected="selected"</c:if> >挂账</option>
									<option value="3" <c:if test="${queryStatus==3}" > selected="selected"</c:if> >已结清</option>
								</select>
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
	<table class="table" width="100%" layoutH="121">
		<thead>
			<th align="center">服务订单编号</th>
			<th align="center">开单时间</th>
			<th align="center">车牌号</th>
			<th align="center">车主姓名</th>
			<th align="center">车主电话</th>
			<th align="center">服务接待人</th>
			<th align="center">订单金额</th>
			<th align="center">实收金额</th>
			<th align="center">结算状态</th>
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
					<td align="center"><fmt:formatNumber value="${customerEn.amount}" type="currency"/></td>
					<td align="center"><fmt:formatNumber value="${customerEn.shopmonney}" type="currency"/></td>
					<td align="center">
						<c:if test="${customerEn.cusEntats==0}" >进行中</c:if>
						<c:if test="${customerEn.cusEntats==1}" >结算(封单)</c:if>
						<c:if test="${customerEn.cusEntats==2}" >挂账</c:if>
						<c:if test="${customerEn.cusEntats==3}" >已结清</c:if>
					</td>
					<td align="center">
						<c:if test="${customerEn.cusEntats==0}" >
							<a title="确认结算" target="dialog" width="500" height="300" mask="true" href="${pageContext.request.contextPath}/fourshop/amout.html?entrustId=${customerEn.entrustId}" rel="fourshopamout" class="btn btn-rz"></a>
						</c:if>
						<c:if test="${customerEn.cusEntats==1}" >
							<a title="确认收款" target="dialog" width="500" height="300" mask="true" href="${pageContext.request.contextPath}/fourshop/pay.html?entrustId=${customerEn.entrustId}" rel="fourshopamout" class="btn btn-czmm"></a>
						</c:if>
						
						<a title="查看" target="dialog" width="1200" height="800" mask="true" href="${pageContext.request.contextPath}/forushop/scan.html?entrustId=${customerEn.entrustId}" rel="fourshopview" class="btn btn-ck"></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
