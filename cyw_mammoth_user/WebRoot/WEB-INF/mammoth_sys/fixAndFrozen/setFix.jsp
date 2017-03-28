<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设为维修</title>
</head>
<body>
    <input type="hidden" id="setfix_start" value="${starDate}" >
    <input type="hidden" id="setfix_end" value="${endDate}" >
    <table width="100%" class="margin-left-10">
        <tr>
            <td width="60" align="left"><label  class="margin-right-10"><input name="setfix_type" <c:if test="${type=='null' or type=='2'}">checked</c:if> type="radio" value="2" ><span class="margin-left-5">维修</span></label></td>
            <td align="left"><label class="margin-left-10"><input name="setfix_type" <c:if test="${type=='3'}">checked</c:if> type="radio" value="3"><span class="margin-left-5">冻结</span></label></td>
        </tr>
    </table>
 	<ul class="roomNum widthB90 clearBoth margin-LF-auto" id="fix_rooms_roomsid" data-type="${type}" style="float:none;">
        <c:forEach items="${ids}" var="id">
            <li data-roomid="${id}" >${id}</li>
        </c:forEach>
    </ul>
    <!-- <div class="fundRoomNum margin-left-20">
        <span>选定房间：${fn:length(ids)}</span>
    </div> -->
    <div class="fundRoomNum margin-left-20 margin-top-25" id="setfix_reason" >${reason}</div>
    <textarea class="widthB90 margin-LF-auto displayBlock height140" style="resize:none;" id="setfix_fix_fixreasontxt" cols="" rows=""></textarea>
    <div class="clearBoth widthB90 margin-LF-auto margin-top-15">
    	<select class="select widthB100" id="setfix_fix_fixreasonsel">
            <option value=""> </option>
            <c:forEach items="${codes}" var="code">
                <option value="${code.codeNamec}">${code.codeNamec}</option>
            </c:forEach>
        </select>
    </div>
    <div class="clearBoth">
        <a id="setfix_fix_cancelbtn" class="button_02 floatR margin-right-20 margin-left-5" href="javascript:;">放弃(N)</a>
        <a id="setfix_fix_okbtn" class="button_02 floatR margin-left-5" href="javascript:;">确定(Y)</a>
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
        seajs.use("${pageContext.request.contextPath}/js/fixAndFrozen/setFix.js?number=" + Math.random());
    </script>
</body>