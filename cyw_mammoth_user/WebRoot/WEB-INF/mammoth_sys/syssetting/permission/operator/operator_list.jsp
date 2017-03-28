<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设置-用户及权限-用户管理</title>

<body>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../secondMenu.jsp" %>
<div class="center">
    <div class="main" id="webMain">
    	<div class="mainInfo">
        	<h4 class="fontWeight green margin-top-10">用户管理</h4>
            <div class="mainMation margin-top-5">
            	<div class="tableDiv tabInformation" id="theGrid">
                </div>
            </div>
            <!-- choice -->
			<form id="searchId" action="${ctx}/operator/list.do" method="post">
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
			                <a class="button_03 marginLRAuto widthB60" href="javascript:goAdd(6);" id="add">新&nbsp;&nbsp;增</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="del">取&nbsp;&nbsp;消</a>
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
			<input type="hidden" id="delIds" name="delIds" />
			<input type="hidden" id="backIds" name="backIds" />
        </div>
    	<%@ include file="../right.jsp" %>
        <div class="clearBoth"></div>
	</div>
</div>
<%@ include file="../../../footer.jsp" %>
</body>
<script type="text/javascript">
	$(function(){
		// 有效  true 否则 无效
		var status_search = ( "${status eq 1}" === "false" ) ;
		var dataJson = eval('(' + '${listJson}' + ')');
		var groupIds = eval('(' + '${groupIds}' + ')');
		// 初始化列表
		fillOperatorTable(dataJson,groupIds,status_search);
	});
</script>
<script src="${ctx}/js/syssetting/permission/operator_list.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/flexGridEditComm.js" type="text/javascript" ></script>
<link href="${ctx}/js/syssetting/flexGridEditComm.css" rel="stylesheet" type="text/css" />
</html>
