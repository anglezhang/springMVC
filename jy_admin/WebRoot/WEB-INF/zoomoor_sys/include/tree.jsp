<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<ul class="tree treeFolder expand">
	<c:if test="${fn:length(sysAuthRoot) > 0}">
		<c:forEach items="${sysAuthRoot}" var="sysAuth1">
			<li>
				<a <c:if test="${not empty sysAuth1.authUrl}">href="${pageContext.request.contextPath}${sysAuth1.authUrl}" target="navTab" </c:if> rel="${sysAuth1.authId}">${sysAuth1.authName}</a>
				<c:if test="${fn:length(sysAuth1.sysAuths) > 0}">
					<ul>
						<c:forEach items="${sysAuth1.sysAuths}" var="sysAuth2">
							<li>
								<a <c:if test="${not empty sysAuth2.authUrl}">href="${pageContext.request.contextPath}${sysAuth2.authUrl}" target="navTab" </c:if> rel="${sysAuth2.authId}">${sysAuth2.authName}</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach>
	</c:if>
</ul>