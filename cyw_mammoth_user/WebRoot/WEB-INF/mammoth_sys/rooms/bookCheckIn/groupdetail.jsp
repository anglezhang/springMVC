<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript" src="${ctx}/js/rooms/bookCheckIn/bookRoomGroup.js"></script>
<!--住客资料-->
<div class="alertDiv checkInDiv moveBar TeamMecheckDetailsDiv">
	<div class="alertMain greyBg rzLogin" style="margin-top:75px;">
    	<h4 class="moveDivAlert" id="MoveAlertDiv">团单详情（在住 / 离店）<a href="javascript:;" class="closeDiv hideDivOne" onclick="quitWindow();"></a></h4>
        <div class="borderSolid">
           <form id="groupForm">
           <input class="input" name="checkId" type="hidden" >
        	<table class="checkInTitle" width="100%">
            	<tr>
                	<td width="8%" align="right">团代码</td>
                    <td width="12%"><input class="input" name="grpId" type="text" disabled="disabled"></td>
                    <td width="8%" align="right">团名</td>
                    <td width="12%"><input class="input" name="grpName" type="text" disabled="disabled"></td>
                    <td width="8%" align="right">房价方案</td>
                    <td width="12%">
                    	<select class="select">
                        </select>
                    </td>
                    <td width="8%" align="right">销售员</td>
                    <td><input class="input" name="saleManName" type="text" disabled="disabled"></td>
                    <td width="140">&nbsp;</td>
                </tr>
                <tr>
                	<td align="right"><p class="fontWeight font-13">入住类型:</p></td>
                    <td colspan="7">
                    	<span><label><input class="radio" name="inType" type="radio" value="1">普通</label></span>
                        <span><label><input class="radio" name="inType" type="radio" value="2">合约</label></span>
                        <span><label><input class="radio" name="inType" type="radio" value="3">钟点</label></span>
                        <span><label><input class="radio" name="inType" type="radio" value="4">免费</label></span>
                        <span><label><input class="radio" name="inType" type="radio" value="5">自用</label></span>
                    </td>
                    <td width="140">&nbsp;</td>
                </tr>
            </table>
            <ul class="GuestTab clearBoth">
            	<li class="point userTab1">团队详细</li>
                <li class="userTab2">团队账目</li>
            </ul>
            <div class="clearBoth GuestTabIn userDetails" style="display:block;">
            	<div class="GuestTabL">
                	<!--team编号-->
	                    <div class="roomNumber" style="width:99%;">
	                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
	                            <table width="99%">
	                                <tr>
	                                    <td width="70" align="right">领队房号</td>
	                                    <td width="170"><input class="input" name="leadRoom" type="text"></td>
	                                    <td width="80" align="right"><span class="red">支付方式</span></td>
	                                    <td width="170">
	                                    	<select class="select widthB99" name="payId">
	                                   		</select>
	                                    </td>
	                                    <td width="80" align="right">合约单位</td>
	                                    <td>
	                                    	<table width="100%">
	                                            <tr>
	                                                <td style="padding:0;">
	                                                      <input class="input widthB98"  id="theCompany" name="namec" type="text" disabled="disabled">
<!-- 				                            			  <input type="hidden" id="company_id" name ="compId" value="" label="合约单位"/> -->
<!-- 			                                       		  <input type="hidden" id="ta_type" name ="compType" value="" label="合约单位"/> -->
	                                                </td>
	                                                <td width="22" align="left" style="padding:0;">
	                                                    <a class="HYbutton" href="javascript:;"></a>
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="right">领队名</td>
	                                    <td><input class="input" name="leadNamec" type="text"></td>
	                                    <td align="right"><span class="red">团账限额</span></td>
	                                    <td><input class="input gry_9" name="limit" type="text"></td>
	                                    <td align="right">抵店时间</td>
	                                    <td><input class="input gry_9" name="reachDate" type="text" id="reachDate" disabled="disabled"></td>
	                                </tr>
	                                <tr>
	                                    <td align="right">英文名</td>
	                                    <td><input class="input" name="leadNamee" type="text"></td>
	                                    <td align="right">团账账号</td>
	                                    <td><input class="input" name="billId" type="text"></td>
	                                    <td align="right">离店时间</td>
	                                    <td><input class="input" name="leaveDate" type="text" disabled="disabled"></td>
	                                </tr>
	                                <tr>
	                                    <td align="right">联系电话</td>
	                                    <td><input class="input" name="tele" type="text"></td>
	                                    <td align="right">团账余额</td>
	                                    <td><input class="input" name="remainB" type="text" disabled="disabled"></td>
	                                    <td align="right">订单状态</td>
	                                    <td>
	                                    <input class="input" name="bookStat" type="text" disabled="disabled">
