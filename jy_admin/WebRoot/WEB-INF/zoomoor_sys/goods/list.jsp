<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/infogoods/list.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryGoodsCode" value="${queryGoodsCode}" />
	<input type="hidden" name="queryName" value="${queryName}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/infogoods/list.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						适用品牌车系：<input name="infoBrand.name" id="b_name" value="${queryBrandName }" type="text" size="20" readonly="readonly" />
							<input name="infoBrand.id" id="b_id" type="hidden" value="${ queryBrandId}">
							<div style="float: right;" class="buttonActive"><div class="buttonContent"><button type="button" onclick="qk()">清空</button></div></div>
							<a  style="float: right;" title="选择品牌车系" class="btnLook" href="${pageContext.request.contextPath}/lookup/brand.do?backId=${queryBrandId }" rel="look_brand3" width="600" height="550" lookupGroup="infoBrand">品牌车系</a>
					</td>
					<td>
						配件编号：<input type="text" name="queryGoodsCode" value="${queryGoodsCode}"/>
					</td>
					<td>
						配件名称/拼音：<input type="text" name="queryName" value="${queryName}"/>
					</td>
					<td>
						配件品牌：<input type="text" name="queryBrand" value="${queryBrand}"/>
					</td>
					<td>
					 
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<tag:auth code="infogoods.add">
				<li><a class="add" rel="add_goods" href="${pageContext.request.contextPath}/infogoods/add.do" target="dialog"  width="800" height="600" mask="true"><span>添加</span></a></li>
			</tag:auth>
			<tag:auth code="infogoods.delete">
				<li><a class="delete" href="${pageContext.request.contextPath}/infogoods/delete.do" posttype="string" title="确定要删除吗?" target="selectedTodo"><span>删除</span></a></li>
			</tag:auth>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th width="50"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="50">ID</th>
				<th>适用品牌车系</th>
				<th>配件编号</th>
				<th>配件名称</th>
				<th>配件拼音</th>
				<th>配件类别</th>
				<th>最小单位</th>
				<th>配件规格</th>
				<th>配件品牌</th>
				<th>包装单位</th>
				<th>包装规格</th>
				<th>销售单价</th>
				<th>批发售价</th>
				<th>索赔价</th>
				<th>建议零售价</th>
				<th>保险价</th>
				<th>会员价</th>
				<th>折扣</th>
				<th>积分</th>
				<th>操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="goods" varStatus="s">
					<tr align="center">
						<td><input type="checkbox" name="ids" value="${goods.goodsId}"></td>
						<td>${goods.goodsId}</td>
						<td>
							${goods.infoBrand.infoBrand.name }-${goods.infoBrand.name }
						</td>
						<td>${goods.goodsCode}</td>
						<td>${goods.name}</td>
						<td>${goods.pinyin}</td>
						<td>${goods.typeShow}</td>
						<td>${goods.paramConfig.name}</td>
						<td>${goods.standard}</td>
						<td>${goods.goodsBrand}</td>
						<td>${goods.packUnit}</td>
						<td>${goods.packSpec}</td>
						<td>${goods.price}</td>
						<td>${goods.wholesalePrice}</td>
						<td>${goods.claimPrice}</td>
						<td>${goods.advisePrice}</td>
						<td>${goods.insurancePrice}</td>
						<td>${goods.memberPrice}</td>
						<td>${goods.discount}</td>
						<td>${goods.intergral}</td>
						<td>
							<tag:auth code="infogoods.delete">
								 <a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/infogoods/delete.do?ids=${goods.goodsId}" class="btn btn-delete"></a>
							</tag:auth>
							<tag:auth code="infogoods.edit">
								 <a title="修改" target="dialog"  rel="edit_goods"  width="600" height="550" mask="true"  href="${pageContext.request.contextPath}/infogoods/edit.do?id=${goods.goodsId}" class="btn btn-edit"></a>
							</tag:auth>
							<tag:auth code="infogoods.view">
								 <a title="查看" target="dialog"  width="600" height="650" mask="true" href="${pageContext.request.contextPath}/infogoods/view.action?id=${goods.goodsId}" class="btn btn-ck"></a>
							</tag:auth>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
<script>
	function qk(){
		$("#b_name").val("");
		$("#b_id").val("");
		
	}
</script>
