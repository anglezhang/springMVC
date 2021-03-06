<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<script id="customEntrustAct_addserviceItem_model" type="text/html">
		<tr>
			<td>
				{{count}}
				<input type="hidden" name="items[{{index}}].itemId" value="{{itemId}}" />
				<input type="hidden" name="items[{{index}}].servicetype.servicetypeId" value="{{servicetypeId}}" />
			</td>
			<td>
				{{servicename}}
			</td>
			<td>
				{{itemcode}}
			</td>
			<td>
				{{itemname}}
			</td>
			<td>
				{{workhourmoney | formatNumber}}
			</td>
			<td>
				{{discount}}
			</td>
			<td>
				{{intergral}}
			</td>
			<td>
				<a onclick="deleteLine(this),removeItemId({{itemId}})" class="btn btn-delete"><a/>
			</td>
		</tr>
	</script>
	<script id="customerentrustgoodlistmodel" type="text/html">
		<tr>
			<td>
				{{goodsCode}}
			</td>
			<td>
				{{name}}
			</td>
			<td>
				{{price | formatNumber}}
			</td>
			<td>
				<input type="text" name="goods[{{index}}].num" class="required" value="0" size="20"/>
				<input type="text" name="goods[{{index}}].price" class="required" value="{{price}}" size="20"/>
			</td>
			<td>
				{{unitName}}
			</td>
			<td>
				{{brandName}}
			</td>
			<td>
				{{standard}}
			</td>
			<td>
				{{discount}}
			</td>
			<td>
				{{intergral}}
			</td>
			<td>
				<a onclick="deleteLine(this),removeGoods({{goodsId}})" class="btn btn-delete"><a/>
			</td>
		</tr>
	</script>
	<form method="post" action="${pageContext.request.contextPath}/serviceorder/savecreate.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="60">
			<c:if test="${customer!=null}" >
				<h3 style="margin:10px 0 10px 5px;">预约信息</h3>
				<table class="table" width="100%">
					<tr>
						<td>预约单号</td>
						<td>${customer.cusorderNO}</td>
						<td>预约服务类型</td>
						<td>保养</td>
						<td>预约人姓名</td>
						<td>${customer.appname}</td>
						<td>预约人电话</td>
						<td>${customer.apptel}</td>
					</tr>
					<tr>
						<td>预约时间</td>
						<td><fmt:formatDate value="${customer.appoittime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>开单人</td>
						<td></td>
						<td>开单时间</td>
						<td><fmt:formatDate value="${customer.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</table>
			</c:if>
			
			<h3 style="margin:10px 0 10px 5px;">客户/车辆信息</h3>
			<table class="table" width="100%">
				<tr>
					<td width="100">客户车辆：</td>
					<td >
						<input id="platenum" name="infoCar.platenum" type="text"  maxlength="30" class="required" readonly="readonly" size="30" value="${customer.infoCar.platenum}" onchange="clealCarInf()"  />
						<a id="carInfoLookup" title="客户车辆" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/apporder/carlookup.do" lookupGroup="ordercarInfoLookup"  rel="appordercar" lookupPk="ordercarInfoLookup"></a>
						
						<input id="carId" name="carId" type="hidden" value="${customer.infoCar.carId}" />
					</td>
					<td width="100">客户姓名：</td>
					<td width="400">
						<input id="customerName" type="text"  maxlength="30" class="required" readonly="readonly" size="30" value="${customer.infoCustomer.customerName}" />
						<input id="customerId" type="hidden" name="customerId" maxlength="30" value="${customer.infoCustomer.customerId}" />
						<a id="appordercustomerLookup" title="选择客户" width="800" height="600" class="btnLook" href="${pageContext.request.contextPath}/apporder/customerlookup.do" lookupGroup="appordercustomerLookup" rel="appordercustomer"></a>
					</td>
				</tr>
			</table>
			<table class="table" width="100%">
				<tr>
					<td width="100">客户电话</td>
					<td width="400" id="tel">
						${customer.infoCustomer.tel}
					</td>
					<td width="100" >品牌</td>
					<td id="brandName">
						${customer.infoCar.brandName}
					</td>
				</tr>
				<tr>
					<td>发动机编号</td>
					<td id="vinnum">${customer.infoCar.vinnum}</td>
					<td>出厂日期</td>
					<td id="factoryDate"><fmt:formatDate value="${customer.infoCar.factoryDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td>排量</td>
					<td id="displacement">
						${customer.infoCar.displacement}
					</td>
					<td>变速箱类型</td>
					<td id="isAuto">
					</td>
				</tr>
				<tr>
					<td>上次保养日期</td>
					<td id="nextmain">
						<fmt:formatDate value="${customer.infoCar.nextmain}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>上次投保时间</td>
					<td id="nextins">
						<fmt:formatDate value="${customer.infoCar.nextins}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>购车日期</td>
					<td id="buytime">
						<fmt:formatDate value="${customer.infoCar.buytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>挂牌时间</td>
					<td id="listingtime"> 
						<fmt:formatDate value="${customer.infoCar.listingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>行驶里程</td>
					<td id="mileage"> 
						${customer.infoCar.mileage}
					</td>
				</tr>
			</table>
			<h3 style="margin:10px 0 10px 5px;">服务委托单信息</h3>
			<input type="hidden" name="cusappoitId" value="${customer.cusappoitId}">
			<input type="hidden" name="infoDept.deptId" value="${user.infoEmp.infoDept.deptId}">
			<table class="table" width="100%">
				<tr>
					<!--<td>服务委托单编号</td>
					<td></td>-->
					<td width="100">服务接待人</td>
					<td width="400">
						<select name="receptionist" id="receptionist" class="required" style="width:212px;">
							<c:forEach items="${emps}" var="infoEmp" varStatus="s">
								<option value="${infoEmp.empId}">${infoEmp.empName} <c:if test="${!empty infoEmp.paramConfig.name}" >(${infoEmp.paramConfig.name})</c:if>   </option>
							</c:forEach>
						</select>
					</td>
					<td width="100">开单时间</td>
					<td width="400">
						<fmt:formatDate value="${createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>预交车时间</td>
					<td><input  type="text" name="estimatetime" maxlength="200"  readonly="readonly" class="required date"  size="30" datefmt="yyyy-MM-dd HH:mm:ss"/></td>
					<td>指派维修工人</td>
					<td>
						<select name="fixer" id="fixer" style="width:212px;">
							<c:forEach items="${emps}" var="infoEmp" varStatus="s">
								<option value="${infoEmp.empId}">${infoEmp.empName} <c:if test="${!empty infoEmp.paramConfig.name}" >(${infoEmp.paramConfig.name})</c:if>   </option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>服务位置</td>
					<td>
						<select name="fixplace"  class="required" style="width:212px;" >
							<option value="0">${dept.name}</option>
							<c:forEach items="${addrList}" var="addr" varStatus="s">
								<option value="${addr.addrId}">${addr.address} </option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td><input  type="text" name="remark" maxlength="200"   size="30" /></td>
				</tr>
			</table>
			<h3 style="margin:10px 0 10px 5px;">服务项目</h3>
			<div class="panelBar">
				<ul class="toolBar">
					<tag:auth code="serviceItem.lookup.do">
						<li><a class="add" href="${pageContext.request.contextPath}/serviceItem/lookup.do"  width="1100" height="720" mask="true" id="orderaddserviceitem" lookupGroup="orderaddserviceitem" rel="orderaddserviceitem"><span>添加服务项目</span></a></li>
					</tag:auth>
				</ul>
			</div>
			<table class="list" width="100%">
				<thead>
					<th>序号</th>
					<th>服务类型</th>
					<th>项目编号</th>
					<th>项目名称</th>
					<th>工时费用</th>
					<th>折扣系数</th>
					<th>积分</th>
					<th>操作</th>
				</thead>
				<tbody id="customEntrustAct_addserviceItem"></tbody>
			</table>
			<!-- <h3 style="margin:10px 0 10px 5px;">需要的配件</h3>
			<table class="list" width="100%">
				<thead>
					<th>序号</th>
					<th>配件编号</th>
					<th>配件名称</th>
					<th>单价(元)</th>
					<th>数量</th>
					<th>最小单位</th>
					<th>配件品牌</th>
					<th>配件规格</th>
					<th>折扣系数</th>
					<th>积分</th>
					<th>操作</th>
				</thead>
				<tbody id="customerentrustgoodlist">
					
				</tbody>
			</table> -->
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="customer.create.save">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
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
	//设置4s店收费款
	function setShopmonney(ctl)
	{
		var obj = $(ctl);
		var shopmonneyObj = $("#shopmonney")
		var fixplace = obj.val();
		if(fixplace==0)
		{
			shopmonneyObj.prop("readonly",true);
			shopmonneyObj.removeClass('required')
		}else
		{
			shopmonneyObj.prop("readonly",false);
			shopmonneyObj.addClass('required')
		}
	}
	var index = 0;//行数下标
	var itemIds = new Array();	
	var goodIndex = 0;
	var goodIds = new Array();			
	/**
	* 描述:添加服务项目
	* @param serviceitem 服务项目对象
	*/
	function createTable(serviceitem)
	{
		createTableTr(serviceitem,"customEntrustAct_addserviceItem"
			,"customEntrustAct_addserviceItem_model"
			,function()
			{
				serviceitem.index = index;
				serviceitem.count = index + 1;
			},function()
			{
				index++;
				itemIds.push(serviceitem.itemId);
				setSeriItemBrand();
				getGoodsByItems(itemIds);
			});
		
	}
	/**
	* 移除服务项id
	*/
	function removeItemId(itemId)
	{
		removeAtValue(itemIds,itemId);
		setSeriItemBrand();
	}

	/**
	* 服务适用车辆id
	*/
	function setSeriItemBrand()
	{
		/*setlookupParams(itemIds,"orderaddserviceitem","itemIdS");*/
		var cltObj = $("#orderaddserviceitem");
		var objHref = cltObj.prop("href");
		var carId = $("#carId").val();
		var carIds = new Array();
		carIds.push(carId);
		if(!isEmptyObj(carId))
		{
			setlookupParams(carIds,"orderaddserviceitem","carId");
		}
	}
	/**
	* 设置客户信息
	*/
	function setLookVal(customer)
	{
		
		for(k in customer)
		{
			var thisVal = customer[k];
			$("#" + k).val(thisVal);
		}
		for(k in customer)
		{
			var thisVal = customer[k];
			$("#" + k).html(thisVal);
		}
		if(!isEmptyObj(customer['isAuto']))
		{
			if(customer['isAuto']==="true"){
				$("#isAuto").html("自动档");
			}else if(customer['isAuto']==="false")
				$("#isAuto").html("手动档");
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
		setSeriItemBrand();
	}
	/**
	* 车主信息改变 将车辆置空
	*/
	function clealCarInf()
	{
		$("#customerName").val("");
	}
	function setTable(obj)
	{
		createTableTr(obj,"customerentrustgoodlist"
			,"customerentrustgoodlistmodel"
			,function()
			{
				obj.index = goodIndex;
				obj.count = goodIndex + 1;
			},function()
			{
				goodIndex++;
				goodIds.push(obj.goodsId);
			});
		setlookupParams(goodIds,"servicegoodsLookup","goodIDS");
	}
	function removeGoods(goodid)
	{
		removeAtValue(goodIds,goodid);
		setlookupParams(goodIds,"servicegoodsLookup","goodIDS");
	}

	/**
	* 根据服务项目id查询货物 serviceItemId
	*/
	function getGoodsByItems(itemIDs)
	{
		var path = "${pageContext.request.contextPath}/customerorder/servicegoods.ajax";
		var data = {'itemsIds':itemIDs};
		$.post(path,data, function(data) {
			
		});
	}

	$(function()
	{
		setSeriItemBrand();
	});
</script>