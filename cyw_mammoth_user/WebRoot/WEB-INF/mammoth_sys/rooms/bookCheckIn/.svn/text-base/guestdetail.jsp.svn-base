<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<input class="input" id="guestinfo_hairRoomCard" type="hidden" value="${hairRoomCard}"><!-- 是否启用房卡-->
	<input class="input" id="guestinfo_scanCard" type="hidden" value="${scanCard}"><!-- 是否启用扫描身份证-->
    <form id="form1" >
	<div class="alertDiv checkInDiv moveBar checkDetailsDiv" id="checkDetailsDiv">
		<div class="alertMain greyBg rzLogin" style="margin-top:5px;">
			<h4 class="moveDivAlert" id="MoveAlertDiv">
				<span id="guestdetal_info_title">客单详情（在住）</span><a href="javascript:;" class="closeDiv hideDivOne" onclick="quitWindow()"></a>
			</h4>
			<div class="borderSolid">
				<input class="input" name="checkId" type="hidden" id="guestCheckId">
				<input class="input" name="withId" type="hidden">
				<input class="input" name="billbId" type="hidden">
				<input class="input" name="billaId" type="hidden">
				<input class="input" name="grpChkid" type="hidden">
				<table class="checkInTitle" width="100%">
					<tr>
						<td width="8%" align="right">团代码</td>
						<td width="12%"><input class="input" name="grpId" type="text" disabled="disabled">
						</td>
						<td width="8%" align="right">团名</td>
						<td width="12%"><input class="input" name="grpName" type="text" disabled="disabled">
						</td>
						<td width="8%" align="right">房价方案</td>
						<td width="12%"><select class="select" name="prcSchemeId">
						</select></td>
						<td width="8%" align="right">销售员</td>
						<td><input class="input" name="" type="text" disabled="disabled">
						</td>
						<td width="140">&nbsp;</td>
					</tr>
					<tr>
						<td align="right"><p class="fontWeight font-13">入住类型:</p>
						</td>
						<td colspan="7" id="checkInType">  
							<span><label> <input class="radio" name="inType" type="radio" value="1" />普通</label> </span> 
  							<span><label><input class="radio" name="inType" type="radio" value="2" />合约</label> </span> 
  							<span><label><input class="radio" name="inType" type="radio" value="3" />钟点</label> </span> 
 							<span><label><input class="radio" name="inType" type="radio" value="4" />免费</label> </span> 
  							<span><label><input class="radio" name="inType" type="radio" value="5" />自用</label> </span>
						</td>
						<td width="140">&nbsp;</td>
					</tr>
				</table>
				<ul class="GuestTab clearBoth">
					<li class="point userTab1">客人详情</li>
					<li class="userTab2">客人账目</li>
				</ul>
				<div class="clearBoth GuestTabIn userDetails" style="display:block;" id="userDetails">
					<div class="GuestTabL">
						<div class="guestBgF">
							<!--table-->
							<div class="tableDiv floatL margin-left-10" id="guestRoomId" style="height:120px">
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
										<td width="130" id="append"><input class="input " data-validation="isHanZi" maxlength="5" label="中文名" onkeypress="namePress(event)" name="gstNamec" id="gstNamec" type="text">
										</td>
										<td width="70" align="right">性别</td>
										<td width="130"><select class="select widthB100" name="sex" id="sex" >
<!-- 												<option>男</option> -->
<!-- 												<option>女</option> -->
										</select></td>
										<td width="55" align="right">民族</td>
										<td><select class="select widthB93" name="folk" id="folks">
<!-- 												<option>汉</option> -->
<!-- 												<option>回</option> -->
<!-- 												<option>蒙</option> -->
<!-- 												<option>藏</option> -->
										</select></td>
									</tr>
									<tr>
										<td  align="right">英文名</td>
										<td ><input class="input" name="gstNamee" type="text" id="gstNamee">
										</td>
										<td align="right">生日</td>
										<td><input class="input" name="birthday" id="birthday" type="text"  readonly="readonly">
										</td>
										<td align="right">国籍</td>
										<td><select class="select widthB93" name="ntId" id="country">
<!-- 												<option>中国</option> -->
<!-- 												<option>美国</option> -->
										</select></td>
									</tr>
									<tr>
										<td align="right">电话</td>
										<td><input class="input" name="tele" type="text">
										</td>
										<td align="right">证件</td>
										<td><select class="select widthB100" name="crdkindId" id="crdkindId">
<!-- 												<option>身份证</option> -->
<!-- 												<option>驾驶证</option> -->
										</select></td>
										<td align="right">省市</td>
										<td><select class="select widthB93" name="native_">
<!-- 												<option>西安</option> -->
<!-- 												<option>北京</option> -->
<!-- 												<option>南京</option> -->
<!-- 												<option>洛阳</option> -->
										</select></td>
									</tr>
									<tr>
										<td align="right">Email</td>
										<td><input class="input widthB960" name="email" type="text" data-validation="isEmail" label="Email">
										</td>
										<td align="right">证号</td>
										<td colspan="3"><input class="input widthB95" name="crdId" id="crdId" onblur="getBirthdatByIdNo(this.value)" type="text">
										</td>
									</tr>
									<tr>
										<td align="right">车牌号</td>
										<td><input  class="input widthB960" name="plateNumber" type="text">
										</td>
										<td align="right">住址</td>
										<td colspan="3"><input class="input widthB95" name="addr"  
											type="text"></td>
									</tr>
									<tr>
										<td align="right">备注</td>
										<td colspan="5"><input class="input widthB97" name="notice" type="text"></td>
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
						<div class="roomNumber" style="margin-left:8px">
							<div
								class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
								<table width="99%">
									<tr>
										<td align="right">房间编号</td>
										<td><input class="input"  id="guest_room_id" name="room_id" type="text" disabled="true">
											<input class="input" name="roomId" id="roomId" type="hidden">
										</td>
										<td align="right">支付方式</td>
										<td><select class="select" name="payId">
<!-- 												<option>现金</option> -->
<!-- 												<option>信用卡</option> -->
										</select>
										</td>
										<td align="right">客人来源</td>
										<td colspan="3"><select class="select" name="gstOriId" id="gstOriId" style="width: 250px">
<!-- 												<option>自来散客</option> -->
<!-- 												<option>会员</option> -->
<!-- 												<option>网络分销</option> -->
<!-- 												<option>旅行社</option> -->
<!-- 												<option>其他</option> -->
										</select></td>
									</tr>
									<tr>
										<td align="right">入住时间</td>
										<td colspan="3"><input class="input time_02" style="width: 220px"
											class="time_02"  type="text" id="reach_date" name="reach_date" disabled="true">
											<input name="reachDate" type="hidden" id="reachDate" >
										</td>
										<td align="right">客人分类</td>
										<td colspan="3"><select class="select" name="gstKind" style="width: 250px">
