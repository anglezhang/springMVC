<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/app/orderupdate.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input  name="source" type="hidden"  value="1"  />
		<input id="carId" name="infoCar.carId" type="hidden" value="${customerApp.infoCar.carId}" />
		<input  name="cusappoitId" type="hidden" value="${customerApp.cusappoitId}" />
		<input  name="infoBrand.id" type="hidden" value="${customerApp.infoCar.infoBrand.id}" />
		<input  id="customereidtappoittypes" type="hidden"  value="${customerApp.appoittype}"  />
		<div class="pageFormContent" layoutH="80">
			<h3 style="margin:10px 0 10px 5px;">客户信息</h3>
			<table class="table" width="100%">
				<tr>
					<td width="100">客户车辆：</td>
					<td >
						<a id="carInfoLookup" title="客户车辆" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/apporder/carlookup.do?customerId=${customerApp.infoCustomer.customerId}" lookupGroup="editcarInfoLookup" style="margin-right:190px;float:right" rel="editappordercar"></a>
						<input id="platenum" name="infoCar.platenum" type="text"  maxlength="30" onchange="clealCarInf()"  class="required" readonly="readonly" size="30" value="${customerApp.infoCar.platenum}" />
					</td>
					<td width="100">客户姓名：</td>
					<td width="400">
						<input id="customerName" type="text"  maxlength="30" class="required" readonly="readonly" size="30" value="${customerApp.infoCustomer.customerName}" />
						<input id="customerId" type="hidden" name="infoCustomer.customerId" maxlength="30" value="${customerApp.infoCustomer.customerId}" />
						<a id="editappordercustomer" title="选择客户" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/apporder/customerlookup.do?carId=${customerApp.infoCar.carId}" lookupGroup="editappordercustomerLookup" rel="editappordercustomer"></a>
					</td>
				</tr>
			</table>
			<table class="table" width="100%">
				<tr>
					<td width="100">客户电话</td>
					<td width="400" id="tel">
						${customerApp.infoCustomer.tel}
					</td>
					<td width="100">品牌</td>
					<td id="brandName">
						${customerApp.infoCar.infoBrand.name}
					</td>
				</tr>
				<tr>
					<td>上次投保时间</td>
					<td id="nextins">
						<fmt:formatDate value="${customerApp.infoCar.nextins}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>购车日期</td>
					<td id="buytime">
						<fmt:formatDate value="${customerApp.infoCar.buytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>挂牌时间</td>
					<td id="listingtime"> 
						<fmt:formatDate value="${customerApp.infoCar.listingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>行驶里程</td>
					<td id="mileage"> 
						${customerApp.infoCar.mileage}
					</td>
				</tr>
				<tr>
					<td>上次进店时间</td>
					<td>
						${customerApp.lastTime}
					</td>
					<td>上次保养日期</td>
					<td>
						<fmt:formatDate value="${customerApp.infoCar.nextmain}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</table>
			<h3 style="margin:10px 0 10px 5px;">预约基本信息</h3>
			<table class="table" width="100%">
				<tbody>
					<tr>
						<td width="10%">预约类别</td>
						<td width="90%" colspan="3">
							<c:forEach items="${serviceItem}" var="serviceType" varStatus="s">
								<input type="checkbox" name="appoittypes" value="${serviceType.servicetypeId}">${serviceType.servicename}
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>预约时间</td>
						<td colspan="3">
							<input name="appoittime" type="text" class="date" maxlength="30" class="required" readonly="readonly" size="30" datefmt="yyyy-MM-dd HH:mm:ss" value="${fn:substring(customerApp.appoittime,0,19)}" />
						</td>
					</tr>
					<tr>
						<td>开单</td>
						<td>${user.infoEmp.empName}</td>
						<td>开单时间</td>
						<td><fmt:formatDate value="${customerApp.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td>备注</td>
						<td colspan="3">
							<input name="remark" type="text"  maxlength="50"  size="120" value="${customerApp.remark}" />
						</td>
					</tr>
					<tr>
						<td>服务描述</td>		
						<td colspan="3">
							<input name="description" type="text"  maxlength="50"  size="120" value="${customerApp.description}" />
						</td>
					</tr>
				</tbody>
			</table>
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
		for(k in customer)
		{
			var thisVal = customer[k];
			if(thisVal.toString().indexOf(".") != -1)
			{
				thisVal =  thisVal.toString().substr(0,thisVal.toString().indexOf("."));
			}
			$("#" + k).html(thisVal);
		}
		var carInfoLookupObj = $("#appordercustomerLookup");
		var hrefVal = carInfoLookupObj.prop("href");
		if(hrefVal.indexOf("carId") == -1)
		{
			carInfoLookupObj.prop("href",hrefVal + "?carId=" + customer.carId);
		}else if(customer.carId)
		{
			var newHref = hrefVal.substr(0,hrefVal.indexOf("?"));
			carInfoLookupObj.prop("href",newHref + "?carId=" + customer.carId);
		}
		if(customer.iscustomer)
		{
			$("#platenum").trigger('change');
		}
	}
	/**
	* 车主信息改变 将车辆置空
	*/
	function clealCarInf()
	{
		console.debug("clealCarInf");
		$("#customerName").val("");
	}
	//
	$(function()
	{
		setcheckbox();
	});

	/**
	* 复选框设置
	*/
	function setcheckbox()
	{
		var checkVal = $("#customereidtappoittypes").val();
		var checkArr = checkVal.split(",");
		//迭代
		$("[name='appoittypes']").each(function(index, el) {
			var chekobj = $(el);
			var objval = parseInt(chekobj.val());
			for(var i=0;i<checkArr.length;i++)
			{
				var itemval = parseInt(checkArr[i]);
				if(itemval===objval)
				{
					chekobj.attr("checked","checked");
				}
			}
		});
		
	}
</script>