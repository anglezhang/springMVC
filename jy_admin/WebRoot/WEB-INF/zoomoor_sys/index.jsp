<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>嘉悦汽车管理平台</title>
    <%@ include file="head.jsp" %>
  </head>
 <body scroll="no">
	<div id="layout">
		<%@ include file="top.jsp" %>
		<%@ include file="main.jsp" %>
	</div>
	<%@ include file="foot.jsp" %>
    <script type="text/javascript">
		$(function(){
			DWZ.init("${pageContext.request.contextPath}/js/dwz/dwz.frag.xml", {
		//		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
				loginUrl:"${pageContext.request.contextPath}/login.do",	// 跳到登录页面
				statusCode:{ok:200, error:300, timeout:301}, //【可选】
				pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
				keys: {statusCode:"statusCode", message:"message"}, //【可选】
				ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
				debug:false, // 调试模式 【true|false】
				callback:function(){
					initEnv();
					$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
				}
			});
		});
	</script>
</body>
</html>