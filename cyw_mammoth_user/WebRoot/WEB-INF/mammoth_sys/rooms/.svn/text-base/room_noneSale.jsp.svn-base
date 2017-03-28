<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>不可售原因</title>
</head>
	<body>
		<h5 class="margin-left-10" id="roomId_roomType_h5">${roomId}   | ${typeName}</h5>
		<div class="tableDiv floatL margin-left-10 widthB97 margin-top-10" style="height: 300px;">
			<table class="tableMain">
	            <thead> 
	                <tr>
	                    <td width="10%">序号</td>
	                    <td width="10%">房号</td>
	                    <td width="20%">开始日期</td>
	                    <td width="20%">结束日期</td>
	                    <td width="15%">原因</td>
	                    <td>详情</td>
	                </tr>
	            </thead>
	        </table>
	        <div class="tableHeiScll" style="height:250px;">
	        	<table class="tableMain">
	        		 <tbody>
	        		 <c:forEach items="${list}" var="map" varStatus="status">
	        		 	<tr>
	                        <td width="10%">${ status.index + 1}</td>
	                        <td width="10%">${map.roomid}</td>
	                        <td width="20%">${fn:substring(map.startDate,0,9)}</td>
	                        <td width="20%">${fn:substring(map.endDate,0,9)}</td>
	                        <td width="15%">${map.type}</td>
	                        <c:if test="${fn:length(map.noSaleInfo) > 10}"><td>${fn:substring(map.noSaleInfo,0,6)}...</td></c:if>
	                        <c:if test="${fn:length(map.noSaleInfo) <= 10}"><td>${map.noSaleInfo}</td></c:if>
	                    </tr>
	        		 </c:forEach>
	        		 </tbody>
	        	</table>
	        </div>
		</div>
		<table width="560" >
            <tr>
                <td align="right">
                	<a id="rooms_nonosale_cancle" href="javascript:;" class="button_03 floatR margin-left-10">关闭(N)</a> 
                </td>
            </tr>
        </table>
        <script>
        	setTimeout(function()
    		{
    			$("#rooms_nonosale_cancle").unbind('click');
    			$("#rooms_nonosale_cancle").bind('click',function(event)
				{
					$("#closeWindowsroom_nonesale_roomreason").trigger('click');
				});
    		},200);
        </script>
	</body>
</html>