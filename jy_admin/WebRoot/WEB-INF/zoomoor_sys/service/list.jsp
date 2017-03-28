<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/service/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryItemCode" value="${queryItemCode}" />
	<input type="hidden" name="queryItemName" value="${queryItemName}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/service/list.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						项目编码：<input type="text" name="queryItemCode" value="${queryItemCode}" />
					</td>
					<td>
						项目名称：<input type="text" name="queryItemName" value="${queryItemName}" />
					</td>
					<td>
						拼音代码：<input type="text" name="queryPinyin" value="${queryPinyin}" />
					</td>
					<td>服务类型：<select class="" name="queryAppType" style="width:152px;height:21px;"  >
							<option value="">全部</option>
							<c:forEach items="${types}" var="serviceType" varStatus="s">
								<option value="${serviceType.servicetypeId}" <c:if test="${queryAppType==serviceType.servicetypeId}" >selected="selected"</c:if>>${serviceType.servicename}</option>
							</c:forEach>
						</select></td>
					<td>适用车型：<input name="infoBrand.name" id="b_name" value="${queryBrandName }" type="text" size="20" readonly="readonly" />
							<input name="infoBrand.id" id="b_id" type="hidden" value="${ queryBrandId}">
							<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk()">清空</button></div></div>
							<a  style="float: right;" title="选择品牌车系" class="btnLook" href="${pageContext.request.contextPath}/lookup/brand.do?backId=${queryBrandId }" rel="servicelook_brand3" width="600" height="550" lookupGroup="infoBrand">品牌车系</a>
					</td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="service.list.add">
				<li><a class="add" href="${pageContext.request.contextPath}/service/add.do" target="dialog" width="800" height="600" mask="true" rel="serviceadd"><span>添加</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table  class="table" width="100%" layoutH="113">
		<thead align="center">
			<tr>
				<th width="50">服务类型</th>
				<th width="100">项目编号</th>
				<th>项目名称</th>
				<th>拼音代码</th>
				<th>工时费</th>
				<th>适用品牌</th>
				<th>折扣系数</th>
				<th>积分</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="serviceitem" varStatus="status">
				<tr align="center">
					<td align="center">${serviceitem.servicetype.servicename}</td>
					<td>${serviceitem.itemcode}</td>
					<td>${serviceitem.itemname}</td>
					<td>${serviceitem.pinyincode}</td>
					<td><fmt:formatNumber value="${serviceitem.workhourmoney}" type="currency"/></td>
					<td>${serviceitem.brandname}</td>
					<td>${serviceitem.discount}</td>
					<td>${serviceitem.intergral}</td>
					<td>
						<tag:auth code="service.list.view">
							<a title="查看" target="dialog" rel="serviceItemView" width="800" height="600" mask="true" href="${pageContext.request.contextPath}/service/view.do?itemId=${serviceitem.itemId}" class="btn btn-ck"></a>
						</tag:auth>	
						<tag:auth code="service.list.detete">
							<a title="删除" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/service/detete.do?itemId=${serviceitem.itemId}" class="btn btn-delete"></a>
						</tag:auth>	
						<tag:auth code="service.list.edit">
							<a title="编辑" target="dialog" rel="serviceItemEdit" width="800" height="600" mask="true" href="${pageContext.request.contextPath}/service/edit.do?itemId=${serviceitem.itemId}" class="btn btn-edit"></a>
						</tag:auth>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>