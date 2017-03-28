<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.css">
<script src="${pageContext.request.contextPath}/scripts/pinyin.js"></script> 
<title>房态</title>
</head>

<body >
<script src="${pageContext.request.contextPath}/scripts/jquery-migrate-1.2.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>	
<!--secondMenu-->
<%@ include file="secondMenu.jsp" %>

<script type="text/html" id="rooms_option">
    {{each list as value i}}
        <option value="{{#value}}">第{{#value}}层</option>
    {{/each}}
</script>
<!-- 单空 -->
<script type="text/html" id="single_empty_right">
    <ul class="clickRight">
        <li click-type="check-in" ><span class="margin-left-15 fontWeight">入住登记</span></li>
        <li click-type="unclean" ><a href="javascript:;">置为不洁</a></li>
        <li click-type="clean-nocheck" ><a href="javascript:;">置为清洁未查</a></li>
        <li click-type="clean-check" ><a href="javascript:;">置为清洁已查</a></li>
        <li click-type="setfrozeninf" ><a href="javascript:;">维修/冻结</a></li>
    </ul>
</script>
<!-- 单住-->
<script type="text/html" id="single_live_right">
    <ul class="clickRight">
        <li click-type="guest-info"><span class="margin-left-15 fontWeight">客单详情</span></li>
        <li click-type="unclean" ><a href="javascript:;">置为不洁</a></li>
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
        <li click-type="check-in" ><span class="margin-left-15 fontWeight">入住登记</span></li>
        <li click-type="unclean" ><a href="javascript:;">置为不洁</a></li>
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
        <p>客人姓名：{{gstNamec}} ({{count}}人)</p>
        <p>宾客来源：{{source}}</p>
        <p>房费：{{roomPrice}}</p>
        <p class="link"></p>
        <p>抵店时间：{{reachDate | dateFormat:'yyyy-MM-dd'}}</p>
        <p>离店时间：{{leaveDate | dateFormat:'yyyy-MM-dd'}}</p>
        <p class="link"></p>
        <p>押金合计：{{lent}}</p>
        <p>费用合计：{{borrow}}</p>
        <p>结余：{{balance}}</p>
        {{if authBalance==null}}
        <p>催押限额：{{authBalance}}</p>
        {{/if}}
        {{if notice==null}}
        <p class="link"></p>
        <p>{{notice}}</p>
        {{/if}}
    </div>
</script>
<!--团队预定信息 -->
<script type="text/html" id="rooms_groupinf_tips">
    <div class="userTips">
        <p>团队：{{GROUPNAME}}({{COUNT}}间)</p>
        <p class="link"></p>
        {{each LIST as value i}}
            {{if value.ISMAIN}}
                <p>{{value.ROOM_ID}}(主)</p>
            {{/if}}
        {{/each}}
        {{each LIST as value i}}
            {{if i<5 && !value.ISMAIN}}
                    <p>{{value.ROOM_ID}}</p>
            {{/if}}
            {{if i==5}}
            <p>...</p>
            {{/if}}
        {{/each}}
        <p class="link"></p>
        <p>团队收款合计：{{borrow}}</p>
        <p>团队费用合计：房租800.00 其他900.00</p>
        <p>团队账目结余：<span {{if balance<0}}style="color:#FF0000"{{/if}}>{{balance}}</span></p>
        <p>团队催押限额：信用{{limit}}，预授权{{auth_balance}}</p>
    </div>
</script>
<!-- 联房信息-->
<script type="text/html" id="rooms_unioninf_tips">
    <div class="userTips">
        <p>联房：({{COUNT}}间)</p>
        <p class="link"></p>
        {{each LIST as value i}}
            {{if value.ISMAIN}}
                <p>{{value.ROOM_ID}}(主)</p>
            {{/if}}
        {{/each}}
        {{each LIST as value i}}
            {{if i<5 && !value.ISMAIN}}
                <p>{{value.ROOM_ID}} </p>
            {{/if}}
            {{if i==5}}
            <p>...</p>
            {{/if}}
        {{/each}}
        <p class="link"></p>
        <p>B账收款合计：{{borrow}}</p>
        <p>B账费用合计：房租800.00 其他900.00</p>
        <p>B账账目结余：<span {{if balance<0}}style="color:#FF0000"{{/if}}>{{balance}}</span></p>
        <p>B账催押限额：信用{{limit}}，预授权{{auth_balance}}</p>
    </div>
</script>
<!--不可用信息 -->
<script type="text/html" id="rooms_unuserfull_tips">
    <div class="nonSaleTips">
        {{each list as value i}}
        {{if i<5}}
        <p>{{value.startDate | dateFormat:'yyyy-MM-dd'}} 至 {{value.endDate | dateFormat:'yyyy-MM-dd'}} {{value.info}}</p>
        {{/if}}
        {{if i==5}}
        <p>...</p>
        {{/if}}
        {{/each}}
    </div>
</script>
<!--  留房信息-->
<script type="text/html" id="rooms_leaveroominf_tips">
    <div class="userTips">
        <p>客户姓名：{{GUESTNAME}}</p>
        <p>联系电话：{{GUESTMOBILE}}</p>
        <p>抵店时间：{{BEGINTIME | dateFormat:'yyyy-MM-dd'}}</p>
        <p>离店时间：{{ENDTIME | dateFormat:'yyyy-MM-dd'}}</p>
        <br>
        <p>销售人员：张无忌</p>
        <p>联系电话：18094755236</p>
    </div>
</script>
<!--预抵-->
<script type="text/html" id="rooms_willcomeroominf_tips">
    <div class="userTips">
        <p>抵客姓名：{{GUESTNAME}}</p>
        <p>联系电话：{{GUESTMOBILE}}</p>
        <p>抵店时间：{{TIME | dateFormat:'hh:mm'}}</p>
        <br>
        <p>销售人员：张无忌</p>
        <p>联系电话：18094755236</p>
    </div>
</script>
<!--预离-->
<script type="text/html" id="rooms_willleaveroominf_tips">
    <div class="userTips">
        <p>今日：{{TIME | dateFormat:'hh:mm'}}</p>
        <p>{{INFO}}</p>
    </div>
</script>
<!--欠费-->
<script type="text/html" id="rooms_arrears_tips">
    <div class="userTips">
        {{each list as value i}}
            {{if i<2}}
            <p>姓名：{{value.namec}}</p>
            <p>结余：{{value.borrow}}</p>
                {{if value.payManFlag==1}}
                <p>催押限额：信用{{value.limit}}，预售权{{value.auth_balance}}</p>
                {{/if}}
                {{if size>1 && ispayMan}}
                    <p class="link"></p>
                {{/if}}
            {{/if}}
            
        {{/each}}
        {{if ispayMan}}
            <p>B账收款合计：{{borrow}}</p>
            <p>B账费用合计：{{lent}}</p>
            <p>B账账目结余：<span {{if balance<0}}style="color:#FF0000"{{/if}}>{{balance}}</span></p>
            <p>B账催押限额：信用{{limit}}，预授权{{auth_balance}}</p>
        {{/if}}
    </div>
</script>
<!--免费房 自用房-->
<script type="text/html" id="rooms_free_tips">
    <div class="userTips">
        {{info}}
    </div>
</script>
<!--secondMenu -end- -->
<!--banner & menu -END -->
<!--center xingli 2015-09-08-->
<div class="center">
    <!--mainWeb-->
    <div class="main" id="webMain">
    	<!--mainInformation-->
    	<div class="mainInfo overFlowY">
            <div class="status" >
                <!-- 房态图-->
                <ul class="statusUl">
                    <%@ include file="room_color.jsp" %>
                </ul>
            </div>
        </div>
    	<%@ include file="room_rightMenu.jsp" %>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
    </div>
    <!--mainWeb end-->
</div>
<!--center -END -->
<!--copyRight xingli 2015-09-08-->

<%@ include file="../footer.jsp" %>
<!--copyRight -END -->
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/guestdetail/roomGuest.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
<script>
    var rooms_model_cach = new Array();//缓存当前模块共用数据
    setSecondMenuStat(1);
    seajs.config({
        base: '${pageContext.request.contextPath}/js/lib/'
        , alias: {
            'template': 'template/template'
        }
    });
    var typeValues = '${roomsSearchVo.fangxinvalues}';
	var pageType = ${roomsSearchVo.type};//0入住登记 1.入住登记
    if(pageType==0)
    {
        seajs.use(['${pageContext.request.contextPath}/js/rooms/plugins/rooms'
        ,'${pageContext.request.contextPath}/js/rooms/plugins/roomSP']);
    }else if(pageType==1)
    {
        seajs.use(['${pageContext.request.contextPath}/js/rooms/plugins/roomStatSearch'
        ,'${pageContext.request.contextPath}/js/rooms/plugins/roomSP']);
    }
    
</script>
</html>
