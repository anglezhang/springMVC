<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>

<div class="pageContent">
	<table class="table" width="100%">
		<tr>
			<td style="float: right;">部门:</td>
			<td>${deptName }</td>
			<td style="float: right;">库位:</td>
			<td colspan="3">${ position }</td>
		</tr>
		<tr>
		<td style="float: right;">配件编号:</td>
		<td>${ infoGoods.goodsCode }</td>
		<td style="float: right;">配件名称:</td>
		<td>${ infoGoods.name }</td>
		<td style="float: right;"> 最新采购时间:</td>
		<td>${fn:substring(pur_order_date,0,19) }</td>
		</tr>
		<tr>
		<td style="float: right;">适用品牌车型:</td>
		<td>${ infoGoods.infoBrand.infoBrand.name }-${ infoGoods.infoBrand.name }</td>
		<td style="float: right;">配件类型:</td>
		<td>${ infoGoods.infoGoodsType.typeName }</td>
		<td style="float: right;"> 当前库存:</td>
		<td>${ balance }</td>
		</tr>
		<tr>
		<td style="float: right;">配件品牌:</td>
		<td>${ infoGoods.goodsBrand }</td>
		<td style="float: right;">成本价格:</td>
		<td>${ infoGoods.costPrice}</td>
		<td style="float: right;"> 销售价:</td>
		<td>${ infoGoods.price }</td>
		</tr>
		<tr>
		<td style="float: right;">加价率:</td>
		<td>${add_I }</td>
		<td style="float: right;">最新不含税采购价格:</td>
		<td>${price_no_tax }</td>
		<td style="float: right;">最小单位:</td>
		<td>${ infoGoods.paramConfig.name}</td>
		</tr>
		<tr>
		<td style="float: right;"> 配件规格:</td>
		<td>${ infoGoods.standard}</td>
		<td style="float: right;">最新含税采购价格:</td>
		<td>${order_price }</td>
		<td style="float: right;">折扣系数:</td>
		<td>${discount }</td>
		</tr>
		<tr>
		<td style="float: right;">积分:</td>
		<td>${ infoGoods.intergral}</td>
		<td style="float: right;">包装规格:</td>
		<td>${ infoGoods.packSpec}</td>
		<td style="float: right;">包装单位:</td>
		<td>${ infoGoods.packUnit}</td>
		</tr>
		<tr>
		<td style="float: right;">索赔价格:</td>
		<td>${ infoGoods.claimPrice}</td>
		<td style="float: right;">批发售价:</td>
		<td>${ infoGoods.wholesalePrice}</td>
		<td style="float: right;">建议零售价:</td>
		<td>${ infoGoods.advisePrice}</td>
		</tr>
		<tr>
		<td style="float: right;">货号:</td>
		<td>${ infoGoods.artNo}</td>
		<td style="float: right;">保险价:</td>
		<td colspan="3">${ infoGoods.insurancePrice}</td>
		</tr>
	</table>
	<div class="formBar">
			<ul>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
</div>
