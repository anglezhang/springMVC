<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>配件名称：</label>
				${newApply.infoGoods.name }
			</div>
			<div class="unit">
				<label>配件类型：</label>
				${newApply.infoGoods.infoGoodsType.typeName }
			</div>
			<div class="unit">
				<label>成本价：</label>
				${newApply.infoGoods.costPrice }
			</div>
			<div class="unit">
				<label>申请数量：</label>
				${newApply.num }
			</div>
			<div class="unit">
				<label>最小单位：</label>
				${newApply.paramConfig.name }
			</div>
			<div class="unit">
				<label>配件品牌：</label>
				${newApply.infoGoods.goodsBrand }
			</div>
			<div class="unit">
				<label>配件规格：</label>
				${newApply.infoGoods.standard }
			</div>
			<div class="unit">
				<label>交货日期：</label>
				${fn:substring(newApply.takeDate,0,10) }
			</div>
			<div class="unit">
				<label>申请日期：</label>
				${fn:substring(newApply.unitDate,0,19) }
			</div>
			<div class="unit">
				<label>申请人：</label>
				${empName }
			</div>
			<div class="unit">
				<label>状态：</label>
				<c:if test="${newApply.status==0 }">待执行</c:if>
				<c:if test="${newApply.status==1 }">执行中</c:if>
				<c:if test="${newApply.status==2 }">已完成</c:if>
			</div>
			<div class="unit">
				<label>总部反馈：</label>
				${ newApply.feedback}
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
</div>
