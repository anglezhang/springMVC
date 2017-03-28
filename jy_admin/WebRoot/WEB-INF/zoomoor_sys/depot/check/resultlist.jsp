<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageContent">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/depot/checkresult.do" method="post">
			<table class="searchContent">
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;<span style="float:left; margin-top: 5px;">盘点单号：</span>
						<input name="depotCheckVo.checkNo" style="float: left;" type="text" size="20" id="r_name" value="${checkNo }" readonly="readonly" class='required' />
						<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk_r()">清空</button></div></div>
						<a style="float: right;" title="选择盘点单号" class="btnLook" href="${pageContext.request.contextPath}/checklookup/checkno.ajax"  id="look_checkNo" width="600" height="550" lookupGroup="depotCheckVo">盘点单号</a>
					</td>
					<td>
						<tag:auth code="depot.entercheck">
							<div class="buttonActive"><div class="buttonContent"><button type="submit" id="depotResultCheck_list">查询</button></div></div>
						</tag:auth>
				</td>
				</tr>
			</table>
			</form>
</div>
	<form method="post" id="depot_result_check_form" action="${pageContext.request.contextPath}/depot/depotcheckresult.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone_manager);">
		<input name="checkNo" value="${checkNo }" type="hidden"/>
		<div class="pageFormContent" layoutH="115">
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
						<th>账面数量</th>
						<th>实盘数量</th>
						<th>盘盈/亏</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${depotCheckResultList }" var="resultcheck" varStatus="s">
					<tr align="center">
						<input value="${resultcheck.id}" type="hidden" id="" name="depotVo[${s.index }].checkId" />
						<td>${resultcheck.goods_code }</td>
						<td>${resultcheck.name }</td>
						<td>${resultcheck.standard }</td>
						<td>${resultcheck.art_no }</td>
						<td>${resultcheck.goods_brand }</td>
						<td>${resultcheck.unit }</td>
						<td>${resultcheck.position }</td>
						<td><fmt:formatNumber value="${resultcheck.account_num }" pattern="#0.0000"/></td>
						<td><fmt:formatNumber value="${resultcheck.fact_num }" pattern="#0.0000"/></td>
						<td><fmt:formatNumber value="${resultcheck.balance}" pattern="#0.0000"/></td>
					</tr>
					</c:forEach>
				
				</tbody>
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
				<%-- <tag:auth code="depot.depotcheckresult">
				<c:if test="${fn:length(depotCheckResultList)>0 }">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">入账</button></div></div>
					</li>
				</c:if>
				</tag:auth> --%>
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
		$("#depotResultCheck_list").click();
	}
}
function qk_r(){
		$("#r_name").val("");
	}
</script>