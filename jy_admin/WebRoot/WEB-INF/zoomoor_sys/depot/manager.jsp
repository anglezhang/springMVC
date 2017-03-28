<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<script id="goodslist_bill_depot" type="text/html">
		<tr>
			<input value="{{goodsId}}" name="deptvo[{{goodsIndex}}].goodsId" type="hidden"/>
			<input value="{{unitId}}" name="deptvo[{{goodsIndex}}].unitId" type="hidden"/>
			<input value="{{tagId}}" name="deptvo[{{goodsIndex}}].tagId" type="hidden"/>
			<input value="{{comeId}}" name="deptvo[{{goodsIndex}}].comeId" type="hidden"/>
			<input value="0" name="deptvo[{{goodsIndex}}].listId" type="hidden"/>
			<td>{{goodsCode}}</td>
			<td>{{name}}</td>
			<td>{{costprice}}</td>
			<td>
				{{unitName}}
			</td>
			<td>{{brandName}}</td>
			<td>{{standard}}</td>
			<td ><input id="num_{{goodsId}}" type="text" ondblclick="openCWwx('{{tagId}}','{{comeId}}','{{summaryId}}','{{goodsId}}')" name="deptvo[{{goodsIndex}}].countNum" class="required" size="10"  readonly="readonly"/></td>
			<td>
				<input type="hidden" id='position_{{goodsId}}' name="positions" />
				<a title="设置" href="javascript:void(0);" onclick="openCWwx('{{tagId}}','{{comeId}}','{{summaryId}}','{{goodsId}}')" class="btn btn-xj"></a>
				<a onclick="deleteLine(this),removeGoodid({{goodsId}})" class="btn btn-delete"><a/>
			</td>
		</tr>
	</script>
<div class="pageContent">
	<div class="pageHeader">
			<table class="searchContent">
				<tr>
					<td>
						 &nbsp;&nbsp;&nbsp;操作类型：
						<select name="" onchange="getSummary()" id="stype" <c:if test="${isalone==1}">disabled="disabled"</c:if>>
							<option value="false" <c:if test="${!SType }">selected</c:if>>出库</option>
							<option value="true"  <c:if test="${SType }">selected</c:if>>入库</option>
						</select>
					</td>
					<td >
						 &nbsp;&nbsp;&nbsp;业务摘要：
						<select name="" id="summary_name" onchange="getSummaryId()" >
							<c:forEach items="${summaryList }" var="summary">
									<c:if test="${isalone==1}">
										<c:if test="${summary.summary_id==5 || summary.summary_id==6}">
											<option value="${summary.summary_id }" <c:if test="${summary.summary_id==summaryId }">selected</c:if> >${summary.name }</option>
										</c:if>
									</c:if>
									<c:if test="${isalone!=1}">
										<option value="${summary.summary_id }" <c:if test="${summary.summary_id==summaryId }">selected</c:if> >${summary.name }</option>
									</c:if>
							</c:forEach>
						</select>
					</td>
					<td>
						<tag:auth code="depot.opensearch,depot.opensearch.wx">
							<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="openSeachNo();">设置筛选条件</button></div></div>
						</tag:auth>
				</td>
				</tr>
			</table>
