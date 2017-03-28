/**
 * 
 */
package com.cyw.mammoth.web.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class UserInfoTag extends RequestContextAwareTag {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6627877605441223064L;

	/**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    /*private OperatorSvc operatorSvc;
	 @Autowired
	 public void setOperatorSvc(OperatorSvc operatorSvc) {
			this.operatorSvc = operatorSvc;
   }*/
    /**
     * 属性名称
     */
    private String propertyName;

	@Override
	protected int doStartTagInternal() throws Exception {
		JspWriter out = pageContext.getOut();  
		StringBuffer sb = new StringBuffer();
		String styleHtml="";
		int returnKy=EVAL_PAGE;
		try {
			/*if(null==operatorSvc){
				 WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
					operatorSvc=applicationContext.getBean(OperatorSvc.class);
			}*/
			try{
				Operator oper = AppBaseCfg.getOperator();
				if(null!=oper){
					if(StrUtils.isValidString(propertyName)){
						switch(propertyName.trim()){
						case "operId":{
							styleHtml = oper.getOperId();
							break;
						}
						case "operName":{
							styleHtml = oper.getOperName();
							break;
						}
						case "post":{
							styleHtml = oper.getPost()==null?"":oper.getPost();
							break;
						}
						default:{
							styleHtml="";
						}
						}
					}
				}
				
			}catch(Exception e){
				logger.error("UserInfoTag.java 获取用户信息发生错误,",e);
			}
			
				sb.append(styleHtml==null?"":styleHtml);  
				out.print(sb.toString());  
			} catch (Exception e) {  
				logger.error("UserInfoTag.java 发生错误,",e);
				out.print("");  
			}
		return returnKy;
	}
	
	@Override
	public int doEndTag() throws JspException {
		
		return super.doEndTag();
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
}
