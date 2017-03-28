<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="include/taglib.jsp" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cywManage-css.css">

<script type="text/javascript" src="${ctx}/scripts/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-form.js"></script>
<script src="${ctx}/js/calendar/WdatePicker.js"></script> 
<script src="${ctx}/js/common.js"></script> 
<!-- styles 
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />-->
<link href="${ctx}/scripts/wijmo.min.css" rel="stylesheet" />
<!-- jQuery and Bootstrap -->
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- Wijmo -->
<script src="${ctx}/scripts/wijmo.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/wijmo.grid.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/wijmo.input.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/customMergeManager.js" type="text/javascript"></script>
<!-- JQuery plugin -->
<script src="${ctx}/scripts/pinyin.js" type="text/javascript"></script>	
<script src="${ctx}/scripts/json2.js" type="text/javascript"></script>	
<script type="text/javascript" src="${ctx}/altDialog/altDialog.js"></script>
<script src="${ctx}/scripts/jquery.inputmask.min.js" type="text/javascript"></script>
</head>
<body>
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="now_ymd" scope="page"/>
	<input type="hidden" id="path" value="${pageContext.request.contextPath}">
    <div class="menu">
		<a href="${ctx}/">
			<div class="logo">
		 		<h1 class="padding-top-20 fontWeight">${systemTitle}<span class="margin-left-10">客房管理</span></h1>
		     	<h6 class="padding-top-none fontWeight">Village tourism management system</h6>
		 	</div>
		</a>
		<ul class="nav">
		  	<li><a  <mammoth:AuthJudge funcId="c_408466">href="${ctx}/rooms.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-01.png"><span class="displayB">房态</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_131293">href="${ctx}/bookroom/booklist.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-02.png"><span class="displayB">预定</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_182497" >href="${ctx}/guest/gstIndex.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-03.png"><span class="displayB">住客</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_479169">href="${ctx}/noguest/noguestIndex.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-04.png"><span class="displayB">非住客</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_359934">href="${ctx}/marketing/tadoclist.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-05.png"><span class="displayB">营销</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_403098" >href="${ctx}/search/searchindex.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-06.png"><span class="displayB">查询</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_194467">href="${ctx}/reportdoc/list.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-07.png"><span class="displayB">报表</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_157218">href="${ctx}/dayaudit/index.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-08.png"><span class="displayB">夜审</span></a></li>
			<li><a  <mammoth:AuthJudge funcId="c_500756">href="${ctx}/setting.do"</mammoth:AuthJudge> ><img src="${ctx}/img/nav-09.png"><span class="displayB">设置</span></a></li>
			<li class="usrCenter">
				<div class="bdL">
					<span class="displayB"><img src="${ctx}/img/user_02.png"> <shiro:principal/></span><span class="displayB"><mammoth:UserInfo propertyName="post" /></span>
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
	
    <!--退房弹出框-->
<!-- 	<div class="alertDiv moveBar alertDiv2 query displayNone" id="searchAlert1"> -->
<!-- 		<div class="alertMain greyBg alertMain2" style="width:72%; margin-top:150px; height: 600px;" id="searchAlert1"> -->
	<div class="alertDiv moveBar alertDiv2 query displayNone" style="width:72%; margin-top:150px;" id="searchAlert1">
		<div class="alertMain greyBg alertMain2" style="width:100%; margin-top:0; height: 600px;">
    		<h4 class="moveDivAlert">查询<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
            <div class="alertCenter">
            	<h1 class="textAlignCenter">当前可售房</h1>
                <h5 class="textAlignCenter"><span id="datestr"></span><span class="margin-left-20" id="timestr"></span></h5>
                <div class="tableDiv widthB98 marginLRAuto margin-top-10" style=" height:460px;">
                    <!--table title-->
                    <table class="tableMain">
                        <thead> 
                            <tr>
                                <td width="15%">房态</td>
                                <td width="25%">房类</td>
                                <td width="10%">楼层</td>
                                <td>房号</td>
                            </tr>
                        </thead>
                    </table>
                    <!--table title end -->
                    <!--table enner-->
                    <div class="tableHeiScll" style="height:420px;">
                        <table class="tableMain tabChangBg">
                            <tbody id="dataMain1">
                                
                            </tbody>
                        </table>
                    </div>
                    <!--table enner -END- -->
                </div>
                <!--table -END- -->
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
    <!--房间存量表弹出框-->
