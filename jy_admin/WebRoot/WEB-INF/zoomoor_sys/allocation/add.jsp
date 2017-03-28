<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<script id="goodslistallocation" type="text/html">
		<tr>
			<input type="hidden" id="aav_{{goodsIndex}}_zd" name="applyList" />
			<td>{{goodsCode}}</td>
			<td>{{name}}</td>
			<td>{{price}}</td>
			<td>
				0
			</td>
			<td>
				{{unitName}}
			</td>
			<td>{{brandName}}</td>
			<td>{{standard}}</td>
			<td id="source_{{goodsIndex}}_zd"></td>
			<td id="num_{{goodsIndex}}_zd"><input type="text" ondblclick="siteApply('{{goodsIndex}}_zd','{{goodsId}}');" class="required" size="10"  readonly="readonly"/></td>
			<td>
			<a onclick="siteApply('{{goodsIndex}}_zd','{{goodsId}}');" class="btn btn-xj" title="设置"><a/>				
			<a onclick="deleteLine(this),removeGoodid({{goodsId}})" class="btn btn-delete"><a/>
			</td>
		</tr>
	</script>
	<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);"  action="${pageContext.request.contextPath}/allocation/add.do?applytype=${applytype}&seachType=1" method="post">
			<table class="searchContent">
				<tr>
					<c:if test="${empty infodept||!empty seachType}">
					<td>
						店铺名称：
						<select id="de_id" onchange="getDBInfo();" name="deptId" >
								<option value="">==请选择店铺==</option>
							<c:forEach items="${deptList }" var="dept">
								<option value="${dept.deptId }" <c:if test="${ dept.deptId==infodept.deptId}">selected</c:if>>${dept.name }</option>
							</c:forEach>
							
						</select><span style="color: red">*</span>
					</td>
					</c:if>
					<c:if test="${!empty infodept && empty seachType  }">
					<td>
						店铺名称：
						<select name="deptId" disabled="disabled">
								<option value="${infodept.deptId }"selected>${infodept.name }</option>
						</select>
					</td>
					</c:if>
					<td>
						<div class="buttonActive" style="display: none"><div class="buttonContent"><button type="submit" id="dept_db_info">检索</button></div></div>
					</td>
				</tr>
			</table>
	</form>
</div>
	<form method="post" id="order_form" action="${pageContext.request.contextPath}/allocation/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone_add);">
		<input value="${infodept.deptId }" type="hidden" name="deptId" id="dept_id" class="required"/>
		<input value="${applytype}" type="hidden" name="applytype" id="applytype"/>
		<div class="pageFormContent" layoutH="120">
			<div class="panelBar">
				<ul class="toolBar">
					<tag:auth code="allocationapply.addgoods">
						<li><a class="add" href="${pageContext.request.contextPath}/service/servicegoodslookup.do"  width="800" height="600" mask="true" rel="servicegoodsadd_${infodept.deptId  }" id="servicegoodsLookup_allocation" lookupGroup="servicegoodsLookup"><span>添加配件</span></a></li>
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
						<th>来源</th>
						<th>调拨数量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="goodtbody_allocation">
					<input type="hidden"  name="applyList" />
					<c:forEach items="${applyList }" var="applylist" varStatus="s">
						<tr>
						<input type="hidden" id="aav_${applylist.applyId }" name="applyList" />
						<td goodid="${applylist.infoGoods.goodsId }" >${applylist.infoGoods.goodsCode }</td>
						<td>${applylist.infoGoods.name }</td>
						<td>${applylist.infoGoods.costPrice }</td>
						<td>
							${applylist.num}
						</td>
						<td>
							${applylist.infoGoods.paramConfig.name }
						</td>
						<td>${applylist.infoGoods.goodsBrand }</td>
						<td>${applylist.infoGoods.standard}</td>
						<td id="source_${applylist.applyId }"></td>
						<td id="num_${applylist.applyId }"><input type="text" class="required" size="10"  readonly="readonly"/></td>
						<td>
							<a onclick="siteApply('${applylist.applyId}','${applylist.infoGoods.goodsId }');" class="btn btn-xj" title="设置"><a/>
							<a onclick="deleteLine(this),removeGoodid('${applylist.infoGoods.goodsId}')" class="btn btn-delete" title="删除"><a/>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="unit">
			<table class="list" width="100%" >
				<tr>
					<td>调拨创建人：</td>
					<input name="empid" value="${sysUser.infoEmp.empId }" type="hidden"/>
					<td>${fn:length(sysUser.infoEmp.empName) > 0 ? sysUser.infoEmp.empName : sysUser.username}</td>
					<td>备注：</td>
					<td>
						<input  name="memo" type="text" class="" size="30" maxlength="50"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="allocation.save">
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
		var tableHtml = template("goodslistallocation", infGoods);
		$("#goodtbody_allocation").append(tableHtml);
		goodIDS.push(infGoods.goodsId);
		setLookHref();
	}
//打开调拨设置
function siteApply(applyId,goodsId){
		var deptId=$("#dept_id").val();
		var url="${pageContext.request.contextPath}/allocation/siteapply.action?applyId="+applyId+"&goodsId="+goodsId+"&deptId="+deptId;
		$.pdialog.open(url, "order_site_db", "调拨设置", {width: 1300, height: 600, mask:true});
}

function dialogAjaxDone_add(json){
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		if (json.navTabId){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			var applytype='${applytype}';
			$.pdialog.closeCurrent();
			$("#dept_ref"+applytype).click();
			
		}
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
		var lookObj = $("#servicegoodsLookup_allocation");
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
$(function()
	{
		goodIDS = getAttrbuteCollection("goodid");
		setLookHref();
	});	
function  getDBInfo(){
	$("#dept_id").val($("#de_id").val());
	$("#dept_db_info").click();
}	
	
		
</script>
