<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<div class="clearBoth GuestTabIn userCatalog" style="display:none;" datashow-id="guestdetal_tabguestibill" id="guestdetail_userCatalog">
	<div class="GuestTabL2">
		<div class="roomNumber margin-top-10" style="width:99%;">
			<div
				class="tableDivDow clearBoth margin-bottom-10 floatL widthAllBlock">
				<table width="99%">
					<tr>
						<td width="13%" align="right">房号</td>
						<td width="20%"><input class="input" id="account_roomid" type="text" disabled="disabled" value="${detail.roomId}">
						</td>
						<td width="13%" align="right">中文名</td>
						<td width="20%">
							<select class="select" id="account_namec" >
								<c:forEach items="${guests}" var="guest">
									<option value="${guest.check_id}">${guest.gst_namec}</option>
								</c:forEach>
							</select>
						</td>
						<td width="13%" align="right">英文名</td>
						<td colspan="3">
							<select class="select" id="account_namee">
								<c:forEach items="${guests}" var="guest">
									<option value="${guest.check_id}">${guest.gst_namee}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">标准价</td>
						<td><input class="input" name="" type="text" id="account_price" disabled="disabled" value="${detail.price}">
						</td>
						<td align="right">当前房租</td>
						<td><input class="input" name="" type="text" id="account_roomprice" disabled="disabled" value="${detail.roomPrice}">
						</td>
						<td align="right">入住时间</td>
						<td><input class="input" name="" type="text" id="account_inDate" disabled="disabled" value="${fn:substring(detail.reachDate,0,10)}">
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
						<td colspan="3"><input class="input" name="" type="text" id="account_leavedate" disabled="disabled" value="${fn:substring(detail.leaveDate,0,10)}">
						</td>
					</tr>
					<tr>
						<td align="right">备注</td>
						<td colspan="3"><input class="input widthB99" id="account_notice"
							type="text" disabled="disabled" value="${detail.notice}" >
						</td>
						<td   align="right"><span class="red">支付方式</span>
						</td>
						<td>
							
							<input class="input" id="account_paytype" type="text" colspan="3" disabled="disabled" <c:forEach items="${hsettls}" var="hcode">
								<c:if test="${hcode.codeId==detail.payId}">
									value="${hcode.codeNamec}">
								</c:if>
							</c:forEach>>
							
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
                    <td width="10%"><input class="input" name="" type="text" id="account_borrowA" disabled="disabled" style="text-align:right"></td>
                    <td width="8%" align="right">已付</td>
                    <td width="10%"><input class="input" name="" type="text" id="account_lentA" disabled="disabled" style="text-align:right"></td>
                    <td width="8%" align="right">余额</td>
                    <td width="10%"><input class="input widthB92" name="" type="text" id="account_remainA" disabled="disabled" style="text-align:right"></td>
                    <td width="15%" align="right"><span class="red">限额+授权</span></td> 
                    <td><input class="input widthB92" name="" type="text" id="account_totalA" disabled="disabled" style="text-align:right"></td>
                </tr>
                <tr>
                    <td align="right">B账号</td>
                    <td><input class="input" name="" type="text" id="account_billbid" disabled="disabled"></td>
                    <td align="right">总额</td>
                    <td><input class="input" name="" type="text" id="account_borrowB" disabled="disabled" style="text-align:right"></td>
                    <td align="right">已付</td>
                    <td><input class="input" name="" type="text" id="account_lentB" disabled="disabled" style="text-align:right"></td>
                    <td align="right">余额</td>
                    <td><input class="input widthB92" name="" type="text" id="account_remainB" disabled="disabled" style="text-align:right"></td>
                    <td align="right"><span class="red">限额+授权</span></td>
                    <td><input class="input widthB92" name="" type="text" id="account_totalB" disabled="disabled" style="text-align:right"></td>
                </tr>
				</table>
			</div>
		</div>
		<div class="tableDivDow clearBoth floatL widthAllBlock">
			<table width="98%">
				  <tr>
                    <td width="70">
                       <label><input class="radio" name="radioAccount" type="radio" value="1" checked="checked">A账页</label>
                    </td>
                    <td width="70">
                       <label><input class="radio" name="radioAccount" type="radio" value="2">B账页</label>
                    </td>
                    <td width="330">&nbsp;</td>
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

		<div class="guestBgF">
			<!--table-->
			<div class="tableDiv floatL margin-left-10" style="width:100%;height:310px" id="guestdetail_accountGrid">
			</div>
			<div class="clearBoth"></div>
		</div>

		<div
			class="tableDivDow clearBoth floatL widthAllBlock margin-bottom-10">
			<table width="98%">
				<tr>
					<td align="right" width="35">从</td>
					<td width="85"><input class="input" name="" id="account_startDate" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'account_inDate\')}',maxDate:'#F{$dp.$D(\'account_endDate\')}'})">
					</td>
					<td align="right" width="35">到</td>
					<td width="85"><input class="input" name="" id="account_endDate" type="text"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'account_startDate\')}'})">
					</td>
					<td><label><input class="radio" name="disturb" checked="checked"
							type="radio" value="N">未结</label> <label><input
							class="radio margin-left-10" name="disturb" type="radio"
							value="Y">已结</label> <label><input
							class="radio margin-left-10" name="disturb" type="radio"
							value="A">全部</label></td>
					<td width="80"><label><input class="checkbox" id="isInvalid"
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
		<a class="button_02 widthPx100" href="javascript:;" id="guest_bill_refush_smbtn">刷新(R)</a>
        <a class="button_02 widthPx100" id="checkOut" href="javascript:;" <mammoth:AuthJudge funcId="c_225758" />>结账(J)</a>
        <a class="button_02 widthPx100" id="deposit" href="javascript:;" <mammoth:AuthJudge funcId="c_713533" />>押金(D)</a>
        <a class="button_02 widthPx100" id="leavingHotel" href="javascript:;" <mammoth:AuthJudge funcId="c_918064" />>离店(Q)</a>
        <a class="button_02 widthPx100" id="receivables" href="javascript:;" <mammoth:AuthJudge funcId="c_834505" />>收款(S)</a>
        <a class="button_02 widthPx100" id="checkOutOperation" href="javascript:;">退房(U)</a>
        <a class="button_02 widthPx100" id="preAuthorization" href="javascript:;" <mammoth:AuthJudge funcId="c_357946" />>预授权(A)</a>
        <a class="button_02 widthPx100" id="guestSplitInfo" href="javascript:;">分账(F)</a>
        <a class="button_02 widthPx100" id="accountedFor" href="javascript:;" <mammoth:AuthJudge funcId="c_403156" />>入账(I)</a>
        <a class="button_02 widthPx100" id="customAccounts" href="javascript:;" <mammoth:AuthJudge funcId="c_164927" />>改单(E)</a>
        <a class="button_02 widthPx100" href="javascript:;"  id="cancleBill" <mammoth:AuthJudge funcId="c_629846" />>取消(C)</a>
        <a class="button_02 widthPx100" href="javascript:;" id="guest_bill_contract">合同(B)</a>
        <a class="button_02 widthPx100" id="transferAccounts" href="javascript:;" <mammoth:AuthJudge funcId="c_920879" />>转账(M)</a>
        <a class="button_02 widthPx100" href="javascript:;" id="guest_bill_print" >打印(P)</a>
        <a class="button_02 widthPx100" id="forALongTime" href="javascript:;" <mammoth:AuthJudge funcId="c_933336" />>半/全日租</a>
        <a class="button_02 widthPx100" href="javascript:;"  id="accountsQuit">退出(X)</a>
    	<div class="clearBoth red">注意加收半（全）日租</div>
		<ul class="guestUl userBlock">
			<li class="fontWeight">房间列表</li>
		</ul>
		<ul class="guestUl2" id="roomList2">
			<c:forEach items="${roomBills}" var="room" varStatus="status">
				<c:if test="${room.payman_flag eq '0'}">
					<li><a href='javascript:;' <c:if test="${room.room_id==checkRoom}"> class="roomListLi" </c:if> guestinfo-roomid="${room.room_id}">${room.room_id} (${room.sum}) </a>
				</c:if>
				<c:if test="${room.payman_flag eq '1'}">
					<li><a href='javascript:;'  guestinfo-roomid="${room.room_id}">($ ${room.sum}) </a>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<!--/右侧部分-->