<!-- 	<div class="alertDiv moveBar alertDiv2 query displayNone" id="searchAlert2"> -->
<!-- 		<div class="alertMain greyBg alertMain2" style="width:72%; margin-top:150px; height: 600px;"> -->
	<div class="alertDiv moveBar alertDiv2 query displayNone" style="width:72%; margin-top:150px;" id="searchAlert2">
		<div class="alertMain greyBg alertMain2" style="width:100%; margin-top:0;">
    		<h4 class="moveDivAlert">查询<a href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        	<div class="borderSolid">
            	<h1 class="textAlignCenter">房间存量表</h1>
                <table width="300" class="tableMain marginLRAuto margin-top-15">
                    <tr>
                        <td width="27%" align="right"><span class="margin-right-5">起始时间</span></td>
                        <td><input class="input ui-widget-content ui-corner-all Wdate" onClick="WdatePicker();" id="startDate" name="startDate" type="text" value="" readonly="readonly"></td>
                    </tr>
                </table>
                <div class="tableDiv widthB98 marginLRAuto margin-top-20 pr" style="border-bottom:0;">
	                <!--table title-->
	                <div style="margin-right:16px;">
		                <table class="tableMain textAlignCenter floatL">
		                    <thead id="dataHead2"> 
		                        <tr>
		                            <td>房间类型</td>
		                            <td width="6%">周一<br>2</td>
		                            <td width="6%">周二<br>2</td>
		                            <td width="6%">周三<br>3</td>
		                            <td width="6%">周四<br>4</td>
		                            <td width="6%">周五<br>5</td>
		                            <td width="6%">周六<br>6</td>
		                            <td width="6%">周日<br>7</td>
		                            <td width="6%">周一<br>8</td>
		                            <td width="6%">周二<br>9</td>
		                            <td width="6%">周三<br>10</td>
		                            <td width="6%">周四<br>11</td>
		                            <td width="6%">周五<br>12</td>
		                            <td width="6%">周六<br>13</td>
		                            <td width="6%">周日<br>14</td>
		                        </tr>
		                    </thead>
		                </table>
	                </div>
	                <div class="hideScroll" style="height:42px;"></div>
	              </div>  
	                <!--table title end -->
	                <!--table enner-->
	               <div class="tableDiv widthB98 marginLRAuto" style="overflow-y:scroll; height:425px; border-top:0;">
	                <table class="tableMain tabChangBg">
	                    <tbody id="dataMain2">
	                        
	                    </tbody>
	                </table>
	                <div class="clearBoth"></div>
	                <!--table enner -END- -->
                    </div>
                    <!--table -END- -->
                </div>
            <div class="clearBoth"></div>
        </div>
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
	     //startServer();屏蔽启动websorcket server，请不要删除此行
		 document.body.oncontextmenu = function (){
			    return false;
		}; 
		//控制回退事件 onselectstart="return false"
		/*window.onbeforeunload = function()
		{
			 altInfMsg("离开此页将丢失未保存数据，是否离开"
		 	,function(){}
		 	,function(){});
			 return false;
		} */
		window.onbeforeunload = true;
		//添加双击后 会选中 并且显示蓝色的问题
		$("body").attr("onselectstart","return false");
		
		//关闭所有div
		function closeAllDiv(){
			$("#searchAlert1").fadeOut();
			$("#searchAlert2").fadeOut();
		}
		
		//打开“当前可售房”查询div
		function openAlertDiv1(){
			$("#searchAlert1").fadeIn();
			$.ajax({
				url : "${ctx}/search/getcurravailableforsalerooms",
				type : "post",
				dataType : 'json',
				data : {
				},
				success : function(result) {
					$("#datestr").text(result.datestr);
					$("#timestr").text(result.timestr);
					var data = result.datalist;
					if(data.length < 0){
						altWaringMsg("当前没有可售房源。");
						return;
					}else{
						$("#dataMain1").empty();
						var html = "";
						for(var i = 0; i < data.length; i++){
							if(data[i].stat_row_num == "0"){
								if(data[i].type_row_num == "0"){
									html = "<tr><td>"+data[i].floor_no+"</td><td style=\"text-align:left;\">"+data[i].room_ids+"</td></tr>";
								}else if(data[i].type_row_num == "1"){
									html = "<tr><td style=\"text-align:left;\">"+data[i].room_type+"</td><td>"+data[i].floor_no+"</td><td style=\"text-align:left;\">"+data[i].room_ids+"</td></tr>";
								}else{
									html = "<tr><td rowspan=\""+data[i].type_row_num+"\" style=\"text-align:left;\">"+data[i].room_type+"</td><td>"+data[i].floor_no+"</td><td style=\"text-align:left;\">"+data[i].room_ids+"</td></tr>";
								}
							}else if(data[i].stat_row_num == "1"){
								html = "<tr><td width=\"15%\" >"+data[i].curr_stat+":<br>"+data[i].curr_stat_text+"</td><td width=\"25%\" style=\"text-align:left;\">"+data[i].room_type+"</td><td width=\"10%\">"+data[i].floor_no+"</td><td style=\"text-align:left;\">"+data[i].room_ids+"</td></tr>";
							}else{
								if(data[i].type_row_num == "0" || data[i].type_row_num == "1"){
									html = "<tr><td width=\"15%\" rowspan=\""+data[i].stat_row_num+"\">"+data[i].curr_stat+":<br>"+data[i].curr_stat_text+"</td><td width=\"25%\" style=\"text-align:left;\">"+data[i].room_type+"</td><td width=\"10%\">"+data[i].floor_no+"</td><td style=\"text-align:left;\">"+data[i].room_ids+"</td></tr>";
								}else{
									html = "<tr><td width=\"15%\" rowspan=\""+data[i].stat_row_num+"\">"+data[i].curr_stat+":<br>"+data[i].curr_stat_text+"</td><td width=\"25%\" rowspan=\""+data[i].type_row_num+"\" style=\"text-align:left;\">"+data[i].room_type+"</td><td width=\"10%\">"+data[i].floor_no+"</td><td style=\"text-align:left;\">"+data[i].room_ids+"</td></tr>";
								}
							}
							$("#dataMain1").append(html);
						}
					}
				}
			});
		}
		
		function goSearch2(){
			$.ajax({
						url :  "${ctx}/search/getroomnumbydate",
						type : "post",
						dataType : 'json',
						data : {
							startDate : $("#startDate").val()
						},
						success : function(result) {
							if(!result.issuccess){
								altWaringMsg("当前时间以后两周之内没有可售房。");
							}else{
								var datalist = result.datalist;
								var headlist = result.headlist;
								var typelist = result.typelist;
								//插入表头
								if(headlist.length != 0){
									$("#dataHead2").empty();
									var headHtml = "<tr><td>房间类型</td>";
									for(var i = 0; i < headlist.length; i++){
										if(i == 0){
											$("#startDate").val(headlist[i].date_day);
										}
										var headArr = headlist[i].date_day.split("-");
										headHtml = headHtml + "<td width=\"6%\">"+headlist[i].date_weak+"<br>"+headArr[2]+"</td>";
									}
									headHtml = headHtml + "</tr>";
									$("#dataHead2").append(headHtml);
								}
								
								//插入数据
								if(typelist.length > 0){
									$("#dataMain2").empty();
									for(var i = 0; i < typelist.length; i++){
										var code_id = typelist[i].codeId;
										var innerHtml = "<tr><td style=\"text-align:left;\">" + code_id + "：" + typelist[i].codeNamec + "（" + typelist[i].num + "）" +"</td>";
										for(var j = 0; j < headlist.length; j++){
											var headdate = headlist[j].date_day;
											var have = false;
											for(var k = 0; k < datalist.length; k++){
												if(datalist[k].date_day == headdate && datalist[k].code_id == code_id){
													innerHtml = innerHtml + "<td width=\"6%\">"+datalist[k].nouse_num+"</td>";
													have = true;
													break;
												}
											}
											if(!have){
												innerHtml = innerHtml + "<td width=\"6%\">"+typelist[i].num+"</td>";
											}
											
										}
										innerHtml = innerHtml + "</tr>";
										$("#dataMain2").append(innerHtml);
									}
									//插入空行
									$("#dataMain2").append("<tr><td></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td><td width=\"6%\"></td></tr>");
									
									var totalHtml = "<tr><td>总存量</td>";
									var useHtml = "<tr><td>占房数</td>";
									var ratioHtml = "<tr><td>占房率（%）</td>";
									for(var i = 0; i < headlist.length; i++){
										totalHtml = totalHtml + "<td width=\"6%\">"+headlist[i].room_nouse_num+"</td>";
										useHtml = useHtml + "<td width=\"6%\">"+headlist[i].room_use_num+"</td>";
										ratioHtml = ratioHtml + "<td width=\"6%\">"+headlist[i].use_ratio+"</td>";
									}
									totalHtml = totalHtml + "</tr>";
									$("#dataMain2").append(totalHtml);
									useHtml = useHtml + "</tr>";
									$("#dataMain2").append(useHtml);
									ratioHtml = ratioHtml + "</tr>";
									$("#dataMain2").append(ratioHtml);
								}
							}
						}
					});
		}
		
		//打开“房间存量表”查询div
		function openAlertDiv2(){
			//显示页面
			$("#searchAlert2").fadeIn();
			//初始化表头
			goSearch2();
			
			//日期控件添加回车事件监听
			$("#startDate").unbind();
			$("#startDate").keydown(function(event){
				var e = event || window.event;
				var keyCode = e.keyCode || e.which;
				if(keyCode == 13){
					goSearch2();
				}
			});
		}
		
		//查询弹出框按键监听
		$(document).keydown(function(event){
  			var e = event || window.event;
			var keyCode = e.keyCode || e.which;
			switch (keyCode) {
			case 116:
				closeAllDiv();
				openAlertDiv1();
				break;
			case 117:
				closeAllDiv();
				openAlertDiv2();
				break;
			case 118:
				closeAllDiv();
				break;
			case 119:
				closeAllDiv();
				break;
			case 120:
				closeAllDiv();
				break;
			default:
				break;
			}
  		});
  		
  		/*右侧菜单点击*/
		$("#searchMenu li a").click(function(){
			$(".rightNav li a").removeClass("rightNavPoint");
			$(this).addClass("rightNavPoint");
			var text = $(this).text();
			switch(text){
				case "当前可售房" : 
					closeAllDiv();
					openAlertDiv1();
					break;
				case "房间存量表" :
					closeAllDiv();
					openAlertDiv2();
					break;
				case "当前状态（房态）" : 
					break;
				case "当前状态（入住）" : 
					break;
				case "房间预测" : 
					break;
				default :
					break;
			}
		});
		/*右侧菜单点击 结束*/
  		
  		//关闭弹出框
  		$(".closeAlert").click(function(){
			$(".alertDivBg").fadeOut();
			$(this).parent().parent().parent().fadeOut();
		});
		
		$(".closeDiv").click(function(){
			$(".alertDivBg").fadeOut();
			$(this).parents(".alertDiv").fadeOut();
		});
		
		<c:if test="${isSearchPage == 1 }">openAlertDiv1();</c:if>
		
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
	});
	
	/**
	*@描述 错误提示信息
	*@param msg 提示信息
	* eg: altEerrMsg('错误提示');
	*/
	var altEerrMsg = function(msg)
	{
		var params = {type:'error',content:msg,title:'错误提示'
        ,id:'maipage_tips_erroemsg'};
		$.fn.alertDialog(params); 
	    $.fn.alertDialogShow(params.id);
	};

	/**
	*@描述 提示
	*@param msg 提示消息
	*@fun 点击确定后 执行函数 没有时确定后执行函数 不写即可 即 altInfMsg('测试消息');
	*@cancel点击取消按钮执行函数
	*eg: altInfMsg('次提示也！',function()
	*	{
	*		alert('你点击了确定');
	*	});
	*/
	var altInfMsg = function(msg,fun,cancel)
	{
		var param = {type:'tip',content:msg,title:'消息提示'
		 			,id:'maipage_tips_infmsg'
		 			,confirm:fun
		 			,cancel:cancel
				 }
		$.fn.alertDialog(param); 
		$.fn.alertDialogShow(param.id);
	};

	/**
	*@描述 成功提示
	*@param msg 提示消息
	*@pamra fun 点击成功按钮后执行函数 若无 则不传
	*/
	var altSuccessMsg = function(msg)
	{
		var param = {type:'succsess',content:msg,title:'消息提示'
		 			,id:'maipage_tips_successmsg'
				 }
		$.fn.alertDialog(param); 
		$.fn.alertDialogShow(param.id);
	};
	/**
	*@描述 警告提示
	*@param msg 提示消息
	*@fun 点击确定后 执行函数 没有时确定后执行函数 不写即可 即 altWaringMsg('测试消息');
	*@cancel点击取消按钮执行函数
	*eg: altWaringMsg('次提示也！',function()
	*	{
	*		alert('你点击了确定');
	*	});
	*/
	var altWaringMsg = function(msg,fun,cancel)
	{
		var param = {type:'warning',content:msg,title:'警告提示'
		 			,id:'maipage_tips_wraingmsg'
		 			,confirm:fun
		 			,cancel:cancel
				 }
		$.fn.alertDialog(param); 
		$.fn.alertDialogShow(param.id);
	};

	/**
	*禁止按钮拖动事件
	*/
	document.ondragstart = function() {
    		return false;
		};

</script>
<script type="text/javascript">
function logout(){
  altInfMsg("是否退出本系统?",function(){
     if(typeof idcard ==="object"){
        window.location.href="/logout";
        idcard.closeSystem();
      }else{
        window.location.href="/logout";
      }
     
  },function(){return false;});
}
$(function(){
  switchMenu();
});
</script>
</html>
