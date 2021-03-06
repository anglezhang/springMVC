<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageContent">
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr>
				<th align="center">服务单号</th>
				<th align="center">车牌号</th>
				<th align="center">预约时间</th>
				<th align="center">预约状态</th>
				<th align="center">指派店铺</th>
				<th align="center">客户姓名</th>
				<th align="center">客户电话</th>
				<th align="center">来源</th>
				<th align="center">开单时间</th>
				<th align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="customerAppoit" varStatus="s">
				<tr>
					<td>
						${customerAppoit.cusorderNO}
					</td>
					<td>
						${customerAppoit.infoCar.platenum}
					</td>
					<td>
						<fmt:formatDate value="${customerAppoit.appoittime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<c:if test="${customerAppoit.cus==0}" >待确认</c:if>
						<c:if test="${customerAppoit.cus==1}" >已确认</c:if>
						<c:if test="${customerAppoit.cus==2}" >已履约</c:if>
						<c:if test="${customerAppoit.cus==4}" >已取消</c:if>
					</td>
					<td>
						${customerAppoit.infoDept.name}
					</td>
					<td>
						${customerAppoit.infoCustomer.customerName}
					</td>
					<td>
						${customerAppoit.infoCustomer.tel}
					</td>
					<td>
						<c:if test="${customerAppoit.source==0}">微信</c:if>
						<c:if test="${customerAppoit.source==1}">PC后台</c:if>
					</td>
					<td>
						<fmt:formatDate value="${customerAppoit.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<tag:auth code="app.order.scan">
							<a title="查看" target="dialog" width="1100" height="720" mask="true" href="${pageContext.request.contextPath}/app/orderscan.do?cusappoitId=${customerAppoit.cusappoitId}" rel="appcustomerScan" class="btn btn-ck"></a>
						</tag:auth>	
						<c:if test="${customerAppoit.cus==0}">
							<tag:auth code="app.order.edit">
								<a title="修改" target="dialog" width="1100" height="720" mask="true" href="${pageContext.request.contextPath}/app/orderedit.do?cusappoitId=${customerAppoit.cusappoitId}" rel="appcustomerEdit" class="btn btn-edit"></a>
							</tag:auth>	
							<tag:auth code="app.order.cancel">
								<a title="取消订单" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/app/orderdcancle.do?cusappoitId=${customerAppoit.cusappoitId}" class="btn btn-off"></a>
							</tag:auth>
							<tag:auth code="app.order.Assign">
								<a title="立即指派" target="dialog" width="1100" height="720" mask="true" mask="true" rel="apporderAssgin" href="${pageContext.request.contextPath}/app/Assign.do?cusappoitId=${customerAppoit.cusappoitId}" class="btn btn-cy"></a>
							</tag:auth>
							<tag:auth code="app.order.delete">
								<a title="删除" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/app/orderdelete.do?cusappoitId=${customerAppoit.cusappoitId}" class="btn btn-delete"></a>
							</tag:auth>	
						</c:if> 
						<c:if test="${customerAppoit.cus==1}">
							<tag:auth code="order.create.custome">
								<a title="创建服务订单" target="dialog" width="1200" height="600" mask="true" href="${pageContext.request.contextPath}/customer/createorder.do?cusappoitId=${customerAppoit.cusappoitId}" rel="appcustomerEdit" rel="creatcustomentrust" class="btn btn-sh"></a>
							</tag:auth>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../../include/page.jsp" %>
</div>
