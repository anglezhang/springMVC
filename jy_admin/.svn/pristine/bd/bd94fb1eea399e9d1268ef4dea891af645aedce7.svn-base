<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" id="form_d" action="${pageContext.request.contextPath}/infodept/save.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone2);">
		<input type="hidden" name="upId" value="${infodept.deptId == null ? 0 : infodept.deptId}">
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="name" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/infodept/check.ajax?upId=${infodept.deptId}" />
			</div>
			<div class="unit">
				<label>是否门店：</label>
				<input type="radio" value="0" checked="checked" name="isShop" onclick="addShop(1)"/>否
				
				<input type="radio" value="1" name="isShop" <c:if test="${infodept.isShop}">disabled="disabled"</c:if> onclick="addShop(2);"/>是
			</div>
			<div class="unit">
				<label>排序顺序：</label>
				<input type="text" name="sort" class="required digits" maxlength="4" size="30" value="10"/>&nbsp;&nbsp;&nbsp;   数字越小越靠前
			</div>
			<div style="display: none" id="shop_s">
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
				<input value="" type="text"  id="address" name="infoDeptSub.address" maxlength="50" size="40"/>
			</div>
			<div class="unit">
				<label>门店编号：</label>
				<input value="" id="deptNo" type="text"  remote="${pageContext.request.contextPath}/infodept/checksub.do"   name="infoDeptSub.deptNo" maxlength="40" size="40"/>
			</div>
			<div class="unit">
				<label>联系人：</label>
				<input  type="text" name="infoDeptSub.linkman" maxlength="20" size="30"/>
			</div>
			<div class="unit">
				<label>联系电话：</label>
				<input type="text"  name="infoDeptSub.phone" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>email：</label>
				<input value="" type="text"  id="email" class="email" name="infoDeptSub.email" maxlength="50" size="30"/>
			</div>
			<div class="unit">
				<label>店铺坐标：</label>
				<input  value="" type="text" id="infoDeptSubposition"   name="infoDeptSub.position" maxlength="30" size="30"/>
				<a title="经纬度选择" id="infoDeptSub" width="600" height="550" class="btnLook" href="${pageContext.request.contextPath}/baidu/mapLookup.do" lookupGroup="infoDeptSub" rel="baiduMap"></a>
			</div>
			<div class="unit">
				<label>店铺面积：</label>
				<input value="" type="text" name="infoDeptSub.acreage" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>报警上限：</label>
				<input value=""  type="text" name="infoDeptSub.alarmUpperLimit" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>报警下限：</label>
				<input value="" type="text" name="infoDeptSub.alarmLowerLimit" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>计算天数：</label>
				<input value="" type="text" name="infoDeptSub.countDay" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>工&nbsp;&nbsp;位&nbsp;&nbsp;数：</label>
				<input value="" type="text" name="infoDeptSub.stationNum" maxlength="30" size="30"/>
			</div>
			<div class="unit">
				<label>备注：</label>
				<textarea   name="infoDeptSub.memo"  rows="5" cols="70"></textarea>
			</div>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<tag:auth code="infodept.save">
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
<script type="text/javascript">
$(function(){
		var sf="陕西";
		var cs="西安";
		var aa="莲湖区";
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
	 	$(".dialog").css("top","150px");
		$(".shadow").css("top","150px");   
		$.pdialog.resizeDialog({style: {height: 200}}, $.pdialog.getCurrent(), "");
})
	function dialogAjaxDone2(json){
	$("#backId_dept").val(json.backId);
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		if (json.navTabId){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
	}
}
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
			$("#province").addClass("required");
			$("#city").addClass("required");
			$("#area").addClass("required");
			$("#address").addClass("required");
			$("#position").addClass("required");
			$("#deptNo").addClass("required");
			$("#email").addClass("email");
		$.pdialog.resizeDialog({style: {height:650}}, $.pdialog.getCurrent(), "");
	}
	
}

function setval(obj)
{
	var params = new Array(obj.position);
	setlookupParams(params,"infoDeptSub","lngLat");
}
</script>

