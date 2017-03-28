<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<script>
		function computeNum(index){
	var orderNum=$("#cgsl_id_"+index).val();
	var orderprice=$("#dj_id_"+index).val();
	var taxrate=$("#sl_id_"+index).val();
	//var hsjHtml="";
	//var bhsjeHtml="";
	//var seHtml="";
	//var hsje="";
	if(orderNum!=""&&orderprice!=""){
		var bhsje=(parseFloat(orderNum)*parseFloat(orderprice)).toFixed(2);
		$("#bhsje_id_"+index).html(bhsje);
	}
	if(taxrate!=""&&orderprice!=""){
		var hsdj=(parseFloat(orderprice)*(1+parseFloat(taxrate)/100)).toFixed(2);
		$("#hsj_id_"+index).html(hsdj);
	}
	if(taxrate!=""&&orderprice!=""&&orderNum!=""){
		var bhsje1=(parseFloat(orderNum)*parseFloat(orderprice)).toFixed(2);
		var hsje1=(parseFloat(bhsje1)*(1+parseFloat(taxrate)/100)).toFixed(2);
		$("#se_id_"+index).html((parseFloat(hsje1)-parseFloat(bhsje1)).toFixed(2));
		$("#hsje_id_"+index).html(hsje1);
	}

}
	</script>
	<script id="goodslist_order" type="text/html">
		<tr>
			<td>
			{{goodsCode}}
			<input value="{{goodsId}}" type="hidden" name="orderList[{{goodsIndex}}].infoGoods.goodsId" id="goods_id"/>
			</td>
			<td>{{name}}</td>
			<td>
				<input type="text" onKeyUp="clearNoNum(event,this)" onBlur="checkNum(this)"  name="orderList[{{goodsIndex}}].orderSmallNum" maxlength="10" style="width:50px;" class="required"  size="30" />
			</td>
			<td>
				<input type="text" id="cgsl_id_{{goodsIndex}}" onKeyUp="clearNoNum(event,this),computeNum('{{goodsIndex}}')" onBlur="checkNum(this)"  name="orderList[{{goodsIndex}}].orderNum" maxlength="10" style="width:50px;" class="required"  size="30" />
			</td>
			<td>
				{{unitName}}
				<input type="hidden" name="orderList[{{goodsIndex}}].unit" id="unitId"  value="{{unitId}}"/>
			</td>
			<td>
				<select name="orderList[{{goodsIndex}}].orderUnit">
			 <c:forEach items="${unitparam}"  var="unitParam">
				<option value="${unitParam.unitId }" >${unitParam.name }</option>
			 </c:forEach>
			</select>
			</td>
			<td>
				<input type="text" id="dj_id_{{goodsIndex}}" name="orderList[{{goodsIndex}}].orderPrice" onKeyUp="clearNoNum(event,this),computeNum('{{goodsIndex}}')" onBlur="checkNum(this)"  maxlength="10" style="width:50px;" class="required"  size="30" />
			</td>
			<td>
				<input type="text" id="sl_id_{{goodsIndex}}" name="orderList[{{goodsIndex}}].taxRate" onKeyUp="clearNoNum(event,this),computeNum('{{goodsIndex}}')" onBlur="checkNum(this)"  maxlength="10" style="width:50px;" class="required"  size="30" />%
			</td>
			<td id="hsj_id_{{goodsIndex}}">
			</td>
			<td>{{brandName}}</td>
			<td>{{standard}}</td>
			<td id="bhsje_id_{{goodsIndex}}">
			</td>
			<td id="se_id_{{goodsIndex}}"></td>	
			<td id="hsje_id_{{goodsIndex}}"></td>
			<td>
				<a onclick="deleteLine(this),removeGoodid({{goodsId}})" class="btn btn-delete"><a/>
			</td>
		</tr>

	</script>
	<form method="post" id="order_form" action="${pageContext.request.contextPath}/order/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="unit">
			<table class="list" width="100%" >
				<tr>
					<td>供应商名称：</td>
					<td>
						<input id="id" type="hidden" name="infoSup.id"  />
						<span style="float: left"><input id="name" type="text" name="infoSup.name" class="required" readonly="readonly"  size="30" /></span>
						<span style="float: left"><a  style="float:right;margin-left: 5px;"  title="选择供应商" width="800" height="600"  class="btnLook" href="${pageContext.request.contextPath}/infosub/lookup.do" lookupGroup="infoSup" rel="infoSub_order">选择供应商</a></span>
					</td>
					<td>操作人</td>
					<input name="InfoEmp.empId" value="${sysUser.infoEmp.empId }" type="hidden"/>
					<td>${fn:length(sysUser.infoEmp.empName) > 0 ? sysUser.infoEmp.empName : sysUser.username}</td>
				</tr>
				
				<tr>
					<td>付款方式：</td>
					<td>
						<select name="paramConfig.unitId">
							<c:forEach items="${paramConfigList }" var="config">
								<option value="${config.unitId }">${config.name }</option>
							</c:forEach>
						</select>
					</td>
					<td>采购备注</td>
					<td><input value="" name="memo" type="text" maxlength="50" size="50" /></td>
				</tr>
			</table>
		</div>
		<div class="pageFormContent" layoutH="100">
			<div class="panelBar">
				<ul class="toolBar">
					<tag:auth code="order.list.addgoods">
						<li><a class="add" href="${pageContext.request.contextPath}/service/servicegoodslookup.do" id="servicegoodsLookup_order" width="800" height="600" mask="true" rel="servicegoodsadd" lookupGroup="servicegoodsLookup"><span>添加配件</span></a></li>
					</tag:auth>
				</ul>
			</div>
			<table class="list" width="100%" >
				<thead>
					<tr>
						<th>配件编号</th>
						<th>配件名称</th>
						<th>订购最小单位数</th>
						<th>采购数量</th>
						<th>最小计量单位</th>
						<th>采购单位</th>
						<th>采购不含税单价</th>
						<th>税率</th>
						<th>采购含税单价</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<th>采购不含税金额</th>
						<th>税额</th>
						<th>采购含税金额</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="goodtbody_order">
							
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="order.save">
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="gosubmit()">保存</button></div></div>
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
	var index=0;
	var goodIDS = new Array();
	/**
	* 选中服务类型带回操作
	*/
	function getLogo(servicetype)
	{
		for(var k in servicetype)
		{
			$("#" + k).val(servicetype[k]);
		}
	}
	/**
	* 选中服务配件带回操作
	*/
	function setTable(infGoods)
	{
		infGoods.goodsIndex = index++;
		/* //设置服务配件的id和数量
		template.helper('formatNumber',function(num)
		{
			num = parseFloat(num);
			var newNum = formatCurrencyTenThou(num);
			return "￥" + newNum;
		}); */
		var tableHtml = template("goodslist_order", infGoods);
		$("#goodtbody_order").append(tableHtml);
		goodIDS.push(infGoods.goodsId);
		setLookHref();
	}