<!-- 												<option>本地</option> -->
<!-- 												<option>外地</option> -->
<!-- 												<option>国外</option> -->
										</select></td>
									</tr>
									<tr>
										<td align="right">离店时间</td>
										<td colspan="3"><input class="input time_02" id="leave_date" name="leave_date" disabled="true"
											type="text" style="width: 220px">
											<input name="leaveDate" type="hidden" id="leaveDate" >
										</td>
										<td align="right">合约单位</td>
										<td colspan="3">
	                                    	<table width="100%">
	                                            <tr>
	                                                <td style="padding:0;">
		                                                <input class="input widthB98"  id="theCompany" type="text">
			                            				<input type="hidden" id="company_id" name ="compId" value="" label="合约单位"/>
		                                       			<input type="hidden" id="ta_type" name ="compType" value="" label="合约单位"/>
	                                       			 </td>
	                                                <td width="22" align="left" style="padding:3;">
	                                                    <a class="HYbutton" href="javascript:;"></a>
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </td>
									</tr>
									<tr>
										<td align="right">标准价</td>
										<td><input class="input" name="price" type="text" id="normalPrice" disabled="disabled"></td>
										<td align="right">折扣率</td>
										<td><input class="input" style="width:85%" name="" type="text" onkeypress="calPress(event)" id="discount"></td>
										<td align="right">A账余额</td>
										<td><input class="input" name="" style="width:80px" type="text" id="remainA" disabled="disabled"></td>
										<td align="right">A账限额</td>
										<td><input class="input" style="width:80px" name="Alimit" type="text" id="Alimit" ></td>
									</tr>
									<tr>
										<td width="70" align="right">当前房租</td>
										<td width="70"><input class="input" name="roomPrice" type="text" id="nowPrice">
										</td>
										<td width="70" align="right"><a id="pricesList"
											href="javascript:;">房价列表</a>
										</td>
										<td width="70">&nbsp;</td>
										<td width="70" align="right">B账余额</td>
										<td width="70"><input class="input" name=""  style="width:80px"type="text" id="remainB" disabled="true"></td>
										<td width="60" align="right">B账限额</td>
										<td><input class="input" style="width:80px" name="Blimit" type="text" id="Blimit"></td>
									</tr>
								</table>
							</div>
						</div>
						<ul class="payMan">
							<li><label><input name="" type="checkbox" value="" id="payman_flag">付费人</label>
							</li>
							<li><label><input name="" type="checkbox" value="" id="city_ledger">可挂AR账</label>
							</li>
							<li><label><input name="" type="checkbox" value="" id="house_pay">可挂房账</label>
							</li>
							<li><label><input name="" type="checkbox" value="" id="free_svc">免服务费</label>
							</li>
							<li><label><input name="" type="checkbox" value="" id="hideprice">隐藏房价</label>
							</li>
							<li><label><input name="" type="checkbox" value="" id="if_fgst">自动转熟客</label>
							</li>
							<li><input class="buttonBefore" name="" type="checkbox" id="accountSetId"  disabled="disabled"
								value=""><a id="split" class="button_02" 
								href="javascript:;">分账设置</a>
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
									<td width="50"><label><input class="checkbox"
											name="" type="checkbox" value="">叫醒</label>
									</td>
									<td width="100"><input class="input" name="" type="text">
									</td>
									<td align="right" class="fontWeight" width="60">免打扰</td>
									<td><input class="radio" name="disturb" type="radio"
										value=""> 取消 <input class="radio" name="disturb"
										type="radio" value=""> 设置</td>
									<td align="right";><a class="rightALink"
										href="javascript:;"></a></td>
								</tr>
							</table>
						</div>
					</div>
					<!--右侧部分-->
					<div class="GuestTabR">
						<a	class="button_03" href="javascript:;" onclick="savaGuest()" id="confirm">确定(O)</a> 
						<a	class="button_03" href="javascript:;" onclick="cancleAdd()" id="giveup">放弃(C)</a> 
						<a class="button_03" href="javascript:;" onclick="addGuest()" id="addGuest">新增客人</a> 
						<a	class="button_03" href="javascript:;" onclick="copyGuest()" id="copyGuest">复制客人</a> 
						<a	class="button_03" href="javascript:;" id="scanCard" onclick="startScanCard()">扫描身份证</a> 
						<a	class="button_03" href="javascript:;" id="sendRoomCard">发房卡</a> 
						<a	class="button_03" id="changeRoom" href="javascript:;" id="changeRoom">换房/续住</a> 
						<a class="button_03" href="javascript:;" onclick="closeWindow()" id="guestDetailQuit">退出</a>
						<ul class="guestUl userBlock" style="width:130px;margin-left:9px;">
							<li class="fontWeight">房间列表</li>
						</ul>
						<ul class="guestUl2 margin-left-20" id="roomList" style="width:130px;height:260px;margin-left:9px;overflow-y:scroll;">
<!-- 							<li><a href="javascript:;">2015</a></li> -->
<!-- 							<li><a href="javascript:;">2016</a></li> -->
<!-- 							<li><a href="javascript:;">2015</a></li> -->
						</ul>
					</div>
					<!--/右侧部分-->
				</div>
				<!--userCatalog-->
				<div class="clearBoth GuestTabIn userCatalog" style="display:none;">
					<div class="GuestTabL2">

						<!--房间编号-->
						<div class="roomNumber margin-top-10" style="width:99%;">
							<div
								class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
								<table width="99%">
									<tr>
										<td width="13%" align="right">房号</td>
										<td width="20%"><input class="input" id="account_roomid" type="text" disabled="disabled">
										</td>
										<td width="13%" align="right">中文名</td>
										<td width="20%"><select class="select" id="account_namec" onchange="loadAccountInfoByName(this.id,'account_namee')">
										</select></td>
										<td width="13%" align="right">英文名</td>
										<td colspan="3"><select class="select" id="account_namee" onchange="loadAccountInfoByName(this.id,'account_namec')">
										</select></td>
									</tr>
									<tr>
										<td align="right">标准价</td>
										<td><input class="input" name="" type="text" id="account_price" disabled="disabled">
										</td>
										<td align="right">当前房租</td>
										<td><input class="input" name="" type="text" id="account_roomprice" disabled="disabled">
										</td>
										<td align="right">入住时间</td>
										<td><input class="input" name="" type="text" id="account_inDate" disabled="disabled">
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td><label><input class="checkbox" name="" id="hideRoomPrice" disabled="disabled"
												type="checkbox" value=""><span class="red">隐藏房价</span>
										</label>
										</td>
										<td>&nbsp;</td>
										<td align="right"><a href="javascript:;" id="priceList_2">房价列表</a>
										</td>
										<td align="right">离店时间</td>
										<td colspan="3"><input class="input" name="" type="text" id="account_leavedate" disabled="disabled">
										</td>
									</tr>
									<tr>
										<td align="right">备注</td>
										<td colspan="3"><input class="input widthB99" id="account_notice"
											type="text" disabled="disabled">
										</td>
										<td   align="right"><span class="red">支付方式</span>
										</td>
										<td><input class="input" id="account_paytype" type="text" colspan="3" disabled="disabled">
										</td>
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
                                    <td width="10%"><input class="input" name="" type="text" id="account_billaid" disabled="disabled"></td>
                                    <td width="8%" align="right">总额</td>
                                    <td width="10%"><input class="input" name="" type="text" id="account_borrowA" disabled="disabled"></td>
                                    <td width="8%" align="right">已付</td>
                                    <td width="10%"><input class="input" name="" type="text" id="account_lentA" disabled="disabled"></td>
                                    <td width="8%" align="right">余额</td>
                                    <td width="10%"><input class="input widthB92" name="" type="text" id="account_remainA" disabled="disabled"></td>
                                    <td width="15%" align="right"><span class="red">限额+授权</span></td>
                                    <td><input class="input widthB92" name="" type="text" id="account_totalA" disabled="disabled"></td>
                                </tr>
                                <tr>
                                    <td align="right">B账号</td>
                                    <td><input class="input" name="" type="text" id="account_billbid" disabled="disabled"></td>
                                    <td align="right">总额</td>
                                    <td><input class="input" name="" type="text" id="account_borrowB" disabled="disabled"></td>
                                    <td align="right">已付</td>
                                    <td><input class="input" name="" type="text" id="account_lentB" disabled="disabled"></td>
                                    <td align="right">余额</td>
                                    <td><input class="input widthB92" name="" type="text" id="account_remainB" disabled="disabled"></td>
                                    <td align="right"><span class="red">限额+授权</span></td>
                                    <td><input class="input widthB92" name="" type="text" id="account_totalB" disabled="disabled"></td>
                                </tr>
								</table>
							</div>
						</div>
						<div class="tableDivDow clearBoth floatL widthAllBlock">
							<table width="98%">
								  <tr>
		                            <td width="70">
		                               <label><input class="radio" name="radioAccount" type="radio" value="1">A账页</label>
		                            </td>
		                            <td width="70">
		                               <label><input class="radio" name="radioAccount" type="radio" value="2">B账页</label>
		                            </td>
		                            <td width="330">&nbsp;</td>
		                            <td width="70">
		                               <label><input class="radio" name="radioType" type="radio" value="A">明细</label>
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

						<div class="guestBgF">
							<!--table-->
							<div class="tableDiv floatL margin-left-10" style="width:100%;height:310px" id="accountGrid">
							</div>
							<div class="clearBoth"></div>
						</div>

						<div
							class="tableDivDow clearBoth floatL widthAllBlock margin-bottom-10">
							<table width="98%">
								<tr>
									<td align="right" width="35">从</td>
									<td width="85"><input class="input" name="" id="account_startDate" type="text" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'account_endDate\')}'})">
									</td>
									<td align="right" width="35">到</td>
									<td width="85"><input class="input" name="" id="account_endDate" type="text" onkeypress="searchAccount(event)" onclick="WdatePicker({minDate:'#F{$dp.$D(\'account_startDate\')}'})">
									</td>
									<td><label><input class="radio" name="disturb"
											type="radio" value="N">未结</label> <label><input
											class="radio margin-left-10" name="disturb" type="radio"
											value="Y">已结</label> <label><input
											class="radio margin-left-10" name="disturb" type="radio"
											value="A">全部</label></td>
									<td width="80"><label><input class="checkbox"
											name="" type="checkbox" value="">无效单</label>
									</td>
									<td align="right" ; width="100">
