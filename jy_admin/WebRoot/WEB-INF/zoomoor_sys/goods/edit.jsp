<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/infogoods/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="goodsId" value="${goods.goodsId}" type="hidden"/>
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>配件编号：</label>
				<input type="text" name="goodsCode" value="${goods.goodsCode }" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/infogoods/check.ajax?id=${goods.goodsId}" />
			</div>
			<div class="unit">
				<label>配件名称：</label>
				<input type="text" name="name" value="${ goods.name}" class="required" maxlength="50" size="30" />
			</div>
			<div class="unit">
				<label>配件拼音：</label>
				<input type="text" name="pinyin" value="${ goods.pinyin}" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>适用品牌车系：</label>
				<input name="infoBrand.id" type="hidden" value="${goods.infoBrand.id }">
				<input class="required" name="infoBrand.name" onclick="openBrandLookUp()" value="${goods.infoBrand.infoBrand.name }-${goods.infoBrand.name }"  type="text" size="30" readonly="readonly" />
				<a title="选择品牌车系" class="btnLook" id="brandlook"  href="${pageContext.request.contextPath}/lookup/brand.do?backId=${goods.infoBrand.id }" rel="look_brand2" width="600" height="550" lookupGroup="infoBrand">品牌车系</a>
						
			</div>
			<div class="unit">
				<label>配件类别：</label>
				<input name="infoGoodsType.groupId" type="hidden" value="${goods.infoGoodsType.groupId }">
				<input class="required" name="infoGoodsType.typeName" onclick="openGoodsLookUp()" value="${goods.typeShow }" type="text" size="30" readonly="readonly" />
				<a title="选择配件类别" class="btnLook" id="goodslook"  href="${pageContext.request.contextPath}/lookup/goodsType.do?backId=${goods.infoGoodsType.groupId  }" rel="look_gt2" width="600" height="550" lookupGroup="infoGoodsType">配件类别</a>
				
			</div>
			<div class="unit">
				<label>最小计量单位：</label>
				<select name="paramConfig.unitId">
					<c:forEach items="${paramConfigList }" var="paramconfig">
						<option value="${paramconfig.unitId }" <c:if test="${paramconfig.unitId==goods.paramConfig.unitId }">selected</c:if>>${paramconfig.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="unit">
				<label>规格：</label>
				<input type="text" name="standard" value="${goods.standard }" class=" required " maxlength="10" size="30"  />
			</div>
			<div class="unit">
				<label>配件品牌：</label>
				<input type="text" name="goodsBrand" value="${goods.goodsBrand }"  class="" maxlength="30" size="30"  />
			</div>
			<div class="unit">
				<label>货号：</label>
				<input type="text" name="artNo" value="${goods.artNo }"  class="" maxlength="10" size="30"  />
			</div>
			<div class="unit">
				<label>包装单位：</label>
				<input type="text" name="packUnit" value="${goods.packUnit }" class="" maxlength="10" size="30"  />
			</div>
			<div class="unit">
				<label>包装规格：</label>
				<input type="text" name="packSpec" value="${goods.packSpec }" class="" maxlength="10" size="30"  />
			</div>
			<div class="unit">
				<label>销售单价：</label>
				<input type="text" name="price" value="${goods.price }" class="" maxlength="30" size="30"  />
			</div>
			<div class="unit">
				<label>批发售价：</label>
				<input type="text" name="wholesalePrice" value="${goods.wholesalePrice }" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>索赔价：</label>
				<input type="text" name="claimPrice" value="${goods.claimPrice }" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>建议零售价：</label>
				<input type="text" name="advisePrice"  value="${goods.advisePrice }" class="required" maxlength="30" size="30"  />
			</div>
			<div class="unit">
				<label>成本价：</label>
				<input type="text"  value="${goods.costPrice }"  readonly="readonly" size="30"/>
			</div>
			<div class="unit">
				<label>保险价：</label>
				<input type="text" name="insurancePrice"  value="${goods.insurancePrice }" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>会员价：</label>
				<input type="text" name="memberPrice" value="${goods.memberPrice }" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>折扣：</label>
				<input type="text" name="discount" onKeyUp="clearNoNum(event,this)" onBlur="checkNum(this)"   value="${goods.discount }"   maxlength="4" size="30"  />
			</div>
			<div class="unit">
				<label>积分：</label>
				<input type="text" name="intergral"  value="${goods.intergral }" class="digits" maxlength="20" size="30"  />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="infogoods.update">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
					</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script>
	function openBrandLookUp(){
		$("#brandlook").click();
	}
		function openGoodsLookUp(){
		$("#goodslook").click();
	}
</script>