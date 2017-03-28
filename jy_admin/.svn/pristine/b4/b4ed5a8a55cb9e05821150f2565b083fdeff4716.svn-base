<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" id="form_d" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageFormContent" layoutH="60">
		<input name="empId" type="hidden" value="${infEmp.empId}">
		<c:forEach items="${cfgEmpMaps}" var="cfgEmpMap" varStatus="s">
			<input  type="hidden" cfgEmpMaps value="${cfgEmpMap.paramConfig.unitId}">
		</c:forEach>
		<table>
			<tr>
				<td rowspan="4">
					<div class="unit">
						<label>照片：</label>
						<c:if test="${empty infEmp.url}" >
							<img id="preview" src="${pageContext.request.contextPath}/template/no_picture.gif"  width="150px" height="150px">
						</c:if>
						<c:if test="${!empty infEmp.url}" >
							<img id="preview" src="${infEmp.url}" width="150px" height="150px">
						</c:if>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>员工编号：</label>
						${infEmp.empCode}
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="unit">
						<label>姓名：</label>
						${infEmp.empName}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>身份证号：</label>
						${infEmp.idCard}
					</div>
				</td>
			</tr>
			<tr>
				<td>	
					<div class="unit">
						<label>性别：</label>
						<c:if test="${!infEmp.gender}"> 女 </c:if>   
						<c:if test="${infEmp.gender}"> 男 </c:if>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>所在部门：</label>
						<c:forEach items="${deptS}" var="infDept" varStatus="s">
							 <c:if test="${infEmp.infoDept.deptId==infDept.deptId}" >${infDept.name}</c:if> 
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>联系电话：</label>
						${infEmp.mobile}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>紧急联系人：</label>
						${infEmp.attn}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>紧急联系人电话：</label>
						${infEmp.attnTel}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>年龄：</label>
						${infEmp.age}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>邮箱：</label>
						${infEmp.email}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>出生日期：</label>
						${fn:substring(infEmp.birthday,0,10)}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>籍贯：</label>
						${infEmp.nativePlace}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>民族：</label>
						${infEmp.nation}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>婚否：</label>
						<input type="radio" disabled  name="ismarry" maxlength="50"  <c:if test="${infEmp.ismarry}" >checked="checked" </c:if> size="30"/> 婚
						<input type="radio" disabled  name="ismarry" maxlength="50" <c:if test="${infEmp.ismarry}" >checked="checked" </c:if> size="30"/> 否
					</div>
				</td>
				<td>
					<div class="unit">
						<label>转正日期：</label>
						${fn:substring(infEmp.formalDate,0,10)}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>职位：</label>
						${infEmp.position}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>合同开始日期：</label>
						${fn:substring(infEmp.lcBDate,0,10)}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>合同结束日期：</label>
						${fn:substring(infEmp.lcEDate,0,10)}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>入职日期：</label>
						${fn:substring(infEmp.inDate,0,10)}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>离职日期：</label>
						${fn:substring(infEmp.outDate,0,10)}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>年假天数：</label>
						${infEmp.holidayNum}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>招聘来源：</label>
						${infEmp.recruitSource}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>驾照类型：</label>
						<c:if test="${empty infEmp.drivinglicense.unitId}" >无驾照</c:if> 
						<c:forEach items="${drivingConfigList}" var="paramconfig" varStatus="s">
							<c:if test="${infEmp.drivinglicense.unitId==paramconfig.unitId}" >
								${paramconfig.name}
							</c:if> 
						</c:forEach>
					</div>
				</td>
				<td colspan="2">
					<div class="unit">
						<label>专业技能：</label>
						<c:forEach items="${professConfigList}" var="paramconfig" varStatus="status">
							<input type="checkbox" disabled="disabled" paramConfig name="professConfigs[${status.index}].paramConfig.unitId" value="${paramconfig.unitId}"  >	${paramconfig.name}
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>参加工作时间：</label>
						${fn:substring(infEmp.startJob,0,10)}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>学历：</label>
						${infEmp.edu}
					</div>
				</td>
				<td>
					<div class="unit">
						<label>毕业院校：</label>
						${infEmp.school}
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="unit">
						<label>兴趣特长：</label>
						${infEmp.interest}
					</div>
				</td>
			</tr>
		</table>
		</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
	</form>
</div>
<script type="text/javascript">

	/**
	* 技能复选框选中
	*/
	function setParam()
	{
		var cfgEmpMaps = $("[cfgEmpMaps]");
		var paramConfig = $("[paramConfig]");
		//迭代
		if(cfgEmpMaps.length != 0 )
		{
			for(var i=0;i<cfgEmpMaps.length;i++)
			{
				var obj = $(cfgEmpMaps[i]);
				for(var j=0;j<paramConfig.length;j++)
				{
					var paramObj = $(paramConfig[j]);
					
					if(obj.val()==paramObj.val())
					{
						paramObj.prop("checked",true);
					}
				}
			}
		}
		
	} 
	$(function()
	{
		setParam();
	});
</script>