</div>
<!--押金弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 depositDiv" id="depositDiv">
		<div class="alertMain greyBg" style="width:560px; margin-top:240px;">
	    	<h4 class="moveDivAlert2">押金<a href="javascript:;" class="closeDiv2 closeAlert" id="depositClose"></a></h4>
	        <div class="borderSolid">
	        	<!--押金-->
	        	<form id="depositForm">
	        	<table width="95%">
	            	<tr>
	                	<td align="right"><span class="red">单号</span></td>
	                    <td><input class="input" name="accoId" type="text" id="depositAcco"  data-validation="required,isNum" label="单号"></td>
	                    <td colspan="2">&nbsp;</td>
	                </tr>
	                <tr>
	                	<td width="20%" align="right">收款方式</td>
	                    <td>
	                    	<select class="select widthB99" id="depositSettle" name="setlId" disabled="disabled">
	                    		<c:forEach items="${hsettls}" var="hcode">
									<option value="${hcode.codeId}" <c:if test="${hcode.codeId==depositCode}">selected</c:if> >${hcode.codeNamec}</option>
								</c:forEach>
	                        </select>
	                    </td>
	                    <td width="20%" align="right">币种</td>
	                    <td>
	                    	<select class="select widthB99" id="depositMoneyKind" name="moneyKind"  disabled="disabled">
	                    		<c:forEach items="${exchanges}" var="hcode">
									<option value="${hcode.codeId}" <c:if test="${fn:trim(hcode.codeId)==moneyId}">selected</c:if> >${hcode.codeNamec}</option>
								</c:forEach>
	                        </select>
	                    </td>
	                </tr>
	                <tr>
	                	<td align="right"><span class="red">本位币金额</span></td>
	                    <td><input class="input" name="balance" type="text" id="depositBalance"  data-validation="required,positiveMoney" label="本位币金额"></td>
	                    <td align="right"><span class="red">外币金额</span></td>
	                    <td><input class="input" name="foreignm" type="text" disabled="disabled" data-validation="required,positiveMoney"></td>
	                </tr>
	                <tr>
	                	<td align="right">备注</td>
	                    <td colspan="3"><input class="input widthB98" name="notes" type="text"></td>
	                </tr>
	            </table>
	            <!--押金-->
	            </form>
	            <div class="alertRight clearBoth margin-top-30">
	               <a class="buttonLikeA floatR margin-right-30" href="javascript:;" id="depositQuit">退出</a>
	               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="depositCancle">放弃</a>
	               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="depositSubmit">确定</a>
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
	<!--离店弹出框-->
	<div class="alertDiv moveBar2 alertDiv2 LeavingHotelDiv" id="leavingHotelDiv">
		<div class="alertMain greyBg" style="width:650px; margin-top:200px;">
			<h4 class="moveDivAlert2">
				离店操作<a href="javascript:;" class="closeDiv2 closeAlert" id="leavingHotelClose"></a>
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
							class="radio margin-right-5" name="leavingHotelRadio" type="radio" value="D" checked="checked">当前客人</label>
					</li>
				</ul>
				<div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="LeavingHotelGrid" style="height:150px;over-flow:auto">
				</div>
				<!--table -END- -->
				<div class="clearBoth padding-top-10 textAlignRight margin-right-5">
					<label><input class="checkbox margin-right-5" name=""
						type="checkbox" value="">逐个提示</label>
				</div>
				<div class="alertRight clearBoth margin-top-30">
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="leavingHotelQuit">退出</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="leavingHotelCancle">放弃</a>
					<a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="leavingHotelConfirm">确定</a>
				</div>
				<div class="clearBoth"></div>
			</div>
		</div>
	</div>
	<!--/离店弹出框-->
	<!--预授权出框-->
