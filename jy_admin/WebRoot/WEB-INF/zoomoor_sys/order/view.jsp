<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
		<form method="post" id="order_form" action="${pageContext.request.contextPath}/order/audit.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden"  name="orderId" value="${orderPur.purOrderId }"/>
		<input type="hidden"  name="auditType" value="${auditType }"/>
		<div class="unit">
			<table class="list" width="100%" >
				<tr>
					<td>供应商名称：</td>
					<td>
						${orderPur.infoSup.name }
					</td>
					<td>操作人</td>
					<td>${orderPur.infoEmp.empName }</td>
				</tr>
				
				<tr>
					<td>付款方式：</td>
					<td>
							<c:forEach items="${paramConfigList }" var="config">
								<c:if test="${orderPur.paramConfig.unitId==config.unitId }">${config.name }</c:if>
							</c:forEach>
					</td>
					<td>采购备注</td>
					<td>${orderPur.memo }</td>
				</tr>
				<c:if test="${empty status }">
				<c:forEach items="${auditList }" var="audit">
				<tr>
				<td>
				<c:if test="${audit.auditType==1 }">采购审核：</c:if>
				<c:if test="${audit.auditType==2 }">财务审核：</c:if>
				<c:if test="${audit.auditType==3 }">总经理审核：</c:if>
				</td>
				<td>
					<c:if test="${audit.status==1 }"> 通过</c:if>
					<c:if test="${audit.status==2 }"> 不通过</c:if>
					<c:if test="${audit.status==0 }"> 未审核</c:if>
				</td>
				<td>审核备注：</td>
				<td>${audit.memo }</td>
			</tr>
			</c:forEach>
			</c:if>
				<c:if test="${!empty status }">
				<tr>
				<td>采购审核：</td>
				<td>
					
					<select <c:if test="${ auditType==1}">  name="status"</c:if>  <c:if test="${ auditType==2||auditType==3}">disabled="disabled"</c:if>>
						<option value="1">通过</option>
						<option value="2">不通过</option>
					</select>
				</td>
				<td>审核备注：</td>
				<td><input type="text" value="${cgaudit }" <c:if test="${ auditType==1}"> name="memo"</c:if> size="30" <c:if test="${auditType==2||auditType==3}">readonly="readonly"</c:if>/></td>
			</tr>
			<tr>
				<td>财务审核：</td>
				<td>
					<select <c:if test="${ auditType==2}">  name="status"</c:if> <c:if test="${ auditType==1||auditType==3}">disabled="disabled"</c:if>>
						<c:if test="${ auditType==1}">
							<option value=" ">未审核</option>
						</c:if>
						<option value="1">通过</option>
						<option value="2">不通过</option>
					</select>
				</td>
				<td>审核备注：</td>
				<td><input type="text" value="${cwaudit }" <c:if test="${ auditType==2}"> name="memo"</c:if>  size="30"   <c:if test="${ auditType==1||auditType==3}">readonly="readonly"</c:if> /></td>
			</tr>
			<tr>
				<td>总经理审核：</td>
				<td>
					<select <c:if test="${ auditType==3}">  name="status"</c:if> <c:if test="${ auditType==1||auditType==2}">disabled="disabled"</c:if>>
						<c:if test="${ auditType==1||auditType==2}">
							<option value=" ">未审核</option>
						</c:if>
						<option value="1">通过</option>
						<option value="2">不通过</option>
					</select>
				</td>
				<td>审核备注：</td>
				<td><input type="text"  <c:if test="${ auditType==3}"> name="memo"</c:if> size="30"  <c:if test="${ auditType==1||auditType==2}">readonly="readonly"</c:if>/></td>
			</tr>
			</c:if>
			</table>
		</div>
		<div class="pageFormContent" layoutH="175">
			<table class="list" width="100%" >
				<thead>
					<tr>
						<th>配件编号</th>
						<th>配件名称</th>
						<th>订购最小单位数</th>
						<th>采购数量</th>
						<th>最小计量单位</th>
						<th>采购单位</th>
						<th>采购不含税单价</th>
						<th>税率</th>
						<th>采购含税单价</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<th>采购不含税金额</th>
						<th>税额</th>
						<th>采购含税金额</th>
					</tr>
				</thead>
				<tbody id="goodtbody">
					<c:forEach items="${orderList}" var="orderList" varStatus="status">
						<tr>
							<td>${orderList.infoGoods.goodsCode}</td>
							<td>${orderList.infoGoods.name}</td>
							<td>${orderList.orderSmallNum }</td>
							<td>${orderList.orderNum }</td>
							<td>
								${orderList.unitName }
							</td>
							<td>
								<c:forEach items="${unitConfigList }" var="unitconfig">
									<c:if test="${orderList.orderUnit==unitconfig.unitId }">${unitconfig.name }</c:if>
								</c:forEach>
							</td>
							<td>${orderList.orderPrice }</td>
							<td>${orderList.taxRate }</td>
							<td><fmt:formatNumber value="${orderList.taxtRatePrice }" pattern="#0.00"/></td>
							<td>${orderList.infoGoods.goodsBrand }</td>
							<td>${orderList.infoGoods.standard }</td>
							<td><fmt:formatNumber value="${orderList.nTaxRateCount }" pattern="#0.00"/></td>
							<td><fmt:formatNumber value="${orderList.taxRateCount }" pattern="#0.00"/></td>
							<td><fmt:formatNumber value="${orderList.ytaxRateCount }" pattern="#0.00"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
			<c:if test="${!empty status }">
				<tag:auth code="order.audit">
				<li>
					<div class="button"><div class="buttonContent"><button type="submit" class="button">审核</button></div></div>
				</li>
				</tag:auth>
			</c:if>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
