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
    <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/bootstrap-3.0.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/knockout-2.3.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/report/Scripts/GrapeCity.ActiveReports.Viewer.Html.js"></script>
</head>
<body>
<%@ include file="../reportheader.jsp" %>


	<div class="center">
		<div class="main" id="webMain">
			<!-- 左侧 -->
			<div class="mainInfo overFlowY" style="width:74.5%;">
			    <input value="${pageContext.request.contextPath}" id="rpath" type="hidden"/>
			    <div class="container" style="width:100%">
			        <div id="settingsContainer">
			            <div class="settings-row">
			                <label>选择报表:</label>
			                <div id="btnReport" class="btn-group">
			                    <button type="button" class="btn btn-default" data-bind="CAH020.rdlx">
			                                                          统计报表
			                    </button>
			                     <button type="button" class="btn btn-default" data-bind="CAH001.rdlx">
			                                                          未结清账目下退房报表CAH001(当日)
			                    </button>
			                </div>
			            </div>
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
	                        <ul class="roomNum" style="height:580px;">
	                            <li style="width:100%;">未结清账目下退房保镖啊</li>
	                            <li style="width:100%;">营业单明细报表</li>
	                            <li style="width:100%;">收款周期/结账明细报表</li>
	                            <li style="width:100%;">住客实际离去报表（当日）</li>
	                            <li style="width:100%;">住客行将离去报表（当日）</li>
	                            <li style="width:100%;">前台收银试算平衡表（按日期）</li>
	                            <li style="width:100%;">前台收银取消单报表</li>
	                            <li style="width:100%;">换房及房租修改轨迹报表</li>
	                            <li style="width:100%;">前台AR挂账明细报表</li>
	                            <li style="width:100%;">会员AR挂账明细报表（按日期）</li>
	                        </ul>
	                    </form>
	                    <form class="currentState displayNone" action="" method="get">
	                        <ul class="roomNum" style="height:470px">
	                            <li style="width:100%;">REC001 住客实际抵达报表（当日）</li>
	                            <li style="width:100%;">REC002 订房入住报表（当日） </li>
	                            <li style="width:100%;">REC003 住客实际离去报表(当日) </li>
	                            <li style="width:100%;">REC004 住客行将抵达报表（当日）</li>
	                            <li style="width:100%;">REC005 住客行将离去报表（当日） </li>
	                            <li style="width:100%;">REC010 在住宾客一览表 </li>
	                            <li style="width:100%;">REC019 在住客人报表 </li>
	                            <li style="width:100%;">REC021 半日租时间以后客人实际离去报表 </li>
	                            <li style="width:100%;">REC101 住客实际抵达报表 </li>
	                            <li style="width:100%;">REC102 订房入住报表 </li>
	                            <li style="width:100%;">REC103 住客实际离去报表 </li>
	                            <li style="width:100%;">REC104 住客行将抵达报表 </li>
	                            <li style="width:100%;">REC105 住客行将离去报表</li>
	                        </ul>
	                        <table width="90%" class="margin-left-10 margin-bottom-10 margin-top-20">
	                            <tr>
	                                <td width="50%" align="right"><label><input name="entRoom" type="radio"><span class="margin-left-10 margin-right-30">明细</span></label></td>
	                                <td align="left"><label><input name="entRoom" type="radio"><span class="margin-left-10">汇总</span></label></td>
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
	                                <td width="30%" align="right"><input class="input" name="" type="text"></td>
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
    <script type="text/javascript">
        $(function(){ 
            var rpath=$("#rpath").val();
            var viewer = GrapeCity.ActiveReports.Viewer({
                element: '#viewerContainer',
                reportService: {
                    url: 'http://10.0.1.200:88/ReportService.asmx'
                },
                uiType: 'desktop',
                reportLoaded: function () {
                    reportsButtons.prop('disabled', false);
                },
                localeUri: rpath+'/report/Scripts/i18n/zh.txt'
            });

            var reportsButtons = $('#btnReport button');

            reportsButtons.bind('click', function (ev) {
                ev.stopImmediatePropagation();
                reportsButtons.removeClass('active');
                var target = $(ev.target);
                target.addClass('active');
                reportsButtons.prop('disabled', true);
                var reportOption = {
                    id: target.attr('data-bind'),
                     parameters: [
                     {
                         name:'Title',
                         value:'未结清账目下退房报表CAH001'
                     }
                    ,{
                        name:'SubTitle',
                        value:'未结清账目下退房报表CAH001'
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
                        value:'CAH001'
                    }]
                };
                viewer.option('report', reportOption);
            });

        }); 
        
        /*右侧TAB页切换DIV*/
		$("#userCheckIn").click(function(){
			$(".checkLogin").css("display","block");
			$(".currentState").css("display","none");
		});
		$("#userCurrentState").click(function(){
			$(".checkLogin").css("display","none");
			$(".currentState").css("display","block");
		});
		/*END右侧TAB页切换DIV*/
		//房态右Tab页  右侧的
		$(".roomStatusTitle li").click(function(){
			$(".roomStatusTitle li").removeClass("point");
			$(this).addClass("point");
		});
		
		//房态右Tab页  右侧的END
    </script>   
    <%@ include file="../footer.jsp" %>
</body>
</html>
