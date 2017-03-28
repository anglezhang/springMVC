<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="include/taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<title>房间管理</title>
<style type="text/css">
.menu .nav .bdL{ height:70px;}
.menu .nav li > a{ height:90px;}
.msgBox .tabTit{ height:31px;}
.msgBox .tabSel{ height:34px;}
.msgBox .msg,.updateSysBox .updateSys{ width:96%;}
.msgBox .tabCon{ width:100%; padding-top:0;}
.serviceBox .service{ width:95%;}
</style>
</head>
<body>
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="now_ymd" scope="page"/>
    <div class="menu">
		<a href="${ctx}/">
			<div class="logo">
		 		<h1 class="padding-top-20 fontWeight" style="margin-top: 4px;">${systemTitle}<span class="margin-left-10">客房管理</span></h1>
		     	<h6 class="padding-top-none fontWeight" style="margin-top: 5px;">Village tourism management system</h6>
		 	</div>
		</a>
		<ul class="nav">
		  	<li><a href="${ctx}/rooms.do"><img src="${ctx}/img/nav-01.png"><span class="displayB">房态</span></a></li>
			<li><a href="${ctx}/bookroom/booklist.do"><img src="${ctx}/img/nav-02.png"><span class="displayB">预定</span></a></li>
			<li><a href="${ctx}/guest/gstIndex.do"><img src="${ctx}/img/nav-03.png"><span class="displayB">住客</span></a></li>
			<li><a href="${ctx}/noguest/noguestIndex.do"><img src="${ctx}/img/nav-04.png"><span class="displayB">非住客</span></a></li>
			<li><a href="${ctx}/marketing/tadoclist.do"><img src="${ctx}/img/nav-05.png"><span class="displayB">营销</span></a></li>
			<li><a href="${ctx}/search/searchindex.do"><img src="${ctx}/img/nav-06.png"><span class="displayB">查询</span></a></li>
			<li><a href="${ctx}/reportdoc/list.do"><img src="${ctx}/img/nav-07.png"><span class="displayB">报表</span></a></li>
			<li><a href="javascript:;"><img src="${ctx}/img/nav-08.png"><span class="displayB">夜审</span></a></li>
			<li><a href="${ctx}/setting.do"><img src="${ctx}/img/nav-09.png"><span class="displayB">设置</span></a></li>
			<li class="usrCenter">
				<div class="bdL">
					<span class="displayB"><img src="${ctx}/img/user_02.png"> <shiro:principal/></span><span class="displayB">前台值班经理</span>
				</div>
				<div class="subNav">
					<a href="/userCenter.do">我的账号</a>
					<span>消息与提醒</span>
					<span>操作手册</span>
					<span>技术支持</span>
					<span>村游论坛</span>
					<span>关于村游</span>
					<span>检查更新</span>
					<span onClick="javascript:logout();">退&emsp;&emsp;出</span>
				</div>
			</li>
		</ul>
	</div>
	    
    <!--消息提醒弹出-->
<div class="alertDiv msgBox moveBar" style="top:100px; z-index:11;">
  <div class="alertMain" style="width:600px;">
      <h4 class="moveDivAlert">消息与提醒<a href="javascript:;" class="closeDiv"></a></h4>
      <!--弹出层内容-->
      <div class="alertCenter msg">
        <span class="tabTit tabSel">业务消息</span>
        <span class="tabTit">系统消息<span class="msgNum">10</span></span>
        <div class="clearBoth"></div>
        <ul class="tabCon" style="display:block;">
          <li>
            <div class="msgType">催费</div>
            房间:8302,住客:黄药师,已欠费 265.00元，请及时催费。
          </li>
          <li>
            <div class="msgType">预抵</div>
            预订团队：村游团1，抵达时间为今日16:00，逾期未达，请及时联系
          </li>
          <li>
            <div class="msgType">预离</div>
            房间:8305,今日12:00离店，未办理手续，请核实
          </li>
          <li>
            <div class="msgType">夜审</div>
            已到01:00夜审时间，请注意按时执行夜审。
          </li>
          <li>
            <div class="msgType">订单审核</div>
            订单号：252658791，预订 2015-08-30至2015-09-02 房间5间，请及时审核。
          </li>
          <li>
            <div class="msgType">催费</div>
            房间:8302,住客:黄药师,已欠费 265.00元，请及时催费。
          </li>
        </ul> 
        <ul class="tabCon">
          <li>
            <div class="msgType">版本更新</div>
            已有新版本V98651.15.1209，请及时更新
          </li>
        </ul> 
      </div>
      <!--/弹出层内容-->
    </div>  
