<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath }/resource/printcss/table.css" />
<title>调拨单</title>
</head>

<body>
	<div class="table-box">
    	<table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td class="title">西安嘉悦汽车维修服务有限公司</td>
                <td class="title" style="font-size:24px;">调拨单</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
             <c:forEach items="${list }"  var="headlist" begin="0" end="0">
            <tr>
                <td width="8%">调拨单号</td>
                <td width="30%">${headlist.allocation_no }</td>
                <td width="10%">调拨给的店铺</td>
                <td width="20%">${headlist.to_name }</td>
                <td width="10%">创建时间</td>
                <td width="20%">${ fn:substring(headlist.sw_date,0,19)}</td>
            </tr>
            <tr>
                <td width="8%">备注</td>
                <td width="30%">${headlist.memo }</td>
                <td width="10%">调拨创建人</td>
                <td width="20%">${headlist.uName }</td>
                <td width="10%">&nbsp;</td>
                <td width="20%">&nbsp;</td>
            </tr>
            </c:forEach>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="7%">序号</td>
                <td width="8%">配件编号</td>
                <td width="8%">配件名称</td>
                <td width="8%">配件类别</td>
                <td width="6%">配件品牌</td>
                <td width="6%">配件规格</td>
                <td width="8%">申请数量</td>
                <td width="8%">实际调拨数量</td>
                <td width="8%">最小单位</td>
                <td width="8%">调拨来源</td>
            </tr>
        </table>
       <c:forEach items="${list }"  var="list" varStatus="s">
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="7%">${s.count }</td>
                <td width="8%">${list.goods_code }</td>
                <td width="8%">${list.goods_name }</td>
                <td width="8%">${list.type_name }</td>
                <td width="6%">${list.goods_brand }</td>
                <td width="6%">${list.standard }</td>
                <td width="8%">${list.apply_num }</td>
                <td width="8%">${list.num }	</td>
                <td width="8%">${list.unit }</td>
                <td width="8%">${list.from_name }</td>
            </tr>
        </table>
       </c:forEach>
        <table class="table" cellpadding="0" cellspacing="0" style="border-bottom:0;">
            <tr>
                <td width="7%">打印时间</td>
                <td width="20%">${fn:substring(printDate,0,19) }</td>
                <td width="7%">操作人：</td>
                <td width="7%">${sysUser.infoEmp.empName }</td>
                <td width="30%">&nbsp;</td>
                <td width="7%">&nbsp;</td>
                <td width="13%">&nbsp;</td>
            </tr>
            <tr height="60"></tr>
            <tr>
                <td align="center" colspan="8">
                	<input type="button" value="打印" class="button" onclick="goPrint()" />
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
<script>
	function goPrint(){
		window.print();
	}
</script>
