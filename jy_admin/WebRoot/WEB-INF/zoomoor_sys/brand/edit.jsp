<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
	<form method="post" action="${pageContext.request.contextPath}/brand/update.do" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="id" value="${brand.id}">
		<input id="img" type="hidden" name="url" value="${brand.url }" >
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>权限编码：</label>
				<input type="text" name="name" class="required" maxlength="50" size="30" remote="${pageContext.request.contextPath}/brand/check.ajax?groupId=${brand.id}" value="${brand.name}" />
			</div>
			<div class="unit">
				<label>排序顺序：</label>
				<input type="text" name="sort" class="required digits"   maxlength="4" size="30" value="${brand.sort}"/>
			</div>
			<div class="unit">
				<label>品牌图：</label>
				<c:if test="${ empty brand.url }">
					<img id="preview" src="${pageContext.request.contextPath}/template/no_picture.gif" width="150px" height="150px">
				</c:if>
				<c:if test="${ !empty brand.url }">
					<img id="preview" src="${brand.url }" width="150px" height="150px">
				</c:if>
				<input id="img_upload" type="file" >
			</div>
		</div>
		<div class="formBar">
			<ul>
				<tag:auth code="brand.update">
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
	function dialogAjaxDone2(json){
	$("#backId_brand").val(json.backId);
	DWZ.ajaxDone(json);
	if (json[DWZ.keys.statusCode] == DWZ.statusCode.ok){
		if (json.navTabId){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
	}
}
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
		$("#img").val(json.url);
	}
</script>