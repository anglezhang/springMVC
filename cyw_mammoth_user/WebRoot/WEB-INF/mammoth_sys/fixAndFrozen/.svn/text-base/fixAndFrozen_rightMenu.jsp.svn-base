<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!--房态右侧菜单 -->
<div class="rightMenu rightMenuFT2 margin-top-none">
    <div class="roomStatus">
        <div class="choice padding-top-10 padding-bottom-30">
            <form class="currentState" action="${pageContext.request.contextPath}/roomFixFrozen/fixFrozen.do" method="post" id="rooms_searchvo">
                <div class="roomStatusRadio">
                    <label><input name="roomStatu" value="OOO" <c:if test="${roomsSearchVo.roomStatu=='OOO'}">checked</c:if> type="radio"><span class="margin-left-5">维修</span></label>
                    <label><input name="roomStatu" value="OOI" <c:if test="${roomsSearchVo.roomStatu=='OOI'}">checked</c:if> type="radio"><span class="margin-left-5">冻结</span></label>
                    <label><input name="roomStatu" value="%" <c:if test="${roomsSearchVo.roomStatu=='%' or roomsSearchVo.roomStatu==null }">checked</c:if> type="radio"><span class="margin-left-5">全部</span></label>
                </div>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR padding-bottom-15">
                    <div class="jsWord">筛选</div>
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
                </div>
                <dl class="inputDiv margin-top-none margin-left-25">
                    <dt>开始日期</dt>
                    <dd>
                        <!--日期-->
                        <input class="input"  name="startdate" id="roomssearchvo_startdate" type="text" value="${fn:substring(roomsSearchVo.startdate,0,10)}">
                        <input type="hidden" id="roomssearchvo_startdate_minDate" value="${startDate}">
                        <input type="hidden" id="roomssearchvo_startdate_old_minDate" value="${fn:substring(roomsSearchVo.startdate,0,10)}">
                        <!--END日期-->
                    </dd>
                    <dt>结束日期</dt>
                    <dd>
                        <input class="input" name="enddate" id="roomssearchvo_enddate" type="text"  value="${fn:substring(roomsSearchVo.enddate,0,10)}">
                         <input type="hidden" id="roomssearchvo_enddate_olddate" value="${fn:substring(roomsSearchVo.enddate,0,10)}">
                    </dd>
                </dl>
                <div class="clearBoth"></div>
                <ul class="roomNum" id="rooms_roomid_div" onselectstart="return false">
                </ul>
                <div class="fundRoomNum">
                    <span><a href="javascript:;" class="checkRoom textDecoration" id="rooms_roomid_count">选定房间：0</a></span>
                </div>
                <div class="clearBoth margin-top-20"></div>  
                <div class="cabDivNoneHei clearBoth padding-top-10">                      
                    <a class="button_07 floatL" href="javascript:;" id="rooms_btn_refresh">条件查询</a>
                    <a class="button_07 floatL" href="javascript:;" id="rooms_btn_clear">清空条件</a>
                    <a class="button_07 floatL" href="javascript:;" id="fixfrozen_btn_setfix">设为维修</a>
                    <a class="button_07 floatL" href="javascript:;" id="fixfrozen_btn_setfrozen">设为冻结</a>
                    <a class="button_07 floatL" href="javascript:;" id="fixfrozen_btn_fixfrozeninf">维修/冻结详情</a>
                    <a class="button_07 floatL" href="javascript:;">打印</a>
                </div>
            </form>
            <div class="clearBoth"></div>
        </div>
        <div class="clearBoth"></div>
    </div>
</div>