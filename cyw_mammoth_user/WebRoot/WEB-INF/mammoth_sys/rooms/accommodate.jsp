<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房态-预定入住</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/cywManage-css.css">
</head>
<body>
	<input type="hidden" id="path"
		value="${pageContext.request.contextPath}" />
	<%@ include file="../header.jsp"%>
	<!--secondMenu-->
	<%@ include file="secondMenu.jsp"%>
	<!--secondMenu -end- -->
	<!--banner & menu -END -->
	<!--center xingli 2015-09-08-->
	<div class="center">
		<!--mainWeb-->
		<div class="main" id="webMain">
			<!--mainInformation-->
			<div class="RoomCheckIn overFlowY">
				<!--table-->
				<div class="tableDiv yd-SK">
					<!--table title-->
					<div id="guestAccommodate"></div>
					<!--table title end -->
				</div>
				<div class="tableDiv yd-TD displayNone">
					<!--table title-->
					<table class="tableMain">
						<thead>
							<tr>
								<td width="5%">序号</td>
								<td width="6%">订单号</td>
								<td width="6%">团代码</td>
								<td width="5%">团名</td>
								<td width="6%">联系人</td>
								<td width="10%">联系人电话</td>
								<td width="8%">合约单位</td>
								<td width="8%">抵店日期</td>
								<td width="8%">离店日期</td>
								<td width="5%">状态</td>
								<td width="6%">销售员</td>
								<td width="8%">预订房类</td>
								<td width="6%">订房书</td>
								<td width="5%">房号</td>
								<td>checkID</td>
							</tr>
						</thead>
					</table>
					<!--table title end -->
					<!--table enner-->
					<table class="tableMain tabChangBg">
						<tbody>
							<tr>
								<td width="4%">001</td>
								<td width="6%">21601</td>
								<td width="8%">25147</td>
								<td width="7%">听涛阁</td>
								<td width="10%">tinggaoge</td>
								<td width="8%">男</td>
								<td width="8%">188888</td>
								<td width="8%">627</td>
								<td width="5%">1888</td>
								<td width="5%">男</td>
								<td width="8%">188888</td>
								<td width="8%">6107</td>
								<td width="5%">188</td>
								<td width="5%">188</td>
								<td>--</td>
							</tr>
							<tr>
								<td>002</td>
								<td>21601</td>
								<td>25147</td>
								<td>听涛阁</td>
								<td>tinggaoge</td>
								<td>男</td>
								<td>188888</td>
								<td>6107</td>
								<td>627</td>
								<td>男</td>
								<td>188888</td>
								<td>6107</td>
								<td>6127</td>
								<td>6127</td>
								<td>--</td>
							</tr>
						</tbody>
					</table>
					<!--table enner -END- -->
				</div>
				<!--table -END- -->
			</div>
			<!--mainInformation END-->
			<!--mainRightMenu-->
			<div class="rightMenu rightMenuFT">
				<div class="roomStatus">
					<ul class="roomStatusTitle">
						<li class="point" id="userCheckIn">散客预订</li>
						<li id="userCurrentState">团队预订</li>
					</ul>
					<div class="clearBoth"></div>

					<div class="choice padding-top-10 padding-bottom-30">
						<form class="checkLogin" action="" method="get"
							id="guestAccoSearchForm">
							<dl class="inputDiv margin-top-none">
								<dt>订单号</dt>
								<dd>
									<input class="input" name="book_list"
										value="${searchVo.book_list}" type="text" placeholder="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
								</dd>

								<dt>中文名</dt>
								<dd>
									<input class="input" name="gstNamec"
										value="${searchVo.gstNamec}" type="text" placeholder="">
								</dd>
								<dt>英文名</dt>
								<dd>
									<input class="input" name="gstNamee"
										value="${ searchVo.gstNamee}" type="text" placeholder="">
								</dd>
								<dt>电话</dt>
								<dd>
									<input class="input" name="mobile" value="${ searchVo.mobile}"
										type="text" placeholder="">
								</dd>
								<dt>抵店日期</dt>
								<dd>
									<input class="input Wdate" name="reachDate" readonly="readonly"
										value="${searchVo.reachDate}" type="text" placeholder="">
								</dd>
								<dt>离店日期</dt>
								<dd>
									<input class="input" name="leaveDate"
										onclick="WdatePicker({minDate:'%y-%M-%d'});"
										value="${searchVo.leaveDate}" type="text" placeholder="">
								</dd>
								<dt>房间号</dt>
								<dd>
									<input class="input" name="room_id" value="${searchVo.room_id}"
										type="text" placeholder="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
								</dd>
								<!-- <dt>销售员</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd> -->
								<dt>状态</dt>
								<dd>
									<select class="select widthB98" name="bookStat" id="bookStat">
										<option value="">----请选择----</option>
										<option value="B"
											<c:if test="${searchVo.bookStat=='B' }">selected</c:if>>预定</option>
										<option value="O"
											<c:if test="${searchVo.bookStat=='O' }">selected</c:if>>确认预定</option>
										<option value="R"
											<c:if test="${searchVo.bookStat=='R' }">selected</c:if>>部分抵达</option>
									</select>
								</dd>
							</dl>
							<div class="cabDivNoneHei clearBoth padding-top-10">
								<a class="button_03 floatL margin-left-5"
									href="javascript:void(0);" onclick="goSearch(0)">条件查询</a> <a
									class="button_03 floatL margin-left-5"
									href="javascript:void(0);" onclick="clearText()">清空条件</a> <a
									class="button_03 floatL margin-left-5" id="playEnt"
									href="javascript:;">办理入住</a> <a
									class="button_03 floatL margin-left-5" href="javascript:;">查看订单</a>
							</div>
							<!--散客预定小键盘-->
							<div class="cabDiv clearBoth">
								<input value="" id="codeLetter" name="codeLetter" type="hidden" />
								 <input value="" id="symbol" name="symbol" type="hidden" /> <a
									href="javascript:;" onclick="goSearch('a');">A</a> <a
									href="javascript:;" onclick="goSearch('b');">B</a> <a
									href="javascript:;" onclick="goSearch('c');">C</a> <a
									href="javascript:;" onclick="goSearch('d');">D</a> <a
									href="javascript:;" onclick="goSearch('e');">E</a> <a
									href="javascript:;" onclick="goSearch('f');">F</a> <a
									href="javascript:;" onclick="goSearch('g');">G</a> <a
									href="javascript:;" onclick="goSearch('h');">H</a> <a
									href="javascript:;" onclick="goSearch('i');">I</a> <a
									href="javascript:;" onclick="goSearch('j');">J</a> <a
									href="javascript:;" onclick="goSearch('k');">K</a> <a
									href="javascript:;" onclick="goSearch('l');">L</a> <a
									href="javascript:;" onclick="goSearch('m');">M</a> <a
									href="javascript:;" onclick="goSearch('n');">N</a> <a
									href="javascript:;" onclick="goSearch('o');">O</a> <a
									href="javascript:;" onclick="goSearch('p');">P</a> <a
									href="javascript:;" onclick="goSearch('q');">Q</a> <a
									href="javascript:;" onclick="goSearch('r');">R</a> <a
									href="javascript:;" onclick="goSearch('s');">S</a> <a
									href="javascript:;" onclick="goSearch('t');">T</a> <a
									href="javascript:;" onclick="goSearch('*')">*</a> <a
									href="javascript:;" onclick="goSearch('u');">U</a> <a
									href="javascript:;" onclick="goSearch('v');">V</a> <a
									href="javascript:;" onclick="goSearch('w');">W</a> <a
									href="javascript:;" onclick="goSearch('x');">X</a> <a
									href="javascript:;" onclick="goSearch('y');">Y</a> <a
									href="javascript:;" onclick="goSearch('z');">Z</a> <a
									href="javascript:;" onclick="goSearch('#');">#</a>
							</div>
							<!--小键盘END-->
						</form>

						<!----------------------------------------团队预定查询条件----------------------------------------------->
						<!-- 团队预定查询条件 start -->
						<form class="currentState displayNone" action="" method="get">
							<dl class="inputDiv margin-top-none">
								<dt>订单号</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>团代码</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>团名</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>联系人</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>联系电话</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>合约单位</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>抵店日期</dt>
								<dd>
									<input class="dataTimeInput datetimepicker" type="text"
										value="" />
								</dd>
								<dt>离店日期</dt>
								<dd>
									<input class="dataTimeInput datetimepicker" type="text"
										value="" />
								</dd>
								<dt>销售员</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>房间号</dt>
								<dd>
									<input class="input" name="" type="text">
								</dd>
								<dt>状态</dt>
								<dd>
									<select class="select widthB98">
										<option value="">全部</option>
										<option value="">未确认</option>
										<option value="">已确认</option>
										<option value="">部分抵达</option>
									</select>
								</dd>

							</dl>
							<div class="cabDivNoneHei clearBoth padding-top-10">
								<a class="button_03 floatL margin-left-5" href="javascript:;">条件查询</a>
								<a class="button_03 floatL margin-left-5" href="javascript:;">清空条件</a>
								<a class="button_03 floatL margin-left-5" href="javascript:;">办理入住</a>
								<a class="button_03 floatL margin-left-5" href="javascript:;">查看订单</a>
							</div>
							<!--小键盘-->
							<div class="cabDiv clearBoth">
								<a href="javascript:;">A</a> <a href="javascript:;">B</a> <a
									href="javascript:;">C</a> <a href="javascript:;">D</a> <a
									href="javascript:;">E</a> <a href="javascript:;">F</a> <a
									href="javascript:;">G</a> <a href="javascript:;">H</a> <a
									href="javascript:;">I</a> <a href="javascript:;">J</a> <a
									href="javascript:;">K</a> <a href="javascript:;">L</a> <a
									href="javascript:;">M</a> <a href="javascript:;">N</a> <a
									href="javascript:;">O</a> <a href="javascript:;">P</a> <a
									href="javascript:;">Q</a> <a href="javascript:;">R</a> <a
									href="javascript:;">S</a> <a href="javascript:;">T</a> <a
									href="javascript:;">*</a> <a href="javascript:;">U</a> <a
									href="javascript:;">V</a> <a href="javascript:;">W</a> <a
									href="javascript:;">X</a> <a href="javascript:;">Y</a> <a
									href="javascript:;">Z</a> <a href="javascript:;">#</a>
							</div>
							<!--小键盘END-->
						</form>
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
	<div class="copyRight clearBoth">
		<ul class="hotel">
			<li>村游管理系统</li>
			<li>网址：<a href="bbs.cyw.so" target="_blank">bbs.cyw.so</a></li>
			<li>客服电话：029-87885231</li>
			<li>客服QQ：2880078737</li>
			<li><span class="margin-right-20">|</span>本软件授权<a
				class="margin-left-10 margin-right-10" href="javascript:;">[新印象太河湾度假村]</a>为合法最终用户</li>
		</ul>
		<ul class="serverPhone">
			<li>操作员：<a href="javascript:;">管理员</a></li>
			<li>2015-09-20<span class="margin-right-10">14：39</span></li>
		</ul>
	</div>
	<!--copyRight -END -->
	<!--弹出层阴影-->
	<div class="alertDivBg"></div>
	<div class="alertDivBg2"></div>
	<!--弹出层阴影结束-->
	<!--留房弹出框-->
	<div class="alertDiv checkInDiv moveBar bookRegistration">
		<div class="alertMain greyBg" style="width:1040px; margin-top:120px;">
			<h4 class="moveDivAlert2">
				预订登记<a href="javascript:;" class="closeDiv"></a>
			</h4>
			<div class="borderSolid">
				<!--留房弹出框左侧-->
				<div class="leaveTheRoomL">

					<ul class="statusUl">
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>
						<li class="statusLi idle">
							<div class="statusBlock">
								<h5>9527</h5>
								<h6>标准间</h6>
							</div>
							<div class="pointStatus"></div> <img class="pointStatusRight"
							src="../img/point-right.png">
						</li>

						<div class="clearBoth"></div>
					</ul>
					<!--留房表格Table-->
					<div class="tableDiv whiteBackground floatL" style="width:420px;">
						<!--table title-->
						<table class="tableMain">
							<thead>
								<tr>
									<td width="65">序号</td>
									<td width="75">房类</td>
									<td width="90">订房</td>
									<td width="80">留房</td>
									<td width="110">抵店日期</td>
									<td width="100">离店日期</td>
								</tr>
							</thead>
						</table>
						<!--table title end -->
						<!--table enner-->
						<div class="lfTabMain">
							<table class="tableMain tabChangBg">
								<tbody>
									<tr>
										<td width="65">001</td>
										<td width="90">21601</td>
										<td width="120">TingTao</td>
										<td width="100">听涛阁</td>
										<td width="110">听涛阁</td>
										<td width="100">可修改</td>
									</tr>
									<tr>
										<td>002</td>
										<td>21601</td>
										<td>TingTao</td>
										<td>听涛阁</td>
										<td>听涛阁</td>
										<td>可修改</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!--table enner -END- -->
					</div>
					<div class="roomNum floatR"
						style="width:275px;margin-top:8px;margin-right:-2px;">
						<div class="floatL">
							<ul class="guestUl3 ">
								<li><a href="javascript:;">2015</a>
								</li>
								<li><a href="javascript:;">2016</a>
								</li>
								<li><a href="javascript:;">2015</a>
								</li>
								<li><a href="javascript:;">2016</a>
								</li>
							</ul>
							<a href="javascript:;" class="checkRoom">留房：5</a>
						</div>
						<div class="floatL margin-top-30  margin-left-10">
							<a class="forSN2 margin-top-30"><img width="17"
								src="../img/right_01.png"> </a> <a
								class="forSN2 margin-top-10"><img width="17"
								src="../img/right_02.png"> </a>
						</div>
						<div class="floatR">
							<ul class="guestUl3">
								<li><a href="javascript:;">2015</a>
								</li>
								<li><a href="javascript:;">2016</a>
								</li>
								<li><a href="javascript:;">2015</a>
								</li>
								<li><a href="javascript:;">2016</a>
								</li>
							</ul>
							<a href="javascript:;" class="checkRoom">选定留房：2</a>
						</div>
					</div>
					<!--/留房表格Table-->
				</div>
				<!--留房弹出框左侧END-->
				<!--留房弹出框右侧-->
				<div class="leaveTheRoomR">
					<div class="roomStatus">
						<div class="choice padding-bottom-10">
							<form class="" action="" method="get">
								<dl class="inputDiv2" style="border:none;">
									<dt>楼名</dt>
									<dd>
										<select class="select widthB100">
											<option>--</option>
										</select>
									</dd>
									<dt>楼层</dt>
									<dd>
										<select class="select widthB100">
											<option>--</option>
										</select>
									</dd>
									<dt>房型</dt>
									<dd>
										<table width="100%">
											<tr>
												<td style="padding:0;"><input class="input" name=""
													type="text" placeholder="">
												</td>
												<td width="28" align="right"><a
													class="SFbutton floatR marginRight-4" href="javascript:;">···</a>
												</td>
											</tr>
										</table>
									</dd>
									<dt>特征</dt>
									<dd>
										<table width="100%">
											<tr>
												<td style="padding:0;"><input class="input" name=""
													type="text" placeholder="">
												</td>
												<td width="28" align="right"><a
													class="SFbutton floatR marginRight-4" href="javascript:;">···</a>
												</td>
											</tr>
										</table>
									</dd>
									<dt>抵店</dt>
									<dd>
										<input class="input" name="" type="text" placeholder="">
									</dd>
									<dt>离店</dt>
									<dd>
										<input class="dataTimeInput datetimepicker" type="text"
											value="" />
									</dd>
									<div class="clearBoth"></div>
								</dl>
								<table width="90%" class="margin-left-10 margin-bottom-20">
									<tr>
										<td align="center"><label><input name=""
												type="radio"><span class="margin-left-10">全部</span>
										</label></td>
										<td align="center"><label><input name=""
												type="radio"><span class="margin-left-10">选择</span>
										</label></td>
									</tr>
								</table>
								<div class="clearBoth"></div>
								<a class="button_06 floatL margin-left-30" href="javascript:;">刷新</a>
								<a class="button_06 floatL margin-left-30" href="javascript:;">清空条件</a>
								<a class="button_06 floatL margin-left-30" href="javascript:;">确定</a>
								<a class="button_06 floatL margin-left-30" href="javascript:;">放弃</a>
								<a class="button_06 floatL margin-left-30" href="javascript:;">自助排房</a>
								<a class="button_06 floatL margin-left-30" href="javascript:;">退出</a>
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
	<!--选定房间弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 checkRoomDiv">
		<div class="alertMain greyBg" style="width:500px;margin-top:150px;">
			<h4 class="moveDivAlert2">
				选定房间明细表<a href="javascript:;" class="closeDiv2 closeAlert"></a>
			</h4>
			<div class="borderSolid">
				<!--table-->
				<div class="widthB100">
					<!--table-->
					<div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
						<!--table title-->
						<table class="tableMain">
							<thead>
								<tr>
									<td width="15%">房号</td>
									<td width="15%">建筑</td>
									<td width="15%">楼层</td>
									<td width="20%">房号</td>
									<td>房型</td>
								</tr>
							</thead>
						</table>
						<!--table title end -->
						<!--table enner-->
						<div class="tableHeiScll height300">
							<table class="tableMain">
								<tbody>
									<tr>
										<td width="15%">001</td>
										<td width="15%">z3</td>
										<td width="15%">nan</td>
										<td width="20%">yes</td>
										<td>状态</td>
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
					<span class="margin-left-10" href="javascript:;">选定房间：5</span>
					<div class="clearBoth">
						<a class="button_02 floatR margin-left-5" href="javascript:;">退出</a>
						<a class="button_02 floatR margin-left-5" href="javascript:;">取消选中</a>
					</div>
				</div>

				<div class="clearBoth"></div>
			</div>
		</div>
	</div>
	<!--/选定房间弹出框-->
