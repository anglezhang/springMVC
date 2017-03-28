<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/depot/searchbill.do" method="post">
		<input type="hidden" value="${isshop }" name="isshop"/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<c:if test="${ isshop==0 }">
					<td>
						店铺名称：
						<select name="queryDeptId">
							<option value="">所有店铺</option>
							<c:forEach items="${deptList }" var="dept">
								<option value="${dept.deptId }" <c:if test="${queryDeptId==dept.deptId }">selected</c:if>>${dept.name }</option>
							</c:forEach>
						</select>
					</td>
					</c:if>
					<td>
					仓位：
					<input name="depotPosition.id" type="hidden" value="${bill.depotPosition.id }" id="position_id">
					<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk1()">清空</button></div></div>
					<input name="depotPosition.name" id="position_name" value="${ bill.depotPosition.name}" type="text" size="5" readonly="readonly"  />
					<a style="float: right;" title="选择仓位" class="btnLook" href="${pageContext.request.contextPath}/lookup/depotposition.do"  id="look_dopt_gt" width="600" height="550" lookupGroup="depotPosition">仓位</a>
			
					</td>
					<td>
						适用品牌车系：<input name="infoBrand.name" id="b_name" value="${bill.infoBrand.name }" type="text" size="5" readonly="readonly" />
							<input name="infoBrand.id" id="b_id" type="hidden" value="${ bill.infoBrand.id}">
							<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk2()">清空</button></div></div>
							<a  style="float: right;" title="选择品牌车系" class="btnLook" href="${pageContext.request.contextPath}/lookup/brand.do?backId=${queryBrandId }" rel="look_brand3" width="600" height="550" lookupGroup="infoBrand">品牌车系</a>
					</td>
					<td>
						配件类型：
						<input name="infoGoodsType.groupId" type="hidden" value="${bill.infoGoodsType.groupId }" id="type_id">
						<input  name="infoGoodsType.typeName" id="type_name" value="${bill.infoGoodsType.typeName }" type="text" size="5" readonly="readonly" />
						<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk3()">清空</button></div></div>
					
						<a  style="float: right;" title="选择配件类别" class="btnLook" href="${pageContext.request.contextPath}/lookup/goodsType.do" rel="look_gt" width="600" height="550" lookupGroup="infoGoodsType">配件类别</a>
					</td>
					<td>
						配件编号：<input type="text" size="8" name="queryGoodsCode" value="${queryGoodsCode}"/>
					</td>
					<td>
						配件名称：<input type="text" size="8" name="queryGoodsName" value="${queryGoodsName}"/>
					</td>
					<td>
						配件品牌：<input type="text" size="8" name="queryGoodsBrand" value="${queryGoodsBrand}"/>
					</td>
					<td>
					 
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
		<div class="pageFormContent" layoutH="85">
			<table class="list" width="100%" layoutH="88" >
		<thead>
			<tr>
				<th>配件编号</th>
				<th>配件名称</th>
				<th>最小单位</th>
				<th>配件品牌</th>
				<th>配件规格</th>
				<th>供应商</th>
				<th>当前库存</th>
				<th>所在店铺</th>
				<th>库位</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="s" items="${list}" var="depot">
				<tr>
					<td>${depot.goods_code}</td>
					<td>${depot.goods_name}</td>
					<td>${depot.unit}</td>
					<td>${depot.goods_brand}</td>
					<td>${depot.standard}</td>
					<td>${depot.sup_name}</td>
					<td>${depot.balance}</td>
					<td>${depot.dept_name}</td>
					<td>${depot.position}</td>
					<td>
						<tag:auth code="deopt.list.view">
								 <a title="查看详细" target="dialog" width="800" height="280" mask="true" rel="depot_view" href="${pageContext.request.contextPath}/depot/searchview.do?goodsId=${depot.goods_id}&deptId=${depot.dep_id}&position=${depot.depot_position_id}&balance=${depot.balance}" class="btn btn-ck"></a>
						</tag:auth>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list }">
			<tr><td colspan="9" style="font-size: 20px; text-align: center;color: red;">暂无库存</td></tr>
			</c:if>
		</tbody>
	</table>
		</div>
</div>
<script>
$(function(){
	var isshop='${isshop}';
	if(isshop==0){
		navTab.closeTab(553);
	}else{
		navTab.closeTab(545);
	}
})
	function qk1(){
		$("#position_id").val("");
		$("#position_name").val("");
		
	}
	function qk2(){
		$("#b_id").val("");
		$("#b_name").val("");
		
	}
		function qk3(){
		$("#type_id").val("");
		$("#type_name").val("");
		
	}
</script>