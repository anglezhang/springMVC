<%@ page language="java" pageEncoding="UTF-8"%>
<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<option value="20">20</option>
			<option value="50">50</option>
			<option value="100">100</option>
			<option value="200">200</option>
		</select>
		<span>条，共${pager.totalCount}条</span>
	</div>
	
	<div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
</div>
<script>
	$("select[name='numPerPage']").val('${pager.numPerPage}');
</script>