function gosubmit(){
var gid=$("#goodtbody_order").html().replaceAll("\\s", "").replaceAll("　", "");
if(gid==""||gid==null){
	var errorMsg = new Object();
	errorMsg.statusCode = 300;
	errorMsg.message = "请选择配件";
	dialogAjaxDone(errorMsg);
}else{
	$("#order_form").submit();
}
}	
	/**
	* 描述:删除id
	*/
	function removeGoodid (goodId) {
		var value = parseInt(goodId);
		var lenght = goodIDS.length;
		for(var i=0;i<lenght;i++)
		{
			var id = parseInt(goodIDS[i]);
			if(id==value)
			{
				goodIDS.splice(i,1);
			}
		}
		setLookHref();
	}
	/**
	* 设置配额选中href
	*/
	function setLookHref()
	{
		var lookObj = $("#servicegoodsLookup_order");
		var hrefVal = lookObj.prop("href");
		if(hrefVal.indexOf("?") != -1)
		{
			hrefVal = hrefVal.substr(0,hrefVal.indexOf("?"));
			
			
		}
		hrefVal += "?goodIDS=";
		for(var i=0;i<goodIDS.length;i++)
		{
			hrefVal += goodIDS[i] + ","
		}
		if(hrefVal.indexOf(",") != -1)
		{
			hrefVal = hrefVal.substr(0,hrefVal.length-1);
		}
		lookObj.prop("href",hrefVal);
	}
</script>