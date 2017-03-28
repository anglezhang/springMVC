<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<script id="goodslist" type="text/html">
		<tr>
			<td>{{goodsCode}}</td>
			<td>{{name}}</td>
			<td>{{price | formatNumber}}</td>
			<td>{{brandName}}</td>
			<td>{{standard}}</td>
			<td>
				<input type="text" name="siteGoodMap[{{goodsIndex}}].number" maxlength="200" style="width:50px;" class="required number"  size="30" />
				<input type="hidden" name="siteGoodMap[{{goodsIndex}}].infoGoods.goodsId"  value="{{goodsId}}"/>
			</td>
			<td>
				<a onclick="deleteLine(this)" class="btn btn-delete"><a/>
			</td>
		</tr>
	</script>
	<form method="post" action="${pageContext.request.contextPath}/service/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input id="servicetypindex" type="hidden" value="${serviceitem.sitemgoodsMappings.size()}" />
		<input id="servicetypindex" type="hidden" name="itemId" value="${serviceitem.itemId}" />
		<div class="unit">
			<table class="list" width="100%" >
				<tr>
					<td>服务类型：</td>
					<td><a title="选择服务类型" width="400" height="600"  class="btnLook" href="${pageContext.request.contextPath}/service/servicelookup.do" lookupGroup="servicetypeLookup" rel="servicetype">选择服务类型</a></td>
					<td>
						<input id="servicetypeId" type="hidden" name="servicetypeId" value="${serviceitem.servicetype.servicetypeId}" />
						<input id="servicename" type="text" name="servicename" maxlength="200" class="required" readonly="readonly"  size="30" value="${serviceitem.servicetype.servicename}" />
					</td>
				</tr>
			</table>
		</div>
		<div class="pageFormContent" layoutH="320">
			<div class="unit">
				<label>适用品牌车系</label>
				<input name="infoBrand.id" type="hidden" value="${serviceitem.infoBrand.id}">
				<input name="infoBrand.name" type="text" size="30" class="required" readonly="readonly" value="${serviceitem.brandname}" />
				<a title="选择品牌车系" class="btnLook" href="${pageContext.request.contextPath}/lookup/brand.do?dialogId=caraddlook_brand" rel="caraddlook_brand" lookupPk="ordercarInfoLookup" width="600" height="550" mask="true"  lookupGroup="infoBrand">品牌车系</a>
			</div>
			<div class="unit">
				<label>项目编码：</label>
				<input id="itemcode" type="text" name="itemcode" maxlength="30" class="required" value="${serviceitem.itemcode}" size="30" />
			</div>
			<div class="unit">
				<label>项目名称：</label>
				<input id="itemname" type="text" name="itemname" maxlength="30" class="required" value="${serviceitem.itemname}" size="30" />
			</div>
			<div class="unit">
				<label>拼音代码：</label>
				<input type="text" name="pinyincode" maxlength="30" value="${serviceitem.pinyincode}"  size="30" />
			</div>
			<div class="unit">
				<label>工时费(￥元)：</label>
				<input id="workhourmoney" type="text" name="workhourmoney" maxlength="30" class="required number"  value="${serviceitem.workhourmoney}" size="30" />
			</div>
			<div class="unit">
				<label>折扣系数：</label>
				<input id="discount" type="text" name="discount" maxlength="3" class="number"  size="30" value="${serviceitem.discount}" />
			</div>
			<div class="unit">
				<label>积分：</label>
				<input id="intergral" type="text" name="intergral" maxlength="30" class="digits" value="${serviceitem.intergral}" size="30" />
			</div>
		</div>
		<div class="pageFormContent" layoutH="340">
			<div class="panelBar">
				<ul class="toolBar">
					<tag:auth code="service.list.add">
						<li><a class="add" href="${pageContext.request.contextPath}/service/servicegoodslookup.do"  width="800" height="600" mask="true" rel="servicegoodsadd" id="servicegoodsLookup" lookupGroup="servicegoodsLookup"><span>添加配件</span></a></li>
					</tag:auth>
				</ul>
			</div>
			<table class="list" width="100%" >
				<thead>
					<tr>
						<th>配件编号</th>
						<th>配件名称</th>
						<th>销售价格</th>
						<th>品牌</th>
						<th>配件规格</th>
						<th>数量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="goodtbody">
					<c:forEach items="${serviceitem.sitemgoodsMappings}" var="sitemgoodsMappings" varStatus="status">
						<tr>
							<td goodid="${sitemgoodsMappings.infoGoods.goodsId}" >${sitemgoodsMappings.infoGoods.goodsCode}</td>
							<td>${sitemgoodsMappings.infoGoods.name}</td>
							<td><fmt:formatNumber value="${sitemgoodsMappings.infoGoods.price}" type="currency"/></td>
							<td>${sitemgoodsMappings.infoGoods.infoBrand.name}</td>
							<td>${sitemgoodsMappings.infoGoods.standard}</td>
							<td>
								<input type="text" name="siteGoodMap[${status.index}].number" maxlength="200" style="width:50px;" class="required number"  size="30" value="${sitemgoodsMappings.number}" />
								<input type="hidden" name="siteGoodMap[${status.index}].infoGoods.goodsId"  value="${sitemgoodsMappings.infoGoods.goodsId}"/>
								<input type="hidden" name="siteGoodMap[${status.index}].itemGoodsId"  value="${sitemgoodsMappings.itemGoodsId}"/>
							</td>
							<td>
								<a onclick="deleteLine(this)" class="btn btn-delete"><a/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="service.update.do">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
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
		var tableHtml = template("goodslist", infGoods);
		$("#goodtbody").append(tableHtml);
		goodIDS.push(infGoods.goodsId);
		setLookHref();
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
		var lookObj = $("#servicegoodsLookup");
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
	//初始值
	$(function()
	{
		goodIDS = getAttrbuteCollection("goodid");
		setLookHref();
		index = $("#goodtbody").find("tr").length;
		//筛选配件
		var obj = new Object();
		obj.id = $("[name='infoBrand.id']").val();
		obj.name = $("[name='infoBrand.name']").val();
		 setlookhref(obj);
	});

	/**
	* 品牌车系回调函数
	*/
	function setlookhref(obj)
	{
		var id = obj['id'];
		var ids = new Array();
		ids.push(id);
		//设置参数
		setlookupParams(ids,"servicegoodsLookup",'brandId');
	}
</script>