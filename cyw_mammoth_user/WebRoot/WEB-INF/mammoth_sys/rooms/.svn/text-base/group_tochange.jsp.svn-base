<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理</title>
<style type="text/css">
	.dataTable{
		table-layout:fixed;/* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
	}
	.dataTable td{
	    word-break:keep-all;/* 不换行 */
	    white-space:nowrap;/* 不换行 */
	    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
	    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
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
	
	<!--弹出层阴影结束-->
	<!--建筑ADD弹出-->
	<div class="alertDiv buildingAdd moveBar displayBlock">

		<div class="alertMain" style="width:870px;position:absolute;left:50%;top:0;margin-left:-25%;">
			<div class="barderWSolid">
				<h4 class="moveDivAlert">
					散团互转<a href="javascript:;" class="closeDiv"></a>
				</h4>
				<!--弹出层内容-->
				<div class="borderSolid">
					<table class="table margin-top-20 height400">
						<tr>
							<td width="360" align="center" valign="top">
								<form id="formL">
									<table width="100%">
										<tr>
											<td align="right">团代码/团名</td>
											<td colspan="4"><input class="input" type="text"
												id="grp_id_nameL" name="grp_id_nameL" onkeydown="searchL();" onchange="lockInput('grp_id_nameL','roomidL');">
											</td>
										</tr>
										<tr>
											<td width="100" align="right">相关房号</td>
											<td width="60"><input class="input" name="roomidL"
												id="roomidL" type="text" onkeydown="searchL();" onchange="lockInput('roomidL','grp_id_nameL');"></td>
											<td width="100" align="right"><span class="red">B账+授权</span>
											</td>
											<td width="40"><input class="input gry_9" id="billL"
												readonly="readonly" name="billL" type="text" value="">
											</td>
											<td><span class="red">笔</span></td>
										</tr>
									</table>
								</form> <!--table-->
								<div
									class="tableDiv margin-left-10 widthB95 whiteBackground margin-top-20"
									id="grop_divL">
									<!--table title-->
									<table class="tableMain tableMain23">
										<thead>
											<tr>
												<td width="50px">房号</td>
												<td>中文名</td>
												<td width="80px">英文名</td>
												<td width="60px">付费人</td>
												<td width="8px"></td>
											</tr>
										</thead>
									</table>
									<!--table title end -->
									<!--table enner-->
									<div class="tableHeiScll height60 height300">
										<table class="tableMain tabChangBg dataTable">
											<tbody id="div_gridL">
												
											</tbody>
										</table>
									</div>
									<!--table enner -END- -->
								</div>
							</td>
							<!-- -------------------------------------------------第二个开始框---------------------------------------------------------------- -->
							<td align="center"><a class="forSN margin-top-90"
								onclick="singleMove('left');"><img
									src="${pageContext.request.contextPath}/img/right_01.png">
							</a> <!-- 单个左移 --> <a class="forSN margin-top-10"
								onclick="allMove('left');"><img
									src="${pageContext.request.contextPath}/img/right_02.png">
							</a> <!-- 批量左移 --> <a class="forSN margin-top-50"
								onclick="singleMove('right');"><img
									src="${pageContext.request.contextPath}/img/left_01.png">
							</a> <!-- 单个右移 --> <a class="forSN margin-top-10"
								onclick="allMove('right');"><img
									src="${pageContext.request.contextPath}/img/left_02.png">
							</a> <!-- 批量右移 -->
							</td>
							<td width="360" valign="top">
								<form id="formR">
									<table width="100%">
										<tr>
											<td align="right">团代码/团名</td>
											<td colspan="4"><input class="input" name="grp_id_nameR"
												id="grp_id_nameR" type="text" onkeydown="searchR();" onchange="lockInput('grp_id_nameR','roomidR');">
											</td>
										</tr>
										<tr>
											<td width="100" align="right">相关房号</td>
											<td width="60"><input class="input" id="roomidR"
												name="roomidR" type="text" onkeydown="searchR();" onchange="lockInput('grp_id_nameR','roomidR');"></td>
											<td width="100" align="right"><span class="red">B账+授权</span>
											</td>
											<td width="40"><input class="input gry_9" readonly="readonly"
												id="billR" name="billR" type="text" value=""></td>
											<td><span class="red">笔</span></td>
										</tr>
									</table>
								</form> <!--table-->
								<div
									class="tableDiv floatL margin-left-10 widthB95 whiteBackground margin-top-20">
									<!--table title-->
									<table class="tableMain tableMain23">
										<thead>
											<tr>
												<td width="50px">房号</td>
												<td>中文名</td>
												<td width="80px">英文名</td>
												<td width="60px">付费人</td>
												<td width="8px"></td>
											</tr>
										</thead>
									</table>
									<!--table title end -->
									<!--table enner-->
									<div class="tableHeiScll height60 height300">
										<table class="tableMain tabChangBg dataTable">
											<tbody id="div_gridR">

											</tbody>
										</table>
									</div>
									<!--table enner -END- -->
								</div>
							</td>
						</tr>

					</table>
					<div class="alertRight clearBoth margin-top-20">
						<a class="buttonLikeA floatR margin-right-10" onclick="exitdiv();">退出</a>
						<a class="buttonLikeA floatR margin-right-10" id="cancel" style="color: grey;cursor:not-allowed;">放弃</a>
						<a class="buttonLikeA floatR margin-right-10" id="ok" style="color: grey;cursor:not-allowed;">确定</a>
					</div>
					<div class="clearBoth"></div>
				</div>
				<!--/弹出层内容-->
			</div>
		</div>
	</div>
	<input type="hidden" id="path" name="path"
		value="${pageContext.request.contextPath}">
	<input type="hidden" id="grp_chkidL" name="grp_chkidL" value="0">
	<input type="hidden" id="with_idL" name="with_idL" value="0">
	<input type="hidden" id="billb_idL" name="billb_idL" value="0">
	<input type="hidden" id="gst_typeL" name="gst_typeL" value="S">

	<input type="hidden" id="grp_chkidR" name="grp_chkidR" value="0" />
	<input type="hidden" id="with_idR" name="with_idR" value="0">
	<input type="hidden" id="billb_idR" name="billb_idR" value="0">
	<input type="hidden" id="gst_typeR" name="gst_typeR" value="S">
	<!--/建筑ADD弹出-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rooms/groupTochange.js"></script>
</body>
</html>
