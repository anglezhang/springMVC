<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/service/servicegoodslookup.do">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="queryGoodsCode" value="${queryGoodsCode}" />
		<input type="hidden" name="queryName" value="${queryName}" />
		<input type="hidden" name="queryBand" value="${queryBand}" />
		<input type="hidden" name="brandId" value="${brandId}" />
	</form>
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/service/servicegoodslookup.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="goodIDS" value="${goodIDS}" />
		<input type="hidden" name="brandId" value="${brandId}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						配件编号：<input type="text" name="queryGoodsCode" value="${queryGoodsCode}"/>
					</td>
					<td>
						配件名称/拼音：<input type="text" name="queryName" value="${queryName}"/>
					</td>
					<td>
						配件品牌：<input type="text" name="queryBand" value="${queryBand}"/>
					</td>
				</tr>
				<tr>
					<td>
						配件类别：<select name="goodsType" style="width:152px;height:21px">
									<option value="" selected="selected">全部</option>
									<c:forEach items="${types}" var="type" varStatus="s">
										<option value="${type.groupId}" >${type.typeName}</option>
									</c:forEach>
								</select>
					</td>
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<!--<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="service.list.add">
				<li><a class="add" href="${pageContext.request.contextPath}/service/servicetypeadd.do?dialogId=servicetypeadd" target="dialog" width="400" height="300" mask="true" rel="servicetypeadd"><span>添加</span></a></li>
			</tag:auth>
		</ul>
	</div>-->
	<table class="list" width="100%" layoutH="88" >
		<thead>
			<tr>
				<th>配件编号</th>
				<th>配件类别</th>
				<th>配件名称</th>
				<th>配件拼音</th>
				<th>最小单位</th>
				<th>配件品牌</th>
				<th>成本价</th>
				<th>配件规格</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="infoGoods">
				<tr>
					<td>${infoGoods.goodsCode}</td>
					<td>${infoGoods.infoGoodsType.typeName}</td>
					<td>${infoGoods.name}</td>
					<td>${infoGoods.pinyin}</td>
					<td>${infoGoods.paramConfig.name}</td>
					<td>${infoGoods.infoBrand.name}</td>
					<td><fmt:formatNumber value="${infoGoods.costPrice}" type="currency"/></td>
					<td>${infoGoods.standard}</td>
					<td><a class="btn btn-xzdh" href="javascript:$.bringBack({goodsId:'${infoGoods.goodsId}',goodsCode:'${infoGoods.goodsCode}',name:'${infoGoods.name}',brandName:'${infoGoods.infoBrand.name}',standard:'${infoGoods.standard}',price:'${infoGoods.price}',discount:'${infoGoods.discount}',intergral:'${infoGoods.intergral}'}),setTable({goodsId:'${infoGoods.goodsId}',goodsCode:'${infoGoods.goodsCode}',name:'${infoGoods.name}',unitId:'${infoGoods.paramConfig.unitId }',unitName:'${infoGoods.paramConfig.name }',brandName:'${infoGoods.infoBrand.name}',standard:'${infoGoods.standard}',price:'${infoGoods.costPrice}',discount:'${infoGoods.discount}',intergral:'${infoGoods.intergral}'})" title="选择带回"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>