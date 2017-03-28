<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/app/assignsave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input  name="source" type="hidden"  value="1"  />
		<input  name="cusappoitId" type="hidden" value="${customerApp.cusappoitId}" />
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
			</table>
			<h3 style="margin:10px 0 10px 5px;">预约基本信息</h3>
			<table class="table" width="100%">
				<tbody>
					<tr>
						<td>预约类别</td>
						<td>
							<c:if test="${customerApp.appoittype==0}">
								维修
							</c:if>
							<c:if test="${customerApp.appoittype==1}">
								保养
							</c:if>
							<c:if test="${customerApp.appoittype==2}">
								美容
							</c:if>
						</td>
						<td>预约时间</td>
						<td>
							<fmt:formatDate value="${customerApp.latsmaintenance}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>
					<tr>
						<td>指派店铺</td>
						<td  colspan="3">
							<input id="deptname" type="text" value="${customerApp.infoDept.name}" maxlength="30" size="30" class="required"/>
							<input id="deptId" name="empId" value="${customerApp.infoDept.deptId}" type="hidden"  maxlength="30"  />
							<a id="shopInfoLookup" title="指派店铺" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/apporder/shopLookup.do" lookupGroup="shopInfoLookup" rel="shopInfoLookup"></a>
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
				<tag:auth code="app.order.assginSave">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">立即指派</button></div></div>
					</li>
				</tag:auth>
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
	}
</script>