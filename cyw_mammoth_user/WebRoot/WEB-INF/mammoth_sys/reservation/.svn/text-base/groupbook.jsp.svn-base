<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>预订-团队预订</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.css">
</head>
<body>
<%@ include file="../header.jsp" %>
<!--secondMenu-->
<div class="secondMenuDiv">
	<ul class="secondMenu">
        <li><a href="${pageContext.request.contextPath}/bookroom/booklist.do">散客预订</a></li>
        <li><a class="thisSecMenu" href="${pageContext.request.contextPath}/group/grouplist.do">团队预订</a></li>
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
            	<div class="tableDiv" id="gptable" style="height: 98%">
                	<!--table title-->
                	
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
        </div>
        <!--mainInformation END-->
          <!--mainRightMenu-->
    	<div class="rightMenu">
        	<div class="roomStatus">                
                <div class="choice padding-top-10 padding-bottom-10">
                    <form class="bookRight" id="bookform" action="" method="get">
                    <input type="hidden" id="hotelDateStr" value="${hotelDateStr}"/>
                        <dl class="inputDiv2 margin-top-none">
                            <dt>订单号</dt>
                            <dd>
                                <input class="input" class="input" id="id_book_list" name="bookList" type="text" placeholder="">
                            </dd>
                            <dt>团代码</dt>
                            <dd>
                                <input class="input" name="grpId" type="text" placeholder="">
                            </dd>
                            <dt>团名</dt>
                            <dd>
                                <input class="input" name="grpName" type="text" placeholder="">
                            </dd>
                            <dt>领队</dt>
                            <dd>
                                <input class="input" name="leaderNamec" maxlength="10" type="text" placeholder="">
                            </dd>
                            <dt>领队电话</dt>
                            <dd>
                                <input class="input" name="mobile" data-inputmask="'mask': '9{0,11}','placeholder':''" type="text" placeholder="">
                            </dd>
                            <dt>抵店日期</dt>
                            <dd>
                                <input class="input Wdate" name="reachDate" onclick="WdatePicker();" type="text" placeholder="">
                            </dd>
                            <dt>离店日期</dt>
                            <dd>
                                <input class="input Wdate" name="leaveDate"  onclick="WdatePicker();" type="text" placeholder="">
                            </dd>
                            <dt>房间号</dt>
                            <dd>
                                <input class="input" name="room_id"  data-inputmask="'mask': '9{0,20}','placeholder':''"  type="text" placeholder="">
                            </dd>
                            <dt>状态</dt>
                            <dd>
                                <select class="select" name="bookStat" id="search_bookStat">
	                               <option value=""  <c:if test="${empty searchVo.bookStat}">selected</c:if>></option>
 	                               <option value="B" <c:if test="${searchVo.bookStat=='B' }">selected</c:if>>未确认</option>
	                               <option value="C" <c:if test="${searchVo.bookStat=='C' }">selected</c:if>>取消</option>
	                               <option value="O" <c:if test="${searchVo.bookStat=='O' }">selected</c:if>>已确认</option>
	                               <option value="R" <c:if test="${searchVo.bookStat=='R' }">selected</c:if>>部分抵达</option>
	                               <option value="A" <c:if test="${searchVo.bookStat=='A' }">selected</c:if>>全部抵达</option>
	                               <option value="N" <c:if test="${searchVo.bookStat=='N' }">selected</c:if>>NO SHOW</option>
	                               <option value="P" <c:if test="${searchVo.bookStat=='P' }">selected</c:if>>过期</option>
                               </select>
                            </dd>
                        </dl>
                        <div class="clearBoth"></div>
                        <table width="90%" class="margin-left-10 margin-top-10 margin-bottom-10">
                            <tr>
                                 <td align="right"><label  class="margin-right-10"><input name="active" <c:if test="${searchVo.active==0 }">checked</c:if> value="0" type="radio"><span class="margin-left-5">有效</span></label></td>
                                <td align="left"><label class="margin-left-10"><input name="active" <c:if test="${searchVo.active==1 }">checked</c:if> value="1" type="radio"><span class="margin-left-5">无效</span></label></td>
                            </tr>
                        </table>
                        <div class="cabDivNoneHei clearBoth padding-top-10">
                            <a class="button_07 floatL" href="javascript:;" onclick="goSearch(0)">条件查询</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="clearText()">清空条件</a>
                            <input value="" id="viewId" type="hidden"/>
                            <a class="button_07 floatL" href="javascript:;" onclick="viewAlert();" >订单详情</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="goAdd(-1)">新增</a>
                        </div>
                        
                        <!--小键盘-->
                        <div class="cabDiv clearBoth">
                            <input value="" id="codeLetter" name="codeLetter" type="hidden"/>
                        	<input value="" id="symbol" name="symbol" type="hidden"/>
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
<!--copyRight -END -->
<!--弹出层阴影-->
<div class="alertDivBg"></div>
<div class="alertDivBg2"></div>
<div class="alertDivBg3"></div>
<!--弹出层阴影结束-->
<!--入住登记-->
<div id="grpBookDiv" class="alertDiv checkInDiv moveBar orderDetailsDiv">
	<div class="alertMain greyBg" style="width:970px; margin-top:100px;">
    	<h4 class="moveDivAlert" id="MoveAlertDiv">团队预订<a href="javascript:;" class="closeDiv""></a></h4>
    	<form  id="skyd" action="">
    	<input type="hidden" value="${pageContext.request.contextPath}" id="path"/>
		<input type="hidden" value="" name="isEdit" id="isEdit"/>
		<input type="hidden" value="" name="checkId" id="checkId"/>
		<input type="hidden" value="" name="isCopyOrder" id="isCopyOrder"/>
        <div class="borderSolid">
            <div class="clearBoth GuestTabIn fitBook posR">
            	<div class="GuestTabL3">
                    <table width="100%">
                        <tr>
                            <td width="70" align="right">订单号</td>
                            <td width="130"><input id="theBookList" maxlength="20" class="input" name="bookList"   data-validation="required" label="订单号" type="text"></td>
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
                            <td><input class="input widthB92 gry_9" value="<fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm"/>" readonly="readonly" name="bookTime" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">预订人</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">合约单位</td>
                            <td>
								<input class="input" id="compUnit" name="unitNamec" type="text" placeholder="合约单位" >
								<input style="display: none;" hidden id="compId" name="compId" type="text" placeholder="合约单位ID">
								<input style="display: none;" hidden id="compType" name="compType" type="text" placeholder="合约单位TYPE">
								<a class="HYbutton" href="javascript:;"></a>
                            </td>
                            <td align="right"><span class="gry_9">订单状态</span></td>
                            <td>
                                 <!-- <input class="input widthB92 gry_9" id="bookStat" readonly="readonly" name="bookStat" type="text" value="未确认"/> -->
                                 <input class="input widthB92 gry_9" id="bookStat" name="bookStatTmp" disabled="disabled" readonly="readonly" type="text"/>
                                 <input class="input widthB92 gry_9"  readonly="readonly" id="bookStatReal" name="bookStat" type="hidden"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">联系电话</td>
                            <td><input name="tele" data-inputmask="'mask': '9{0,20}','placeholder':''" class="input" type="text" /></td>
                            <td align="right" class="gry_9">销售员</td>
                            <td><input id="saler" class="input widthB92 gry_9" readonly="readonly" name="saler" type="text"></td>
                            <td align="right"><span class="gry_9">确认时间</span></td>
                            <td><input class="input widthB92 gry_9" readonly="readonly" name="confirmDate" type="text"></td>
                        </tr>
                        <tr>
                        	<td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="notice" type="text"></td>
                            <td colspan="2"><a class="button_02 margin-top-none floatR margin-right-10" href="javascript:;" id="otherInformation" onclick="otherInformationFunc()">其他信息</a></td>                         
                        </tr>
                    </table>
                    <div class="clearBoth"></div>
                </div>
                
                <div class="GuestTabL3 floatL padding-left-none padding-right-none padding-bottom-none" style="border:none;width:758px;background:none;">
                	
                    <div class="GuestTabL3" style="width:76%;">
                        <table width="100%">
                            <tr>
                                <td width="70" align="right">团代码</td>
                                <td width="100"><input name="grpId" data-validation="required" label="团代码" class="input" type="text" value=""/></td>
                                <td width="70" align="right"><span class="gry_9">抵店日期</span></td>
                                <td width="100"><input id="reachDate" name="reachDate"  readonly="readonly" class="input gry_9" type="text"  value=""/></td>
                                <td width="70" align="right">房价方案</td>
                                <td>
                                    <select class="select widthB100" id="prcSchemeId" name="prcSchemeId" >
                                         <option value=""></option>
                                         <c:forEach var="hp" items="${hroomPlanList}">
	                                      	<option value="${fn:trim(hp.hroomPlan.codeId)}">${hp.hroomPlan.codeNamec}</option>
	                                     </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">团名</td>
                                <td ><input name="grpName" maxlength="20" data-validation="required" label="团名" class="input" type="text"  value=""/></td>
                                <td align="right"><span class="gry_9" >离店日期</span></td>
                                <td><input id="leaveDate" class="input Wdate gry_9" name="leaveDate"  readonly="readonly" type="text"></td>
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
                                <td ><input class="input"  name="leadNamec" maxlength="5" data-validation="required" label="领队名" type="text" onBlur="theNamee.value=(pinyin.getFullChars(this.value));" onChange="theNamee.value=(pinyin.getFullChars(this.value));" onKeydown="theNamee.value=(pinyin.getFullChars(this.value));" value=""/></td>
                                <td align="right">预抵时间</td>
                                <td><input class="input" name="reachTime"  type="text" maxlength="5"></td>
                                <td align="right"><span class="red">团账限额</span></td>
                                <td><input class="input" id="limit" name="biilbLimit" type="text" onchange="limitConvert()" data-inputmask="'alias': '9{0,10}.9{0,2}'" value="0.00"/></td>
                            </tr>
                            <tr>
                                <td align="right">英文名</td>
                                <td ><input class="input" id="theNamee" name="leadNamee" type="text"  data-validation="required" label="英文名" value=""/></td>
                                <td align="right">预计人数</td>
                                <td><input class="input" name="gstNum" id="gstNum" data-inputmask="'mask': '9{0,5}','placeholder':''" data-validation="required" label="预计人数" type="text"></td>
                                <td align="right"><span class="gry_9">团账账号</span></td>
                                <td><input class="input gry_9" id="billId" readonly="readonly" name="billId" data-inputmask="'mask': '9{0,10}','placeholder':''"   type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">电话</td>
                                <td colspan="3"><input class="input widthB98" name="mobile" data-inputmask="'mask': '9{0,11}','placeholder':''" type="text" value=""/></td>
                                <td align="right"><span class="gry_9">团账余额</span></td>
                                <td><input class="input gry_9" name="billbBlance" readonly="readonly" type="text"></td>
                            </tr>
                        </table>
                    </div>
                    <ul class="payMan floatR margin-bottom-none">
                        <li><label><input name="cityLedger" type="checkbox" value=true>可挂AR账</label></li>
                        <li><label><input name="housePay" type="checkbox" checked="checked" value="true">可挂房账</label></li>
                        <li><label><input name="hideprice" type="checkbox" value="true">隐藏房价</label></li>
                        <li><label><input name="freeSvc" type="checkbox" checked="checked" value="true">免费服务</label></li>
                        <li><label><input name="ifFgst" type="checkbox" checked="checked" value="true">自动转熟客</label></li>
                    </ul>
                    <ul class="payMan floatR borderNone margin-bottom-none">
                        <li class="clearBoth">
                           <input id="roomCharacter" name="roomCharacter" value="0" data-validation="required" type="text" hidden style="display: none;"  />
                        	<input id="fjtzChk_dd" class="buttonBefore" readonly="readonly" onclick="return false;" name="" type="checkbox"  value="0" style="margin-top:-8px;" type="checkbox" value="">
                        	<a id="featuresChooseButton" class="button_02 margin-top-none featuresChooseButton" style="margin-top:3px;" jack="showKeFangForm(1)" onclick="showKeFangForm(1)" href="javascript:;">房间特征</a>
                        </li>
                        <li class="clearBoth">
                          <input class="buttonBefore" id="accountSetId" name="" style="margin-top:-8px;" type="checkbox" value="" readonly="readonly" onclick="return false;">
                          <a id="split" class="button_02" style="margin-top:3px;" jack="consumeShow()" onclick="consumeShow()" <mammoth:AuthJudge funcId="c_655139" />>分账设置</a>
                        </li>
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
                            	<td width="100">订房数：<span id="totalBookNum">0</san></td>
                                <td width="100">留房数：<span id="totalSaveNum">0</span></td>
                                <td width="200">房价合计：<span id="totalPrice">9999999</span></td>
                                <td align="right"><a href="javascript:;" onclick="showCurrentRoomInfo()">留房表</a></td>
                            </tr>
                        </table>
                        
                </div>
                <!--右侧部分-->
                <div class="GuestTabR GTabPos posA margin-top-20">
                    <a class="button_02 <mammoth:AuthJudge funcId="c_113506">group1</mammoth:AuthJudge>" href="javascript:;" jack="certain()"  <mammoth:AuthJudge funcId="c_113506">onclick="certain()"</mammoth:AuthJudge>>确定</a>
                    <a class="button_02 group1" href="javascript:;" jack="btn_giveup()"  onclick="btn_giveup()">放弃</a>
                    <a class="button_02 <mammoth:AuthJudge funcId="c_406747" >group2</mammoth:AuthJudge>" href="javascript:;" jack="copyOrder()"  <mammoth:AuthJudge funcId="c_406747">onclick="copyOrder()"</mammoth:AuthJudge>>复制订单</a>
                    <a class="button_02 <mammoth:AuthJudge funcId="c_935916">group2</mammoth:AuthJudge>" id="btn_confirmOrder" jack="confirmOrder()"  <mammoth:AuthJudge funcId="c_935916">onclick="confirmOrder()"</mammoth:AuthJudge>>确认订单</a>
                    <a class="button_02 <mammoth:AuthJudge funcId="c_849337">group2</mammoth:AuthJudge>" id="btn_cancelOrder" jack="btn_cancelOrder()"  <mammoth:AuthJudge funcId="c_849337">onclick="btn_cancelOrder()"</mammoth:AuthJudge>>取消订单</a>
                    <a class="button_02 <mammoth:AuthJudge funcId="c_206003" >group2</mammoth:AuthJudge>" href="javascript:;">预订金</a>
                    <a class="button_02 group2" href="javascript:;">预发房卡</a>
                    <a class="button_02 group2" href="javascript:;" jack="closeGroupBookForm()" onclick="closeGroupBookForm()" >退出</a>
                   
                </div>
                
                <!--/右侧部分-->
            </div>
            <div class="clearBoth"></div>
        </div>
        </form>
    </div>
