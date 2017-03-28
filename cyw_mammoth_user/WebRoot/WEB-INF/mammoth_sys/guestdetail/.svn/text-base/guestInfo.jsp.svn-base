<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<div class="clearBoth GuestTabIn userDetails" style="display:block;" datashow-id="guestdetal_tabguestinfo" id="guestdetail_userDetails">
	<div class="GuestTabL">
		<div class="guestBgF">
			<!--table-->
			<div class="tableDiv floatL margin-left-10" id="guestinfo_guestRoomId" style="height:120px">
			</div>
			<div class="suerPhoto">
				<dl>
					<dt>熟客编号</dt>
					<dd>
					<table width="100%">
                        <tr>
                            <td style="padding:0;">
                                <input class="input widthB100" name="gstId" type="text"   disabled="disabled">
                   			 </td>
                            <td  width="30px" align="right" style="padding:0;">
                                <a class="HYbutton" href="javascript:;"></a>
                            </td>
                        </tr>
                   </table>
					</dd>
					<dt>会员卡号</dt>
					<dd>
						<input class="input" name="" type="text">
					</dd>
				</dl>
			</div>
			<div class="tableDivDow clearBoth margin-bottom-10 floatL">
				<table width="99%">
					<tr>
						<td width="70" align="right">中文名</td>
						<td width="130" id="guestinfo_append">
							<input class="input validate[required]"   label="中文名" name="gstNamec" id="guestinfo_gstNamec" type="text" value="${fn:trim(detail.gstNamec)}">
						</td>
						<td width="70" align="right">性别</td>
						<td width="130">
							<select class="select widthB100" name="sex" id="guestinfo_sex" >
								<c:forEach items="${genderlist}" var="hcode">
									<option value="${hcode.codeId}" <c:if test="${detail.sex==hcode.codeId}"> selected </c:if> >${hcode.codeNamec}</option>
								</c:forEach>
							</select>
						</td>
						<td width="55" align="right">民族</td>
						<td>
							<select class="select widthB93" name="folk" id="guestinfo_folks">
								<c:forEach items="${hfolklist}" var="hcode">
									<option value="${fn:trim(hcode.codeId)}" <c:if test="${fn:trim(detail.folk)==fn:trim(hcode.codeId)}"> selected </c:if> >${hcode.codeNamec}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td  align="right">英文名</td>
						<td ><input class="input" name="gstNamee" type="text" value="${detail.gstNamee}" id="guestinfo_gstNamee">
						</td>
						<td align="right">生日</td>
						<td><input class="input" name="birthday" id="guestinfo_birthday" onclick="WdatePicker()" value="${fn:substring(detail.birthday,0,10)}" type="text"  >
						</td>
						<td align="right">国籍</td>
						<td>
							<select class="select widthB93" name="ntId" id="guestinfo_country">
								<c:forEach items="${countrylist}" var="hcode">
									<option value="${fn:trim(hcode.codeId)}" <c:if test="${fn:trim(detail.ntId)==fn:trim(hcode.codeId)}" > selected </c:if> >${hcode.codeNamec}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">电话</td>
						<td><input class="input" name="tele" type="text">
						</td>
						<td align="right">证件</td>
						<td>
							<select class="select widthB100" name="crdkindId" id="guestinfo_crdkindId">
								<c:forEach items="${crdlist}" var="hcode">
									<option value="${hcode.codeId}" <c:if test="${detail.crdkindId==hcode.codeId}" > selected </c:if> >${hcode.codeNamec}</option>
								</c:forEach>
							</select>
						</td>
						<td align="right">省市</td>
						<td>
							<select class="select widthB93" name="native_">
								<c:forEach items="${prolist}" var="hcode">
									<option value="${hcode.codeId}" <c:if test="${detail.native_==hcode.codeId}" > selected </c:if> >${hcode.codeNamec}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Email</td>
						<td><input class="input widthB960" name="email" type="text" data-validation="isEmail" label="Email" value="${detail.email}">
						</td>
						<td align="right">证号</td>
						<td colspan="3"><input class="input widthB95" name="crdId" id="guestinfo_crdId" type="text" value="${detail.crdId}">
						</td>
					</tr>
					<tr>
						<td align="right">车牌号</td>
						<td><input  class="input widthB960" name="plateNumber" type="text" value="${detail.plateNumber}" >
						</td>
						<td align="right">住址</td>
						<td colspan="3">
							<input class="input widthB95" name="addr" type="text" value="${detail.addr}">
						</td>
					</tr>
					<tr>
						<td align="right">备注</td>
						<td colspan="5"><input class="input widthB97" name="notice" type="text" value="${detail.notice}"></td>
					</tr>
				</table>
			</div>
			<div class="floatR userImg margin-right-30">
				<img class="" src="" id="crdPhoto" onerror="javascript:this.src='/img/user_none2.png'"> <a
					class="button_02" id="otherInformation" href="javascript:;">其他信息</a>
			</div>
			<!--table -END- -->
			<div class="clearBoth"></div>
		</div>
		<!--房间编号-->
		<div class="roomNumber">
			<div
				class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
				<table width="99%">
					<tr>
						<td align="right" width="70">房间编号</td>
						<td  width="70"><input class="input"  id="guestinfo_room_id" name="room_id" type="text" disabled="true" value="${detail.roomId}">
							<input class="input" name="roomId" id="guestinfo_roomId" type="hidden" value="${detail.roomId}">
						</td>
						<td align="right" width="70">支付方式</td>
						<td  width="115">
							<select class="select" name="payId" style="width:100%;">
								<c:forEach items="${hsettls}" var="hcode">
									<option value="${hcode.codeId}" <c:if test="${fn:trim(detail.payId)==fn:trim(hcode.codeId)}">selected</c:if> >${hcode.codeNamec}</option>
								</c:forEach>
							</select>
						</td>
						<td align="right" width="65">客人来源</td>
						<td colspan="3">
							<select class="select" name="gstOriId" id="guestinfo_gstOriId" style="width:102%;">
								<c:forEach items="${gstSrcList}" var="gstscr">
									<option value="${gstscr.codeId}" src-type="${gstscr.srcType}">${gstscr.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">入住时间</td>
						<td colspan="3">
							<input class="input time_02" style="width:97%;" type="text" id="guestinfo_reach_date" name="reach_date" disabled="true" value="${fn:substring(detail.reachDate,0,10)}">
							<input name="reachDate" type="hidden" id="guestinfo_reachDate" value="${fn:substring(detail.reachDate,0,10)}">
						</td>
						<td align="right">客人分类</td>
						<td colspan="3">
							<select class="select" name="gstKind" style="width:102%;">
								<c:forEach items="${gstTypelist}" var="hcode">
									<option value="${hcode.codeId}">${hcode.codeNamec}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">离店时间</td>
						<td colspan="3">
							<input class="input time_02" id="guestinfo_leave_date" name="leave_date" disabled="true" type="text" style="width: 97%" value="${fn:substring(detail.leaveDate,0,10)}">
							<input name="leaveDate" type="hidden" id="guestinfo_leaveDate" value="${fn:substring(detail.leaveDate,0,10)}">
						</td>
						<td align="right">合约单位</td>
						<td colspan="3">
                            <input class="input"  id="guestinfo_theCompany" type="text" style="width:83%;" >
            				<input type="hidden" id="guestinfo_company_id" name ="compId" value="" label="合约单位"/>
                   			<input type="hidden" id="guestinfo_ta_type" name ="compType" value="" label="合约单位"/>
                            <a class="HYbutton" href="javascript:;" style="float:right"></a>
                        </td>
					</tr>
					<tr>
						<td align="right" width="70">标准价</td>
						<td><input class="input" name="price" type="text" style="text-align:right" id="guestinfo_normalPrice" disabled="disabled" value="${detail.price}"></td>
						<td align="right" width="70">折扣率</td>
						<td><input class="input" style="width:93%" name="" type="text"  onkeypress="calPress(event)" id="guestinfo_discount" ></td>
						<td align="right" width="65">A账余额</td>
						<td><input class="input" name="" style=" text-align:right" type="text"  id="guestinfo_remainA" disabled="disabled" value="${detail.borrow-detail.lent}"></td>
						<td align="right" width="65">A账限额</td>
						<td><input class="input" style=" text-align:right" name="Alimit" type="text"  id="guestinfo_Alimit" value="${detail.alimit}"></td>
					</tr>
					<tr>
						<td width="70" align="right">当前房租</td>
						<fmt:formatNumber value="${detail.roomPrice}" var="fmt_roomPrice"  pattern="#.00" />
						<td width="70">
							<input class="input" name="roomPrice" type="text" id="guestinfo_nowPrice" value="${fmt_roomPrice}" style="text-align:right">
						</td>
						<td colspan="2" >
							<input type="checkbox" value="true" id="guestinfo_enableRoomPlan" data-editable="" data-roomPlan_price="${fmt_roomPrice}" ${isRoomPlan == "true" ? "checked" : ""}/>
							<input type="hidden" value="${isRoomPlan}" id="guestinfo_isRoomPlan" name="isRoomPlan" />
							<span id="guestinfo_enableRoomPlan_span">启用房价方案</span>
							<a id="guestinfo_pricesList" href="javascript:;" style="margin-left:10px">房价列表</a>
						</td>
						<td align="right">B账余额</td>
						<td ><input class="input" name=""  style="text-align:right"type="text" id="guestinfo_remainB" disabled="true" value="${detail.bborrow-detail.blent}"></td>
						<td align="right">B账限额</td>
						<td><input class="input" style="text-align:right" name="Blimit" type="text" id="guestinfo_Blimit" value="${detail.blimit}" ></td>
					</tr>
				</table>
			</div>
		</div>
		<ul class="payMan">
			<li><label><input type="checkbox" <c:if test="${detail.paymanFlag==1}">checked disabled </c:if> id="guestinfo_payman_flag">付费人</label>
			</li>
			<li><label><input type="checkbox" <c:if test="${detail.cityLedger==true}">checked</c:if> id="guestinfo_city_ledger">可挂AR账</label>
			</li>
			<li><label><input type="checkbox" <c:if test="${detail.housePay==true}">checked</c:if> id="guestinfo_house_pay">可挂房账</label>
			</li>
			<li><label><input type="checkbox" <c:if test="${detail.freeSvc==true}">checked</c:if> id="guestinfo_free_svc">免服务费</label>
			</li>
			<li><label><input type="checkbox" <c:if test="${detail.hideprice==true}">checked</c:if> id="guestinfo_hideprice">隐藏房价</label>
			</li>
			<li><label><input type="checkbox" <c:if test="${detail.ifFgst==true}">checked</c:if> id="guestinfo_if_fgst">自动转熟客</label>
			</li>
			<li><input class="buttonBefore" name="" type="checkbox" id="accountSetId"  disabled="disabled"
				value="" <c:if test="${detail.isBpaid}">checked</c:if>      ><a id="guestinfo_split" class="button_02" 
				href="javascript:;" <mammoth:AuthJudge funcId="c_232183" />>分账设置</a>
			</li>
		</ul>
		<!--/房间编号-->
		<div
			class="tableDivDow clearBoth margin-bottom-10 floatL  botInf" >
			<table width="98%">
				<tr>
					<td align="right" width="80">通话级别</td>
					<td width="120"><select class="select"
						style="width:110px;">
							<option>国际长途</option>
							<option>市内</option>
							<option>国内长途</option>
					</select></td>
					<td width="50">
						<label>
							<input class="checkbox" name="" type="checkbox" value="">叫醒
						</label>
					</td>
					<td width="100"><input class="input" name="" type="text">
					</td>
					<td align="right" class="fontWeight" width="60">免打扰</td>
					<td>
						<label class="margin-right-10"><input class="radio" name="disturb" type="radio" value=""> 取消</label>
						<label><input class="radio" name="disturb" type="radio" value=""> 设置</label>
					</td>
					<td align="right";>
						<a class="rightALink"	href="javascript:;"></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--右侧部分-->
	<div class="GuestTabR">
		<a	class="button_03 disabledAButton" href="javascript:;" id="guestinfo_confirm" <mammoth:AuthJudge funcId="c_492293" />>确定(O)</a>
		<a	class="button_03 disabledAButton" href="javascript:;" id="guestinfo_giveup" <mammoth:AuthJudge funcId="c_814982" />>放弃(C)</a>
		<a  class="button_03" href="javascript:;" id="guestinfo_addGuest" <mammoth:AuthJudge funcId="c_529312" />>新增客人</a>
		<a	class="button_03" href="javascript:;" id="guestinfo_copyGuest" <mammoth:AuthJudge funcId="c_279816" />>复制客人</a>
		<a	class="button_03" href="javascript:;" id="guestinfo_scanCardbtn" <mammoth:AuthJudge funcId="c_214980" />>扫描身份证</a>
		<a	class="button_03" href="javascript:;" id="guestinfo_sendRoomCard" <mammoth:AuthJudge funcId="c_311781" />>发房卡</a>
		<a	class="button_03" href="javascript:enter();" id="guestinfo_changeRoom" <mammoth:AuthJudge funcId="c_456864" />>换房/续住</a>
		<a  class="button_03" href="javascript:;" id="guestinfo_guestDetailQuit">退出</a>
		<ul class="guestUl userBlock" style="width:130px;margin-left:9px;">
			<li class="fontWeight">房间列表</li>
		</ul>
		<ul class="guestUl2 margin-left-20" id="guestinfo_roomList" style="width:130px;height:260px;margin-left:9px;overflow-y:scroll;">
			<c:forEach items="${roomIds}" var="room" varStatus="status">
				<li><a href='javascript:;' guestinfo-roomid="${room}">${room}</a>
			</c:forEach>
		</ul>
	</div>
	<!--/右侧部分-->
</div>

<!--换房弹出框-->
	<div id="cr_div" class="alertDiv moveBar alertDiv2 changeRoomDiv" style=" display: none; width:1036px; height:830px; ">
		<div class="alertMain greyBg" style="width:830px;position:absolute;left:50%;top:0;margin-left:-415px; z-index:10">
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
	<style type="text/css">
	.eidt-row-back{
		color: #000000;
	}
	.eidt-row-blue{
		color: blue;
	}
</style>