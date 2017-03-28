<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>住客-住客资料</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.css">
</head>
<body>
<%@ include file="../header.jsp" %>
<script src="${pageContext.request.contextPath}/scripts/jquery-migrate-1.2.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.min.js"></script>	
<script src="${pageContext.request.contextPath}/js/jQuery.Hz2Py-min.js"></script> 
<script src="${pageContext.request.contextPath}/scripts/pinyin.js"></script> 
<!--secondMenu-->
<div class="secondMenuDiv">
	<ul class="secondMenu">
         <li><a href="gstIndex.do">住客资料</a></li>
        <li><a class="thisSecMenu" href="groupIndex.do">团队资料</a></li>
    </ul>
</div>
<!--secondMenu -end- -->
<!--banner & menu -END -->
<!--center xingli 2015-09-08-->
<div class="center">
    <!--mainWeb-->
    <div class="main" id="webMain">
    	<!--mainInformation-->
        <div class="mainInfo2">
        <!--table-->
            	<div class="tableDiv" id="groupGrid" style="overflow-x: hidden; overflow-y: auto;height: 662px;">
				</div>
                <!--table -END- -->
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<div class="rightMenu">
        	<div class="roomStatus">                
                <div class="choice padding-bottom-10">
                    <form class="bookRight"  id="searchForm">
                        <dl class="inputDiv2 margin-top-none">
                            <dt>团代码</dt>
                            <dd>
                                <input class="input gry_9" name="grpId" type="text" >
                            </dd>
                            <dt>团名</dt>
                            <dd>
                                <input class="input" name="grpName" type="text" placeholder="">
                            </dd>
                            <dt>领队名</dt>
                            <dd>
                                <input class="input" name="leadNamec" type="text" placeholder="">
                            </dd>
                            <dt>抵店日期</dt>
                            <dd>
                                <input class="input" name="reachDate" type="text" placeholder="" onclick="WdatePicker();">
                            </dd>
                            <dt>离店日期</dt>
                            <dd>
                                <input class="input" name="leaveDate" type="text" placeholder="" onclick="WdatePicker();">
                            </dd>
                            <dt>房间号</dt>
                            <dd>
                                <input class="input" name="roomId" type="text" placeholder="" id="searchGroupRoomId">
                            </dd>
                            
                        </dl>
                        <div class="clearBoth"></div>
                        <table width="90%" class="margin-left-10 margin-top-20">
                            <tr>
                                <td align="right"><label  class="margin-right-10"><input name="chkStat" type="radio" checked="checked" value='I'><span class="margin-left-5">在住</span></label></td>
                                <td align="left"><label class="margin-left-10"><input name="chkStat" type="radio" value='O'><span class="margin-left-5">离店</span></label></td>
                            </tr>
                        </table>
                        <div class="cabDivNoneHei padding-top-15">
                            <a class="button_07 floatL" href="javascript:;" onclick="doSearch()">条件查询</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="doClear()">清空条件</a>
                            <a class="button_07 floatL" href="javascript:;" id="checkDetails" onclick="showGroupDetails()" >团队详情</a>
                            <a class="button_07 floatL" href="javascript:;" <mammoth:AuthJudge funcId="c_380878" />>电话设置</a>
                        </div>
                        <table width="86%" class="margin-left-10 margin-bottom-10">
                            <tr>
                                <td width="25%" align="right"><span class="margin-right-5">房号</span></td>
                                <td width="25%"  align="left"><input class="input" name="" type="text" placeholder="" id="searchRoomId" onkeypress="searchByRoom(event)"></td>
                                <td width="25%"  align="right"><span class="margin-right-5">账号</span></td>
                                <td align="left"><input class="input" name="" type="text" placeholder="" id="searchaAccountId"  onkeypress="searchByAccount(event)"></td>
                            </tr>
                        </table>
                        <!--小键盘-->
                        <div class="cabDiv clearBoth">
                        	<a href="javascript:;">A</a>
                            <a href="javascript:;">B</a>
                            <a href="javascript:;">C</a>
                            <a href="javascript:;">D</a>
                            <a href="javascript:;">E</a>
                            <a href="javascript:;">F</a>
                            <a href="javascript:;">G</a>
                            <a href="javascript:;">H</a>
                            <a href="javascript:;">I</a>
                            <a href="javascript:;">J</a>
                            <a href="javascript:;">K</a>
                            <a href="javascript:;">L</a>
                            <a href="javascript:;">M</a>
                            <a href="javascript:;">N</a>
                            <a href="javascript:;">O</a>
                            <a href="javascript:;">P</a>
                            <a href="javascript:;">Q</a>
                            <a href="javascript:;">R</a>
                            <a href="javascript:;">S</a>
                            <a href="javascript:;">T</a>
                            <a href="javascript:;">*</a>
                            <a href="javascript:;">U</a>
                            <a href="javascript:;">V</a>
                            <a href="javascript:;">W</a>
                            <a href="javascript:;">X</a>
                            <a href="javascript:;">Y</a>
                            <a href="javascript:;">Z</a>
                            <a href="javascript:;">#</a>
                        </div>
                        <!--小键盘END-->
                    </form>
                    <div class="clearBoth"></div>
                </div>
            </div>
        </div>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
    </div>
    <!--mainWeb end-->
</div>
<!--center -END -->
<!--copyRight xingli 2015-09-08-->
<%@ include file="../footer.jsp" %>
<!--弹出层阴影-->
<div class="alertDivBg"></div>
<div class="alertDivBg2"></div>
<div class="alertDivBg3"></div>
<!--弹出层阴影结束-->
<%@ include file="groupdetail.jsp" %>
<!--/拆分账目弹出框-->
<!--/离店弹出框-->
<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/guests/group.js">
</script>
<script src="${pageContext.request.contextPath}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
		<script>
			var rooms_model_cach = new Array();//缓存当前模块共用数据
			var guestDetaiAccFlexGrid = null;//住客资料账务grid对象
			var moneyId ='${moneyId}';
			var refundCode = '${refundCode}';
			seajs.config({
		        base: '${pageContext.request.contextPath}/js/lib/'
		        , alias: {
		            'template': 'template/template'
		        }
		    });
		    seajs.use(["${pageContext.request.contextPath}/js/guestdetail/guestaccount.js?number=" + Math.random()]);
		</script>
</body>
</html>
