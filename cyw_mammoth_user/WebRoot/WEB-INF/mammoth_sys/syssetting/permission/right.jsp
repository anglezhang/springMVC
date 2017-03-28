<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设置-用户及权限</title>
</head>
<body>
	<div class="rightMenu">
		<h6><img class="margin-right-5" src="/img/point-down.png">用户及权限</h6>
		<ul class="rightNav">
	   		<li>
	   			<a href="${ctx}/hfunction/list.do" 
	   				<c:if test="${hoverType==1 }">class="rightNavPoint"</c:if> > 
	   				<img class="rightNavImg " src="${ctx}/img/rightNavImg.png">功能设置
	   			</a>
	   		</li>
	     	<li>
	     		<a href="${ctx}/workGroup/list.do" 
	     			<c:if test="${hoverType==2 }">class="rightNavPoint"</c:if>>
	     			<img class="rightNavImg" src="${ctx}/img/rightNavImg.png">工作组设置
	     		</a>
	     	</li>
	     	<li></li>
	     	<li>
	     		<a href="${ctx}/operator/list.do" 
	     			<c:if test="${hoverType==3 }">class="rightNavPoint"</c:if>>
	     			<img class="rightNavImg" src="${ctx}/img/rightNavImg.png">用户管理
	     		</a>
	     	</li>
	 	</ul>
	</div>
</body>
<script type="text/javascript">
</script>
</html>
