<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理</title>
<style type="text/css">
	.eidt-row-back{
		color: #000000;
	}
	.eidt-row-blue{
		color: blue;
	}
</style>
</head>

<body>
	<!--banner & menu  xingli  2015-09-08-->
	<%@ include file="../header.jsp"%>
	<!--secondMenu-->
	<%@ include file="secondMenu.jsp"%>
	<!--banner & menu -END -->
	<!--center xingli 2015-09-08-->
	<div class="center"></div>
	<!--center -END -->
	<!--copyRight xingli 2015-09-08-->
	<%@ include file="../footer.jsp"%>
	<!--copyRight -END -->
	<!--弹出层阴影-->
	<div class="alertDivBg2"></div>
	<div class="alertDivBg3"></div>
	<!--换房弹出框-->
	<div class="alertDiv moveBar alertDiv2 changeRoomDiv displayBlock"
		style="position:inherit;">
		<div class="alertMain greyBg"
			style="width:830px;position:absolute;left:50%;top:0;margin-left:-25%;">
			<h4 class="moveDivAlert2">
				换房/续住<a href="javascript:;" class="closeDiv"></a>
			</h4>
			<div class="borderSolid">
				<table width="20%" class="margin-left-10">
					<tr>
						<td width="50%" align="left"><label><input
								class="chaRomPoint1" name="chaRom" type="radio" checked>
				<input type="hidden" id="radio_check" value="H"/><span
								class="margin-left-10">换房</span> </label></td>
						<td align="left"><label><input class="chaRomPoint2"
								name="chaRom" type="radio"><span class="margin-left-10">续住</span>
						</label></td>
					</tr>
				</table>
				<div class="chaRom1 margin-top-20">
					<!--换房续住-->
					<table width="100%">
						<tr>
							<td width="80">
								<h5 class="fontWeight">房号:</h5> <input
								class="input margin-top-10 floatL widthB40" type="text"
								id="room_idA" name="room_idA" value="" onkeyup="selectA(event);">
								<span class="floatL margin-top-15 margin-left-5 fontWeight"
								id="curr_statA"></span>
								<div class="clearBoth"></div> <a class="button_03 margin-top-10"
								href="javascript:provideRoomCard(1);">发房卡</a>
							</td>
							<td>
								<div class="tableDiv" id="roomsAdiv"
									style="width:600px;height:160px;"></div>
							</td>
						</tr>
						<tr>
							<td width="80">&nbsp;</td>
							<td colspan="2" align="center"><a
								class="roomPointBottom floatL margin-bottom-10 margin-top-10 margin-left-200"
								href="javascript:;" id="c_ida" onclick="moveAtoB();"></a> <a
								class="roomPointTop floatL margin-bottom-10 margin-top-10 margin-left-30"
								href="javascript:;" id="c_idb" onclick="moveBtoA();"></a>
							</td>
						</tr>
						<tr>
							<td width="80">
								<h5 class="fontWeight">房号:</h5> <input
								class="input margin-top-10 floatL widthB40" type="text"
								id="room_idB" name="room_idB" value="" onkeyup="selectB(event);">
								<span class="floatL margin-top-15 margin-left-5 fontWeight"
								id="curr_statB"></span>
								<div class="clearBoth"></div> <a class="button_03 margin-top-10"
								href="javascript:provideRoomCard(2);">发房卡</a>
							</td>
							<td>
								<!--table-->
								<div class="tableDiv" id="roomsBdiv"
									style="width:600px;height:160px;"></div> <!--table -END- -->
							</td>
						</tr>
					</table>
					<div class="alertRight clearBoth margin-top-30">
						<a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="exitH" onclick="exitdiv();return false;">退出</a> 
						<a class="buttonLikeA floatR margin-right-10" href="javascript:;"  style="color: grey;cursor:not-allowed;" id="cancelH">放弃</a> 
						<a class="buttonLikeA floatR margin-right-10" href="javascript:;" style="color: grey;cursor:not-allowed;" id="okH">确定</a>
					</div>
					<div class="clearBoth"></div>

				</div>

				<div class="chaRom2 margin-top-10 displayNone">
					<table width="100%">
						<tr>
							<td width="80%" valign="top">
								<!--table-->
								<div class="tableDiv" id="divchangetoroom"
									style="width:600px;height:350px;"></div>
								<table width="100%" class="margin-top-10">
									<tr>
										<td width="10%" align="right">房号</td>
										<td width="25%"><input class="input" type="text" id="room_idP" name="room_idP" onkeydown="selectRoom(event);" value="" ></td>
										<td width="25%"><label><input type="checkbox" id="with_roomP" class="checkbox margin-right-5" value="" onclick="searchToData(true);">显示同来房</label></td>
										<td align="right"><label><input type="checkbox" class="checkbox margin-right-5" id="modify_bpaidP">同时修改分账日期</label></td>
									</tr>
									<tr>
										<td width="20%" align="right">团代码/团名</td>
										<td width="25%"><input class="input" type="text" id="grp_id_nameP" name="grp_id_nameP" onkeydown="selectRoom(event);" value=""></td>
									</tr>
								</table> <!--table -END- -->
							</td>
							<td valign="top">
							<a class="button_03 margin-left-5" href="javascript:;" style="color: grey;cursor:not-allowed;" id="okP">确定</a> 
							<a class="button_03 margin-left-5" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancelP">放弃</a> 
							<a class="button_03 margin-left-5" href="javascript:;" onclick="modifyLeave();">批量预离</a> 
							<a class="button_03 margin-left-5" href="javascript:;" id="changeLeaveTime">批量续住</a>
							<a class="button_03 margin-left-5" href="javascript:;" id="giveRoomCard" onabort="provideRoomCard(3);">发房卡</a> 
							<a class="button_03 margin-left-5" href="javascript:;" id="exitP" onclick="exitdiv();return false;">退出</a>
							</td>
						</tr>
					</table>
				</div>
				<!--换房续住-->


			</div>
		</div>
	</div>
	<!--修改离店时间-->
	<div class="alertDiv moveBar2 alertDiv3 changeLeaveTimeDiv">
		<div class="alertMain greyBg" style="width:460px;margin-top:300px;">
			<h4 class="moveDivAlert2">
				修改离店日期<a href="javascript:;" class="closeDiv2 closeAlertTips"></a>
			</h4>
			<div class="borderSolid padding-0 whiteBackground">
				<table class="margin-bottom-50 margin-top-50">
					<tr>
						<td width="150" align="right">新离店日期</td>
						<td><input class="input ui-widget-content ui-corner-all Wdate" type="text"
							id="newleavedate" name="newleavedate" value="" readonly="readonly"></td>
					</tr>
				</table>
				<div class="alertTipsBottom">
					<a class="button_06 floatR margin-right-10" href="javascript:;" id="cancel">取消</a>
					<a class="button_06 floatR margin-right-10" href="javascript:;" onclick="queding();">确定</a>
				</div>
			</div>
		</div>
	</div>
	<!--/修改离店时间END-->
	<!--/换房弹出框-->
	<!--弹出层阴影结束-->
	<input type="hidden" id="path" name="path"
		value="${pageContext.request.contextPath}">
	<!-- 页面缓存两个房间住客信息 -->
	<input type="hidden" id="with_id_A" name="with_id_A" value="">
	<input type="hidden" id="with_id_B" name="with_id_B" value="">
	<input type="hidden" id="room_id_A" name="romi_id_A" value="">
	<input type="hidden" id="room_id_B" name="romi_id_B" value="">
	<input type="hidden" id="gst_type_A" name="guest_type_A" value="">
	<input type="hidden" id="gst_type_B" name="guest_type_B" value="">
	<input type="hidden" id="userid" value="<shiro:principal/>">
	<input type="hidden" id="username" value="<mammoth:UserInfo propertyName="operName" />">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rooms/wards_continuedtolive.js"></script>
</body>
</html>
