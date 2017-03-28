<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>X梦想——登录页面</title>
<link href="${pageContext.request.contextPath }/resource/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/resource/css/login.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/resource/js/jquery.min.js" type="text/jscript"></script>
<script src="${pageContext.request.contextPath }/resource/js/form.js" type="text/jscript"></script>
</head>
<body onkeypress="goEnter(event)">
<div class="login-cont">
    <p class="login-logo"><img src="${pageContext.request.contextPath }/resource/image/logo-login.png" width="1028" /></p>
     <div class="login-main">
          <form action="login.do" method="post" id="login">
          	<c:if test="${returnUrl != null }" ><input type="hidden" name="returnUrl" value="${returnUrl}"/></c:if>
			<c:if test="${processUrl != null }" ><input type="hidden" name="processUrl" value="${processUrl}"/></c:if>
             <p class="login-name">
              <span class="foont14-73 marginBottom3" id="error_span" >
              	<c:if test="${errors != null }" >
	              		<c:forEach var="error" items="${errors}">
							${error}<br>
		   				</c:forEach>
	   			</c:if>
	   		</span>
			<input type="text" value="" id="username" name="username" onfocus="clearError();" maxlength="100" placeholder="用户名"/> <span class="foont14-73" id="admin_span" > </span></p>
			<p class="login-pass"><input type="password" name="password" onfocus="clearError();" id="password" onblur="clearSpan(this,'password')" value="" maxlength="32" placeholder="密码" /> <span class="foont14-73" id="password_span"></span></p>
            <c:if test="${errors != null}">
            	<p class="login-pass"><img src="${pageContext.request.contextPath }/captcha.svl" onclick="this.src='${pageContext.request.contextPath }/captcha.svl?d='+new Date()*1" style="cursor: pointer;" /><input type="text" name="captcha" maxlength="4" placeholder="验证码" id="captcha" onblur="clearSpan(this,'captcha')"  /> <span class="foont14-73" id="captcha_span"></span></p>
           	</c:if>
            <div class="login-button"><a href="javascript:void(0);" onclick="goSubmit()">登录</a></div>
          </form>
     </div>
 
</div>
<script type="text/javascript"> 
$(document).ready(function() { 

   var gao = $(window).height();//获取浏览器窗口 可见高度
  $(".login-cont").height(gao) // 设置class为div高度

   	$("#username").blur(function(event) {
   		clearSpan(this,'admin');
   	});


   } 
) 
</script>
<script type="text/javascript">
//判断浏览器是否支持 placeholder属性
function isPlaceholder(){
 var input = document.createElement('input');
 return 'placeholder' in input;
}

if (!isPlaceholder()) {//不支持placeholder 用jquery来完成
 $(document).ready(function() {
     if(!isPlaceholder()){
         $("input").not("input[type='password']").each(//把input绑定事件 排除password框
             function(){
                 if($(this).val()=="" && $(this).attr("placeholder")!=""){
                     $(this).val($(this).attr("placeholder"));
                     $(this).focus(function(){
                         if($(this).val()==$(this).attr("placeholder")) $(this).val("");
                     });
                     $(this).blur(function(){
                         if($(this).val()=="") $(this).val($(this).attr("placeholder"));
                     });
                 }
         });
         //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换
         var pwdField = $("input[type=password]");
         var pwdVal  = pwdField.attr('placeholder');
         pwdField.after('<input id="pwdPlaceholder" type="text" value='+pwdVal+' autocomplete="off" />');
         var pwdPlaceholder = $('#pwdPlaceholder');
         pwdPlaceholder.show();
         pwdField.hide();
         
         pwdPlaceholder.focus(function(){
          pwdPlaceholder.hide();
          pwdField.show();
          pwdField.focus();
         });
         
         pwdField.blur(function(){
          if(pwdField.val() == '') {
           pwdPlaceholder.show();
           pwdField.hide();
          }
         });
         
     }
 });
 
}
function goSubmit(){
	clearError();
	var name=$("#username").val();
	var password=$("#password").val();
	if(name==null||name==""||name=="用户名"){
		$("#admin_span").html("请输入用户名");
		$("#username").focus();
		return false;	
	}
	if(password==null||password==""||password=="密码"){
		$("#password_span").html("请输入密码");	
		$("#password").focus();
		return false;
	}
	
	if( $("#captcha").length > 0){
		var captcha=$("#captcha").val();
		if(captcha==null||captcha==""||captcha=="验证码"){
			$("#captcha_span").html("请输入验证码");	
			$("#captcha").focus();
			return false;
		}
	}
	
	$("#login").submit();
}
function clearSpan(obj,id){
	if(obj.value!=null&&obj.value!==""&&obj.value!="用户名"){
		$("#"+id+"_span").html("");	
	}else{
		
	 	if(id==="admin"){
			$("#admin_span").html("请输入用户名");
		}else if(id==="password"){
			$("#password_span").html("请输入密码");	
		}else if(id==="captcha"){
			$("#captcha_span").html("请输入验证码");	
		}
	}
}
function clearError(){
	$("#error_span").html("");
}
function goEnter(event){
	if(event.keyCode==13){
		goSubmit()
	}
}
</script>
</body>
</html>