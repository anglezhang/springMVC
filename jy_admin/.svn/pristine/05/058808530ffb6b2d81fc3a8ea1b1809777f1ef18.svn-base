<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/service/customerorder/goamout.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="60">
			<input name="accountmanID" type="hidden" value="${accountmanID}">
			<input name="entrustId" type="hidden" value="${entrustId}">
			<table >
				<tr>
					<td>
						<div class="unit">
							<label>订单服务项目数</label>
							<input type="text" name="serviceCount" maxlength="30" readonly="readonly" value="${amout.serviceCount}"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>订单累计工时费</label>
							<input  type="text" name="workHourAmout" maxlength="30" readonly="readonly" value="${amout.workHourAmout}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>配件材料费</label>
							<input type="text" name="goodsAmout" maxlength="30" value="${amout.goodsAmout}" readonly="readonly"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>总费用</label>
							<input id="customerName" type="text" name="customerName" maxlength="30" readonly="readonly" value="${amout.sumAmout}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>折扣系数</label>
							<input type="text" name="discount" maxlength="30" readonly="readonly" value="${amout.discount}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>优惠金额</label>
							<input id="customerName" type="text" name="customerName" maxlength="30" readonly="readonly" value="0"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>应收费用</label>
							<input id="customerName" type="text" name="customerName" maxlength="30" readonly="readonly" value="${amout.payAmout}" size="30" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="customer.entrust.confirm">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">确认结算</button></div></div>
					</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button id="addCarinfobtn" type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>