<!-- 										<a class="button_02" id="splitAccounts" href="javascript:;">拆分账目</a> -->
									</td>
								</tr>
							</table>
						</div>
					</div>
					<!--右侧部分-->

					<div class="GuestTabR2">
						<a class="button_02 widthPx100" href="javascript:;" onclick="accountRefresh()">刷新(R)</a>
	                    <a class="button_02 widthPx100" id="checkOut" href="javascript:;">结账(J)</a>
	                    <a class="button_02 widthPx100" id="deposit" href="javascript:;">押金(D)</a>
	                    <a class="button_02 widthPx100" id="LeavingHotel" href="javascript:;">离店(Q)</a>
	                    <a class="button_02 widthPx100" id="receivables" href="javascript:;">收款(S)</a>
	                    <a class="button_02 widthPx100" id="checkOutOperation" href="javascript:;">退房(U)</a>
	                    <a class="button_02 widthPx100" id="preAuthorization" href="javascript:;">预授权(A)</a>
	                    <a class="button_02 widthPx100" id="guestSplitInfo" href="javascript:;">分账(F)</a>
	                    <a class="button_02 widthPx100" id="accountedFor" href="javascript:;">入账(I)</a>
	                    <a class="button_02 widthPx100" id="customAccounts" href="javascript:;">改单(E)</a>
	                    <a class="button_02 widthPx100" href="javascript:;" onclick="cancleBill()">取消(C)</a>
	                    <a class="button_02 widthPx100" href="javascript:;">合同(B)</a>
	                    <a class="button_02 widthPx100" id="transferAccounts" href="javascript:;">转账(M)</a>
	                    <a class="button_02 widthPx100" href="javascript:;">打印(P)</a>
	                    <a class="button_02 widthPx100" id="forALongTime" href="javascript:;">半/全日租</a>
	                    <a class="button_02 widthPx100" href="javascript:;" onclick="closeWindow()">退出(X)</a>
                    <div class="clearBoth red">注意加收半（全）日租</div>
						<ul class="guestUl userBlock">
							<li class="fontWeight">房间列表</li>
						</ul>
						<ul class="guestUl2" id="roomList2">
<!-- 							<li><a href="javascript:;">2015</a></li> -->
<!-- 							<li><a href="javascript:;">2016</a></li> -->
<!-- 							<li><a href="javascript:;">2015</a></li> -->
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
		<!--其他信息弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 otherDiv">
		<div class="alertMain greyBg" style="width:830px; margin-top:150px;">
	    	<h4 class="moveDivAlert2">其他信息<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
	        <div class="borderSolid">
	        	<!--分账-->
	        	<table width="100%">
	            	<tr>
	                	<td width="35%" valign="top">
	                    	<h5 class="margin-left-5 fontWeight">外宾登记信息</h5>
	                    	<div class="splitDiv margin-top-5">
	                        	<dl class="inputDiv2 margin-top-none margin-bottom-20">
												<dt>英文名</dt>
												<dd>
													<input class="input" name="" type="text" placeholder="" id="otherNamee">
												</dd>
												<dt>性别</dt>
												<dd>
													<select class="select widthB99" id="otherSex" name="otherSex">
													</select>
												</dd>
												<dt>国籍</dt>
												<dd>
													<select class="select widthB99" id="otherCountry" >
													</select>
												</dd>
												 <dt>证件类型</dt>
				                                <dd>
				                                    <select class="select widthB99" id="otherCrdkindId" name="otherCrdkindId">
				                                    </select>
				                                </dd>
				                                <dt>证件号码</dt>
				                                <dd>
				                                    <input class="input" name="" id="otherCrdId" type="text" placeholder="">
				                                </dd>
                                
												<dt>签证类型</dt>
												<dd>
													<select class="select widthB99" name="visakindId">
													</select>
												</dd>
												<dt>签证号码</dt>
												<dd>
													<input class="input" name="visaId" type="text" placeholder="">
												</dd>
												<dt>签发机关</dt>
												<dd>
													<select class="select widthB99" name="depart">
													</select>
												</dd>
												<dt>有效日期</dt>
												<dd> 
													<input class="input" name="crdVld" type="text" placeholder="" id="crdVld" onclick="WdatePicker({dchanged:buttonChange,Mchanged:buttonChange,ychanged:buttonChange})">
												</dd>
												<dt>入境日期</dt>
												<dd>
													<input class="input" name="inDate" type="text" placeholder="" id="inDate" onclick="WdatePicker({dchanged:buttonChange,Mchanged:buttonChange,ychanged:buttonChange})">
												</dd>
												<dt>入境口岸</dt>
												<dd>
													<select class="select widthB99" name="inPort" >
													</select>
												</dd>
												<dt>接待单位</dt>
												<dd>
													<input class="input" name="" type="text" placeholder="">
												</dd>
												<div class="clearBoth"></div>
											</dl>
											<div class="clearBoth"></div>
										</div></td>
									<td valign="top">
										<!--外宾登记信息Right-->
										<h5 class="margin-left-5 fontWeight">客人签名</h5>
										<div class="textarea margin-top-5 height200"></div>
										<table width="100%" class="margin-top-30 gry_9">
				                            <tr>
				                                <td width="25%" align="right">登记人</td>
				                                <td width="25%"><input class="input" name="chkOperName" type="text" disabled="disabled"></td>
				                                <td width="18%" align="right">操作时间</td>
				                                <td width="32%"><input class="input" name="chkTime" type="text" disabled="disabled"></td>
				                            </tr>
				                            <tr>
				                                <td align="right">最后修改人</td>
				                                <td><input class="input" name="lastOperName" type="text" disabled="disabled"></td>
				                                <td align="right">操作时间</td>
				                                <td><input class="input" name="lastTime" type="text" disabled="disabled"></td>
				                            </tr>
				                            <tr>
				                                <td align="right">退房人</td>
				                                <td><input class="input" name="outOperName" type="text" disabled="disabled"></td>
				                                <td align="right">操作时间</td>
				                                <td><input class="input" name="outTime" type="text" disabled="disabled"></td>
				                            </tr>
				                            <tr>
				                                <td align="right">恢复入住人</td>
				                                <td><input class="input" name="rechkOperName" type="text" disabled="disabled"></td>
				                                <td align="right">操作时间</td>
				                                <td><input class="input" name="rechkTime" type="text" disabled="disabled"></td>
				                            </tr>
				                            <tr>
				                                <td align="right">最后账目处理人</td>
				                                <td><input class="input" name="lastCashierName" type="text" disabled="disabled"></td>
				                                <td align="right">操作时间</td>
				                                <td><input class="input" name="cashierTime" type="text" disabled="disabled"></td>
				                            </tr>
				                        </table> <!--外宾登记信息Right  END--></td>
								</tr>
							</table>
							<!--分账-->
			
							<div class="alertRight clearBoth margin-top-30">
								<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="otherQuit()">关闭</a>
