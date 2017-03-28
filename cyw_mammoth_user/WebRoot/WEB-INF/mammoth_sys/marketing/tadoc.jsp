<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销-合约单位</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<style type="text/css">
.disable01{
color: grey;cursor:not-allowed;
}
</style>
</head>

<body>
	<%@ include file="../header.jsp" %>
	<%@ include file="secondMenu.jsp" %>
	<script type="text/javascript">
setSecondMenuStat(2);
</script>
	<!--banner & menu -END -->
	<!--center xingli 2015-09-08-->
	<div class="center">
		<!--mainWeb-->
		<div class="main" id="webMain">
			<!--mainInformation-->
			<div class="mainInfo2">
			
				<!--table-->
				<div class="tableDiv" id="ydtable" style="overflow-x: hidden; overflow-y: auto;height: 664px;">
            	
                </div>
				<!--table -END- -->
			</div>
			<!--mainInformation END-->
			<!--mainRightMenu-->
			<!-- -
			<form action="${pageContext.request.contextPath}/marketing/doTadoclist.do" method="post" id="tadocform"> -->
			<div class="rightMenu">
				<div class="roomStatus">
					<div class="choice padding-bottom-10">
						<form id="searchForm" class="bookRight" action="" method="get">
							<dl class="inputDiv2 margin-top-none">
								<dt>所属类别</dt>
								<dd>
									<select class="select widthB94" name="taType">
										<option value="">全部</option>
										<c:forEach items="${hyunitcodes}" var="list">
                                             <option value="${list.codeId}">${list.codeNamec}</option>
                                        </c:forEach>
									</select>
								</dd>
								<dt>简称</dt>
								<dd>
									<input class="input" name="compId" value="${searchVo.compId }" type="text" maxlength="10" placeholder="">
								</dd>
								<dt>中文名</dt>
								<dd>
									<input class="input" name="namec" value="${searchVo.namec }" type="text" maxlength="20" placeholder="">
								</dd>
								<dt>单位电话</dt>
								<dd>
									<input class="input" name="tele" type="text"  data-inputmask="'mask': '9', 'repeat': 11, 'greedy' : false" >
								</dd>
								<dt>联系人</dt>
								<dd>
									<input class="input" name="connector" value="${searchVo.connector }" type="text" placeholder="">
								</dd>
								<dt>联系手机</dt>
								<dd>
									<input class="input" name="phone" value="${searchVo.phone }" data-inputmask="'mask': '9', 'repeat': 11, 'greedy' : false" type="text" >
								</dd>
								<dt>合同号</dt>
								<dd>
									<input class="input" name="id" value="${searchVo.id }" data-inputmask="'mask': '9{0,5}'" type="text" placeholder="">
								</dd>
								<dt>销售员</dt>
								<dd>
									<input class="input" name="salemanId" value="${searchVo.salemanId }" maxlength="10" type="text" placeholder="">
								</dd>
							</dl>
							<div class="clearBoth"></div>
							<table width="90%" class="margin-left-10 margin-top-20">
								<tr>
									<td align="right"><label class="margin-right-10"><input
											name="chkStat" id="effective" type="radio" value="1" checked="checked"><span  style="font-size: 14px" class="margin-left-5">有效</span>
									</label>
									</td>
									<td align="left"><label class="margin-left-10"><input
											name="chkStat" id="uneffective" type="radio" value="0"><span style="font-size: 14px" class="margin-left-5">无效</span>
									</label>
									</td>
								</tr>
							</table>
							<div class="cabDivNoneHei clearBoth padding-top-15">
								<a class="button_07 floatL" href="javascript:;" onclick="doSearch()">条件查询</a>
								<a class="button_07 floatL" href="javascript:;" onclick="doClear()">清空条件</a>
								<a id="unitDetail" class="button_07 floatL" href="javascript:;" <mammoth:AuthJudge funcId="c_819594" />>单位详情</a>
								<a id="addTadoc" class="button_07 floatL" href="javascript:;">新增</a>
								<a class="button_07 floatL" href="javascript:;">查看业绩</a>
								<a class="button_07 floatL" href="javascript:;">合并</a>
								<a class="button_07 floatL" href="javascript:;">改合同号</a>
								<a class="button_07 floatL" href="javascript:;">合同价</a>
							</div>
							
							<!--小键盘-->
							<div class="cabDiv clearBoth">
								<input value="" id="codeLetter" name="codeLetter" type="hidden"/>
                        	    <input value="" id="symbol" name="symbol" type="hidden"/>
                        	    
								<a href="javascript:;" onclick="doSearch('a');">A</a>
	                            <a href="javascript:;" onclick="doSearch('b');">B</a>
	                            <a href="javascript:;" onclick="doSearch('c');">C</a>
	                            <a href="javascript:;" onclick="doSearch('d');">D</a>
	                            <a href="javascript:;" onclick="doSearch('e');">E</a>
	                            <a href="javascript:;" onclick="doSearch('f');">F</a>
	                            <a href="javascript:;" onclick="doSearch('g');">G</a>
	                            <a href="javascript:;" onclick="doSearch('h');">H</a>
	                            <a href="javascript:;" onclick="doSearch('i');">I</a>
	                            <a href="javascript:;" onclick="doSearch('j');">J</a>
	                            <a href="javascript:;" onclick="doSearch('k');">K</a>
	                            <a href="javascript:;" onclick="doSearch('l');">L</a>
	                            <a href="javascript:;" onclick="doSearch('m');">M</a>
	                            <a href="javascript:;" onclick="doSearch('n');">N</a>
	                            <a href="javascript:;" onclick="doSearch('o');">O</a>
	                            <a href="javascript:;" onclick="doSearch('p');">P</a>
	                            <a href="javascript:;" onclick="doSearch('q');">Q</a>
	                            <a href="javascript:;" onclick="doSearch('r');">R</a>
	                            <a href="javascript:;" onclick="doSearch('s');">S</a>
	                            <a href="javascript:;" onclick="doSearch('t');">T</a>
	                            <a href="javascript:;" onclick="doSearch('*')">*</a>
	                            <a href="javascript:;" onclick="doSearch('u');">U</a>
	                            <a href="javascript:;" onclick="doSearch('v');">V</a>
	                            <a href="javascript:;" onclick="doSearch('w');">W</a>
	                            <a href="javascript:;" onclick="doSearch('x');">X</a>
	                            <a href="javascript:;" onclick="doSearch('y');">Y</a>
	                            <a href="javascript:;" onclick="doSearch('z');">Z</a>
	                            <a href="javascript:;" onclick="doSearch('#');">#</a>
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
	
	<!--单位详情-->
	<div class="alertDiv checkInDiv moveBar unitDetailDiv">
		<div class="alertMain greyBg" style="width:970px; margin-top:125px;">
			<h4 class="moveDivAlert" id="MoveAlertDiv">
				合约单位详情<a href="javascript:;" class="closeDiv"></a>
			</h4>
			<div class="borderSolid">

				<div class="clearBoth GuestTabIn fitBook posR">
				<form id="addTADocForm">
					<h6 class="fontWeight padding-bottom-5">单位资料</h6>
                <div class="GuestTabL3 posR">
                <div class="posA" style="top:-30px; right:0;"><label><input id="theStatus" name="status" value="1"  class="checkbox margin-right-5" type="checkbox" checked>有效</label></div>
						<table width="100%">
							<tr>
								<td width="70" align="right">简称</td>
								<td width="150"><input id="flag" name="flag" type="hidden"/><input id="theCompId" class="input" name="compId" type="text" data-validation="required" label="简称" maxlength="30">
								</td>
								<td width="70" align="right">电话</td>
								<td width="150"><input id="theTele" class="input" name="tele" maxlength="30" type="text" data-inputmask="'mask': '9', 'repeat': 11, 'greedy' : false,'placeholder':''">
								</td>
								<td width="70" align="right">类别</td>
								<td><select id="theTaType" class="select" name="taType" data-validation="required">
										<c:forEach items="${hyunitcodes}" var="list">
                                             <option value="${list.codeId}">${list.codeNamec}</option>
                                        </c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">中文名</td>
								<td><input id="theNamec" class="input" maxlength="100" name="namec" data-validation="required" type="text" data-validation="required" label="中文名" onBlur="theNamee.value=(pinyin.getFullChars(this.value));" onChange="theNamee.value=(pinyin.getFullChars(this.value));" onKeydown="theNamee.value=(pinyin.getFullChars(this.value));" />
								</td>
								<td align="right">传真</td>
								<td><input id="theFax" class="input" name="fax" max="20" type="text" data-inputmask="'mask': '9', 'repeat': 12, 'greedy' : false">
								</td>
								<td align="right">联系人</td>
								<td><input id="theConnector" class="input" name="connector" type="text" maxlength="10" data-validation="required" label="联系人" >
								</td>
							</tr>
							<tr>
								<td align="right">英文名</td>
								<td><input id="theNamee" class="input" maxlength="100" name="namee" type="text">
								</td>
								<td align="right">邮箱</td>
								<td><input id="theEmail" class="input" name="email" data-validation="isEmail"  label="邮箱" type="text" maxlength="20">
								</td>
								<td align="right">手机</td>
								<td><input id="phone" maxlength="15" class="input" name="phone" type="text" data-inputmask="'mask': '9', 'repeat': 11, 'greedy' : false,'placeholder':''" data-validation="required,simpleMobile" label="手机">
								</td>
							</tr>
							<tr>
								<td align="right">地址</td>
								<td colspan="3"><input id="theAddr" class="input widthB98" name="addr" type="text" maxlength="50">
								</td>
								<td align="right">销售员</td>
								<td><select class="select " id="saleManList" name="salemanId"></select>
								</td>
							</tr>
						</table>
					</div>

					<h6 class="fontWeight clearBoth padding-top-10 padding-bottom-5">合约资料</h6>
					<div class="GuestTabL3">
						<table width="100%">
							<tr>
								<td width="70" align="right" class="gry_9">主合同号</td>
								<td width="100">
								<input id="showId" class="input gry_9"type="text" readonly="readonly" disabled onclick="return false;" />
								<input id="theId" name="id" type="hidden" /> 
								</td>
								<td width="70" align="right">有效期限</td>
								<td width="100"><input id="expiryDate" class="input" data-validation="required,simpleDate" label="有效期限" name="expiryDate" type="text">
								</td>
								<td width="70" align="right">房价方案</td>
								<td><select class="select " id="hroomPlanList" name="rateCode">
								</select></td>
							</tr>
							<tr>
								<td width="70" align="right">副合同号</td>
								<td width="100"><input id="theTaCompCd" class="input" name="taCompCd" type="text" data-validation="required" label="副合同号" data-inputmask="'mask': '9{0,12}'">
								</td>
								<td width="70" align="right">信用限额</td>
								<td width="100"><input id="limit" class="input" name="limit" type="text" onchange="limitConvert()" data-validation="required" label="信用限额">
								</td>
								<td align="right" width="30">开户行</td>
								<td><input id="theBankId" class="input" maxlength="15"  name="bankId" type="text" maxlength="20" data-validation="required" label="开户行">
								</td>
							</tr>
							<tr>
								<td width="70" align="right">会员卡号</td>
								<td>
									<select class="select">
										<option>--</option>
								    </select>
								</td>
								<td width="70" align="right">收款周期</td>
								<td width="100"><input id="period" style="text-align: left;" class="input" name="period" type="text" data-validation="required" label="收款周期">
								</td>
								<td align="right">银行账号</td>
								<td><input id="bankNum"  class="input" name="bankNum" type="text" data-inputmask="'mask': '9999 9999 9999 9999 9{0,3}', 'greedy' : false,'placeholder':''" data-validation="required" label="银行账号">
								</td>
							</tr>
							<tr>
								<td align="right">&nbsp;</td>
								<td colspan="5">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" valign="top">其他条款</td>
								<td colspan="5">
									<textarea id="theNotes" type="textarea" class="textarea" style="height: 200px" data-validation="required" label="其他条款" name="notes" cols="" rows=""></textarea>
								</td>
							</tr>
						</table>
					</div>
					</form>

					<!--右侧部分-->
					<div class="GuestTabR GTabPos posA margin-top-30">
						<a id="OK" class="button_02 group1"  jack='formSubmit()' href="javascript:;" onclick="formSubmit()">确定</a> 
						<a id="cancel" class="button_02 group1" jack='formCancel()' href="javascript:;" onclick="formCancel()">放弃</a>
						<a id="add" class="button_02 group2" jack='formClear()' href="javascript:;" onclick="formClear()">新增</a> 
						<a id="del" class="button_02 group2" jack='setStatus()' href="javascript:;" onclick="setStatus()">取消</a>
						<a id="contract" class="button_02 group2" jack='' href="javascript:;">创建AR账号</a>
						<a class="button_02 group2" jack='' href="javascript:;">合同价</a>
						<a class="button_02 group2" jack='' href="javascript:;">签单人</a>
						<a id="countCost" jack='' class="button_02 group2" href="javascript:;">查看业绩</a>
						<a id="print" jack='' class="button_02 group2" href="javascript:;">打印</a>
						<a class="button_02 group2" jack='closeUnitWindow()' href="javascript:;" onclick="closeUnitWindow();" >退出</a>
					</div>
					<!--/右侧部分-->
				</div>
				<div class="clearBoth"></div>
			</div>
		</div>
	</div>
	<!--/单位详情-->
</body>
<script src="${ctx}/scripts/jquery.inputmask.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/marketing/tadoc.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/marketing/tadocgrid.js"></script>
<script type="text/javascript">


</script>
</html>
