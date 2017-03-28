<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销-熟客资料</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>

<body>
	<%@ include file="../header.jsp" %>
	<%@ include file="secondMenu.jsp" %>
	<script type="text/javascript">
setSecondMenuStat(3);
</script>
<!--banner & menu -END -->
<!--center xingli 2015-09-08-->
<div class="center">
    <!--mainWeb-->
    <div class="main" id="webMain">
    	<!--mainInformation-->
        <div class="mainInfo2">
	        <!--table-->
	    	<div id="flexgrid" class="tableDiv" style="overflow-x: hidden; overflow-y: auto;height: 664px;">
	        
	        </div>
	        <!--table -END- -->
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<div class="rightMenu">
        	<div class="roomStatus">                
                <div class="choice padding-top-10 padding-bottom-10">
                    <form id="searchForm" class="bookRight" action="" method="get">
                        <dl class="inputDiv2 margin-top-none">
                            <dt>熟客编号</dt>
                            <dd>
                                <input class="input" name="gstId" data-inputmask="'mask': '9', 'repeat': 11, 'greedy' : false" type="text" placeholder="">
                            </dd>
                            <dt>中文名</dt>
                            <dd>
                                <input class="input" name="gstNamec" type="text" placeholder="">
                            </dd>
                            <dt>英文名</dt>
                            <dd>
                                <input class="input" name="gstNamee" type="text" placeholder="">
                            </dd>
                            <dt>联系电话</dt>
                            <dd>
                                <input class="input" name="mobile"  data-inputmask="'mask': '9', 'repeat': 11, 'greedy' : false" type="text" placeholder="">
                            </dd>
                            <dt>身份证号</dt>
                            <dd>
                                <input class="input" hidden name="crdkindId" type="text"  style="display: none;"><!-- 需要写定 为 身份证 -->
                                <input class="input" name="crdId" type="text" data-inputmask="'mask': '9', 'repeat': 30, 'greedy' : false" placeholder="">
                            </dd>
                            <!--  
                            <dt>合约单位</dt>
                            <dd>
                                <input class="input" name="" type="text" placeholder="">
                            </dd>-->
                            <dt>会员卡号</dt>
                            <dd>
                                <input class="input" name="" data-inputmask="'mask': '9', 'repeat': 20, 'greedy' : false" type="text" placeholder="">
                            </dd>
                            <dt>住店次数从</dt>
                            <dd>
                                <table width="100%">
                                	<tr>
                                    	<td><input class="input" name="" data-inputmask="'mask': '9', 'repeat': 5, 'greedy' : false" type="text" placeholder=""></td>
                                        <td width="30" align="center">到</td>
                                        <td><input class="input widthB90" name="" data-inputmask="'mask': '9', 'repeat': 5, 'greedy' : false" type="text" placeholder=""></td>
                                    </tr>
                                </table>	
                            </dd>
                            <dt>上次住店从</dt>
                            <dd>
                                <table width="100%">
                                	<tr>
                                    	<td><input class="input Wdate" name="reachDateStart" type="text" onclick="WdatePicker();" placeholder=""></td>
                                        <td width="30" align="center">到</td>
                                        <td><input class="input widthB90 Wdate" name="reachDateEnd" onclick="WdatePicker({maxDate:'%y-%M-%d'});" type="text" placeholder=""></td>
                                    </tr>
                                </table>	
                            </dd>
                        </dl>
                        <div class="clearBoth"></div>
                        <br />
                        <table width="90%" class="margin-left-10 margin-top-20">
                            <tr>
                                <td align="right"><label  class="margin-right-10"><input name="status" type="radio" id="effective" value="0" checked="checked"><span style="font-size: 14px" class="margin-left-5">有效</span></label></td>
                                <td align="left"><label class="margin-left-10"><input name="status" type="radio" id="uneffective" value="1"><span style="font-size: 14px" class="margin-left-5">无效</span></label></td>
                            </tr>
                        </table>
                        <div class="cabDivNoneHei clearBoth padding-top-15">
                        	<a class="button_07 floatL" href="javascript:;" onclick="doSearch()">条件查询</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="searchReset()">清空条件</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="formEdit()">熟客详情</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="formAdd()">新增</a>
                            <a class="button_07 floatL" href="javascript:;">查看业绩</a>
                            <a class="button_07 floatL" href="javascript:;">合并</a>
                        </div>
                        <!--小键盘-->
                        <div class="cabDiv clearBoth padding-top-10">
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
<!--弹出层阴影结束-->
<!--入住登记-->
<form id="dataForm" action="">
<div class="alertDiv checkInDiv moveBar acquaintanceDiv">
	<div class="alertMain greyBg" style="width:970px; margin-top:100px;">
    	<h4 class="moveDivAlert" id="MoveAlertDiv"><span id="formTitle">熟客资料维护</span><a href="javascript:;" class="closeDiv"></a></h4>
        <div class="borderSolid">
            <div class="clearBoth GuestTabIn fitBook posR">
            	<div class="GuestTabL3 margin-top-30 posR">
                  <div class="posA" style="top:-30px; right:0;"><label><input id="theStatus" class="checkbox margin-right-5" name="status" value="0" type="checkbox" checked readonly onclick="return false;">有效</label></div>
                    <div class="tableDivDow clearBoth margin-bottom-10 floatL posR" style="width:600px;">
                            <table width="100%">
                                <tr>
                                    <td width="70" align="right">中文名</td>
                                    <td width="100"><input class="input" name="gstNamec" type="text" data-validation="required,isHanZi" label="中文名" maxlength="4" onBlur="gstNamee.value=(pinyin.getFullChars(this.value));" onChange="gstNamee.value=(pinyin.getFullChars(this.value));" onKeydown="gstNamee.value=(pinyin.getFullChars(this.value));"></td>
                                    <td width="50" align="right">性别</td>
                                    <td width="100">
                                    	<select class="select widthB95" name="sex" data-validation="required" label="性别">
                                             <c:forEach items="${sexs}" var="list" varStatus="status">
                                             <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if>>${list.codeNamec}</option>
                                             </c:forEach>
                                        </select>
                                    </td>
                                    <td width="50" align="right">民族</td>
                                    <td width="110">
                                    	<select class="select widthB94" name="folk">
                                             <c:forEach items="${folks}" var="list" varStatus="status">
                                             <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if>>${list.codeNamec}</option>
                                             </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">英文名</td>
                                    <td><input class="input" id="gstNamee" name="gstNamee" type="text" data-validation="required" readonly="readonly"></td>
                                    <td align="right">生日</td>
                                    <td><input class="input" name="birthday" type="text"  data-inputmask="'alias': 'yyyy-mm-dd'" data-validation="simpleDate" label="生日"></td>
                                    <td align="right">国籍</td>
                                    <td>
                                    	<select class="select widthB94" name="ntId" data-validation="required" label="国籍">
                                             <c:forEach items="${countrys}" var="list" varStatus="status">
                                             <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if>>${list.codeNamec}</option>
                                             </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">电话</td>
                                    <td><input class="input" name="mobile" type="text" data-inputmask="'mask': '9', 'repeat': 11, 'greedy' : false"></td>
                                    <td align="right">证件</td>
                                    <td>
                                    	<select class="select widthB95" name="crdkindId" data-validation="required" label="证件">
                                             <c:forEach items="${cardTypes}" var="list" varStatus="status">
                                                <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if> >${list.codeNamec}</option>
                                             </c:forEach>
                                        </select>
                                    </td>
                                    <td align="right">省市</td>
                                    <td>
                                    	<select class="select widthB94" name="areaId" data-validation="required" label="省市">
                                             <c:forEach items="${citys}" var="list" varStatus="status">
                                                <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if> >${list.codeNamec}</option>
                                             </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Email</td>
                                    <td><input class="input" name="email" type="text" maxlength="30" data-validation="isEmail" label="Email"></td>
                                    <td align="right">证号</td>
                                    <td colspan="5">
                                    	<input class="input widthB98" name="crdId" type="text" maxlength="18">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">车牌号</td>
                                    <td><input class="input" name="plateNumber" type="text" maxlength="20"></td>
                                    <td align="right">住址</td>
                                    <td colspan="5">
                                    	<input class="input widthB98" name="addr" type="text" maxlength="50">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">备注</td>
                                    <td colspan="5">
                                    	<input class="input widthB99" name="notice" type="text" maxlength="100">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="floatR userImg">
                        	<img class="" src="../img/user_none2.png">
                            <a class="button_02" href="javascript:;" onclick="showOther()">其他信息</a>
                        </div>
                        <div class="clearBoth"></div>
                        
                </div>
                <input type="text" hidden class="other" style="display: none;" name="visakindId" placeholder="签证类型">
                <input type="text" hidden class="other" style="display: none;" name="visaId" placeholder="签证号码" value="">
                <input type="text" hidden class="other" style="display: none;" name="depart" placeholder="签发机关">
                <input type="text" hidden class="other" style="display: none;" name="inDate" placeholder="入境日期">
                <input type="text" hidden class="other" style="display: none;" name="inPort" placeholder="入境口岸">
                 
                <div class="acquaintanceNumber">
                    <div class="roomNumber margin-top-10">
                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
                            <table width="100%">
                             <tr>
                                    <td width="70" align="right" class="gry_9">熟客编号</td>
                                    <td><input class="input gry_9" id="gstId" name="gstId" type="text" readonly="readonly" ></td>
                                    <td width="70" align="right">常住房类</td>
                                    <td><select class="select widthB95" name="roomTypeid">
                                    	     <option value=""></option>
		                                      <c:forEach items="${roomTypes}" var="list" varStatus="status">
		                                                <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if> >${list.codeNamec}</option>
		                                      </c:forEach>
		                                </select>
                                    </td>
                                    <td width="65" align="right" class="gry_9">上次抵店</td>
                                    <td><input class="input gry_9" name="reachDate" type="text" value="2000-01-01" readonly="readonly"></td>
                                    
                                </tr>
                                <tr>
                                    <td align="right">客人来源</td>
                                    <td>
                                    	<select class="select widthB95" name="gstOriId">
		                                     <c:forEach items="${customSources}" var="list" varStatus="status">
		                                                <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if> >${list.codeNamec}</option>
		                                      </c:forEach>
		                                </select>
                                    </td>
                                    <td align="right" class="gry_9">累计消费</td>
                                    <td><input class="input gry_9" name="" value="0.00" readonly="readonly" type="text"></td>
                                    <td align="right" class="gry_9">上次离店</td>
                                    <td><input class="input gry_9" name="leaveDate" type="text" value="2000-01-02" readonly="readonly"></td>
                                </tr>
                                <tr>
                                    <td align="right">客人分类</td>
                                    <td>
                                    	<select class="select widthB95" name="gstKind">
		                                     <c:forEach items="${customTypes}" var="list" varStatus="status">
		                                                <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if> >${list.codeNamec}</option>
		                                      </c:forEach>
		                                </select>
                                    </td>
                                    <td width="70" align="right" class="gry_9">住房次数</td>
                                    <td><input class="input gry_9" name="" type="text" readonly="readonly" value="0"></td>
                                    <td align="right" class="gry_9">上次房号</td>
                                    <td><input class="input gry_9" name="roomId" type="text" readonly="readonly" value="1001" maxlength="6"></td>
                                </tr>
                                <tr>
                                    <td align="right">合约单位</td>
                                    <td colspan="3">
                                    	<table width="100%">
		                                    <tr>
		                                        <td style="padding:0;">
		                                            <input class="input" id="compUnit" name="namec" type="text" placeholder="合约单位">
		                                            <input style="display: none;" hidden id="compId" name="compId" type="text" placeholder="合约单位ID">
		                                            <input style="display: none;" hidden id="compType" name="compType" type="text" placeholder="合约单位TYPE">
		                                        </td>
		                                        <td width="22" align="left">
		                                            <a class="HYbutton" href="javascript:;" placeholder="查看合约单位详情"></a>
		                                        </td>
		                                    </tr>
		                                </table>
                                    </td>
                                    <td align="right" class="gry_9">上次房价</td>
                                    <td><input class="input gry_9" name="roomPrice" readonly="readonly" type="text" value="0.00"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                     <ul class="payMan margin-top-10">
                    	<li><label><input  name="cityLedger" type="checkbox"  value="0">可挂AR账</label></li>
                        <li><label><input id="housePay"  name="housePay" type="checkbox" checked="checked" value="0">可挂房账</label></li>
                        <li><label><input id="freeSvc" name="freeSvc" type="checkbox" checked="checked" value="0">免服务费</label></li>
                        <li class="margin-bottom-10"><label><input name="hideprice" type="checkbox" value="0">隐藏房价</label></li>
                        <li class="margin-bottom-10 padding-top-10">
                        	<label>
                        	<input id="fjtzChk" class="checkbox floatL" readonly="readonly" onclick="return false;" name="" type="checkbox"  value="0">
                        	<input id="roomCharacter" name="roomCharacter" value="0" data-validation="required" type="text" hidden style="display: none;"/>
                        	 <a class="button_02 floatL" href="javascript:;"  onclick="showKeFangForm()"style="margin-top:-5px ;">客房特征</a>
                        	</label>
                        </li>
                    </ul>
                    
                    <div class="GuestTabL3 margin-top-10">
                    <table width="100%">
                        <tr>
                            <td width="70" align="right">付款方式</td>
                            <td width="150">
                            	<select class="select widthB95" name="payId" data-validation="required" label="付款方式">
                                      <c:forEach items="${payTypes}" var="list" varStatus="status">
                                                <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if> >${list.codeNamec}</option>
                                      </c:forEach>
                                </select>
                            </td>
                            <td width="70" align="right">开户行</td>
                            <td width="150">
                            	<input class="input" name="bankId" type="text" maxlength="20">
                            </td>
                            <td width="70" align="right" class="gry_9">会员卡号</td>
                            <td><input class="input gry_9" name="" type="text" readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td align="right">信用限额</td>
                            <td>
                            	<input class="input" id="limit" name="limit" type="text" onchange="limitConvert()"  data-inputmask="'alias': '9{0,10}.9{0,2}'" data-validation="required" label="信用限额">
                            </td>
                            <td align="right">信用卡号</td>
                            <td><input class="input" id="creditId" name="creditId" type="text" data-inputmask="'alias': '9999 9999 9999 9999 9{0,3}','placeholder':''"></td>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="right">AR帐号</td>
                            <td>
                            	<input class="input" name="arNum" type="text" maxlength="30" data-inputmask="'alias': '9{0,20}'">
                            </td>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="right">担保人</td>
                            <td>
                            	<input class="input" name="guarantor" type="text" data-validation="isHanZi" label="担保人" maxlength="30">
                            </td>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </table>
                </div>  
                
                <!--熟客编号-->
                </div>
                </form>
                <!--/熟客编号-->
                <!--右侧部分-->
                <div class="GuestTabR GTabPos posA">
                    <a class="button_02 group1" href="javascript:;" jack="formSubmit()" onclick="formSubmit()">确定</a>
                    <a class="button_02 group1" href="javascript:;" jack="formGiveUp()" onclick="formGiveUp()">放弃</a>
                    <a class="button_02 group2" href="javascript:;" jack="formDel()" onclick="formDel()">取消</a>
                    <a class="button_02 group2" href="javascript:;">查看业绩</a>
                    <a class="button_02 group2" href="javascript:;">会员</a>
                    <a class="button_02 group2" href="javascript:;">特别提示</a>
                    <a class="button_02 group2" href="javascript:;" jack="closeForm()" onclick="closeForm()">退出</a>
                </div>
                <!--/右侧部分-->
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/入住登记-->
<!--其他信息弹出框-->
<div class="alertDiv moveBar2 alertDiv2 otherDiv">
	<div class="alertMain greyBg" style="width:830px;margin-top:150px;">
    	<h4 class="moveDivAlert2">其他信息<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--分账-->
        	<table width="100%">
            	<tr>
                	<td width="35%" valign="top">
                    	<h5 class="margin-left-5 fontWeight">外宾登记信息</h5>
                    	<form id="otherForm">
                    	<div class="splitDiv margin-top-5">
                        	<dl class="inputDiv2 margin-top-none margin-bottom-20">
                                <dt>英文名</dt>
                                <dd>
                                    <input class="input widthB99" name="gstNamee" type="text" placeholder="">
                                </dd>
                                <dt>性别</dt>
                                <dd>
                                    <select class="select widthB99" name="sex">
                                          <c:forEach items="${sexs}" var="list" varStatus="status">
                                             <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if>>${list.codeNamec}</option>
                                          </c:forEach>
                                    </select>
                                </dd>
                                <dt>国籍</dt>
                                <dd>
                                  <select class="select widthB99" name="ntId" data-validation="required" label="国籍">
                                             <c:forEach items="${countrys}" var="list" varStatus="status">
                                             <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if>>${list.codeNamec}</option>
                                             </c:forEach>
                                        </select>
                                </dd>
                                <dt>证件类型</dt>
                                <dd>
                                    <select class="select widthB99" name="crdkindId" data-validation="required" label="证件">
                                             <c:forEach items="${cardTypes}" var="list" varStatus="status">
                                                <option value="${list.codeId}" <c:if test="status.index==0"> selected</c:if> >${list.codeNamec}</option>
                                             </c:forEach>
                                    </select>
                                </dd>
                                <dt>证件号码</dt>
                                <dd>
                                    <input class="input widthB99" name="crdId" type="text" maxlength="20">
                                </dd>
                                
                                <dt>签证类型</dt>
                                <dd>
                                    <select class="select widthB99" name="visakindId">
                                    <option value=""></option>
                                         <c:forEach items="${visakindIds}" var="list" varStatus="status">
                                             <option value="${list.codeId}" >${list.codeNamec}</option>
                                         </c:forEach>
                                    </select>
                                </dd>
                                <dt>签证号码</dt>
                                <dd>
                                    <input class="input widthB99" name="visaId" type="text" placeholder="">
                                </dd>
                                <dt>签证机关</dt>
                                <dd>
                                    <select class="select widthB99" name="depart">
                                    <option value=""></option>
                                         <c:forEach items="${departs}" var="list" varStatus="status">
                                             <option value="${list.codeId}" >${list.codeNamec}</option>
                                         </c:forEach>
                                    </select>
                                </dd>
                                <dt>有效日期</dt>
                                <dd>
                                    <input class="input widthB99" name="" onclick="WdatePicker({dchanged:otherinputListener,Mchanged:otherinputListener,ychanged:otherinputListener})" type="text" placeholder="">
                                </dd>
                                <dt>入境日期</dt>
                                <dd>
                                    <input class="input widthB99" name="inDate" onclick="WdatePicker({dchanged:otherinputListener,Mchanged:otherinputListener,ychanged:otherinputListener})" type="text" placeholder="">
                                </dd>
                                <dt>入境口岸</dt>
                                <dd>
                                    <select class="select widthB99" name="inPort">
                                         <option value=""></option>
                                         <c:forEach items="${inPorts}" var="list" varStatus="status">
                                             <option value="${list.codeId}" >${list.codeNamec}</option>
                                         </c:forEach>
                                    </select>
                                </dd>
                                <dt>接待单位</dt>
                                <dd>
                                    <input class="input widthB99" name="" type="text" placeholder="">
                                </dd>
                                <div class="clearBoth"></div>
                            </dl>
                            <div class="clearBoth"></div>
                           
                        </div> </form>
                    </td>
                    <td valign="top">
                    <!--外宾登记信息Right-->
                    	<h5 class="margin-left-5 fontWeight">客人签名</h5>
                        <div class="textarea margin-top-5 height200">
                        
                        </div>
                        <form id="otherForm" action="">
                        <table width="100%" class="margin-top-30 gry_9" >
                            <tr>
                                <td width="25%" align="right">登记人</td>
                                <td width="25%"><input class="input" name="operId"   readonly disabled type="text" placeholder=""></td>
                                <td width="18%" align="right">操作时间</td>
                                <td width="32%"><input class="input" name="operTime" readonly disabled type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">最后修改人</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                                <td align="right">操作时间</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">退房人</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                                <td align="right">操作时间</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">恢复入住人</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                                <td align="right">操作时间</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                            </tr>
                            <tr>
                                <td align="right">最后账目处理人</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                                <td align="right">操作时间</td>
                                <td><input class="input" name="" readonly disabled type="text"></td>
                            </tr>
                        </table>
                        </form>
                        <!--外宾登记信息Right  END-->
                    </td>
                </tr>
            </table>
            <!--分账-->
            
            <div class="alertRight clearBoth margin-top-30 otherOperCls">
               <!--  <a class="buttonLikeA floatR margin-right-10 group2" id="cloaseOther" href="javascript:;" jack="cloaseOther()" onclick="cloaseOther()">退出</a>
               <a class="buttonLikeA floatR margin-right-10 group1" id="formGiveUpOtherForm" href="javascript:;" jack="formGiveUpOtherForm()" onclick="formGiveUpOtherForm()">放弃</a>-->
               <a class="buttonLikeA floatR margin-right-10" id="confrimOhterForm" href="javascript:;" jack="confrimOhterForm()" onclick="confrimOhterForm()">关闭</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/其他信息弹出框-->
<!--特征ALERT-->
<div class="alertDiv moveBar2 alertDiv2 featuresButton">
	<div class="alertMain" style="width:500px; margin-top:225px;">
    	<h4 class="moveDivAlert2">房间特征筛选<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid fangJainFeatures">
        	<div class="roomButtonFblock">
            	<ul class="FTsx">
                    <c:forEach items="${hroomCharacters}" var="list" varStatus="status">
                                             
                         <li><label><input class="checkbox margin-right-5 sonChk" name="roomCharacter" type="checkbox" value="${list.placeholderId}">${list.codeNamec}</label></li>
                    </c:forEach>
                </ul>
             </div>
             <table width="460">
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

</body>
<script src="${ctx}/scripts/jquery-migrate-1.2.1.js" type="text/javascript"></script>
<script src="${ctx}/js/lib/autocomplete/jquery.autocomplete.min.js"></script>
<script src="${ctx}/scripts/jquery.inputmask.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/marketing/fguest.js"></script>

</html>