<!-- 								<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="otherGiveUp()">放弃</a> -->
<!-- 								<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="otherConfirm()">确定</a> -->
							</div>
							<div class="clearBoth"></div>
						</div>
					</div>
				</div>
				</form>
	<!--/其他信息弹出框-->
	<!--房价列表弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 pricesList">
		<div class="alertMain greyBg" style="width:555px; margin-top:200px;">
			<h4 class="moveDivAlert2">
				房价列表<a href="javascript:;" class="closeDiv2 closeAlert"></a>
			</h4>
			<div class="borderSolid">
				<h5 class="margin-left-5" id="houseInfo"></h5>
				<div class="roomButtonFblock margin-top-5">
					<table id="priceTable">
<!-- 						<tr> -->
<!-- 							<td width="110" align="center">2015-04-28</td> -->
<!-- 							<td width="80" align="center">周四</td> -->
<!-- 							<td width="100" align="center">98.00</td> -->
<!-- 							<td width="90" align="center">修改<input -->
<!-- 								class="checkbox margin-left-5" type="checkbox"> -->
<!-- 							</td> -->
<!-- 							<td width="100">&nbsp;</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td width="110" align="center">2015-04-28</td> -->
<!-- 							<td width="80" align="center">周四</td> -->
<!-- 							<td width="100" align="center">98.00</td> -->
<!-- 							<td width="90" align="center">修改<input -->
<!-- 								class="checkbox margin-left-5" type="checkbox"> -->
<!-- 							</td> -->
<!-- 							<td width="100">&nbsp;</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td width="110" align="center">2015-04-28</td> -->
<!-- 							<td width="80" align="center">周四</td> -->
<!-- 							<td width="100" align="center">98.00</td> -->
<!-- 							<td width="90" align="center">修改<input type="checkbox" -->
<!-- 								class="checkbox margin-left-5" checked> -->
<!-- 							</td> -->
<!-- 							<td width="100"><input class="input margin-r"> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td width="110" align="center">2015-04-28</td> -->
<!-- 							<td width="80" align="center">周四</td> -->
<!-- 							<td width="100" align="center">98.00</td> -->
<!-- 							<td width="90" align="center">修改<input -->
<!-- 								class="checkbox margin-left-5" type="checkbox" checked> -->
<!-- 							</td> -->
<!-- 							<td width="100"><input class="input margin-r"> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td width="110" align="center">2015-04-28</td> -->
<!-- 							<td width="80" align="center">周四</td> -->
<!-- 							<td width="100" align="center">98.00</td> -->
<!-- 							<td width="90" align="center">修改<input -->
<!-- 								class="checkbox margin-left-5" type="checkbox" checked> -->
<!-- 							</td> -->
<!-- 							<td width="100"><input class="input margin-r"> -->
<!-- 							</td> -->
<!-- 						</tr> -->

					</table>
				</div>
				<h5
					class="margin-left-5 textAlignRight margin-right-10 margin-top-5">
					合计：<span class="fontWeight" id="totalPrice"></span>元
				</h5>

				<div class="alertRight clearBoth margin-top-30">
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="priceCancle()">取消(N)</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="priceConfirm()">确定(Y)</a>
				</div>
				<div class="clearBoth"></div>
			</div>
		</div>
	</div>
	<!--/房价列表弹出框-->
	<!--分账弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 splitInfoDiv" id="splitInfoDiv">
		<div class="alertMain greyBg" style="width:555px; margin-top:150px;">
			<h4 class="moveDivAlert2">
				分账<a href="javascript:;" class="closeDiv2 closeAlert"></a>
			</h4>
			<div class="borderSolid">
				<!--分账-->
				<table width="100%">
					<tr>
						<td width="42%">
							<h5 class="margin-left-5 fontWeight">A账页项目</h5>
							<ul class="splitDivLeft margin-top-5"  id="consumeA_ID">
							</ul></td>
						<td align="center">
							<a class="forSN margin-top-20" href="javascript:;" onclick="consumeMove('right','movePart')"><img src="/img/right_01.png"></a>
	                    	<a class="forSN margin-top-10" href="javascript:;" onclick="consumeMove('right','moveAll')"><img src="/img/right_02.png"></a>
	                    	<a class="forSN margin-top-30" href="javascript:;" onclick="consumeMove('left','movePart')"><img src="/img/left_01.png"></a>
	                    	<a class="forSN margin-top-10" href="javascript:;" onclick="consumeMove('left','moveAll')"><img src="/img/left_02.png"></a>
						</td>
						<td width="42%">
							<h5 class="margin-left-5 fontWeight">B账页项目</h5>
							<ul class="splitDivRight margin-top-5" id="consumeB_ID">
							</ul></td>
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
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="loadConsume()">放弃</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="consumeSubmit()">确定</a>
				</div>
				<div class="clearBoth"></div>
			</div>
		</div>
	</div>
	<!--/分账弹出框-->
	<!--换房弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 changeRoomDiv">
		<div class="alertMain greyBg" style="width:830px; margin-top:150px;">
			<h4 class="moveDivAlert2">
				换房 / 续住操作<a href="javascript:;" class="closeDiv2 closeAlert"></a>
			</h4>
			<div class="borderSolid">
				<!--换房续住-->
				<table width="100%">
					<tr>
						<td width="80">
							<h5 class="fontWeight">房间号:</h5> <input
							class="input margin-top-10" name="" type="text"> <a
							class="button_03 margin-top-10" href="javascript:;">发房卡</a></td>
						<td>
							<!--table-->
							<div class="tableDiv">
								<!--table title-->
								<table class="tableMain">
									<thead>
										<tr>
											<td width="15%">序号</td>
											<td width="14%">房号</td>
											<td width="15%">房型</td>
											<td width="15%">楼层</td>
											<td width="15%">建筑</td>
											<td width="15%">房间特征</td>
											<td>状态</td>
										</tr>
									</thead>
								</table>
								<!--table title end -->
								<!--table enner-->
								<div class="tableHeiScll height140">
									<table class="tableMain tabChangBg">
										<tbody>
											<tr>
												<td width="15%">001</td>
												<td width="15%">21601</td>
												<td width="15%">TingTao</td>
												<td width="15%">听涛阁</td>
												<td width="15%">听涛阁</td>
												<td width="15%">2</td>
												<td>可修改</td>
											</tr>
											<tr>
												<td>002</td>
												<td>21601</td>
												<td>TingTao</td>
												<td>听涛阁</td>
												<td>听涛阁</td>
												<td>2</td>
												<td>可修改</td>
											</tr>
											<tr>
												<td>002</td>
												<td>21601</td>
												<td>TingTao</td>
												<td>听涛阁</td>
												<td>听涛阁</td>
												<td>2</td>
												<td>可修改</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--table enner -END- -->
							</div> <!--table -END- --></td>
					</tr>
					<tr>
						<td width="80">&nbsp;</td>
						<td colspan="2" align="center"><a
							class="roomPointBottom floatL margin-bottom-10 margin-top-10 margin-left-200"
							href="javascript:;"></a> <a
							class="roomPointTop floatL margin-bottom-10 margin-top-10 margin-left-30"
							href="javascript:;"></a></td>
					</tr>
					<tr>
						<td width="80">
							<h5 class="fontWeight">房间号:</h5> <input
							class="input margin-top-10" name="" type="text"> <a
							class="button_03 margin-top-10" href="javascript:;">发房卡</a></td>
						<td>
							<!--table-->
							<div class="tableDiv">
								<!--table title-->
								<table class="tableMain">
									<thead>
										<tr>
											<td width="15%">序号</td>
											<td width="14%">房号</td>
											<td width="15%">房型</td>
											<td width="15%">楼层</td>
											<td width="15%">建筑</td>
											<td width="15%">房间特征</td>
											<td>状态</td>
										</tr>
									</thead>
								</table>
								<!--table title end -->
								<!--table enner-->
								<div class="tableHeiScll height140">
									<table class="tableMain tabChangBg">
										<tbody>
											<tr>
												<td width="15%">001</td>
												<td width="15%">21601</td>
												<td width="15%">TingTao</td>
												<td width="15%">听涛阁</td>
												<td width="15%">听涛阁</td>
												<td width="15%">2</td>
												<td>可修改</td>
											</tr>
											<tr>
												<td>002</td>
												<td>21601</td>
												<td>TingTao</td>
												<td>听涛阁</td>
												<td>听涛阁</td>
												<td>2</td>
												<td>可修改</td>
											</tr>
											<tr>
												<td>002</td>
												<td>21601</td>
												<td>TingTao</td>
												<td>听涛阁</td>
												<td>听涛阁</td>
												<td>2</td>
												<td>可修改</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--table enner -END- -->
							</div> <!--table -END- --></td>
					</tr>
				</table>
				<!--换房续住-->

				<div class="alertRight clearBoth margin-top-30">
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;">退出</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;">放弃</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;">确定</a>
				</div>
				<div class="clearBoth"></div>
			</div>
		</div>
	</div>
	<!--/换房弹出框-->
	<!--押金弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 depositDiv" id="depositDiv">
		<div class="alertMain greyBg" style="width:560px; margin-top:240px;">
	    	<h4 class="moveDivAlert2">押金<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
	        <div class="borderSolid">
	        	<!--押金-->
	        	<form id="depositForm">
	        	<table width="95%">
	            	<tr>
	                	<td align="right"><span class="red">单号</span></td>
	                    <td><input class="inputInRight" name="accoId" type="text" ></td>
	                    <td colspan="2">&nbsp;</td>
	                </tr>
	                <tr>
	                	<td width="20%" align="right">收款方式</td>
	                    <td>
	                    	<select class="select widthB99" id="depositSettle" name="setlId">
	                        </select>
	                    </td>
	                    <td width="20%" align="right">币种</td>
	                    <td>
	                    	<select class="select widthB99" id="depositMoneyKind" name="moneyKind">
	                        </select>
	                    </td>
	                </tr>
	                <tr>
	                	<td align="right"><span class="red">本位币金额</span></td>
	                    <td><input class="inputInRight" name="balance" type="text"></td>
	                    <td align="right"><span class="red">外币金额</span></td>
	                    <td><input class="inputInRight" name="foreignm" type="text"></td>
	                </tr>
	                <tr>
	                	<td align="right">备注</td>
	                    <td colspan="3"><input class="input widthB98" name="notes" type="text"></td>
	                </tr>
	            </table>
	            <!--押金-->
	            </form>
	            <div class="alertRight clearBoth margin-top-30">
	               <a class="buttonLikeA floatR margin-right-30" href="javascript:;" onclick="depositQuit()">退出</a>
	               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="depositGiveUp()">放弃</a>
	               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="depositSave()">确定</a>
	            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
	</div>
	<!--/押金弹出框-->
	<!--收款弹出框-->
