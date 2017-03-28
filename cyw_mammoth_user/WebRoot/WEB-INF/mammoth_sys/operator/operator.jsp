<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设置-用户管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cywManage-css.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/lib/autocomplete/jquery.autocomplete.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>

<body>
	<%@ include file="../header.jsp" %>
	<!--secondMenu-->
	<%@ include file="../syssetting/secondMenu.jsp"%>
	<!--secondMenu -end- -->
<div class="center">
    <!--mainWeb-->
    <div class="main" id="webMain">
    	<!--mainInformation-->
        <div class="mainInfo2"> 
        <!--table-->
            	<div id="flexgrid" class="tableDiv">
              
                </div>
         <!--table -END- -->
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<div class="rightMenu">
        	<div class="roomStatus">                
                <div class="choice padding-bottom-10">
                    <form id="searchForm" class="bookRight" action="" method="get">
                        <dl class="inputDiv2 margin-top-none">
                            <dt>账号</dt>
                            <dd>
                                <input class="input" name="operId" type="text" placeholder="">
                            </dd>
                            <dt>操作员名称</dt>
                            <dd>
                                <input class="input" name="operName" type="text" placeholder="">
                            </dd>
                            <dt>所属组别</dt>
                            <dd>
                                <input class="input" name="groupId" type="text" placeholder="">
                            </dd>
                            <dt>所属部门</dt>
                            <dd>
                                <input class="input" name="deptId" type="text" placeholder="">
                            </dd>
                           
                        </dl>
                        <div class="clearBoth"></div>
                        <table width="90%" class="margin-left-10 margin-top-20">
                            <tr>
                                <td align="right"><label  class="margin-right-10"><input name="status" value="0" checked="checked"  type="radio"><span style="font-size: 14" class="margin-left-5">有效</span></label></td>
                                <td align="left"><label class="margin-left-10"><input name="status"  value="1"  type="radio"><span style="font-size: 14" class="margin-left-5">无效</span></label></td>
                            </tr>
                        </table>
                        <div class="cabDivNoneHei padding-top-15">                            
                            <a class="button_07 floatL" href="javascript:;" onclick="doSearch()">条件查询</a>
                            <a class="button_07 floatL" href="javascript:;">清空条件</a>
                            <a class="button_07 floatL" id="nonResident" href="javascript:;" onclick="formEdit()">操作员详情</a>
                            <a class="button_07 floatL" href="javascript:;" onclick="formAdd()">新增</a>
                        </div>
                        <!--小键盘-->
                        <div class="cabDiv clearBoth">
                        	<a href="javascript:;">A</a>
                            <a href="javascript:;">B</a>
                            <a href="javascript:;">C</a>
                            <a href="javascript:;">D</a>
                            <a href="javascript:;">E</a>
                            <a href="javascript:;">F</a>
                            <a href="javascript:;">G</a>
                            <a href="javascript:;">H</a>
                            <a href="javascript:;">I</a>
                            <a href="javascript:;">J</a>
                            <a href="javascript:;">K</a>
                            <a href="javascript:;">L</a>
                            <a href="javascript:;">M</a>
                            <a href="javascript:;">N</a>
                            <a href="javascript:;">O</a>
                            <a href="javascript:;">P</a>
                            <a href="javascript:;">Q</a>
                            <a href="javascript:;">R</a>
                            <a href="javascript:;">S</a>
                            <a href="javascript:;">T</a>
                            <a href="javascript:;">*</a>
                            <a href="javascript:;">U</a>
                            <a href="javascript:;">V</a>
                            <a href="javascript:;">W</a>
                            <a href="javascript:;">X</a>
                            <a href="javascript:;">Y</a>
                            <a href="javascript:;">Z</a>
                            <a href="javascript:;">#</a>
                        </div>
                        <!--小键盘END-->
                    </form>
                    <div class="clearBoth"></div>
                </div>
            </div>
        </div>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
    </div>
    <!--mainWeb end-->
</div>

