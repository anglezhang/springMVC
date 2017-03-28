<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>

<body>
<%@ include file="../header.jsp" %>
<div class="center">
	<!--secondMenu-->
	<%@ include file="secondMenu.jsp" %>
    <!--secondMenu -end- -->
    <!--mainWeb-->
    <div class="main" id="webMain">
    	<!--mainInformation-->
    	<div class="mainInfo">
        	<h4 class="fontWeight green margin-top-10">建筑定义</h4>
            <div class="mainMation margin-top-5">
            	<!--table-->
            	<div class="tableDiv">
                	<!--table title-->
                	<table class="tableMain">
                    	<thead> 
                            <tr>
                                <td width="15%">ID</td>
                                <td width="14%">code号</td>
                                <td width="15%">英文名</td>
                                <td width="15%">中文名</td>
                            </tr>
                        </thead>
                    </table>
                    <!--table title end -->
                    <!--table enner-->
                    <div class="tableHeiScll">
                       <form action="${pageContext.request.contextPath}/building/list.do" method="post" id="searchId">
                        <table class="tableMain">
                            <tbody>
                                <c:forEach items="${list }" var="build" varStatus="s">
                                <tr>
                                    <td width="15%">${build.ID }</td>
                                    <td width="15%">${build.code_id }</td>
                                    <td width="15%">${build.code_namee }</td>
                                    <td width="15%">${build.code_namec }</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
            </div>
            <!-- choice -->
            
            <div class="choice margin-top-5">
            	<div class="searchBorder">
                	<h6>检索</h6>
                    <dl>
                    	<dt><input name="status" type="radio" value="0" <c:if test="${status==0 || empty status}">checked</c:if> onclick="getSearch()"></dt>
                        <dd>有效</dd>
                        <dt><input name="status" type="radio" value="1" <c:if test="${status==1 }">checked</c:if> onclick="getSearch()"></dt>
                        <dd>无效</dd>
                    </dl>
                    <div class="clearBoth"></div>
                </div>
              <div class="searchBorder margin-top-20">
                	<h6>排序</h6>
                    <dl>
                    	<dt><input name="sortType" type="radio" <c:if test="${sortType==1 || empty sortType}">checked</c:if> value="1" onclick="getSearch()"></dt>
                        <dd>代码</dd>
                        <dt><input name="sortType" type="radio"  <c:if test="${sortType==2}">checked</c:if> value="2" onclick="getSearch()"></dt>
                        <dd>英文名</dd>
                        <dt><input name="sortType" type="radio" <c:if test="${sortType==3}">checked</c:if> value="3" onclick="getSearch()"></dt>
                        <dd>中文名</dd>
                        <dt><input name="sortType" type="radio" <c:if test="${sortType==4}">checked</c:if> value="4" onclick="getSearch()"></dt>
                        <dd>时间</dd>
                    </dl>
                    <div class="clearBoth"></div>
                </div>
                <input class="button" type="button" value="新增(A)" onclick="goAdd()" id="add">
                <input class="button" type="button" value="编辑(B)" onclick="goUpdate()" id="update">
                <input class="button" type="button" value="删除(D)" onclick="goDel()" id="del">
                <input class="button" type="button" value="打印(P)" onclick="goPrint()" id="print">
            </div>
             </form>
            <!-- choice END -->
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<%@ include file="room/right.jsp" %>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
    </div>
    <!--mainWeb end-->
</div>
<!--center -END -->
<!--copyRight xingli 2015-09-08-->
<%@ include file="../footer.jsp" %>
<!--copyRight -END -->
<!--弹出层阴影-->
<div class="alertDivBg" style="display:none;">

</div>
<!--弹出层阴影结束-->
</body>
</html>