<!--/押金弹出框-->
<!--收款弹出框-->
<div class="alertDiv moveBar2 alertDiv2 receivablesDiv" id="receivablesDiv">
	<div class="alertMain greyBg" style="width:560px; margin-top:240px;">
    	<h4 class="moveDivAlert2">收款<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<form id="receivablesForm">
        	<!--押金-->
        	<table width="95%">
            	<tr>
                	<td align="right"><span class="red">单号</span></td>
                    <td><input class="inputInRight" name="accoId" type="text"></td>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                	<td width="20%" align="right">收款方式</td>
                    <td>
                    	<select class="select widthB99" name="setlId" id="receivablesSettle" >
                        </select>
                    </td>
                    <td width="20%" align="right">币种</td>
                    <td>
                    	<select class="select widthB99" name="moneyKind" id="receivablesMoneyKind">
                        </select>
                    </td>
                </tr>
                <tr>
                	<td align="right"><span class="red">本位币金额</span></td>
                    <td><input class="inputInRight" name="balance" type="text"></td>
                    <td align="right"><span class="red">外币金额</span></td>
                    <td><input class="inputInRight" name="foreignm" type="text"></td>
                </tr>
                <tr>
                	<td align="right">备注</td>
                    <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                </tr>
            </table>
            <!--押金-->
            </form>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;" onclick="receivaQuit()">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="receivaGiveUp()">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="receivaSave()">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
	</div>
	<!--/收款弹出框-->
	<!--离店弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 LeavingHotelDiv" id="LeavingHotelDiv">
		<div class="alertMain greyBg" style="width:650px; margin-top:200px;">
			<h4 class="moveDivAlert2">
				离店操作<a href="javascript:;" class="closeDiv2 closeAlert"></a>
			</h4>
			<div class="borderSolid">
				<!--table-->
				<ul class="widthB96 margin-left-10">
					<li class="floatL margin-right-20"><label><input
							class="radio margin-right-5" name="leavingHotelRadio" type="radio" value="A">全部离店</label>
					</li>
					<li class="floatL margin-right-20"><label><input
							class="radio margin-right-5" name="leavingHotelRadio" type="radio" value="B">选择离店</label>
					</li>
					<li class="floatL margin-right-20"><label><input
							class="radio margin-right-5" name="leavingHotelRadio" type="radio" value="C">当日离店</label>
					</li>
					<li class="floatL margin-right-20"><label><input
							class="radio margin-right-5" name="leavingHotelRadio" type="radio" value="D">当前客人</label>
					</li>
				</ul>
				<div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="LeavingHotelGrid" style="height:150px">
				</div>
				<!--table -END- -->
				<div class="clearBoth padding-top-10 textAlignRight margin-right-5">
					<label><input class="checkbox margin-right-5" name=""
						type="checkbox" value="">逐个提示</label>
				</div>
				<div class="alertRight clearBoth margin-top-30">
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="leavingHotelQuit()">退出</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" >放弃</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="leavingHotelConfirm()">确定</a>
				</div>
				<div class="clearBoth"></div>
			</div>
		</div>
	</div>
	<!--/离店弹出框-->
	<!--预授权出框-->
