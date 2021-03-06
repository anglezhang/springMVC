<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>房间管理--房价方案列表</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cywManage-css.css">

<script type="text/javascript" src="${ctx}/scripts/jquery-2.0.0.min.js"></script>
<script src="${ctx}/js/common.js"></script> 
<link href="${ctx}/scripts/wijmo.min.css" rel="stylesheet" />
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- Wijmo -->
<script src="${ctx}/scripts/wijmo.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/wijmo.grid.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/wijmo.input.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/customMergeManager.js" type="text/javascript"></script>
<!-- JQuery plugin -->
<script src="${ctx}/scripts/json2.js" type="text/javascript"></script>	
<script type="text/javascript" src="${ctx}/altDialog/altDialog.js"></script>
<script src="${ctx}/scripts/jquery.inputmask.min.js" type="text/javascript"></script>

<script src="${ctx}/js/syssetting/room/roomPlanList.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/commAltMessger.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/flexGridEditComm.js" type="text/javascript" ></script>
<link href="${ctx}/js/syssetting/flexGridEditComm.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.borderSolid1 {
	border: 0;
	 margin: 0; 
	padding: 10px;
	background: #f2f3f4;
	margin-top: 0;
	-moz-box-shadow: 0px #464646;
	-webkit-box-shadow: 0px #464646;
	box-shadow: 0px #464646;
	height:580px;
}

</style>
<script type="text/javascript">
	// 节假日类型集合
	var hcodesDataMap ;
	// 房类集合
	var roomTypeDataMap ;
	// 状态
	var status_search ;
	// 初始化
	$(function(){
		hcodesDataMap = eval('(' + '${hcodes}' + ')');
		roomTypeDataMap = eval('(' + '${hroomTypeIds}' + ')');
		status_search = ("${editFlag}" == "true");
		// 初始化房价方案列表
		fillHroomPlanListTable(hcodesDataMap , roomTypeDataMap , status_search);
		// 打开房价方案列表页面
		parent.showHroomPlanList();
		
		if(!status_search){
			goShowButton_1(false);
			goShowButton_2(false);
			$("a#add").css({"cursor":"not-allowed","color":"grey"}).prop("href","javascript:;");
		}
	})
	// 放弃当前操作并刷新列表
	function giveUpAndRefresh(){
		fillHroomPlanListTable(hcodesDataMap , roomTypeDataMap , status_search);
		// 置灰 确定 放弃按钮
		goShowButton_1(false);
	}
</script>
</head>
<body>
	<div class="borderSolid borderSolid1">
        <div class="widthB80 floatL" style="height: 100%;">
            <div class="tableDiv floatL widthB97 margin-top-10" id="theGrid" style="height: 98%;overflow-y:auto;overflow-x:hidden; "></div>
        </div>
        <ul class="floatL margin-left-20 margin-top-5">
			<li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a></li>
	        <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a></li>
	        <li class="margin-top-10"><a class="buttonLikeA" href="javascript:goAdd(3);" id="add">新&nbsp;&nbsp;增</a></li>
	        <li class="margin-top-10"><a class="buttonLikeA" href="javascript:;" style="color: grey;cursor:not-allowed;" id="del">取&nbsp;&nbsp;消</a></li>
	        <li class="margin-top-10"><a class="buttonLikeA" href="javascript:parent.closeDiv('roomPlanListAlert');"id="quit">退&nbsp;&nbsp;出</a></li>
        </ul>
	    <div class="clearBoth"></div>
	</div>
</body>
</html>
