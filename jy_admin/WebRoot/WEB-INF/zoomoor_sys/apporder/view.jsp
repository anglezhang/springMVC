<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<input  id="customereidtappoittypes" type="hidden"  value="${customerApp.appoittype}"  />
	<form method="post" action="${pageContext.request.contextPath}/app/ordersave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input  name="source" type="hidden"  value="1"  />
		<input id="carId" name="infoCar.carId" type="hidden" />
		<input id="brandId" name="infoBrand.id" type="hidden" />
		<div class="pageFormContent" layoutH="80">
			<h3 style="margin:10px 0 10px 5px;">客户信息</h3>
			<table class="table" width="100%">
				<tr>
					<td width="100">客户姓名：</td>
					<td width="400">
						${customerApp.infoCustomer.customerName}
					</td>
					<td width="100">客户车辆：</td>
					<td >
						${customerApp.infoCar.platenum}
					</td>
				</tr>
			</table>
			<table class="table" width="100%">
				<tr>
					<td width="100">车主电话</td>
					<td width="400">
						${customerApp.infoCustomer.tel}
					</td>
					<td width="100">品牌</td>
					<td>
						${customerApp.infoCar.infoBrand.name}
					</td>
				</tr>
				<tr>
					<td>上次投保时间</td>
					<td>
						<fmt:formatDate value="${customerApp.infoCar.nextins}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>购车日期</td>
					<td >
						<fmt:formatDate value="${customerApp.infoCar.buytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>挂牌时间</td>
					<td > 
						<fmt:formatDate value="${customerApp.infoCar.listingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>行驶里程</td>
					<td > 
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
						<td>预约类别</td>
						<td colspan="3">
							<c:forEach items="${serviceItem}" var="serviceType" varStatus="s">
								<input type="checkbox" name="appoittypes" disabled="disabled" value="${serviceType.servicetypeId}">${serviceType.servicename}
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td colspan="3">预约时间</td>
						<td>
							<fmt:formatDate value="${customerApp.appoittime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
					<tr>
						<td>指派店铺</td>
						<td  colspan="3">
							${customerApp.infoDept.name}
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
							${customerApp.remark}
						</td>
					</tr>
					<tr>
						<td>服务描述</td>		
						<td colspan="3">
							${customerApp.description}
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
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