<!--/预授权出框-->
<!--新增预授权-->
<div class="alertDiv moveBar3 alertDiv3 addPreAwardDiv">
	<div class="alertMain greyBg" style="width:460px; margin-top:180px;">
    	<h4 class="moveDivAlert3">新增预授权<a href="javascript:;" class="closeDiv2 closeAlert3"></a></h4>
        <div class="borderSolid">
        	<form id="addPreAwardForm">
	        	<table width="95%">
	            	 <tr>
	                	<td align="right">授权号</td>
	                    <td><input class="input" name="authId" type="text"></td>
	                </tr>
	                <tr>
	                	<td align="right">信用卡卡号</td>
	                    <td><input class="input" name="creditId" type="text"></td>
	                </tr>
	                <tr>
	                	<td align="right">持卡人</td>
	                    <td><input class="input widthB50" name="creditHolder" type="text"></td>
	                </tr>
	                <tr>
	                	<td align="right">预授权金额</td>
	                    <td><input class="input widthB50" name="balance" type="text"></td>
	                </tr>
	                <tr>
	                	<td align="right">有效期</td>
	                    <td><input class="input widthB50" name="expired" type="text" onclick="WdatePicker()"></td>
	                </tr>
	                <tr>
	                	<td align="right" valign="top"><div class="padding-top-10">备注</div></td>
	                    <td><textarea class="textarea widthB97 margin-top-5" name="note" cols="" rows=""></textarea></td>
	                </tr>
	            </table>
            </form>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;" onclick="quitPreAuth()">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="giveUpPreAuth()">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="savePreAuth()">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--新增预授权END-->
<!--入账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 accountedForDiv" id="accountedForDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">入账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
        	<form id="accountForm">
            	<ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="1">A账页</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="2">B账页</label></li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB70 margin-top-10 padding-10">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right">房号</td>
                            <td width="30%"><input class="input" name="roomId" id="incomeRoomId" type="text" disabled="disabled"></td>
                            <td width="20%" align="right">信用限额</td>
                            <td><input class="input" name="" type="text" id="accountlimit" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <td align="right">账号</td>
                            <td><input class="input" name="billId" type="text" id="accountBillId" disabled="disabled"></td>
                            <td align="right">账单号</td>
                            <td><input class="input" name="accoId" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">中文名</td>
                            <td>
                            	<select class="select widthB100" id="incomeNamec" name="incomeNamec">
                                </select>
                            </td>
                            <td align="right">消费点</td>
                            <td>
                            	<select class="select widthB100" id="accountConsume" name="consId" onchange="changePaied()">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">英文名</td>
                            <td>
                            	<select class="select widthB100" id="incomeNamee" name="incomeNamee">
                                </select>
                            </td>
                            <td align="right">币种</td>
                            <td>
                            	<select class="select widthB100" id="accountExchange" name="moneyKind">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">&nbsp;</td>
                            <td><label><input class="checkbox margin-right-5" name="" type="checkbox" id="isserve">收服务费</label></td>
                            <td align="right">本位币</td>
                            <td><input class="input" name="balance" type="text" id="balance" onkeypress="calServeMoney(event)"></td>
                        </tr>
                        <tr>
                            <td align="right">服务费</td>
                            <td><input class="input" name="svcCharge" id="serveMoney" type="text" disabled="disabled"></td>
                            <td align="right">外币金额</td>
                            <td><input class="input" name="foreignm" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                        </tr>
                    </table>
                    <!--table enner -END- -->
                </div>
                <ul class="floatL margin-left-40 margin-top-10">
                	<li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="billConfirm()">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="accountFormReset">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"  onclick="billQuit()">退出</a></li>
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="list_01" type="radio" value="A">当前客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="B">所有客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="C">所有房间</label></li>
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
	<div class="alertMain greyBg" style="width:660px; margin-top:150px;">
    	<h4 class="moveDivAlert2">结账打单<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB80 floatL">
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 " style="height:100px" id="checkOutGrid">
                        <!--table title-->
<!--                         <table class="tableMain"> -->
<!--                             <thead>  -->
<!--                                 <tr> -->
<!--                                     <td width="25%">贷方科目</td> -->
<!--                                     <td width="20%">金额</td> -->
<!--                                     <td width="20%">外币</td> -->
<!--                                     <td width="20%">币种</td> -->
<!--                                     <td>备注</td> -->
<!--                                 </tr> -->
<!--                             </thead> -->
<!--                         </table> -->
                        <!--table title end -->
                        <!--table enner-->
<!--                         <div class="tableHeiScll height100"> -->
<!--                             <table class="tableMain"> -->
<!--                                 <tbody> -->
<!--                                     <tr> -->
<!--                                         <td width="25%">贷方科目</td> -->
<!--                                         <td width="22%">金额</td> -->
<!--                                         <td width="21%">外币</td> -->
<!--                                         <td width="20%">币种</td> -->
<!--                                         <td>备注</td> -->
<!--                                     </tr> -->
<!--                                     <tr> -->
<!--                                         <td>002</td> -->
<!--                                         <td>李四</td> -->
<!--                                         <td>女</td> -->
<!--                                         <td>同住</td> -->
<!--                                         <td>0</td> -->
<!--                                     </tr> -->
<!--                                 </tbody> -->
<!--                             </table> -->
<!--                         </div> -->
                        <!--table enner -END- -->
                    </div>
                    <!--/table-->
                    <form id="checkOutForm">
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10 padding-top-10 padding-bottom-10">
                    	<table width="100%">
                            <tr>
                                <td width="20%" align="right">结账方式</td>
                                <td width="30%">
                                	<select class="select widthB100" id="checkOutSetlId" name="checkOutSetlId">
                                    </select>
                                </td>
                                <td width="20%" align="right">AR账号</td>
                                <td><input class="input" name="ARbillId" type="text" id="ARbillId"></td>
                            </tr>
                            <tr>
                                <td align="right">币种</td>
                                <td>
                                	<select class="select widthB100" id="checkOutmoneyKind" name="checkOutmoneyKind">
                                         <option>--</option>
                                    </select>
                                </td>
                                <td align="right">结账余额</td>
                                <td><input class="input" name="checkOutRemain" type="text" id="checkOutRemain" onkeypress="checkOutMoney(event)"></td>
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
                                <td><input class="input" name="" type="text"></td>
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
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="checkOutSubmit()">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="checkOutGiveUp()">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">打单</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="checkOutQuit()">退出</a></li>
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="checkOut_print" type="radio" value="1">打印订单</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="checkOut_print" type="radio" value="2">打印电话</label></li>
<!--                     <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_01" type="radio" value="">所有房间</label></li> -->
                    <li class="margin-top-15" style="border:solid 1px #999;padding:5px;">
                    	<label class="displayBlock"><input class="radio margin-right-5" name="checkOut_language" type="radio" value="1">中文单</label>
                        <label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="checkOut_language" type="radio" value="2">英文单</label>
                    </li>
                    <li style="border:solid 1px #999;padding:5px;border-top:none;">
                    	<label class="displayBlock margin-top-5"><input class="radio margin-right-5" name="checkOut_type" type="radio" value="1">当前账页</label>
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
    	<h4 class="moveDivAlert2">转账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
            	
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="transferAccountGrid" style="height:300px">
                    <!--table title-->
<!--                     <table class="tableMain"> -->
<!--                         <thead>  -->
<!--                             <tr> -->
<!--                             	<td width="7%">序号</td> -->
<!--                                 <td width="9%">中文名</td> -->
<!--                                 <td width="7%">房号</td> -->
<!--                                 <td width="7%">摘要</td> -->
<!--                                 <td width="9%">团代码</td> -->
<!--                                 <td width="11%">A账余额</td> -->
<!--                                 <td width="11%">B账余额</td> -->
<!--                                 <td width="12%">抵店日期</td> -->
<!--                                 <td width="12%">离店日期</td> -->
<!--                                 <td width="7%">公司</td> -->
<!--                                 <td>账号</td> -->
<!--                             </tr> -->
<!--                         </thead> -->
<!--                     </table> -->
                    <!--table title end -->
                    <!--table enner-->
