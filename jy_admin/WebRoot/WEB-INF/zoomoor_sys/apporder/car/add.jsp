<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/apporder/carInfosave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input  type="hidden" name="customerId" maxlength="30" value="${customerId}" />
		<input name="dialogId" type="hidden" value="${dialogId}">
		<div class="pageFormContent" layoutH="80">
			<table>
				<tr>
					<td>
						<div class="unit">
							<label>车牌号：</label>
							<input  type="text" name="platenum" maxlength="30" class="required"  size="30" />
						</div>
					</td>
					<td>
						<div class="unit">
							<label>颜色：</label>
							<input  type="text" name="color" maxlength="20"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>品牌车系</label>
							<input name="infoBrand.id" type="hidden" value="">
							<input name="infoBrand.name" type="text" size="30" class="required" readonly="readonly" />
							<a title="选择品牌车系" class="btnLook" href="${pageContext.request.contextPath}/lookup/brand.do?dialogId=caraddlook_brand" rel="caraddlook_brand" lookupPk="ordercarInfoLookup" width="600" height="550" mask="true"  lookupGroup="infoBrand">品牌车系</a>
						</div>
					</td>
					<td>
						<div>
							<label>车主姓名</label>
							<input id="carAddCustomerName" type="text" name="infCustomer.customerName" maxlength="30" size="30" />
							<input id="carAddinfoCustomerId" type="hidden" name="infCustomer.customerId" maxlength="30" readonly="readonly" />
							<a title="选择客户" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/carinfo/customerinfolookup.do" lookupGroup="carinfocustomer" rel="carinfocustomer"></a>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>车架号VIN：</label>
							<input  type="text" name="framnum" maxlength="80"  size="30" />
						</div>
					</td>
					<td>
						<div class="unit">
							<label>发动机号：</label>
							<input  type="text" name="vinnum" maxlength="80"   size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>购车时间：</label>
							<input  type="text" name="buytime" maxlength="19" class="date"  size="30" readonly="readonly"  />
						</div>
					</td>
					<td>
						<div class="unit">
							<label>最新里程：</label>
							<input  type="text" name="mileage" maxlength="7" class="number"  size="30" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>上次保养：</label>
							<input  type="text" name="nextmain" maxlength="19" class="date"  size="30" readonly="readonly"  />
						</div>
					</td>
					<td>
						<div class="unit">
							<label>挂牌时间：</label>
							<input  type="text" name="listingtime" maxlength="19" class="date" size="30"  readonly="readonly"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>上次投保时间</label>
							<input  type="text" name="nextins"  class="date" size="30" readonly="readonly"  />
						</div>
					</td>
					<td>
						<div class="unit">
							<label>下次回访：</label>
							<input  type="text" name="nextvis"  class="date" size="30" readonly="readonly" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<label>排量：</label>
						<select name="displacement" id="displacement" style="width:212px;">
							<c:forEach items="${paramConfigList }" var="paramconfig">
								<option value="${paramconfig.name}"<c:if test="${status.index==0}">selected="true"</c:if> >${paramconfig.name }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<label>是否自动档：</label>
						<input type="radio" name="isAuto" checked="checked" value="false"/> 否 <input type="radio" name="isAuto" value="true"/> 是
					</td>
				</tr>
				<tr>
					<td>
						<label>出厂日期：</label>
						<input  type="text" name="factoryDate"  class="date" size="30" readonly="readonly"  />
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>保险公司:</label>
							<input  type="text" name="inscompany" maxlength="80"   size="40" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="unit">
							<label>备注：</label>
							<input  type="text" name="remark" maxlength="50"  size="40" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button id="addCarinfobtn" type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	function dialogAjaxDone2(json)
	{
		//DWZ.ajaxDone(json);
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok)
		{
			$("body").removeData($(dialog).data("id"));
			var dialog = $.pdialog._current;
			$(dialog).trigger(DWZ.eventType.pageClear).remove();
			$("#carinfolookupsubmit").trigger('click');
		}
	}
	function setcarLookVal(customer)
	{
		
		$("#carAddCustomerName").val(customer['customerName']);
		$("#carAddinfoCustomerId").val(customer['customerId'])
	}
</script>