</body>
<input type="hidden" id="testid" value="${list}">
<script type="text/javascript">
	var path = $("#path").val();
	var view = undefined;
	var grid = undefined;
	$(function() {
		/*遮罩层DIV高度*/
		$(".alertDivBg").css("height", $(document).height());
		$(".alertDivBg").css("width", $(document).width());
		/*遮罩层DIV高度*/
		//房态右Tab页  右侧的
		$(".roomStatusTitle li").click(function() {
			$(".roomStatusTitle li").removeClass("point");
			$(this).addClass("point");
		});
		//房态右Tab页  右侧的END
		$("#playEnt").click(function() {
			$(".bookRegistration").fadeIn();
			$(".alertDivBg").fadeIn();
		});
		$(".closeAlert").click(function() {
			$(".checkRoomDiv").fadeOut();
			$(".alertDivBg2").fadeOut();
		});
		/*选定房间*/
		$(".checkRoom").click(function() {
			$(".checkRoomDiv").fadeIn();
			$(".alertDivBg2").fadeIn();

		});
		/*选定房间*/
		$(".bookRegistration h4 a").click(function() {
			$(".alertDivBg").fadeOut();
			$(".bookRegistration").fadeOut();
		});

		//-------------------------------------------------初始化FlexGrid--------------------------------------------------
		//var aa = eval('('+'${list}'+')');
		var list = ${list};
		var count = list.length;
		var data = list;
		console.info(list);
		var number = 1;
		if(list==null || list.length==0){
			return;
		}
		var tempCheckId = list[0].checkId;
		var array = [];
		for ( var i = 0; i < count; i++) {
			var status = "";
			if (data[i].bookStat == "B") {
				status = "未确认";
			} else if (data[i].bookStat == "C") {
				status = "取消";
			} else if (data[i].bookStat == "O") {
				status = "已确认";
			} else if (data[i].bookStat == "R") {
				status = "部分抵达";
			} else if (data[i].bookStat == "A") {
				status = "全部抵达";
			} else if (data[i].bookStat == "N") {
				status = "NO SHOW";
			}

			if (tempCheckId != data[i].checkId) {
				tempCheckId = data[i].checkId;
				number++;
			}

			array.push({
				序号 : number,
				checkId : data[i].checkId,
				订单号 : data[i].bookList,
				英文名 : data[i].gstNamee,
				中文名 : data[i].gstNamec,
				合约单位 : data[i].taName,
				电话 : data[i].mobile,
				抵店日期 : data[i].reachDate,
				离店日期 : new Date(data[i].leaveDate),
				状态 : status,
				//订房数:data[i].bookNum,

				//房类:data[i].roomtypeId,
				房类 : data[i].codeNamec,
				订房 : data[i].bookNum,
				留房 : data[i].saveNum,
				抵店日期 : data[i].breachDate,
				离店日期 : data[i].bleaveDate,
				房价 : data[i].broomPrice,
				取消数 : data[i].cancelNum,
				NOSHOW数 : data[i].noShowNum,
				抵达数 : data[i].reachNum,
			});

		}

		view = new wijmo.collections.CollectionView(array);
		grid = new wijmo.grid.FlexGrid('#guestAccommodate', {
			autoGenerateColumns : false,
			allowMerging : wijmo.grid.AllowMerging.All,
			isReadOnly : true,
			selectionMode : wijmo.grid.SelectionMode.RowRange,
			columns : [ {
				header : '序号',
				binding : '序号',
				width : 50,
				isReadOnly : true,
				allowMerging : true
			}, {
				header : '订单号',
				binding : '订单号',
				width : '*',
				allowMerging : true
			}, {
				header : '中文名',
				binding : '中文名',
				width : '*',
				allowMerging : true
			},{
				header : '英文名',
				binding : '英文名',
				width : '*',
				allowMerging : true
			}, 
			//----------------bookroom info----------------------
			{
				header : '房类',
				binding : '房类',
				width : '*'
			}, {
				header : '抵店日期',
				binding : '抵店日期',
				width : '*',
				format : 'yyyy-MM-dd'
			}, {
				header : '离店日期',
				binding : '离店日期',
				width : '*',
				format : 'yyyy-MM-dd'
			}, {
				header : '订房数',
				binding : '订房',
				width : '*'
			}, {
				header : '留房数',
				binding : '留房',
				width : '*'
			}, {
				header : '抵达数',
				binding : '抵达数',
				width : '*'
			}, {
				header : '状态',
				binding : '状态',
				width : '*',
				allowMerging : true
			}, {
				header : 'checkId',
				binding : 'checkId',
				width : '*',
				allowMerging : true
			}, ],
			itemsSource : view,
		});
		grid.mergeManager = new wijmo.grid.CustomMergeManager(grid, '订单号');
		view.trackChanges = true;

	});

