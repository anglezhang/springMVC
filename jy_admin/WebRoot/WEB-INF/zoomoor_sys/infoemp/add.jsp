<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" id="form_d" action="${pageContext.request.contextPath}/emplyeeinf/empsave.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<div class="pageFormContent" layoutH="60">
		<table>
			<tr>
				<td rowspan="4">
					<div class="unit">
						<label>照片：</label>
						<img id="preview" src="${pageContext.request.contextPath}/template/no_picture.gif" width="150px" height="150px">
						<input id="img_upload" type="file" >
						<input id="uploadsrcval" name="url" type="hidden">
						<input name="status" type="hidden" value="1">
					</div>
				</td>
				<td>
					<div class="unit">
						<label>员工编号：</label>
						<input value="" type="text" class="required" name="empCode" maxlength="50" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>姓名：</label>
						<input value="" type="text"  class="required" name="empName" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>身份证号：</label>
						<input value="" type="text"  class="alphanumeric" name="idCard" maxlength="20" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>性别：</label>
						<input type="radio" checked="checked"  name="gender" value="false" /> 女
						<input type="radio"   name="gender" value="true" /> 男
					</div>
				</td>
				<td>
					<div class="unit">
						<label>所在部门：</label>
						<select name="infoDept.deptId" style="width:152px;height:21px">
							<c:forEach items="${deptS}" var="infDept" varStatus="s">
								<option value="${infDept.deptId}">${infDept.name}</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>联系电话：</label>
						<input type="text"  class="phone" name="mobile"  maxlength="20" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>紧急联系人：</label>
						<input value="" type="text"  name="attn" maxlength="50" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>紧急联系人电话：</label>
						<input value="" type="text"  name="attnTel" maxlength="20" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>年龄：</label>
						<input value="" type="text" class="digits" name="age" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>邮箱：</label>
						<input value="" type="text"  name="email" class="email" maxlength="50" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>出生日期：</label>
						<input value="" type="text"   name="birthday" class="date" readonly="readonly" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>籍贯：</label>
						<input value="" type="text"  name="nativePlace" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>民族：</label>
						<input value="" type="text"  name="nation" maxlength="50" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>婚否：</label>
						<input type="radio"   name="ismarry" maxlength="50" checked="checked" size="30"/> 婚
						<input type="radio"   name="ismarry" maxlength="50" size="30"/> 否
					</div>
				</td>
				<td>
					<div class="unit">
						<label>转正日期：</label>
						<input value="" type="text"  name="formalDate" readonly="readonly" class="date" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>职位：</label>
						<select name="position" style="width:152px;height:21px">
							<c:forEach items="${postConfigList}" var="paramconfig" varStatus="s">
								<option value="${paramconfig.name}">${paramconfig.name}</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>合同开始日期：</label>
						<input value="" type="text"  name="lcBDate" class="date" readonly="readonly"   maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>合同结束日期：</label>
						<input value="" type="text"  name="lcEDate" class="date" readonly="readonly" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>入职日期：</label>
						<input value="" type="text"  name="inDate" class="date" readonly="readonly" maxlength="50" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>离职日期：</label>
						<input value="" type="text" name="outDate" class="date" readonly="readonly" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>年假天数：</label>
						<input value="" type="text"  name="holidayNum" class="number" maxlength="50" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>招聘来源：</label>
						<input value="" type="text"   name="recruitSource" maxlength="50" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>驾照类型：</label>
						<select name="drivinglicense.unitId" style="width:152px;height:21px">
							<option value="" selected="selected">无驾照</option>
							<c:forEach items="${drivingConfigList}" var="paramconfig" varStatus="s">
								<option value="${paramconfig.unitId}">${paramconfig.name}</option>
							</c:forEach>
						</select>
					</div>
				</td>
				<td colspan="2">
					<div class="unit">
						<label>专业技能：</label>
						<c:forEach items="${professConfigList}" var="paramconfig" varStatus="status">
							<input type="checkbox" name="professConfigs[${status.index}].paramConfig.unitId" value="${paramconfig.unitId}">	${paramconfig.name}
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="unit">
						<label>参加工作时间：</label>
						<input  name="startJob"  readonly="readonly"  class="date" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>学历：</label>
						<input type="text" name="edu" maxlength="30" size="30"/>
					</div>
				</td>
				<td>
					<div class="unit">
						<label>毕业院校：</label>
						<input type="text" name="school" maxlength="50" size="30"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="unit">
						<label>兴趣特长：</label>
						<input  type="text" name="interest" maxlength="100" size="80"/>
					</div>
				</td>
			</tr>
		</table>
		</div>
	<div class="formBar">
		<ul>
			<tag:auth code="info.emplyee.save">
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
<script type="text/javascript">
	$(document).ready(function(){
		$("#img_upload").uploadify({
			swf: '${pageContext.request.contextPath}/js/dwz/uploadify/scripts/uploadify.swf',
			uploader: '${pageContext.request.contextPath}/upload.do',
			buttonText:'请选择图片',
			fileSizeLimit:'10240KB',
			fileTypeDesc:'*.jpg;*.jpeg;*.gif;*.png;',
			fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;',
			auto:true,
			multi:false,
			width: 100,
			height: 20,
			onUploadSuccess:changeImg,
			onInit: function () { //载入时触发，将flash设置到最小
				$("#img_upload-queue").hide(); 
			}
		});
		$("#img_upload").css("margin-left","150px");
	});
	function changeImg(file, data, response){
		var json = jQuery.parseJSON(data);
		$("#preview").attr("src", json.url);
		$("#uploadsrcval").val(json.url);
	}
</script>