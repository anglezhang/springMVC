<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房态管理</title>
</head>

<body>
<%@ include file="../header.jsp" %>
<!--secondMenu-->
<%@ include file="../rooms/secondMenu.jsp" %>
<script type="text/html" id="rooms_option">
    {{each list as value i}}
        <option value="{{#value}}">第{{#value}}层</option>
    {{/each}}
</script>
<!-- 单空 -->
<script type="text/html" id="single_empty_right">
    <ul class="clickRight">
        <li click-type="unclean" ><span class="margin-left-15 fontWeight">置为不洁</span></li>
        <li click-type="clean-nocheck" ><a href="javascript:;">置为清洁未查</a></li>
        <li click-type="clean-check" ><a href="javascript:;">置为清洁已查</a></li>
    </ul>
</script>
<!-- 单住-->
<script type="text/html" id="single_live_right">
    <ul class="clickRight">
        <li click-type="unclean" ><span class="margin-left-15 fontWeight">置为不洁</span></li>
        <li click-type="clean-nocheck" ><a href="javascript:;">置为清洁未查</a></li>
        <li click-type="clean-check" ><a href="javascript:;">置为清洁已查</a></li>
    </ul>
</script>
<!-- 多混 -->
<script type="text/html" id="multiple_mixed_right">
    <ul class="clickRight">
        <li click-type="unclean" ><span class="margin-left-15 fontWeight">置为不洁</span></li>
        <li click-type="clean-nocheck"><a href="javascript:;">置为清洁未查</a></li>
        <li click-type="clean-check"><a href="javascript:;">置为清洁已查</a></li>
    </ul>
</script>
<!--多空 -->
<script type="text/html" id="multiple_empty_right">
    <ul class="clickRight">
        <li click-type="unclean" ><span class="margin-left-15 fontWeight">置为不洁</span></li>
        <li click-type="clean-nocheck"><a href="javascript:;">置为清洁未查</a></li>
        <li click-type="clean-check"><a href="javascript:;">置为清洁已查</a></li>
    </ul>
</script>
<!-- 清洁未查-->
<script type="text/html" id="clear_uncheck">
    <img src="${pageContext.request.contextPath}/img/ft-03.png">
</script>
<!--脏房 -->
<script type="text/html" id="clear_unclean">
    <img src="${pageContext.request.contextPath}/img/ft-02.png">
</script>
<!-- 客户信息-->
<script type="text/html" id="rooms_guestinf_tips">
    <div class="userImgTips">
        <p>客人姓名：{{gstNamec}}</p>
        <p>宾客来源：{{gstOriId}}</p>
        <p>房费：{{prcSchemeId}}</p>
        <p class="link"></p>
        <p>抵店时间：{{reachDate | dateFormat:'yyyy-MM-dd hh:mm:ss'}}</p>
        <p>离店时间：{{leaveDate | dateFormat:'yyyy-MM-dd hh:mm:ss'}}</p>
        <p class="link"></p>
        <p>压金合计：押金</p>
        <p>费用合计：费用</p>
        <p>结余：999</p>
    </div>
</script>
<!--团队预定信息 -->
<script type="text/html" id="rooms_groupinf_tips">
    <div class="userTips">
        <p>团队：{{GROUPNAME}}({{COUNT}}间)</p>
        <p class="link"></p>
        {{each LIST as value i}}
        <p>{{value.ROOM_ID}} {{if value.ISMAIN}}(主){{/if}}</p>
        {{/each}}
    </div>
</script>
<!-- 联房信息-->
<script type="text/html" id="rooms_unioninf_tips">
    <div class="userTips">
        <p>联房：({{COUNT}}间)</p>
        <p class="link"></p>
        {{each LIST as value i}}
        <p>{{value.ROOM_ID}} {{if value.ISMAIN}}(主){{/if}}</p>
        {{/each}}
    </div>
</script>
<!--不可用信息 -->
<script type="text/html" id="rooms_unuserfull_tips">
    <div class="nonSaleTips">
        {{each list as value i}}
        <p>{{value.startDate | dateFormat:'yyyy-MM-dd hh:mm:ss'}} 至 {{value.endDate | dateFormat:'yyyy-MM-dd hh:mm:ss'}} {{value.info}}</p>
        {{/each}}
    </div>
</script>
<div class="center">
	<div class="main" >
		<div class="mainInfo overFlowY">
			<div class="status">
				<ul class="statusUl" style="margin: 1px 1px 1px 1px;">
					<%@ include file="room_color.jsp" %>
				</ul>
			</div>
		</div>
		<%@ include file="roomstat_rightMenu.jsp" %>
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
    seajs.use("${pageContext.request.contextPath}/js/roomsManager/roomstat");
</script>
</body>