</div>
<!--/消息提醒弹出-->

<!--技术支持弹出-->
<div class="alertDiv serviceBox moveBar" style="top:152px; z-index:11;">
  <div class="alertMain" style="width:400px;">
      <h4 class="moveDivAlert">技术支持<a href="javascript:;" class="closeDiv"></a></h4>
      <!--弹出层内容-->
      <div class="alertCenter service">
        <ul>
          <li>
            <span class="fixW">您的专属客服：</span>吴定国
          </li>
          <li>
            <span class="fixW">电话：</span>18692654871
          </li>
          <li>
            <span class="fixW">QQ：</span>2880078752
          </li>
          <li class="margin-top-20">
            <span class="fixW">全国免费热线：</span>400-123-1234
          </li>
          <li>
            <span class="fixW">客服QQ：</span>2880078701
          </li>
          <li>
            <span class="fixW">网址：</span>cyw.so
          </li>
        </ul>
        
      </div>
      <!--/弹出层内容-->
    </div>  
</div>
<!--/技术支持弹出-->

<!--检查更新弹出-->
<div class="alertDiv updateSysBox moveBar" style="top:152px; z-index:11;">
  <div class="alertMain" style="width:500px;">
      <h4 class="moveDivAlert">关于本系统<a href="javascript:;" class="closeDiv"></a></h4>
      <!--弹出层内容-->
      <div class="alertCenter updateSys">
        <div class="logo">
          <img src="../img/logo.png">
          <div class="floatL">
            <h1 class="fontWeight">
              村游管理系统<span class="margin-left-10">客房管理</span>
            </h1>
            <h6 class="padding-top-none fontWeight">Village tourism management system</h6>
          </div>
        </div>
        <ul>
          <li>
            <span class="fixW">当前版本：</span>V98256.15.1202
          </li>
          <li>
            <span class="fixW">产品ID：</span>NG4HW - VH26C - 733KW - K6F98 - J8CK4
          </li>
        </ul>
        <ul class="copyR"> 
          <li>
            本软件授权 [<a href="#">新印象太河湾度假村</a>] 为合法最终用户 
          </li> 
          <li>
            <input type="checkbox" name="autoUpdate" checked="checked"> 自动安装新版本
          </li>
          <li class="font">
            Copyright © 2015 Cunyou. All Rights Reserved
          </li>

        </ul>
        <div class="alertRight clearBoth margin-top-20">
            <span class="buttonLikeA">检查更新</span>
            <span class="buttonLikeA">关闭</span>
         </div>
      </div>
      <!--/弹出层内容-->
    </div>  
</div>
<!--/检查更新弹出-->
<div class="alertDivBg" style="display:none;"></div>    
	
</body>
<script type="text/javascript">
	$(function(){
		document.body.oncontextmenu = function (){
			    return false;
		};
		//控制回退事件 onselectstart="return false"
		// window.onbeforeunload = function(){ return '将丢失未保存的数据!'; } 
		//添加双击后 会选中 并且显示蓝色的问题
		$("body").attr("onselectstart","return false");
		
		
		/*
		*描述：下拉导航点击显示弹层
		*@param：text 点击相关内容
		*/
		$(".usrCenter .subNav span, .rightNav li a").click(function(){
			var text = $(this).text();
			switch(text){
				case "修改密码":
					$(".updatePwdBox, .alertDivBg").fadeIn();
					break;
				case "消息与提醒":
					$(".msgBox, .alertDivBg").fadeIn();
					break;
				case "技术支持":
					$(".serviceBox, .alertDivBg").fadeIn();
					break;
				case "检查更新":
					$(".updateSysBox, .alertDivBg").fadeIn();
					break;
				default :
					break;	
			}
		});

	
	    $(".msgBox .tabTit").click(function(){
	      var index = $(".msgBox .tabTit").index($(this));
	      $(this).addClass("tabSel").siblings(".tabTit").removeClass("tabSel");
	      $($(".msgBox .tabCon").get(index)).show().siblings(".tabCon").hide();
	    });
	    
	    $(".closeDiv").click(function(){
			$(".alertDivBg").fadeOut();
			$(this).parents(".alertDiv").fadeOut();
		});
		
	});
	
	 function logout(){
		altInfMsg('是否确认退出系统？',function(){
			window.location.href="/logout"; 
		},function(){});
	}
</script>
</html>
