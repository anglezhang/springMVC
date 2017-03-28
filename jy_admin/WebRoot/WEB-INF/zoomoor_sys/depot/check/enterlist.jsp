<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageContent">
	<c:if test="${empty lookType }">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/depot/checkentering.do" method="post">
			<table class="searchContent">
				<tr>
					<td>
					&nbsp;&nbsp;&nbsp;<span style="float:left; margin-top: 5px;">盘点单号：</span>
					<input name="depotCheckVo.checkNo" style="float: left;" type="text" size="20" id="c_name" value="${checkNo }" readonly="readonly" class='required' />
					<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk_c()">清空</button></div></div>
					<a style="float: right;" title="选择盘点单号" class="btnLook" href="${pageContext.request.contextPath}/checklookup/checkno.ajax?isenter=1"  id="look_checkNo" width="600" height="550" lookupGroup="depotCheckVo">盘点单号</a>
				
					</td>
					<td>
						<tag:auth code="depot.entercheck">
							<div class="buttonActive"><div class="buttonContent"><button type="submit" id="depotEnterCheck_list">查询</button></div></div>
						</tag:auth>
				</td>
				</tr>
			</table>
			</form>
</div>
</c:if>
	<form method="post" id="depot_enter_check_form" action="${pageContext.request.contextPath}/depot/depotcheckenter.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone_manager);">
		<div class="pageFormContent" layoutH="90">
			<div class="panelBar">
			</div>
			<table class="list" width="100%" >
				<thead>
					<!-- 采购头部信息 -->
					<tr id="cg_thead"  align="center">
						<th>配件编号</th>
						<th>配件名称</th>
						<th>配件规格</th>
						<th>货号</th>
						<th>配件品牌</th>
						<th>最小单位</th>
						<th>库位</th>
						<th>帐面数量</th>
						<th>实盘数量</th>
						<c:if test="${empty lookType }"><th>盘点数量</th></c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${depotCheckEnterList }" var="entercheck" varStatus="s">
					<tr align="center">
						<input value="${entercheck.id}" type="hidden" id="" name="depotVo[${s.index }].checkId" />
						<td>${entercheck.goods_code }</td>
						<td>${entercheck.name }</td>
						<td>${entercheck.standard }</td>
						<td>${entercheck.art_no }</td>
						<td>${entercheck.goods_brand }</td>
						<td>${entercheck.unit }</td>
						<td>${entercheck.position }</td>
						<td><fmt:formatNumber value="${entercheck.account_num }" pattern="#0.0000"/></td>
						<td><fmt:formatNumber value="${entercheck.fact_num }" pattern="#0.0000"/></td>
						<c:if test="${empty lookType }"><td><input  type="text" onKeyUp="clearNoNum(event,this)" onBlur="checkNum(this)"  name="depotVo[${s.index }].checkNum" class="number"  size="5"/></td></c:if>
											
					</tr>
					</c:forEach>
				
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<c:if test="${empty lookType }">
				<tag:auth code="depot.depotcheckenter">
				<c:if test="${fn:length(depotCheckEnterList)>0 }">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">录入</button></div></div>
					</li>
				</c:if>
				</tag:auth>
				</c:if>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script>
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
		$("#depotEnterCheck_list").click();
	}
}

function getNum(obj,bnum){
	var objval=parseInt($(obj).val());
	var balance=parseInt(bnum);
	if(objval>balance){
		$(obj).val(balance);
	}
}
function qk_c(){
		$("#c_name").val("");
	}
</script>