</div>
	<form method="post" id="depot_form" action="${pageContext.request.contextPath}/depot/depotsave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone_manager);">
		<input value="${summaryId }" type="hidden" id="summary_id_name" name="summaryId" />
		<input value="" type="hidden" id="bill_tagId" />
		<input value="" type="hidden" id="bill_comeId"  />
		<input value="0" type="hidden" id="" name="billId" />
		<div class="pageFormContent" layoutH="115">
			<div class="panelBar">
			</div>
			<table class="list" width="100%" >
				<thead>
					<!-- 采购头部信息 -->
					<tr id="cg_thead"  align="center" style="display: none">
						<th>配件编号</th>
						<th>配件名称</th>
						<th>成本价</th>
						<th>采购不含税单价</th>
						<th>采购含税单价</th>
						<th>采购数量</th>
						<th>最小单位</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<th>当前库存</th>
						<th width="150">含税</th>
						<th width="150">确认入库数量</th>
						<th width="150">操作</th>
					</tr>
					<!-- 调拨头部信息 -->
					<tr id="db_thead" align="center" style="display: none">
						<th>调拨单号</th>
						<th>配件编号</th>
						<th>配件名称</th>
						<th>成本价</th>
						<th>调拨数量</th>
						<th>最小单位</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<th>调拨来源</th>
						<th>当前库存</th>
						<%-- <c:if test="${summaryId==3 }">
						<th>调拨日期</th>
						</c:if> --%>
						<th  width="150"> 确认出库数量</th>
						<th width="150">操作</th>
					</tr>
					<!-- 维修领料头部信息 -->
					<tr id="wx_thead"  align="center" style="display: none">
						<th>服务单号</th>
						<c:if test="${summaryId==6 }">
						<th>出库单号</th>
						<th>出库时间</th>
						</c:if>
						<th>车牌号</th>
						<th>品牌车系名称</th>
						<th>发动机号</th>
						<th>车架vin号</th>
						<th>颜色</th>
						<th>最新里程</th>
						<th>排量</th>
					</tr>
					<!--盘点头部信息 -->
					<tr id="pd_thead" align="center" style="display: none">
						<th>盘点单号</th>
						<th>配件编号</th>
						<th>配件名称</th>
						<th>成本价</th>
						<th>最小单位</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<th>盘点数量</th>
						<th>当前库存</th>
						<c:if test="${summaryId==4}"><th>盘盈数量</th></c:if>
						<c:if test="${summaryId==8}"><th>盘亏数量</th></c:if>
						<th>操作</th>
					</tr>
					<!--领料头部信息 -->
					<tr id="ly_thead" align="center" style="display: none">
						<th>配件编号</th>
						<th>配件名称</th>
						<th>成本价</th>
						<th>最小单位</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<th>当前库存</th>
						<th width="150">数量</th>
						<th width="150">操作</th>
					</tr>
				</thead>
				<input type="hidden"  name="positions" />
				<tbody id="goodtbody_bill"></tbody>
			</table>
			<table class="list" width="100%" id="wx_table_bill" style="display: none">
					<thead>
					<tr id="pd_thead" align="center">
						<th>服务编码</th>
						<th>服务名称</th>
						<th>服务类型</th>
						<th>工时费</th>
					</tr>
					</thead>
					<tbody id="tbody_bill_service"></tbody>
			</table>
			<div class="panelBar" id="wx_div_bill" style="display: none">
				<ul class="toolBar">
					<c:if test="${summaryId!=6 }">
					<tag:auth code="depot.addgoods,depot.addgoods.wx">
						<li><a class="add"  width="800" height="600" mask="true" rel="servicegoodsadd_bill_depot" id="servicegoodsLookup_manager" href="${pageContext.request.contextPath}/service/servicegoodslookup.do" lookupGroup="servicegoodsLookup"><span>添加配件</span></a></li>
					</tag:auth>
					</c:if>
				</ul>
			</div>
			<table class="list" width="100%" id="wx_goodtable_bill" style="display: none">
				<thead>
					<tr align="center">
						<th>配件编号</th>
						<th>配件名称</th>
						<th>成本价</th>
						<th>最小单位</th>
						<th>配件品牌</th>
						<th>配件规格</th>
						<c:if test="${summaryId==6 }">
						<th>出库数量</th>
						<th>退库数量</th>
						</c:if>
						<c:if test="${summaryId!=6 }">
						<th>数量</th>
						</c:if>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="goodtbody_depot_bill"></tbody>
			</table>
		</div>
			
		<div class="unit">
			<table class="list" width="100%" >
				<tr>
					<td>操作人：</td>
					<input name="InfoEmp.empId" value="${sysUser.infoEmp.empId }" type="hidden"/>
					<td>${fn:length(sysUser.infoEmp.empName) > 0 ? sysUser.infoEmp.empName : sysUser.username}</td>
					<td>经办人：</td>
					<td>
						<select name="empId">
							<c:forEach items="${empList }" var="emplist">
								<option value="${emplist.empId }">${emplist.empName }</option>
							</c:forEach>
						</select>
						
					</td>
					<td>备注：</td>
					<td><input value="" name="memo" type="text" maxlength="50" size="50" /></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="depot.depotsave,depot.depotsave.wx">
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="goSaveDepot()">保存</button></div></div>
				</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<input type="hidden" id="managerbarandId">
<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/depot/manager.do" method="post">
		<input value="" id="subStype" name="SType" type="hidden"/>
		<input value="" id="subName" name="subName" type="hidden"/>
		<input value="${isalone}" id="isalone" name="isalone" type="hidden"/>
		<div class="buttonActive" style="display: none"><div class="buttonContent"><button type="submit"  id="resf_id"></button></div></div>
