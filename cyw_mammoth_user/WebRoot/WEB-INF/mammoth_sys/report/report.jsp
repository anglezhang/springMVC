<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>报表</title>
    <link href="${pageContext.request.contextPath }/report/css/theme-cosmo.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/report/css/site.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/report/css/GrapeCity.ActiveReports.Viewer.Html.css" rel="stylesheet">
    <style type="text/css">
		<!-- 
		.roomNum li { 
		    width:200px; 
		    white-space:nowrap; 
		    text-overflow:ellipsis; 
		    overflow: hidden; 
		    } 
		--> 
		#viewerContainer{ height:648px; border-color:#AAADB2;}
		.ar-viewer .toolbar{ border-width: 0 0 1px; border-color:#CCC;}
		.menu .nav .bdL{ height:70px !important;}
		.menu .nav li > a{ height:90px !important;}
		.msgBox .tabTit{ height:31px !important;}
		.msgBox .tabSel{ height:34px !important;}
		.msgBox .msg,.updateSysBox .updateSys{ width:96% !important;}
		.msgBox .tabCon{ width:100% !important; padding-top:0 !important;}
		.serviceBox .service{ width:95% !important;}
		.menu .logo h1{ margin:4px 0 5px;}
		.menu .nav{ margin-right:1px;}
    </style>
</head>
<body>
	<%@ include file="../header.jsp"%>


	<div class="center">
		<div class="main" id="webMain">
			<!-- 左侧 -->
			<div class="mainInfo" style="width:74.5%;">
			    <input value="${pageContext.request.contextPath}" id="rpath" type="hidden"/>
			    <div class="container" style="width:100%">
			        <div id="settingsContainer">
<!-- 			            <div class="settings-row"> -->
<!-- 			                <label>选择报表:</label> -->
<!-- 			                <div id="btnReport" class="btn-group"> -->
<!-- 			                    <button type="button" class="btn btn-default" data-bind="CAH020.rdlx"> -->
<!-- 			                                                          统计报表 -->
<!-- 			                    </button> -->
<!-- 			                     <button type="button" class="btn btn-default" data-bind="CAH001.rdlx"> -->
<!-- 			                                                          未结清账目下退房报表CAH001(当日) -->
<!-- 			                    </button> -->
<!-- 			                </div> -->
<!-- 			            </div> -->
			        </div>
			        <div id="viewerContainer">
			        </div>
			    </div>
    		</div>
    		<!-- 左侧END -->
    		<!-- 右侧 -->
    		<div class="rightMenu rightMenuFT" style="border-top:none;">
	        	<div class="roomStatus">
	            	<ul class="roomStatusTitle" style="top:-33px;">
	                    <li class="point" id="userCheckIn">快捷报表</li>
	                    <li id="userCurrentState">报表选取</li>
	                </ul>
	                <div class="choice padding-top-10 padding-bottom-30">
	                    <form class="checkLogin" action="" method="get">
	                        <ul class="roomNum" style="height:580px; margin-right: 1%; overflow-y:auto;" id="fast_report">
<!-- 	                            <li style="width:100%;">未结清账目下退房保镖啊</li> -->
<!-- 	                            <li style="width:100%;">营业单明细报表</li> -->
<!-- 	                            <li style="width:100%;">收款周期/结账明细报表</li> -->
<!-- 	                            <li style="width:100%;">住客实际离去报表（当日）</li> -->
<!-- 	                            <li style="width:100%;">住客行将离去报表（当日）</li> -->
<!-- 	                            <li style="width:100%;">前台收银试算平衡表（按日期）</li> -->
<!-- 	                            <li style="width:100%;">前台收银取消单报表</li> -->
<!-- 	                            <li style="width:100%;">换房及房租修改轨迹报表</li> -->
<!-- 	                            <li style="width:100%;">前台AR挂账明细报表</li> -->
<!-- 	                            <li style="width:100%;">会员AR挂账明细报表（按日期）</li> -->
	                        </ul>
	                    </form>
	                    <form class="currentState displayNone" action="" method="get">
	                        <ul class="roomNum" style="height:470px; margin-right: 1%; overflow-y:auto;" id="other_report">
<!-- 	                            <li style="width:100%;">REC001 住客实际抵达报表（当日）</li> -->
<!-- 	                            <li style="width:100%;">REC002 订房入住报表（当日） </li> -->
<!-- 	                            <li style="width:100%;">REC003 住客实际离去报表(当日) </li> -->
<!-- 	                            <li style="width:100%;">REC004 住客行将抵达报表（当日）</li> -->
<!-- 	                            <li style="width:100%;">REC005 住客行将离去报表（当日） </li> -->
<!-- 	                            <li style="width:100%;">REC010 在住宾客一览表 </li> -->
<!-- 	                            <li style="width:100%;">REC019 在住客人报表 </li> -->
<!-- 	                            <li style="width:100%;">REC021 半日租时间以后客人实际离去报表 </li> -->
<!-- 	                            <li style="width:100%;">REC101 住客实际抵达报表 </li> -->
<!-- 	                            <li style="width:100%;">REC102 订房入住报表 </li> -->
<!-- 	                            <li style="width:100%;">REC103 住客实际离去报表 </li> -->
<!-- 	                            <li style="width:100%;">REC104 住客行将抵达报表 </li> -->
<!-- 	                            <li style="width:100%;">REC105 住客行将离去报表</li> -->
	                        </ul>
	                        <table width="90%" class="margin-left-10 margin-bottom-10 margin-top-20">
	                            <tr>
	                                <td width="50%" align="right"><label><input name="entRoom" type="radio" value="1"><span class="margin-left-10 margin-right-30">明细</span></label></td>
	                                <td align="left"><label><input name="entRoom" type="radio" value="2"><span class="margin-left-10">汇总</span></label></td>
	                                <td align="left"><label><input name="entRoom" type="radio" value="3" checked="checked"><span class="margin-left-10">不限</span></label></td>
	                            </tr>
	                        </table>
	                        <table width="90%" class="margin-left-10 margin-bottom-10 margin-top-10">
	                            <tr>
	                                <td width="20%" align="right"><span class="margin-right-5">属性</span></td>
	                                <td width="30%" align="right">
	                                	<select class="select widthB100">
		                                     <option>REC前台</option>
		                                     <option>HSK管家</option>
		                                     <option>SAL销</option>
		                                     <option>CAH财务</option>
		                                     <option>MAN总经理</option>
		                                </select>
	                                </td>
	                                <td width="20%" align="right"><span class="margin-right-5">代码</span></td>
	                                <td width="30%" align="right"><input class="input" name="code_id_text" type="text" id="code_id_text"></td>
	                            </tr>
	                        </table> 
	                    </form>
	                    <div class="clearBoth"></div>
	                </div>
	                <div class="clearBoth"></div>
	            </div>
	        </div>
	        <!-- 右侧END -->
    	</div>
    </div>
    <!-- /.container -->
<!--     <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/jquery-1.10.2.js"></script> -->
<!--     <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/jquery-1.10.2.js"></script> -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/bootstrap-3.0.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/knockout-2.3.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/GrapeCity.ActiveReports.Viewer.Html.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/altDialog/altDialog.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/syssetting/commAltMessger.js"></script>
    <script type="text/javascript">
    
	var datalist;
	var reportPath;
     $(function(){ 
         var rpath=$("#rpath").val();

		/*右侧TAB页切换DIV*/
		$("#userCheckIn").click(function(){
			$(".checkLogin").css("display","block");
			$(".currentState").css("display","none");
		});
		$("#userCurrentState").click(function(){
			$(".checkLogin").css("display","none");
			$(".currentState").css("display","block");
			
			$("input[name='entRoom']").click(function(){
					filterReportTitle();
			});
		});
		/*END右侧TAB页切换DIV*/
		//房态右Tab页  右侧的
		$(".roomStatusTitle li").click(function(){
			$(".roomStatusTitle li").removeClass("point");
			$(this).addClass("point");
		});
		
		//代码文本框添加回车事件监听
		$(document).keydown(function(event){
			var e = event || window.event;
			var keyCode = e.keyCode || e.which;
			if(keyCode == 13){
				//屏蔽回车表单提交
				return false;
			}
		});
		//代码文本框绑定内容改变事件
		$("#code_id_text").bind("input propertychange", function(){
			//内容该病后重新过滤报表
			filterReportTitle();
		});
		
		//过滤报表
		function filterReportTitle(){
			$("#other_report").empty();
					var radioVal = $("input[name='entRoom']:checked").val();
					radioVal = radioVal == undefined ? "" : radioVal;
					var code_id = $("#code_id_text").val().trim().toUpperCase();
					for(var i = 0; i < datalist.length; i++){
						if(code_id == ""){
							if(radioVal == 3){
								$("#other_report").append("<li style=\"width:100%;\" title=\""+datalist[i].rpt_id+":"+datalist[i].rpt_title+"\" rpt_file=\""+datalist[i].rpt_file+"\" rpt_title=\""+datalist[i].rpt_title+"\" rpt_id=\""+datalist[i].rpt_id+"\">"+datalist[i].rpt_id+":"+datalist[i].rpt_title+"</li>");
							}else{
								if(datalist[i].rpt_type == radioVal){
									$("#other_report").append("<li style=\"width:100%;\" title=\""+datalist[i].rpt_id+":"+datalist[i].rpt_title+"\" rpt_file=\""+datalist[i].rpt_file+"\" rpt_title=\""+datalist[i].rpt_title+"\" rpt_id=\""+datalist[i].rpt_id+"\">"+datalist[i].rpt_id+":"+datalist[i].rpt_title+"</li>");
								}
							}
						}else{
							if((datalist[i].rpt_id+":"+datalist[i].rpt_title).toUpperCase().indexOf(code_id) >= 0){
								if(radioVal == 3){
									$("#other_report").append("<li style=\"width:100%;\" title=\""+datalist[i].rpt_id+":"+datalist[i].rpt_title+"\" rpt_file=\""+datalist[i].rpt_file+"\" rpt_title=\""+datalist[i].rpt_title+"\" rpt_id=\""+datalist[i].rpt_id+"\">"+datalist[i].rpt_id+":"+datalist[i].rpt_title+"</li>");
								}else{
									if(datalist[i].rpt_type == radioVal){
										$("#other_report").append("<li style=\"width:100%;\" title=\""+datalist[i].rpt_id+":"+datalist[i].rpt_title+"\" rpt_file=\""+datalist[i].rpt_file+"\" rpt_title=\""+datalist[i].rpt_title+"\" rpt_id=\""+datalist[i].rpt_id+"\">"+datalist[i].rpt_id+":"+datalist[i].rpt_title+"</li>");
									}
								}
							}
						}
					}
					$("#other_report li").click(function(){
						var reportOption = {
			                   id: $(this).attr("rpt_file"),
			                   parameters: [
			                   {
			                        name:'Title',
			                        value:$(this).attr("rpt_title")
			                   }
			                   ,{
			                       name:'SubTitle',
			                       value:$(this).attr("rpt_title")
			                   },
			                   {
			                       name:'CompanyCnName',
			                       value:'户县太河湾印象农庄'
			                   },
			                   {
			                       name:'CompanyEnName',
			                       value:'Huxian impression too River Farm'
			                   },
			                   {
			                       name:'OperID',
			                       value:'UNI'
			                   },
			                   {
			                       name:'ReportCode',
			                       value:$(this).attr("rpt_id")
			                   }]
			               };
			               viewer.option('report', reportOption);
					});
		}
		
		
		//进入页面默认加载快捷报表
		$.ajax({
			url : $("#rpath").val() + "/reportdoc/getreportdoclist",
			type : "post",
			dataType : 'json',
			data : {
				isShortCut : "0",
				rptType : "3",
				rptId : ""
			},
			success : function(result) {
				//报表列表置空
				$("#fast_report").empty();
				$("#other_report").empty();
					
				datalist = result.datalist;
				reportPath = result.reportPath;
				if(datalist.length <= 0){
					return;
				}
				//报表服务器路径不为空，初始化报表控件
				if(reportPath == "" || reportPath == "null" || reportPath == undefined){
					altWaringMsg("请先配置报表服务器路径。");
					return;
				}else{
					//初始化报表控件
					viewer = GrapeCity.ActiveReports.Viewer({
		                element: "#viewerContainer",
		                reportService: {
		                    url: reportPath + "/ReportService.asmx"
		                },
		                uiType: "desktop",
		                localeUri: rpath+"/report/Scripts/i18n/zh.txt"
		            });
				}
				//进入页面构建快捷报表列表
				for(var i = 0; i < datalist.length; i++){
					if(datalist[i].rpt_shortcut_allow == "1"){
						$("#fast_report").append("<li style=\"width:100%;\" title=\""+datalist[i].rpt_id+":"+datalist[i].rpt_title+"\" rpt_file=\""+datalist[i].rpt_file+"\" rpt_title=\""+datalist[i].rpt_title+"\" rpt_id=\""+datalist[i].rpt_id+"\">"+datalist[i].rpt_id+":"+datalist[i].rpt_title+"</li>");
					}
				}
				$("#fast_report li").click(function(){
					var reportOption = {
	                    id: $(this).attr("rpt_file"),
	                    parameters: [
	                    {
	                         name:'Title',
	                         value:$(this).attr("rpt_title")
	                    }
	                    ,{
	                        name:'SubTitle',
	                        value:$(this).attr("rpt_title")
	                    },
	                    {
	                        name:'CompanyCnName',
	                        value:'户县太河湾印象农庄'
	                    },
	                    {
	                        name:'CompanyEnName',
	                        value:'Huxian impression too River Farm'
	                    },
	                    {
	                        name:'OperID',
	                        value:'UNI'
	                    },
	                    {
	                        name:'ReportCode',
	                        value:$(this).attr("rpt_id")
	                    }]
	                };
	                viewer.option('report', reportOption);
				});
				//进入页面构建报表选取列表
				for(var i = 0; i < datalist.length; i++){
					$("#other_report").append("<li style=\"width:100%;\" title=\""+datalist[i].rpt_id+":"+datalist[i].rpt_title+"\" rpt_file=\""+datalist[i].rpt_file+"\" rpt_title=\""+datalist[i].rpt_title+"\" rpt_id=\""+datalist[i].rpt_id+"\">"+datalist[i].rpt_id+":"+datalist[i].rpt_title+"</li>");
				}
				$("#other_report li").click(function(){
					var reportOption = {
	                    id: $(this).attr("rpt_file"),
	                    parameters: [
	                    {
	                         name:'Title',
	                         value:$(this).attr("rpt_title")
	                    }
	                    ,{
	                        name:'SubTitle',
	                        value:$(this).attr("rpt_title")
	                    },
	                    {
	                        name:'CompanyCnName',
	                        value:'户县太河湾印象农庄'
	                    },
	                    {
	                        name:'CompanyEnName',
	                        value:'Huxian impression too River Farm'
	                    },
	                    {
	                        name:'OperID',
	                        value:'UNI'
	                    },
	                    {
	                        name:'ReportCode',
	                        value:$(this).attr("rpt_id")
	                    }]
	                };
	                viewer.option('report', reportOption);
				});
			}
		});
     }); 
    </script>   
    <%@ include file="../footer.jsp" %>
</body>
</html>
