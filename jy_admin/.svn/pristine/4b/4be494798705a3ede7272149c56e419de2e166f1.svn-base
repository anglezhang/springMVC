package com.zoomoor.core.web;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_LIST;
import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_SUPER;
import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;
import static com.zoomoor.common.web.Constants.MESSAGE;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysUser;
import com.zoomoor.admin.service.SysAuthenticationSvc;
import com.zoomoor.admin.service.SysUserSvc;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.common.web.session.SessionProvider;
import com.zoomoor.core.web.util.SysUtils;

/**
 * 上下文信息拦截器
 * 
 * 包括登录信息、权限信息
 */
@SuppressWarnings("unchecked")
public class AdminContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 获得用户
		SysUser user = null;

		// 正常状态
		Integer userId = sysAuthenticationSvc.retrieveUserIdFromSession(
				session, request);
		if (userId != null) {
			user = session.getUserSession(request, AUTH_USER);
			if (user == null) {
				user = sysUserSvc.get(userId);
			}
		}

		// 此时用户可以为null
		SysUtils.setUser(request, user);
		// User加入线程变量
		SysThreadVariable.setUser(user);

		String uri = getURI(request);

		// 不在验证的范围内
		if (exclude(uri)) {
			return true;
		}

		// 用户为null跳转到登陆页面
		if (user == null) {
			// index页跳转
			if (uri.indexOf("index.do") != -1) {
				response.sendRedirect("login.do");
			} else {
				JSONObject json = new JSONObject();
				json.put("statusCode", "301");
				json.put("message", "会话超时，请重新登录。");
				ResponseUtils.renderJson(response, json.toJSONString());
			}
			return false;
		}

		// 站点的管理员被禁用。
		if (user.getIsDisabled() == 1) {
			request.setAttribute(MESSAGE, "该账号已被禁用。");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}

		// 没有访问权限，提示无权限。
		if (!permistionPass(request, uri, user)) {
			request.setAttribute(MESSAGE, "没有该地址的访问授权。");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		// System.out.println("postHandle.........");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("afterCompletion.........");
	}

	private boolean exclude(String uri) {
		uri = uri.replace("/admin", ""); // 开发去除项目路径
		String[] excludeUrls = { "/login.do", "/logout.do" };

		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean permistionPass(HttpServletRequest request, String uri,
			SysUser user) {

		// 开发去除项目路径
		uri = uri.replace("/admin", "");

		// 去除系统路径
		String[] excludeUrls = { "/index.do", "/systree.do", "/usertree.do",
				"/upload.do", "/fund/upload.do", "/product/upload.do" };
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}

		// 去除公共路径
		if (uri.indexOf("lookup") != -1) {
			return true;
		}

		// 是否拥有所有权限
		if (session.getIsSuperSession(request, AUTH_SUPER)) {
			return true;
		}

		HashSet<SysAuth> authSet = session.getAuthSession(request, AUTH_LIST);
		if (authSet == null) {
			return false;
		}

		for (SysAuth auth : authSet) {
			if (auth.getAuthUrl().indexOf(uri) != -1) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 获得第三个路径分隔符的位置
	 * 
	 * @param request
	 * @throws IllegalStateException
	 *             访问路径错误，没有三(四)个'/'
	 */
	private static String getURI(HttpServletRequest request)
			throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		return uri;
		// String ctxPath = helper.getOriginatingContextPath(request);
		// int start = 0, i = 0, count = 2;
		// if (!StringUtils.isBlank(ctxPath)) {
		// count++;
		// }
		// while (i < count && start != -1) {
		// start = uri.indexOf('/', start + 1);
		// i++;
		// }
		// if (start <= 0) {
		// throw new
		// IllegalStateException("admin access path not like '/admin/system/...' pattern: "
		// + uri);
		// }
		// return uri.substring(start);
	}

	private SessionProvider session;
	private SysAuthenticationSvc sysAuthenticationSvc;
	private SysUserSvc sysUserSvc;

	@Autowired
	public void setSession(SessionProvider session) {
		this.session = session;
	}

	@Autowired
	public void setSysUserSvc(SysUserSvc sysUserSvc) {
		this.sysUserSvc = sysUserSvc;
	}

	@Autowired
	public void SysAuthenticationSvc(SysAuthenticationSvc sysAuthenticationSvc) {
		this.sysAuthenticationSvc = sysAuthenticationSvc;
	}
}