</form>
<script>
	var brandId = "";
	function getSummary(){
		var stype=$("#stype").val();
		var url="${pageContext.request.contextPath}/depot/getsummary.ajax";
		$.post(url,{stype:stype},function(data){
			var objArray= new Array();
			objArray=eval(data);
			var summary_html="";
			for(var i=0;i<objArray.length;i++){
				summary_html+="<option value='"+objArray[i].summary_id+"'>"+objArray[i].name+"</option>";
			}
			$("#summary_name").html(summary_html);
			$("#summary_id_name").val($("#summary_name").val());	
		});
		
	
	}
//打开搜索业务单号页面
function openSeachNo(){
	$("#subStype").val($("#stype").val());
	$("#subName").val($("#summary_name").val());
	var isalone='${isalone}';
	$("#isalone").val(isalone);
	$("#resf_id").click();
	var summaryId=$("#summary_name").val();
	var url="${pageContext.request.contextPath}/depot/opensearch.action?summaryId="+summaryId;
	$.pdialog.open(url, "search_no_yw", "设置筛选条件", {width: 800, height: 500, mask:true});
}
$(function(){
	$("#summary_id_name").val($("#summary_name").val());
	var isalone='${isalone}';
	
	if(isalone==1){
		navTab.closeTab(499);
	}else{
		navTab.closeTab(508);
	}
	navTab._closeOtherTab();
	
	
	
})

function getSummaryId(){
	$("#summary_id_name").val($("#summary_name").val());
}

function dialogAjaxDone_manager(json){
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		if (json.navTabId){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		var bNo=json.b_no;
		var summaryId=json.summaryId;
		$("#resf_id").click();
		if(summaryId!=4&&summaryId!=8&&summaryId!=6){
			var url="${pageContext.request.contextPath}/depot/confirmprint.action?summaryId="+summaryId+"&bNo="+bNo;
			$.pdialog.open(url, "depot_confrimprint_add", "是否打印", {width: 500, height: 280, mask:true});
		}
	}
}
function goSaveDepot(){
	var summaryId=$("#summary_name").val();
	var bodyHtml=$("#goodtbody_bill").html();
	if(bodyHtml==""||goodtbody_bill==null){
			var errorMsg = new Object();
			errorMsg.statusCode = 300;
			errorMsg.message = "请选择出入库信息";
			dialogAjaxDone(errorMsg);
	}else{
		if(summaryId==5||summaryId==6){
		var bodydepotHtml=$("#goodtbody_depot_bill").html();
		if(bodydepotHtml==""||bodydepotHtml==null){
			var errorMsg = new Object();
			errorMsg.statusCode = 300;
			errorMsg.message = "请选择出入库信息";
			dialogAjaxDone(errorMsg);
			return ;
		}else{
			$("#depot_form").submit();
		}
		
		}else{
			$("#depot_form").submit();
		}
	}
}


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
		var tagId=$("#bill_tagId").val();
		var comeId=$("#bill_comeId").val();
		var summaryId=$("#summary_id_name").val();
		infGoods.tagId=tagId;
		infGoods.comeId=comeId;
		infGoods.summaryId=summaryId;
		infGoods.goodsIndex = index++;
		//设置服务配件的id和数量
		/* template.helper('formatNumber',function(num)
		{
			num = parseFloat(num);
			var newNum = formatCurrencyTenThou(num);
			return "￥" + newNum;
		}); */
		var tableHtml = template("goodslist_bill_depot", infGoods);
		$("#goodtbody_depot_bill").append(tableHtml);
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
		var lookObj = $("#servicegoodsLookup_manager");
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
		var brandId = $("#managerbarandId").val();
		lookObj.prop("href",hrefVal + "&brandId=" + brandId);
	}
<c:if test="${summaryId!=6 }">	
$(function()
	{
		goodIDS = getAttrbuteCollection("goodid");
		setLookHref();
	});	
</c:if>	
function  openCWwx(tag_id,come_id,summid,goodsid,num,inx){
		if(summid==1||summid==3||summid==6){
			var url="${pageContext.request.contextPath}/depot/openposition.action?tagId="+tag_id+"&comeId="+come_id+"&num="+num+"&summaryId="+summid+"&inx="+inx;
			$.pdialog.open(url, "depot_position_dialog", "设置", {width: 600, height: 650, mask:true});
		}else{
			var url="${pageContext.request.contextPath}/depot/openbill.action?goodsId="+goodsid+"&tagId="+tag_id+"&iswx=1";
			$.pdialog.open(url, "depot_bill_dialog", "设置", {width: 1000, height: 650, mask:true});
		}
}
</script>