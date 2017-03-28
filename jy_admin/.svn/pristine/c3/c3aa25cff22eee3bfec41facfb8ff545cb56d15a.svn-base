<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/allocationapply/listdetail.action?applytype=${applytype}">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryGoodsName" value="${queryGoodsName}" />
	<input type="hidden" name="queryTypeName" value="${queryTypeName}" />
	<input type="hidden" name="queryTypeId" value="${queryTypeId}" />
	<input type="hidden" name="queryGoodsBrand" value="${queryGoodsBrand}" />
	<input type="hidden" name="queryStartDate" value="${queryStartDate}" />
	<input type="hidden" name="queryEndDate" value="${queryEndDate}" />
	<input type="hidden" name="deptId" value="${deptId}" />
	<input type="hidden" name="queryStatus" value="${queryStatus}" />
	<input type="hidden" name="applytype" value="${applytype}" />
	<input type="hidden" name="queryGoodsCode" value="${queryGoodsCode}" />
</form>
<div class="pageHeader">
	<form <c:if test="${!empty applytype }">onsubmit="return dialogSearch(this);"</c:if> <c:if test="${empty applytype }">onsubmit="return navTabSearch(this);"</c:if> action="${pageContext.request.contextPath}/allocationapply/listdetail.action?applytype=${applytype}" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<c:if test="${!empty applytype }">
					
					<td>
						店铺名称：
						<select name="deptId">
								<option value=" ">==全部店铺==</option>
							<c:forEach items="${deptList }" var="dept">
								<option value="${dept.deptId }" <c:if test="${ deptId==dept.deptId}">selected</c:if>>${dept.name }</option>
							</c:forEach>
							
						</select>
					</td>
					</c:if>
					<td>
						配件编号：<input type="text" name="queryGoodsCode" value="${queryGoodsCode}"/>
					</td>
					<%-- <td>
						配件名称：<input type="text" name="queryGoodsName" value="${queryGoodsName}"/>
					</td> --%>
					<td>
						配件类型：<input name="infoGoodsType.typeName" id="b_name" value="${queryTypeName }" type="text" size="20" readonly="readonly" />
							<input name="infoGoodsType.groupId" id="b_id" type="hidden" value="${ queryTypeId}">
							<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk()">清空</button></div></div>
							<a  style="float: right;" title="选择配件类别" class="btnLook" href="${pageContext.request.contextPath}/lookup/goodsType.do?backId=${queryTypeId }" rel="look_brand3" width="600" height="550" lookupGroup="infoGoodsType">配件类别</a>
				
					</td>
					<%-- <td>
						配件品牌：<input type="text" name="queryGoodsBrand" value="${queryGoodsBrand}"/>
					</td> --%>
					<td>
						申请日期：<input type="text" class="date" name="queryStartDate" value="${queryStartDate}"/>-<input type="text" class="date"  name="queryEndDate" value="${queryEndDate}"/>
					</td>
					<td>
						申请状态：
						<select name="queryStatus">
							<option value="">全部状态</option>
							<option value="0" <c:if test="${queryStatus==0 }">selected</c:if>>待执行</option>
							<option value="1" <c:if test="${queryStatus==1 }">selected</c:if>>执行中</option>
							<option value="2" <c:if test="${queryStatus==2 }">selected</c:if>>已完成</option>
							
						</select>
					</td>
					<td>
					 
						<div class="buttonActive"><div class="buttonContent"><button type="submit" id="dept_ref${applytype }">检索</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<c:if test="${empty applytype }">
			<tag:auth code="allocationapply.add">
				<li><a class="add" href="${pageContext.request.contextPath}/allocationapply/add.do" target="dialog"  width="1200" height="600" mask="true" rel="order_add_yh"><span>创建要货申请</span></a></li>
			</tag:auth>
			</c:if>
			<c:if test="${!empty applytype }">
			<tag:auth code="allocationapp.add">
				<li><a class="add" href="javascript:void(0);" onclick="goCreateDB();"><span>创建调拨单</span></a></li>
			</tag:auth>
			<tag:auth code="allocationapply.feedback">
				<li><a class="add" href="javascript:void(0);" onclick="gofeed();"><span>回执</span></a></li>
			</tag:auth>
			</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr align="center">
				<c:if test="${!empty applytype }"><th><input type="checkbox" group="ids" class="checkboxCtrl"></th></c:if>
				<th>品牌车型</th>
				<th>配件编号</th>
				<th>配件名称</th>
				<th>配件类型</th>
				<th>成本价</th>
				<th>申请数量</th>
				<th>最小单位</th>
				<th>配件品牌</th>
				<th>配件规格</th>
				<th>交货日期</th>
				<th>申请店铺</th>
				<th>申请时间</th>
				<th>申请人</th>
				<th>状态</th>
				<th>总部反馈</th>
				<th width="150px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="apply" varStatus="s">
					<tr align="center">
						<c:if test="${!empty applytype }">
						<td>
							<c:if test="${ apply.status==0}">
								<input type="checkbox" name="ids" value="${apply.applyId},${apply.infoDept.deptId}">
							</c:if>
						</td>
						</c:if>
						<td>${fn:substring(apply.infoGoods.infoBrand.infoBrand.name,0,5)}-${fn:substring(apply.infoGoods.infoBrand.name,0,5)}</td>
						<td>${apply.infoGoods.goodsCode}</td>
						<td>${apply.infoGoods.name}</td>
						<td>${apply.infoGoods.typeShow}</td>
						<td>${apply.infoGoods.costPrice}</td>
						<td>${apply.num}</td>
						<td>${apply.paramConfig.name}</td>
						<td>${apply.infoGoods.goodsBrand}</td>
						<td>${apply.infoGoods.standard}</td>
						<td>${fn:substring(apply.takeDate,0,10) }</td>
						<td>${apply.infoDept.name}</td>
						<td>
							${fn:substring(apply.unitDate,0,19) }
						</td>
						<td>${apply.empName}</td>
						<td>
							<c:if test="${ apply.status==0}">待执行</c:if>
							<c:if test="${ apply.status==1}">执行中</c:if>
							<c:if test="${ apply.status==2}">已完成</c:if>
						</td>
						<td>${fn:substring(apply.feedback,0,15)}</td>
						<td width="150px">
							<tag:auth code="allocationapply.view">
								 <a title="查看" target="dialog"  width="500" height="450" mask="true" href="${pageContext.request.contextPath}/allocationapply/view.do?id=${apply.applyId}" class="btn btn-ck"></a>
							</tag:auth>
							<c:if test="${empty applytype }">
								<tag:auth code="allocationapply.view.feedback">
									 <a title="查看回执" target="dialog"  width="700" height="250" mask="true" href="${pageContext.request.contextPath}/allocationapply/lookfeedback.do?id=${apply.applyId}" class="btn btn-ss"></a>
								</tag:auth>
							</c:if>
						</td>
						
					</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="formBar">
	<ul>
		<c:if test="${!empty applytype }">
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
		</li>
		</c:if>
	</ul>
