<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>村游管理系统-登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
</head>

<body class="login">
	<div class="loginInputDiv">
    	<div class="login_cont">
        	<a class="login_close floatR margin-right-30" href="javascript:;"><img src="${pageContext.request.contextPath}/img/close_small.png"></a>
        	<a class="login_close floatR margin-right-20" href="javascript:;"><img src="${pageContext.request.contextPath}/img/close_small_02.png"></a>            
        </div>
        <h1 class="loginTitleChinese">村游管理系统<span>|</span>客房管理</h1>
        <h2 class="loginTitleEnglish">CunYou Hotel Management System</h2>
        <form id="loginForm" action="${pageContext.request.contextPath }/ajaxLogin" method="post">
        <div class="inputBar">
        	<div id="errorTips" class="errorTips"></div>
            <div class="userInput borderColorHui">
            	<div class="login_userImg huiImg"><img src="${pageContext.request.contextPath}/img/login_user.png"></div>
            	<input name="username" type="text" id="operName" value="" autocomplete="off">
            </div>
            <div class="userInput borderColorHui margin-top-10">
            	<div class="login_userImg huiImg"><img src="${pageContext.request.contextPath}/img/login_password.png"></div>
            	<input class="password" name="password" id="password" type="password" value="" autocomplete="off">
            	<input type="hidden" name="captcha" value="0000">
            	<input type="hidden" name="rememberMe" value="1">
            </div>
            <div class="RememberPassword margin-top-5" >
            	<label><label>
            </div>
            <a  href="javascript:;" class="loginButton margin-top-10" onclick="goLogin()">登录</a>
        </div>
        </form>
        <div class="login_copyright">
        	<span class="companyDetails">村游管理系统<span class="margin-left-10 margin-right-10">本软件授权</span>[新印象太河湾度假村]<span class="margin-left-10">为合法最终用户</span></span>
            <span class="companyDetails">Copyright © 2010-2015 www.cywsx.com All Rights Reserved ICP备陕B2-20100023</span>
        </div>
        	
    </div>
</body>
<script type="text/javascript">
$(function(){
	$(".userInput input").focus(function(){
		$(".userInput input").parent('.userInput').removeClass("borderColorLv");
		$(".userInput input").parent('.userInput').addClass("borderColorHui");
		$(this).parent('.userInput').removeClass("borderColorHui");
		$(this).parent('.userInput').addClass("borderColorLv");
		$(".login_userImg").removeClass("lvImg");
		$(".login_userImg").addClass("huiImg");
		$(this).prev('.login_userImg').removeClass("huiImg");
		$(this).prev('.login_userImg').addClass("lvImg");
	});
	var inputMarLeft = $(".userInput").css('marginLeft');
	$(".errorTips").css("marginLeft",inputMarLeft);
});

	//
	var options = {  
	   //target: '#output',          //把服务器返回的内容放入id为output的元素中      
	   beforeSubmit: showRequest,  //提交前的回调函数  
	   success: showResponse,      //提交后的回调函数  
	   //url: 'ajaxLogin',            //默认是form的action， 如果申明，则会覆盖  
	   type: 'post',              //默认是form的method（get or post），如果申明，则会覆盖  
	   dataType: 'json',           //html(默认), xml, script, json...接受服务端返回的类型  
	   //clearForm: true,          //成功提交后，清除所有表单元素的值  
	   //resetForm: true,          //成功提交后，重置所有表单元素的值  
	   timeout: 3000               //限制请求的时间，当请求大于3秒后，跳出请求  
	}  
	function showRequest(formData, jqForm, options){  
	   //formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]  
	   //jqForm:   jQuery对象，封装了表单的元素     
	   //options:  options对象  
	   var queryString = $.param(formData);   //name=1&address=2  
	   var formElement = jqForm[0];              //将jqForm转换为DOM对象  
	   var operName = formElement.operName.value;  //访问jqForm的DOM元素  
	   var password = formElement.password.value; 
	   if(operName==""){
			$("#errorTips").html("用户名不能为空！");
			 $("#errorTips").show();
			$("#operName").focus();
			return false;
		}
		if(password==""){
			$("#errorTips").html("密码不能为空！");
			 $("#errorTips").show();
			$("#password").focus();
			return false;
		}
	   return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证  
   };  
  
   function showResponse(responseText, statusText){  
	   //dataType=json  
	   console.log(responseText);
       if(responseText.success){//跳转到主页面
         $("#errorTips").html("");
          window.location.href="main";
       }else{//提示错误信息
         $("#errorTips").text(responseText.msg);
         $("#errorTips").show();
       }
	}; 

//登录
function goLogin(){
 //$("#loginForm")[0].submit();
 $("#loginForm").ajaxSubmit(options);  

}
//页面回车事件
document.onkeydown=function(event){
      var e = event || window.event || arguments.callee.caller.arguments[0];
       if(e && e.keyCode==13){ // enter 键
         $("#operName").focus();
         goLogin();
      }
  };
</script>
</html>
