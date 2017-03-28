package com.zoomoor.common.tld;

import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_LIST;
import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_SUPER;
import static com.zoomoor.admin.service.SysAuthenticationSvc.AUTH_USER;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.zoomoor.admin.entity.SysAuth;
import com.zoomoor.admin.entity.SysUser;

/**
 * 按钮权限标签
 */
@SuppressWarnings("unchecked")
public class AuthTag extends TagSupport{
	
	private static final long serialVersionUID = 6733375108988989480L;

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	
	private String code; //权限代码
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int doStartTag() throws JspException {
		
		if(StringUtils.isEmpty(code)){
			return SKIP_BODY;
		}
		
		HttpServletRequest request= (HttpServletRequest) this.pageContext.getRequest();
		HttpSession session = request.getSession();
		
		boolean auth_super = (Boolean) session.getAttribute(AUTH_SUPER);
		if(auth_super){
			return EVAL_BODY_INCLUDE;
		}

		String[] codes = code.split(",");
		// 权限限制标记
		boolean flag_cando = false;
		for(String code : codes){ 
			// 确认Session中是否存在user
			SysUser user = (SysUser)session.getAttribute(AUTH_USER);
			if (user != null) {
				// 存在的情况下进行后续操作。
				// 用户拥有的权限
				HashSet<SysAuth> authSet = (HashSet<SysAuth>) session.getAttribute(AUTH_LIST);
				// 遍历用户拥有的权限
				for (SysAuth auth : authSet) {
					if (code.equals(auth.getAuthCode())) {
						// 若用户拥有该权限，则可执行
						flag_cando = true;
						break;
					}
				}
				
			}
			if(flag_cando){
				break;
			}
		}
		if (flag_cando) {
			// 显示标签中内容
			return EVAL_BODY_INCLUDE;
		}  
		return SKIP_BODY;
	}
}