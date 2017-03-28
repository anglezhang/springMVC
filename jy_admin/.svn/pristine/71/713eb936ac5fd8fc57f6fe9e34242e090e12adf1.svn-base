<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/depot/depotDetail.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryAllocationNo" value="${queryAllocationNo}" />
	<input type="hidden" name="queryEmpName" value="${queryEmpName}" />
	<input type="hidden" name="queryDeptId" value="${queryDeptId}" />
	<input type="hidden" name="queryStartDate" value="${queryStartDate}" />
	<input type="hidden" name="queryEndDate" value="${queryEndDate}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/depot/depotDetail.do" method="post">
		<input value="${iszb }" name="iszb" type="hidden"/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						仓库：
						<c:if test="${iszb!=0 }">
						<select name="queryDeptId" id="queryDeptId">
							<option value="">所有店铺</option>
							<c:forEach items="${deptList }" var="dept">
									<option value="${dept.deptId }" <c:if test="${queryDeptId==dept.deptId }">selected</c:if>>${dept.name }</option>
							</c:forEach>
						</select>
						</c:if>
						<c:if test="${iszb==0 }">
						<select name="queryDeptId" id="queryDeptId">
							<c:forEach items="${deptList }" var="dept">
									<c:if test="${queryDeptId==dept.deptId }">
									<option value="${dept.deptId }" >${dept.name }</option>
									</c:if>
							</c:forEach>
						</select>
						</c:if>
					</td>
					<td>
						时间：<input type="text" class="date" name="queryStartDate" value="${queryStartDate}"/>-<input type="text" class="date"  name="queryEndDate" value="${queryEndDate}"/>
					</td>
					<td>
						业务摘要：
						<select name="summaryId" id="summaryId">
							<option value="">所有摘要</option>
							<c:forEach items="${summaryList }" var="summary">
								<option value="${summary.summaryId }" <c:if test="${summaryId==summary.summaryId }">selected</c:if>>${summary.name }</option>
							</c:forEach>
						</select>
					</td>
					
					<td>
						 单号：<input type="text" id="queryBno" name="queryBno" value="${queryBno}"/>
					</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
						<tag:auth code="depot.print.list">
							<div class="buttonActive" style="display: none" id="showprint"><div class="buttonContent"><button type="button" onclick="goPrint();" >打印出入库单</button></div></div>
						</tag:auth>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th></th>
				<th>业务摘要</th>
				<th>仓库</th>
				<th>单据号</th>
				<th>配件编号</th>
				<th>配件名称</th>
				<th>配件类别</th>
				<th>数量</th>
				<th>最小单位</th>
				<th>销售价</th>
				<th>成本价</th>
				<th>销售金额</th>
				<th>成本金额</th>
				<th>出入库时间</th>
				<th>出入库操作人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="detailList" varStatus="s">
					<tr align="center">
						<td><input type="radio" value="${detailList.b_no },${detailList.summary_id},${detailList.dep_id}" onclick="showPrint()" id="bno" name="bno"/></td>
						<td>${detailList.summary}</td>
						<td>${detailList.dep_name }</td>
						<td>${detailList.b_no}</td>
						<td>${detailList.goods_code}</td>
						<td>${detailList.goods_name}</td>
						<td>${detailList.group_name}</td>
						<td>${detailList.num}</td>
						<td>${detailList.unit}</td>
						<td>${detailList.price}</td>
						<td>${detailList.cost_price}</td>
						<td>${detailList.dmoney}</td>
						<td>${detailList.cost_money}</td>
						<td>${fn:substring(detailList.bill_date,0,19)}</td>
						<td>${detailList.operator}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	function goPrint(){
		var queryDeptId;
		var summaryId;
		var queryBno;
		var array;
		
		$("input[name='bno']").each(function(){
			if($(this).attr("checked")=="checked"){
				array=$(this).val().split(",");
				queryBno=array[0];
				summaryId=array[1];
				queryDeptId=array[2];
			}
		})
		var url="/depot/print/"+queryDeptId+"/"+summaryId+"/"+queryBno;
		window.open(url,"_blank");
	}
function showPrint(){
	$("#showprint").show();

}	
	
	
</script>