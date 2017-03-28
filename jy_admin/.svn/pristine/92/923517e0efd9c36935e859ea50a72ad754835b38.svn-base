<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/settlement/billsettlesave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="entrustId" type="hidden" value="${entrustId}">
		<div class="pageFormContent" layoutH="60">
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
							<input id="customerName" type="text" name="customerName" maxlength="30" readonly="readonly" value="${amout.saleAmout}"  size="30" />
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
				<tr>
					<td>
						<div class="unit">
							<label>挂账客户</label>
							<input  type="text"  maxlength="7" readonly="readonly" value="${cus.customerName}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>操作员</label>
							<input  type="text"  maxlength="7" readonly="readonly" value="${user.infoEmp.empName}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>操作时间</label>
							<input  type="text" value="${fn:substring(now,0,19)}"  readonly="readonly" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>确认收款额度</label>
							<input  type="text" name="reallymoney" maxlength="7"  class="required number" size="30" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="settlement.billsave.do">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">确认挂账结清</button></div></div>
					</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button id="addCarinfobtn" type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>