<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<div class="unit">
		<table class="list" width="100%" >
			<tr>
				<td>服务类型：${serviceitem.servicetype.servicename}</td>
				<td></td>		
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div class="pageFormContent" layoutH="320">
		<div class="unit">
			<label>适用品牌车系</label>
			${serviceitem.brandname}
		</div>
		<div class="unit">
			<label>项目编码：</label>
			${serviceitem.itemcode}
		</div>
		<div class="unit">
			<label>项目名称：</label>
			${serviceitem.itemname}
		</div>
		<div class="unit">
			<label>拼音代码：</label>
			${serviceitem.pinyincode}
		</div>
		<div class="unit">
			<label>工时费(￥元)：</label>
			${serviceitem.workhourmoney}
		</div>
		<div class="unit">
			<label>折扣系数：</label>
			${serviceitem.discount}
		</div>
		<div class="unit">
			<label>积分：</label>
			${serviceitem.intergral}
		</div>
	</div>
	<div class="pageFormContent" layoutH="340">
		<table class="list" width="100%" >
			<thead>
				<tr>
					<th>配件编号</th>
					<th>配件名称</th>
					<th>销售价格</th>
					<th>品牌</th>
					<th>配件规格</th>
					<th>数量</th>
				</tr>
			</thead>
			<tbody id="goodtbody">
				<c:forEach items="${serviceitem.sitemgoodsMappings}" var="sitemgoodsMappings" varStatus="status">
					<tr>
						<td>${sitemgoodsMappings.infoGoods.goodsCode}</td>
						<td>${sitemgoodsMappings.infoGoods.name}</td>
						<td><fmt:formatNumber value="${sitemgoodsMappings.infoGoods.price}" type="currency"/></td>
						<td>${sitemgoodsMappings.infoGoods.infoBrand.name}</td>
						<td>${sitemgoodsMappings.infoGoods.standard}</td>
						<td>${sitemgoodsMappings.number}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
</div>