<!-- 	                                    	<select class="select widthB99"> -->
<!-- 	                                             <option>--</option> -->
<!-- 	                                   		</select> -->
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="right">团队备注</td>
	                                    <td rowspan="2" colspan="3">
	                                    	<textarea class="textarea height60" name="notice2" cols="" rows=""></textarea>
	                                    </td>
	                                    <td rowspan="2" colspan="2">
	                                    	<table width="100%">
	                                        	<tr>
	                                            	<td width="75" align="right"><span class="gry_9">房间总数</span></td>
	                                                <td width="50"><input class="input gry_9" name="" type="text" disabled="disabled" id="
	                                                
	                                                
	                                                "></td>
	                                                <td width="40" align="right"><span class="gry_9">人数</span></td>
	                                                <td><input class="input gry_9" name="gstNum" type="text" disabled="disabled"></td>
	                                            </tr>
	                                            <tr>
	                                            	<td align="right"><span class="gry_9">房租合计</span></td>
	                                                <td colspan="3">
	                                                	<input class="input widthB50 textAlignRight gry_9" name="" type="text" disabled="disabled">
	                                                	<a id="pricesList" class="textDecoration floatR padding-top-5 curPoint">房价列表</a>
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="right">&nbsp;</td>
	                                </tr>
	                            </table>
	                        </div>
	                    </div>
                    <!--/team编号-->
                    <div class="clearBoth"></div>
                    
                    
                    <div class="roomNumber margin-top-10">
                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock positionR">
                        	<div class="jsWord" style="margin-top:-5px;">同时修改团员资料</div>
                            <table width="99%" class="margin-top-10">
                               <tr>
                                    <td width="70" align="right">团员来源</td>
                                    <td width="120">
                                    	<select class="select" name="gstOriId" id="gstOriId">
                                   		</select>
                                    </td>
                                    <td width="55" align="right">国籍</td>
                                    <td width="120">
                                    	<select class="select" name="country" id="country">
                                   		</select>
                                    </td>
                                    <td width="75" align="right">当前房租</td>
                                    <td><input class="input" name="roomPrice" type="text" id="roomPrice"></td>
                                </tr>
                                <tr>
                                    <td align="right">团员分类</td>
                                    <td>
                                    	<select class="select" name="gstKind" id="gstKind">
                                   		</select>
                                    </td>
                                    <td align="right">省市</td>
                                    <td>
                                    	<select class="select" name="native_" id="native_">
                                   		</select>
                                    </td>
                                    <td align="right" colspan="2">
                                        <a id="split" class="button_02 floatR" href="javascript:;">分账设置</a>
                                        <span class="margin-top-15 margin-right-10 floatR">分账</span>
                                        <input class="buttonBefore floatR margin-top-20 margin-right-5" name="" type="checkbox" value="">
                                    </td>
                                </tr>
                               
                                <tr>
                                    <td align="right">团员备注</td>
                                    <td colspan="5"><input class="input widthB99" name="notice" type="text"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <ul class="payMan margin-top-10 padding-bottom-5 padding-top-5">
                    	<li><label><input name="" type="checkbox" value="" id="city_ledger">可挂AR账</label></li>
                        <li><label><input name="" type="checkbox" value="" id="house_pay">可挂房账</label></li>
                        <li><label><input name="" type="checkbox" value="" id="free_svc">免服务费</label></li>
                        <li><label><input name="" type="checkbox" value="" id="hideprice">隐藏房价</label></li>
                        <li><label><input name="" type="checkbox" value="" id="if_fgst">自动转熟客</label></li>
                        
                    </ul>
                    <!--/房间编号-->
                    
                    <div class="mainInfo2 widthB100 margin-left-none height186">
                    <!--table-->
                            <div class="tableDiv" id="grpGuestGrid">
                            </div>
                            <!--table -END- -->
                    </div>
                    
                    <div class="clearBoth"></div>
                    <!--房间编号-->
                </div>
                <!--右侧部分-->
                <div class="GuestTabR">
                    <a class="button_03" href="javascript:;" onclick="groupFormSubmit()" id="confirm">确定</a>
                    <a class="button_03" href="javascript:;" id="giveup" onclick="cancleEdit()">放弃</a>
                    <a class="button_03" href="javascript:;" id="scanCard" onclick="startScanCard()">扫描身份证</a>
                    <a class="button_03" href="javascript:;" id="sendRoomCard">发房卡</a>
                    <a class="button_03" href="javascript:;" id="otherInformation">其他信息</a>
                    <a class="button_03" href="javascript:;" id="groupDetailQuit" onclick="groupDetailQuit()">退出</a>
                    <ul class="payMan2 margin-top-10 padding-bottom-5 padding-top-5 positionR"  id="guestChecks">
                    	<div class="jsWord" style="margin-top:-5px; margin-left:5px;">同时修改设置</div>
                    	<li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" checked="checked" id="isGstOri" >团员来源</label></li>
                        <li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" checked="checked" id="isGstKind">团员分类</label></li>
                        <li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" checked="checked" id="isCountry">团员国籍</label></li>
                        <li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" checked="checked" id="isNative">团员省市</label></li>
                        <li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" checked="checked" id="isPrice">团员房租</label></li>
                        <li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" checked="checked" id="isSplit">团员分账</label></li>
                        <li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" checked="checked" id="isNotice">团员备注</label></li>
                        <li class="margin-top-10"><label><input class="margin-right-5" type="checkbox" value="" id="isOpen">开关变量</label></li>
                        
                    </ul>
                </div>
                <!--/右侧部分-->
            </div>
             </form>
            <!--userCatalog-->
            
            <div class="clearBoth GuestTabIn userCatalog" style="display:none;">
            	<div class="GuestTabL2">
                	
                    <!--房间编号-->
                    <div class="roomNumber margin-top-10" style="width:99%;">
                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
                            <table width="99%">
                                <tr>
                                    <td width="11%" align="right">房号</td>
                                    <td width="18%"><input class="input" name="" type="text"></td>
                                    <td width="11%" align="right">中文名</td>
                                    <td width="18%">
                                    	<select class="select widthB100">
                                             <option>--</option>
                                   		</select>
                                    </td>
                                    <td width="11%" align="right">英文名</td>
                                    <td colspan="3">
                                    	<select class="select widthB98">
                                             <option>--</option>
                                   		</select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">标准价</td>
                                    <td><input class="input textAlignRight" name="" type="text"></td>
                                    <td align="right">当前房租</td>
                                    <td><input class="input textAlignRight" name="" type="text"></td>
                                    <td align="right">入住时间</td>
                                    <td><input class="input" name="" type="text"></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><label><input class="checkbox" name="" type="checkbox" value=""><span class="red">隐藏房价</span></label></td>
                                    <td>&nbsp;</td>
                                    <td align="left"><a class="textDecoration" href="javascript:;">房价列表</a></td>
                                    <td align="right">离店时间</td>
                                    <td colspan="3"><input class="input" name="" type="text"></td>
                                </tr>
                                <tr>
                                    <td align="right">备注</td>
                                    <td colspan="3"><input class="input widthB99" name="" type="text"></td>
                                    <td align="right"><span class="red">支付方式</span></td>
                                    <td colspan="3"><input class="input" name="" type="text"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!--/房间编号-->
                    
                    <div class="roomNumber" style="width:99%;">
                        <div class="tableDivDow clearBoth floatL widthAllBlock">
                            <table width="99%">
                                <tr>
                                    <td width="10%" align="right">A账号</td>
                                    <td width="10%"><input class="input" name="" type="text"></td>
                                    <td width="8%" align="right">总额</td>
                                    <td width="10%"><input class="input" name="" type="text"></td>
                                    <td width="8%" align="right">已付</td>
                                    <td width="10%"><input class="input" name="" type="text"></td>
                                    <td width="8%" align="right">余额</td>
                                    <td width="10%"><input class="input widthB92" name="" type="text"></td>
                                    <td width="15%" align="right"><span class="red">限额+授权</span></td>
                                    <td><input class="input widthB92" name="" type="text"></td>
                                </tr>
                                <tr>
                                    <td align="right">B账号</td>
                                    <td><input class="input" name="" type="text"></td>
                                    <td align="right">总额</td>
                                    <td><input class="input" name="" type="text"></td>
                                    <td align="right">已付</td>
                                    <td><input class="input" name="" type="text"></td>
                                    <td align="right">余额</td>
                                    <td><input class="input widthB92" name="" type="text"></td>
                                    <td align="right"><span class="red">限额+授权</span></td>
                                    <td><input class="input widthB92" name="" type="text"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="tableDivDow clearBoth floatL widthAllBlock">
                    <table width="98%">
                        <tr>
                            <td width="70">
                               <label><input class="radio" name="" type="radio" value="">A账页</label>
                            </td>
                            <td width="70">
                               <label><input class="radio" name="" type="radio" value="">B账页</label>
                            </td>
                            <td width="330">&nbsp;</td>
                            <td width="70">
                               <label><input class="radio" name="" type="radio" value="">明细</label>
                            </td>
                            <td width="70">
                               <label><input class="radio" name="" type="radio" value="">汇总</label>
                            </td>
                            <td width="70">
                               <label><input class="radio" name="" type="radio" value="">归类</label>
                            </td>
                        </tr>
                    </table>
                    </div>
                    
                    <div class="guestBgF">
                    	<!--table-->
                        <div class="tableDiv floatL margin-left-10" style="width:100%;">
                            <!--table title-->
                            <table class="tableMain">
                                <thead> 
                                    <tr>
                                        <td width="12%">序号</td>
                                        <td width="15%">单号</td>
                                        <td width="14%">借项</td>
                                        <td width="15%">贷款</td>
                                        <td width="14%">金额</td>
                                        <td>外币</td>
                                    </tr>
                                </thead>
                            </table>
                            <!--table title end -->
                            <!--table enner-->
                            <div class="tableHeiScll height186">
                                <table class="tableMain">
                                    <tbody>
                                        <tr>
                                            <td width="12%">001</td>
                                            <td width="15%">张三</td>
                                            <td width="15%">男</td>
                                            <td width="15%">房主</td>
                                            <td width="15%">200</td>
                                            <td>20150914</td>
                                        </tr>
                                        <tr>
                                            <td>002</td>
                                            <td>李四</td>
                                            <td>女</td>
                                            <td>同住</td>
                                            <td>0</td>
                                            <td>20150914</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!--table enner -END- -->
                        </div>
                        
                        
                        <!--table -END- -->
                        <div class="clearBoth"></div>
                    </div>
                    
                    <div class="tableDivDow clearBoth floatL widthAllBlock margin-bottom-10">
                    <table width="98%">
                        <tr>
                            <td align="right" width="20">从</td>
                            <td width="85"><input class="input" name="" type="text"></td>
                            <td align="right" width="20">到</td>
                            <td width="85"><input class="input" name="" type="text"></td>
                            <td>
                               <label><input class="radio" name="disturb" type="radio" value="">未结</label>
                               <label><input class="radio margin-left-10" name="disturb" type="radio" value="">已结</label>
                               <label><input class="radio margin-left-10" name="disturb" type="radio" value="">全部</label>
                            </td>
                            <td width="120"><label><input class="checkbox" name="" type="checkbox" value="">无效单</label></td>
                            <td align="right"; width="100">
                               <a class="button_02" href="javascript:;" id="splitAccounts">拆分账目</a>
                            </td>
                        </tr>
                    </table>
                    </div>
                </div>
                <!--右侧部分-->
                
                <div class="GuestTabR2">
                
                	<a class="button_02 widthPx100" href="javascript:;">刷新(R)</a>
                    <a class="button_02 widthPx100" id="checkOut" href="javascript:;">结账(J)</a>
                    <a class="button_02 widthPx100" id="deposit" href="javascript:;">押金(D)</a>
                    <a class="button_02 widthPx100" id="LeavingHotel" href="javascript:;">离店(Q)</a>
                    <a class="button_02 widthPx100" id="receivables" href="javascript:;">收款(S)</a>
                    <a class="button_02 widthPx100" id="checkOutOperation" href="javascript:;">退房(U)</a>
                    <a class="button_02 widthPx100" id="preAuthorization" href="javascript:;">预授权(A)</a>
                    <a class="button_02 widthPx100" id="guestSplitInfo" href="javascript:;">分账(F)</a>
                    <a class="button_02 widthPx100" id="accountedFor" href="javascript:;">入账(I)</a>
                    <a class="button_02 widthPx100" id="customAccounts" href="javascript:;">改单(E)</a>
                    <a class="button_02 widthPx100" href="javascript:;">取消(C)</a>
                    <a class="button_02 widthPx100" href="javascript:;">合同(B)</a>
                    <a class="button_02 widthPx100" id="transferAccounts" href="javascript:;">转账(M)</a>
                    <a class="button_02 widthPx100" href="javascript:;">打印(P)</a>
                    <a class="button_02 widthPx100" id="forALongTime" href="javascript:;">半/全日租</a>
                    <a class="button_02 widthPx100" href="javascript:;">退出(X)</a>
                    <div class="clearBoth red">注意加收半（全）日租</div>
                	<ul class="guestUl userBlock">
                    	<li class="fontWeight"> 
                        	房间列表
                        </li>
                    </ul>
                    <ul class="guestUl2">
                        <li>
                       		<a href="javascript:;">2015</a>
                        </li>
                        <li>
                        	<a href="javascript:;">2016</a>
                        </li>
                        <li>
                       		<a href="javascript:;">2015</a>
                        </li>
                    </ul>
                    
                </div>
                <!--/右侧部分-->
            </div>
            <!--/userCatalog-->
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/住客资料-->
<!--房价列表弹出框-->
<div class="alertDiv moveBar2 alertDiv2 pricesList">
	<div class="alertMain greyBg" style="width:620px; margin-top:40px;">
    	<h4 class="moveDivAlert2">房价列表<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<div class="floatL widthB20">
            	<ul class="guestUl widthB100" style="height:242px;">
                    <li class="fontWeight"> 
                        房间列表
                    </li>
                    <li>
                        <a href="javascript:;">2015</a>
                    </li>
                    <li>
                        <a href="javascript:;">2016</a>
                    </li>
                    <li>
                        <a href="javascript:;">2015</a>
                    </li>
                    <li>
                        <a href="javascript:;">2016</a>
                    </li>
                </ul>
            </div>
        	<div class="floatL widthB77">
        		<h5 class="margin-left-10">8302 | 标准间</h5>
                <!--table-->
                    <div class="tableDiv floatL margin-left-10 margin-top-5" style="width:100%;">
                        <!--table title-->
                        <table class="tableMain">
                            <thead> 
                                <tr>
                                    <td width="12%">序号</td>
                                    <td width="15%">单号</td>
                                    <td width="14%">借项</td>
                                    <td width="15%">贷款</td>
                                    <td width="14%">金额</td>
                                    <td>外币</td>
                                </tr>
                            </thead>
                        </table>
                        <!--table title end -->
                        <!--table enner-->
                        <div class="tableHeiScll height186">
                            <table class="tableMain">
                                <tbody>
                                    <tr>
                                        <td width="12%">001</td>
                                        <td width="15%">张三</td>
                                        <td width="15%">男</td>
                                        <td width="15%">房主</td>
                                        <td width="15%">200</td>
                                        <td>20150914</td>
                                    </tr>
                                    <tr>
                                        <td>002</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                        <td>20150914</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--table enner -END- -->
                    </div>
                    
                    
                    <!--table -END- -->
            	<h5 class="margin-left-5 textAlignRight margin-right-10 margin-top-5">合计：<span class="fontWeight">1468.00</span>元</h5>
            
                <div class="alertRight clearBoth margin-top-30">
                   <a class="buttonLikeA floatR" href="javascript:;">取消</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;">放弃</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;">确定</a>
                </div>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/房价列表弹出框-->
