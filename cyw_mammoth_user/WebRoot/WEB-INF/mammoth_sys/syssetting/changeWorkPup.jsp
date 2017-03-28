<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设置-用户及权限-功能设置</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cywManage-css.css">
</head>
<body style="margin-left: 0px">
<!--换班次-->
<div class="borderSolid changeWorkPup" style="margin: 0 auto">
                <ul class="c2">
                    <li><span class="inb">当前操作员：</span> ${operName}</li>
                    <li><span class="inb">当前班次：</span> ${currBanciName}</li>  
                    <li><span class="inb">切换班次为：</span>
                        <select id="shiftId" name="shiftId" class="select" style="width: 145px">
                            <c:forEach items="${banciList}" var="list" varStatus="status">
                                <option value="${list.codeId}" <c:if test="list.codeId==shiftLogId"> selected</c:if>>${list.codeNamec}</option>
                            </c:forEach>
                        </select>    
                    </li>     
                </ul>
                <br>
                <div class="alertRight">
                   <a class="buttonLikeA margin-right-5" href="javascript:;" onclick="changeWorkPup()">是(Y)</a>
                   <a class="buttonLikeA margin-right-5" href="javascript:;" onclick="parent.closeChangeWorkPup()">否(N)</a>
                </div>
            <div class="clearBoth"></div>
</div>
<!--/换班次-->
<script type="text/javascript">
function changeWorkPup(shiftId){
  parent.changeWorkPup(document.getElementById("shiftId").value);
}
</script>
</body>
</html>