<!--center -END -->
<!--copyRight xingli 2015-09-08-->
<%@ include file="../footer.jsp"%>
<!--copyRight -END -->
<!--弹出层阴影-->
<div class="alertDivBg"></div>
<div class="alertDivBg2"></div>
<!--弹出层阴影结束-->
<!--入住登记-->
  <div class="alertDiv checkInDiv moveBar acquaintanceDiv">
	<div class="alertMain greyBg" style="width:750px; margin-top:100px;">
    	<h4 class="moveDivAlert" id="MoveAlertDiv"><span id="formTitle">操作员资料维护</span><a href="javascript:;" class="closeDiv"></a></h4>
        <div class="borderSolid">
            <div class="clearBoth GuestTabIn fitBook posR">
            	<div class="GuestTabL5 margin-top-30 posR">
            	<form id="dataForm" action="">
                	<div class="posA" style="top:-30px; right:0;"><label><input id="theStatus" name="status" value="0" class="checkbox margin-right-5" type="checkbox" checked onclick="return false;">有效</label></div>
                    <div class="tableDivDow clearBoth margin-bottom-10 floatL" style="width:500px;">
                            <table width="100%">
                                <tr>
                                    <td width="70" align="right">账号</td>
                                    <td width="100"><input id="operId" name="operId" type="text" hidden style="display: none;" data-validation="required" label="账号" /><input class="input" id="theOperId" name="" type="text" data-validation="required" label="账号"></td>
                                    <td width="50" align="right">&nbsp;</td>
                                    
                                    
                                </tr>
                                <tr>
                                    <td align="right">操作员名称</td>
                                    <td><input class="input" name="operName" type="text" data-validation="required" label="操作员名称"></td>
                                    <td align="right">&nbsp;</td>
                                   
                                   
                                </tr>
                                <tr>
                                    <td align="right">密码</td>
                                    <td><input class="input" name="passwd" type="text" data-validation="required" label="密码"></td>
                                    <td align="right">&nbsp;</td>
                                   
                                   
                                </tr>
                                <tr>
                                    <td align="right">重复密码</td>
                                    <td><input class="input" id="passwd1" name="passwd1" type="text" data-validation="required" label="重复密码"></td>
                                    <td align="right">&nbsp;</td>
                                    
                                </tr>
                                <tr>
                                    <td align="right">所属组别</td>
                                    <td>
                                    <select class="select" name="groupId">
                                    	 <option value="1">前台</option>
                                    	  <option value="2">客房</option>
                                    	   <option value="3">管理员</option>
                                    	   
                                    	</select>
                                    </td>
                                    <td align="right">&nbsp;</td>
                                   
                                </tr>
                                <!-- <tr>
                                    <td align="right">所属部门</td>
                                    <td>
                                    
                                    	<select class="select" name="deptId">
                                    	 <option value="1">前台</option>
                                    	  <option value="2">客房</option>
                                    	   <option value="3">康乐</option>
                                    	    <option value="4">办公室</option>
                                    	</select>
                                    </td>
                                    <td align="right">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="right">操作员级别</td>
                                    <td>
                                    	<select class="select" name="rights">
                                    	 <option value="1">员工</option>
                                    	  <option value="2">领班</option>
                                    	   <option value="3">主管</option>
                                    	    <option value="4">经理</option>
                                    	</select>
                                    </td>
                                    <td align="right">&nbsp;</td>
                                </tr> -->
                            </table>
                            
                        </div>
                        
                        <div class="clearBoth"></div>
                    </form>    
                </div>
                
                 
               
                <!--右侧部分-->
                <div class="GuestTabR GTabPos posA">
                    <a class="button_02 group1" href="javascript:;" jack="formSubmit()" onclick="formSubmit()">确定</a>
                    <a class="button_02 group1" href="javascript:;" jack="formGiveUp()" onclick="formGiveUp()">放弃</a>
                    <a class="button_02 group2" href="javascript:;" jack="formDel()" onclick="formDel()">取消</a>
                    <a class="button_02 group2" href="javascript:;">消费统计</a>
                    <a class="button_02 group2" href="javascript:;">会员</a>
                    <a class="button_02 group2" href="javascript:;">特别提示</a>
                    <a class="button_02 group2" href="javascript:;" jack="formClose()" onclick="formClose()">退出</a>
                </div>
                <!--/右侧部分-->
            </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/入住登记-->
</body>


</body>
<script src="${ctx}/scripts/jquery-migrate-1.2.1.js" type="text/javascript"></script>
<script src="${ctx}/js/lib/autocomplete/jquery.autocomplete.min.js"></script>
<script src="${ctx}/scripts/jquery.inputmask.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery.form-validator.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/operator/operator.js"></script>

</html>