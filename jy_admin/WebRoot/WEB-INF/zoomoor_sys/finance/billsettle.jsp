<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/settlement/billsave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="entrustId" type="hidden" value="${entrustId}">
		<div class="pageFormContent" layoutH="60">
			<table >
				<tr>
					<td>
						<div class="unit">
							<label>订单服务项目数</label>
							<input type="text"  maxlength="30" readonly="readonly" value="${amout.serviceCount}"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>订单累计工时费</label>
							<input  type="text"  maxlength="30" readonly="readonly" value="${amout.workHourAmout}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>配件材料费</label>
							<input type="text"  maxlength="30" value="${amout.goodsAmout}" readonly="readonly"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>总费用</label>
							<input  type="text"  maxlength="30" readonly="readonly" value="${amout.sumAmout}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>折扣系数</label>
							<input type="text"  maxlength="30" readonly="readonly" value="${amout.discount}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>优惠金额</label>
							<input  type="text"  maxlength="30" readonly="readonly" value="${amout.saleAmout}"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>应收费用</label>
							<input type="text"  maxlength="30" readonly="readonly" value="${amout.payAmout}" size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>选择挂账客户</label>
							<input  type="text" id="customerName"   class="required" size="30" />
							<input  type="hidden" id="customerId"  name="billcustomerID"  size="30" />
							<a title="选择挂账客户" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/apporder/customerlookup.do" id="financebollsetle" lookupGroup="financebollsetle" rel="financebollsetle"></a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="settlement.billsave.do">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">确认挂账</button></div></div>
					</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button id="addCarinfobtn" type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	/**
	* 设置客户信息
	*/
	function setLookVal(customer)
	{
		for(k in customer)
		{
			var thisVal = customer[k];
			if(thisVal.toString().indexOf(".") != -1)
			{
				thisVal =  thisVal.toString().substr(0,thisVal.toString().indexOf(":"));
			}
			$("#" + k).val(thisVal);
		}
	
	}
</script>