<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>夜审</title>
</head>

<body>
	<!--banner & menu  xingli  2015-09-08-->
	<%@ include file="../header.jsp"%>
	<!--banner & menu -END -->
	
	<!--secondMenu-->
	<div class="secondMenuDiv">
		<ul class="secondMenu">
	        <li><a class="thisSecMenu" href="javascript:;">夜审</a></li>
	    </ul>
	</div>
	<!--secondMenu -end- -->
	<!--center xingli 2015-09-08-->
	<div class="center">
	    	<div class="nightCheck">
		<div class="checkTop">
			营业日：<input class="input" id="opTime" name="opTime" type="text" value="" readonly="readonly">
			操作员：<input class="input" id="op" name="op" type="text" value="" readonly="readonly">
			<a class="button_07" href="javascript:startDayAudit('1');">开始夜审</a>
		</div>
		<ul class="checkLi">
			<li id="currStep0" class="wait">
				<span class="checkStep">0</span> 检查数据完整性
				<span class="checkStatus"></span>
			</li>
			<li id="currStep1" class="wait">
				<span class="checkStep">1</span> 置夜审标志
				<span class="checkStatus"></span>
			</li>
			<li id="currStep2" class="wait">
				<span class="checkStep">2</span> 过房租
				<span class="checkStatus"></span>
			</li>
			<li id="currStep3" class="wait">
				<span class="checkStep">3</span> 修改房态
				<span class="checkStatus"></span>
			</li>
			<li id="currStep4" class="wait">
				<span class="checkStep">4</span> 转熟客
				<span class="checkStatus"></span>
			</li>
			<li id="currStep5" class="wait">
				<span class="checkStep">5</span> 清理预定数据
				<span class="checkStatus"></span>
			</li>
			<li id="currStep6" class="wait">
				<span class="checkStep">6</span> 统计
				<span class="checkStatus"></span>
			</li>
			<li id="currStep7" class="wait">
				<span class="checkStep">7</span> 数据迁移
				<span class="checkStatus"></span>
			</li>
			<li id="currStep99" class="wait">
				<span class="checkStep">99</span> 夜审完成
				<span class="checkStatus"></span>
			</li>
		</ul>

	</div>
	</div>
	<!--center -END -->
	<!--弹出层阴影-->
	<div id="msg_tip_bg" class="alertDivBg" style="display:none;"></div>
	<!--消息弹出-->
	<div id="msg_tip" class="alertDiv nightAuditoBox moveBar" style="top:142px; display:none;">
	  <div class="alertMain" style="width:650px;">
	      <h4 class="moveDivAlert">提示<a href="javascript:;" class="closeDiv"></a></h4>
	      <!--弹出层内容-->
	      <div class="alertCenter nightAudito">
	        <div class="nightAudito-cont">
	        </div>
	        <div class="alertRight clearBoth">
	            <span class="buttonLikeA" onclick="closeDiv();">确定</span>
	            <span class="buttonLikeA">复制</span>
	            <span class="buttonLikeA" onclick="download();">另存为</span>
	        </div>
	      </div>
	      <!--/弹出层内容-->
	
	    </div>  
	</div>
	
	<!--操作提示弹出-->
	<div id="room_price_msg_tip" class="alertDiv moveBar" style="top:142px; display:none;">
	  <div class="alertMain" style="width:450px;">
	      <h4 class="moveDivAlert">提示信息<a href="javascript:;" class="closeDiv"></a></h4>
	      <!--弹出层内容-->
	      <div class="alertCenter nightMsgAlert">
	            <div class="nightMsg">
	                <p id="room_price_msg"></p>
	                <ul>
	                    <li>
	                        <label><input type="radio" name="handletype" value="1"> 以房价列表为准。</label>
	                    </li> 
	                    <li>
	                        <label><input type="radio" name="handletype" value="2"> 以现有房价为准。</label>
	                    </li>
	                    <li>
	                        <label><input type="radio" name="handletype" value="3"> 退出夜审，手动处理。</label>
	                    </li>     
	                </ul>
	                 
	            </div> 
	        <div class="alertRight clearBoth">
	            <span class="buttonLikeA" onclick="handlePrice();">确定</span>
	        </div>
	      </div>
	      <!--/弹出层内容-->
	    </div>  
	</div>
	<!--/操作提示弹出-->
	
	<!--copyRight xingli 2015-09-08-->
	<%@ include file="../footer.jsp"%>
	<!--copyRight -END -->

	<input type="hidden" id="path" name="path"
		value="${pageContext.request.contextPath}">
</body>
<script type="text/javascript">
//消息弹出框
$(function(){
	$(".closeDiv").click(function(){
		$("#room_price_msg_tip").fadeOut();
		$("#msg_tip_bg").fadeOut();
		$("#msg_tip").fadeOut();
	});
});
$(
	$.ajax({
		url:"${ctx}/dayaudit/getdayaudithead",
		type:"GET",
		dataType:"json",
		data:{},
		success:function(result){
			$("#opTime").val(result.hotelDate);
			$("#op").val(result.oper);
		}
	})
);