</div>
<!--/入住登记-->

<!--留房弹出框-->
<div class="alertDiv checkInDiv moveBar2 alertDiv2 leaveTheRoom">
	<div class="alertMain greyBg" style="width:960px; margin-top: 50px;">
    	<h4 class="moveDivAlert2">留房操作<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--留房弹出框左侧-->
            <div class="leaveTheRoomL" style="width:77%;">
            
            	<div class="statusUl height400">
            		<!-- 加楼层图标 -->
            		<!-- code here -->
            		<div id="remainRoomUL">
            		
            		</div>
                    <div class="clearBoth"></div>
                </div>
                <!--留房表格Table-->
                <div class="tableDiv">
                    <!--table title-->
                    
                    <!--table title end -->
                    <!--table enner-->
                    <div class="lfTabMain">
                     	<div id="remainRoomFlexGrid"></div>
                    </div>
                    <!--table enner -END- -->
                </div>
                <ul class="roomNum" id="remainRoomNum">
                    <!-- <li>9523</li>
                    <li>9524</li>
                    <li>9525</li>
                    <li>9526</li>
                    <li>9527</li> -->
                </ul>
                <a id="brCount" class="floatL margin-left-15" href="javascript:void(0);">留房:0</a>
                
            <!--/留房表格Table-->   
            </div>
            <!--留房弹出框左侧END-->
            <!--留房弹出框右侧-->
            <div class="leaveTheRoomR" style="width:22%;">
            	<div class="roomStatus margin-left-10"">                
                <div class="choice padding-bottom-10">
                    <form class="" action="" method="get" id="remainConditionForm">
                    	<input type="hidden" name="remainRoomTypeId">
                    	<input type="hidden" name="remainCheckId">
                    	<input type="hidden" name="bookId">
                        <dl class="inputDiv4">
                            <dt>楼名</dt>
                            <dd>
                                <select class="select widthB90" id="remainBuildingName" name="remainBuildingName" onchange="getFloors(this.value)">
                                     <option value="0">全部</option>
                                </select>
                            </dd>
                            <dt>楼层</dt>
                            <dd>
                                <select class="select widthB90" id="remainFloorNo" name="remainFloorNo">
                                     <option value="">全部</option>
                                </select>
                            </dd>
                            <dt>房型</dt>
                            <dd>
                            <input class="input widthB85" name="remainRoomType" type="text" placeholder="" readonly="readonly"></dd>
                            <dt>特征</dt>
                            <dd>
                            	<table width="90%">
                                		<tr>
                                            <td style="padding:0;">
                                                <input id="TMroomCharacter_show" class="input" name="" type="text" placeholder="">
                                            </td>
                                            <td width="28" align="right">
                                                <input id="TMroomCharacter" name="roomCharacter" value="0" data-validation="required" type="text" hidden style="display: none;"  />
                                                <a class="SFbutton floatR marginRight-4" href="javascript:;" onclick="showKeFangForm(2)">···</a>
                                            </td>
                                   		</tr>
                                	</table>
                            </dd>
                            <dt>抵店</dt>
                            <dd><input class="input widthB85" name="remainReachDate" type="text" placeholder="" readonly="readonly"></dd>
                            <dt>离店</dt>
                            <dd><input class="input widthB85" name="remainLeaveDate" type="text" value="" readonly="readonly"/></dd>
                            <div class="clearBoth"></div>
                        </dl>
                        <div class="cabDivNoneHei padding-top-10 padding-left-50">
	                        <a class="button_06  margin-top-10" href="javascript:void(0);" onclick="remainRoomQuery(false)">条件查询</a>
	                        <a class="button_06  margin-top-10" href="javascript:void(0);" onclick="clearRemainConditionForm()">清空条件</a>
	                        <a class="button_06  margin-top-10" href="javascript:void(0);" id="remainRoomOK">确定</a>
	                        <a class="button_06  margin-top-10" href="javascript:void(0);" onclick="remainRoomGiveUp()">放弃</a>
	                        <a class="button_06  margin-top-10" href="javascript:void(0);" onclick="remainRoomQuery(true)">自助排房</a>
	                        <a class="button_06  margin-top-10" href="javascript:void(0);" id="remianQuit">退出</a>
                        </div>
                    </form>
                    <div class="clearBoth"></div>
                </div>
            </div>
        </div>
        <div class="clearBoth"></div>
            </div>
            <!--留房弹出框右侧END-->
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/留房弹出框-->
<!--其他信息弹出框-->
<div class="alertDiv moveBar2 alertDiv3 otherInformationDiv">
	<div class="alertMain greyBg" style="width:630px; margin-top:180px;">
    	<h4 class="moveDivAlert2">其他信息<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--分账-->
        	<form action="" id="otherInfoForm">
            <table width="95%" class="margin-top-10 gry_9">
                <tr>
                    <td width="25%" align="right">订单号</td>
                    <td width="25%"><input class="input gry_9" name="bookList" type="text" placeholder="Admin" disabled></td>
                    <td width="25%" align="right">订单状态</td>
                    <td><input class="input gry_9" name="bookStat" type="text" placeholder="未确认" disabled></td>
                </tr>
            </table>
            <div class="clearBoth link2"></div>
        	<table width="95%" class="gry_9 margin-top-10">
                <tr>
                    <td width="25%" align="right">预订人</td>
                    <td width="25%"><input class="input gry_9" name="bookerName" type="text" placeholder="" disabled></td>
                    <td width="25%" align="right">入住登记人</td>
                    <td><input class="input gry_9" name="chkOperid" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">联系电话</td>
                    <td><input class="input gry_9" name="tele" type="text" disabled></td>
                    <td align="right">入住登记时间</td>
                    <td><input class="input gry_9" name="chkTime" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">会员卡号</td>
                    <td><input class="input gry_9" name="" type="text" disabled></td>
                    <td align="right">最后修改人</td>
                    <td><input class="input gry_9" name="lastOper" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">预订方式</td>
                    <td>
                    	<input class="input gry_9" name="bookType" type="text" disabled>
                    </td>
                    <td align="right">修改时间</td>
                    <td><input class="input gry_9" name="lastTime" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">预订登记人</td>
                    <td><input class="input gry_9" name="bookOperid" type="text" disabled></td>
                    <td align="right">恢复入住人</td>
                    <td><input class="input gry_9" name="rechkOperid" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">预订登记时间</td>
                    <td><input class="input gry_9" name="bookTime" type="text" disabled></td>
                    <td align="right">恢复时间</td>
                    <td><input class="input gry_9" name="rechkTime" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">订单确认人</td>
                    <td><input class="input gry_9" name="confirmOpeid" type="text" disabled></td>
                    <td align="right">退房人</td>
                    <td><input class="input gry_9" name="outOper" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">订单确认时间</td>
                    <td><input class="input gry_9" name="confirmDate" type="text" disabled></td>
                    <td align="right">退房时间</td>
                    <td><input class="input gry_9" name="outTime" type="text" disabled></td>
                </tr>
            </table>
            <!--分账-->
            </form>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="closeOtherInfo()">退出</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/其他信息弹出框-->
