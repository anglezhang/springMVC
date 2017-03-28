<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.css">
<title>房间管理</title>
</head>
<style type="text/css">
.searchDiv{display:none;width:200px;height:150px;background:#fff; position: absolute;top:30px; left:0;z-index: 999;overflow-y:scroll;border:solid 1px #ccc;}
.searchDiv li{width:95%; height: 30px; line-height: 30px; padding-left: 5%; cursor: pointer;}
.searchDiv li:hover{background:green;color:#fff;}
.hyunit {
  width:84% !important;
}
</style>
<body class="bodyAll">
<!--banner & menu  xingli  2015-09-08-->
<%@ include file="../header.jsp" %>
<!--secondMenu-->
<div class="secondMenuDiv">
	<ul class="secondMenu">
        <li><a class="thisSecMenu" href="/noguest/noguestIndex.do">非住客资料</a></li>
    </ul>
</div>
<!--secondMenu -end- -->
<!--banner & menu -END -->
<!--center xingli 2015-09-08-->
<div class="center">
    <!--mainWeb-->
    <div class="main" id="webMain">
    	<!--mainInformation-->
        <div class="mainInfo2" style="overflow: hidden;">
        <!--table-->
            	<div class="tableDiv" id="noguestdiv" style="height: 100%" >
                </div>
                <!--table -END- -->
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<div class="rightMenu">
        	<div class="roomStatus">                
                <div class="choice padding-bottom-10">
                    <form class="bookRight" id="noguestform" method="post">
                          <div class="clearBoth"></div>
                           <br />
                           <br />
                        <dl class="inputDiv2 margin-top-none" style="width:96%;">
                            <dt>非住客简称</dt>
                            <dd>
                                <input class="input" type="text" placeholder="" maxlength="10"  name="nogstId" id="nogstId">
                            </dd>
                            <dt>非住客全称</dt>
                            <dd>
                                <input class="input" type="text" placeholder="" maxlength="20" name="nogstName" id="nogstName">
                            </dd>
                            <!--  <dt>合约单位</dt>
                            <dd>
                                <input class="input" type="text" placeholder="" name="compId" id="company_id" value="">
                                <div id="no_guest_comp_type_compId"></div>
                            </dd>-->
                            <dt>联系人</dt>
                            <dd>
                                <input class="input" type="text" placeholder="" maxlength="10" name="connEctor" id="connEctor">
                            </dd>
                            <dt>创建日期从</dt>
                            <dd>
                               <table width="97%">
                                    <tr>
                                        <td><input class="input" onclick="WdatePicker({maxDate:'%y-%M-%d'});" id="creaTimeStart" name="creaTimeStart" type="text"></td>
                                        <td align="center" width="30">到</td>
                                        <td><input class="input" onclick="WdatePicker({maxDate:'%y-%M-%d'});"   id="creaTimeEnd" name="creaTimeEnd" type="text"></td>
                                    </tr>
                                </table>
                            </dd>
                            <dt>付款期限从</dt>
                            <dd>
                                <table width="97%">
                                    <tr>
                                        <td><input class="input" data-inputmask="'alias': 'yyyy-mm-dd'" id="payDateStart" name="payDateStart" type="text"></td>
                                        <td align="center" width="30">到</td>
                                        <td><input class="input" data-inputmask="'alias': 'yyyy-mm-dd'" id="payDateEnd" name="payDateEnd" type="text"></td>
                                    </tr>
                                </table>
                            </dd>
                            <dt>账号</dt>
                            <dd>
                                <input class="input" type="text" placeholder="" id="billId"  name="billId" data-inputmask="'mask': '9', 'repeat': 20, 'greedy' : false" />
                            </dd>
                            <dt>&nbsp;</dt>
                            <dd>
                                <label class="margin-right-5"><input class="checkbox margin-right-5" id="hotelFlag" name="hotelFlag" type="checkbox" value="true">酒店自用</label>
                            </dd>
                        </dl>
                        <div class="clearBoth"></div>
                        <br />
                        <table width="90%" class="margin-left-10 margin-top-20">
                            <tr>
                                <td align="right"><label  class="margin-right-10"><input name="rstatus" type="radio" id="effective" value="0"><span class="margin-left-5">有效</span></label></td>
                                <td align="left"><label class="margin-left-10"><input name="rstatus" type="radio" id="uneffective" value="1"><span class="margin-left-5">无效</span></label></td>
                            </tr>
                        </table>
                        </form>
                        <div class="clearBoth"></div>
                        <br />
                        <div class="cabDivNoneHei clearBoth padding-top-15">
                        	<a class="button_07 floatL" href="javascript:;" onclick="doSearch();">条件查询</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="clearCond();">清空条件</a>
                            <a id="nonResident" class="button_07 floatL" href="javascript:;" onclick="nogeustdetail();">非住客详情</a>
                            <a class="button_07 floatL" href="javascript:;" <mammoth:AuthJudge funcId="c_588903">onclick="addGuest();"</mammoth:AuthJudge>>新增</a>
                        </div>
                         <div class="clearBoth"></div>
                        <br />
                        <!--小键盘-->
                        <div class="cabDiv clearBoth">
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
<!--非住客资料-->
<div class="alertDiv checkInDiv moveBar nonResidentDiv" id="nonResidentDiv">
	<div class="alertMain greyBg rzLogin" style="margin-top:120px;">
		<input type="hidden" id="typeData" name="typeData" value=""/>
    	<h4 class="moveDivAlert" id="MoveAlertDiv"><span id="titleId">非住客维护</span><a href="javascript:;" class="closeDiv hideDivOne"></a></h4>
        <div class="borderSolid">
        <form action="" id="noguestData">
            <ul class="GuestTab clearBoth margin-top-10">
            	<li class="point userTab1">非住客详情</li>
                <li class="userTab2">非住客账目</li>
            </ul>
            <div class="clearBoth GuestTabIn userDetails">
            	<div class="GuestTabL">
                	<!--非住客资料-->
                    <div class="roomNumber margin-top-20 positionR" style="width:99%;">
                    <label style="position:absolute;right:0;top:-60px;"><input class="checkbox" id="noguestStatus" name="status" type="checkbox" checked disabled value="0" onclick="return false;">有效</label>
                    	<div class="positionA divIndexTopWord">非住客资料</div>
                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
                        	
                            <table width="99%" class="margin-top-20">
                                <tr>
                                    <td width="70" align="right">简称</td>
                                    <td width="230"><input class="input" type="text" name="nogstId" data-validation="required" id="nogst_id"  label="简称" value="" maxlength="10"></td>
                                    <td align="right">手机</td>
                                    <td><input class="input" type="text" maxlength="50" name="mobile" id="mobile" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
                                    <td width="80" align="right">传真</td>
                                    <td><input class="input" type="text"  name="fax" id="fax" value=""></td>
                                </tr>
                                <tr>
                                    <td align="right">全称</td>
                                    <td><input class="input" type="text" name="nogstName" id="nogst_name" value="" data-validation="required" label="全称" maxlength="20"/></td>
                                    <td width="80" align="right">固话</td>
                                    <td width="150"><input class="input" type="text" name="phone" id="phone" value=""></td>
                                    <td align="right">Email</td>
                                    <td><input class="input" type="text" name="email" id="email" data-validation="isEmail" label="Email" value="" maxlength="30"></td>
                                </tr>
                                <tr>
                                    <td align="right">合约单位</td>
                                    <td>
                                    	<input class="input hyunit" id="theCompany" name="unitNamec" type="text" data-validation="required" label="合约单位">
                                  		<input type="hidden" id="company_id" name ="compId" value="" data-validation="required" label="合约单位">
                                    	<input type="hidden" id="ta_type" name ="compType" value="" data-validation="required" label="合约单位">
                                        <a class="HYbutton" href="javascript:;"></a>
                                    </td>
                                    <td align="right">联系人</td>
                                    <td><input class="input" type="text" name="connector" id="connector" value="" maxlength="10"></td>
                                    <td>&nbsp;</td>
                                    <td  align="left">
                                    	<label><input class="checkbox margin-right-5 padding-right-5" type="checkbox"  name="hotelFlag" id="hotel_flag" value="true">酒店自用</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td align="right">备注</td>
                                	<td colspan="5"><input class="input" type="text" id="notes" name="notes" maxlength="100" /></td>
                                </tr>
                                
                            </table>
                           
                        </div>
                        
                    </div>
                    <!--/非住客资料-->
                    <!--合约资料-->
                    <div class="roomNumber margin-top-20 positionR" style="width:99%;">
                    	<div class="positionA divIndexTopWord">账目资料</div>
                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
                            <table width="99%" class="margin-top-20">
                                <tr>
                                	<td width="120" align="right">付款截止日期</td>
                                    <td width="200"><input class="input" name="payDate" data-validation="required,simpleDate" label="付款截止日期" type="text"  id="payDate"></td>
                                    <td width="80" align="right">开户行</td>
                                    <td><input class="input" name="bankaddr" type="text" id="bankaddr"  maxlength="20" /></td>
                                </tr>
                                <tr>
                                	<td align="right">付款方式</td>
                                    <td>
                                    	<select class="select widthB99" id="pay_id" name="payId" data-validation="required" label="付款方式"> 
                                    		<c:forEach items="${listPay}" var="list">
                                             <option value="${list.codeId}">${list.codeNamec}</option>
                                             </c:forEach>
                                   		</select>
                                    </td>
                                    <td align="right">信用卡号</td>
                                    <td><input class="input"   type="text" id="theBankno" >
                                        <input  id="bankno" name="bankno" type="hidden"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">信用限额</td>
                                    <td><input class="input" name="limit" type="text" id="limit" data-validation="required" label="信用限额"></td>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!--/合约资料-->
                    <div class="roomNumber margin-top-20 positionR borderNone" style="width:99%;">
                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock gry_9">
                            <table width="99%" class="margin-top-20">
                                <tr>
                                    <td width="120" align="right">创建人</td>
                                    <td width="130"><input class="input" name="creaId" type="text" disabled> </td>
                                    <td width="80" align="right">操作时间</td>
                                    <td width="130"><input class="input" name="creaTime" type="text" disabled></td>
                                    <td width="80" align="right">账号</td>
                                    <td><input class="input gry_9 widthB70" name="" type="text" disabled></td>
                                </tr>
                                <tr>
                                    <td align="right">取消人</td>
                                    <td><input class="input" name="" type="text" disabled></td>
                                    <td align="right">操作时间</td>
                                    <td><input class="input gry_9" name="" type="text" disabled></td>
                                    <td align="right">总额</td>
                                    <td><input class="input gry_9 widthB70" name="" type="text" disabled></td>
                                </tr>
                                <tr>
                                    <td align="right">最后账目处理人</td>
                                    <td><input class="input" name="" type="text" disabled></td>
                                    <td align="right">操作时间</td>
                                    <td><input class="input gry_9" name="" type="text" disabled></td>
                                    <td align="right">已付</td>
                                    <td><input class="input gry_9 widthB70" name="" type="text" disabled></td>
                                </tr>
                                <tr>
                                    <td align="right">最后修改人</td>
                                    <td><input class="input" name="modiId" type="text" disabled></td>
                                    <td align="right">操作时间</td>
                                    <td><input class="input gry_9" name="modiTime" type="text" disabled></td>
                                    <td align="right">余额</td>
                                    <td><input class="input gry_9 widthB70" name="" type="text" disabled></td>
                                </tr>
                                <tr>
                                    <td align="right">修改次数</td>
                                    <td><input class="input" name="updateTimes" type="text" disabled></td>
                                    <td align="right" colspan="4">&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <!--右侧部分-->
                <div class="GuestTabR">
                    <a class="button_03 margin-top-none <mammoth:AuthJudge funcId="c_101840">group1</mammoth:AuthJudge>" jack='saveData()' href="javascript:;" <mammoth:AuthJudge funcId="c_101840">onclick="saveData();"</mammoth:AuthJudge>>确定</a>
                    <a class="button_03 <mammoth:AuthJudge funcId="c_101840">group1</mammoth:AuthJudge>" href="javascript:;" jack="giveUp()" <mammoth:AuthJudge funcId="c_101840">onclick="giveUp();"</mammoth:AuthJudge>>放弃</a>
                    <a id="theDel" class="button_03 <mammoth:AuthJudge funcId="c_101840">group2</mammoth:AuthJudge>" href="javascript:;" jack="delNoguest()" <mammoth:AuthJudge funcId="c_101840">onclick="delNoguest();"</mammoth:AuthJudge>>取消</a>
                    <a class="button_03 group2" href="javascript:;" jack='exportNoguest()' onclick="exportNoguest();">退出</a>
                </div>
                <!--/右侧部分-->
                
                	<input type="hidden" id="id" name="id" value=""/>
                </form>
            </div>
            <!--userCatalog-->
            
            <div class="clearBoth GuestTabIn userCatalog" style="display:none;">
            <form id="noguestAccountForm">
            	<div class="GuestTabL">
                	<input id="noguest_account_ID" class="input gry_9" name="id"  type="hidden">
                    <!--房间编号-->
                    <div class="roomNumber margin-top-10" style="width:99%;">
                        <div class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
                            <table id="cp_tab" class='cp_tab' width="99%">
                                <tr>
                                    <td width="70" align="right">简称</td>
                                    <td width="160"><input id="f_nogstId" class="input gry_9" name="nogstId" type="text" readonly="readonly"></td>
                                    <td width="70" align="right">合约单位</td>
                                    <td width="160">
                                    	<table width="100%">
                                            <tr>
                                                <td style="padding:0;"><input id="f_namec" class="input gry_9" readonly="readonly" name="unitNamec" type="text"></td>
                                                <td width="28" align="right">
                                                    <a class="SFbutton floatR marginRight-4" href="javascript:;" readonly="readonly" disabled="disabled">···</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="70" align="right"><span class="red">付款方式</span></td>
                                    <td><input id="f_payId" class="input gry_9 widthB97" readonly="readonly" name="payName" type="text"></td>
                                </tr>
                                <tr>
                                    <td align="right">全称</td>
                                    <td><input id="f_nogstName" class="input gry_9" readonly="readonly" name="nogstName" type="text"></td>
                                    <td align="right">开户银行</td>
                                    <td><input id="f_bankaddr" class="input gry_9" name="bankaddr" type="text"></td>
                                    <td align="right"><span class="red">信用限额</span></td>
                                    <td><input id="f_limit" class="input gry_9 widthB97" readonly="readonly" name="nLimit" type="text"></td>
                                </tr>
                                <tr>
                                    <td align="right">&nbsp;</td>
                                    <td>
                                    	<label class="margin-right-5"><input class="checkbox margin-right-5" name="" type="checkbox" value="" readonly="readonly" disabled="disabled">酒店自用</label>
                                    	<label class="margin-right-5"><input class="checkbox margin-right-5" name="" type="checkbox" value="" readonly="readonly" disabled="disabled">有效</label>
                                    </td>
                                    <td align="right">信用卡号</td>
                                    <td><input id="f_bankno" class="input gry_9" readonly="readonly" name="bankno" type="text"></td>
                                    <td align="right"><span class="red">付款期限</span></td>
                                    <td><input class="input gry_9 widthB97" readonly="readonly" name="payDate" type="text"></td>
                                </tr>
                                <tr>
                                    <td align="right">备注</td>
                                    <td colspan="5"><input id="f_notes" readonly="readonly" class="input gry_9 widthB99" name="notes" type="text"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <!--/房间编号-->
                    
                    <div class="roomNumber" style="width:99%;">
                        <div class="tableDivDow clearBoth floatL widthAllBlock padding-bottom-5">
                            <table width="99%">
                                <tr>
                                    <td width="70" align="right">账号</td>
                                    <td width="110"><input class="input" name="billId" type="text" id="noguest_billid" disabled="disabled"></td>
                                    <td width="70" align="right">总额</td>
                                    <td width="110"><input class="input textAlignRight" name="nBorrow" type="text" id="noguest_borrow" disabled="disabled"></td>
                                    <td width="70" align="right">已付</td>
                                    <td width="110"><input class="input textAlignRight" name="nLent" type="text" id="noguest_lent" disabled="disabled"></td>
                                    <td width="70" align="right">余额</td>
                                    <td><input class="input textAlignRight" name="nRemain" type="text" id="noguest_remain" disabled="disabled"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    </form>
                    <div class="tableDivDow clearBoth floatL widthAllBlock">
                    <table width="100%">
                        <tr>
		                    <td width="470">&nbsp;</td>
		                    <td width="70">
		                       <label><input class="radio" name="radioType" type="radio" value="A" checked="checked">明细</label>
		                    </td>
		                    <td width="70">
		                       <label><input class="radio" name="radioType" type="radio" value="B">汇总</label>
		                    </td>
		                    <td width="70">
		                       <label><input class="radio" name="radioType" type="radio" value="C">归类</label>
		                    </td>
                        </tr>
                    </table>
                    </div>
                    <!--table-->
                        <div class="tableDiv floatL" style="width:100%;margin-left:1px;height:310px" id="noguestAccountGrid">
                        </div>
                    <div class="clearBoth"></div>
                    	
                    
                    <div class="tableDivDow clearBoth floatL widthAllBlock margin-bottom-10">
                    <table width="98%">
                        <tr>
                           	<td align="right" width="35">从</td>
							<td width="85"><input class="input" name="" id="account_startDate" type="text" onclick="WdatePicker()">
							</td>
							<td align="right" width="35">到</td>
							<td width="85"><input class="input" name="" id="account_endDate" type="text"  onclick="WdatePicker()">
							</td>
                            <td width="190">
                               <label><input class="radio" name="disturb" type="radio" value="N" checked="checked">未结</label>
                               <label><input class="radio margin-left-10" name="disturb" type="radio" value="Y">已结</label>
                               <label><input class="radio margin-left-10" name="disturb" type="radio" value="A">全部</label>
                            </td>
                            <td width="80"><label><input class="checkbox margin-right-5" name="" id="isInvalid" type="checkbox" value="">无效单</label></td>
                            <td align="right">
                               <!--  <a class="button_02" href="javascript:;">拆分账目</a>-->
                            </td>
                        </tr>
                    </table>
                    </div>
                </div>
                <!--右侧部分-->
                
                <div class="GuestTabR">
                    	<a class="button_03" href="javascript:;" id="nogst_accrefresh_btn">刷新(R)</a>
                        <a class="button_03 margin-top-none" href="javascript:;" id="receivables" <mammoth:AuthJudge funcId="c_101840"></mammoth:AuthJudge>>收款(S)</a>
                        <a class="button_03" href="javascript:;" id="accountedFor" <mammoth:AuthJudge funcId="c_101840"></mammoth:AuthJudge>>入账(I)</a>
                        <a class="button_03" href="javascript:;" id="cancleBill"<mammoth:AuthJudge funcId="c_101840"></mammoth:AuthJudge>>取消(C)</a>
                        <a class="button_03" href="javascript:;" id="transferAccounts"<mammoth:AuthJudge funcId="c_101840"></mammoth:AuthJudge>>转账(M)</a>
                        <a class="button_03" href="javascript:;" id="checkOut"<mammoth:AuthJudge funcId="c_101840"></mammoth:AuthJudge>>结账(J)</a>
                        <!--  <a class="button_03" href="javascript:;">改单(E)</a>-->
                        <a class="button_03" href="javascript:;" id="noguest_bill_contract">合同(B)</a>
                        <a class="button_03" href="javascript:;" id="noguest_bill_print">打印(P)</a>
                        <a class="button_03" href="javascript:;" id="noguestAccountsQuit">退出(X)</a>
                </div>
                <!--/右侧部分-->
            </div>
            <!--/userCatalog-->
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/住客资料-->
<!--收款弹出框-->
<div class="alertDiv moveBar2 alertDiv2 receivablesDiv" id="receivablesDiv">
	<div class="alertMain greyBg" style="width:560px; margin-top:240px;">
    	<h4 class="moveDivAlert2">收款<a href="javascript:;" class="closeDiv2 closeAlert" id="receivablesClose"></a></h4>
        <div class="borderSolid">
        	<form id="receivablesForm">
        	<!--押金-->
        	<table width="95%">
            	<tr>
                	<td align="right"><span class="red">单号</span></td>
                    <td><input class="input" name="accoId" type="text" id="receivablesAcco" data-validation="required,isNum" label="单号"></td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                	<td width="20%" align="right">收款方式</td>
                    <td>
                    	<select class="select widthB99" name="setlId" id="receivablesSettle" >
                    		<c:forEach items="${hsettls}" var="hcode">
									<option value="${fn:trim(hcode.codeId)}" data-moneyid = "${fn:trim(hcode.moneyId)}">${hcode.codeNamec}</option>
							</c:forEach>
                        </select>
                    </td>
                    <td width="20%" align="right">币种</td>
                    <td>
                    	<select class="select widthB99" name="moneyKind" id="receivablesMoneyKind" disabled="disabled">
	                    	<c:forEach items="${exchanges}" var="hcode">
								<option value="${fn:trim(hcode.codeId)}">${hcode.codeNamec}</option>
							</c:forEach>
						</select>
                    </td>
                </tr>
                <tr>
                	<td align="right"><span class="red">本位币金额</span></td>
                    <td><input class="input" name="balance" type="text" id="receivablesBalance"  data-validation="required,positiveMoney" label="本位币金额"></td>
                    <td align="right"><span class="red">外币金额</span></td>
                    <td><input class="input" name="foreignm" type="text" data-validation="required,positiveMoney"></td>
                </tr>
                <tr>
                	<td align="right">备注</td>
                    <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                </tr>
            </table>
            <!--押金-->
            </form>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;"  id="receivesQuit">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="receivesCancle">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="receivesSubmit">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
	</div>
	<!--/收款弹出框-->
<!--入账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 accountedForDiv" id="accountedForDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">入账<a href="javascript:;" class="closeDiv2 closeAlert" id="accountClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
        	<form id="accountForm">
        		<input class="input" name="nLimit" type="hidden" id="nLimit" disabled="disabled">
        		<input class="input" name="nRemain" type="hidden" id="nRemain" disabled="disabled">
                <div class="tableDiv floatL margin-left-10 widthB70 margin-top-10 padding-10" style="width:450px">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right">账号</td>
                            <td width="30%"><input class="input" name="billId" type="text" id="accountBillId" disabled="disabled"></td>
                            <td width="20%" align="right">可用金额</td>
                            <td><input class="input" style="text-align:right" name="" type="text" id="accountlimit" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <td align="right">简称</td>
                            <td><input class="input" name="nogstId" type="text" id="accountBillId" disabled="disabled"></td>
                            <td align="right">账单号</td>
                            <td><input class="input" name="accoId" id="accountAcco" type="text" data-validation="required,isNum" label="账单号"></td>
                        </tr>
                        <tr>
                            <td align="right">全称</td>
                            <td>
                            	<input class="input" name="nogstName" type="text" id="accountBillId" disabled="disabled">
                            </td>
                            <td align="right">消费点</td>
                            <td>
                            	<select class="select widthB100" id="accountConsume" name="consId" >
                            		<c:forEach items="${consumes}" var="hcode">
										<option value="${fn:trim(hcode.codeId)}" data-moneyid = "${fn:trim(hcode.moneyId)}" data-svcRate="${fn:trim(hcode.svcRate)}">${hcode.codeNamec}</option>
									</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right"></td>
                            <td>
                            </td>
                            <td align="right">币种</td>
                            <td>
                            	<select class="select widthB100" id="accountExchange" name="moneyKind" disabled="disabled">
                            		<c:forEach items="${exchanges}" var="hcode">
										<option value="${fn:trim(hcode.codeId)}" <c:if test="${fn:trim(hcode.codeId)==moneyId}">selected</c:if> >${hcode.codeNamec}</option>
									</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">&nbsp;</td>
                            <td><label><input class="checkbox margin-right-5" name="" type="checkbox" id="isserve">收服务费</label></td>
                            <td align="right">本位币</td>
                            <td><input class="input" name="balance" type="text" id="accountBalance"  data-validation="required,positiveMoney" label="本位币金额"></td>
                        </tr>
                        <tr>
                            <td align="right">服务费</td>
                            <td><input class="input" name="svcCharge" id="serveMoney" type="text" disabled="disabled"></td>
                            <td align="right">外币金额</td>
                            <td><input class="input" name="foreignm" type="text" id="accountForeignm" data-validation="required,positiveMoney"></td>
                        </tr>
                        <tr>
                            <td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="notes" id="accountNotes" type="text"></td>
                        </tr>
                    </table>
                    <!--table enner -END- -->
                </div>
                <ul class="floatL margin-left-40 margin-top-10" >
                	<li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="accountForSubmit">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="accountFormReset" id="accountForReset">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"  id="accountForQuit">退出</a></li>
                </ul>
                </form>
                <!--table -END- -->
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/入账弹出框-->
<!--结账打单弹出框-->
<div class="alertDiv moveBar2 alertDiv2 checkOutDiv" id="checkOutDiv">
	<div class="alertMain greyBg" style="width:760px; margin-top:150px;">
    	<h4 class="moveDivAlert2">结账打单<a href="javascript:;" class="closeDiv2 closeAlert" id="checkOutClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB80 floatL">
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 " style="height:100px;" id="checkOutGrid">
                    </div>
                    <!--/table-->
                    <form id="checkOutForm">
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10" >
                    	<table width="100%">
                            <tr>
                                <td width="20%" align="right">结账方式</td>
                                <td width="30%">
                                	<select class="select widthB100" id="checkOutSetlId" name="checkOutSetlId">
                                		<c:forEach items="${hsettls}" var="hcode">
											<option value="${fn:trim(hcode.codeId)}" data-moneyid = "${fn:trim(hcode.moneyId)}">${hcode.codeNamec}</option>
										</c:forEach>
                                    </select>
                                </td>
                                <td width="20%" align="right">AR账号</td>
                                <td><input class="input" name="ARbillId" type="text" id="ARbillId"></td>
                            </tr>
                            <tr>
                                <td align="right">币种</td>
                                <td>
                                	<select class="select widthB100" id="checkOutmoneyKind" name="checkOutmoneyKind" disabled="disabled">
                                         <c:forEach items="${exchanges}" var="hcode">
											<option value="${fn:trim(hcode.codeId)}" >${hcode.codeNamec}</option>
										</c:forEach>
                                    </select>
                                </td>
                                <td align="right">结账余额</td>
                                <td><input class="input" name="checkOutRemain" type="text" id="checkOutRemain" ></td>
                            </tr>
                            <tr>
                                <td align="right">已结金额</td>
                                <td><input class="input" name="checkOutPaidMoney" type="text" id="checkOutPaidMoney" disabled="disabled"> </td>
                                <td align="right">本位币余额</td>
                                <td><input class="input" name="checkOutBalance" type="text" id="checkOutBalance" disabled="disabled"></td>
                            </tr>
                            <tr>
                                <td align="right">结账零头</td>
                                <td><input class="input" name="checkOutOddment" type="text" id="checkOutOddment"> </td>
                                <td align="right"><span class="red">本位币合计</span></td>
                                <td><input class="input" name="checkOutSumBalance" type="text" id="checkOutSumBalance" disabled="disabled"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td width="20%" align="right"><span class="red">外币实付</span></td>
                                <td width="30%"><input class="input" name="" type="text"></td>
                                <td width="20%" align="right"><span class="red">本位币实付</span></td>
                                <td><input class="input" name="" type="text" id="checkOutHadPaid" disabled="disabled"></td>
                            </tr>
                            <tr>
                                <td align="right">外币小费</td>
                                <td><input class="input" name="" type="text"></td>
                                <td align="right">本位币小费</td>
                                <td><input class="input" name="" type="text" id="checkOutTipMoney" disabled="disabled"></td>
                            </tr>
                            <tr>
                                <td align="right">结账备注</td>
                                <td colspan="3"><input class="input widthB98" name="" id="checkOutNotes" type="text"> </td>
                            </tr>
                        </table>
                    </div>
                    </form>
                </div>
                
                
                <ul class="floatL margin-left-20 margin-top-5">
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"  id="checkOutSubmit"> 确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"  id="checkOutCancle">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="checkOutPrint">打单</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"  id="checkOutQuit">退出</a></li>
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="checkOut_print" type="radio" value="1" checked="checked">打印订单</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="checkOut_print" type="radio" value="2">打印电话</label></li>
<!--                     <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">所有房间</label></li> -->
                    <li class="margin-top-15" style="border:solid 1px #999;padding:5px;">
                    	<label class="displayBlock"><input class="radio margin-right-5" name="checkOut_language" type="radio" value="1" checked="checked">中文单</label>
                        <label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="checkOut_language" type="radio" value="2">英文单</label>
                    </li>
                    <li style="border:solid 1px #999;padding:5px;border-top:none;">
                    	<label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="checkOut_type" type="radio" value="1" checked="checked">当前账页</label>
                        <label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="checkOut_type" type="radio" value="2">部分账页</label>
                        <label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="checkOut_type" type="radio" value="3">全部账页</label>
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
<div class="alertDiv moveBar2 alertDiv2 transferAccountsDiv" id="transferAccountsDiv">
	<div class="alertMain greyBg" style="width:750px; margin-top:50px;">
    	<h4 class="moveDivAlert2">转账<a href="javascript:;" class="closeDiv2 closeAlert" id="transferAccountsClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
        		<div>
        			自：非住客（<span id="transferGuestInfo"></span>）转出账目，<span id="transferInInfo"></span>
        		</div>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="transferAccountGrid" style="height:300px;width:95%">
                </div>
                <!--table -END- -->
                <ul class="widthB96 margin-left-10 padding-top-10 clearBoth">
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5 guest" name="transferRadio_1" type="radio" value="1" checked="checked">个人</label></li>
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5 guest" name="transferRadio_1" type="radio" value="2">团体</label></li>
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5 noGuest" name="transferRadio_1" type="radio" value="3">非住客</label></li>
                    <li class="floatR" style="margin-right:18px">
                    	<table width="150">
                        	<tr>
                            	<td width="50" align="right">房号</td>
                                <td><input class="input" name="" type="text" id="transferRoomId" ></td>
                            </tr>
                        </table>
                    </li>
                    <li class="floatR margin-right-20">
                    	<table width="150">
                        	<tr>
                            	<td width="50" align="right">账号</td>
                                <td><input class="input" name="" type="text" id="transferBillId" ></td>
                            </tr>
                        </table>
                    </li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR guestDiv" id="transferDiv_1" style="width:95%">
                    <form id="transferForm_1">
                    	<div class="jsWord">检索条件</div>
	                    <table class="widthB98 margin-top-15 margin-bottom-10">
	                        <tr>
	                            <td width="12%" align="right">中文名</td>
	                            <td width="13%"><input class="input" name="gstNamec" type="text"></td>
	                            <td width="12%" align="right">英文名</td>
	                            <td width="13%"><input class="input" name="gstNamee" type="text"></td>
	                            <td width="12%" align="right">抵店日期</td>
	                            <td width="13%"><input class="input" name="reachDate" type="text" onclick="WdatePicker()"></td>
	                            <td width="12%" align="right">房号</td>
	                            <td><input class="input" name="roomId" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">团代码</td>
	                            <td><input class="input" name="grpId" type="text"></td>
	                            <td align="right">团名</td>
	                            <td><input class="input" name="grpName" type="text"></td>
	                            <td align="right">离店日期</td>
	                            <td><input class="input" name="leaveDate" type="text" onclick="WdatePicker()"></td>
	                            <td align="right">退房人</td>
	                            <td><input class="input" name="outPerName" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">合约单位</td>
	                            <td colspan="3">
										<table width="100%">
                                           <tr>
                                              <td style="padding:0;">
                                                <input class="input widthB98"  id="guest_theCompany" type="text" >
	                            				<input type="hidden" id="guest_company_id" name ="guest_compId" value="" label="合约单位"/>
                                       			<input type="hidden" id="guest_ta_type" name ="guest_compType" value="" label="合约单位"/>
                                      			 </td>
                                               <td width="22" align="left" style="padding:3;">
                                                   <a class="HYbutton" href="javascript:;"></a>
                                               </td>
                                           </tr>
                                       </table>
	                            </td>
	                            <td align="right" colspan="4">
	                            	<label class="margin-right-15"><input class="radio margin-right-5" name="transferRadio_2" type="radio" value="I" checked="checked">在住客人</label>
	                                <label><input class="radio" name="transferRadio_2" type="radio" value="O">离店客人</label>
	                            </td>
	                        </tr>
	                    </table>
                    </form>
                </div>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR guestDiv displayNone" id="transferDiv_2" style="width:95%">
                	<form id="transferForm_2">
	                    <div class="jsWord">检索条件</div>
	                    <table class="widthB98 margin-top-15 margin-bottom-10">
	                        <tr>
	                            <td width="12%" align="right">中文名</td>
	                            <td width="13%"><input class="input" name="leadNamec" type="text"></td>
	                            <td width="12%" align="right">英文名</td>
	                            <td width="13%"><input class="input" name="leadNamee" type="text"></td>
	                            <td width="12%" align="right">抵店日期</td>
	                            <td width="13%"><input class="input" name="reachDate" type="text" onclick="WdatePicker()"></td>
	                            <td width="12%" align="right">房号</td>
	                            <td><input class="input" name="roomId" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">团代码</td>
	                            <td><input class="input" name="grpId" type="text"></td>
	                            <td align="right">团名</td>
	                            <td><input class="input" name="grpName" type="text"></td>
	                            <td align="right">离店日期</td>
	                            <td><input class="input" name="leaveDate" type="text" onclick="WdatePicker()"></td>
	                            <td align="right">退房人</td>
	                            <td><input class="input" name="outPerName" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">合约单位</td>
	                            <td colspan="3">
	                            	<table width="100%">
                                           <tr>
                                              <td style="padding:0;">
                                                <input class="input widthB98"  id="group_theCompany" type="text">
	                            				<input type="hidden" id="group_company_id" name ="group_compId" value="" label="合约单位"/>
                                       			<input type="hidden" id="group_ta_type" name ="group_compType" value="" label="合约单位"/>
                                      			 </td>
                                               <td width="22" align="left" style="padding:3;">
                                                   <a class="HYbutton" href="javascript:;"></a>
                                               </td>
                                           </tr>
                                       </table>
	                            </td>
	                            <td align="right" colspan="4">
	                            	<label class="margin-right-15"><input class="radio margin-right-5" name="transferRadio_3" type="radio" value="I">在住客人</label>
	                                <label><input class="radio" name="transferRadio_3" type="radio" value="O">离店客人</label>
	                            </td>
	                        </tr>
	                    </table>
                    </form>
                </div>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR noGuestDiv displayNone" id="transferDiv_3" style="width:95%">
                	<form id="transferForm_3">
	                    <div class="jsWord">检索条件</div>
	                    <table class="widthB98 margin-top-15 margin-bottom-10">
	                        <tr>
	                            <td width="20%" align="right">非住客简称</td>
	                            <td width="40%"><input class="input" name="nogstId" type="text"></td>
	                            <td width="20%" align="right">创建日期</td>
	                            <td><input class="input" name="creaTimeStart" type="text" onclick="WdatePicker()"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">非住客名称</td>
	                            <td><input class="input" name="nogstName" type="text"></td>
	                            <td align="right">创建人</td>
	                            <td><input class="input" name="" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">合约单位</td>
	                            <td><table width="100%">
	                                            <tr>
	                                               <td style="padding:0;">
		                                                <input class="input widthB98"  id="noguest_theCompany" type="text">
			                            				<input type="hidden" id="noguest_company_id" name ="noguest_compId" value="" label="合约单位"/>
		                                       			<input type="hidden" id="noguest_ta_type" name ="noguest_compType" value="" label="合约单位"/>
	                                       			 </td>
	                                                <td width="22" align="left" style="padding:3;">
	                                                    <a class="HYbutton" href="javascript:;"></a>
	                                                </td>
	                                            </tr>
	                                        </table></td>
	                            <td colspan="2">&nbsp;</td>
	                        </tr>
	                    </table>
                    </form>
                </div>
                <div class="alertRight clearBoth margin-top-30">
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="quitTransferForm">退出</a>
                   <a class="buttonLikeA floatR margin-right-10" id="accountDetermine" href="javascript:;" >确定</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="clearTransferForm">清空条件</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="searchTransferGrid">条件检索</a>
                </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/转账弹出框-->
<!--确定转账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 accountDetermineDiv displayNone" id="accountDetermineDiv">
	<div class="alertMain greyBg" style="width:900px;margin-top:150px;">
    	<h4 class="moveDivAlert2">转账<a href="javascript:;" class="closeDiv2 closeAlert" id="accountDetermineClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB85 floatL">
                	<table width="100%">
                    	<tr>
                        	<td align="right">中文名</td>
                            <td><input class="input" name="" type="text" id="transferTopNamec" disabled="disabled"></td>
                            <td align="right">英文名</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text" id="transferTopNamee" disabled="disabled"></td>
                            <td align="right">房号</td>
                            <td><input class="input" name="" type="text" id="transferTopRoomId" disabled="disabled"></td>
                            <td align="center">
                            	<label class="margin-right-15"><input class="radio margin-right-5" name="transferTopRadio" type="radio" value="1" checked="checked">A1账</label>
                                <label><input class="radio" name="transferTopRadio" type="radio" value="2" disabled="disabled">B3账</label>
                            </td>
                        </tr>
                    	<tr>
                    		<td width="50" align="right">账号</td>
                            <td width="100"><input class="input" name="" type="text" id="transferTopBillId" disabled="disabled"></td>
                            <td width="60" align="right">总额</td>
                            <td width="60"><input class="input" name="" type="text" id="transferTopBorrow" disabled="disabled" style="text-align:right"></td>
                            <td width="45" align="right">已付</td>
                            <td width="60"><input class="input" name="" type="text" id="transferTopLent" disabled="disabled" style="text-align:right"></td>
                            <td width="40" align="right">余额</td>
                            <td width="70"><input class="input" name="" type="text" id="transferTopRemain" disabled="disabled" style="text-align:right"></td>
                            <td rowspan="2" align="center">
                            	<a class="roomPointBottom" href="javascript:;" id="transferToBottom" ></a>
                            </td>
                        </tr>
                        <tr>
                        	<td align="right">内容</td>
                            <td><input class="input" name="" type="text" disabled="disabled"></td>
                            <td align="right">备注</td>
                            <td colspan="5"><input class="input widthB99" name="" type="text" id="transferTopNotice" disabled="disabled"></td>
                        </tr>
                        
                    </table>
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="transferTopGrid" style="height:150px">
                    </div>
                    <!--/table-->
                    
                    <div class="clearBoth margin-bottom-20"></div>
                    <table width="100%">
                    	<tr>
                        	<td align="right">中文名</td>
                            <td><input class="input" name="" type="text" id="transferBottomNamec" disabled="disabled"></td>
                            <td align="right">英文名</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text" id="transferBottomNamee" disabled="disabled"></td>
                            <td align="right">房号</td>
                            <td><input class="input" name="" type="text" id="transferBottomRoomId" disabled="disabled"></td>
                            <td rowspan="2" align="center">
                                <a class="roomPointTop" href="javascript:;" id="transferToTop"></a>
                            </td>
                            
                        </tr>
                    	<tr>
                    		<td width="50" align="right">账号</td>
                            <td width="100"><input class="input" name="" type="text" id="transferBottomBillId" disabled="disabled"></td>
                            <td width="60" align="right">总额</td>
                            <td width="60"><input class="input" name="" type="text" id="transferBottomBorrow" disabled="disabled" style="text-align:right"></td>
                            <td width="45" align="right">已付</td>
                            <td width="60"><input class="input" name="" type="text" id="transferBottomLent" disabled="disabled" style="text-align:right"></td>
                            <td width="40" align="right">余额</td>
                            <td width="70"><input class="input" name="" type="text" id="transferBottomRemain" disabled="disabled" style="text-align:right"></td>
                        </tr>
                        <tr>
                        	<td align="right">内容</td>
                            <td><input class="input" name="" type="text" disabled="disabled"></td>
                            <td align="right">备注</td>
                            <td colspan="5"><input class="input widthB99" name="" type="text" id="transferBottomNotice" disabled="disabled"></td>
                            <td align="center">
                            	<label class="margin-right-15"><input class="radio margin-right-5" name="transferBottomRadio" type="radio" value="1" checked="checked">A2账</label>
                                <label><input class="radio" name="transferBottomRadio" type="radio" value="2">B4账</label>
                            </td>
                        </tr>
                        
                    </table>
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="transferBottomGrid" style="height:150px">
                    </div>
                    <!--/table-->
                </div>
                <ul class="floatL margin-left-20 margin-top-90">
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="accountDetermineConfirm">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="accountDetermineCancle">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="accountDetermineRefresh">刷新</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="accountDetermineQuit" >退出</a></li>
                </ul>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/确定转账弹出框-->
<!--结账打单弹出框-->
<div class="alertDiv moveBar2 alertDiv2 customAccountsDiv" id="customAccountsDiv">
	<div class="alertMain greyBg" style="width:750px;margin-top:150px;">
    	<h4 class="moveDivAlert2">自定义账目<a href="javascript:;" class="closeDiv2 closeAlert" id="customAccountsClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB80 floatL">
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="customAccountsGrid" style="height:150px">
                    </div>
                    <!--/table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td align="right">客人名</td>
                                <td><input class="input" name="" type="text" id="customAccountName"></td>
                                <td align="right">协议单位</td>
                                <td colspan="3"><input class="input widthB98" name="" id="customAccountCompId" type="text"></td>
                            </tr>
                            <tr>
                                <td width="13%" align="right">房号</td>
                                <td width="18%"><input class="input" name="" type="text" id="customAccountRoomId"></td>
                                <td width="17%" align="right">抵店时间</td>
                                <td width="18%"><input class="input" name="" id="customAccountReachDate" type="text"></td>
                                <td width="17%" align="right">离店时间</td>
                                <td><input class="input widthB93" name="" id="customAccountLeaveDate" type="text"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td width="13%" align="right">房租</td>
                                <td width="12%"><input class="input" name="" id="customAccountRoomPrice" type="text"></td>
                                <td width="13%" align="right">消费额</td>
                                <td width="12%"><input class="input" name="" id="customAccountBorrow" type="text"></td>
                                <td width="13%" align="right">已付额</td>
                                <td width="12%"><input class="input" name="" id="customAccountLent" type="text"></td>
                                <td width="13%" align="right">余额</td>
                                <td><input class="input widthB90" name="" id="customAccountRemain" type="text"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <ul class="floatL margin-left-20 margin-top-5">
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"   id="customAccountCancle">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"   id="customAccountAdd">添加</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"   id="customAccountDelete">删除</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">打印</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"   id="customAccountQuit">退出</a></li>
                </ul>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
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
<!--/拆分账目弹出框-->
<!--/收款弹出框-->
<input type="hidden" id="spath" name="spath" value="${pageContext.request.contextPath}">
</body>
<!-- JQuery plugin -->
<script src="${ctx}/scripts/jquery-migrate-1.2.1.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery.inputmask.min.js" type="text/javascript"></script>
<script src="${ctx}/js/lib/autocomplete/jquery.autocomplete.min.js"></script>	
<script src="${ctx}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
<script>
	var rooms_model_cach = new Array();//缓存当前模块共用数据
	var accountGrid = null;//住客资料账务grid对象
	var moneyId ='${moneyId}';
	var refundCode = '${refundCode}';
	seajs.config({
        base: '${pageContext.request.contextPath}/js/lib/'
        , alias: {
            'template': 'template/template'
        }
    });
    seajs.use(["${pageContext.request.contextPath}/js/noguest/noguestaccount.js?number=" + Math.random()]);
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/noguest/noguest.js"></script>
</html>

