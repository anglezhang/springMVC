<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/service/customerorder/list.do">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" /
		<input type="hidden" tabCurrenIndex name="currenIndex"  value="${currenIndex}" />
</form>
<div class="pageContent">
	<div class="pageHeader">
		<form id="cutomerorderform" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/service/customerorder/list.do" method="post">
			<input type="hidden" name="pageNum" value="${pager.pageNum}" />
			<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			<input type="hidden" id="currenIndex" tabCurrenIndex name="currenIndex" value="${currenIndex}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							服务订单编号：<input type="text" name="queryOrderNO" value="${queryOrderNO}"/>
						</td>
						<td>
							车牌号：<input type="text" name="queryPlatenum" value="${queryPlatenum}"/>
						</td>
						<td>
							客户姓名：<input type="text" name="queryCusName" value="${queryCusName}"/>
						</td>
						<td style="display:none">
							服务进度：<select id="cutomerorderqueryStatus" name="queryStatus"  style="width:120px;">
										<option value="-1"  <c:if test="${queryStatus==-1}" > selected="selected"</c:if> >全部</option>
										<option value="0"  <c:if test="${queryStatus==0}" > selected="selected"</c:if> >进行中</option>
										<option value="1" <c:if test="${queryStatus==1}" > selected="selected"</c:if> >结算(封单)</option>
										<option value="2" <c:if test="${queryStatus==2}" > selected="selected"</c:if> >挂账</option>
										<option value="3" <c:if test="${queryStatus==3}" > selected="selected"</c:if> >已结清</option>
									</select>
						</td>
						<td>
							服务位置：<select name="queryFixplace"  style="width:120px;">
											<option value="">全部</option>
											<option value="0" <c:if test="${queryFixplace eq 0}" > selected="selected"  </c:if> >${dept.name}</option>
											<c:forEach items="${addrList}" var="addr" varStatus="s">
												<option value="${addr.addrId}" <c:if test="${queryFixplace==addr.addrId}" > selected="selected" </c:if> >${addr.address} </option>
											</c:forEach>
										</select>
						</td>
					</tr>
					<tr>
						<td>
							开单时间：<input type="text" name="queryOrderBeginTime" value="${queryOrderBeginTime}" class="date" datefmt="yyyy-MM-dd HH:mm:ss" readonly="readonly" />~<input type="text" name="queryOrderEndTime" value="${queryOrderEndTime}" class="date" datefmt="yyyy-MM-dd HH:mm:ss" readonly="readonly" />
						</td>
						<td>
							接待人：<input type="text" name="queryOperator" value="${queryOperator}"/>
						</td>
						<td>
							维修人：<input type="text" name="queryFixMan" value="${queryFixMan}"/>
						</td>
						<td colspan="2">
							 
							<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="cusorder.create.custome">
				<li><a class="add" rel="createcustomerEntreusAdd" href="${pageContext.request.contextPath}/customer/createorder.do" target="dialog"  width="1200" height="600" mask="true"><span>创建委托单</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<div class="panelBar">
		<ul class="toolBar">
			<li class="selected"><a href="#" currenIndex="-1"><span>全部(${countAll})</span></a></li>
			<li ><a currenIndex="0" href="#"><span>进行中(<c:if test="${empty count0}" >0</c:if> <c:if test="${!empty count0}" >${count0}</c:if>)</span></a></li>
			<li ><a currenIndex="1" href="#"><span>已结算(<c:if test="${empty count1}" >0</c:if> <c:if test="${!empty count1}" >${count1}</c:if>)</span></a></li>
			<li ><a currenIndex="2" href="#"><span>挂账(<c:if test="${empty count2}" >0</c:if> <c:if test="${!empty count2}" >${count2}</c:if>)</span></a></li>
			<li ><a currenIndex="3" href="#"><span>已结清(<c:if test="${empty count3}" >0</c:if> <c:if test="${!empty count3}" >${count3}</c:if>)</span></a></li>
		</ul>
	</div>
	<div>
		<%@ include file="./alllist.jsp" %>
	</div>
</div>
<script type="text/javascript">
function gotofulsh() 
{
	$("[currenIndex]").each(function(index, el) {
		var obj = $(el);
			obj.bind('click',  function(event) {
			var objIndex = $(this).attr("currenIndex");
			$("#cutomerorderqueryStatus").val(objIndex);
			$("#cutomerorderform").submit();
		});		
	});

}
$(function()
{
	gotofulsh();
});
</script>