<!--分账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 splitInfoDiv">
	<div class="alertMain greyBg" style="width:555px; margin-top:150px;">
    	<h4 class="moveDivAlert2">分账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--分账-->
        	<table width="100%">
            	<tr>
                	<td width="42%">
                    	<h5 class="margin-left-5 fontWeight">A账页项目</h5>
                    	<ul class="splitDivLeft margin-top-5" id="consumeA_ID">
                        </ul>
                    </td>
                    <td align="center">
                    	<a class="forSN margin-top-20" href="javascript:;" onclick="consumeMove('right','movePart')"><img src="/img/right_01.png"></a>
                    	<a class="forSN margin-top-10" href="javascript:;" onclick="consumeMove('right','moveAll')"><img src="/img/right_02.png"></a>
                    	<a class="forSN margin-top-30" href="javascript:;" onclick="consumeMove('left','movePart')"><img src="/img/left_01.png"></a>
                    	<a class="forSN margin-top-10" href="javascript:;" onclick="consumeMove('left','moveAll')"><img src="/img/left_02.png"></a>
                    </td>
                    <td width="42%">
                    	<h5 class="margin-left-5 fontWeight">B账页项目</h5>
                    	<ul class="splitDivRight margin-top-5" id="consumeB_ID">
                        </ul>
                    </td>
                </tr>
            </table>
            <div class="clearBoth"></div>
           <table class="margin-left-30 margin-top-20">
					<tr>
						<td width="80" height="30" align="right">起始日期</td>
						<td width="110"><input class="input" id="split_startDate" name="" type="text" onfocus="var date = $('#reach_date').val();WdatePicker({minDate:date,maxDate:'#F{$dp.$D(\'split_endDate\')}'})">
						</td>
						<td width="80" align="right">终止日期</td>
						<td width="110"><input class="input" id="split_endDate" name="" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'split_startDate\')}'})">
						</td>
					</tr>
					<tr>
						<td width="110" colspan="4"><label><input
								class="checkbox margin-right-5 margin-left-15" name="" id="if_bate"
								type="checkbox" value="">续住时自动修改分账日期</label>
						</td>
					</tr>
			</table>
            <!--分账-->
            
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="consumeQuit()">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="consumeCancle()">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="consumeQuit()">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/分账弹出框-->
<!--其他信息弹出框-->
<div class="alertDiv moveBar2 alertDiv2 otherDiv">
	<div class="alertMain greyBg" style="width:630px; margin-top:150px;">
    	<h4 class="moveDivAlert2">其他信息<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        <form id="otherForm">
        	<!--分账-->
        	<table width="95%" class="margin-top-30 gry_9">
                <tr>
                    <td width="25%" align="right">订单号</td>
                    <td width="25%"><input class="input gry_9" name="" type="text" placeholder="Admin" disabled></td>
                    <td width="18%" align="right">登记人</td>
                    <td width="32%"><input class="input gry_9" name="checkName" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">订单状态</td>
                    <td>
                    	<select class="select widthB100 gry_9" disabled>
                             <option>--</option>
                        </select>
                    </td>
                    <td align="right">登记时间</td>
                    <td><input class="input gry_9" name="chk_time" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">确定时间</td>
                    <td><input class="input gry_9" name="confirm_date" type="text" disabled></td>
                    <td align="right">恢复入住人</td>
                    <td><input class="input gry_9" name="rechkName" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">预订方式</td>
                    <td>
                    	<select class="select widthB100 gry_9" disabled>
                             <option>--</option>
                        </select>
                    </td>
                    <td align="right">恢复时间</td>
                    <td><input class="input gry_9" name="" type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">预订人</td>
                    <td><input class="input gry_9" name="bookName" type="text" disabled></td>
                    <td align="right">最后修改人</td>
                    <td><input class="input gry_9" name="lastName
                    
                    " type="text" disabled></td>
                </tr>
                <tr>
                    <td align="right">联系电话</td>
                    <td><input class="input gry_9" name="" type="text" disabled></td>
                    <td align="right">修改时间</td>
                    <td><input class="input gry_9" name="" type="text" disabled></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                    <td align="right">退房人</td>
                    <td><input class="input gry_9" name="outName" type="text" disabled></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                    <td align="right">退房时间</td>
                    <td><input class="input gry_9" name="" type="text" disabled></td>
                </tr>
            </table>
            </form>
            <!--分账-->
            
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">退出</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/其他信息弹出框-->
<!--押金弹出框-->
<div class="alertDiv moveBar2 alertDiv2 depositDiv">
	<div class="alertMain greyBg" style="width:560px; margin-top:240px;">
    	<h4 class="moveDivAlert2">收银<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--押金-->
        	<table width="95%">
            	<tr>
                	<td align="right">收款方式</td>
                    <td>
                    	<select class="select widthB99">
                             <option>--</option>
                        </select>
                    </td>
                    <td width="20%" align="right">币种</td>
                    <td>
                    	<select class="select widthB99">
                             <option>--</option>
                        </select>
                    </td>
                </tr>
                <tr>
                	<td width="20%" align="right"><span class="red">单号</span></td>
                    <td>
                    	<input class="inputInRight" name="" type="text">
                    </td>
                    <td colspan="2">&nbsp;</td>
                    
                </tr>
                <tr>
                	<td align="right"><span class="red">本位币金额</span></td>
                    <td><input class="inputInRight" name="" type="text"></td>
                    <td align="right"><span class="red">外币金额</span></td>
                    <td><input class="inputInRight" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right">备注</td>
                    <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                </tr>
            </table>
            <!--押金-->
            
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/押金弹出框-->
<!--收款弹出框-->
<div class="alertDiv moveBar2 alertDiv2 receivablesDiv">
	<div class="alertMain greyBg" style="width:560px; margin-top:240px;">
    	<h4 class="moveDivAlert2">收款<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--押金-->
        	<table width="95%">
            	<tr>
                	<td align="right"><span class="red">收款方式</span></td>
                    <td>
                    	<select class="select widthB99">
                             <option>--</option>
                        </select>
                    </td>
                    <td width="20%" align="right">币种</td>
                    <td>
                    	<select class="select widthB99">
                             <option>--</option>
                        </select>
                    </td>
                </tr>
                <tr>
                	<td width="20%" align="right"><span class="red">单号</span></td>
                    <td>
                    	<input class="inputInRight" name="" type="text">
                    </td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                	<td align="right"><span class="red">本位币金额</span></td>
                    <td><input class="inputInRight" name="" type="text"></td>
                    <td align="right"><span class="red">外币金额</span></td>
                    <td><input class="inputInRight" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right">备注</td>
                    <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                </tr>
            </table>
            <!--押金-->
            
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/收款弹出框-->
<!--离店弹出框-->
<div class="alertDiv moveBar2 alertDiv2 LeavingHotelDiv">
	<div class="alertMain greyBg" style="width:650px; margin-top:200px;">
    	<h4 class="moveDivAlert2">离店操作<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
            	<ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="" type="radio" value="">全部离店</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="" type="radio" value="">选择离店</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="" type="radio" value="">当日离店</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="" type="radio" value="">当前客人</label></li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                    <!--table title-->
                    <table class="tableMain">
                        <thead> 
                            <tr>
                                <td width="12%">中文名</td>
                                <td width="15%">英文名</td>
                                <td width="8%">房号</td>
                                <td width="12%">付款人</td>
                                <td width="9%">摘要</td>
                                <td width="15%">离店日期</td>
                                <td>登记日期</td>
                            </tr>
                        </thead>
                    </table>
                    <!--table title end -->
                    <!--table enner-->
                    <div class="tableHeiScll height186">
                        <table class="tableMain">
                            <tbody>
                                <tr>
                                    <td width="13%">001</td>
                                    <td width="15%">张三</td>
                                    <td width="8%">男</td>
                                    <td width="13%">房主</td>
                                    <td width="10%">200</td>
                                    <td width="15%">在住</td>
                                    <td>20150914</td>
                                </tr>
                                <tr>
                                    <td>002</td>
                                    <td>李四</td>
                                    <td>女</td>
                                    <td>同住</td>
                                    <td>0</td>
                                    <td>在住</td>
                                    <td>20150914</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
                <div class="clearBoth padding-top-10 textAlignRight margin-right-5">
                	<label><input class="checkbox margin-right-5" name="" type="checkbox" value="">逐个提示</label>
                </div>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/离店弹出框-->
