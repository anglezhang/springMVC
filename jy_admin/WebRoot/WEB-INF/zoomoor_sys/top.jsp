<%@ page language="java" pageEncoding="UTF-8"%>
<div id="header">
	<div class="headerNav">
		<a class="logo" href="${pageContext.request.contextPath}/index.do">标志</a>
		<ul class="nav">
			<!-- 
			<li id="switchEnvBox"><a href="javascript:">（<span>北京</span>）切换城市</a>
				<ul>
					<li><a href="sidebar_1.html">北京</a></li>
					<li><a href="sidebar_2.html">上海</a></li>
					<li><a href="sidebar_2.html">南京</a></li>
					<li><a href="sidebar_2.html">深圳</a></li>
					<li><a href="sidebar_2.html">广州</a></li>
					<li><a href="sidebar_2.html">天津</a></li>
					<li><a href="sidebar_2.html">杭州</a></li>
				</ul>
			</li>
			<li><a href="https://me.alipay.com/dwzteam" target="_blank">捐赠</a></li>
			<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
			<li><a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a></li>
			<li><a href="http://weibo.com/dwzui" target="_blank">微博</a></li>
			-->
			<li style="background-image: none;">您好，${fn:length(sysUser.infoEmp.empName) > 0 ? sysUser.infoEmp.empName : sysUser.username} 所在部门：${sysUser.infoEmp.infoDept.name}</li>
			<li style="background-image: none;"><a href="${pageContext.request.contextPath}/logout.do">退出</a></li>
		</ul>
		
		<!-- <ul class="themeList" id="themeList">
			<li theme="default"><div class="selected">蓝色</div></li>
			<li theme="green"><div>绿色</div></li>
			<li theme="red"><div>红色</div></li>
			<li theme="purple"><div>紫色</div></li>
			<li theme="silver"><div>银色</div></li>
			<li theme="azure"><div>天蓝</div></li>
		</ul> -->
		
	</div>
	<!-- navMenu -->
</div>