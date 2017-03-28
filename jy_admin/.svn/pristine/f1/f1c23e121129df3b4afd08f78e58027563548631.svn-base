<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/infosub/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" value="${infosub.id }" name="id"/>
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="name" value="${infosub.name }" class="required" maxlength="20" size="50"/>
			</div>
			<div class="unit">
				<label>编码：</label>
				<input type="text" name="code" value="${infosub.code }" class="required" maxlength="25" size="30" remote="${pageContext.request.contextPath}/infosub/check.ajax?id=${infosub.id}" />
			</div>
			<div class="unit">
				<label>简称：</label>
				<input type="text" name="shortName" value="${infosub.shortName }" maxlength="40" size="30"/>
			</div>
			<div class="unit">
				<label>法人代表：</label>
				<input type="text" name="legalPerson"  value="${infosub.legalPerson }"  class="required" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>邮政编码：</label>
				<input type="text" name="zipCode" value="${infosub.zipCode }" class="digits" maxlength="9" size="30"  />
			</div>
			<div class="unit">
				<label>联系电话：</label>
				<input type="text" name="phone" value="${infosub.phone }" class="required" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>联系地址：</label>
				<input type="text" name="address" value="${infosub.address }" class="required" maxlength="50" size="30"  />
			</div>
			<div class="unit">
				<label>传真号码：</label>
				<input type="text" name="faxNum" value="${infosub.faxNum }" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>联系人：</label>
				<input type="text" name="linkMan" value="${infosub.linkMan }" class="required" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>开户银行：</label>
				<input type="text" name="bank"  value="${infosub.bank }" class="" maxlength="30" size="30"  />
			</div>
			<div class="unit">
				<label>银行账号：</label>
				<input type="text" name="bankNo"  value="${infosub.bankNo }"  class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>税号：</label>
				<input type="text" name="taxNo" value="${infosub.taxNo }"  class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>财务编码：</label>
				<input type="text" name="financeNo" value="${infosub.financeNo }" class="" maxlength="20" size="30"  />
			</div>
			<div class="unit">
				<label>注册资本：</label>
				<input type="text" name="regCapital" value="${infosub.regCapital }"  class="required" maxlength="20" size="30"  />&nbsp; 万元
			</div>
			<div class="unit">
				<label>评级：</label>
				<input type="text" name="grade" value="${infosub.grade }" class="required" maxlength="10" size="30"  />
			</div>
			<div class="unit">
				<label>经营范围：</label>
				<textarea name="businessScope" cols="60" maxlength="200" rows="8">${infosub.businessScope }</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="infosub.update">
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
					</li>
				</tag:auth>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