</div>
<script>
	function qk(){
		$("#b_name").val("");
		$("#b_id").val("");
		
	}
function gofeed(){
	var strids="";
	$("input[name='ids']").each(function(){
		if($(this).attr("checked")){
		 strids+= $(this).val().substring(0,$(this).val().indexOf(","))+","; 	
		}
	})
	if(strids==""){
		var errorMsg = new Object();
		errorMsg.statusCode = 300;
		errorMsg.message = "请至少选择一条申请！";
		dialogAjaxDone(errorMsg);
	}else{
		strids=strids.substring(0,strids.length-1);
		var url = "${pageContext.request.contextPath}/allocationapply/openback.do?ids="+strids;
      	$.pdialog.open(url, "apply_feedback", "添加回执", {width: 550, height: 300, mask:true});	
	}
	
}
function goCreateDB(){
	var ischange = true;
	var fisrtId = "";
	var applyId="";
	var deptid="";
	$("input[name='ids']").each(function(index){
		if($(this).attr("checked")=="checked"){
		var i=0;
		applyId+=$(this).val().substring(0,$(this).val().indexOf(","))+",";
		deptid=$(this).val().substring($(this).val().indexOf(",")+1);
			if(i==0){
				fisrtId=deptid;
				i++;
			}else if(fisrtId!=deptid){
				ischange=false;
				return ;
			}
		}
	});
	if(ischange){
		var applytype='${applytype}';
		var url="${pageContext.request.contextPath}/allocation/add.do?applyIds="+applyId.substring(0,applyId.length-1)+"&deptId="+deptid+"&applytype="+applytype;
		$.pdialog.open(url, "order_add_db", "创建调拨单", {width: 1200, height: 700, mask:true});
	}else{
		var errorMsg = new Object();
		errorMsg.statusCode = 300;
		errorMsg.message = "请选择同一个店铺！";
		dialogAjaxDone(errorMsg);
	}
	
}	
	
</script>