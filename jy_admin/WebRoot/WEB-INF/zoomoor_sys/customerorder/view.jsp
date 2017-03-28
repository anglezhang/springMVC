<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/serviceorder/saveupdate.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="entrustId" value="${cusEntity.entrustId}">
		<div class="pageFormContent" layoutH="60">
			<c:if test="${cusAppentity!=null}" >
				<h3 style="margin:10px 0 10px 5px;">预约信息</h3>
				<table class="table" width="100%">
					<tr>
						<td>预约单号</td>
						<td>${customer.cusorderNO}</td>
						<td>预约服务类型</td>
						<td>保养</td>
						<td>预约人姓名</td>
						<td>${customer.appname}</td>
						<td>预约人电话</td>
						<td>${customer.apptel}</td>
					</tr>
					<tr>
						<td>预约时间</td>
						<td><fmt:formatDate value="${cusAppentity.appoittime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>开单人</td>
						<td></td>
						<td>开单时间</td>
						<td><fmt:formatDate value="${cusAppentity.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</table>
			</c:if>
			
			<h3 style="margin:10px 0 10px 5px;">客户/车辆信息</h3>
			<table class="table" width="100%">
				<tr>
					<td width="100">客户姓名：</td>
					<td width="400">
						${cusEntity.carCusMap.infoCustome.customerName}
					</td>
					<td width="100">客户车辆：</td>
					<td >
						${cusEntity.carCusMap.infoCar.platenum}
					</td>
				</tr>
			</table>
			<table class="table" width="100%">
				<tr>
					<td width="100">客户电话</td>
					<td width="400" id="tel">
						${cusEntity.carCusMap.infoCar.infCustomer.tel}
					</td>
					<td width="100" >品牌</td>
					<td id="brandName">
						${cusEntity.carCusMap.infoCar.brandName}
					</td>
				</tr>
				<tr>
					<td>发动机编号</td>
					<td id="vinnum">${cusEntity.carCusMap.infoCar.vinnum}</td>
					<td>出厂日期</td>
					<td id="factoryDate"><fmt:formatDate value="${cusEntity.carCusMap.infoCar.factoryDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td>排量</td>
					<td id="displacement">
						${cusEntity.carCusMap.infoCar.displacement}
					</td>
					<td>变速箱类型</td>
					<td id="isAuto">
						<c:if test="${cusEntity.carCusMap.infoCar.isAuto}" >
							自动档
						</c:if>
						<c:if test="${!cusEntity.carCusMap.infoCar.isAuto}" >
							手动挡
						</c:if>
					</td>
				</tr>
				<tr>
					<td>上次保养日期</td>
					<td id="nextmain">
						<fmt:formatDate value="${cusEntity.carCusMap.infoCar.nextmain}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>上次投保时间</td>
					<td id="nextins">
						<fmt:formatDate value="${cusEntity.carCusMap.infoCar.nextins}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>购车日期</td>
					<td id="buytime">
						<fmt:formatDate value="${cusEntity.carCusMap.infoCar.buytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>挂牌时间</td>
					<td id="listingtime"> 
						<fmt:formatDate value="${cusEntity.carCusMap.infoCar.listingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>行驶里程</td>
					<td id="mileage"> 
						${cusEntity.carCusMap.infoCar.mileage}
					</td>
				</tr>
			</table>
			<h3 style="margin:10px 0 10px 5px;">服务委托单信息</h3>
			<input type="hidden" name="cusappoitId" value="${customer.cusappoitId}">
			<input type="hidden" name="infoDept.deptId" value="${user.infoEmp.infoDept.deptId}">
			<table class="table" width="100%">
				<tr>
					<td width="10%">服务单号</td>
					<td width="40%">${cusEntity.cusEnstrustNum}</td>
				</tr>
				<tr>
					<!--<td>服务委托单编号</td>
					<td></td>-->
					<td width="10%">服务接待人</td>
					<td width="40%">
						<c:forEach items="${emps}" var="infoEmp" varStatus="s">
							<c:if test="${cusEntity.receptionist eq infoEmp.empId}" >${infoEmp.empName}</c:if> 
						</c:forEach>
					</td>
					<td width="10%">开单时间</td>
					<td width="40%">
						<fmt:formatDate value="${cusEntity.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td>预交车时间</td>
					<td>
						${fn:substring(cusEntity.estimatetime,0,19)}
					</td>
					<td>指派维修工人</td>
					<td>
						<c:forEach items="${emps}" var="infoEmp" varStatus="s">
							<c:if test="${cusEntity.fixer eq infoEmp.empId}" >${infoEmp.empName}</c:if> 
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>服务位置</td>
					<td>
						<c:if test="${cusEntity.fixplace==0}"> ${dept.name}</c:if> 
						<c:forEach items="${addrList}" var="addr" varStatus="s">
							 <c:if test="${cusEntity.fixplace==addr.addrId}" >${addr.address} </c:if>
						</c:forEach>
					</td>
					<td>4s店服务收款</td>
					<td>
						${cusEntity.shopmonney}
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td>${cusEntity.remark}</td>
				</tr>
			</table>
			<h3 style="margin:10px 0 10px 5px;">服务项目</h3>
			<table class="list" width="100%">
				<thead>
					<th>服务类型</th>
					<th>项目编号</th>
					<th>项目名称</th>
					<th>工时费用</th>
					<th>折扣系数</th>
					<th>积分</th>
				</thead>
				<tbody id="customEntrustAct_addserviceItem">
					<c:forEach items="${cusEntity.customerEntrustSubs}" var="sub" varStatus="status">
						<tr>
							<c:if test="${not empty sub.serviceitem}" >
								<td>
									<c:if test="${not empty sub.serviceitem.servicetype}" >
										${sub.serviceitem.servicetype.servicename}
									</c:if>
								</td>
								<td>
									${sub.serviceitem.itemcode}
								</td>
								<td>
									${sub.serviceitem.itemname}
								</td>
								<td>
									<fmt:formatNumber value="${sub.serviceitem.workhourmoney}" type="currency"/>
								</td>
								<td>
									${sub.serviceitem.discount}
								</td>
								<td>
									${sub.serviceitem.intergral}
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!--<h3 style="margin:10px 0 10px 5px;">需要的配件</h3>
			<div class="panelBar">
				<ul class="toolBar">
					<tag:auth code="service.list.add">
						<li><a class="add" href="${pageContext.request.contextPath}/service/servicegoodslookup.do"  width="800" height="600" mask="true" id="servicegoodsLookup" rel="servicegoodsadd" lookupGroup="servicegoodsLookup"><span>添加配件</span></a></li>
					</tag:auth>
				</ul>
			</div> 
			<table class="list" width="100%">
				<thead>
					<th>序号</th>
					<th>配件编号</th>
					<th>配件名称</th>
					<th>单价(元)</th>
					<th>数量</th>
					<th>最小单位</th>
					<th>配件品牌</th>
					<th>配件规格</th>
					<th>折扣系数</th>
					<th>积分</th>
					<th>操作</th>
				</thead>
				<tbody id="customerentrustgoodlist">
					
				</tbody>
			</table>-->
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button id="addCarinfobtn" type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>