<!--预授权出框-->
<div class="alertDiv moveBar2 alertDiv2 preAuthorizationDiv">
	<div class="alertMain greyBg" style="width:650px; margin-top:200px;">
    	<h4 class="moveDivAlert2">预授权<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
            	
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                    <!--table title-->
                    <table class="tableMain">
                        <thead> 
                            <tr>
                                <td width="12%">中文名</td>
                                <td width="15%">英文名</td>
                                <td width="8%">房号</td>
                                <td width="12%">付款人</td>
                                <td width="9%">摘要</td>
                                <td width="15%">离店日期</td>
                                <td>登记日期</td>
                            </tr>
                        </thead>
                    </table>
                    <!--table title end -->
                    <!--table enner-->
                    <div class="tableHeiScll height186">
                        <table class="tableMain">
                            <tbody>
                                <tr>
                                    <td width="13%">001</td>
                                    <td width="15%">张三</td>
                                    <td width="8%">男</td>
                                    <td width="13%">房主</td>
                                    <td width="10%">200</td>
                                    <td width="15%">在住</td>
                                    <td>20150914</td>
                                </tr>
                                <tr>
                                    <td>002</td>
                                    <td>李四</td>
                                    <td>女</td>
                                    <td>同住</td>
                                    <td>0</td>
                                    <td>在住</td>
                                    <td>20150914</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
                <ul class="widthB100 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="radio1" type="radio" value="">有效</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="radio1" type="radio" value="">全部</label></li>
                    <li class="floatR margin-right-20">有效预授权合计：5000.00</li>
                </ul>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">取消</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">取消预授权</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">完成预授权</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="addPreAward">新授权</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--新增预授权-->
