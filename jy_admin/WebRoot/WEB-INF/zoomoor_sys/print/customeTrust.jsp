<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/resource/printcss/table.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jiayue/lib/jquery/jquery.js"></script>
<title>打印服务订单书</title>
</head>

<body>
	<div class="table-box">
    	<table class="table" cellpadding="0" cellspacing="0" style="border-bottom:0;">
        	<tr>
            	<td class="title">维修服务委托书</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td style="font-size:18px; font-weight:700; text-align:left;" width="7%">托修方信息</td>
           	</tr>
            <tr>
                <td width="8%">客户名称</td>
                <td width="30%">${customerEntrust.carCusMap.infoCustome.customerName}</td>
                <td width="10%">客户电话</td>
                <td width="20%">${customerEntrust.carCusMap.infoCustome.tel}</td>
            </tr>
            <tr>
                <td width="8%">车牌号</td>
                <td width="30%">${customerEntrust.carCusMap.infoCar.platenum}</td>
                <td width="10%">车主姓名</td>
                <td width="20%">${customerEntrust.carCusMap.infoCar.infCustomer.customerName}</td>
                <td width="10%">车主电话</td>
                <td width="20%">${customerEntrust.carCusMap.infoCar.infCustomer.tel}</td>
            </tr>
            <tr>
                <td width="8%">VIN</td>
                <td width="30%">${customerEntrust.carCusMap.infoCar.vinnum}</td>
                <td width="10%">发动机型号</td>
                <td width="20%">${customerEntrust.carCusMap.infoCar.framnum}</td>
                <td width="10%">车辆描述</td>
                <td width="20%">${customerEntrust.carCusMap.infoCar.remark}</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td style="font-size:18px; font-weight:700; text-align:left;" width="7%">承修方信息</td>
           	</tr>
             <c:if test="${customerEntrust.fixplace==0}" >
                <tr>
                    <td width="8%">经销商名称</td>
                    <td width="30%">
                        ${customerEntrust.infoDept.name} 
                    </td>
                    <td width="10%">联系电话</td>
                    <td width="20%">${customerEntrust.infoDept.infoDeptSub.phone} </td>
                </tr>
                <tr>
                    <td width="8%">地址/编号</td>
                    <td width="30%">${customerEntrust.infoDept.infoDeptSub.address}</td>
                    <td width="10%">联系邮箱</td>
                    <td width="20%">${customerEntrust.infoDept.infoDeptSub.email}</td>
                </tr>
            </c:if>
            <c:if test="customerEntrust.fixplace!=0" >
                <tr>
                    <td width="8%">经销商名称</td>
                    <td width="30%">
                        ${fixAddr.address} 
                    </td>
                    <td width="10%">联系电话</td>
                    <td width="20%">${fixAddr.telephone} </td>
                </tr>
                <tr>
                    <td width="8%">地址/编号</td>
                    <td width="30%">${fixAddr.detailedaddr}</td>
                    <td width="10%">联系邮箱</td>
                    <td width="20%">${fixAddr.email}</td>
                </tr>
            </c:if>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td style="font-size:18px; font-weight:700; text-align:left;" width="7%">服务委托书信息</td>
           	</tr>
            <tr>
                <td width="6%">服务委托书编号</td>
                <td width="7%">${customerEntrust.cusEnstrustNum}</td>
                <td width="7%">服务接待人</td>
                <td width="7%">${infoEmp.empName}</td>
                <td width="7%">开单时间</td>
                <td width="7%"><fmt:formatDate value="${customerEntrust.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td width="7%">预计交车时间</td>
                <td width="9%"><fmt:formatDate value="${customerEntrust.estimatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            <tr>
                <td width="6%">服务维修点</td>
                <td width="7%">${fixAddr.address}</td>
                <td width="7%">上次维修时间</td>
                <td width="7%">2015-3-10</td>
                <td width="7%">维修类型</td>
                <td width="7%">保养</td>
                <td width="7%">&nbsp;</td>
                <td width="9%">&nbsp;</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0" style="padding:0">
            <tr>
                <td width="7%" style="border-right:1px solid #333; padding:0 2%;" height="150">用户故障描述</td>
                <td width="43%" style="border-right:1px solid #333;" height="150">&nbsp;</td>
                <td width="7%" style="border-right:1px solid #333;  padding:0 2%;" height="150">故障检查报告</td>
                <td width="43%" height="150">&nbsp;</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="7%">备注</td>
                <td width="93%">&nbsp;</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td style="font-size:18px; font-weight:700; text-align:left;" width="7%">服务项目</td>
           	</tr>
            <tr>
                <td width="6%">序号</td>
                <td width="7%">维修类型</td>
                <td width="7%">项目编号</td>
                <td width="7%">项目名称</td>
                <td width="7%">工时费用</td>
            </tr>
            <c:forEach items="${customerEntrust.customerEntrustSubs}" var="sub" varStatus="s">
                <tr>
                    <td width="6%">${s.index + 1}</td>
                    <td width="7%">${sub.serviceitem.servicetype.servicename}</td>
                    <td width="7%">${sub.serviceitem.itemcode}</td>
                    <td width="7%">${sub.serviceitem.itemname}</td>
                    <td width="7%"><fmt:formatNumber value="${sub.serviceitem.workhourmoney}" type="currency"/></td>
                </tr>
            </c:forEach>
            <tr class="blod">
                <td width="6%">&nbsp;</td>
                <td width="7%">&nbsp;</td>
                <td width="7%">&nbsp;</td>
                <td width="7%" style="font-size:22px;">预计总费用</td>
                <td width="7%" style="font-size:22px;">${amout.sumAmout}&nbsp;</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0" style="border-bottom:0;">
        	<tr>
            	<td style="font-size:18px; font-weight:700; text-align:left;" width="7%">友情提示</td>
           	</tr>
            <tr>
                <td width="100%">1.随车贵重物品客户自行保管，如有遗失，承修方不承担任何责任。</td>
            </tr>
            <tr>
                <td width="100%">2.托修方同意承修方依据本维修服务委托书之维修项目进行诊断或维修，同意在交车前支付相关工时、零件、税务等费用。相关费用按照实际发生额进行结算，谢绝欠款。</td>
            </tr>
            <tr>
                <td width="100%">3.预计交车时间仅供参考，如需延长，承修方将另行通知(口头通知，电话通知等)。</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0"  style="border-bottom:0;">
            <tr>
                <td width="6%">打印时间</td>
                <td width="7%">${time}</td>
                <td width="7%">开单人：</td>
                <td width="7%">${user.infoEmp.empName}</td>
                <td width="7%">服务顾问：</td>
                <td width="7%">${infoEmp.empName}</td>
                <td width="7%">托修方：</td>
                <td width="9%">&nbsp;</td>
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


