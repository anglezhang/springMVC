<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" action="${pageContext.request.contextPath}/service/servicelookup.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						服务类型名称：<input type="text" name="queryTypeName" value="${queryTypeName}"/>
					</td>
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit" id="servicelookupsubmit">检索</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="list" width="100%" layoutH="84" >
		<thead>
			<th width="50" align="center">序号</th>
			<th align="center">服务类型名称</th>
			<th align="center">选中带回</th>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="servicetype" varStatus="status">
				<tr>
					<td align="center">${status.index + 1}</td>
					<td align="center">${servicetype.servicename}</td>
					<td align="center">
						<a class="btn btn-xzdh" href="javascript:$.bringBack({servicetypeId:'${servicetype.servicetypeId}',servicename:'${servicetype.servicename}'}),getLogo({servicetypeId:'${servicetype.servicetypeId}',servicename:'${servicetype.servicename}'})" title="选择带回"></a>
						<tag:auth code="service.itemtype.delete">
							<a title="删除" target="ajaxTodo"  mask="true" href="${pageContext.request.contextPath}/service/typedetete.do?servicetypeId=${servicetype.servicetypeId}" class="btn btn-delete" callback="showDiloaAjax"></a>
						</tag:auth>	
						<tag:auth code="service.itemtype.edit">
							<a title="编辑" target="dialog" rel="serviceTypeEdit" width="400" height="300" mask="true" href="${pageContext.request.contextPath}/service/typeedit.do?servicetypeId=${servicetype.servicetypeId}" class="btn btn-edit"></a>
						</tag:auth>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
<script type="text/javascript">
	function showDiloaAjax(json)
	{
		if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
			$("#servicelookupsubmit").trigger('click');
		}
	}
</script>