<div class="alertDiv moveBar3 alertDiv3 addPreAwardDiv">
	<div class="alertMain greyBg" style="width:460px; margin-top:180px;">
    	<h4 class="moveDivAlert3">新增预授权<a href="javascript:;" class="closeDiv2 closeAlert3"></a></h4>
        <div class="borderSolid">
        	<!--押金-->
        	<table width="95%">
            	<tr>
                	<td width="100" align="right">单号</td>
                    <td><input class="input widthB50" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right">信用卡卡号</td>
                    <td><input class="input" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right">持卡人</td>
                    <td><input class="input widthB50" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right">授权号</td>
                    <td><input class="input" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right">预授权金额</td>
                    <td><input class="input widthB50" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right">有效期</td>
                    <td><input class="input widthB50" name="" type="text"></td>
                </tr>
                <tr>
                	<td align="right" valign="top"><div class="padding-top-10">备注</div></td>
                    <td><textarea class="textarea widthB97 margin-top-5" name="" cols="" rows=""></textarea></td>
                </tr>
                
            </table>
            <!--押金-->
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--新增预授权END-->
<!--/预授权出框-->
<!--入账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 accountedForDiv">
	<div class="alertMain greyBg" style="width:660px;margin-top:200px;">
    	<h4 class="moveDivAlert2">入账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
            	<ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="">A账页</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="">B账页</label></li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB70 margin-top-10 padding-10">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right">房号</td>
                            <td width="30%"><input class="input" name="" type="text"></td>
                            <td width="20%" align="right">信用限额</td>
                            <td><input class="input" name="" type="text"></td>
                            <!--<td><label><input class="checkbox margin-right-5" name="" type="checkbox">收服务费</label></td>-->
                        </tr>
                        <tr>
                            <td align="right">账号</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right"><span class="red">账单号</span></td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">中文名</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                            <td align="right"><span class="red">消费点</span></td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">英文名</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                            <td align="right">币种</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">&nbsp;</td>
                            <td>
                            	<label><input class="checkbox margin-right-5" name="" type="checkbox">收服务费</label>
                            </td>
                            <td align="right"><span class="red">本位币</span></td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">服务费</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right"><span class="red">外币金额</span></td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                        </tr>
                    </table>
                    <!--table enner -END- -->
                </div>
                <ul class="floatL margin-left-40 margin-top-10">
                	<li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">退出</a></li>
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">当前客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">所有客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">所有房间</label></li>
                </ul>
                <!--table -END- -->
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/入账弹出框-->
<!--结账打单弹出框-->
<div class="alertDiv moveBar2 alertDiv2 checkOutDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:150px;">
    	<h4 class="moveDivAlert2">结账打单<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB80 floatL">
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                        <!--table title-->
                        <table class="tableMain">
                            <thead> 
                                <tr>
                                    <td width="25%">贷方科目</td>
                                    <td width="20%">金额</td>
                                    <td width="20%">外币</td>
                                    <td width="20%">币种</td>
                                    <td>备注</td>
                                </tr>
                            </thead>
                        </table>
                        <!--table title end -->
                        <!--table enner-->
                        <div class="tableHeiScll height100">
                            <table class="tableMain">
                                <tbody>
                                    <tr>
                                        <td width="25%">贷方科目</td>
                                        <td width="22%">金额</td>
                                        <td width="21%">外币</td>
                                        <td width="20%">币种</td>
                                        <td>备注</td>
                                    </tr>
                                    <tr>
                                        <td>002</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--table enner -END- -->
                    </div>
                    <!--/table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td width="20%" align="right">结账方式</td>
                                <td width="30%">
                                	<select class="select widthB100">
                                         <option>--</option>
                                    </select>
                                </td>
                                <td width="20%" align="right">AR账号</td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">币种</td>
                                <td>
                                	<select class="select widthB100">
                                         <option>--</option>
                                    </select>
                                </td>
                                <td align="right">结账余额</td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                            <tr>


                                <td align="right">已结金额</td>
                                <td><input class="input" name="" type="text"> </td>
                                <td align="right">本位币余额</td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">结账零头</td>
                                <td><input class="input" name="" type="text"> </td>
                                <td align="right"><span class="red">本位币合计</span></td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td width="20%" align="right"><span class="red">外币实付</span></td>
                                <td width="30%"><input class="input" name="" type="text"></td>
                                <td width="20%" align="right"><span class="red">本位币实付</span></td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">外币小费</td>
                                <td><input class="input" name="" type="text"></td>
                                <td align="right">本位币小费</td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">结账备注</td>
                                <td colspan="3"><input class="input widthB98" name="" type="text"> </td>
                            </tr>
                        </table>
                    </div>
                </div>
                
                
                <ul class="floatL margin-left-20 margin-top-5">
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">打单</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">退出</a></li>
                    <li class="margin-top-10"><label><input class="checkbox margin-right-5" name="list_01" type="checkbox" value="">打印订单</label></li>
                    <li class="margin-top-5"><label><input class="checkbox margin-right-5" name="list_01" type="checkbox" value="">打印电话</label></li>
                    <li class="margin-top-15" style="border:solid 1px #999;padding:5px;">
                    	<label class="displayBlock"><input class="radio margin-right-5" name="list_02" type="radio" value="">中文单</label>
                        <label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="list_02" type="radio" value="">英文单</label>
                    </li>
                    <li style="border:solid 1px #999;padding:5px;border-top:none;">
                    	<label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="list_03" type="radio" value="">当前账页</label>
                        <label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="list_03" type="radio" value="">部分账页</label>
                        <label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="list_03" type="radio" value="">全部账页</label>
                    </li>
                </ul>
                
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td width="16%" align="right">会员卡号</td>
                                <td width="30%">
                                	<table width="100%">
                                        <tr>
                                            <td style="padding:0;"><input class="input" name="" type="text"></td>
                                            <td width="28" align="right">
                                                <a class="SFbutton floatR marginRight-4" href="javascript:;">···</a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="20%" align="right">公司简称</td>
                                <td>
                                	<table width="100%">
                                        <tr>
                                            <td style="padding:0;"><input class="input" name="" type="text"></td>
                                            <td width="28" align="right">
                                                <a class="SFbutton floatR marginRight-4" href="javascript:;">···</a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                    
                
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/结账打单弹出框-->
<!--转账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 transferAccountsDiv">
	<div class="alertMain greyBg" style="width:750px; margin-top:200px;">
    	<h4 class="moveDivAlert2">转账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
            	
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                    <!--table title-->
                    <table class="tableMain">
                        <thead> 
                            <tr>
                            	<td width="7%">序号</td>
                                <td width="9%">中文名</td>
                                <td width="7%">房号</td>
                                <td width="7%">摘要</td>
                                <td width="9%">团代码</td>
                                <td width="11%">A账余额</td>
                                <td width="11%">B账余额</td>
                                <td width="12%">抵店日期</td>
                                <td width="12%">离店日期</td>
                                <td width="7%">公司</td>
                                <td>账号</td>
                            </tr>
                        </thead>
                    </table>
                    <!--table title end -->
                    <!--table enner-->
                    <div class="tableHeiScll height186">
                        <table class="tableMain">
                            <tbody>
                                <tr>
                                    <td width="8%">001</td>
                                    <td width="9%">李四</td>
                                    <td width="7%">lisi</td>
                                    <td width="7%">摘要</td>
                                    <td width="9%">团代码</td>
                                    <td width="11%">A账余额</td>
                                    <td width="11%">B账余额</td>
                                    <td width="12%">抵店日期</td>
                                    <td width="12%">离店日期</td>
                                    <td width="7%">公司</td>
                                    <td>账号</td>
                                </tr>
                                <tr>
                                    <td>002</td>
                                    <td>李四</td>
                                    <td>女</td>
                                    <td>同住</td>
                                    <td>0</td>
                                    <td>在住</td>
                                    <td>12</td>
                                    <td>同住</td>
                                    <td>0</td>
                                    <td>在住</td>
                                    <td>jj</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
                <ul class="widthB96 margin-left-10 padding-top-10 clearBoth">
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5" name="list_04" type="radio" value="">个人</label></li>
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5" name="list_04" type="radio" value="">团体</label></li>
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5" name="list_04" type="radio" value="">非住客</label></li>
                    <li class="floatR">
                    	<table width="150">
                        	<tr>
                            	<td width="50" align="right">账号</td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                        </table>
                    </li>
                    <li class="floatR margin-right-20">
                    	<table width="150">
                        	<tr>
                            	<td width="50" align="right">房号</td>
                                <td><input class="input" name="" type="text"></td>
                            </tr>
                        </table>
                    </li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR">
                    <div class="jsWord">检索条件</div>
                    <table class="widthB98 margin-top-15 margin-bottom-10">
                        <tr>
                            <td width="12%" align="right">中文名</td>
                            <td width="13%"><input class="input" name="" type="text"></td>
                            <td width="12%" align="right">英文名</td>
                            <td width="13%"><input class="input" name="" type="text"></td>
                            <td width="12%" align="right">抵店日期</td>
                            <td width="13%"><input class="input" name="" type="text"></td>
                            <td width="12%" align="right">房号</td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">团代码</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">团名</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">离店日期</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">退房人</td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">合约单位</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                            <td align="right" colspan="4">
                            	<label class="margin-right-15"><input class="radio margin-right-5" name="list_05" type="radio" value="">在住客人</label>
                                <label><input class="radio" name="list_05" type="radio" value="">离店客人</label>
                            </td>
                        </tr>
                    </table>
                
                
                </div>
                <div class="alertRight clearBoth margin-top-30">
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;">退出</a>
                   <a class="buttonLikeA floatR margin-right-10" id="accountDetermine" href="javascript:;">确定</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;">清空条件</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;">条件检索</a>
                </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--确定转账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 accountDetermineDiv displayNone">
	<div class="alertMain greyBg" style="width:900px;margin-top:150px;">
    	<h4 class="moveDivAlert2">转账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB85 floatL">
                	<table width="100%">
                    	<tr>
                        	<td align="right">中文名</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">英文名</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                            <td align="right">房号</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="center">
                            	<label class="margin-right-15"><input class="radio margin-right-5" name="list_05" type="radio" value="">A1账</label>
                                <label><input class="radio" name="list_05" type="radio" value="">B3账</label>
                            </td>
                        </tr>
                    	<tr>
                    		<td width="50" align="right">账号</td>
                            <td width="100"><input class="input" name="" type="text"></td>
                            <td width="60" align="right">总额</td>
                            <td width="60"><input class="input" name="" type="text"></td>
                            <td width="45" align="right">已付</td>
                            <td width="60"><input class="input" name="" type="text"></td>
                            <td width="40" align="right">余额</td>
                            <td width="70"><input class="input" name="" type="text"></td>
                            <td rowspan="2" align="center">
                            	<a class="roomPointBottom" href="javascript:;"></a>
                            </td>
                        </tr>
                        <tr>
                        	<td align="right">内容</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">备注</td>
                            <td colspan="5"><input class="input widthB99" name="" type="text"></td>
                        </tr>
                        
                    </table>
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                        <!--table title-->
                        <table class="tableMain">
                            <thead> 
                                <tr>
                                    <td width="10%">借项</td>
                                    <td width="10%">贷款</td>
                                    <td width="10%">单号</td>
                                    <td width="10%">金额</td>
                                    <td width="10%">外币</td>
                                    <td width="10%">币种</td>
                                    <td width="10%">服务费</td>
                                    <td width="15%">时间</td>
                                    <td>摘要</td>
                                </tr>
                            </thead>
                        </table>
                        <!--table title end -->
                        <!--table enner-->
                        <div class="tableHeiScll height100">
                            <table class="tableMain">
                                <tbody>
                                    <tr>
                                        <td width="10%">001</td>
                                        <td width="10%">z3</td>
                                        <td width="10%">nan</td>
                                        <td width="10%">yes</td>
                                        <td width="10%">金额</td>
                                        <td width="10%">外币</td>
                                        <td width="10%">币种</td>
                                        <td width="15%">服务费</td>
                                        <td>状态</td>
                                    </tr>
                                    <tr>
                                        <td>002</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--table enner -END- -->
                    </div>
                    <!--/table-->
                    
                    <div class="clearBoth margin-bottom-20"></div>
                    <table width="100%">
                    	<tr>
                        	<td align="right">中文名</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">英文名</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                            <td align="right">房号</td>
                            <td><input class="input" name="" type="text"></td>
                            <td rowspan="2" align="center">
                                <a class="roomPointTop" href="javascript:;"></a>
                            </td>
                            
                        </tr>
                    	<tr>
                    		<td width="50" align="right">账号</td>
                            <td width="100"><input class="input" name="" type="text"></td>
                            <td width="60" align="right">总额</td>
                            <td width="60"><input class="input" name="" type="text"></td>
                            <td width="45" align="right">已付</td>
                            <td width="60"><input class="input" name="" type="text"></td>
                            <td width="40" align="right">余额</td>
                            <td width="70"><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                        	<td align="right">内容</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">备注</td>
                            <td colspan="5"><input class="input widthB99" name="" type="text"></td>
                            <td align="center">
                            	<label class="margin-right-15"><input class="radio margin-right-5" name="list_10" type="radio" value="">A2账</label>
                                <label><input class="radio" name="list_10" type="radio" value="">B4账</label>
                            </td>
                        </tr>
                        
                    </table>
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                        <!--table title-->
                        <table class="tableMain">
                            <thead> 
                                <tr>
                                    <td width="10%">借项</td>
                                    <td width="10%">贷款</td>
                                    <td width="10%">单号</td>
                                    <td width="10%">金额</td>
                                    <td width="10%">外币</td>
                                    <td width="10%">币种</td>
                                    <td width="10%">服务费</td>
                                    <td width="15%">时间</td>
                                    <td>摘要</td>
                                </tr>
                            </thead>
                        </table>
                        <!--table title end -->
                        <!--table enner-->
                        <div class="tableHeiScll height100">
                            <table class="tableMain">
                                <tbody>
                                    <tr>
                                        <td width="10%">001</td>
                                        <td width="10%">z3</td>
                                        <td width="10%">nan</td>
                                        <td width="10%">yes</td>
                                        <td width="10%">金额</td>
                                        <td width="10%">外币</td>
                                        <td width="10%">币种</td>
                                        <td width="15%">服务费</td>
                                        <td>状态</td>
                                    </tr>
                                    <tr>
                                        <td>002</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--table enner -END- -->
                    </div>
                    <!--/table-->
                </div>
                <ul class="floatL margin-left-20 margin-top-90">
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">刷新</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">退出</a></li>
                </ul>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>


