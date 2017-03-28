<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>住客信息</title>
</head>
	<body>
		<!-- 客人下拉框-->
		<script type="text/html" id="guestinfo_guestorc_option">
			{{each LIST as value i}}
				<option value="{{value.codeId}}" >{{value.codeNamec}}</option>
			{{/each}}
		</script>
		<script type="text/html" id="guestinfo_guestorc_gstNemee">
			{{each LIST as value i}}
				<option value="{{value.check_id}}" >{{value.gst_namee}} {{if value.payman_flag==='1'}}($){{/if}} </option>
			{{/each}}
		</script>
		<script type="text/html" id="guestinfo_guestorc_gstNemec">
			{{each LIST as value i}}
				<option value="{{value.check_id}}" >{{value.gst_namec}} {{if value.payman_flag==='1'}}($){{/if}} </option>
			{{/each}}
		</script>
		<form id="guestinfo_infoform" >
			<input class="input" name="checkId" type="hidden" id="guestinfo_checkId" value="${detail.checkId}">
			<input class="input" name="withId" type="hidden" id="guestinfo_withId"  value="${detail.withId}">
			<input class="input" type="hidden" id="guestinfo_hoteldate" value="${hotelDate}">
			<input class="input" type="hidden" id="guestinfo_pagetype" value="${pageType}">
			<input class="input" name="billbId" type="hidden" value="${detail.billbId}">
			<input class="input" name="billaId" type="hidden" value="${detail.billaId}">
			<input class="input" name="grpChkid" type="hidden" value="${detail.grpChkid}">
			<input class="input" id="guestinfo_visakindId" name="visakindId" type="hidden" value="${detail.visakindId}"><!-- 签证类型-->
			<input class="input" id="guestinfo_visaId" name="visaId" type="hidden" value="${detail.visaId}"><!-- 签证号码-->
			<input class="input" id="guestinfo_depart" name="depart" type="hidden" value="${detail.depart}"><!-- 签发机关-->
			<input class="input" id="guestinfo_crdVld" name="crdVld" type="hidden" value="${detail.crdVld}"><!-- 有效日期-->
			<input class="input" id="guestinfo_inDate" name="inDate" type="hidden" value="${detail.inDate}"><!-- 入境日期-->
			<input class="input" id="guestinfo_inPort" name="inPort" type="hidden" value="${detail.inPort}"><!-- 入境口岸-->
			<input class="input" id="guestinfo_accoutlistinit" value="false" type="hidden"><!-- 账务列表初始化-->
			<input class="input" id="guestinfo_scanidinf" value="${scanidInf}" type="hidden"><!-- 账务列表初始化-->
			<input class="input" id="guestinfo_hairRoomCard" type="hidden" value="${hairRoomCard}"><!-- 是否启用房卡-->
			<input class="input" id="guestinfo_scanCard" type="hidden" value="${scanCard}"><!-- 是否启用扫描身份证-->
			<input class="input"  type="hidden"><!-- 接待单位-->
			<input class="input" id="chkStat" type="hidden" value="${chkStat}"><!-- 状态-->
			<table class="checkInTitle" width="100%">
				<tr>
					<td width="8%" align="right">团代码</td>
					<td width="12%"><input class="input" name="grpId" type="text" disabled="disabled">
					</td>
					<td width="8%" align="right">团名</td>
					<td width="12%"><input class="input" name="grpName" type="text" disabled="disabled">
					</td>
					<td width="8%" align="right">房价方案</td>
					<td width="12%"><select class="select" name="prcSchemeId" id="guestdetail_prcSchemeId" >
						<option value='' data-roomplanType="${guestdetail_roomplanType}"></option>
						<c:forEach items="${guestdetail_prcSchemeId}" var="rp">
							<option value="${rp.hroomPlan.codeId}" 
								    data-roomplanType="${rp.hroomPlan.rmplanType}"
									data-checked="${rp.hroomPlan.checked}"
									data-editable="${rp.hroomPlan.editable}"
									${fn:trim(guestdetail_rateCode) == fn:trim(rp.hroomPlan.codeId) ? "selected='selected'" : ""}
							>${rp.hroomPlan.codeNamec}</option>
						</c:forEach>
					</select></td>
					<td width="8%" align="right" class="gry_9">销售员</td>
					<td><input class="input widthB92 gry_9" name="guestdetail_saler" type="text" id="guestdetail_saler" value="${saler}" readonly="readonly">
					</td>
					<td width="140">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><p class="fontWeight font-13">入住类型:</p>
					</td>
					<td colspan="7" id="checkInType">  
						<span><label><input class="radio roomplanType" name="inType" type="radio" checked="true" value="1" />普通</label> </span> 
						<span><label><input class="radio roomplanType" name="inType" type="radio" value="2" />合约</label> </span> 
						<span><label><input class="radio roomplanType" name="inType" type="radio" value="3" />钟点</label> </span> 
						<span><label><input class="radio roomplanType" name="inType" type="radio" value="4" />免费</label> </span> 
						<span><label><input class="radio roomplanType" name="inType" type="radio" value="5" />自用</label> </span>
					</td>
					<td width="140">&nbsp;</td>
				</tr>
			</table>
			<ul class="GuestTab clearBoth">
				<li class="point userTab1" id="guestdetal_tabguestinfo" <mammoth:AuthJudge funcId="c_191374" />  >客人详情</li>
				<li class="userTab2" id="guestdetal_tabguestibill" <mammoth:AuthJudge funcId="c_272987" />>客人账目</li>
			</ul>
			<%@ include file="guestInfo.jsp" %>
		</form>
			<!--userCatalog-->
		<%@ include file="billInfo.jsp" %>
		<!--/userCatalog-->
		<div class="alertDivBg2"></div>
		<div class="clearBoth"></div>
		<script src="${pageContext.request.contextPath}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
		<script>
			var rooms_model_cach = new Array();//缓存当前模块共用数据
			var guestDetaiAccFlexGrid = null;//住客资料账务grid对象
			var guests = ${guests};
			var detail = ${guestdetail};
			var moneyId ='${moneyId}';
			var refundCode = '${refundCode}';
			var extLeaveDate = '${extLeaveDate}';
			var guest_room_utils = null;//room工具类
    		var frame_utils = null;//调试工具类
			seajs.config({
		        base: '${pageContext.request.contextPath}/js/lib/'
		        , alias: {
		            'template': 'template/template'
		        }
		    });
		    /*seajs.use(["${pageContext.request.contextPath}/js/guestdetail/guestdetail.js?number=" + Math.random()
		    	,"${pageContext.request.contextPath}/js/guestdetail/guestdetailSP.js?number=" + + Math.random()]
		    	,function (a,b) 
		    	{
		    		// body...
		    		a.init(b);
		    	});*/
			seajs.use("${pageContext.request.contextPath}/js/guestdetail/guestdetail.js?number=" + Math.random());
		</script>
	</body>
</html>