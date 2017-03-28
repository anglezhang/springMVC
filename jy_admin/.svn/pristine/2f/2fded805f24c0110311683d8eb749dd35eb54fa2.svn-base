<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<div class="pageFormContent" >
		<div class="tabs" currentIndex="${currentIndex }" eventType="click">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li onclick="hiddenBtn(0)"><a href="javascript:;"><span>店铺总览</span></a></li>
						<li onclick="hiddenBtn(1)"><a href="javascript:;"><span>配件总览</span></a></li>
					</ul>
				</div>
			</div>
	<div class="tabsContent">
	<div class="pageFormContent" >
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/allocationapply/list.do" id="dept_form" method="post">
			<table class="searchContent">
				<tr>
					<td>
						<span style="float: left; margin-top: 2px;">店铺名称：</span>
						<select name="deptId">
								<option value=" ">==全部店铺==</option>
							<c:forEach items="${deptList }" var="dept">
								<option value="${dept.deptId }" <c:if test="${ deptId==dept.deptId}">selected</c:if>>${dept.name }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<span style="float: left; margin-top: 2px;">申请日期：</span><input type="text" class="date" name="queryStartDate" value="${queryStartDate}"/><span style="float: left; margin-top: 2px;">-</span><input type="text" class="date"  name="queryEndDate" value="${queryEndDate}"/>
					</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit" id="s_0">检索</button></div></div>
					</td>
				</tr>
			</table>
	</form>
			<div class="pageContent">
				<div class="panelBar">
				</div>
				<table class="table" width="100%" layoutH="112">
					<thead>
						<tr align="center">
							<th>店铺名称</th>
							<th>申请项数</th>
							<th>总额</th>
							<th>申请日期</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${volist}" var="applyVo" varStatus="s">
								<tr align="center" ondblclick="goApply('${applyVo.countDeptId}','${applyVo.countDate }')" style="cursor: pointer;">
									<td>${applyVo.countDeptName}</td>
									<td>${applyVo.countNum}</td>
									<td>${applyVo.countMoney}</td>
									<td>${applyVo.countDate}</td>
									<td>
										<tag:auth code="allocation.list">
											 <a title="明细" target="dialog"  rel="zl_dept_view"  width="1200" height="700" mask="true" href="${pageContext.request.contextPath}/allocationapply/listdetail.action?deptId=${applyVo.countDeptId}&applytype=1&unitDate=${applyVo.countDate}" class="btn btn-ck"></a>
										</tag:auth>
									</td>
									
								</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
				</div>
				<div class="pageFormContent">
			<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/allocationapply/goodslist.do"  id="goods_form" method="post">
				<input type="hidden" value="${currentIndex }" id="current" name="currentIndex"/>
			<table class="searchContent">
				<tr>
					<td>
						<span style="float: left; margin-top: 2px;">配件名称：</span>
						<input type="text" class="" name="queryGoodsName" value="${queryGoodsName}"/>
					</td>
					<span style="float: left; margin-top: 2px;">配件编号：</span>
						<input type="text" class="" name="queryGoodsCode" value="${queryGoodsCode}"/>
					<td>
					</td>
					<td>
					 
						<div class="buttonActive"><div class="buttonContent"><button type="submit" id="s_1">检索</button></div></div>
					</td>
				</tr>
			</table>
	</form>
			<div class="pageContent">
				<div class="panelBar">
				</div>
				<table class="table" width="100%" layoutH="112">
					<thead>
						<tr align="center">
							<th>配件编号</th>
							<th>配件名称</th>
							<th>配件类型</th>
							<th>配件规格</th>
							<th>申请数量</th>
							<th>总额</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${goodslist}" var="goods" varStatus="s">
								<tr align="center" ondblclick="goview('${goods.countGoodsId}')" style="cursor: pointer;">
									<td>${goods.countGoodsCode}</td>
									<td>${goods.countGoodsName}</td>
									<td>${goods.countTypeShow}</td>
									<td>${goods.countStandard}</td>
									<td>${goods.countNum}</td>
									<td>${goods.countMoney}</td>
									<td>
										<tag:auth code="allocationapply.infogoods.view">
											 <a title="查看" target="dialog" rel="zl_goods_view_dialog"  width="600" height="650" mask="true" href="${pageContext.request.contextPath}/infogoods/view.action?id=${goods.countGoodsId}" class="btn btn-ck"></a>
										</tag:auth>
									</td>
									
								</tr>
						</c:forEach>
					</tbody>
				</table>	
				</div>
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
		</div>
	</div>
	<div class="formBar">
		<ul>
		<li>
			<div class="button" id="sbtn_update"><div class="buttonContent"><button type="submit">保存</button></div></div>
		</li>
		</ul>
	</div>
</div>
<script>
	function hiddenBtn(ind){
		$("#current").val(ind);
		$("#s_"+ind).click();
	}
function goview(gid){
	var url="${pageContext.request.contextPath}/infogoods/view.action?id="+gid;
	$.pdialog.open(url, "zl_goods_view_dialog", "查看", {width: 600, height: 650, mask:true});
}
function goApply(did,udate){
	var url="${pageContext.request.contextPath}/allocationapply/listdetail.action?deptId="+did+"&applytype=1&unitDate="+udate;
	$.pdialog.open(url, "zl_dept_view", "申请明细", {width: 1200, height: 700, mask:true});
}
$(function(){
	navTab._closeOtherTab();
})	
</script>

