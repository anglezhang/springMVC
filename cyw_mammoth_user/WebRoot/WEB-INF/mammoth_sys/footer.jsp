<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>页脚</title>
</head>
  <body>
   <div class="copyRight clearBoth">
	<ul class="hotel">
    	<li>村游管理系统</li>
        <li>网址：<a href="bbs.cyw.so" target="_blank">www.cyw.com</a></li>
        <li>客服电话：029-87885231</li>
        <li>客服QQ：2880078737</li>
        <li><span class="margin-right-20">|</span>本软件授权<a class="margin-left-10 margin-right-10" href="javascript:;">[新印象太河湾度假村]</a>为合法最终用户</li>
    </ul>
    <ul class="serverPhone">
    	<li>操作员：<a href="javascript:;"><shiro:principal/></a></li>
        <li class="timeStr" timeStr="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/></li>
    </ul>
</div>
<script type="text/javascript">
	/*
	*描述：获取系统时间，并实时动态显示
	*@param：timeAdd 时间戳递增
	*/
	function getTimeStr(){
		var timeStr=Date.parse($(".timeStr").attr("timeStr"));	// 日期转时间戳
		function timeAdd(){
			timeStr+=1000;
			var timeStrFormat = getSmpFormatDateByLong(timeStr,true);	// 时间戳转日期
			var timeStrFormat = timeStrFormat.substr(0,timeStrFormat.length-3);	 //去掉秒数
			$(".timeStr").html(timeStrFormat);
		}
		return timeAdd;
	}
	var sysTime=getTimeStr();
	setInterval('sysTime()',1000);
</script>
</body>
</html>
