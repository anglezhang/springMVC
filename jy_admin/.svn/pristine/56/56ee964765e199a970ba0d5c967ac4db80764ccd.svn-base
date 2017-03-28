<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageHeader">
	<form id="lookuppagerForm" method="post" action="${pageContext.request.contextPath}/apporder/customerlookup.do">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="carId" value="${carId}" />
	</form>
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/apporder/customerlookup.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="carId" value="${carId}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						客户姓名：<input type="text" name="querytName" value="${querytName}"/>
					</td>
					<td>
						客户电话：<input type="text" name="queryTel" value="${queryTel}"/>
					</td>
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit" id="customerlookupsubmit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<tag:auth code="customer.add">
				<a class="add" href="${pageContext.request.contextPath}/apporder/addcustomer.do?dialogId=orderaddcustomer&carId=${carId}" target="dialog"  width="530" height="300" mask="true" rel="orderaddcustomer" ><span>添加</span></a>
				</li>
				</tag:auth>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="124" >
		<thead>
			<th width="50" align="center">序号</th>
			<th align="center">客户姓名</th>
			<th align="center">客户电话</th>
			<th align="center">性别</th>
			<th align="center">详细地址</th>
			<th align="center">操作</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infoCustome" varStatus="status">
				<tr>
					<td align="center">${status.index + 1}</td>
					<td align="center">${infoCustome.customerName}</td>
					<td align="center">${infoCustome.tel}</td>
					<td align="center">
						<c:if test="${!infoCustome.gender}">
							女
						</c:if>
						<c:if test="${infoCustome.gender}">
							男
						</c:if>
					</td>
					<td align="center">${infoCustome.detaladdress}</td>
					<td align="center">
						<a class="btn btn-xzdh" href="javascript:$.bringBack({customerId:'${infoCustome.customerId}',customerName:'${infoCustome.customerName}',tel:'${infoCustome.tel}'}),setLookVal({customerId:'${infoCustome.customerId}',customerName:'${infoCustome.customerName}',tel:'${infoCustome.tel}'})" title="选择带回"></a>
						<tag:auth code="app.order.edit">
							<a title="修改" target="dialog" width="530" height="300"  mask="true" href="${pageContext.request.contextPath}/customer/edit.do?customerId=${infoCustome.customerId}&dialogId=customereidt" rel="customereidt" class="btn btn-edit"></a>
						</tag:auth>	
						<tag:auth code="customer.delete">
							<a title="删除" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/customer/delete.do?customerId=${infoCustome.customerId}" callback="showDiloaAjax" class="btn btn-delete"></a>
						</tag:auth>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../../include/page.jsp" %>
</div>
<script type="text/javascript">
	function showDiloaAjax(json)
	{
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
			$("#customerlookupsubmit").trigger('click');
		}
	}
	//判断是否有carId
	$(function()
	{
		var carId = $("[name='carId']").val();
		if(carId==="")
		{
			var errorMsg = new Object();
			errorMsg.statusCode = 300;
			errorMsg.message = "请先选择车辆";
			dialogAjaxDone(errorMsg);
			var dialog = $.pdialog._current;
			$("body").removeData($(dialog).data("id"));
			$(dialog).trigger(DWZ.eventType.pageClear).remove();
		}
	});
</script>