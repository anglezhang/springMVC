<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房型</title>
</head>
	<body>
		<div class="roomButtonFblock" style="height:200px;">
			<table width="80%" class="margin-left-40">
				<c:forEach items="${list}" var="room">
					<tr>
                        <td width="150" align="right">${room.name}<span class="margin-left-10">${room.id}</span></td>
                        <td width="30" align="center"><input name="rooms_types_value" room-codeid="${room.id}" type="checkbox" value="${room.id}"></td>
                        <td width="40"><input class="smailInputT" name="rooms_types_number" type="text" value="0"></td>
                        <td room-count="${room.count}">(${room.count})</td>
                    </tr>
				</c:forEach>
			</table>
		</div>
		<table width="460" >
			<tr>
				<td>
					<label>
						<input id="rooms_type_selecall" class="radio"  type="checkbox"><span class="margin-left-10">全选</span>
					</label>
				</td>
			</tr>
            <tr>
                <td align="right"><a href="javascript:;" closewindow="closeWindowsroom_typeinfo_list" id="rooms_types_cancle" class="button_03 floatR margin-left-10">取消(N)</a> <a href="javascript:;" id="rooms_types_confirm" class="button_03 floatR">确定(Y)</a></td>
            </tr>
        </table>
        <script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
        <script type="text/javascript">
        	seajs.config({
		        base: '${pageContext.request.contextPath}/js/lib/'
		        , alias: {
		            'template': 'template/template'
		        }
		    });
		    seajs.use("${pageContext.request.contextPath}/js/rooms/plugins/roomsType.js?number=" + Math.random());
        </script>
	</body>
</html>