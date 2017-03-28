<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!--预订登记-->
<div id="bookRegistration"
	class="alertDiv checkInDiv bookRegistration moveBar">
	<div class="alertMain greyBg" style="width:1040px; margin-top:130px;">
		<h4 class="moveDivAlert">
			预订登记<a href="javascript:closeBookRegistration();" class="closeDiv"></a>
		</h4>
		<div class="borderSolid">
			<!--留房弹出框左侧-->
			<div class="leaveTheRoomL">
				<ul id="roomStateList" class="statusUl height400">

				</ul>
				<!--留房表格Table-->
				<div class="tableDiv whiteBackground floatL" style="width:552px;margin-top:7px;height: 190px;" id="bookCheckInInfoList"></div>
				<ul id="alreadyCheckInPersonListUL" class="roomNum floatR roomUl marginRight-2" style="overflow-y: auto;">
				</ul>
				<!--/留房表格Table-->
			</div>
			<!--留房弹出框左侧END-->
			<!--留房弹出框右侧-->
			<div class="leaveTheRoomR">
				<div class="roomStatus">
					<div class="choice padding-bottom-10">
						<table width="60%" class="margin-left-50">
							<tr>
								<td align="center">
									<label>
										<input name="radio1" type="radio" checked="checked"><span class="margin-left-10">可售房</span> 
									</label>
								</td>
								<td align="center">
									<label>
										<input name="radio1" type="radio"><span class="margin-left-10">全部</span> 
									</label>
								</td>
							</tr>
						</table>
						
						<!-- 记录页面初始化的时候的数据 -->
						<input type="hidden" id="bookId_first" name="bookId_first">
						<input id="reachDate_first" name="reachDate_first" type="hidden"/>
						<input id="leaveDate_first" name="leaveDate_first" type="hidden"/>
						<form id="checkInputForm" name="checkInputForm" action="" method="post">
							<input type="hidden" id="checkId" name="checkId">
							<input type="hidden" id="grpChkid" name="grpChkid">
							<input type="hidden" id="bookList" name="bookList">
							<input type="hidden" id="bookId" name="bookId">
							<input type="hidden" id="withId" name="withId">
							<input type="hidden" id="bookItemNums" name="bookItemNums">
							
							<input id="reachDate_hidden" name="reachDate" type="hidden"/>
							<input id="leaveDate_hidden" name="leaveDate" type="hidden"/>
							<div id="roomSateDiv" class="roomNumber margin-top-20 positionR margin-left-25" style="width:90%;">
								<div class="positionA divIndexTopWord">筛选</div>
								<table width="100%">
									<tr>
										<td width="54" align="right">楼名</td>
										<td>
											<select id="buildId" name="buildId" class="select widthB100" onchange="buildChange(this)">
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">楼层</td>
										<td>
											<select id="floorNo" name="floorNo" class="select widthB100">
												<option value="">全部</option>
											</select>
										</td>
									</tr>
									<tr>
										<td align="right">房类</td>
										<td>
											<table width="100%">
												<tr>
													<td style="padding:0;">
														<input class="input" name="roomType" id="roomType" type="text" readonly="readonly" onclick="ajaxRoomType()">
														<input class="input" name="roomTypeVal" id="roomTypeVal" type="hidden">
													</td>
													<td width="28" align="right"><a
														class="SFbutton floatR marginRight-4" href="javascript:ajaxRoomType();">···</a>
													</td>
												</tr>
											</table></td>
									</tr>
									<tr>
										<td align="right">特征</td>
										<td>
											<table width="100%">
												<tr>
													<td style="padding:0;">
														<input class="input" name="roomCharater" id="roomCharater" type="text" readonly="readonly" onclick="ajaxRoomCharacter()"> 
														<input class="input" name="roomCharaterTotalVal" id="roomCharaterTotalVal" type="hidden">
														<input class="input" name="roomCharaterTotalVal_1" id="roomCharaterTotalVal_1" type="hidden">
													</td>
													<td width="28" align="right">
														<a class="SFbutton floatR marginRight-4" href="javascript:ajaxRoomCharacter();">···</a>
													</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<!-- <dt>抵店</dt>
	                            <dd><input class="input gry_9" id="reachDate" name="reachDate" type="text" readonly="readonly"/></dd>
	                            <dt>离店</dt>
	                            <dd><input class="input gry_9" id="leaveDate" name="leaveDate"type="text" readonly="readonly"/></dd> -->
								<div class="clearBoth"></div>
							</div>
						</form>
						<table id="roomSateTable" width="100%">
							<tr>
								<td width="80" align="right">抵店</td>
								<td>
									<input class="input gry_9" id="reachDate_readonly" name="reachDate_readonly" type="text" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td align="right">离店</td>
								<td>
									<input class="input gry_9" id="leaveDate_readonly" name="leaveDate_readonly" type="text" readonly="readonly" />
								</td>
							</tr>
						</table>
						<div class="clearBoth"></div>
						<a class="button_06 floatL margin-left-30" href="javascript:checkInputFormSearch(true , false , false);">条件查询</a>
						<a class="button_06 floatL margin-left-25" href="javascript:clearCheckInputForm();">清空条件</a>
						<div class="clearBoth"></div>
						<div class="margin-top-5" style="width:245px;margin-left:25px;">
							<div class="floatL">
								<ul id="saveRoom_ul" class="guestUl4 height140"></ul>
								<a id="saveRoom_a" href="javascript:showCurrentRoomInfo('saveRoom');" class="checkRoom textDecoration">留房：<span id="saveRoom_span">0</span></a>
							</div>
							<div class="floatL margin-top-10  margin-left-10">
								<a class="forSN2 margin-top-30 rightMove" href="javascript:rightMoveRoom();">
									<img width="17" src="${ctx}/img/right_01.png"> 
								</a> 
								<a class="forSN2 margin-top-10 rightMove" href="javascript:rightMoveAllRoom();">
									<img width="17" src="${ctx}/img/right_02.png"> 
								</a>
							</div>
							<div class="floatR">
								<ul id="selectRoom_ul" class="guestUl4 height140"></ul>
								<a id="selectRoom_a" href="javascript:showCurrentRoomInfo('selectRoom');" class="checkRoom textDecoration">选定：<span id="selectRoom_span">0</span></a>
							</div>
						</div>
						<div class="clearBoth margin-bottom-10"></div>
						<a id="confirmSelect" class="button_06 floatL margin-left-30" href="javascript:;" style="cursor: not-allowed;color: grey;" <mammoth:AuthJudge funcId="c_567215" />>确定选中</a> 
						<a id="confirmGiveUpSelect" class="button_06 floatL margin-left-25" href="javascript:;" style="cursor: not-allowed;color: grey;" <mammoth:AuthJudge funcId="c_517189" />>全部放弃</a> 
						<a id="autoSelectRooom" class="button_06 floatL margin-left-30" href="javascript:;" style="cursor: not-allowed;color: grey;" <mammoth:AuthJudge funcId="c_690656" />>自动排房</a> 
						<a id="confirmAllReach" class="button_06 floatL margin-left-25" href="javascript:confirmAllReach();">全部抵达</a> 
						<a id="saveCheckIn" class="button_06 floatL margin-left-30" href="javascript:;">办理入住</a> 
						<a class="button_06 floatL margin-left-25" href="javascript:closeBookRegistration();">退&nbsp;&nbsp;出</a>
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
<style type="text/css">
/* .borderSolid .leaveTheRoomL .statusUl {
	overflow-y: auto;
}
.forSN2_hover {
	background: url(${ctx}/img/point_left_06_bg.png) repeat-x;
}

.forSN2_hover img {
	-webkit-filter: brightness(1000%);
}
 */
