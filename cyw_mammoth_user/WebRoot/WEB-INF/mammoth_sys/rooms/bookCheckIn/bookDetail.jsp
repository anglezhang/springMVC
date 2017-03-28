<%@ page language="java" pageEncoding="UTF-8"%>
<!--订单详情-->
<div id="bookDetail" class="alertDiv checkInDiv moveBar teamOrdersDiv">
	<div class="alertMain greyBg" style="width:970px; margin-top:10px;">
		<h4 class="moveDivAlert" id="MoveAlertDiv">
			<span id="orderDetailTitle">散客预订</span><a href="javascript:closeBookDetail();" class="closeDiv"></a>
		</h4>
		<div class="borderSolid">
			<div class="clearBoth GuestTabIn fitBook posR">
				<div class="GuestTabL3">
					<table style="width: 100%;">
						<tr>
							<td width="70" align="right">订单号</td>
							<td width="130"><input class="input" name="" type="text">
							</td>
							<td width="70" align="right">客人来源</td>
							<td width="130"><select class="select widthB100">
									<option>自来散客</option>
									<option>会员</option>
									<option>网络分销</option>
									<option>旅行社</option>
									<option>其他</option>
							</select>
							</td>
							<td width="90" align="right">预订方式</td>
							<td><select class="select widthB95">
									<option>自来散客</option>
									<option>会员</option>
									<option>网络分销</option>
									<option>旅行社</option>
									<option>其他</option>
							</select>
							</td>
						</tr>
						<tr>
							<td width="70" align="right">会员卡号</td>
							<td width="130"><input class="input" name="" type="text">
							</td>
							<td width="70" align="right">客人分类</td>
							<td width="130"><select class="select widthB100">
									<option>--</option>
							</select>
							</td>
							<td width="90" align="right"><span class="gry_9">预定时间</span>
							</td>
							<td><input class="input widthB92" name="" type="text">
							</td>
						</tr>
						<tr>
							<td align="right">预订人</td>
							<td><input class="input widthB92" name="" type="text">
							</td>
							<td align="right">合约单位</td>
							<td>
								<table style="width: 100%;">
									<tr>
										<td style="padding:0;"><input class="input" name=""
											type="text" placeholder="">
										</td>
										<td width="22" align="left"><a class="HYbutton"
											href="javascript:;"></a>
										</td>
									</tr>
								</table>
							</td>
							<td align="right">订单状态</td>
							<td><select class="select">
									<option>--</option>
							</select>
							</td>
						</tr>
						<tr>
							<td align="right">联系电话</td>
							<td><input class="input" type="text" value="" /></td>
							<td align="right">销售员</td>
							<td><input class="input" name="" type="text"></td>
							<td align="right"><span class="gry_9">确认时间</span></td>
							<td><input class="input widthB92" name="" type="text">
							</td>
						</tr>
						<tr>
							<td align="right">备注</td>
							<td colspan="3"><input class="input widthB98" name=""
								type="text"></td>
							<td colspan="2"><a
								class="button_02 margin-top-none floatR margin-right-5"
								href="javascript:otherInfoDetail();" id="otherInformation">其他信息</a>
							</td>
						</tr>
					</table>
					<div class="clearBoth"></div>
				</div>

				<div
					class="GuestTabL3 floatL padding-left-none padding-right-none padding-bottom-none"
					style="border:none;width:758px;background:none;">

					<div class="GuestTabL3" style="width:76%;">
						<table style="width: 100%;">
							<tr>
								<td width="70" align="right">团代码</td>
								<td width="100"><input class="input" type="text" value="" />
								</td>
								<td width="70" align="right"><span class="gry_9">抵店日期</span>
								</td>
								<td width="100"><input class="input" type="text" value="" />
								</td>
								<td width="70" align="right">房价方案</td>
								<td><select class="select widthB100">
										<option>--</option>
								</select>
								</td>
							</tr>
							<tr>
								<td align="right">团名</td>
								<td><input class="input" type="text" value="" /></td>
								<td align="right"><span class="gry_9">离店日期</span></td>
								<td><input class="input" name="" type="text"></td>
								<td align="right"><span class="red">支付方式</span></td>
								<td><select class="select widthB100">
										<option>--</option>
								</select>
								</td>
							</tr>
							<tr>
								<td align="right">领队名</td>
								<td><input class="input" type="text" value="" /></td>
								<td align="right">预抵时间</td>
								<td><input class="input" name="" type="text"></td>
								<td align="right"><span class="red">团账限额</span></td>
								<td><input class="input" type="text" value="" /></td>
							</tr>
							<tr>
								<td align="right">英文名</td>
								<td><input class="input" type="text" value="" /></td>
								<td align="right">预计人数</td>
								<td><input class="input" name="" type="text"></td>
								<td align="right">团账账号</td>
								<td><input class="input" name="" type="text"></td>
							</tr>
							<tr>
								<td align="right">电话</td>
								<td colspan="3"><input class="input widthB99" type="text"
									value="" /></td>
								<td align="right">团账余额</td>
								<td><input class="input" name="" type="text"></td>
							</tr>
						</table>
					</div>
					<ul class="payMan floatR margin-bottom-none">
						<li><label><input name="" type="checkbox" value="">可挂AR账</label>
						</li>
						<li><label><input name="" type="checkbox" value="">可挂房账</label>
						</li>
						<li><label><input name="" type="checkbox" value="">隐藏房价</label>
						</li>
						<li><label><input name="" type="checkbox" value="">免费服务</label>
						</li>
						<li><label><input name="" type="checkbox" value="">自动转熟客</label>
						</li>
					</ul>
					<ul class="payMan floatR borderNone margin-bottom-none">
						<li class="clearBoth"><input class="buttonBefore" name=""
							style="margin-top:-8px;" type="checkbox" value=""><a
							class="button_02 margin-top-none" style="margin-top:3px;"
							href="javascript:;">房间特征</a></li>
						<li class="clearBoth"><input class="buttonBefore" name=""
							style="margin-top:-8px;" type="checkbox" value=""><a
							id="split" class="button_02" style="margin-top:3px;"
							href="javascript:;">分账设置</a></li>
					</ul>
				</div>


				<div class="skSec2">
					<div class="tableDiv left" style="width:757px;">
						<!--table title-->
						<table class="tableMain">
							<thead>
								<tr>
									<td width="100">序号</td>
									<td width="100">订单号</td>
									<td width="100">中文名</td>
									<td width="100">英文名</td>
									<td width="100">联系电话</td>
									<td width="100">销售员</td>
								</tr>
							</thead>
						</table>
						<!--table title end -->
						<!--table enner-->
						<div class="overflowScro">
							<table class="tableMain tabChangBg">
								<tbody>
									<tr>
										<td width="100">001</td>
										<td width="100">21601</td>
										<td width="100">TingTao</td>
										<td width="100">听涛阁</td>
										<td width="100">听涛阁</td>
										<td width="100"><a class="reservedRoom"
											href="javascript:stayRoom();">留房</a></td>
									</tr>
									<tr>
										<td>002</td>
										<td>21601</td>
										<td>TingTao</td>
										<td>听涛阁</td>
										<td>听涛阁</td>
										<td><a class="reservedRoom" href="javascript:stayRoom();">留房</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!--table enner -END- -->
					</div>
					<table style="width: 100%;">
						<tr>
							<td width="100">订房数：0</td>
							<td width="100">留房数：0</td>
							<td width="200">房价合计：99999.99</td>
							<td align="right"><a href="javascript:;">留房表</a></td>
						</tr>
					</table>

				</div>
				<!--右侧部分-->
				<div class="GuestTabR GTabPos posA margin-top-20">
					<a class="button_02" href="javascript:;">确定</a> <a
						class="button_02" href="javascript:;">放弃</a> <a class="button_02"
						href="javascript:;">复制订单</a> <a class="button_02"
						href="javascript:;">预订金</a> <a class="button_02"
						href="javascript:;">预发房卡</a> <a class="button_02"
						href="javascript:closeBookDetail();">退出</a>
				</div>
				<!--/右侧部分-->
			</div>
			<div class="clearBoth"></div>
		</div>
	</div>
</div>
<!--/订单详情->