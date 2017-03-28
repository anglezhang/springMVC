<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>配件编号：</label>
				${goods.goodsCode }
			</div>
			<div class="unit">
				<label>配件名称：</label>
				${ goods.name}
			</div>
			<div class="unit">
				<label>配件拼音：</label>
				${ goods.pinyin}
			</div>
			<div class="unit">
				<label>适用品牌车系：</label>
				${goods.infoBrand.infoBrand.name }-${goods.infoBrand.name }
			</div>
			<div class="unit">
				<label>配件类别：</label>
				${goods.typeShow }
			</div>
			<div class="unit">
				<label>最小计量单位：</label>
					<c:forEach items="${paramConfigList }" var="paramconfig">
						<c:if test="${paramconfig.unitId==goods.paramConfig.unitId }">${paramconfig.name }</c:if>
					</c:forEach>
			</div>
			<div class="unit">
				<label>规格：</label>
				${goods.standard }
			</div>
			<div class="unit">
				<label>配件品牌：</label>
				${goods.goodsBrand }
			</div>
			<div class="unit">
				<label>货号：</label>
				${goods.artNo }
			</div>
			<div class="unit">
				<label>包装单位：</label>
				${goods.packUnit }
			</div>
			<div class="unit">
				<label>包装规格：</label>
				${goods.packSpec }
			</div>
			<div class="unit">
				<label>销售单价：</label>
				${goods.price }
			</div>
			<div class="unit">
				<label>批发售价：</label>
				${goods.wholesalePrice }
			</div>
			<div class="unit">
				<label>索赔价：</label>
				${goods.claimPrice }
			</div>
			<div class="unit">
				<label>建议零售价：</label>
				${goods.advisePrice }
			</div>
			<div class="unit">
				<label>成本价：</label>
				${goods.costPrice }
			</div>
			<div class="unit">
				<label>保险价：</label>
				${goods.insurancePrice }
			</div>
			<div class="unit">
				<label>会员价：</label>
				${goods.memberPrice }
			</div>
			<div class="unit">
				<label>折扣：</label>
				${goods.discount }
			</div>
			<div class="unit">
				<label>积分：</label>
				${goods.intergral }
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
