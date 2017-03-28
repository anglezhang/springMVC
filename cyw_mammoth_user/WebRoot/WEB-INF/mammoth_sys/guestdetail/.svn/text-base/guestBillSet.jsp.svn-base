<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分账</title>
</head>
<body >
	<script type="text/html" id="guest_bill_billoption">
		<li {{if type=='A'}}guest-bill-a{{/if}}{{if type=='B'}}guest-bill-b{{/if}}="{{id}}" tag="false">{{codeNamec}}</li>
	</script>
	<input type="hidden" value="${bp.checkId}" id="guest_bill_set_bpaiedcheckid">
	<table width="100%">
		<tbody>
			<tr>
				<td width="42%">
					<h5 class="margin-left-5 fontWeight">A账页项目</h5>
					<ul class="splitDivLeft margin-top-5" id="guest_bill_setconsumeA_ID">
						<c:forEach items="${list}" var="hconsume">
							<li guest-bill-a="${hconsume.codeId}" tag="false">${hconsume.codeNamec}</li>
						</c:forEach>
					</ul>
				</td>
				<td align="center">
					<span class="forSN margin-top-20" id="guest_bill_goleft"><img src="/img/right_01.png"></span>
					<span class="forSN margin-top-10" id="guest_bill_goleftAll"><img src="/img/right_02.png"></span>
					<span class="forSN margin-top-30" id="guest_bill_goright"><img src="/img/left_01.png"></span>
					<span class="forSN margin-top-10" id="guest_bill_gorightAll"><img src="/img/left_02.png"></span>
				</td>
				<td width="42%">
					<h5 class="margin-left-5 fontWeight">B账页项目</h5>
					<ul class="splitDivRight margin-top-5" id="guest_bill_set_consumeB_ID">
						<c:forEach items="${blist}" var="hconsume">
							<li guest-bill-b="${hconsume.codeId}" tag="false">${hconsume.codeNamec}</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="clearBoth"></div>
	<table class="margin-left-30 margin-top-20">
		<tbody>
			<tr>
			<td width="80" height="30" align="right">起始日期</td>
			<td width="110"><input class="input" id="split_startDate" name="" type="text" value="${fn:substring(bp.beginDate,0,10)}">
			</td>
			<td width="80" align="right">终止日期</td>
			<td width="110"><input class="input" id="guest_bill_set_split_endDate" name="" type="text" value="${fn:substring(bp.endDate,0,10)}">
			</td>
			</tr>
			<tr>
				<td width="110" colspan="4"><label><input class="checkbox margin-right-5 margin-left-15" name="" id="guest_bill_set_if_bate" type="checkbox" <c:if test="${grpdoc.ifBdate=='true'}">checked</c:if> value="">续住时自动修改分账日期</label>
				</td>
			</tr>
		</tbody>
	</table>
	<!--分账-->
	<div class="alertRight clearBoth margin-top-30">
		<a class="buttonLikeA floatR margin-right-10" id="guest_bill_set_quit" href="javascript:;" >退出</a>
		<a class="buttonLikeA floatR margin-right-10" id="guest_bill_set_drop" href="javascript:;" >放弃</a>
		<a class="buttonLikeA floatR margin-right-10" id="guest_bill_set_confirm" href="javascript:;" >确定</a>
	</div>
	<div class="clearBoth"></div>
	<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
	<script>
		seajs.config({
	        base: '${pageContext.request.contextPath}/js/lib/'
	        , alias: {
	            'template': 'template/template'
	        }
	    });
		seajs.use('${pageContext.request.contextPath}/js/guestdetail/guestBillSet.js?number=' + Math.random());
	</script>
</body>