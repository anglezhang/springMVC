<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/service/customerorder/discountsave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="entrustId" type="hidden" value="${cusEntrust.entrustId}">
		<input name="discounter" type="hidden" value="${discounter.infoEmp.empId}">
		<input id="baseAmount" type="hidden" value="${cusEntrust.amount}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>车主姓名</label>
				<input type="text"  maxlength="30" readonly="readonly" value="${cusEntrust.carCusMap.infoCar.platenum}"  size="30" />
			</div>
			<div class="unit">
				<label>车牌号</label>
				<input type="text"  maxlength="30" readonly="readonly" value="${cusEntrust.carCusMap.infoCustome.customerName}"  size="30" />
			</div>
			<div class="unit">
				<label>总费用</label>
				<input type="text" id="amount" name="amount" maxlength="30" readonly="readonly" value="${cusEntrust.amount}"  size="30" />
			</div>
			<div class="unit">
				<label>折扣系数</label>
				<input type="text" id="discount" onkeyup="checkAmout()" name="discount" maxlength="30" class="required number" value="${cusEntrust.discount}"  size="30" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="customer.entrust.confirm">
					<c:if test="${cusEntrust.discounter eq 0}" >
						<li>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div>
						</li>
					</c:if>
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
	* 折扣价格计算
	* 1.折扣不能大于1
	* 2.总费用=当前总费用*折扣系数
	*/
	function checkAmout()
	{
		var baseAmount = parseFloat($("#baseAmount").val());
		var discount = parseFloat($("#discount").val());
		if(discount > 1 || isEmptyObj(discount))
		{
			var errorMsg =  new Object();
			errorMsg.statusCode = 300;
			errorMsg.message = "折扣系数不能大于1或者为空";
			dialogAjaxDone(errorMsg);
			return;
		}
		var amount = baseAmount * discount;
		$("#amount").val(Math.round(amount.toFixed(2)*100)/100);
	}
</script>