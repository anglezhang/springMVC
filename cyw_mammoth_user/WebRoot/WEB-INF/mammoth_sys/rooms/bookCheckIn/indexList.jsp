<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房态--预定入住</title>
<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/autocomplete/jquery.autocomplete.css">
</head>

<body>
<%@ include file="../../header.jsp"%>
<script src="${ctx}/scripts/pinyin.js"></script> 
<script src="${ctx}/scripts/jquery-migrate-1.2.1.js" type="text/javascript"></script>
<script src="${ctx}/js/lib/autocomplete/jquery.autocomplete.min.js"></script>
<script src="${ctx}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>  
<!--secondMenu-->
<%@ include file="../secondMenu.jsp"%>
<!--secondMenu -end- -->
<!--banner & menu -END -->
<!--center xingli 2015-09-08-->
<div class="center">
    <!--mainWeb-->
    <div class="main" id="webMain">
    	<!--mainInformation-->
    	<div class="RoomCheckIn overFlowY" style="overflow-x: hidden;overflow-y: auto;">
            <!--table-->
           	<div class="tableDiv yd-SK" id="orderList" style="overflow-x: hidden; overflow-y: auto;height: 664px;"></div>
            <!--table-->
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<div class="rightMenu rightMenuFT">
        	<div class="roomStatus">
            	<ul class="roomStatusTitle">
                    <li id="fitBookCheckInTitle" class="point" >散客预订</li>
                    <li id="groupBookCheckInTitle">团队预订</li>
                </ul>
                <input type="hidden" id="currentItemFlag" name="currentItemFlag" value="fit" />
                <div class="clearBoth"></div>
                <div class="choice padding-top-10 padding-bottom-30">
                    <form id="bookCheckInForm" name="bookCheckInForm" class="checkLogin" action="${ctx}/bookCheckIn/findFitOrderList.do" method="post">
                        <dl class="inputDiv margin-top-none">
                            <dt>订单号</dt>
                            <dd>
                                <input class="input" name="book_list" id="book_list" type="text" data-inputmask="'mask': '*{0,20}'"/>
                            </dd>
                            
                            <!-- 散客预定查询条目 -->
                            <dt class="fitBookCheckInSearchItem">中文名</dt>
                            <dd class="fitBookCheckInSearchItem">
                                <input class="input" name="gstNamec" id="gstName" type="text" maxlength="10" />
                            </dd>
                            <dt class="fitBookCheckInSearchItem">英文名</dt>
                            <dd class="fitBookCheckInSearchItem">
                                <input class="input" name="gstNamee" type="text" data-inputmask="'mask': 'a{0,40}'"/>
                            </dd>
                            <dt class="fitBookCheckInSearchItem">预订人</dt>
                            <dd class="fitBookCheckInSearchItem">
                                <input class="input" name="book_operid" id="book_operid" type="text" maxlength="10" />
                            </dd>
                            <!-- 散客预定查询条目 -->
                            <!-- 团体预定查询条目 -->
                            <dt class="groupBookCheckInSearchItem" style="display: none;">团代码</dt>
                            <dd class="groupBookCheckInSearchItem" style="display: none;">
                                <input class="input" name="groupCode" id="groupCode" type="text" maxlength="10" />
                            </dd>
                            <dt class="groupBookCheckInSearchItem" style="display: none;">团名</dt>
                            <dd class="groupBookCheckInSearchItem" style="display: none;">
                                <input class="input" name="groupName" id="groupName" type="text" maxlength="40" />
                            </dd>
                            <dt class="groupBookCheckInSearchItem" style="display: none;">领队名</dt>
                            <dd class="groupBookCheckInSearchItem" style="display: none;">
                                <input class="input" name="leaderName" id="leaderName" type="text" maxlength="10"/>
                            </dd>
                            <!-- 团体预定查询条目 -->
                            <dt>电话</dt>
                            <dd>
                                <input class="input" name="mobile" id="mobile" type="text" data-inputmask="'mask': '9{0,11}'" />
                            </dd>
                            
                            <!-- <dt>合约单位</dt>
                            <dd>
                                <input class="input" name="comp_name" id="comp_name" type="text" />
                            </dd> -->
                            <dt>抵店日期</dt>
                            <dd><!--  value="${now_ymd}" -->
                                <input class="input ui-widget-content ui-corner-all Wdate gry_9" name="reachDate" id="reachDate1" type="text" readonly="readonly" value="${hotelDate}"/>
                            </dd>
                            <dt>离店日期</dt>
                            <dd>
                                <input class="input ui-widget-content ui-corner-all Wdate" name="leaveDate" id="leaveDate1" type="text" readonly="readonly"/>
                            </dd>
                            <!-- <dt>销售员</dt>
                            <dd>
                                <input class="input" name="salePeson" id="salePeson" type="text" />
                            </dd> -->
                            <dt>房间号</dt>
                            <dd>
                                <input class="input" name="room_id" id="room_id" type="text" data-inputmask="'mask': '9{0,6}'" />
                            </dd>
                            <dt>状态</dt>
                            <dd>
                                <select class="select widthB98" id="bookStat" name="bookStat">
                                	<option value="" selected="selected"></option>
									<option value="B" ${bookRoomSearchVo.bookStat eq "B" ? "selected=\"selected\"" : ""} >未确认</option>
	                                <!-- <option value="C" ${bookRoomSearchVo.bookStat eq "C" ? "selected=\"selected\"" : ""} >取消</option> -->
	                                <option value="O" ${bookRoomSearchVo.bookStat eq "O" ? "selected=\"selected\"" : ""} >已确认</option>
	                                <option value="R" ${bookRoomSearchVo.bookStat eq "R" ? "selected=\"selected\"" : ""} >部分抵达</option>
	                                <!-- <option value="A" ${bookRoomSearchVo.bookStat eq "A" ? "selected=\"selected\"" : ""} >全部抵达</option>
	                                <option value="N" ${bookRoomSearchVo.bookStat eq "N" ? "selected=\"selected\"" : ""} >NO SHOW</option> -->
                                </select>
                            </dd>
                            
                        </dl>
                    </form>
                    <div class="cabDivNoneHei clearBoth padding-top-5">                      
                        <a id="searchForm_a" class="button_07 floatL" href="javascript:searchForm();">条件查询</a>
                        <a class="button_07 floatL" href="javascript:clearSearchForm();">清空条件</a>
                        <a class="button_07 floatL" href="javascript:checkIn();">办理入住</a>
                        <a class="button_07 floatL" href="javascript:;">打&nbsp;&nbsp;印</a>
                    </div>
                    <form id="bookCheckInForm1" name="bookCheckInForm1" class="checkLogin" action="${ctx}/bookCheckIn/findFitOrderList.do" method="post">
                        <input type="hidden" id="symbol" name="symbol" />
                        <input type="hidden" id="codeLetter" name="codeLetter" />
                    </form>
                    <!--小键盘-->
                    <div class="cabDiv clearBoth">
                        <a href="javascript:searchFormForKeys('a');">A</a>
                        <a href="javascript:searchFormForKeys('b');">B</a>
                        <a href="javascript:searchFormForKeys('c');">C</a>
                        <a href="javascript:searchFormForKeys('d');">D</a>
                        <a href="javascript:searchFormForKeys('e');">E</a>
                        <a href="javascript:searchFormForKeys('f');">F</a>
                        <a href="javascript:searchFormForKeys('g');">G</a>
                        <a href="javascript:searchFormForKeys('h');">H</a>
                        <a href="javascript:searchFormForKeys('i');">I</a>
                        <a href="javascript:searchFormForKeys('j');">J</a>
                        <a href="javascript:searchFormForKeys('k');">K</a>
                        <a href="javascript:searchFormForKeys('l');">L</a>
                        <a href="javascript:searchFormForKeys('m');">M</a>
                        <a href="javascript:searchFormForKeys('n');">N</a>
                        <a href="javascript:searchFormForKeys('o');">O</a>
                        <a href="javascript:searchFormForKeys('p');">P</a>
                        <a href="javascript:searchFormForKeys('q');">Q</a>
                        <a href="javascript:searchFormForKeys('r');">R</a>
                        <a href="javascript:searchFormForKeys('s');">S</a>
                        <a href="javascript:searchFormForKeys('t');">T</a>
                        <a href="javascript:searchFormForKeys('*')">*</a>
                        <a href="javascript:searchFormForKeys('u');">U</a>
                        <a href="javascript:searchFormForKeys('v');">V</a>
                        <a href="javascript:searchFormForKeys('w');">W</a>
                        <a href="javascript:searchFormForKeys('x');">X</a>
                        <a href="javascript:searchFormForKeys('y');">Y</a>
                        <a href="javascript:searchFormForKeys('z');">Z</a>
                        <a href="javascript:searchFormForKeys('#');">#</a>
                    </div>
                    <!--小键盘END-->
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
<%@ include file="../../footer.jsp"%>
<!--copyRight -END -->
<!--弹出层阴影-->
<!-- <div class="alertDivBg"></div>
<div class="alertDivBg2"></div>
<div class="alertDivBg3"></div> -->
<!--弹出层阴影结束-->
<!--预定登记-->
<script type="text/javascript" src="${ctx}/js/rooms/bookCheckIn/indexList.js"></script>
<script type="text/javascript" src="${ctx}/js/rooms/bookCheckIn/checkInInput.js"></script>
<script type="text/javascript" src="${ctx}/js/rooms/bookCheckIn/bookDetail.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.inputmask.min.js"></script>
<%@ include file="checkInInput.jsp"%>
<!--/预定登记-->
<!--订单详情-->
<%@ include file="bookDetail.jsp"%>


<!-- <script type="text/javascript" src="${ctx}/js/rooms/bookCheckIn/bookRoomGuests.js"></script> -->
<!--/订单详情-->
</body>
</html>