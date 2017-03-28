<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath }/resource/printcss/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath }/resource/printcss/content.css" />
<title>调拨入库单</title>
</head>

<body>
<div class="content border_999">
    <p class="title">
    西安嘉悦汽车维修服务有限公司
    </p>
    <p class="title_lx border_bottom999">
    调拨入库单
  </p>
	<div class="div_1 border_bottom999">
        <table border="0" cellpadding="0" cellspacing="0" class="table_1">
                <c:forEach items="${list }"  var="headlist" begin="0" end="0">
                <tr>
                    <td width="7%">入库单号：</td>
                    <td width="17%">${headlist.b_no }</td>
                    <td width="7%">调拨单号：</td>
                    <td width="16%">${headlist.allocation_no }</td>
                    <td width="9%">入库日期：</td>
                    <td width="22%">${fn:substring(headlist.bill_date,0,19) }</td>
                    <td width="7%">执行部门：</td>
                    <td width="15%">${headlist.dept_name }</td>
                </tr>
                <tr>
                    <td>备注：</td>
                    <td colspan="7">${headlist.memo }</td>
                </tr>
                </c:forEach>
            </table>
    </div>
    <div class="div_1 border_bottom999">
        <table border="0" cellpadding="0" cellspacing="0" class="table_2">
                <tr class="bold_2">
                    <td>序号</td>
                    <td>配件编号</td>
                    <td>配件名称</td>
                    <td>配件类别</td>
                    <td>配件品牌</td>
                    <td>配件规格</td>
                    <td>调拨数量</td>
                    <td>最小单位</td>
                    <td>销售价</td>
                    <td>调拨来源</td>
                    <td>当前库存</td>
                </tr>
                <c:forEach items="${list }"  var="list" varStatus="s">
                <tr>
                    <td>${s.count }</td>
                    <td>${list.goods_code }</td>
                    <td>${list.goods_name }</td>
                    <td>${list.type_name }</td>
                    <td>${list.goods_brand }</td>
                  <td>${list.standard }</td>
                  <td>${list.num }</td>
                  <td>${list.unit }</td>
                  <td>${list.order_price }</td>
                  <td>${list.tag_dep_name }</td>
                  <td>${list.stock_now }</td>
                </tr>
                </c:forEach>
            </table>
    </div>
    <div class="div_1 border_bottom999">
        <table border="0" cellpadding="0" cellspacing="0" class="table_1">
                <tr>
                    <td width="7%">打印时间：</td>
                    <td width="14%">${fn:substring(printDate,0,19) }</td>
                    <td width="7%">入库总数：</td>
                    <td width="10%">${ckcount }</td>
                    <td width="7%">&nbsp;</td>
                    <td width="9%">&nbsp;</td>
                    <td width="8%">&nbsp;</td>
                    <td width="10%">&nbsp;</td>
                    <td width="5%">&nbsp;</td>
                    <td width="7%">&nbsp;</td>
                    <td width="7%">&nbsp;</td>
                    <td width="9%">&nbsp;</td>
                </tr>
            </table>
    </div>
    <div class="div_1">
    	<table border="0" cellpadding="0" cellspacing="0" class="table_3">
            <c:forEach items="${list }" var="footlist" begin="0" end="0">
            <tr>
            	<td width="7%">调拨人:</td>
                <td width="14%">${footlist.emp_name }</td>
                <td width="7%">入库人：</td>
                <td width="30%">${footlist.uName }</td>
                <td width="6%">核料员：</td>
                <td width="13%"></td>
                <td width="5%">验收人：</td>
                <td width="18%"></td>
            </tr>
            </c:forEach>
            <tr>
            	<td colspan="8" height="30"></td>
            </tr>
            <tr>
                <td colspan="8" align="center">
                	<input type="button" class="card_dy" value="打印" onclick="goPrint()" />
                </td>
            </tr>
        
        </table>
    </div>
</div>
</body>
</html>
<script>
	function goPrint(){
		window.print();
	}
</script>
