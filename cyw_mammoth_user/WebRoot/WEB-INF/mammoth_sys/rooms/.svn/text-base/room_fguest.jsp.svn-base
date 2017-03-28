<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>熟客查询</title>
</head>
	<body>
		<script type="text/html" id="rooms_fguest_searchtable">
			<table class="tableMain" style="width:920px;">
				<thead> 
                    <tr>
                        <td width="10%">中文名</td>
                        <td width="25%">证件号码</td>
                        <td width="20%">联系电话</td>
                        <td width="5%">性别</td>
                        <td width="18%">合约单位</td>
						<td width="10%">熟客编号</td>
						<td width="12%">备注</td>
                    </tr>
                </thead>
			</table>
			<table class="tableMain" style="width:920px;">
				<tbody>
				{{each list as value i}}
					<tr {{if i==0}} class="fguestTtableSlected" data-selected="true" style="background-color:#b6d1a6;" {{/if}}>
						<td width="10%">{{value.gstNamec}}</td>
                        <td width="25%">{{value.idCard}}</td>
                        <td width="20%">{{value.mobile}}</td>
                        <td width="5%">{{value.gender | setSex}}</td>
                        <td width="18%">{{value.companyName}}</td>
						<td width="10%">{{value.gstId}}</td>
						<td width="12%">{{value.notice}}</td>
					</tr>
				{{/each}}
				</tbody>
			</table>
		</script>
		<table class="widthB98 margin-top-15 margin-bottom-10">
	        <tr>
	            <td width="12%" align="right"><span class="blue_01">中文名</span></td>
	            <td width="18%">
	            	<input class="input" fguest-name="gstNamec" name="chinaeseName" id="roomfguest_chinaesename" type="text">
	            	<div class="tableDiv height200 overflowScro positionA inputAlertDiv displayNone" id="room_fguest_list" is-show="true" style="background:#fff;width:610px;">	
	            	</div>
            	</td>
	            <td width="15%" align="right"><span class="blue_01">联系电话</span></td>
	            <td width="25%"><input class="input" fguest-name="mobile" name="mobile" id="roomfguest_mobile" type="text"></td>
	            <td width="15%" align="right"><span class="blue_01">会员卡号</span></td>
	            <td><input class="input" fguest-name="memberCardNO" name="memberCard" id="roomfguest_membercard" type="text"></td>
	        </tr>
	        <tr>
	            <td align="right"><span class="blue_01">英文名</span></td>
	            <td><input class="input" fguest-name="gstNamee" name="englishName" id="roomfguest_englishname" type="text"></td>
	            <td align="right"><span class="blue_01">身份证号</span></td>
	            <td><input class="input" fguest-name="idCard" name="idCard" id="roomfguest_idcard" type="text"></td>
	            <td align="right"><span class="blue_01">熟客编号</span></td>
	            <td><input class="input"  fguest-name="gstId" name="vipNO" id="roomfguest_vipno" type="text"></td>
	        </tr>
	    </table>
		<div class="link2"></div>
		 <table class="widthB98 margin-top-15 margin-bottom-10">
	        <tr>
	            <td width="12%" align="right"><span class="gry_9">客人来源</span></td>
	            <td width="25%">
	            	<input class="input gry_9" readonly fguest-name="gstOrgSrc" type="text">
	            </td>
	            <td width="15%" align="right"><span class="gry_9">上次抵店</span></td>
	            <td width="18%"><input class="input gry_9" readonly fguest-name="reachDate" type="text"></td>
	            <td width="15%" align="right"><span class="gry_9">上次房号</span></td>
	            <td><input class="input gry_9" readonly fguest-name="roomId" type="text"></td>
	        </tr>
	        <tr>
	            <td align="right"><span class="gry_9">客人分类</span></td>
	            <td>
	            	<input class="input gry_9" readonly fguest-name="gstType" type="text">
	            </td>
	            <td align="right"><span class="gry_9">上次离店</span></td>
	            <td><input class="input gry_9" readonly fguest-name="leaveDate" type="text"></td>
	            <td align="right"><span class="gry_9">上次房价</span></td>
	            <td><input class="input gry_9" readonly fguest-name="lastPrice" type="text"></td>
	        </tr>
	        <tr>
	            <td align="right"><span class="gry_9">合约单位</span></td>
	            <td>
	            	<table width="100%">
	                    <tr>
	                        <td style="padding:0;">
	                            <input class="input widthB90 gry_9" readonly fguest-name="companyName" type="text">
	                        </td>
	                        <td width="22" align="left" style="padding:0;">
	                            <a class="HYbutton" href="javascript:;"></a>
	                        </td>
	                    </tr>
	                </table>
	            </td>
	            <td align="right"><span class="gry_9">常住房类</span></td>
	            <td>
	            	<input class="input widthB90 gry_9" readonly fguest-name="usuallyRoomType" type="text">
	            </td>
	            <td align="right"><span class="gry_9">住房次数</span></td>
	            <td><input class="input gry_9" readonly fguest-name="leaveTimes" type="text"></td>
	        </tr>
	        <tr>
	            <td colspan="4"></td>
	            <td align="right"><span class="gry_9">累计消费</span></td>
	            <td><input class="input gry_9" readonly fguest-name="cumuLative" type="text"></td>
	        </tr>
	    </table>
	    <table class="widthB97 margin-top-15 margin-bottom-10 margin-left-10" fguest-gstid="">
			<tr>
			   <td width="80"><span class="fontWeight">消费记录</span></td>
			   <td width="80" align="center"><a class="theDay" is-selected="true" href="javascript:;" >今日</a></td>
			   <td width="80" align="center"><a class="theDay" is-selected="false" href="javascript:;">最近7日</a></td>
			   <td width="80" align="center"><a class="theDay" is-selected="false" href="javascript:;">最近30日</a></td>
			   <td width="80" align="center"><a class="theDay" is-selected="false" href="javascript:;">最近1年</a></td>
			   <td><input class="input" name="" type="text"></td>
			   <td width="20" align="center">至</td>
			   <td><input class="input" name="" type="text"></td>
			</tr>
		</table>
		<div class="tableDiv floatL margin-left-10 widthB97 margin-bottom-10" style="width:98%;height:200px;">
			
		</div>
		 <div class="alertRight clearBoth margin-top-20">
	       <a class="buttonLikeA floatR margin-right-5" id="room_fguestcancle_btn" href="javascript:;">退出</a>
	       <a class="buttonLikeA floatR margin-right-5" id="room_fguestprint_btn" href="javascript:;">打印</a>
	    </div>
		<div class="clearBoth"></div>
		<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
		<script>
			seajs.config({
		        base: '${pageContext.request.contextPath}/js/lib/'
		        , alias: {
		            'template': 'template/template'
		        }
		    });
			seajs.use("${pageContext.request.contextPath}/js/rooms/plugins/roomFguest.js?number=" + Math.random());
		</script>
   	</body>
</html>