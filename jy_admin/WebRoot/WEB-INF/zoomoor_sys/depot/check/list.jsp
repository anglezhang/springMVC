<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<div class="pageContent">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/depot/checklist.do" method="post">
			<table class="searchContent">
				<tr>
					<td>
						 &nbsp;&nbsp;&nbsp;店铺：
						<select name="deptId">
							<c:forEach items="${deptList}" var="dept">
								<option value="${dept.deptId }" <c:if test="${deptId==dept.deptId }">selected</c:if>>${dept.name }</option>
							</c:forEach>
						</select>
					</td>
					<td >
					 &nbsp;&nbsp;&nbsp;<span style="float:left; margin-top: 5px;">仓位：</span>
					<input name="depotCheckVo.positions" type="hidden" value="${posids }" id="b_id">
					<input name="depotCheckVo.posName" style="float: left;" type="text" size="20" id="b_name" value="${posname }" readonly="readonly" class='required' />
					<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk_check()">清空</button></div></div>
					<a style="float: right;" title="选择仓位" class="btnLook" href="${pageContext.request.contextPath}/checklookup/depotposition.do"  id="look_gt" width="600" height="550" lookupGroup="depotCheckVo">仓位</a>
				</td>
					<td>
						<tag:auth code="depot.check">
							<div class="buttonActive"><div class="buttonContent"><button type="submit" id="depotCheck_list">读取帐面信息</button></div></div>
						</tag:auth>
				</td>
				</tr>
			</table>
			</form>
</div>
	<form method="post" id="depot_check_form" action="${pageContext.request.contextPath}/depot/depotchecksave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone_manager);">
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
						<th>当前库存</th>
						<th>库位</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${depotCheckList }" var="depotcheck" varStatus="s">
					<input value="${depotcheck.come_id}" type="hidden" id="" name="depotVo[${s.index }].comeId" />
					<input value="${depotcheck.goods_id}" type="hidden" id="" name="depotVo[${s.index }].goodsId" />
					<input value="${depotcheck.depot_position_id}" type="hidden" id="" name="depotVo[${s.index }].positionId" />
					<input value="${depotcheck.balance}" type="hidden" id="" name="depotVo[${s.index }].accNum" />
					<tr align="center">
						<td>${depotcheck.goods_code }</td>
						<td>${depotcheck.name }</td>
						<td>${depotcheck.standard }</td>
						<td>${depotcheck.art_no }</td>
						<td>${depotcheck.goods_brand }</td>
						<td>${depotcheck.unit }</td>
						<td>${depotcheck.balance }</td>
						<td>${depotcheck.position }</td>					
					</tr>
					</c:forEach>
				
				</tbody>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="depot.depotchecksave">
				<c:if test="${fn:length(depotCheckList)>0 }">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">盘点</button></div></div>
					</li>
				</c:if>
				</tag:auth>
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
		$("#depotCheck_list").click();
	}
}
function qk_check(){
		$("#b_name").val("");
		$("#b_id").val("");
		
	}
</script>