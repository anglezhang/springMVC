<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>4s店登录页面</title>
<link href="${pageContext.request.contextPath }/resource/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/resource/css/content.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/resource/js/jquery-1.11.1.min.js" type="text/javascript" ></script>
</head>
<body class="logBj logBj_1" onkeypress="goEnter(event)">
<form action="${pageContext.request.contextPath }/index4s.html" method="post" id="login">
	<div class="logCon">
    	<div class="logCon_bj"></div>
        <div class="logCon_inp">
       	  	<p class="logCon_title"><img src="${pageContext.request.contextPath }/resource/image/ico-2.png" /></p>
       	  	<p class="logFbt">管理系统</p>
            <table border="0" cellpadding="0" cellspacing="0" class="logTabale">
                <tr>
                	<td colspan="3" height="20" class="logTis">
                		<c:if test="${errors != null }" >
	              		<c:forEach var="error" items="${errors}">
							${error}<br>
		   				</c:forEach>
	   				</c:if>
                	</td>
                </tr>
                <tr>
                    <td colspan="2">
                     <div class="select form-cel-1">
                            <p class="f-cel-top"><span class="fkcs">嘉悦4s店</span></p>
                            <div class="x-ul">
                                <div class="x-ul-con">
                                    <c:forEach items="${addrList }" var="addr">
			                    		 <p>${addr.address }</p>
			                    	</c:forEach> 
                                </div>
                                <select class="down-payment" name="addrId">
                                   <c:forEach items="${addrList }" var="addr">
			                    		<option value="${addr.addrId }">${addr.address }</option>
			                    	</c:forEach>    
                                </select>
                            </div>
                        </div>
                    </td>
                    <td width="177" class="logTis" id="admin_span"></td>
                </tr>
                <tr>
                	<td colspan="3" height="20"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="password" name="password" onfocus="clearError();" id="password" onblur="clearSpan(this,'password')" maxlength="32"  class="tab_pass" placeholder="密码" /></td>
                    <td class="logTis" id="password_span"></td>
                </tr>
                <tr>
                	<td colspan="3" height="20"></td>
                </tr>
                <c:if test="${errors != null}">
                <tr>
                    <td>
                    <input type="text"  class="tab_yz" name="captcha" maxlength="4" placeholder="请输入验证码" id="captcha" onblur="clearSpan(this,'captcha')"  />
                    </td>
                    <td align="right"><img src="${pageContext.request.contextPath }/captcha.svl" onclick="this.src='${pageContext.request.contextPath }/captcha.svl?d='+new Date()*1" style="cursor: pointer;" /></td>
                    <td class="logTis" id="captcha_span"></td>
                </tr>
                </c:if>
                <tr>
                	<td colspan="3" height="30"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="button" onclick="goSubmit()" value="登录" class="logBtn"  /></td>
                    <td></td>
                </tr>
            </table>
      </div>
    </div>
    </form>
 </body>
<script type="text/javascript"> 
$(document).ready(function() { 

   var gao = $(window).height();//获取浏览器窗口 可见高度
  $(".login-cont").height(gao) // 设置class为div高度

   	$("#addrId").blur(function(event) {
   		clearSpan(this,'admin');
   	});


   } 
) 
var winHeight = $(window).height();
	var marginTop = (winHeight-510)/2;
	var logHeight = winHeight-marginTop;
	$(".logBj").height(logHeight);
	$(".logCon").css("margin-top",marginTop+'px');
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
         pwdField.after('<input id="pwdPlaceholder" class="tab_pass" type="text" value='+pwdVal+' autocomplete="off" />');
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
	//var name=$("#username").val();
	var password=$("#password").val();
	/* if(name==null||name==""||name=="用户名"){
		$("#admin_span").html("请输入用户名");
		$("#username").focus();
		return false;	
	} */
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

//////////////// select 下拉菜单////////////////////
	$(".select").click(function(){
		$(this).find(".x-ul").fadeToggle(150);
	})
	$(".select").mouseleave(function(){
		$(this).find(".x-ul").fadeOut();
	})
    $("body").on("click",".x-ul .x-ul-con p",function(){
        click_selectli($(this));
    });
	
	function click_selectli(thisobj)
	{
		
        thisobj.parents(".select").find("select option").attr("selected",false);
		var index=thisobj.index();
		thisobj.parents(".select").find("select option:eq("+index+")").attr("selected",true);
		thisobj.parents(".select").find("span").html(thisobj.html())
		thisobj.parents(".select").find("select").change();
	}
</script>
</html>