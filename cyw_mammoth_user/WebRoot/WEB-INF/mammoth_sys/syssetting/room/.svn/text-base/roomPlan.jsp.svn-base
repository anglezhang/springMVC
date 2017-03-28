<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>房间管理--房价方案</title>
</head>

<body>
	<%@ include file="../../header.jsp"%>
	<!--secondMenu-->
	<%@ include file="../secondMenu.jsp"%>
	<!--secondMenu -end- -->
	<div class="center">
    	<!--mainInformation-->
    	<div class="mainInfo">
        	<h4 class="fontWeight green margin-top-10">房价方案</h4>
            <div class="mainMation margin-top-5">
            	<!--table-->
            	<div class="tableDiv tabInformation" id="theGrid">
                </div>
                <!--table -END- -->
            </div>
            <!-- choice -->
			<form id="searchId" action="${ctx}/hroomPlan/list.do" method="post">
	            <div class="choice">
               	 	<table class="widthB80 marginLRAuto margin-top-10 margin-bottom-30">
	                    <tr>
	                        <td width="50%" align="center">
	                        	<label>
	                        		<input name="status" type="radio" value="0"
										<c:if test="${status eq 0 or empty status}">checked</c:if>
										onclick="getSearch()" />有效
								</label>
	                        </td>
	                        <td align="center">
	                        	<label>
	                        		<input name="status" type="radio" value="1"
										<c:if test="${status eq 1}">checked</c:if>
										onclick="getSearch()">无效
	                        	</label>
	                        </td>
	                    </tr>
	                </table>
	                <c:choose>
	                	<c:when test="${status eq 0 or empty status}">
		                	<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:goAdd(4);" id="add">新&nbsp;&nbsp;增</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="del">取&nbsp;&nbsp;消</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="batchUpdateDate">批量改期</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;"id="roomPlanPrint">打&nbsp;&nbsp;印</a>
	                	</c:when>
	                	<c:otherwise>
	                		<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
			                <a class="button_03 marginLRAuto widthB60" 
			                	 <c:choose>
	                				<c:when test="${empty listJson || fn:length(listJson) == 0  || fn:length(listJson) == 2 }">
	                					style="color: grey;cursor:not-allowed;"
	                					href="javascript:;"
	                				</c:when>
	                				<c:otherwise>
	                					href="javascript:goBack();"
			                		</c:otherwise>
		                		</c:choose>
			                	id="back">恢&nbsp;&nbsp;复</a>
	                	</c:otherwise>
	                </c:choose>
	            </div>
			</form>
			<!-- 被删除的条目id -->
			<input type="hidden" id="delIds" name="delIds" />
			<!-- 被恢复的条目id -->
			<input type="hidden" id="backIds" name="backIds" />
			<!-- 房价方案  当前修改条目的id -->
			<input type="hidden" id="currUpdId" name="currUpdId" />
			<!-- 房价方案  当前修改条目所在的行号 -->
			<input type="hidden" id="currUpdRow" name="currUpdRow" />
        </div>
        <!--mainInformation END-->
        <%@ include file="right.jsp" %>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
	</div>
	<!--center -END -->
	<%@ include file="../../footer.jsp"%>
<div class="alertDivBg"></div>
<!--房价方案列表ALERT-->
<div id="roomPlanListAlert" class="alertDiv moveBar2 alertDiv2 housingProjectDiv">
	<div class="alertMain greyBg" style="width:664px;margin-top:100px;">
    	<h4 class="moveDivAlert2">房价方案设置<a href="javascript:closeDiv('roomPlanListAlert');" class="closeDiv2 closeAlert"></a></h4>
		<iframe id="roomPlanListFrame" name="roomPlanListFrame" src="" style="margin: 0 8px 8px; width: 648px; height: 600px;border: 0;box-shadow: 1px 0 15px 2px #464646;"></iframe>
    </div>
</div>
<!--房价方案列表ALERT-->
<!--批量改期ALERT-->
<div id="batchUpdDateAlert" class="alertDiv moveBar2 alertDiv2 batchUpdDateAlert" style="z-index:22">
	<div class="alertMain" style="width:500px;">
		<h4 class="moveDivAlert"> 批量修改有效日期<a href="javascript:closeDiv('batchUpdDateAlert');" class="closeDiv"></a></h4>
		<div class="borderSolid">
			<div class="roomButtonFblock" id="whitBlock" style="overflow-y:auto;height: 100px;line-height: 48px;">
				<table width="100%">
					<tr>
						<td style="text-align: right;" width="32%">
							起始日期
						</td>
						<td style="text-align: left;">
							<input class="input ui-widget-content ui-corner-all Wdate" name="batchStartDate" id="batchStartDate" type="text" style="width: 200px;"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							截止日期
						</td>
						<td style="text-align: left;">
							<input class="input ui-widget-content ui-corner-all Wdate" name="batchEndDate" id="batchEndDate" type="text" style="width: 200px;"/>
						</td>
					</tr>
				</table>
			</div>
			<table width="460">
				<tr>
					<td align="right">
						<a href="javascript:closeDiv('batchUpdDateAlert');" class="button_03 floatR margin-left-10">取消(N)</a> 
						<a href="javascript:goBatchUpdateDate();" class="button_03 floatR" id="saveBatchUpdDate">确定(Y)</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<!--批量改期-->
<script type="text/javascript">
	$(function(){
		// 有效  true 否则 无效
		var status_search = ( "${status eq 1}" === "false" ) ;
		var dataJson = eval('(' + '${listJson}' + ')');
		// 初始化列表
		fillHroomPlanTable(dataJson,status_search);
	});
</script>
<script src="${ctx}/js/syssetting/room/roomPlan.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/flexGridEditComm.js" type="text/javascript" ></script>
<link href="${ctx}/js/syssetting/flexGridEditComm.css" rel="stylesheet" type="text/css" />
</body>
</html>
