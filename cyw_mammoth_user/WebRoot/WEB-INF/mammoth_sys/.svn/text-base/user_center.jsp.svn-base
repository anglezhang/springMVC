<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>

<body>

<%@ include file="header.jsp" %>
	<!--secondMenu-->
<div class="secondMenuDiv">
	<ul class="secondMenu">
        <li><a href="javascript:;" class="thisSecMenu">用户中心</a></li>
    </ul>
</div>
<!--secondMenu -end- -->
<!--banner & menu -END -->
<!--center xingli 2015-09-08-->
<div class="center">
    	<!--mainInformation-->
    	<div class="mainInfo loginInfo">
      	<h4 class="fontWeight green margin-top-10">登录信息</h4>
        <div class="usrInfo">
          <img src="../img/user_03.png" class="usrImg">
          <div class="usrName"><shiro:principal/></span></div>
          <div class="usrPos"><mammoth:UserInfo propertyName="post" /></div>
        </div>
        <div class="margin-top-5 tableDiv btmN">
        	<!--table-->
            <table class="tableMain tabChangBg">
              <thead> 
                <tr>
                    <td width="15%">序号</td>
                    <td width="14%">时间</td>
                    <td width="15%">动作</td>
                    <td width="15%">使用设备</td>
                    <td width="15%">IP</td>
                </tr>
              </thead>
                <tbody>
                    <tr>
                        <td width="15%">001</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="15%">002</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%"></td>
                    </tr>
                    <tr>
                        <td width="15%">003</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="15%">004</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="15%">005</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%">&nbsp;</td>
                        <td width="15%"></td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
      </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<div class="rightMenu">
        	<h6><img class="margin-right-5" src="../img/point-down.png">信息管理</h6>
          <ul class="rightNav">
            <li><a class="rightNavPoint" href="#"><img class="rightNavImg" src="../img/rightNavImg.png">登录信息</a></li>
            <li><a href="#"><img class="rightNavImg" src="../img/rightNavImg.png">修改密码</a></li>
            <li><a href="#"><img class="rightNavImg" src="../img/rightNavImg.png">消息与提醒</a></li>
            <li><a href="#"><img class="rightNavImg" src="../img/rightNavImg.png">操作手册</a></li>
            <li><a href="#"><img class="rightNavImg" src="../img/rightNavImg.png">技术支持</a></li>
            <li><a href="#"><img class="rightNavImg" src="../img/rightNavImg.png">村游论坛</a></li>
            <li><a href="#"><img class="rightNavImg" src="../img/rightNavImg.png">关于村游</a></li>
            <li><a href="#"><img class="rightNavImg" src="../img/rightNavImg.png">检查更新</a></li>
            <li><a href="javascript:logout();"><img class="rightNavImg" src="../img/rightNavImg.png">退出</a></li>
          </ul>
            
        </div>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
</div>
<!--center -END -->
<%@ include file="footer.jsp" %>
<!--弹出层阴影-->
<div class="alertDivBg" style="display:none;"></div>
<!--弹出层阴影结束-->
<!--修改密码弹出-->
<div class="alertDiv updatePwdBox moveBar" style="top:152px;">
	<div class="alertMain" style="width:400px;">
      <h4 class="moveDivAlert">修改密码<a id="closeDiva" href="javascript:;" class="closeDiv"></a></h4>
      <!--弹出层内容-->
      <div class="alertCenter updatePwd">
        <ul>
          <li>
            <span class="tar">原密码</span>
            <input type="text"  name="oldPwd" class="input" maxlength="10" />
          </li>
          <li>
            <span class="tar">新密码</span>
            <input type="text" name="newPwd" class="input" maxlength="10" />
          </li>
          <li>
            <span class="tar">重新输入</span>
            <input type="text" name="reNewPwd" class="input" maxlength="10" />
          </li>
        </ul> 
        <p class="note">密码由6-10位数字、字母或符号组成，不包含空格。修改密码后，您需要重新登录系统。</p>
        <div class="alertRight clearBoth margin-top-20">
            <a class="buttonLikeA" href="javascript:changePwd();">确定(Y)</a>
            <a class="buttonLikeA closeAlertBtn" href="javascript:closeChangePwdAlert();">放弃(N)</a>
         </div>
         <div class="clearBoth"></div>
      </div>
      <!--/弹出层内容-->
    </div>
</div>
<!--/修改密码弹出-->
<!--修改密码成功弹出-->
<div class="alertDiv updatePwdOkBox moveBar" style="top:152px;">
  <div class="alertMain" style="width:400px;">
      <h4 class="moveDivAlert">消息提示<a href="javascript:;" class="closeDiv"></a></h4>
      <!--弹出层内容-->
      <div class="alertCenter updatePwdOk">
		<p>密码修改成功，请重新登录！</p>
        <div class="alertRight clearBoth">
            <span class="buttonLikeA">确定</span>
        </div>
      </div>
      <!--/弹出层内容-->
    </div>  
</div>
<!--/修改密码成功弹出-->
<script type="text/javascript" src="/js/userCenter/userCenterIndex.js"></script>
<script type="text/javascript" src="/js/reservation/Utilities.js"></script>
</body>
<script type="text/javascript">
	$(function(){
		/*遮罩层DIV高度*/
		$(".alertDivBg").css("height",$(document).height());     
        $(".alertDivBg").css("width",$(document).width());  
		/*遮罩层DIV高度*/
		
		/*右侧菜单点击*/
		$(".rightNav li a").click(function(){
			$(".rightNav li a").removeClass("rightNavPoint");
			$(this).addClass("rightNavPoint")
			//$(this).parent().parent().remove();
		});
		/*右侧菜单点击 结束*/
			
		$(".closeDiv").click(function(){
			$(".alertDivBg").fadeOut();
			$(this).parents(".alertDiv").fadeOut();
			if($(this).parents().hasClass("updatePwdOkBox")){
				window.location.href="/logout";
			}
		});
		//建筑定义，新增END

	    $("li:contains('修改密码')").click(function(){
	      $(".updatePwdBox, .alertDivBg").fadeIn();
	    });	
	});	
</script>
</html>