<!--                     <div class="tableHeiScll height186"> -->
<!--                         <table class="tableMain"> -->
<!--                             <tbody> -->
<!--                                 <tr> -->
<!--                                     <td width="8%">001</td> -->
<!--                                     <td width="9%">李四</td> -->
<!--                                     <td width="7%">lisi</td> -->
<!--                                     <td width="7%">摘要</td> -->
<!--                                     <td width="9%">团代码</td> -->
<!--                                     <td width="11%">A账余额</td> -->
<!--                                     <td width="11%">B账余额</td> -->
<!--                                     <td width="12%">抵店日期</td> -->
<!--                                     <td width="12%">离店日期</td> -->
<!--                                     <td width="7%">公司</td> -->
<!--                                     <td>账号</td> -->
<!--                                 </tr> -->
<!--                                 <tr> -->
<!--                                     <td>002</td> -->
<!--                                     <td>李四</td> -->
<!--                                     <td>女</td> -->
<!--                                     <td>同住</td> -->
<!--                                     <td>0</td> -->
<!--                                     <td>在住</td> -->
<!--                                     <td>12</td> -->
<!--                                     <td>同住</td> -->
<!--                                     <td>0</td> -->
<!--                                     <td>在住</td> -->
<!--                                     <td>jj</td> -->
<!--                                 </tr> -->
<!--                             </tbody> -->
<!--                         </table> -->
<!--                     </div> -->
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
                <ul class="widthB96 margin-left-10 padding-top-10 clearBoth">
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5 guest" name="transferRadio_1" type="radio" value="1">个人</label></li>
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5 guest" name="transferRadio_1" type="radio" value="2">团体</label></li>
                    <li class="floatL margin-right-20 margin-top-5"><label><input class="radio margin-right-5 noGuest" name="transferRadio_1" type="radio" value="3">非住客</label></li>
                    <li class="floatR">
                    	<table width="150">
                        	<tr>
                            	<td width="50" align="right">房号</td>
                                <td><input class="input" name="" type="text" id="transferRoomId" disabled="disabled"></td>
                            </tr>
                        </table>
                    </li>
                    <li class="floatR margin-right-20">
                    	<table width="150">
                        	<tr>
                            	<td width="50" align="right">账号</td>
                                <td><input class="input" name="" type="text" id="transferBillId" disabled="disabled"></td>
                            </tr>
                        </table>
                    </li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR guestDiv" id="transferDiv_1">
                    <form id="transferForm_1">
                    	<div class="jsWord">检索条件</div>
	                    <table class="widthB98 margin-top-15 margin-bottom-10">
	                        <tr>
	                            <td width="12%" align="right">中文名</td>
	                            <td width="13%"><input class="input" name="gstNamec" type="text"></td>
	                            <td width="12%" align="right">英文名</td>
	                            <td width="13%"><input class="input" name="gstNamee" type="text"></td>
	                            <td width="12%" align="right">抵店日期</td>
	                            <td width="13%"><input class="input" name="reachDate" type="text"></td>
	                            <td width="12%" align="right">房号</td>
	                            <td><input class="input" name="roomId" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">团代码</td>
	                            <td><input class="input" name="grpId" type="text"></td>
	                            <td align="right">团名</td>
	                            <td><input class="input" name="grpName" type="text"></td>
	                            <td align="right">离店日期</td>
	                            <td><input class="input" name="leaveDate" type="text"></td>
	                            <td align="right">退房人</td>
	                            <td><input class="input" name="outPerName" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">合约单位</td>
	                            <td colspan="3">
									<table width="100%">
                                           <tr>
                                              <td style="padding:0;">
                                                <input class="input widthB98"  id="guest_theCompany" type="text">
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
	                            	<label class="margin-right-15"><input class="radio margin-right-5" name="transferRadio_2" type="radio" value="I">在住客人</label>
	                                <label><input class="radio" name="transferRadio_2" type="radio" value="O">离店客人</label>
	                            </td>
	                        </tr>
	                    </table>
                    </form>
                </div>
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR guestDiv displayNone" id="transferDiv_2">
                	<form id="transferForm_2">
	                    <div class="jsWord">检索条件</div>
	                    <table class="widthB98 margin-top-15 margin-bottom-10">
	                        <tr>
	                            <td width="12%" align="right">中文名</td>
	                            <td width="13%"><input class="input" name="leadNamec" type="text"></td>
	                            <td width="12%" align="right">英文名</td>
	                            <td width="13%"><input class="input" name="leadNamee" type="text"></td>
	                            <td width="12%" align="right">抵店日期</td>
	                            <td width="13%"><input class="input" name="reachDate" type="text"></td>
	                            <td width="12%" align="right">房号</td>
	                            <td><input class="input" name="roomId" type="text"></td>
	                        </tr>
	                        <tr>
	                            <td align="right">团代码</td>
	                            <td><input class="input" name="grpId" type="text"></td>
	                            <td align="right">团名</td>
	                            <td><input class="input" name="grpName" type="text"></td>
	                            <td align="right">离店日期</td>
	                            <td><input class="input" name="leaveDate" type="text"></td>
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
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-15 positionR noGuestDiv displayNone" id="transferDiv_3">
                	<form id="transferForm_3">
	                    <div class="jsWord">检索条件</div>
	                    <table class="widthB98 margin-top-15 margin-bottom-10">
	                        <tr>
	                            <td width="20%" align="right">非住客简称</td>
	                            <td width="40%"><input class="input" name="nogstId" type="text"></td>
	                            <td width="20%" align="right">创建日期</td>
	                            <td><input class="input" name="creaTimeStart" type="text"></td>
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
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="quitTransferForm()">退出</a>
                   <a class="buttonLikeA floatR margin-right-10" id="accountDetermine" href="javascript:;">确定</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="clearTransferForm()">清空条件</a>
                   <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="searchTransferGrid()">条件检索</a>
                </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/转账弹出框-->
