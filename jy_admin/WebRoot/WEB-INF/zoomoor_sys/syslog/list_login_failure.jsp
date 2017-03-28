<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/syslog/list_login_failure.do">
	<input type="hidden" name="pageNum" value="${pager.pageNum}" />
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<input type="hidden" name="queryUsername" value="${queryUsername}" />
	<input type="hidden" name="queryTitle" value="${queryTitle}" />
	<input type="hidden" name="queryIp" value="${queryIp}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/syslog/list_login_failure.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<%-- <td>
						用户：<input type="text" name="queryUsername" value="${queryUsername}"/>
					</td> --%>
					<td>
						标题：<input type="text" name="queryTitle" value="${queryTitle}"/>
					</td>
					<td>
						IP：<input type="text" name="queryIp" value="${queryIp}"/>
					</td>
					<td>
					 
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<form id="del_loginfailure_form" onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/syslog/delete_login_failure_batch.do" method="post">
			<ul class="toolBar">
				<tag:auth code="sys.log.loginfailure.delete">
					<li><a class="delete" href="${pageContext.request.contextPath}/syslog/delete_login_failure.do" target="selectedTodo" postType="string" title="确定要删除吗?"><span>删除</span></a></li>
				</tag:auth>
				<tag:auth code="sys.log.loginfailure.delete.batch">
					<div style="float: right;">
						<select class="combox" name="days">
							<option value="365">一年前日志</option>
							<option value="90">一季前日志</option>
							<option value="30">一月前日志</option>
							<option value="7">一周前日志</option>
							<option value="0">所有日志</option>
						</select>
						<li><a id="del_loginfailure" class="delete"><span>批量删除</span></a></li>
					</div>
				</tag:auth>
			</ul>
		</form>
	</div> 
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th width="50"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="50">ID</th>
				<!-- <th width="100">用户</th> -->
				<th width="120">时间</th>
				<th width="120">IP</th>
				<th width="120">标题</th>
				<th>内容</th>
				<th width="60">操作选项</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.list}" var="syslog">
					<tr align="center">
						<td><input type="checkbox" name="ids" value="${syslog.logId}"></td>
						<td>${syslog.logId}</td>
						<%-- <td>${syslog.sysUser == null ? "" : syslog.sysUser.username}</td> --%>
						<td><fmt:formatDate value="${syslog.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${syslog.ip}</td>
						<td>${syslog.title}</td>
						<td align="left" title="${syslog.content}">${syslog.content}</td>
						<td>
							<tag:auth code="sys.log.loginfailure.delete">
								<a title="删除" target="ajaxTodo" href="${pageContext.request.contextPath}/syslog/delete_login_failure.do?ids=${syslog.logId}" class="btn btn-delete"></a>
							</tag:auth>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../include/page.jsp" %>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#del_loginfailure").click(function() {
			alertMsg.confirm("确定要删除吗?", {
				okCall: function(){
					$("#del_loginfailure_form").submit();
				}
			});
		});
	});
</script>