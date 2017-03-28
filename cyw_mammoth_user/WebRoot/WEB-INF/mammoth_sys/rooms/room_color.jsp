<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<c:forEach items="${roomsStats}" var="roomsStat">
    <!--楼层 -->
    <c:if test="${roomsStat.isFloor==true}">
        <li class="statusLi floor" room-isfloor="true" floor-no="${roomsStat.floorNo}">
            <img class="floorImg" src="${pageContext.request.contextPath}/img/Floor_01.png">
            <span class="floorWord">${roomsStat.floorNo}</span>
        </li>
    </c:if>
    <c:if test="${roomsStat.isFloor==false}">
        <!--房间状态判断 -->
        <c:if test="${roomsStat.roomStatF=='V'}" >
            <li room class="statusLi idle" room-isfloor="false" <c:if test="${roomsStat.isUsefull==false}"> cannotuse </c:if> room-type="${roomsStat.roomTypeCode}" floor-no="${roomsStat.floorNo}" room-stat="${roomsStat.roomStatF}" data-duty="${roomsStat.roomStatS}" room-id="${roomsStat.roomId}">
                <div class="statusBlock">
                    <h5>${roomsStat.roomId}</h5>
                    <h6>${roomsStat.roomType}</h6>
                    <!-- 清洁状态-->
                    <div class="statusImg">
                        <c:if test="${roomsStat.roomStatS=='D'}">
                            <img src="${pageContext.request.contextPath}/img/ft-02.png">
                        </c:if>
                        <c:if test="${roomsStat.roomStatT=='P' && roomsStat.roomStatS=='C'}">
                            <img  src="${pageContext.request.contextPath}/img/ft-03.png">
                        </c:if>
                    </div>
                    <c:if test="${roomsStat.isUsefull==false}">
                        <div isusefull-roomid="${roomsStat.roomId}" class="nonSale">
                            <p class="nonSaleWord">不可售</p>
                        </div>
                    </c:if>
                </div>
                <c:if test="${roomsStat.isUsefull==true or roomsStat.isUsefull==null}">
                    <div class="pointStatus" style="display: none;" isshow="false"></div>
                    <img class="pointStatusRight" src="${pageContext.request.contextPath}/img/point-right.png" style="display: none;">
                </c:if>
                
            </li>
        </c:if>
        <!--在住 -->
        <c:if test="${roomsStat.roomStatF=='O'}">
            <c:if test="${roomsStat.roomStatS=='C' or roomsStat.roomStatS=='D'}">
                <li room class="statusLi using" room-isfloor="false" floor-no="${roomsStat.floorNo}" room-stat="${roomsStat.roomStatF}" room-id="${roomsStat.roomId}" guest-withid="${roomsStat.withID}" guest-grpcheckid="${roomsStat.grpCheckId}" guest-checkid="${roomsStat.checkId}">
                    <div class="statusBlock">
                        <h5>${roomsStat.roomId}</h5>
                        <h6>${roomsStat.roomType}</h6>
                        <!-- 清洁状态-->
                        <div class="statusImg">
                            <c:if test="${roomsStat.roomStatS=='D'}">
                                <img  src="${pageContext.request.contextPath}/img/ft-02.png">
                            </c:if>
                            <c:if test="${roomsStat.roomStatT=='P' && roomsStat.roomStatS=='C'}">
                                <img  src="${pageContext.request.contextPath}/img/ft-03.png">
                            </c:if>
                        </div>
                        <!--住客性别 -->
                        <div class="userImg">
                            <c:if test="${roomsStat.gender=='0261'}">
                                <img statusImg-checkid="${roomsStat.checkId}" src="${pageContext.request.contextPath}/img/people-01.png">
                            </c:if>
                            <c:if test="${roomsStat.gender=='0260'}">
                                <img statusImg-checkid="${roomsStat.checkId}" src="${pageContext.request.contextPath}/img/people-02.png">
                            </c:if>
                        </div>
                        <!-- 客态-->
                        <ul class="userStatus">
                            <c:forEach items="${roomsStat.guestStat}" var="roomsGuest">
                                <li><img guest-checkid="${roomsStat.checkId}" status-type='${roomsGuest}' src="${pageContext.request.contextPath}/img/${roomsGuest}"></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="pointStatus" style="display: none;" isshow="false"></div>
                    <img class="pointStatusRight" src="${pageContext.request.contextPath}/img/point-right.png" style="display: none;">
                </li>
            </c:if>
        </c:if>
        <!-- 维修-->
        <c:if test="${roomsStat.roomStatF=='O' and roomsStat.roomStatS=='O' and roomsStat.roomStatT=='O'}" >
            <li room class="statusLi standardBlack" floor-no="${roomsStat.floorNo}" room-stat="frozen" room-id="${roomsStat.roomId}">
                <div class="statusBlock">
                    <h5>${roomsStat.roomId}</h5>
                    <h6>${roomsStat.roomType}</h6>
                </div>
            </li>
        </c:if>
        <!-- 冻结-->
        <c:if test="${roomsStat.roomStatF=='O' and roomsStat.roomStatS=='O' and roomsStat.roomStatT=='I'}" >
            <li room class="statusLi standardgey" floor-no="${roomsStat.floorNo}" room-stat="frozen" room-id="${roomsStat.roomId}">
                <div class="statusBlock">
                    <h5>${roomsStat.roomId}</h5>
                    <h6>${roomsStat.roomType}</h6>
                </div>
            </li>
        </c:if>
        <!-- 网房 -->
        <c:if test="${roomsStat.roomStatF=='N'}" >
            <li room class="statusLi business" floor-no="${roomsStat.floorNo}" room-stat="${roomsStat.roomStatF}" room-id="${roomsStat.roomId}">
                <div class="statusBlock">
                    <h5>${roomsStat.roomId}</h5>
                    <h6>${roomsStat.roomType}</h6>
                    <div class="statusImg">
                    </div>
                    <!-- 客态-->
                    <ul class="userStatus">
                        <c:if test="${roomsStat.roomStatT=='R'}">
                            <li><img src="${pageContext.request.contextPath}/img/st-10.png"></li>
                        </c:if>
                        <c:if test="${roomsStat.roomStatT=='P'}">
                            <li><img src="${pageContext.request.contextPath}/img/st-02.png"></li>
                        </c:if>
                    </ul>
                </div>
                <div class="pointStatus" style="display: none;" isshow="false"></div>
                <img class="pointStatusRight" src="${pageContext.request.contextPath}/img/point-right.png" style="display: none;">
            </li>
        </c:if>
    </c:if>
</c:forEach>