<!--/确定转账弹出框-->
<!--/转账弹出框-->
<!--客人账目分账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 guestSplitInfoDiv">
	<div class="alertMain greyBg" style="width:555px;margin-top:150px;">
    	<h4 class="moveDivAlert2">分账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--分账-->
        	<table width="100%">
            	<tr>
                	<td width="42%">
                    	<h5 class="margin-left-5 fontWeight">A账页项目</h5>
                    	<ul class="splitDivLeft margin-top-5">
                        	<li>房租</li>
                            <li>半日租/全日租</li>
                            <li>加床费</li>
                            <li>钟点房费</li>
                            <li>迷你吧</li>
                            <li>大堂吧</li>
                            <li>餐饮</li>
                            <li>杂项</li>
                            <li>调账专用</li>
                            <li>零头</li>
                            <li>小费</li>
                        </ul>
                    </td>
                    <td align="center">
                    	<a class="splitPointRight" href="javascript:;"></a>
                    	<a class="splitPointLeft margin-top-10" href="javascript:;"></a>
                    </td>
                    <td width="42%">
                    	<h5 class="margin-left-5 fontWeight">B账页项目</h5>
                    	<ul class="splitDivRight margin-top-5">
                        	<li>111</li>
                            <li>222</li>
                            <li>333</li>
                        </ul>
                    </td>
                </tr>
            </table>
            <div class="clearBoth"></div>
            <table class="margin-left-30 margin-top-20">
                <tr>
                    <td width="80" height="30" align="right">起始日期</td>
                    <td width="110"><input class="dataTimeInput datetimepicker" type="text" value=""/></td>
                    <td width="80" align="right">终止日期</td>
                    <td width="110"><input class="dataTimeInput datetimepicker" type="text" value=""/></td>
                </tr>
                <tr>
                    <td colspan="4"><label><input class="checkbox margin-right-5 margin-left-15" name="" type="checkbox" value="">续住时自动修改分账日期</label></td>
                </tr>
            </table>
            <!--分账-->
            
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">取消</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/客人账目分账弹出框-->
<!--半日租入账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 forALongTimeDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">入账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
            	<ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="">A账页</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="">B账页</label></li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB70 margin-top-10 padding-10">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right"><span class="red">房号</span></td>
                            <td width="30%"><input class="input" name="" type="text"></td>
                            <td width="20%" align="right">信用限额</td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        
                        <tr>
                            <td align="right">账号</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">消费点</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">中文名</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                            <td align="right">币种</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">英文名</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                            <td align="right"><span class="red">账单号</span></td>
                            <td>
                            	<input class="input" name="" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td align="right">&nbsp;</td>
                            <td><label><input class="checkbox margin-right-5" name="" type="checkbox">收服务费</label></td>
                            <td align="right"><span class="red">本位币</span></td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right"><span class="red">服务费</span></td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right"><span class="red">外币金额</span></td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                        </tr>
                    </table>
                    <!--table enner -END- -->
                </div>
                <ul class="floatL margin-left-40 margin-top-10">
                	<li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">退出</a></li>
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">当前客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">所有客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">所有房间</label></li>
                </ul>
                <!--table -END- -->
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/半日租入账弹出框-->
<!--退房弹出框-->
<div class="alertDiv moveBar2 alertDiv2 checkOutOperationDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">退房操作<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<div class="tableDiv floatL margin-left-10 widthB93 margin-top-10 padding-10">
            	<h1 class="margin-left-20 margin-top-20">9527是否退房？</h1>
                <div class="margin-top-10 floatR">
                	<a class="buttonLikeA floatL margin-right-10" href="javascript:;">是(Y)</a>
                	<a class="buttonLikeA floatL margin-right-20" href="javascript:;">否(N)</a>
                </div>
            </div>
            <div class="tableDiv floatL margin-left-10 widthB93 margin-top-10 padding-10">
            	<h1 class="margin-left-20 margin-top-20">8302 属于联房（团队房），是否退房？</h1>
                <div class="margin-top-10 floatR">
                	<a class="buttonLikeA floatL margin-right-10" href="javascript:;">全部退房</a>
                    <a class="buttonLikeA floatL margin-right-10" href="javascript:;">仅退此房</a>
                	<a class="buttonLikeA floatL margin-right-20" href="javascript:;">取消</a>
                </div>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/退房弹出框-->
