<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>其他信息</title>
</head>
	<body>
		<table width="100%">
        	<tbody>
            	<tr>
                	<td width="35%" valign="top">
                    	<h5 class="margin-left-5 fontWeight">外宾登记信息</h5>
                    	<div class="splitDiv margin-top-5">
                        	<dl class="inputDiv2 margin-top-none margin-bottom-20">
								<dt>英文名</dt>
								<dd>
									<input class="input" name="" type="text" placeholder="" id="otherguestinfo_otherNamee" value="${guestdoc.gstNamee}">
								</dd>
								<dt>性别</dt>
								<dd>
									<select class="select widthB99" id="otherguestinfo_otherSex" name="otherSex">
										<c:forEach items="${genderlist}" var="hcode">
											<option value="${hcode.codeId}" <c:if test="${guestdoc.sex==hcode.codeId}">selected</c:if> >${hcode.codeNamec}</option>
										</c:forEach>
									</select>
								</dd>
								<dt>国籍</dt>
								<dd>
									<select class="select widthB99" id="otherguestinfo_otherCountry" name="ntId">
										<c:forEach items="${countrylist}" var="hcode">
											<option value="${hcode.codeId}" <c:if test="${guestdoc.ntId==hcode.codeId}">selected</c:if>>${hcode.codeNamec}</option>
										</c:forEach>
									</select>
								</dd>
								 <dt>证件类型</dt>
		                        <dd>
		                            <select class="select widthB99" id="otherguestinfo_otherCrdkindId" name="otherCrdkindId">
		                            	<c:forEach items="${crdlist}" var="hcode">
											<option value="${hcode.codeId}" <c:if test="${guestdoc.crdkindId==hcode.codeId}">selected</c:if>>${hcode.codeNamec}</option>
										</c:forEach>
		                            </select>
		                        </dd>
		                        <dt>证件号码</dt>
		                        <dd>
		                            <input class="input" name="" id="otherguestinfo_otherCrdId" type="text" value="${guestdoc.crdId}">
		                        </dd>
		        
								<dt>签证类型</dt>
								<dd>
									<select class="select widthB99" id="otherguestinfo_visakindId" name="visakindId">
									 <option value=""></option>
										<c:forEach items="${visaTypes}" var="hcode">
											<option value="${hcode.codeId}" <c:if test="${entity.visakindId==hcode.codeId}">selected</c:if> >${hcode.codeNamec}</option>
										</c:forEach>
									</select>
								</dd>
								<dt>签证号码</dt>
								<dd>
									<input class="input" id="otherguestinfo_visaId" name="visaId" type="text" value="${entity.visaId}" >
								</dd>
								<dt>签发机关</dt>
								<dd>
									<select class="select widthB99" id="otherguestinfo_depart" name="depart">
									 <option value=""></option>
										<c:forEach items="${visaDepartment}" var="hcode">
											<option value="${hcode.codeId}" <c:if test="${entity.depart==hcode.codeId}">selected</c:if>>${hcode.codeNamec}</option>
										</c:forEach>
									</select>
								</dd>
								<dt>有效日期</dt>
								<dd> 
									<input class="input" name="crdVld" type="text" placeholder="" id="otherguestinfo_crdVld" value="${entity.crdVld}">
								</dd>
								<dt>入境日期</dt>
								<dd>
									<input class="input" name="inDate" type="text" placeholder="" id="otherguestinfo_inDate"  value="${entity.inDate}">
								</dd>
								<dt>入境口岸</dt>
								<dd>
									<select class="select widthB99" id="otherguestinfo_inPort" name="inPort">
									 <option value=""></option>
										<c:forEach items="${partOfEntry}" var="hcode">
											<option value="${hcode.codeId}" <c:if test="${entity.inPort==hcode.codeId}">selected</c:if>>${hcode.codeNamec}</option>
										</c:forEach>
									</select>
								</dd>
								<dt>接待单位</dt>
								<dd>
									<input class="input" name="" type="text" placeholder="">
								</dd>
								<div class="clearBoth"></div>
							</dl>
							<div class="clearBoth"></div>
						</div>
					</td>
					<td valign="top">
						<!--外宾登记信息Right-->
						<h5 class="margin-left-5 fontWeight">客人签名</h5>
						<div class="textarea margin-top-5 height200"></div>
						<table width="100%" class="margin-top-30 gry_9">
		                    <tbody>
			                    <tr>
			                        <td width="25%" align="right">登记人</td>
			                        <td width="25%">
			                        	<input class="input" name="chkOperName" type="text" disabled="disabled" value="${entity.chkOperid}">
			                        </td>
			                        <td width="18%" align="right">操作时间</td>
			                        <td width="32%"><input class="input" name="chkTime" type="text" disabled="disabled" value="${fn:substring(entity.rechkTime,0,16)}"></td>
			                    </tr>
			                    <tr>
			                        <td align="right">最后修改人</td>
			                        <td>
			                        	<input class="input" name="lastOperName" type="text" disabled="disabled" value="${entity.lastOper}">
		                        	</td>
			                        <td align="right">操作时间</td>
			                        <td><input class="input" name="lastTime" type="text" disabled="disabled" value="${fn:substring(entity.lastTime,0,16)}"></td>
			                    </tr>
			                    <tr>
			                        <td align="right">退房人</td>
			                        <td><input class="input" name="outOperName" type="text" disabled="disabled" value="${entity.outOper}" ></td>
			                        <td align="right">操作时间</td>
			                        <td><input class="input" name="outTime" type="text" disabled="disabled"  value="${entity.outTime}"></td>
			                    </tr>
			                    <tr>
			                        <td align="right">恢复入住人</td>
			                        <td><input class="input" name="rechkOperName" type="text" disabled="disabled" value="${entity.rechkOperid}"></td>
			                        <td align="right">操作时间</td>
			                        <td><input class="input" name="rechkTime" type="text" disabled="disabled" value="${entity.rechkTime}"></td>
			                    </tr>
			                    <tr>
			                        <td align="right">最后账目处理人</td>
			                        <td><input class="input" name="lastCashierName" type="text" disabled="disabled" value="${entity.lastCashier}" ></td>
			                        <td align="right">操作时间</td>
			                        <td><input class="input" name="cashierTime" type="text" disabled="disabled" value="${entity.cashierTime}" ></td>
			                    </tr>
		                	</tbody>
		            	</table> <!--外宾登记信息Right  END-->
		        	</td>
				</tr>
			</tbody>
		</table>
		<div class="alertRight clearBoth margin-top-30">
			<a id="otherguestinfo_quti_btn" class="buttonLikeA floatR margin-right-10" href="javascript:;" >关闭</a>
		</div>
		<div class="clearBoth"></div>
		<script src="${pageContext.request.contextPath}/js/lib/seajs/sea.js"></script>
		<script>
			seajs.use("${pageContext.request.contextPath}/js/guestdetail/otherGuestInfo.js?number=" + Math.random());
		</script>
	</body>
</html>