<!--房间特征ALERT-->
<div class="alertDiv alertDiv2 featuresButton moveBar2">
	<div class="alertMain" style="width:500px; margin-top:250px;">
    	<h4 class="moveDivAlert2">房间特征筛选<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid fangJainFeatures">
        	<div class="roomButtonFblock">
            	<ul class="FTsx">
                    <c:forEach items="${hroomCharacters}" var="list" varStatus="status">
                                             
                         <li><label><input class="checkbox margin-right-5 sonChk" name="roomCharacter" label="${list.codeNamec}" type="checkbox" value="${list.placeholderId}">${list.codeNamec}</label></li>
                    </c:forEach>
                </ul>
             </div>
             <table id="featuresDivOper" width="460">
                <tr>
                    <td><label><input id="chkall" class="checkbox"  name="chkall" type="checkbox" ><span class="margin-left-10">全选</span></label></td>
                </tr>
                <tr>
                    <td align="right"><a href="javascript:;" class="button_03 floatR margin-left-10 group1" jack="closeKeFangForm()" onclick="closeKeFangForm()">取消(N)</a> <a href="javascript:;" class="button_03 floatR group1" jack="saveCharaters()" onclick="saveCharaters()">确定(Y)</a></td>
                </tr>
            </table>
        </div>
       
    </div>