<!--结账打单弹出框-->
<div class="alertDiv moveBar2 alertDiv2 customAccountsDiv">
	<div class="alertMain greyBg" style="width:750px;margin-top:150px;">
    	<h4 class="moveDivAlert2">自定义账目<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB80 floatL">
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                        <!--table title-->
                        <table class="tableMain">
                            <thead> 
                                <tr>
                                    <td width="10%">序号</td>
                                    <td width="10%">单号</td>
                                    <td width="10%">借项</td>
                                    <td width="10%">贷项</td>
                                    <td width="10%">金额</td>
                                    <td width="10%">外币</td>
                                    <td width="10%">币种</td>
                                    <td width="15%">服务费</td>
                                    <td>状态</td>
                                </tr>
                            </thead>
                        </table>
                        <!--table title end -->
                        <!--table enner-->
                        <div class="tableHeiScll height100">
                            <table class="tableMain">
                                <tbody>
                                    <tr>
                                        <td width="10%">001</td>
                                        <td width="10%">z3</td>
                                        <td width="10%">nan</td>
                                        <td width="10%">yes</td>
                                        <td width="10%">金额</td>
                                        <td width="10%">外币</td>
                                        <td width="10%">币种</td>
                                        <td width="15%">服务费</td>
                                        <td>状态</td>
                                    </tr>
                                    <tr>
                                        <td>002</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                        <td>李四</td>
                                        <td>女</td>
                                        <td>同住</td>
                                        <td>0</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--table enner -END- -->
                    </div>
                    <!--/table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td align="right">客人名</td>
                                <td><input class="input" name="" type="text"></td>
                                <td align="right">协议单位</td>
                                <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                            </tr>
                            <tr>
                                <td width="13%" align="right">房号</td>
                                <td width="18%"><input class="input" name="" type="text"></td>
                                <td width="17%" align="right">抵店时间</td>
                                <td width="18%"><input class="input" name="" type="text"></td>
                                <td width="17%" align="right">离店时间</td>
                                <td><input class="input widthB93" name="" type="text"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td width="13%" align="right">房租</td>
                                <td width="12%"><input class="input" name="" type="text"></td>
                                <td width="13%" align="right">消费额</td>
                                <td width="12%"><input class="input" name="" type="text"></td>
                                <td width="13%" align="right">已付额</td>
                                <td width="12%"><input class="input" name="" type="text"></td>
                                <td width="13%" align="right">余额</td>
                                <td><input class="input widthB90" name="" type="text"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <ul class="floatL margin-left-20 margin-top-5">
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">添加</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">删除</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">打印</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">退出</a></li>
                </ul>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/结账打单弹出框-->
