<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/printcss/table.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jiayue/lib/jquery/jquery.js"></script>
<title>打印结算单</title>
</head>

<body>
	<div class="table-box">
    	<table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td class="title">西安嘉悦汽车维修服务有限公司</td>
                <td class="title" style="font-size:24px;">结算单</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="8%">结算单编号</td>
                <td width="10%">${customerEntrust.settlement}</td>
                <td width="10%">服务委托书编号</td>
                <td width="10%">${customerEntrust.cusEnstrustNum}</td>
                <td width="8%">服务类型</td>
                <td width="8%">维修</td>
                <td width="8%">客户名称</td>
                <td width="8%">${customerEntrust.carCusMap.infoCustome.customerName}</td>
                <td width="8%">客户电话</td>
                <td width="8%">${customerEntrust.carCusMap.infoCustome.tel}</td>
            </tr>
            <tr>
                <td>服务位置</td>
                <td>快修一号店</td>
                <td>车牌号</td>
                <td>${customerEntrust.carCusMap.infoCar.platenum}</td>
                <td>车型</td>
                <td>${customerEntrust.carCusMap.infoCar.brandName}</td>
                <td>车主姓名</td>
                <td>${customerEntrust.carCusMap.infoCar.infCustomer.customerName}</td>
                <td>状态</td>
                <td>
                    <c:if test="${customerEntrust.cusEntats==1}">结算(封单)</c:if>
                    <c:if test="${customerEntrust.cusEntats==1}">挂账</c:if>
                    <c:if test="${customerEntrust.cusEntats==1}">已结清</c:if>
                </td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td style="font-size:18px; font-weight:700; text-align:left;" width="7%">服务项目</td>
           	</tr>
            <tr>
                <td width="7%">序号</td>
                <td width="20%">维修类型</td>
                <td width="20%">项目编号</td>
                <td width="20%">项目名称</td>
                <td width="20%">工时费用</td>
            </tr>
            <c:forEach items="${customerEntrust.customerEntrustSubs}" var="sub" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>
                    <td>${sub.serviceitem.servicetype.servicename}</td>
                    <td>${sub.serviceitem.itemcode}</td>
                    <td>${sub.serviceitem.itemname}</td>
                    <td>${sub.serviceitem.workhourmoney}</td>
                </tr>
            </c:forEach>
           <tr class="blod">
            	<td>&nbsp;</td>
              	<td>&nbsp;</td>
               	<td>&nbsp;</td>
            	<td>订单累计工时费</td>
				<td>${amout.workHourAmout}</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td style="font-size:18px; font-weight:700; text-align:left;" colspan="9">所需配件</td>
           	</tr>
            <tr>
                <td width="4%">序号</td>
               <!--  <td width="8%">维修类型</td> -->
                <td width="6%">配件编号</td>
                <td width="21%">配件名称</td>
                <td width="6%">单价</td>
                <td width="4%">数量</td>
                <td width="6%">最小单位</td>
                <td width="8%">配件品牌</td>
                <td width="7%">配件规格</td>
                <!--<td width="8%">折扣系数</td>
                 <td width="6%">积分</td> -->
                <td width="8%">总价(元)</td>
               <!--  <td width="30%">折扣后总价(元)</td> -->
            </tr>
             <c:forEach items="${goods}" var="good" varStatus="s">
                 <tr>
                    <td width="4%">${s.index+1}</td>
                   <!--  <td width="8%">二次保养</td> -->
                    <td width="6%">${good.goodNO}</td>
                    <td width="21%">${good.goodName}</td>
                    <td width="6%">${good.price}</td>
                    <td width="4%">${good.num}</td>
                    <td width="6%">${good.unit}</td>
                    <td width="8%">${good.goodBrand}</td>
                    <td width="7%">${good.standard}</td>
                    <td width="8%">${good.sumPrice}</td>
                </tr>
             </c:forEach>
            
            <tr class="blod">
            	<td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>总配件费</td>
                <td><c:if test="${empty cumPrice}">0.00</c:if> <c:if test="${!empty cumPrice}">${cumPrice}</c:if> </td>
            </tr>
            <tr>
            	<td style="font-weight:600;">总计</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="20%">订单服务项目数</td>
                <td width="20%">订单累计工时费</td>
                <td width="15%">配件材料费</td>
                <td width="10%">总费用</td>
                <td width="12%">折扣系数</td>
                <td width="23%">应收费用（折扣后总额）</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="20%">${customerEntrust.customerEntrustSubs.size()}</td>
                <td width="20%">${amout.workHourAmout}</td>
                <td width="15%">${cumPrice}</td>
                <td width="10%">${amout.sumAmout}</td>
                <td width="12%">${amout.discount}</td>
                <td width="23%">${amout.payAmout}</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="7%">打印时间</td>
                <td width="15%">${time}</td>
                <td width="30%">&nbsp;</td>
                <td width="8%">确认收款金额</td>
                 <td width="10%">${customerEntrust.reallymoney}</td>
                <td width="7%">收款时间</td>
                <td width="15%"><fmt:formatDate value="${order.opertime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0" style="border-bottom:0;">
            <tr>
                <td width="7%">&nbsp;</td>
                <td width="7%">&nbsp;</td>
                <td width="7%">&nbsp;</td>
                <td width="7%">&nbsp;</td>
                <td width="50%">&nbsp;</td>
                <td width="7%">客户签字：</td>
                <td width="13%"></td>
            </tr>
            <tr>
                <td width="7%">操作人：</td>
                <td width="7%">${user.infoEmp.empName}</td>
                <td width="7%">收款人：</td>
                <td width="7%">${customerEntrust.accountman.empName}</td>
                <td width="50%">&nbsp;</td>
                <td width="7%">&nbsp;</td>
                <td width="13%">&nbsp;</td>
            </tr>
            <tr height="60"></tr>
            <tr>
                <td align="center" colspan="8">
                	<input id="printBtn" type="button" value="打印" class="button" />
                </td>
            </tr>
        </table>
    </div>
    <script type="text/javascript">
        $(function()
        {
            $("#printBtn").click(function(event) {
                window.print();
            });
        });
    </script>
</body>
</html>
