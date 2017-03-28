<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContent.request.contextPath }/resource/printcss/table.css" />
<title>打印采购单</title>
</head>
<body>
	<div class="table-box">
    	<table class="table" cellpadding="0" cellspacing="0">
        	<tr>
            	<td class="title">西安嘉悦汽车维修服务有限公司</td>
                <td class="title" style="font-size:24px;">采购单</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="8%">采购单号</td>
                <td width="30%">${orderPur.purOrderNo }</td>
                <td width="10%">供货商名称</td>
                <td width="20%">${orderPur.infoSup.name }</td>
                <td width="10%">开单时间</td>
                <td width="20%">${fn:substring(orderPur.purOrderDate,0,19) }</td>
            </tr>
            <tr>
            	
                <td width="8%">审核状态</td>
                <td width="30%">
                <c:forEach items="${auditList }" var="audit">
	                <c:if test="${audit.auditType==1 }">采购审核</c:if>
					<c:if test="${audit.auditType==2 }">财务审核</c:if>
					<c:if test="${audit.auditType==3 }">总经理审核</c:if>
					<c:if test="${audit.status==1 }"> 通过</c:if>
					<c:if test="${audit.status==2 }"> 不通过</c:if>
					<c:if test="${audit.status==0 }"> 未审核</c:if>
				</c:forEach>
				</td>
				<td width="10%"></td>
                <td width="20%"></td>
                <td width="10%">&nbsp;</td>
                <td width="20%">&nbsp;</td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="6%">序号</td>
                <td width="7%">配件编号</td>
                <td width="7%">配件名称</td>
                <td width="7%">配件类别</td>
                <td width="7%">配件品牌</td>
                <td width="7%">配件规格</td>
                <td width="7%">采购数量</td>
                <td width="9%">实际采购数</td>
                <td width="7%">最小单位</td>
                <td width="7%">含税单价</td>
                <td width="7%">含税金额</td>
                <td width="9%">不含税单价</td>
                <td width="12%">不含税金额</td>
            </tr>
        </table>
       <c:forEach items="${orderList }" var="orderList" varStatus="s">
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="6%">${s.count }</td>
                <td width="7%">${orderList.infoGoods.goodsCode}</td>
                <td width="7%">${orderList.infoGoods.name}</td>
                <td width="7%">${orderList.infoGoods.infoGoodsType.typeName}</td>
                <td width="7%">${orderList.infoGoods.goodsBrand }</td>
                <td width="7%">${orderList.infoGoods.standard }</td>
                <td width="7%">${orderList.orderNum }</td>
                <td width="9%"></td>
                <td width="7%">${orderList.unitName }</td>
                <td width="7%"><fmt:formatNumber value="${orderList.taxtRatePrice }" pattern="#0.00"/></td>
                <td width="7%"><fmt:formatNumber value="${orderList.ytaxRateCount }" pattern="#0.00"/></td>
                <td width="9%">${orderList.orderPrice }</td>
                <td width="12%"><fmt:formatNumber value="${orderList.nTaxRateCount }" pattern="#0.00"/></td>
            </tr>
        </table>
       </c:forEach>
        <table class="table" cellpadding="0" cellspacing="0">
            <tr>
                <td width="5%">打印时间</td>
                <td width="15%">${fn:substring(printDate,0,19) }</td>
                <td width="8%"></td>
                <td width="5%"></td>
                <td width="6%">含税金额：</td>
                <td width="8%" id="count_hs"><fmt:formatNumber value="${ytaxCount }" pattern="#0.00"/></td>
                <td width="8%">不含税金额</td>
                <td width="8%" id="count_bhs"><fmt:formatNumber value="${ntaxCount }" pattern="#0.00"/></td>
                <td width="5%">税额</td>
                <td width="8%" id="count_se"><fmt:formatNumber value="${seCount }" pattern="#0.00"/></td>
            </tr>
        </table>
        <table class="table" cellpadding="0" cellspacing="0" style="border-bottom:0;">
            <tr>
                <td width="5%">操作人：</td>
                <td width="20%">${sysUser.infoEmp.empName }</td>
                <td width="75%">&nbsp;</td>
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

