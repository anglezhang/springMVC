<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!--房态右侧菜单 -->
<div class="rightMenu rightMenuFT">
	<div class="roomStatus">
    	<ul class="roomStatusTitle">
        	<li class="point" id="userCheckIn" <mammoth:AuthJudge funcId="c_408466" />>入住登记</li>
            <li id="userCurrentState" <mammoth:AuthJudge funcId="c_265717" />>房态查询</li>
        </ul>
        <div class="clearBoth"></div>
        <div class="choice padding-top-10 padding-bottom-30" >
            <form class="checkLogin" action="${pageContext.request.contextPath}/rooms.do" method="post" <c:if test="${roomsSearchVo.type==0}">id="rooms_searchvo"</c:if> show-id="userCheckIn" >
                <table width="90%" class="margin-left-10 margin-bottom-10 margin-top-20">
                    <tr>
                        <td width="50%" align="center">
                            <input  <c:if test="${roomsSearchVo.type==0}">name="isAll"</c:if> <c:if test="${roomsSearchVo.isAll==false}"> checked </c:if> type="radio" value="false" >
                            <span class="margin-left-10">可售房</span>
                            <input type="hidden" name="rowHouse" value="${roomsSearchVo.rowHouse}" >
                            <input type="hidden" name="type" value="${roomsSearchVo.type}" >
                        </td>
                        <td align="center">
                            <input <c:if test="${roomsSearchVo.type==0}">name="isAll"</c:if> <c:if test="${roomsSearchVo.isAll==true}"> checked </c:if> value="true" type="radio"  >
                            <span class="margin-left-10">全部</span>
                        </td>
                    </tr>
                </table>
                <dl class="inputDiv margin-top-none">
                    <dt>楼名</dt>
                    <dd>
                        <input type="hidden" is-set="false" value="${roomsSearchVo.louceng}" <c:if test="${roomsSearchVo.type==0}">id="rooms_select_louceng"</c:if> >
                        <select class="select widthB100" name="louming"  <c:if test="${roomsSearchVo.type==0}">id="rooms_buildId"</c:if>>
                            <option value="">全部</option>
                        	<c:forEach items="${hList}" var="hbuilding">
                                <option value="${hbuilding.codeId}" <c:if test="${roomsSearchVo.louming==hbuilding.codeId}"> selected </c:if> >${hbuilding.codeNamec}</option>
                            </c:forEach>
                        </select>
                    </dd>
                    <dt>楼层</dt>
                    <dd>
                        <select class="select widthB100" name="louceng" <c:if test="${roomsSearchVo.type==0}">id="rooms_floorNo"</c:if> >
                        	<option value="">全部</option>
                        </select>
                    </dd>
                    <dt>房类</dt>
                    <dd>
                    	<table width="100%">
                        	<tr>
                            	<td style="padding:0;">
                                	<input class="input gry_9" <c:if test="${roomsSearchVo.type==0}">id="rooms_roomchoose"</c:if> type="text" name="fangxingShow" readonly value="${roomsSearchVo.fangxingShow}">
                                    <input <c:if test="${roomsSearchVo.type==0}">id="rooms_roomchoose_value" name="fangxin" </c:if> type="hidden" name="fangxin" value="${roomsSearchVo.fangxin}">
                                    <input <c:if test="${roomsSearchVo.type==0}">id="rooms_roomchoose_typevalues" name="fangxinvalues" </c:if> type="hidden" value="" >
                                </td>
                                <td width="28" align="right">
                                	<a <c:if test="${roomsSearchVo.type==0}">id="rooms_roomchoose_btn"</c:if> class="SFbutton floatR marginRight-4 roomChooseButton" href="javascript:;">···</a>
                                </td>
                            </tr>
                        </table>
                    </dd>
                    <dt>特征</dt>
                    <dd>
                    	<table width="100%">
                        	<tr>
                            	<td style="padding:0;">
                                	<input <c:if test="${roomsSearchVo.type==0}">id="rooms_features" name="fangjiantezheng"</c:if>  class="input gry_9"  type="text" readonly value="${roomsSearchVo.fangjiantezheng}">
                                    <input <c:if test="${roomsSearchVo.type==0}">id="rooms_features_veiw" name="fangjiantezhengID" </c:if>   type="hidden" value="${roomsSearchVo.fangjiantezhengID}">
                                </td>
                                <td width="28" align="right">
                                	<a <c:if test="${roomsSearchVo.type==0}">id="rooms_roomchoosefeatures_btn"</c:if> class="SFbutton floatR marginRight-4 featuresChooseButton" href="javascript:;">···</a>
                                </td>
                            </tr>
                        </table>
                    </dd>
                    <dt>开始日期</dt>
                    <dd>
                        <input class="input gry_9"  name="startdate" <c:if test="${roomsSearchVo.type==0}">id="roomssearchvo_startdate"</c:if> readonly type="text" value="${ fn:substring(roomsSearchVo.startdate,0,10)}">
                        <input type="hidden" <c:if test="${roomsSearchVo.type==0}">id="roomssearchvo_startdate_minDate"</c:if>  value="${startDate}">
                    </dd>
                    <dt>截止日期</dt>
                    <dd>
                        <input class="input" name="enddate" <c:if test="${roomsSearchVo.type==0}">id="roomssearchvo_enddate"</c:if> type="text"  value="${fn:substring(roomsSearchVo.enddate,0,10)}">
                        <input <c:if test="${roomsSearchVo.type==0}">id="roomssearchvo_old_enddate"</c:if> type="hidden"  value="${fn:substring(roomsSearchVo.enddate,0,10)}">
                    </dd>
                </dl>
                <div class="clearBoth"></div>
                <ul class="roomNum" <c:if test="${roomsSearchVo.type==0}">id="rooms_roomid_div"</c:if> >
                </ul>
                <div class="fundRoomNum">
                    <span><a href="javascript:;" class="checkRoom textDecoration" <c:if test="${roomsSearchVo.type==0}">id="rooms_roomid_count"</c:if>>选定房间：0</a></span>
                </div>
                <div class="clearBoth"></div>
                <div class="cabDivNoneHei padding-top-15">
                	<a class="button_07 floatL" href="javascript:;" <c:if test="${roomsSearchVo.type==0}">id="rooms_btn_refresh"</c:if>>刷新</a>
                    <a class="button_07 floatL" href="javascript:;" <c:if test="${roomsSearchVo.type==0}">id="rooms_btn_clear"</c:if>>清空条件</a>
                    <a class="button_07 floatL" href="javascript:;" <c:if test="${roomsSearchVo.type==0}">id="rooms_btn_checkin"</c:if>  <mammoth:AuthJudge funcId="c_247300" />>入住登记</a>
                    <a class="button_07 floatL" href="javascript:;" <c:if test="${roomsSearchVo.type==0}">id="rooms_btn_guestinf"</c:if>  <mammoth:AuthJudge funcId="c_965748" />>客单详情</a>
                    <a class="button_07 floatL" href="javascript:;" able-stat='false' <c:if test="${roomsSearchVo.type==0}">id="rooms_btn_rowhouse"</c:if> style="color: grey;cursor:not-allowed;"  <mammoth:AuthJudge funcId="c_207402" />>自动排房</a>
                    <a class="button_07 floatL" href="javascript:;" <c:if test="${roomsSearchVo.type==0}">id="rooms_btn_vipquery"</c:if>>熟客查询</a>
                </div>
            </form>
            <div class="currentState displayNone" id="f_div" show-id="userCurrentState">
            <form action="${pageContext.request.contextPath}/rooms.do" method="post" <c:if test="${roomsSearchVo.type==1}">id="rooms_searchvo"</c:if> >
                <input  <c:if test="${roomsSearchVo.type==1}">name="isAll"</c:if> type="hidden" value="${roomsSearchVo.isAll}" >
                <input  <c:if test="${roomsSearchVo.type==1}">name="type"</c:if> type="hidden" value="${roomsSearchVo.type}" >
                <a class="stateLink green_bg">
                    <label> 
                        <input  class="checkbox margin-right-10" id="kongfang" data-v="195" name="roomStat" type="checkbox"  <c:if test="${fn:contains(roomsSearchVo.roomStat,'V')}">checked</c:if> value="V">空房
                    </label></a>
                <a class="stateLink red_bg">
                    <label>
                        <input class="checkbox margin-right-10" id="zaizhu" data-v="2047" name="roomStat" type="checkbox"  <c:if test="${fn:contains(roomsSearchVo.roomStat,'O')}"> <c:if test="${!fn:contains(roomsSearchVo.roomStat,'OOO')}"><c:if test="${!fn:contains(roomsSearchVo.roomStat,'OOI')}"> checked </c:if></c:if></c:if> <c:if test="!${roomsSearchVo.roomStat eq 'OOO,OOI'}"> <c:if test="${fn:contains(roomsSearchVo.roomStat,'O,OOI')}"> checked </c:if><c:if test="${fn:contains(roomsSearchVo.roomStat,'O,OOO')}"> checked </c:if></c:if> <c:if test="${roomsSearchVo.roomStat eq 'O,OOO,OOI'}"> checked </c:if>  <c:if test="${fn:contains(roomsSearchVo.roomStat,',O,')}"> checked </c:if> value="O">在住房
                    </label>
                </a>
                <a class="stateLink yellow_bg">
                    <label>
                        <input class="checkbox margin-right-10" id="weixiu" data-v="0" name="roomStat" type="checkbox"  <c:if test="${fn:contains(roomsSearchVo.roomStat,'OOO')}">checked</c:if> value="OOO">维修房
                    </label>
                </a>
                <a class="stateLink blue_bg">
                    <label>
                        <input class="checkbox margin-right-10" id="dongjie" data-v="0" name="roomStat" type="checkbox"  <c:if test="${fn:contains(roomsSearchVo.roomStat,'OOI')}">checked</c:if> value="OOI">冻结房
                    </label>
                </a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.clearStatu,'DP')}">stateSel</c:if>" input-value="DP" id="kt_1" >
                    <label>
                        <input type="hidden"  <c:if test="${fn:contains(roomsSearchVo.clearStatu,'DP')}">name="clearStatu" value="DP"</c:if>><img src="${pageContext.request.contextPath}/img/lb_02.png">不洁
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.clearStatu,'CP')}">stateSel</c:if>" input-value="CP" id="kt_2" >
                    <label>
                        <input type="hidden" <c:if test="${fn:contains(roomsSearchVo.clearStatu,'CP')}">name="clearStatu" value="CP"</c:if>><img src="${pageContext.request.contextPath}/img/lb_03.png">未查
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-01.png')}">stateSel</c:if>" input-value="st-01.png" id="kt_4" >
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-01.png')}">name="guestStatus" value="st-01.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_04.png">自用
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-05.png')}">stateSel</c:if>" input-value="st-05.png" id="kt_8" >
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-05.png')}">name="guestStatus" value="st-05.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_08.png">免费
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-04.png')}">stateSel</c:if>" input-value="st-04.png" id="kt_16" >
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-04.png')}">name="guestStatus" value="st-04.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_09.png">钟点
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-06.png')}">stateSel</c:if>" input-value="st-06.png" id="kt_32" >
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-06.png')}">name="guestStatus" value="st-06.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_05.png">预离
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-07.png')}">stateSel</c:if>" input-value="st-07.png" id="kt_64" >
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-07.png')}">name="guestStatus" value="st-07.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_06.png">预抵
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-02.png')}">stateSel</c:if>" input-value="st-02.png" id="kt_128">
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-02.png')}">name="guestStatus" value="st-02.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_07.png">留房
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-08.png')}">stateSel</c:if>" input-value="st-08.png" id="kt_256">
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-08.png')}">name="guestStatus" value="st-08.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_10.png">欠费
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-09.png')}">stateSel</c:if>" input-value="st-09.png" id="kt_512">
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-09.png')}">name="guestStatus" value="st-09.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_11.png">联房
                    </label><span class="ico"></span></a>
                <a class="stateLB <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-03.png')}">stateSel</c:if>" input-value="st-03.png" id="kt_1024" >
                    <label><input type="hidden" <c:if test="${fn:contains(roomsSearchVo.guestStatus,'st-03.png')}">name="guestStatus" value="st-03.png"</c:if>><img src="${pageContext.request.contextPath}/img/lb_12.png">团队
                    </label><span class="ico"></span></a>

                <dl class="inputDiv margin-top-none clearBoth">
                    <dt>楼名</dt>
                    <dd>
                        <input type="hidden" is-set="false" value="${roomsSearchVo.louceng}" <c:if test="${roomsSearchVo.type==1}">id="rooms_select_louceng"</c:if> >
                        <select class="select widthB100" name="louming"  <c:if test="${roomsSearchVo.type==1}">id="rooms_buildId"</c:if>>
                            <option value="">全部</option>
                            <c:forEach items="${hList}" var="hbuilding">
                                <option value="${hbuilding.codeId}" <c:if test="${roomsSearchVo.louming==hbuilding.codeId}"> selected </c:if> >${hbuilding.codeNamec}</option>
                            </c:forEach>
                        </select>
                    </dd>
                    <dt>楼层</dt>
                    <dd>
                        <select class="select widthB100"  <c:if test="${roomsSearchVo.type==1}">id="rooms_floorNo"</c:if>>
                        	<option value="">全部</option>
                        </select>
                    </dd>
                    <dt>房类</dt>
                    <dd>
                        <table width="100%">
                            <tr>
                                <td style="padding:0;">
                                    <input class="input gry_9" <c:if test="${roomsSearchVo.type==1}">id="rooms_roomchoose"</c:if> type="text" name="fangxingShow" readonly value="${roomsSearchVo.fangxingShow}">
                                    <input <c:if test="${roomsSearchVo.type==1}">id="rooms_roomchoose_value" name="fangxin" </c:if> type="hidden" name="fangxin" value="${roomsSearchVo.fangxin}">
                                    <input <c:if test="${roomsSearchVo.type==1}">id="rooms_roomchoose_typevalues" name="fangxinvalues" </c:if> type="hidden" value="" >
                                </td>
                                <td width="28" align="right">
                                    <a <c:if test="${roomsSearchVo.type==1}">id="rooms_roomchoose_btn"</c:if> class="SFbutton floatR marginRight-4 roomChooseButton" href="javascript:;">···</a>
                                </td>
                            </tr>
                        </table>
                    </dd>
                    <dt>特征</dt>
                    <dd>
                        <table width="100%">
                            <tr>
                                <td style="padding:0;">
                                    <input <c:if test="${roomsSearchVo.type==1}">id="rooms_features" name="fangjiantezheng"</c:if>  class="input gry_9"  type="text" readonly value="${roomsSearchVo.fangjiantezheng}">
                                    <input <c:if test="${roomsSearchVo.type==1}">id="rooms_features_veiw" name="fangjiantezhengID" </c:if> type="hidden" value="${roomsSearchVo.fangjiantezhengID}">
                                </td>
                                <td width="28" align="right">
                                    <a <c:if test="${roomsSearchVo.type==1}">id="rooms_roomchoosefeatures_btn"</c:if> class="SFbutton floatR marginRight-4 featuresChooseButton" href="javascript:;">···</a>
                                </td>
                            </tr>
                        </table>
                    </dd>
                    <dt>开始日期</dt>
                    <dd>
                        <input class="input"  name="startdate" <c:if test="${roomsSearchVo.type==1}">id="roomssearchvo_startdate"</c:if> type="text" value="${ fn:substring(roomsSearchVo.startdate,0,10)}">
                        <input type="hidden" <c:if test="${roomsSearchVo.type==1}">id="roomssearchvo_startdate_minDate"</c:if>  value="${startDate}">
                    </dd>
                    <dt>截止日期</dt>
                    <dd>
                        <input class="input" name="enddate" <c:if test="${roomsSearchVo.type==1}">id="roomssearchvo_enddate"</c:if> type="text"  value="${fn:substring(roomsSearchVo.enddate,0,10)}">
                        <input <c:if test="${roomsSearchVo.type==1}">id="roomssearchvo_old_enddate"</c:if> type="hidden"  value="${fn:substring(roomsSearchVo.enddate,0,10)}">
                    </dd>
                </dl>
                <div class="clearBoth"></div>
                <div class="clearBoth"></div>
                <div class="cabDivNoneHei padding-top-15">
                    <a class="button_07 floatL" href="javascript:;" <c:if test="${roomsSearchVo.type==1}">id="rooms_btn_refresh"</c:if>>条件查询</a>
                    <a class="button_07 floatL" href="javascript:;" <c:if test="${roomsSearchVo.type==1}">id="rooms_btn_clear"</c:if>>清空条件</a>
                </div>
            </form>
            </div>
            <div class="clearBoth"></div>
        </div>
        <div class="clearBoth"></div>
    </div>
</div>