<div class="alertDiv moveBar2 alertDiv2 preAuthorizationDiv" id="preAuthorizationDiv">
	<div class="alertMain greyBg" style="width:660px;margin-top:200px;">
    	<h4 class="moveDivAlert2">预授权<a href="javascript:;" class="closeDiv2 closeAlert" id="preAuthorizationClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" id="preAuthorizationGrid" style="height:150px;width:600px;overflow:auto">
                </div>
                <ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="preAuthRadio" type="radio" value="0" checked="checked">有效</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="preAuthRadio" type="radio" value="">全部</label></li>
                    <li class="floatR">有效预授权合计：<span id="preAuthSumBalance"></span></li>
                </ul>
                <!--table -END- -->
                <div class="clearBoth"></div>
            <div class="alertRight clearBoth margin-top-20">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="quitPreAuth" >退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="preAuthCancle" >取消</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="canclePreAuth" >取消预授权</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="finishPreAuth">完成预授权</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="addPreAward">新授权</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/预授权出框-->
<!--新增预授权-->
<div class="alertDiv moveBar3 alertDiv3 addPreAwardDiv" id="addPreAwardDiv">
	<div class="alertMain greyBg" style="width:460px; margin-top:180px;">
    	<h4 class="moveDivAlert3">新增预授权<a href="javascript:;" class="closeDiv2 closeAlert3" id="addPreAwardClose"></a></h4>
        <div class="borderSolid">
        	<form id="addPreAwardForm">
	        	<table width="95%">
	            	 <tr>
	                	<td align="right">授权号</td>
	                    <td><input class="input" name="authId" type="text" data-validation="required"></td>
	                </tr>
	                <tr>
	                	<td align="right">信用卡卡号</td>
	                    <td><input class="input" name="creditId" type="text" data-validation="required,isNum"></td>
	                </tr>
	                <tr>
	                	<td align="right">持卡人</td>
	                    <td><input class="input widthB50" name="creditHolder" type="text"></td>
	                </tr>
	                <tr>
	                	<td align="right">预授权金额</td>
	                    <td><input class="input widthB50" name="balance" type="text"  data-validation="required,positiveMoney" label="预授权金额"></td>
	                </tr>
	                <tr>
	                	<td align="right">有效期</td>
	                    <td><input class="input widthB50" name="expired" type="text" onclick="WdatePicker({minDate:new Date()})" data-validation="required"></td>
	                </tr>
	                <tr>
	                	<td align="right" valign="top"><div class="padding-top-10">备注</div></td>
	                    <td><textarea class="textarea widthB97 margin-top-5" name="note" cols="" rows=""></textarea></td>
	                </tr>
	            </table>
            </form>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;"  id="quitPreAward">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="canclePreAward">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="savePreAward">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--新增预授权END-->
