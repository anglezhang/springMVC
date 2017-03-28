<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>维修冻结详情</title>
</head>
<body>
	<div class="widthB70 floatL">
		<table width="100%" class="margin-left-10">
			<tr>
                <td width="60" align="left"><label  class="margin-right-10"><input name="fixfrozeninf_type" <c:if test="${type=='null' or type=='2'}">checked</c:if> type="radio" value="2" ><span class="margin-left-5">维修</span></label></td>
                <td align="left"><label class="margin-left-10"><input name="fixfrozeninf_type" <c:if test="${type=='3'}">checked</c:if> type="radio" value="3"><span class="margin-left-5">冻结</span></label></td>
            </tr>
		</table>
		<div class="tableDiv floatL margin-left-10" id="fixfrozen_info_rooms" style="width:98%;height:400px;">
			
		</div>
	</div>
	<div class="widthB30 floatL">
    	<table width="90%" class="margin-left-10 margin-top-20">
            <tr>
                <td align="right"><label  class="margin-right-10"><input name="fixfrozeninf_active" <c:if test="${active=='null' or active=='0'}">checked</c:if> type="radio" value="0" ><span class="margin-left-5">有效</span></label></td>
                <td align="left"><label class="margin-left-10"><input name="fixfrozeninf_active" <c:if test="${active=='1'}">checked</c:if> 	value="1"  type="radio"><span class="margin-left-5">无效</span></label></td>
            </tr>
        </table>
        <dl class="inputDiv3 margin-top-none">
            <dt>开始日期</dt>
            <dd>
                <input class="input"  id="fixfrozeninf_startdate"  type="text" value="${ fn:substring(startDate,0,10)}">
                <input type="hidden" value="${active}" id="fixfrozeninf_type_active">
                <input type="hidden" value="${beginDate}" id="fixfrozeninf_begindate">
                <input type="hidden" value="${ fn:substring(startDate,0,10)}" id="fixfrozeninf_remeber_startdate">
            </dd>
            <dt>结束日期</dt>
            <dd>
                <input class="input"  id="fixfrozeninf_enddate" type="text" value="${fn:substring(endDate,0,10)}">
                <input type="hidden" value="${ fn:substring(endDate,0,10)}" id="fixfrozeninf_remeber_enddate">
            </dd>
        </dl>
        <div class="clearBoth"></div>
		<div class="fzRoomNum">
			房号  <input id="fixfrozen_roomid" class="input" type="text" value="${roomId}">
		</div>
        <div class="cabDivNoneHei margin-top-10 margin-left-20">
        	<a class="button_07 floatL" id="fixfrozen_btn_query" href="javascript:;">查询</a>
        	<c:if test="${type=='2'}" ><a class="button_07 floatL" id="fixfrozen_btn_cancle" href="javascript:;" <mammoth:AuthJudge funcId="c_672352" />>取消维修</a></c:if>
        	<c:if test="${type=='3'}" ><a class="button_07 floatL" id="fixfrozen_btn_cancle" href="javascript:;" <mammoth:AuthJudge funcId="c_574593" />>取消冻结</a></c:if>
            <a class="button_07 floatL" id="fixfrozen_btn_confirm" href="javascript:;" style="color: grey;cursor:not-allowed;" able-stat='false' <mammoth:AuthJudge funcId="c_781596" />>确定</a>
            <a class="button_07 floatL" id="fixfrozen_btn_drop" href="javascript:;" style="color: grey;cursor:not-allowed;" able-stat='false' <mammoth:AuthJudge funcId="c_240072" />>放弃</a>
            <!-- <a class="button_07 floatL" id="fixfrozen_btn_clear" href="javascript:;">清空条件</a> -->
            <a class="button_07 floatL" id="fixfrozen_btn_print" href="javascript:;">打印</a>
            <a class="button_07 floatL" id="fixfrozen_btn_exit" href="javascript:;">退出</a>
        </div>
        
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
        var dataList = ${list};
        seajs.use("${pageContext.request.contextPath}/js/fixAndFrozen/fixFrozenInf.js?number=" + Math.random());
    </script>
</body>