<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!--房态右侧菜单 -->
<div class="rightMenu rightMenuFT2 margin-top-none">
    <div class="roomStatus">
        <div class="choice padding-top-10 padding-bottom-30">
            <form action="${pageContext.request.contextPath}/roomsManager/fastsearch.do" method="post" id="roomstat_fastsearch" >
            </form>
            <form class="currentState" action="${pageContext.request.contextPath}/roomsManager/manager.do" method="post" id="rooms_searchvo">
                <a class="stateLink green_bg" href="javascript:;"><label><input class="checkbox margin-right-5" name="roomStatu" <c:if test="${roomsSearchVo.roomStatu=='V' or roomsSearchVo.roomStatu=='V,O'}">checked</c:if> type="checkbox" value="V">空房</label></a>
                <a class="stateLink red_bg" href="javascript:;"><label><input class="checkbox margin-right-5" name="roomStatu" type="checkbox" <c:if test="${roomsSearchVo.roomStatu=='O' or roomsSearchVo.roomStatu=='V,O'}">checked</c:if> value="O">在住房</label></a>
                <div class="clearBoth"></div>
                <input type="hidden" id="roomstat_clearstatu" name="clearStatu" value="${roomsSearchVo.clearStatu}">
                <input id="roomssearchvo_startdate" type="hidden" value="${ fn:substring(roomsSearchVo.startdate,0,10)}">
                <input id="roomssearchvo_enddate" type="hidden" value="${fn:substring(roomsSearchVo.enddate,0,10)}">
                <a class="stateLB" href="javascript:;" type-check="false" data-value="D"><img src="${pageContext.request.contextPath}/img/lb_02.png">不洁</a>
                <a class="stateLB" href="javascript:;" type-check="false" data-value="CP" ><img src="${pageContext.request.contextPath}/img/lb_03.png">未查</a>
                <a class="stateLB" href="javascript:;" type-check="false" data-value="CI"><img src="${pageContext.request.contextPath}/img/lb_13.png">已查</a>
                <dl class="inputDiv margin-top-none clearBoth">
                    <dt>楼名</dt>
                    <dd>
                        <select class="select widthB100" name="louming" id="rooms_buildId" >
                            <option value="">全部</option>
                            <c:forEach items="${hList}" var="hbuilding">
                                <option value="${hbuilding.codeId}" <c:if test="${roomsSearchVo.louming==hbuilding.codeId}"> selected </c:if> >${hbuilding.codeNamec}</option>
                            </c:forEach>
                        </select>
                    </dd>
                    <dt>楼层</dt>
                    <dd>
                        <input type="hidden" is-set="false" value="${roomsSearchVo.louceng}" id="rooms_select_louceng">
                        <select class="select widthB100" name="louceng" id="rooms_floorNo">
                            <option value="">全部</option>
                        </select>
                    </dd>
                    <dt>房类</dt>
                    <dd>
                        <table width="100%">
                            <tr>
                                <td style="padding:0;">
                                    <input class="input gry_9" id="rooms_roomchoose" type="text" name="fangxingShow" readonly value="${roomsSearchVo.fangxingShow}">
                                    <input id="rooms_roomchoose_value" type="hidden" name="fangxin" value="${roomsSearchVo.fangxin}">
                                    <input id="rooms_roomchoose_typevalues" type="hidden" name="fangxinvalues" value="">
                                </td>
                                <td width="28" align="right">
                                    <a id="rooms_roomchoose_btn" class="SFbutton floatR marginRight-4 roomChooseButton" href="javascript:;">···</a>
                                </td>
                            </tr>
                        </table>
                    </dd>
                    <dt>特征</dt>
                    <dd>
                        <table width="100%">
                            <tr>
                                <td style="padding:0;">
                                    <input id="rooms_features" class="input gry_9" name="fangjiantezheng" type="text" readonly value="${roomsSearchVo.fangjiantezheng}">
                                    <input id="rooms_features_veiw" name="fangjiantezhengID" type="hidden" value="${roomsSearchVo.fangjiantezhengID}">
                                </td>
                                <td width="28" align="right">
                                    <a id="rooms_roomchoosefeatures_btn" class="SFbutton floatR marginRight-4 featuresChooseButton" href="javascript:;">···</a>
                                </td>
                            </tr>
                        </table>
                    </dd>
                </dl>
                <div class="clearBoth"></div>
                <ul class="roomNum" id="rooms_roomid_div">
                </ul>
                <div class="fundRoomNum">
                    <span><a href="javascript:;" class="checkRoom textDecoration" id="rooms_roomid_count">选定房间：0</a></span>
                </div>
                <div class="clearBoth">
                    <dl class="inputDiv margin-top-none clearBoth">
                        <dd>房号<input id="roomstat_roomid" class="input widthB40 margin-left-5" type="text" value="${roomId}"></dd>
                    </dl>
                </div>
                <div class="clearBoth margin-top-20"></div>  
                <div class="cabDivNoneHei clearBoth padding-top-10">                      
                    <a class="button_07 floatL" href="javascript:;" id="rooms_btn_refresh">条件查询</a>
                    <a class="button_07 floatL" href="javascript:;" id="rooms_btn_clear">清空条件</a>
                    <a class="button_07 floatL" href="javascript:;" id="roomstat_btn_unclear">置为不洁</a>
                    <a class="button_07 floatL" href="javascript:;" id="roomstat_btn_clearuncheck">置为清洁未查</a>
                    <a class="button_07 floatL" href="javascript:;" id="roomstat_btn_clearcheck">置为清洁已查</a>
                    <a class="button_07 floatL" href="javascript:;">打印</a>
                </div>
            </form>
            <div class="clearBoth"></div>
        </div>
        <div class="clearBoth"></div>
    </div>
</div>