<!--完成预授权-->
<div class="alertDiv moveBar3 alertDiv3 finishAuthDiv" id="finishAuthDiv">
	<div class="alertMain greyBg" style="width:460px; margin-top:180px;">
    	<h4 class="moveDivAlert3">完成预授权<a href="javascript:;" class="closeDiv2 closeAlert3" id="finishAuthClose"></a></h4>
        <div class="borderSolid">
        	<form id="finishAuthForm">
	        	<table width="95%">
	            	 <tr>
	                	<td align="right">授权额</td>
	                    <td><input class="input" name="" type="text" id="finishAuthBalance" disabled="disabled"></td>
	                </tr>
	                <tr>
	                	<td align="right">完成授权额</td>
	                    <td><input class="input" name="finishBalance" id="finishBalance" type="text" data-validation="required,positiveMoney"></td>
	                </tr>
	                 <tr>
	                	<td align="right">完成授权号</td>
	                    <td><input class="input" name="finishNo" id="finishNo" type="text" data-validation="required,justNumAndWord" maxlength = "10"></td>
	                </tr>
	                <tr>
	                	<td align="right" valign="top"><div class="padding-top-10">备注</div></td>
	                    <td><textarea class="textarea widthB97 margin-top-5" name="note" cols="" rows=""></textarea></td>
	                </tr>
	            </table>
            </form>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;"  id="finishAuthQuit">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="finishAuthCancle">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="finishAuthConfirm">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--完成预授权END-->