/**
 *查询散客预定入住
 */
function goSearch(code) {
	if (typeof grid != "undefined") {
		grid.dispose();
		grid = undefined;
		view = undefined;
	}
	$("#symbol").val("");
	$("#codeLetter").val("");
	if (code != 0) {
		if (code != "*" && code != "#") {
			$("#codeLetter").val(code);
		} else {
			if (code == "*") { //无效
				$("#symbol").val(1);
			}
			if (code == "#") { //有效
				$("#symbol").val(0);
			}
		}
	} else {
		var inputV = true;
		var bookStat = $("#bookStat").val();
		if (bookStat) {
			inputV = false;
		} else {
			$(".roomStatus input[type='text']").each(function() {
				if ($(this).val() != "") {
					inputV = false;
					return false;
				}
			});
		}
		if (inputV) {
			alert("请输入条件");
			return false;
		}

	}

	$.ajax({
		type : "POST",
		url : path + "/bookAccommodate/queryGuestDoc.do",
		data : $("#guestAccoSearchForm").serialize(),
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				
				var list = msg.attributes.list;
				var count = list.length;
				var data = list;
				var number = 1;
				var tempCheckId = list[0].checkId;
				var array = [];
				
				for ( var i = 0; i < count; i++) {
					var status = "";
					if (data[i].bookStat == "B") {
						status = "预定";
					} else if (data[i].bookStat == "C") {
						status = "取消";
					} else if (data[i].bookStat == "O") {
						status = "确认预定";
					} else if (data[i].bookStat == "P") {
						status = "过期预定";
					} else if (data[i].bookStat == "R") {
						status = "部分抵达";
					} else if (data[i].bookStat == "A") {
						status = "全部抵达";
					} else if (data[i].bookStat == "N") {
						status = "NO SHOW";
					}
		
					if (tempCheckId != data[i].checkId) {
						tempCheckId = data[i].checkId;
						number++;
					}
		
					array.push({
						序号 : number,
						checkId : data[i].checkId,
						订单号 : data[i].bookList,
						英文名 : data[i].gstNamee,
						中文名 : data[i].gstNamec,
						合约单位 : data[i].taName,
						电话 : data[i].mobile,
						抵店日期 : data[i].reachDate,
						离店日期 : new Date(data[i].leaveDate),
						状态 : status,
						房类 : data[i].codeNamec,
						订房 : data[i].bookNum,
						留房 : data[i].saveNum,
						抵店日期 : data[i].breachDate,
						离店日期 : data[i].bleaveDate,
						房价 : data[i].broomPrice,
						取消数 : data[i].cancelNum,
						NOSHOW数 : data[i].noShowNum,
						抵达数 : data[i].reachNum,
					});
		
				}
				view = new wijmo.collections.CollectionView(array);
				grid = new wijmo.grid.FlexGrid('#guestAccommodate', {
					autoGenerateColumns : false,
					allowMerging : wijmo.grid.AllowMerging.All,
					isReadOnly : true,
					selectionMode : wijmo.grid.SelectionMode.RowRange,
					columns : [ {
						header : '序号',
						binding : '序号',
						width : 50,
						isReadOnly : true,
						allowMerging : true
					}, {
						header : '订单号',
						binding : '订单号',
						width : '*',
						allowMerging : true
					}, {
						header : '英文名',
						binding : '英文名',
						width : '*',
						allowMerging : true
					}, {
						header : '中文名',
						binding : '中文名',
						width : '*',
						allowMerging : true
					},
					//----------------bookroom info----------------------
					{
						header : '房类',
						binding : '房类',
						width : '*'
					}, {
						header : '抵店日期',
						binding : '抵店日期',
						width : '*',
						format : 'yyyy-MM-dd'
					}, {
						header : '离店日期',
						binding : '离店日期',
						width : '*',
						format : 'yyyy-MM-dd'
					}, {
						header : '订房数',
						binding : '订房',
						width : '*'
					}, {
						header : '留房数',
						binding : '留房',
						width : '*'
					}, {
						header : '抵达数',
						binding : '抵达数',
						width : '*'
					}, {
						header : '状态',
						binding : '状态',
						width : '*',
						allowMerging : true
					}, {
						header : 'checkId',
						binding : 'checkId',
						width : '*',
						allowMerging : true
					}, ],
					itemsSource : view,
				});
				
				
				
			}
		}

	});

}
	
	
	//清空条件	
function clearText(){
	alert();
	$("#guestAccoSearchForm").resetForm();
	//重新设置有效按钮选中
	//document.getElementById("effect").checked=true;
}

/*右侧TAB页切换DIV*/
$("#userCheckIn").click(function() {
	$(".checkLogin").css("display", "block");
	$(".currentState").css("display", "none");
	$(".yd-TD").addClass("displayNone");
	$(".yd-SK").removeClass("displayNone");

});
$("#userCurrentState").click(function() {
	$(".checkLogin").css("display", "none");
	$(".currentState").css("display", "block");
	$(".yd-SK").addClass("displayNone");
	$(".yd-TD").removeClass("displayNone");
});
/*END右侧TAB页切换DIV*/
/*时间控件*/
$('.datetimepicker').datetimepicker();
/*时间控件结束*/
</script>
</html>
