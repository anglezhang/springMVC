<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/infodept/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="deptId" value="${infodept.deptId}">
		<input type="hidden" name="infoDeptSub.deptChildId" value="${infodept.infoDeptSub.deptChildId}">
		<input type="hidden" id="init_province" value="${infodept.infoDeptSub.province}">
		<input type="hidden" id="init_city" value="${infodept.infoDeptSub.city}">
		<input type="hidden" id="init_area" value="${infodept.infoDeptSub.area}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>部门名称：</label>
				<input type="text" name="name" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/infodept/check.ajax?deptId=${infodept.deptId}" value="${infodept.name}" />
			</div>
			<div class="unit">
				<label>是否门店：</label>
				<input type="radio" value="0" <c:if test="${infodept.isShop==false }">checked='checked'</c:if> name="isShop" onclick="addShop(1);"/>否
				<input type="radio" value="1" <c:if test="${infodept.infoDept.isShop}">disabled="disabled"</c:if> <c:if test="${infodept.isShop==true }">checked='checked'</c:if> name="isShop" onclick="addShop(2);"/>是
			</div>
			<div class="unit">
				<label>排序顺序：</label>
				<input type="text" name="sort" class="required digits"   maxlength="4" size="30" value="${infodept.sort}"/>
			</div>
			<div <c:if test="${!infodept.isShop}">style="display: none"</c:if> id="shop_s">
			<div class="divider"></div>
			<h3 style="margin:10px 0 10px 5px;">门店信息</h3>
			<table>
				<tr>
					<td>
						<div class="unit">
							<label>所在省：</label>
							<select  id="province" style="width:150px;height21px;" name="infoDeptSub.province"></select>
						</div>
					</td>
					<td>
						<div class="unit">
							<label>所在市：</label>
							<select  id="city" style="width:150px;height21px;" name="infoDeptSub.city"></select>
						</div>
					</td>
					<td>
						<div class="unit">
							<label>所在区：</label>
							<select  id="area" style="width:150px;height21px;" name="infoDeptSub.area"></select>
						</div>
					</td>
				</tr>
			</table>
			<div class="unit">
				<label>详细地址：</label>
				<input  type="text"  id="address" value="${infodept.infoDeptSub.address}" name="infoDeptSub.address" maxlength="50" size="40"/>
			</div>
			<div class="unit">
				<label>门店编号：</label>
				<input  id="deptNo" type="text" value="${infodept.infoDeptSub.deptNo}" remote="${pageContext.request.contextPath}/infodept/checksub.do"   name="infoDeptSub.deptNo" maxlength="40" size="40"/>
			</div>
			<div class="unit">
				<label>联系人：</label>
				<input  type="text" name="infoDeptSub.linkman" value="${infodept.infoDeptSub.linkman }" maxlength="20" size="30"/>
			</div>
			<div class="unit">
				<label>联系电话：</label>
				<input type="text"  name="infoDeptSub.phone" maxlength="30" value="${infodept.infoDeptSub.phone }" size="30"/>
			</div>
			<div class="unit">
				<label>email：</label>
				<input type="text"  id="email" value="${infodept.infoDeptSub.email }"  name="infoDeptSub.email" maxlength="50" size="30"/>
			</div>
			<div class="unit">
				<label>店铺坐标：</label>
				<input  value="${infodept.infoDeptSub.position}"  type="text" id="infoDeptSubposition"   name="infoDeptSub.position" maxlength="30" size="30"/>
				<a title="经纬度选择"  width="600" height="550" class="btnLook" href="${pageContext.request.contextPath}/baidu/mapLookup.do?lngLat=${infodept.infoDeptSub.position}" lookupGroup="infoDeptSub" id="infoDeptSub" rel="baiduMap"></a>
			</div>
			<div class="unit">
				<label>店铺面积：</label>
				<input value="${infodept.infoDeptSub.acreage}" type="text" name="infoDeptSub.acreage" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>报警上限：</label>
				<input value="${infodept.infoDeptSub.alarmUpperLimit}"  type="text" name="infoDeptSub.alarmUpperLimit" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>报警下限：</label>
				<input value="${infodept.infoDeptSub.alarmLowerLimit}" type="text" name="infoDeptSub.alarmLowerLimit" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>计算天数：</label>
				<input value="${infodept.infoDeptSub.countDay}" type="text" name="infoDeptSub.countDay" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>工&nbsp;&nbsp;位&nbsp;&nbsp;数：</label>
				<input value="${infodept.infoDeptSub.stationNum}" type="text" name="infoDeptSub.stationNum" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>备注：</label>
				<textarea   name="infoDeptSub.memo" >${infodept.infoDeptSub.memo}</textarea>
			</div>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<tag:auth code="infodept.update">
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
<script src="${pageContext.request.contextPath}/js/area.js" type="text/javascript"></script>
<script>

function setval(obj)
{
	var params = new Array(obj.position);
	setlookupParams(params,"infoDeptSub","lngLat");
}


$(function(){
	$(".dialog").css("top","150px");
	$(".shadow").css("top","150px");
	if(${infodept.isShop}){
		$.pdialog.resizeDialog({style: {height: 650}}, $.pdialog.getCurrent(), "");	
		addShop(2);
	}
		var sf=$("#init_province").val();
		var cs=$("#init_city").val();
		var aa=$("#init_area").val();
		if(sf==""||sf==null){
			sf="陕西";
		}
		if(cs==""||cs==null){
			cs="西安";
		}
		if(aa==""||aa==null){
			aa="莲湖区";
		}
		_init_area();
	    $("#province option").each(function(){ //遍历全部option 
	        var txt = $(this).text(); //获取option的内容 
	         if(txt==sf){
	         	$(this).attr("selected","selected");
	         	change(1);
	         }
	    });
     	$("#city option").each(function(){ //遍历全部option 
	        var txt = $(this).text(); //获取option的内容 
	         if(txt==cs){
	         	$(this).attr("selected","selected");
	        	change(2);
	         }
	    });
	    $("#area option").each(function(){ //遍历全部option 
	        var txt = $(this).text(); //获取option的内容 
	         if(txt==aa){
	         	$(this).attr("selected","selected");
	         }
	    });
	    
	
	
})

	function addShop(type){
	if(type==1){
		$("#shop_s").hide();
		$("#province").removeClass("required");
		$("#city").removeClass("required");
		$("#area").removeClass("required");
		$("#address").removeClass("required");
		$("#position").removeClass("required");
		$("#deptNo").removeClass("required");
		$("#email").removeClass("email");
		
		$.pdialog.resizeDialog({style: {height: 200}}, $.pdialog.getCurrent(), "");
	}else if(type==2){
		$("#shop_s").show();
		$("#shop_s").show();
		$("#province").addClass("required");
		$("#city").addClass("required");
		$("#area").addClass("required");
		$("#address").addClass("required");
		$("#position").addClass("required");
		$("#deptNo").addClass("required");
		$("#email").addClass("email");
		
		
		$.pdialog.resizeDialog({style: {height: 650}}, $.pdialog.getCurrent(), "");
				
	}
	
}
$(function()
{
	var type = $("[name='isShop']:checked").val();
	addShop(type+1);
});
</script>