<!--确定转账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 accountDetermineDiv displayNone" id="accountDetermineDiv">
	<div class="alertMain greyBg" style="width:900px;margin-top:150px;">
    	<h4 class="moveDivAlert2">转账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB85 floatL">
                	<table width="100%">
                    	<tr>
                        	<td align="right">中文名</td>
                            <td><input class="input" name="" type="text" id="transferTopNamec"></td>
                            <td align="right">英文名</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text" id="transferTopNamee"></td>
                            <td align="right">房号</td>
                            <td><input class="input" name="" type="text" id="transferTopRoomId"></td>
                            <td align="center">
                            	<label class="margin-right-15"><input class="radio margin-right-5" name="transferTopRadio" type="radio" value="1" checked="checked">A1账</label>
                                <label><input class="radio" name="transferTopRadio" type="radio" value="2">B3账</label>
                            </td>
                        </tr>
                    	<tr>
                    		<td width="50" align="right">账号</td>
                            <td width="100"><input class="input" name="" type="text" id="transferTopBillId"></td>
                            <td width="60" align="right">总额</td>
                            <td width="60"><input class="input" name="" type="text" id="transferTopBorrow"></td>
                            <td width="45" align="right">已付</td>
                            <td width="60"><input class="input" name="" type="text" id="transferTopLent"></td>
                            <td width="40" align="right">余额</td>
                            <td width="70"><input class="input" name="" type="text" id="transferTopRemain"></td>
                            <td rowspan="2" align="center">
                            	<a class="roomPointBottom" href="javascript:;" id="transferToBottom"></a>
                            </td>
                        </tr>
                        <tr>
                        	<td align="right">内容</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">备注</td>
                            <td colspan="5"><input class="input widthB99" name="" type="text" id="transferTopNotice"></td>
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
                            <td><input class="input" name="" type="text" id="transferBottomNamec"></td>
                            <td align="right">英文名</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text" id="transferBottomNamee"></td>
                            <td align="right">房号</td>
                            <td><input class="input" name="" type="text" id="transferBottomRoomId"></td>
                            <td rowspan="2" align="center">
                                <a class="roomPointTop" href="javascript:;" id="transferToTop"></a>
                            </td>
                            
                        </tr>
                    	<tr>
                    		<td width="50" align="right">账号</td>
                            <td width="100"><input class="input" name="" type="text" id="transferBottomBillId"></td>
                            <td width="60" align="right">总额</td>
                            <td width="60"><input class="input" name="" type="text" id="transferBottomBorrow"></td>
                            <td width="45" align="right">已付</td>
                            <td width="60"><input class="input" name="" type="text" id="transferBottomLent"></td>
                            <td width="40" align="right">余额</td>
                            <td width="70"><input class="input" name="" type="text" id="transferBottomRemain"></td>
                        </tr>
                        <tr>
                        	<td align="right">内容</td>
                            <td><input class="input" name="" type="text"></td>
                            <td align="right">备注</td>
                            <td colspan="5"><input class="input widthB99" name="" type="text" id="transferBottomNotice"></td>
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
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="transferConfirm()">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="transferGiveUp()">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="transferRefresh()">刷新</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="transferQuit()">退出</a></li>
                </ul>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>


<!--/确定转账弹出框-->
<!--客人账目分账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 guestSplitInfoDiv" id="guestSplitInfoDiv">
	<div class="alertMain greyBg" style="width:555px;margin-top:150px;">
    	<h4 class="moveDivAlert2">分账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--分账-->
        	<table width="100%">
            	<tr>
                	<td width="42%">
                    	<h5 class="margin-left-5 fontWeight">A账页项目</h5>
                    	<ul class="splitDivLeft margin-top-5" id="account_consumeAID">
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
                    	<ul class="splitDivRight margin-top-5" id="account_consumeBID">
                        </ul>
                    </td>
                </tr>
            </table>
            <div class="clearBoth"></div>
            <table class="margin-left-30 margin-top-20">
                <tr>
                    <td width="80" height="30" align="right">起始日期</td>
						<td width="110"><input class="input" id="acc_startDate" name="" type="text" onfocus="var date = $('#reach_date').val();WdatePicker({minDate:date,maxDate:'#F{$dp.$D(\'acc_endDate\')}'})">
						</td>
						<td width="80" align="right">终止日期</td>
						<td width="110"><input class="input" id="acc_endDate" name="" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'acc_startDate\')}'})">
						</td>
                </tr>
            </table>
            <!--分账-->
            
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="accountConsumeQuit()">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="accountConsumeCancle()">取消</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="accountConsumeSubmit()">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/客人账目分账弹出框-->
<!--半日租入账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 forALongTimeDiv" id="forALongTimeDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">入账<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
         		<ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective_forlong" type="radio" value="1">A账页</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective_forlong" type="radio" value="2">B账页</label></li>
                </ul>
                 <form id="forLongForm">
                <div class="tableDiv floatL margin-left-10 widthB70 margin-top-10 padding-10">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right">房号</td>
                            <td width="30%"><input class="input" name="roomId" id="forLongRoomId" type="text" disabled="disabled"></td>
                            <td width="20%" align="right">信用限额</td>
                            <td><input class="input" name="" type="text" id="forLonglimit" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <td align="right">账号</td>
                            <td><input class="input" name="billId" type="text" id="forLongBillId" disabled="disabled"></td>
                            <td align="right">账单号</td>
                            <td><input class="input" name="accoId" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">中文名</td>
                            <td>
                            	<select class="select widthB100" id="forLongNamec" name="incomeNamec">
                                </select>
                            </td>
                            <td align="right">消费点</td>
                            <td>
                            	<select class="select widthB100" id="forLongConsume" name="consId" onchange="changePaied()">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">英文名</td>
                            <td>
                            	<select class="select widthB100" id="forLongNamee" name="incomeNamee">
                                </select>
                            </td>
                            <td align="right">币种</td>
                            <td>
                            	<select class="select widthB100" id="forLongExchange" name="moneyKind">
                                     <option>--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">&nbsp;</td>
                            <td><label><input class="checkbox margin-right-5" name="" type="checkbox" id="forLongIsserve">收服务费</label></td>
                            <td align="right">本位币</td>
                            <td><input class="input" name="balance" type="text" id="forLongBalance"  onkeypress="calServeMoneyForLong(event)"></td>
                        </tr>
                        <tr>
                            <td align="right">服务费</td>
                            <td><input class="input" name="svcCharge" id="forLongServeMoney" type="text" disabled="disabled"></td>
                            <td align="right">外币金额</td>
                            <td><input class="input" name="foreignm" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="" type="text"></td>
                        </tr>
                    </table>
                    <!--table enner -END- -->
                </div>
                </form>
                <ul class="floatL margin-left-40 margin-top-10">
                	<li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="forLongbillConfirm()">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="forLongFormReset">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"  onclick="forLongBillQuit()">退出</a></li>
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="list_02" type="radio" value="A">当前客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_02" type="radio" value="B">所有客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="list_02" type="radio" value="C">所有房间</label></li>
                </ul>
                <!--table -END- -->
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/半日租入账弹出框-->
<!--退房弹出框-->
<div class="alertDiv moveBar2 alertDiv2 checkOutOperationDiv" id="checkOutOperationDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">退房<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<div class="tableDiv floatL margin-left-10 widthB93 margin-top-10 padding-10" id="retreatRoomFirstDiv">
            	<h1 class="margin-left-20 margin-top-20"><span id="retreatFirstRoomId"></span>是否退房？</h1>
                <div class="margin-top-10 floatR">
                	<a class="buttonLikeA floatL margin-right-10" href="javascript:;" onclick="retreatRoom()">是(Y)</a>
                	<a class="buttonLikeA floatL margin-right-20" href="javascript:;" onclick="retreatQuit()">否(N)</a>
                </div>
            </div>
            <div class="tableDiv floatL margin-left-10 widthB93 margin-top-10 padding-10" id="retreatRoomSecondDiv">
            	<h1 class="margin-left-20 margin-top-20"><span id="retreatSecondRoomId"></span> 属于联房（团队房），是否退房？</h1>
                <div class="margin-top-10 floatR">
                	<a class="buttonLikeA floatL margin-right-10" href="javascript:;" onclick="retreatAllRoom()">全部退房</a>
                    <a class="buttonLikeA floatL margin-right-10" href="javascript:;" onclick="retreatRoom()">仅退此房</a>
                	<a class="buttonLikeA floatL margin-right-20" href="javascript:void(0);" onclick="retreatQuit()">取消</a>
                </div>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/退房弹出框-->
<!--结账打单弹出框-->
<div class="alertDiv moveBar2 alertDiv2 customAccountsDiv" id="customAccountsDiv">
	<div class="alertMain greyBg" style="width:750px;margin-top:150px;">
    	<h4 class="moveDivAlert2">自定义账目<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB80 floatL">
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="customAccountsGrid">
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
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="customAccountGiveUp()">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="customAccountAdd()">添加</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="customAccountDelete()">删除</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;">打印</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" onclick="customAccountQuit()">退出</a></li>
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
