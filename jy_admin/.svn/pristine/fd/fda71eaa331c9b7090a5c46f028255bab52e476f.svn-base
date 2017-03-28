<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent">
		<input value="${summary.summaryId }" name="summaryId" id="summaryId" type="hidden"/>
		<input value="${bNo }" name="bNo" id="bNo" type="hidden"/>
		<input value="${deptId }" name="deptId" id="deptId" type="hidden"/>
		<div class="pageFormContent" layoutH="60">
			<div class="unit">
				<label>业务摘要:</label>
					${summary.name }
			</div>
			<div class="unit">
				<label>业务单号:</label>
					${bNo }
			</div>
		</div>
		<div class="formBar">
			<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="printBySummaryId();">打印</button></div></div>
					</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" id="clo" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
</div>
<script type="text/javascript">
	function printBySummaryId(){
		var queryDeptId=$("#deptId").val();
		var summaryId=$("#summaryId").val();
		var queryBno=$("#bNo").val();
		var url="/depot/print/"+queryDeptId+"/"+summaryId+"/"+queryBno;
		window.open(url,"_blank");
		$("#clo").click();
	}
</script>

