<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理</title>
<script type="text/javascript">
var basePath=window.location.pathname;
// 房间管理定义
var roomDefineArr = ['/building/list.do' , '/hroomType/list.do' , '/hroomCharacters/list.do' , '/hroomDefine/list.do' , '/holidays/list.do' , '/clockRoom/list.do' , '/hroomPlan/list.do'] ;
var permissionArr = ['/hfunction/list.do' , '/workGroup/list.do' , '/operator/list.do'] ;
var currPath=basePath.substr(basePath.lastIndexOf("/")+1);
$(function(){
	$(".secondMenu a").removeClass("thisSecMenu");
	$(".secondMenu a").each(function(){
	   if($(this).attr("href").indexOf(basePath)!=-1){
	     $(this).addClass("thisSecMenu");
	     return false;
	   }else if(roomDefineArr.indexOf(basePath) !=-1&& $(this).prop("id") =="seconde_menu_a_room"){
	     $(this).addClass("thisSecMenu");
	     return false;
	   }else if(permissionArr.indexOf(basePath) !=-1 && $(this).prop("id") =="seconde_menu_a_permission"){
	     $(this).addClass("thisSecMenu");
	     return false;
	   }else{
	      $(this).removeClass("thisSecMenu");
	   }
	});
})

</script>
</head>
  
  <body>
    <div class="secondMenuDiv">
		<ul class="secondMenu">
	        <li><a id="seconde_menu_a_room" href="${ctx}/hroomPlan/list.do">房间管理</a></li>
	        <li><a id="seconde_menu_a_codeCare" href="${ctx}/codeCare/list.do">代码维护</a></li>
	        <li><a href="javascript:;" <c:if test="${firstHoverType == 3}">class="thisSecMenu"</c:if> >报表管理</a></li>
	        <li><a href="javascript:;" <c:if test="${firstHoverType == 5}">class="thisSecMenu"</c:if> >系统参数</a></li>
            <li><a id="seconde_menu_a_permission" href="${ctx}/operator/list.do" >用户及权限</a></li>
	        <li><a href="javascript:;" <c:if test="${firstHoverType == 6}">class="thisSecMenu"</c:if> >数据安全</a></li>
	        <!-- <li><a href="javascript:;"  onclick="logout()" >退出</a></li>-->
	    </ul>
	</div>
  </body>
</html>
