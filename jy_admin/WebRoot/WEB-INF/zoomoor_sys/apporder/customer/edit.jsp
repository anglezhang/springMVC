<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/customer/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" name="dialogId" value="${dialogId}">
		<input type="hidden" name="customerId" value="${infoCustome.customerId}">
		<div class="pageFormContent" layoutH="50">
			<div class="unit">
				<label>客户姓名：</label>
				<input id="customerName" type="text" name="customerName" maxlength="30" class="required"  size="30" value="${infoCustome.customerName}" />
			</div>
			<div class="unit">
				<label>客户类型：</label>
				<input type="radio" name="custype"  value="0" onclick="setNone()" <c:if test="${infoCustome.custype==0}" >checked="true" </c:if> size="30" /> 个人
				<input type="radio" name="custype"  value="1" onclick="setDisplay()" <c:if test="${infoCustome.custype==1}" >checked="true" </c:if> size="30" /> 机构
			</div>
			<div class="unit">
				<label>电话：</label>
				<input id="tel" type="text" name="tel" maxlength="20"  class="required mobile"  size="30" value="${infoCustome.tel}" />
			</div>
			<div id="empinf_div"  <c:if test="${infoCustome.custype==2}" > style="display:none;" </c:if>  >
				<div class="unit">
					<label>性别：</label>
					<input type="radio" name="gender" class="required" <c:if test="${!infoCustome.gender}" >  checked="true" </c:if>value="0">女
					<input type="radio" name="gender" class="required" <c:if test="${infoCustome.gender}" >  checked="true" </c:if> value="1">男
				</div>
				<div class="unit">
					<label>收入水平：</label>
					<select style="width:183px;height:21px" name="income">
						<option value="0" <c:if test="${infoCustome.income==0}" >  selected="selected" </c:if>>1000~3000</option>
						<option value="1" <c:if test="${infoCustome.income==1}" >  selected="selected" </c:if>>3000~5000</option>
						<option value="2" <c:if test="${infoCustome.income==2}" >  selected="selected" </c:if>>5000~8000</option>
						<option value="3" <c:if test="${infoCustome.income==3}" >  selected="selected" </c:if>>8000以上</option>
					</select>
				</div>
			</div>
			<div style="display:none">
				<label>邮箱：</label>
				<input id="eamil" type="text"  maxlength="50"   size="30" />
			</div>
			<div id="provincediv" class="unit" data-val="${infoCustome.province}" >
				<label >所在省份：</label>
				<select  id="province" name="province" style="width:183px;height:21px"></select>
			</div>
			<div id="citydiv" class="unit" data-val="${infoCustome.city}">
				<label>所在城市：</label>
				<select  id="city" name="city" style="width:183px;height:21px"></select>
			</div>
			<div style="display:none">
				<label>所在地区：</label>
				<select  id="area" style="width:183px;height:21px"></select>
			</div>
			<div class="unit">
				<label>详细地址：</label>
				<input name="detaladdress" type="text"  size="50" maxlength="50" value="${infoCustome.detaladdress}">
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script src="${pageContext.request.contextPath}/js/area.js" type="text/javascript"></script>
<script type="text/javascript">
	function dialogAjaxDone2(json){
		DWZ.ajaxDone(json);
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
			$.pdialog.closeCurrent();
			$("#customerlookupsubmit").trigger('click');
		}
	}
	$(function()
	{
		_init_area();
		var defutsf = $("#provincediv").attr("data-val");
		var defutcs = $("#citydiv").attr("data-val");
		var pSelect = "option[value='" + defutsf + "']";
		var cSelect = "option[value='" + defutcs + "']";
		$("#province").find(pSelect).prop("selected",true);
		change(1);
		
		$("#city").find(cSelect).prop("selected",true);
		//$("#area").find("option[value='" + defut-cs + "']").prop("selected",true);
		var custype = $("[name='custype']:checked").val();
		if(custype==1)
			$("#empinf_div").hide();
	});
	/**
	* 描述:设置隐藏
	*/
	function setDisplay()
	{
		$("#empinf_div").hide();
	}
	function setNone()
	{
		$("#empinf_div").show();
	}
</script>