<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<script id="goodslistapplay" type="text/html">
		<tr>
			<td>{{goodsCode}}</td>
			<td>{{name}}</td>
			<td>{{price |formatNumber }}</td>
			<td>
				<input type="text" name="infoGoodList[{{goodsIndex}}].num" maxlength="5" style="width:50px;" class="required number"  size="30" />
				<input type="hidden" name="infoGoodList[{{goodsIndex}}].goodsId" id="goods_id"  value="{{goodsId}}"/>
			</td>
			<td>
				{{unitName}}
				<input type="hidden" name="infoGoodList[{{goodsIndex}}].paramConfig.unitId" id="goods_id"  value="{{unitId}}"/>
			</td>
			<td>{{brandName}}</td>
			<td>{{standard}}</td>
			<td>
				<a onclick="deleteLine(this),removeGoodid({{goodsId}})" class="btn btn-delete"><a/>
			</td>
		</tr>
	</script>
	<form method="post" id="order_form" action="${pageContext.request.contextPath}/allocationapply/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="unit">
			<table class="list" width="100%" >
				<tr>
					<td style="float: right;">操作人：</td>
					<input name="empid" value="${sysUser.infoEmp.empId }" type="hidden"/>
					<td>${fn:length(sysUser.infoEmp.empName) > 0 ? sysUser.infoEmp.empName : sysUser.username}</td>
					<td style="float: right;">交货日期：</td>
					<td>
						<input readonly="readonly" name="takeDate" type="text" class="date"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="pageFormContent" layoutH="100">
			<div class="panelBar">
				<ul class="toolBar">
					<tag:auth code="allocationapply.addgoods">
						<li><a class="add" href="${pageContext.request.contextPath}/service/servicegoodslookup.do"  width="800" height="600" mask="true" id="servicegoodsLookup_apply" rel="servicegoodsadd_allo_xx" lookupGroup="servicegoodsLookup"><span>添加配件</span></a></li>
					</tag:auth>
				</ul>
			</div>
			<table class="list" width="100%" >
				<thead>
					<tr>
						<th>配件编号</th>
						<th>配件名称</th>
						<th>成本价</th>
						<th>申请数量</th>
						<th>最小单位</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="goodtbody">
					
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="allocationapply.savegoods">
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
		//设置服务配件的id和数量
		template.helper('formatNumber',function(num)
		{
			num = parseFloat(num);
			var newNum = formatCurrencyTenThou(num);
			return "￥" + newNum;
		});
		var tableHtml = template("goodslistapplay", infGoods);
		$("#goodtbody").append(tableHtml);
		goodIDS.push(infGoods.goodsId);
		setLookHref();
	}
function gosubmit(){
var gid=$("#goods_id").val();
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
		var lookObj = $("#servicegoodsLookup_apply");
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