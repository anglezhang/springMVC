<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间特征</title>
</head>
	<body>
		<div class="roomButtonFblock" style="height: 200px;">
			<c:forEach items="${list}" var="hroomchr" varStatus="index">
				<ul class="FTsx">
					<li>
						<label>
							<input class="checkbox margin-right-5" name="rooms_hroomchr_value" data-text="${hroomchr.codeNamec}" type="checkbox" value="${hroomchr.placeholderId}">${hroomchr.codeNamec}
						</label>	
					</li>
				</ul>
			</c:forEach>
		</div>
		<table width="460" >
			<tr>
				<td><label><input class="radio" id="rooms_hroomchr_value_checkall" type="checkbox"><span class="margin-left-10">全选</span></label></td>
			</tr>
            <tr>
                <td align="right"><a id="rooms_hroomchr_cancle"  closewindow="closeWindowsrooms_features_listdiv" href="javascript:;" class="button_03 floatR margin-left-10">取消(N)</a> <a id="rooms_hroomchr_confriom" href="javascript:;" class="button_03 floatR">确定(Y)</a></td>
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
		    seajs.use("${pageContext.request.contextPath}/js/rooms/plugins/roomsFeature.js?number=" + Math.random());
        </script>
	</body>
</html>