<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>房间管理--钟点房设置</title>
</head>
<body>
	<%@ include file="../../header.jsp"%>
	<!--secondMenu-->
	<%@ include file="../secondMenu.jsp"%>
	<!--secondMenu -end- -->
	<div class="center">
		<!--mainInformation-->
		<div class="mainInfo">
			<h4 class="fontWeight green margin-top-10">钟点房设置</h4>
			<div class="mainMation margin-top-5">
				<!--table-->
				<div class="tableDiv tabInformation" id="theGrid">
					<!--table title-->
                	<table class="tableMain">
                    	<thead> 
                            <tr>
                                <td width="12%">房型</td>
                                <td width="8%">价格</td>
                                <td width="15%">时长（小时）</td>
                                <td width="12%">起始时段</td>
                                <td width="12%">截止时段</td>
                                <td width="17%">每超时（分钟）</td>
                                <td width="10%">加时价格</td>
                                <td>超时全价</td>
                            </tr>
                        </thead>
                    </table>
                    
                    <!--table title end -->
                    <!--table enner-->
                    <div class="tableHeiScll" style="overflow: auto;height: 590px;">
                        <table class="tableMain tabChangBg">
                            <tbody>
                            	<tr>
                                    <td width="12%">SDT：标准间</td>
                                    <td width="8%">60.00</td>
                                    <td width="15%">4</td>
                                    <td width="12%">07:00</td>
                                    <td width="12%">18:00</td>
                                    <td width="17%">30</td>
                                    <td width="10%">10</td>
                                    <td>120</td>
                                </tr>
                                <tr>
                                    <td>SDT：标准间</td>
                                    <td>60.00</td>
                                    <td>4</td>
                                    <td>07:00</td>
                                    <td>18:00</td>
                                    <td>30</td>
                                    <td>10</td>
                                    <td>120</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
				</div>
				<!--table -END- -->
			</div>
			<!-- choice -->
			<form id="searchId" action="${ctx}/building/list.do" method="post">
            <div class="choice">
                <table class="widthB70 marginLRAuto margin-top-30 margin-bottom-30">
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
		                <a class="button_03 marginLRAuto widthB60" href="javascript:goAdd();" id="add">新&nbsp;&nbsp;增</a>
		                <a class="button_03 marginLRAuto widthB60" href="javascript:goDel();"id="del">取&nbsp;&nbsp;消</a>
                	</c:when>
                	<c:otherwise>
                		<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
		                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
		                <a class="button_03 marginLRAuto widthB60" href="javascript:goBack();"id="del">恢&nbsp;&nbsp;复</a>
                	</c:otherwise>
                </c:choose>
            </div>
			</form>
			<input type="hidden" id="delIds" name="delIds" />
			<input type="hidden" id="backIds" name="backIds" />
		</div>
		<%@ include file="right.jsp"%>
		<div class="clearBoth"></div>
	</div>
	<%@ include file="../../footer.jsp"%>
	<script type="text/javascript">
	$(function(){
	});
</script>
</body>
<style type="text/css">
	.fg_column_readOnly{
		background-color:#EAEAEA;
	}
	.fg_column_readOnly_cell{
		color:grey;
	}
	.select-row-yellow{
		color: #ee6767;
	}
	.eidt-row-blue{
		color: blue;
	}
</style>
</html>
<!-- 
<script type="text/javascript">
	$(function(){
		// 有效  true 否则 无效
		var status_search = ( "${status eq 1}" === "false" ) ;
		var dataJson = eval('(' + '${listJson}' + ')');
		// 初始化列表
		fillClockRoomTable(dataJson,status_search);
	});
</script>
<script src="${ctx}/js/syssetting/clockRoom.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/flexGridEditComm.js" type="text/javascript" ></script>
<link href="${ctx}/js/syssetting/flexGridEditComm.css" rel="stylesheet" type="text/css" />

 -->