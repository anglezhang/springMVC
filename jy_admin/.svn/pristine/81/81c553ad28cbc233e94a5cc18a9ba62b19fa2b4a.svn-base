<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageHeader">
	<form id="carlookuppagerForm" method="post" action="${pageContext.request.contextPath}/apporder/carlookup.do">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="customerId" value="${customerId}" />
	</form>
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/apporder/carlookup.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="customerId" value="${customerId}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						车牌号：<input type="text" name="carNO" value="${carNO}"/>
					</td>
					<!--<td>
						客户姓名：<input type="text" name="customerName" value="${customerName}"/>
					</td>-->
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit" id="carinfolookupsubmit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent" >
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="carinfo.add">
				<li><a class="add" href="${pageContext.request.contextPath}/apporder/addcar.do?dialogId=ordercarinfoAdd&customerId=${customerId}" target="dialog"  width="800" height="600" mask="true" rel="ordercarinfoAdd" ><span>添加</span></a></li>
			</tag:auth>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="124" >
		<thead>
			<th align="center" >车牌号</th>
			<th align="center" >车辆品牌</th>
			<!--<th align="center">客户名称</th>
			<th align="center">车主姓名</th>-->
			<th align="center" >操作</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infoCar" varStatus="status">
				<tr>
					<td align="center">${infoCar.platenum}</td>
					<td align="center">${infoCar.brandName}</td>
					<!--<td align="center">${customerName}</td>
					<th align="center">${infoCar.infCustomer.customerName}</th>-->
					<td align="center">
						<a class="btn btn-xzdh" href="javascript:$.bringBack({carId:'${infoCar.carId}',brandName:'${infoCar.brandName}',brandId:'${infoCar.infoBrand.id}',mileage:'${infoCar.mileage}',buytime:'${fn:substring(infoCar.buytime,0,19)}',platenum:'${infoCar.platenum}',listingtime:'${fn:substring(infoCar.listingtime,0,19)}',nextins:'${fn:substring(infoCar.nextins,0,19)}',displacement:'${infoCar.displacement}',vinnum:'${infoCar.vinnum}',factoryDate:'${fn:substring(infoCar.factoryDate,0,19)}',isAuto:'${infoCar.isAuto}'}),setLookVal({carId:'${infoCar.carId}',brandName:'${infoCar.brandName}',brandId:'${infoCar.infoBrand.id}',mileage:'${infoCar.mileage}',buytime:'${fn:substring(infoCar.buytime,0,19)}',platenum:'${infoCar.platenum}',listingtime:'${fn:substring(infoCar.listingtime,0,19)}',nextins:'${fn:substring(infoCar.nextins,0,19)}',displacement:'${infoCar.displacement}',vinnum:'${infoCar.vinnum}',factoryDate:'${fn:substring(infoCar.factoryDate,0,19)}',isAuto:'${infoCar.isAuto}',iscustomer:true})" title="选择带回"></a>
						<tag:auth code="carinfo.delete">
							<a title="删除" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/carinfo/delete.do?carId=${infoCar.carId}" callback="showDiloaAjax" class="btn btn-delete"></a>
						</tag:auth>	
						<tag:auth code="carinfo.edit">
							<a title="修改" target="dialog"  width="800" height="600"  mask="true" href="${pageContext.request.contextPath}/carinfo/edit.do?carId=${infoCar.carId}&dialogId=carinfoeidt" rel="carinfoeidt" class="btn btn-edit"></a>
						</tag:auth>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../../include/page.jsp" %>
</div>
<script type="text/javascript">
	$(function()
	{
		/*var customerId = $("[name='customerId']").val();
		if(customerId==="")
		{
			var errorMsg = new Object();
			errorMsg.statusCode = 300;
			errorMsg.message = "请先选择客户";
			dialogAjaxDone(errorMsg);
			var dialog = $.pdialog._current;
			$("body").removeData($(dialog).data("id"));
			$(dialog).trigger(DWZ.eventType.pageClear).remove();
		}*/
	});
	function showDiloaAjax(json)
	{
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
			$("#carinfolookupsubmit").trigger('click');
		}
	}
</script>