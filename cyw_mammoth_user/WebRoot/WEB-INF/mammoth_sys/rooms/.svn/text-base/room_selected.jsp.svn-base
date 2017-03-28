<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选中房间</title>
<body>
	<div class="widthB100">
		<div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
			<table class="tableMain">
	            <thead> 
	                <tr>
	                    <td width="15%">序号</td>
	                    <td width="25%">建筑</td>
	                    <td width="15%">楼层</td>
	                    <td width="20%">房号</td>
	                    <td>房类</td>
	                </tr>
	            </thead>
	        </table>
	        <div class="tableHeiScll height300">
	        	<table class="tableMain">
	        		 <tbody>
	        		 <c:forEach items="${list}" var="room" varStatus="status">
	        		 	<tr>
	                        <td width="15%">${ status.index + 1}</td>
	                        <td width="25%">${room.buildName}</td>
	                        <td width="15%">${room.floorNo}</td>
	                        <td width="20%">${room.roomId}</td>
	                        <td>${room.roomTypeName}</td>
	                    </tr>
	        		 </c:forEach>
	        		 </tbody>
	        	</table>
	        </div>
		</div>
	 	<span class="margin-left-10" href="javascript:;">选定房间：${list.size()}</span>
        <div class="clearBoth">
        	<a id="rooms_selectedroom_cancel" closewindow="closeWindowsrooms_selected_list" class="button_02 floatR margin-left-5" href="javascript:;">关闭</a>
        </div>
	</div>
	<div class="clearBoth"></div>
	<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
	<script type="text/javascript">
		seajs.use("${pageContext.request.contextPath}/js/rooms/plugins/roomsSelected.js?number=" + Math.random());
	</script>
</body>