var msg = "";
//下载夜审检查提示
function download(){
	if( msg ){ 
        var inputs = "";
            inputs+="<input type='hidden' name='text' value='"+msg+"' />"; 
        jQuery("<form action='${ctx}/dayaudit/download' method='post'>"+inputs+"</form>").appendTo("body").submit().remove();
    };
	
}
//关闭夜审检查提示
function closeDiv(){
	$("#msg_tip_bg").fadeOut();
	$("#msg_tip").fadeOut();
}

//房价处理方式
function handlePrice(){
	var handle = $("input[name='handletype']:checked").val();
	if(handle == "1"){
		$.ajax({
			url : "${ctx}/dayaudit/handleroomprice",
			type : "get",
			dataType : "json",
			data : {},
			success : function(data){
				if(data){
					startDayAudit('1');
				}
			}
		});
	}else if(handle == "2"){
		startDayAudit("2");
	}else{
		changeStyle($("#currStep0"), "wait");
		$(".checkTop a").css({
			"cursor" : "pointer",
			"color" : "inherit"
		});
		$(".checkTop a").attr("href", "javascript:startDayAudit('1');");
	}
	
	$("#msg_tip_bg").fadeOut();
	$("#room_price_msg_tip").fadeOut();
}

//开始夜审
function startDayAudit(handle){
	$(".checkTop a").css({
		"cursor" : "not-allowed",
		"color" : "grey"
	});
	$(".checkTop a").attr("href", "javascript:;");
	$.ajax({
		url : "${ctx}/dayaudit/execute",
		type : "post",
		dataType : 'json',
		data : {
			currStep : "0",
			currSubStep : "1",
			sqlStep : "1",
			handle : handle
		},
		success : function(result) {
		    	changeStyle($("#currStep0"), "process");
				//启动轮询取夜审结果
		        setTimeout("longPolling(1)", 5000);				
		}
	});
}

//轮询取夜审结果
function longPolling(roomPlan){
	$.ajax({
		type : "post",
		url: "${ctx}/dayaudit/getresult",
		data: {"timed": new Date().getTime(), "roomPlan" : roomPlan},
		dataType: "json",
		error: function (XMLHttpRequest, textStatus, errorThrown) {
		    $("#state").append("[state: " + textStatus + ", error: " + errorThrown + " ]<br/>");
		    if (textStatus == "timeout") { // 请求超时
		            longPolling(); // 递归调用
		        
		        // 其他错误，如网络错误等
		        } else { 
		            longPolling();
		        }
		    },
		success: function (data, textStatus) {
		    var currStep = data.currStep;
		    var currSubStep = data.currSubStep;
		    var issuccess = data.issuccess;
		    switch(currStep){
		    	case 0:
		    		if(issuccess == "true"){
		    			if(currSubStep == "8"){
		    				changeStyle($("#currStep0"), "ok");
		    			}
		    		}else{
		    			if(currSubStep == "8"){
		    				$("#room_price_msg").empty().append(data.msg);
							$("#msg_tip_bg").fadeIn();
		    				$("#room_price_msg_tip").fadeIn();
		    			}else{
			    			$(".nightAudito-cont").empty().append(data.msg);
			    			msg = data.msg;
							$("#msg_tip_bg").fadeIn();
							$("#msg_tip").fadeIn();
			    			changeStyle($("#currStep0"), "error");
		    			}
			    		return;
		    		}
		    		changeStyle($("#currStep1"), "process");
		    		break;
		    	case 1:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep1"), "ok");
		    		}else{
		    			alert(data.msg);
		    			changeStyle($("#currStep1"), "error");
		    		}
		    		changeStyle($("#currStep2"), "process");
		    		break;
		    	case 2:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep2"), "ok");
		    		}else{
		    			alert(data.msg);
		    			changeStyle($("#currStep2"), "error");
		    		}
		    		changeStyle($("#currStep3"), "process");
		    		break;
		    	case 3:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep3"), "ok");
		    		}else{
		    			alert(data.msg);
		    			changeStyle($("#currStep"), "error");
		    		}
		    		changeStyle($("#currStep4"), "process");
		    		break;
		    	case 4:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep4"), "ok");
		    		}else{
		    			alert(data.msg);
		    			changeStyle($("#currStep4"), "error");
		    		}
		    		changeStyle($("#currStep5"), "process");
		    		break;
		    	case 5:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep5"), "ok");
		    		}else{
		    			alert(data.msg);
		    			changeStyle($("#currStep5"), "error");
		    		}
		    		changeStyle($("#currStep6"), "process");
		    		break;
		    	case 6:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep6"), "ok");
		    		}else{
		    			alert(data.msg);
		    			changeStyle($("#currStep6"), "error");
		    		}
		    		changeStyle($("#currStep7"), "process");
		    		break;
		    	case 7:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep7"), "ok");
		    		}else{
		    			alert(data.msg);
		    			changeStyle($("#currStep7"), "error");
		    		}
		    		changeStyle($("#currStep99"), "process");
		    		break;
		    		
		    	default:
		    		if(issuccess == "true"){
		    			changeStyle($("#currStep99"), "ok");
		    			alert("夜审完成！");
		    			return;
		    		}
		    		break;
		    }
		    
		    if (textStatus == "success") { // 请求成功
		        setTimeout("longPolling()", 5000);
		    }
		}
	});
}

function changeStyle(doc, style){
	doc.removeClass();
	doc.addClass(style);
}

</script>
</html>
