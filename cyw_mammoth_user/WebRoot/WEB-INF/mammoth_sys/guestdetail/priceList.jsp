<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>放假列表</title>
</head>
	<body>
        <script type="text/html" id="guestinfo_pricelist_option">
            <td width="10%" style="color:gray;background-color:#EAEAEA;">1</td>
            <td width="35%" style="color:gray;">{{reachDate}}</td>
            <td width="14%" style="color:gray;">{{week}}</td>
            <td width="20%" style="color:gray;">{{standPrice}}</td>
            <td>{{doPrice}}</td>
        </script>
		<div class="floatL widthB20">
        	<ul id="pricesList_roomList" class="guestUl widthB100" style="height:242px;overflow: auto;">
        		<li class="fontWeight"> 房间列表</li>
                <c:forEach items="${roomPriceList}" var="roomprice" varStatus="status">
                    <li room-id="${roomprice.roomId}" > 
                        <a room-data="{'index':${status.index + 1},'reachDate':'${roomprice.reachDate}','week':'${roomprice.week}','standPrice':'${roomprice.standPrice}','doPrice':'${roomprice.doPrice}','roomTypeName':'${roomprice.roomTypeName}','roomId':'${roomprice.roomId}'}" <c:if test="${roomprice.roomId==roomId}">style="background: rgb(228, 241, 208);"</c:if> href="javascript:">${roomprice.roomId}</a>
                    </li> 
                </c:forEach>
        	</ul>
        </div>
        <div class="floatL widthB77">
    		<h5 class="margin-left-10" id="roomId_roomType_h5">${roomId}  | ${typeName}</h5>
            <!--table-->
                <div class="tableDiv floatL margin-left-10 margin-top-5" style="width:100%;">
                    <!--table title-->
                    <table class="tableMain">
                        <thead> 
                            <tr>
                                <td width="10%">序号</td>
                                <td width="35%">日期</td>
                                <td width="14%">星期</td>
                                <td width="20%">房价方案</td>
                                <td>执行价</td>
                            </tr>
                        </thead>
                    </table>
                    <!--table title end -->
                    <!--table enner-->
                    <div class="tableHeiScll height186" style="overflow: auto;">
                        <table id="roomPriceTable" class="tableMain">
                            <tbody>
                                <tr id="guestinfo_pricelist_table">  
                                    <c:forEach items="${roomPriceList}" var="roomprice" varStatus="status">
                                    <c:if test="${roomprice.check_id==checkId}">
                                        <td width="10%" style="color:gray;background-color:#EAEAEA;">1</td>
                                        <td width="35%" style="color:gray;">${roomprice.reachDate}</td>
                                        <td width="14%" style="color:gray;">${roomprice.week}</td>
                                        <td width="20%" style="color:gray;">${roomprice.standPrice}</td>
                                        <td>${roomprice.doPrice}</td>
                                    </c:if>
                                    </c:forEach>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
        	<h5 class="margin-left-5 textAlignRight margin-right-10 margin-top-5">合计：<span id="doPriceTotal" class="fontWeight">${roomPrice}</span>元</h5>
            <div class="alertRight clearBoth margin-top-30">
                <a class="buttonLikeA floatR" id="guestinfo_pricelist_quit" href="javascript:">退出</a>
               <!-- <a class="buttonLikeA floatR margin-right-10" href="javascript:;">放弃</a> 
               <a class="buttonLikeA floatR margin-right-10"  href="javascript:;">确定</a>-->
            </div>
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
        	seajs.use("${pageContext.request.contextPath}/js/guestdetail/priceList.js?number=" + Math.random());
        </script>
	</body>
</html>