<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工作组授权</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/cywManage-css.css">

<script type="text/javascript" src="${ctx}/scripts/jquery-2.0.0.min.js"></script>
<script src="${ctx}/js/common.js"></script> 
<link href="${ctx}/scripts/wijmo.min.css" rel="stylesheet" />
<link href="${ctx}/js/syssetting/flexGridEditComm.css" rel="stylesheet" type="text/css" />


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

<script src="${ctx}/js/syssetting/permission/hfunction_control_list.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/flexGridEditComm.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/commAltMessger.js" type="text/javascript" ></script>
<script src="${ctx}/js/syssetting/permission/workGroup_auth.js" type="text/javascript" ></script>
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
.borderSolid table .splitDivRight {
	width: 100%;
	height: 100%; 
	overflow-y: auto;
	border:0;
}
.splitDivLeft {
	width: 100%;
	height: 100%;
	overflow-y: auto;
	border: 0;
	background: #fff;
}
.splitDivLeft li , .splitDivRight li{
	text-align: left;
	height:26px;
}
.tableDiv .tableHeiScll{
	overflow: auto;
	text-align: left;
}
.tree .treeBody .scdLi{
	width: 92%;
}
.tree .treeBody .scdLi li{
	width: 100%;
}
</style>
<script type="text/javascript">
	// 初始化
	$(function(){
		var functionTypeDataList = eval('(' + '${functionTypeDataList}' + ')');
		var optionHtml = "" ;
		for ( var i = 0; i < functionTypeDataList.length; i++) {
			optionHtml += "<option value=\""+functionTypeDataList[i].code+"\">" ;
				optionHtml += functionTypeDataList[i].name ;
			optionHtml += "</option>" ;
		}
		$("#functionType").html(optionHtml);
		if(functionTypeDataList.length > 0){
			functionTypeChange(functionTypeDataList[0].code);
		}
			
		// 打开列表页面
		parent.showWorkGroupAuthList();
		
	})
</script>
</head>
<body>
	<input type="hidden" id="workGroupId" name="workGroupId" value="${groupId}" />
	<div class="borderSolid borderSolid1">
        <!--弹出层内容-->
        <div class="alertCenter">
           <table class="table margin-top-20 height400">
               <tr>
                   <td width="360" align="center" valign="top">
                        <table width="96%">
                        	<tr>
                            	<td align="left" width="70">功能类型</td>
                                <td align="left">
                                	<select class="select" id="functionType" onchange="functionTypeChange(this.value)">
                                	</select>
                                </td>
                            </tr>
                        </table>
                        <!--table-->
                        <div class="tableDiv margin-left-10 widthB95 whiteBackground">
                            <div class="tableHeiScll height400">
                                <div class="tree" style="margin: 8px 0px;">
									<div id="left_tree_div" class="treeBody">
										
									</div>
								</div>
                            </div>
                        </div>
                   </td>
                   <td align="center">
                       <a class="forSN margin-top-40 margin-left-10" href="javascript:leftMoveFun();"><img src="${ctx}/img/right_01.png"></a>
                       <a class="forSN margin-top-50 margin-left-10" href="javascript:rightMoveFun();"><img src="${ctx}/img/left_01.png"></a>
                   </td>
                   <td width="360" valign="top">
                       <table width="96%">
                        	<tr>
                            	<td align="left" colspan="2">已授权功能</td>
                            </tr>
                        </table>
                        <div class="tableDiv floatL margin-left-10 widthB95 whiteBackground">
                            <div class="tableHeiScll height400" >
                            	<div class="tree" style="margin: 8px 0px;">
									<div id="right_tree_div" class="treeBody right_treeBody">
										
									</div>
								</div>
							</div>
                            </div>
                        </div>
                   </td>
               </tr>
           </table>
           <div class="alertRight clearBoth margin-top-40 height60">
               <a class="buttonLikeA floatR margin-right-20" href="javascript:parent.closeDiv('workGroupAuthListAlert');">退出</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="giveUp">放弃</a>
               <a class="buttonLikeA floatR margin-right-10" href="javascript:;" id="save">确定</a>
           </div>
           <div class="clearBoth"></div>
        </div>
        <!--/弹出层内容-->
	</div>
</body>
</html>
