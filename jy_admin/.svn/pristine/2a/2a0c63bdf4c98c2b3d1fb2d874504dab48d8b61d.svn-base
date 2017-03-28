<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="container">
	<div id="navTab" class="tabsPage">
		<div class="tabsPageHeader">
			<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
				<ul class="navTab-tab">
					<li tabid="main" class="main">
						<a href="javascript:;" title="我的主页"><span><span class="home_icon">我的主页</span></span></a>
					</li>
				</ul>
			</div>
			<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
			<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
			<div class="tabsMore">more</div>
		</div>
		<ul class="tabsMoreList">
			<li><a href="javascript:;">我的主页</a></li>
		</ul>
		<div class="navTab-panel tabsPageContent layoutBox">
			<div class="page unitBox">
				<div class="pageFormContent" layoutH="80" style="margin-right:27px">
					<div class="panel" defH="100">
						<h1>个人信息</h1>
						<div>
							<table class="list" width="100%">
								<tbody>
									<tr>
										<td>您好：${fn:length(sysUser.infoEmp.empName) > 0 ? sysUser.infoEmp.empName : sysUser.username}</td>
									</tr>
									<tr>
										<td>所在部门：${sysUser.infoEmp.infoDept.name}</td>
									</tr>
									<tr>
										<td>您的角色：
											<c:forEach items="${sysUser.sysRoles}" var="sysRole">
												【${sysRole.roleName}】
											</c:forEach>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<br>
					<div class="panel" defH="190">
						<h1>系统信息</h1>
						<div>
							<table class="list" width="100%">
								<tbody>
									<tr>
										<td>操作系统版本：${props['os.name']} - ${props['os.version']}</td>
									</tr>
									<tr>
										<td>操作系统类型：${props['os.arch']} ${props['sun.arch.data.model']}位</td>
									</tr>
									<tr>
										<td>用户、目录、临时目录：${props['user.name']}, ${props['user.dir']}, ${props['java.io.tmpdir']}</td>
									</tr>
									<tr>
										<td>JAVA运行环境：${props['java.runtime.name']} ${props['java.runtime.version']}</td>
									</tr>
									<tr>
										<td>JAVA虚拟机：${props['java.vm.name']} ${props['java.vm.version']}</td>
									</tr>
									<tr>
										<td>运行环境：<%=application.getServerInfo() %></td>
									</tr>
									<tr>
										<td>服务器域名 - IP：<%=basePath%> - <%=request.getRemoteAddr() %></td>
									</tr>
									<tr>
										<td>已用内存：<span style="color:#0078ff;"><fmt:formatNumber value="${(usedMemory/1024/1024)}" pattern="#0.00"/>MB</span>&nbsp;&nbsp;&nbsp;&nbsp;剩余内存：<span style="color:#ff8400;"><fmt:formatNumber value="${(useableMemory/1024/1024)}" pattern="#0.00"/>MB </span>&nbsp;&nbsp;&nbsp;&nbsp;最大内存：<span style="color:#00ac41;"><fmt:formatNumber value="${(maxMemory/1024/1024)}" pattern="#0.00"/>MB</span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>