</div>
<!--/特征-->
<!--分账弹出框-->
<div class="alertDiv moveBar2 alertDiv3 splitInfoDiv">
	<div class="alertMain greyBg" style="width:555px; margin-top:180px;">
    	<h4 class="moveDivAlert2">分账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--分账-->
        	<table width="100%">
            	<tr>
                	<td width="42%">
                    	<h5 class="margin-left-5 fontWeight">A账页项目</h5>
                    	<ul class="splitDivLeft margin-top-5"  id="consumeA_ID"></ul>
                    </td>
                    <td align="center">
                    	<a class="forSN margin-top-20" href="javascript:;" onclick="consumeMove('right','movePart')"><img src="/img/right_01.png"></a>
                    	<a class="forSN margin-top-10" href="javascript:;" onclick="consumeMove('right','moveAll')"><img src="/img/right_02.png"></a>
                    	<a class="forSN margin-top-30" href="javascript:;" onclick="consumeMove('left','movePart')"><img src="/img/left_01.png"></a>
                    	<a class="forSN margin-top-10" href="javascript:;" onclick="consumeMove('left','moveAll')"><img src="/img/left_02.png"></a>
                    </td>
                    <td width="42%">
                    	<h5 class="margin-left-5 fontWeight">B账页项目</h5>
                    	<ul class="splitDivRight margin-top-5" id="consumeB_ID"></ul>
                    </td>
                </tr>
            </table>
            <div class="clearBoth"></div>
            <table class="margin-left-30 margin-top-20">
               <tr>
						<td width="80" height="30" align="right">起始日期</td>
						<td width="110"><input class="input" id="startDate_2" name="beginDate" type="text" onfocus="var date = $('#endDate_2').val();WdatePicker({minDate:date,maxDate:'#F{$dp.$D(\'endDate_2\')}'})">
						</td>
						<td width="80" align="right">终止日期</td>
						<td width="110"><input class="input" id="endDate_2" name="endDate" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate_2\')}'})">
						</td>
					</tr>
					<tr>
						<td width="110" colspan="4"><label><input
								class="checkbox margin-right-5 margin-left-15" name="" id="if_bate"
								type="checkbox" checked="checked" value="1">续住时自动修改分账日期</label>
						</td>
					</tr>
            </table>
            <!--分账-->
               <div id="splitInfoDivOper" class="alertRight clearBoth margin-top-30">
					<a class="buttonLikeA floatR margin-right-10 " href="javascript:;" jack="consumeQuit()" onclick="consumeQuit()">退出</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" jack="loadConsume()" onclick="loadConsume()">放弃</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" jack="consumeSubmit()" onclick="consumeSubmit()">确定</a>
				</div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/分账弹出框-->
<!--选定房间弹出框-->
<%@ include file="selectRoomsAlert.jsp"%>
<!--/选定房间弹出框-->
<script src="${ctx}/scripts/jquery-migrate-1.2.1.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery.inputmask.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>
<script src="${ctx}/js/lib/autocomplete/jquery.autocomplete.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reservation/Utilities.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reservation/groupbook.js"></script>
</body>
</html>