<!--取消预授权-->
<div class="alertDiv moveBar3 alertDiv3 cancleAuthDiv" id="cancleAuthDiv">
	<div class="alertMain greyBg" style="width:460px; margin-top:180px;">
    	<h4 class="moveDivAlert3">完成预授权<a href="javascript:;" class="closeDiv2 closeAlert3" id="cancleAuthClose"></a></h4>
        <div class="borderSolid">
        	<form id="cancleAuthForm">
	        	<table width="95%">
	            	 <tr>
	                	<td align="right">授权额</td>
	                    <td><input class="input" name="" type="text" id="cancleAuthBalance" disabled="disabled"></td>
	                </tr>
	                <tr>
	                	<td align="right">取消授权号</td>
	                    <td><input class="input" name="cancelNo" type="text" data-validation="required,justNumAndWord" maxlength = "10"></td>
	                </tr>
	                <tr>
	                	<td align="right" valign="top"><div class="padding-top-10">备注</div></td>
	                    <td><textarea class="textarea widthB97 margin-top-5" name="note" cols="" rows=""></textarea></td>
	                </tr>
	            </table>
            </form>
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-30" href="javascript:;"  id="cancleAuthQuit">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="cancleAuthCancle">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="cancleAuthConfirm">确定</a>
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--取消预授权END-->
<!--入账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 accountedForDiv" id="accountedForDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">入账<a href="javascript:;" class="closeDiv2 closeAlert" id="accountClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
        	<form id="accountForm">
            	<ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="1">A账页</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective" type="radio" value="2">B账页</label></li>
                </ul>
                <div class="tableDiv floatL margin-left-10 widthB70 margin-top-10 padding-10" style="width:450px">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right">房号</td>
                            <td width="30%"><input class="input" name="roomId" id="accountRoomId" type="text" ></td>
                            <td width="20%" align="right">可用金额</td>
                            <td><input class="input" style="text-align:right" name="" type="text" id="accountlimit" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <td align="right">账号</td>
                            <td><input class="input" name="billId" type="text" id="accountBillId" disabled="disabled"></td>
                            <td align="right">账单号</td>
                            <td><input class="input" name="accoId" id="accountAcco" type="text" data-validation="required,isNum" label="账单号"></td>
                        </tr>
                        <tr>
                            <td align="right">中文名</td>
                            <td>
                            	<select class="select widthB100" id="incomeNamec" name="incomeNamec">
                                </select>
                            </td>
                            <td align="right">消费点</td>
                            <td>
                            	<select class="select widthB100" id="accountConsume" name="consId" >
                            		<c:forEach items="${consumes}" var="hcode">
										<option value="${fn:trim(hcode.codeId)}" data-moneyid="${fn:trim(hcode.moneyId)}"   data-svcRate="${fn:trim(hcode.svcRate)}">${hcode.codeNamec}</option>
									</c:forEach>
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
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="accountRadio" type="radio" value="A" checked="checked">当前客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="accountRadio" type="radio" value="B">所有客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="accountRadio" type="radio" value="C">所有房间</label></li>
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
        			自：住客（<span id="transferGuestInfo"></span>）转出账目，<span id="transferInInfo"></span>
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
                                <label><input class="radio" name="transferTopRadio" type="radio" value="2">B3账</label>
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
<!--客人账目分账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 guestSplitInfoDiv" id="guestSplitInfoDiv">
	<div class="alertMain greyBg" style="width:555px;margin-top:150px;">
    	<h4 class="moveDivAlert2">分账<a href="javascript:;" class="closeDiv2 closeAlert" id="guestSplitClose"></a></h4>
        <div class="borderSolid">
        	<!--分账-->
        	<table width="100%">
            	<tr>
                	<td width="42%">
                    	<h5 class="margin-left-5 fontWeight">A账页项目</h5>
                    	<ul class="splitDivLeft margin-top-5" id="account_consumeAID">
                        </ul>
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
						<td width="110"><input class="input" id="acc_startDate" name="" type="text" disabled="disabled" onfocus="var date = $('#reach_date').val();WdatePicker({minDate:date,maxDate:'#F{$dp.$D(\'acc_endDate\')}'})">
						</td>
						<td width="80" align="right">终止日期</td>
						<td width="110"><input class="input" id="acc_endDate" name="" type="text" disabled="disabled" onclick="WdatePicker({minDate:'#F{$dp.$D(\'acc_startDate\')}'})">
						</td>
                </tr>
            </table>
            <!--分账-->
            
            <div class="alertRight clearBoth margin-top-30">
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;"  id="guestSplitQuit">退出</a>
               <!--<a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="accountConsumeCancle()">取消</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" onclick="accountConsumeSubmit()">确定</a>-->
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/客人账目分账弹出框-->
<!--半日租入账弹出框-->
<div class="alertDiv moveBar2 alertDiv2 forALongTimeDiv" id="forALongTimeDiv">
	<div class="alertMain greyBg" style="width:660px; margin-top:200px;">
    	<h4 class="moveDivAlert2">半/全日租<a href="javascript:;" class="closeDiv2 closeAlert" id="forLongClose"></a></h4>
        <div class="borderSolid">
        	<!--table-->
         		<ul class="widthB96 margin-left-10">
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective_forlong" type="radio" value="1">A账页</label></li>
                    <li class="floatL margin-right-20"><label><input class="radio margin-right-5" name="effective_forlong" type="radio" value="2">B账页</label></li>
                </ul>
                 <form id="forLongForm">
                <div class="tableDiv floatL margin-left-10 widthB70 margin-top-10 padding-10" style="width:450px">
                    <table width="100%">
                        <tr>
                            <td width="20%" align="right">房号</td>
                            <td width="30%"><input class="input" name="roomId" id="forLongRoomId" type="text"></td>
                            <td width="20%" align="right">可用金额</td>
                            <td><input class="input" name="" type="text" id="forLonglimit" disabled="disabled"></td>
                        </tr>
                        <tr>
                            <td align="right">账号</td>
                            <td><input class="input" name="billId" type="text" id="forLongBillId" disabled="disabled"></td>
                            <td align="right">账单号</td>
                            <td><input class="input" name="accoId" type="text" id="forLongAcco" data-validation="required" ></td>
                        </tr>
                        <tr>
                            <td align="right">中文名</td>
                            <td>
                            	<select class="select widthB100" id="forLongNamec" name="incomeNamec">
                                </select>
                            </td>
                            <td align="right">消费点</td>
                            <td>
                            	<select class="select widthB100" id="forLongConsume" name="consId" disabled="disabled">
                            		<c:forEach items="${consumes}" var="hcode">
										<option value="${fn:trim(hcode.codeId)}" data-svcRate="${fn:trim(hcode.svcRate)}" <c:if test="${fn:trim(hcode.codeId)==forALongCode}">selected</c:if>>${hcode.codeNamec}</option>
									</c:forEach>
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
                            	<select class="select widthB100" id="forLongExchange" name="moneyKind" disabled="disabled">
                            		<c:forEach items="${exchanges}" var="hcode">
										<option value="${fn:trim(hcode.codeId)}" <c:if test="${fn:trim(hcode.codeId)==moneyId}">selected</c:if> >${hcode.codeNamec}</option>
									</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">&nbsp;</td>
                            <td><label><input class="checkbox margin-right-5" name="" type="checkbox" id="forLongIsserve">收服务费</label></td>
                            <td align="right">本位币</td>
                            <td><input class="input" name="balance" type="text" id="forLongBalance"  data-validation="required"  ></td>
                        </tr>
                        <tr>
                            <td align="right">服务费</td>
                            <td><input class="input" name="svcCharge" id="forLongServeMoney" type="text" disabled="disabled"></td>
                            <td align="right">外币金额</td>
                            <td><input class="input" name="foreignm" id="forLongForeignm" type="text"></td>
                        </tr>
                        <tr>
                            <td align="right">备注</td>
                            <td colspan="3"><input class="input widthB98" name="notes" id="forLongNotes" type="text"></td>
                        </tr>
                    </table>
                    <!--table enner -END- -->
                </div>
                </form>
                <ul class="floatL margin-left-40 margin-top-10">
                	<li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"  id="forLongSubmit">确定</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" id="forLongFormReset">放弃</a></li>
                    <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;"   id="forLongQuit">退出</a></li>
                    <li class="margin-top-10"><label><input class="radio margin-right-5" name="forLongRadio" type="radio" value="A" checked="checked">当前客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="forLongRadio" type="radio" value="B">所有客人</label></li>
                    <li class="margin-top-5"><label><input class="radio margin-right-5" name="forLongRadio" type="radio" value="C">所有房间</label></li>
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
    	<h4 class="moveDivAlert2">退房<a href="javascript:;" class="closeDiv2 closeAlert" id="retreatRoomClose"></a></h4>
        <div class="borderSolid">
        	<div class="tableDiv floatL margin-left-10 widthB93 margin-top-10 padding-10" id="retreatRoomFirstDiv">
            	<h1 class="margin-left-20 margin-top-20"><span id="retreatFirstRoomId"></span>是否退房？</h1>
                <div class="margin-top-10 floatR">
                	<a class="buttonLikeA floatL margin-right-10" href="javascript:;"   id="retreatRoomConfirm">是(Y)</a>
                	<a class="buttonLikeA floatL margin-right-20" href="javascript:;" id="retreatRoomQuit">否(N)</a>
                </div>
            </div>
            <div class="tableDiv floatL margin-left-10 widthB93 margin-top-10 padding-10" id="retreatRoomSecondDiv">
            	<h1 class="margin-left-20 margin-top-20"><span id="retreatSecondRoomId"></span> 属于<span id="checkoutIsGroup"></span>，是否退房？</h1>
                <div class="margin-top-10 floatR">
                	<a class="buttonLikeA floatL margin-right-10" href="javascript:;"   id="retreatAllRoom">全部退房</a>
                    <a class="buttonLikeA floatL margin-right-10" href="javascript:;"  id="retreatRoom">仅退此房</a>
                	<a class="buttonLikeA floatL margin-right-20" href="javascript:void(0);"   id="retreatQuit">取消</a>
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