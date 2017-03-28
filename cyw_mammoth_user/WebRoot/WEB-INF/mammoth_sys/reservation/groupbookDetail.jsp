<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>预订-团队预订</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.css">
</head>
<body>
<!--入住登记-->

	<div class="alertMain greyBg" style="width:970px; margin-top:100px;">
    	<h4 class="moveDivAlert" id="MoveAlertDiv">团队预订<a href="javascript:;" class="closeDiv" onclick="closeGroupBookForm()"></a></h4>
    	<form  id="dataForm" action="">
        <div class="borderSolid">
            <div class="clearBoth GuestTabIn fitBook posR">
            	<div class="GuestTabL3">
                    <table width="100%">
                        <tr>
                            <td width="70" align="right">订单号</td>
                            <td width="130"><input id="theBookList" class="input" name="bookList" data-inputmask="'mask': '9{0,20}','placeholder':''"  data-validation="required"  type="text"></td>
                            <td width="70" align="right">客人来源</td>
                            <td width="130">
                            	<select name="gstOriId" class="select widthB100">
                                      <c:forEach items="${gestOrilist }" var ="hr">
                                      	<option value="${fn:trim(hr.codeId)}">${hr.codeNamec}</option>
                                     </c:forEach>
                                </select>
                            </td>
                            <td width="90" align="right">预订方式</td>
                            <td>
                                <select name="bookType" class="select widthB95">
                                      <c:forEach var="yd" items="${ydList }">
                                      	<option value="${yd.codeId }">${yd.codeNamec }</option>
                                     </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="70" align="right">会员卡号</td>
                            <td width="130"><input class="input" name="" type="text"></td>
                            <td width="70" align="right">客人分类</td>
                            <td width="130">
                            	<select name="gstKind" class="select widthB100">
                                     <c:forEach var="costum" items="${krflList }">
                                      	<option value="${costum.codeId }">${costum.codeNamec }</option>
                                     </c:forEach>
                                </select>
                            </td>
                            <td width="90" align="right"><span class="gry_9">预定时间</span></td>
                            <td><input class="input widthB92 gry_9" readonly="readonly" name="bookTime" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">预订人</td>
                            <td><input class="input widthB92" name="" type="text"></td>
                            <td align="right">合约单位</td>
                            <td>
                            	<table width="100%">
                                            <tr>
                                                <td style="padding:0;">
                                                 <input class="input" id="compUnit" name="namec" type="text" placeholder="合约单位">
		                                        <input style="display: none;" hidden id="compId" name="compId" type="text" placeholder="合约单位ID">
		                                        <input style="display: none;" hidden id="compType" name="compType" type="text" placeholder="合约单位TYPE">
                                                </td>
                                                <td width="22" align="left">
                                                    <a class="HYbutton" href="javascript:;"></a>
                                                </td>
                                            </tr>
                                        </table>
                            </td>
                            <td align="right"><span class="gry_9">订单状态</span></td>
                            <td>
                                 <input class="input widthB92 gry_9" id="bookStat" readonly="readonly" name="bookStat" type="text" value="未确认"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">联系电话</td>
                            <td><input name="tele" data-inputmask="'mask': '9{0,20}','placeholder':''" class="input widthB92" type="text" /></td>
                            <td align="right">销售员</td>
                            <td><input id="saler" class="input" name="" type="text"></td>
                            <td align="right"><span class="gry_9">确认时间</span></td>
                            <td><input class="input widthB92" readonly="readonly" name="confirmDate" type="text"></td>
                        </tr>
                        <tr>
                        	<td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="notice" type="text"></td>
                            <td colspan="2"><a class="button_02 margin-top-none floatR margin-right-5" href="javascript:;" id="otherInformation">其他信息</a></td>                         
                        </tr>
                    </table>
                    <div class="clearBoth"></div>
                </div>
                
                <div class="GuestTabL3 floatL padding-left-none padding-right-none padding-bottom-none" style="border:none;width:758px;background:none;">
                	
                    <div class="GuestTabL3" style="width:76%;">
                        <table width="100%">
                            <tr>
                                <td width="70" align="right">团代码</td>
                                <td width="100"><input name="grpId" class="input" type="text" value=""/></td>
                                <td width="70" align="right"><span class="gry_9">抵店日期</span></td>
                                <td width="100"><input name="reachDate" class="input gry_9" type="text"  value=""/></td>
                                <td width="70" align="right">房价方案</td>
                                <td>
                                    <select class="select widthB100" id="prcSchemeId" name="prcSchemeId" >
                                         <option value=""></option>
                                         <c:forEach var="hp" items="${hroomPlanList}">
	                                      	<option value="${hp.codeId }">${hp.codeNamec}</option>
	                                     </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">团名</td>
                                <td ><input name="grpName" maxlength="20" class="input" type="text" value=""/></td>
                                <td align="right"><span class="gry_9" >离店日期</span></td>
                                <td><input class="input gry_9" name="leaveDate" type="text"></td>
                                <td align="right"><span class="red">支付方式</span></td>
                                <td>
                                	<select name="payId" class="select widthB100">
                                     <c:forEach var="paytype" items="${paytypeList }">
                                      	<option value="${paytype.codeId }">${paytype.codeNamec}</option>
                                     </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">领队名</td>
                                <td ><input class="input" name="leadNamec" type="text" value=""/></td>
                                <td align="right">预抵时间</td>
                                <td><input class="input" name="reachTime" type="text"></td>
                                <td align="right"><span class="red">团账限额</span></td>
                                <td><input class="input" type="text" value=""/></td>
                            </tr>
                            <tr>
                                <td align="right">英文名</td>
                                <td ><input class="input" name="leadNamee" type="text" value=""/></td>
                                <td align="right">预计人数</td>
                                <td><input class="input" name="" type="text"></td>
                                <td align="right">团账账号</td>
                                <td><input class="input" name="billId" data-inputmask="'mask': '9{0,20}','placeholder':''" data-validation="required" type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">电话</td>
                                <td colspan="3"><input class="input widthB99" type="text" value=""/></td>
                                <td align="right">团账余额</td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                        </table>
                    </div>
                    <ul class="payMan floatR margin-bottom-none">
                        <li><label><input name="" type="checkbox" value="">可挂AR账</label></li>
                        <li><label><input name="" type="checkbox" value="">可挂房账</label></li>
                        <li><label><input name="" type="checkbox" value="">隐藏房价</label></li>
                        <li><label><input name="" type="checkbox" value="">免费服务</label></li>
                        <li><label><input name="" type="checkbox" value="">自动转熟客</label></li>
                    </ul>
                    <ul class="payMan floatR borderNone margin-bottom-none">
                        <li class="clearBoth"><input class="buttonBefore" name="" style="margin-top:-8px;" type="checkbox" value=""><a class="button_02 margin-top-none featuresChooseButton" style="margin-top:3px;" href="javascript:;">房间特征</a></li>
                        <li class="clearBoth"><input class="buttonBefore" name="" style="margin-top:-8px;" type="checkbox" value=""><a id="split" class="button_02" style="margin-top:3px;" href="javascript:;">分账设置</a></li>
                    </ul>
                </div>
                
                
                <div class="skSec2">
                        <div class="tableDiv left" style="width:757px;">
                            <!--table title-->
                            <div id="div_bookRoom" style="width:759px;height:209px"></div>
                            <!--table enner -END- -->
                        </div>
                        <table width="100%">
                        	<tr>
                            	<td width="100">订房数：0</td>
                                <td width="100">留房数：0</td>
                                <td width="200">房价合计：9999999</td>
                                <td align="right"><a href="javascript:;">留房表</a></td>
                            </tr>
                        </table>
                        
                </div>
                <!--右侧部分-->
                <div class="GuestTabR GTabPos posA margin-top-20">
                    <a class="button_02" href="javascript:;">确定</a>
                    <a class="button_02" href="javascript:;">放弃</a>
                    <a class="button_02" href="javascript:;">复制订单</a>
                    <a class="button_02" href="javascript:;">预订金</a>
                    <a class="button_02" href="javascript:;">预发房卡</a>
                    <a class="button_02" href="javascript:;" onclick="closeGroupBookForm()">退出</a>
                   
                </div>
                
                <!--/右侧部分-->
            </div>
            <div class="clearBoth"></div>
        </div>
        </form>
    </div>

<!--/入住登记-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reservation/groupbookDetail.js"></script>
</body>
</html>