<!--拆分账目弹出框-->
<div class="alertDiv moveBar2 alertDiv2 splitAccountsDiv">
	<div class="alertMain greyBg" style="width:650px;margin-top:150px;">
    	<h4 class="moveDivAlert2">拆分账目<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB80 floatL">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right">房号</td>
                            <td width="30%"><input class="input" name="" type="text"></td>
                            <td width="20%" align="right">账号</td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">中文名</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                            <td align="right">英文名</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">账单号</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">ID号</td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">消费点</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                            <td align="right">本位币</td>
                            <td>
                            	<select class="select widthB100">
                                     <option>--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">本位币</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">外币金额</td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                    </table>
                    <div class="clearBoth link2"></div>
                    <table width="100%" class="margin-top-10">
                        <tr>
                            <td width="20%" align="right"><span class="red">拆分本位币</span></td>
                            <td width="30%"><input class="input" name="" type="text"></td>
                            <td width="20%" align="right"><span class="red">拆分外币</span></td>
                            <td><input class="input" name="" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">备注</td>
                            <td colspan="3">
                            	<input class="input widthB98" name="" type="text">
                            </td>
                        </tr>
                    </table>
                </div>
                <ul class="floatL margin-left-20 margin-top-5">
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">退出</a></li>
                </ul>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