.statusUl .statusLi2 .pointStatus_block_av {
	width: 100%;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	background: #000;
	z-index: 7;
	border-radius: 5px;
	background: url(${ctx}/img/opacity.png) repeat;
}
.statusUl .statusLi2 .pointStatusRight_block_av {
	width: 34px;
	height: 34px;
	position: absolute;
	bottom: 5px;
	left: 5px;
	opacity: 1;
	z-index: 8;
}

ul.guestUl4 li {
	cursor: pointer;
}

.clickNo {
	color: grey;
	cursor: not-allowed;
}

.forbid_select_yellow {
	background-color: yellow;
}

.forbid_select_green {
	background-color: #e4f1d0;
}

.selectedCurrentLi {
	background-color: #96bc5b;
}
#saveRoom_ul li ,#selectRoom_ul li  {
	padding: 1px 0px 1px 1px;
}
#saveRoom_ul li:hover,#selectRoom_ul li:not(.clickNo):hover {
	background-color: #96bc5b;
}

.wj-state-selected {
	color: inherit;
}

.guestUl4 {
	overflow-y: auto;
	overflow-x: hidden;
}
</style>
<!--/预订登记-->
<!--  房间特征 -->
<%@ include file="../../syssetting/roomCharaterAlert.jspf"%>
<!--  房间特征 -->
<!--  房间类型 -->
<%@ include file="roomTypeAlert.jspf"%>
<!--  房间类型 -->
<!--选定房间弹出框-->
<%@ include file="selectRoomsAlert.jsp"%>
<!--/选定房间弹出框-->