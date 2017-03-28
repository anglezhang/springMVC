<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath }/resource/printcss/table.css" />
<title>维修发料单</title>
</head>

<body>
	<div class="table-box">
    	<table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td class="title">西安嘉悦汽车维修服务有限公司</td>
                <td class="title" style="font-size:24px;">维修发料单</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
             <c:forEach items="${list }"  var="headlist" begin="0" end="0">
            <tr>
                <td width="8%">出库单号</td>
                <td width="15%">${headlist.b_no }</td>
                <td width="10%">维修服务单</td>
                <td width="15%">${headlist.cusordernum }</td>
                <td width="10%">维修类型</td>
                <td width="10%">${headlist.serviceTypes}</td>
                <td width="10%">发料日期</td>
                <td width="20%">${fn:substring(headlist.bill_date,0,19) }</td>
            </tr>
          	<tr>
                <td width="8%">牌照号：</td>
                <td width="15%">${headlist.platenum }</td>
                <td width="10%">品牌：</td>
                <td width="15%">${headlist.brandName }</td>
                <td width="10%">排量</td>
                <td width="10%">${headlist.displacement }</td>
                <td width="10%">车主姓名</td>
                <td width="20%">${headlist.customer_name }</td>
            </tr>
            </c:forEach>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="6%">序号</td>
                <td width="9%">配件编号</td>
                <td width="9%">配件名称</td>
                <td width="9%">配件品牌</td>
                <td width="9%">配件规格</td>
                <td width="9%">数量</td>
                <td width="9%">最小单位</td>
                <td width="9%">销售单价</td>
                <td width="9%">金额</td>
                <td width="12%">当前库存</td>
            </tr>
        </table>
        <c:forEach items="${list }"  var="list" varStatus="s">
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="6%">${s.count }</td>
                <td width="9%">${list.goods_code }</td>
                <td width="9%">${list.goods_name }</td>
                <td width="9%">${list.goods_brand }</td>
                <td width="9%">${list.standard }</td>
                <td width="9%">${list.num }</td>
                <td width="9%">${list.unit }</td>
                <td width="9%">${list.order_price }</td>
                <td width="9%">${list.order_money }</td>
                <td width="12%">${list.stock_now }</td>
            </tr>
        </table>
        </c:forEach>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="5%">打印时间</td>
                <td width="15%">${fn:substring(printDate,0,19) }</td>
                <td width="8%">&nbsp;</td>
                <td width="5%">&nbsp;</td>
                <td width="6%">&nbsp;</td>
                <td width="15%">&nbsp;</td>
                <td width="5%">总数</td>
                <td width="8%">${ckcount }</td>
                <td width="8%">应收总金额</td>
                <td width="8%"><fmt:formatNumber value="${moneycount }" pattern="#0.00"/></td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0" style="border-bottom:0;">
            <c:forEach items="${list }" var="footlist" begin="0" end="0">
            <tr>
                <td width="5%">调拨人：</td>
                <td width="20%">${footlist.emp_name }</td>
                <td width="5%">出库人：</td>
                <td width="20%">${footlist.uName }</td>
                <td width="5%">核料员：</td>
                <td width="20%"></td>
                <td width="5%">验收人：</td>
                <td width="20%"></td>
            </tr>
            </c:forEach>
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
