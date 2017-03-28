<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/syssetting/flexGridEditComm.css">
<title>维修冻结</title>
</head>
<%@ include file="../header.jsp" %>
<!--secondMenu-->
<%@ include file="../rooms/secondMenu.jsp" %>
<!-- 单空 -->
<script type="text/html" id="single_empty_right">
    <ul class="clickRight">
        <li click-type="setfix" ><span class="margin-left-15 fontWeight">设为维修</span></li>
        <li click-type="setfrozen" ><a href="javascript:;">设为冻结</a></li>
    </ul>
</script>
<!-- 单住-->
<script type="text/html" id="multiple_empty_right">
    <ul class="clickRight">
        <li click-type="setfix" ><span class="margin-left-15 fontWeight">设为维修</span></li>
        <li click-type="setfrozen" ><a href="javascript:;">设为冻结</a></li>
    </ul>
</script>
<script type="text/html" id="rooms_option">
    {{each list as value i}}
        <option value="{{#value}}">第{{#value}}层</option>
    {{/each}}
</script>
<div class="center">
	<div class="main" >
		<div class="mainInfo overFlowY">
			<div class="status">
				<ul class="statusUl">
					<%@ include file="../roomsManager/room_color.jsp" %>
				</ul>
			</div>
		</div>
		<%@ include file="fixAndFrozen_rightMenu.jsp" %>
        <div class="clearBoth"></div>
	</div>
</div>
<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<%@ include file="../footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/altDialog/altDialog.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
<script>
	seajs.config({
        base: '${pageContext.request.contextPath}/js/lib/'
        , alias: {
            'template': 'template/template'
        }
    });
    var typeValues = '${roomsSearchVo.fangxinvalues}';
    seajs.use("${pageContext.request.contextPath}/js/fixAndFrozen/fixAndFrozen");
</script>
</body>