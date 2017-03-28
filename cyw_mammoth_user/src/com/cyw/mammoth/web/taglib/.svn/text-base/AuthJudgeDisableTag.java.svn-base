/**
 * 
 */
package com.cyw.mammoth.web.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.service.OperatorSvc;

/**
 * 根据权限设置是否现实
 * @author wexl@163.com
 *
 */
@Component
public class AuthJudgeDisableTag extends RequestContextAwareTag {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6627877605441223064L;

	/**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    private OperatorSvc operatorSvc;
	 @Autowired
	 public void setOperatorSvc(OperatorSvc operatorSvc) {
			this.operatorSvc = operatorSvc;
   }
    /**
     * 功能ID
     */
    private String funcId;
    
    private String  disable0="'color: grey;cursor:not-allowed;'";

	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = pageContext.getOut();  
		StringBuffer sb = new StringBuffer();
		String styleHtml="";
		int returnKy=EVAL_PAGE;
		try {
			if(null==operatorSvc){
				 WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
					operatorSvc=applicationContext.getBean(OperatorSvc.class);
			}
			    int checkResult= checkHasPermission(getFuncId());
			    switch(checkResult){
			    case 0:
			    	styleHtml="";
			    	break;
			    case 1:
			    	styleHtml="";
			    	break;
			    case -1:
			    	styleHtml="noauthed disabled style="+disable0;
			    	returnKy=SKIP_BODY;
			    	break;
			    default:
			    	styleHtml="";
			    	break;
			    }
				sb.append(styleHtml);  
				out.print(sb.toString());  
			} catch (Exception e) {  
				e.printStackTrace();
			}
		return returnKy;
	}
	/**
	 * 根据控件ID查询是否有此用户的权限
	 * @return 0:没有纳入权限管理  1:有权限 -1:无权限
	 */
	private int checkHasPermission(String funcId){
		funcId=funcId.trim();
		if(!StrUtils.isValidString(funcId)) return 0;
		Operator user= AppBaseCfg.getOperator();
		if(null==user) return -1;
		if(AppBaseCfg.SUPER_ADMIN_GROUPID.equals(user.getGroupId())) return 1;
		if(operatorSvc.checkIsInAuthManage(funcId)<=0) return 0;
		if(operatorSvc.checkIsAuthed(user.getGroupId(), funcId)){//有授权
			return 1;
		}else{
			return -1;
		}
		
	}
	
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	@Override
	public int doEndTag() throws JspException {
		
		return super.doEndTag();
	}
	
}
