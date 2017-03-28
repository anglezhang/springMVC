<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>	
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/service/order/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
</form>
<div class="pageHeader">
	<form id="customerorderlistform" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/service/order/list.do" method="post">
		<input type="hidden" name="pageNum" value="${pager.pageNum}" />
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" /
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						客户信息：<input type="text" name="queryTel" value="${queryTel}" placeholder="姓名或电话"/>
					</td>
					<td>
						车牌号：<input type="text" name="queryPlatenum" value="${queryPlatenum}"/>
					</td>
					<td>
						服务类型：<select  style="width:152px;height:21px" name="queryAppType">
							<option value="">全部</option>
							<c:forEach items="${serviceItem}" var="serviceType" varStatus="s">
								<option value="${serviceType.servicetypeId}" <c:if test="${queryAppType==serviceType.servicetypeId}" >selected="selected"</c:if>>${serviceType.servicename}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						来源：<select  style="width:152px;height:21px"name="querySource">
								<option value="">全部</option>
								<option value="0" <c:if test="${querySource==0}" >selected="selected"</c:if>>微信</option>
								<option value="1" <c:if test="${querySource==1}" >selected="selected"</c:if>>PC后台</option>
								<option value="2" <c:if test="${querySource==2}" >selected="selected"</c:if> >客户端</option>
							</select>
					</td>
					
				</tr>
				<tr>
					<td>
						开单时间：<input type="text" class="date"  readonly="readonly" datefmt="yyyy-MM-dd HH:mm:ss" name="queryBegintime" value="${queryBegintime}" > ~ <input type="text" class="date"  readonly="readonly" datefmt="yyyy-MM-dd HH:mm:ss" name="queryEndtime" value="${queryEndtime}" >
					</td>
					<td>
						预约时间：<input type="text" class="date"  readonly="readonly" datefmt="yyyy-MM-dd HH:mm:ss" name="querySellstart" value="${querySellstart}" > ~ <input type="text" class="date"  readonly="readonly" datefmt="yyyy-MM-dd HH:mm:ss" name="querySellend" value="${querySellend}" >
					</td>
					<td style="display:none;">
						预约状态：
						<select  id="customerorderlistqueryStatus"  style="width:152px;height:21px" name="queryStatus">
							<option value="">所有预约状态</option>
							<option value="0" <c:if test="${queryStatus == 0}">selected="selected"</c:if> >待确认</option>
							<option value="1" <c:if test="${queryStatus == 1}">selected="selected"</c:if> >已确认</option>
							<option value="2" <c:if test="${queryStatus == 2}">selected="selected"</c:if> >已履约</option>
							<option value="4" <c:if test="${queryStatus == 4}">selected="selected"</c:if> >已取消</option>
						</select>
					</td>
					<td>
						 
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="panelBar">
	<ul class="toolBar">
		<li class="selected"><a href="#" currenIndex=""><span>全部(${counAll})</span></a></li>
		<li ><a currenIndex="0" href="#"><span>待确认(${count0})</span></a></li>
		<li ><a currenIndex="1" href="#"><span>已确认(${count1})</span></a></li>
		<li ><a currenIndex="2" href="#"><span>已履约(${count2})</span></a></li>
		<li ><a currenIndex="4" href="#"><span>已取消(${count3})</span></a></li>
	</ul>
</div>
<div>
	<%@ include file="./alllist.jsp" %>
</div>
<script type="text/javascript">
function gotofulsh() 
{
	$("[currenIndex]").each(function(index, el) {
		var obj = $(el);
		obj.bind('click',  function(event) {
			var objIndex = $(this).attr("currenIndex");
			$("#customerorderlistqueryStatus").val(objIndex);
			$("#customerorderlistform").submit();
		});		
	});

}
$(function()
{
	gotofulsh();
});
</script>