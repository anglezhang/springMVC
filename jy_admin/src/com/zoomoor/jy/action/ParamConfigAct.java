package com.zoomoor.jy.action;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.service.ParamConfigSvc;

/**   
 * 类名称：ParamConfigAct   
 * 类描述：   参数配置信息
 * 创建人：liuweixing
 * 创建时间：2015-7-15 上午9:51:02   
 * 修改人：liuweixing
 * 修改时间：2015-7-15 上午9:51:02   
 * 修改备注：   
 * @version       
 */ 
@Controller
public class ParamConfigAct {
	private static final Logger log = LoggerFactory.getLogger(ParamConfigAct.class);
	@Autowired
	private ParamConfigSvc configSvc;
	@Autowired
	private SysLogSvc sysLogSvc;
	/**  
	 * @Title: list  
	 * @Description: 加载配置列表 
	 * @param pager
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/paramconfig/list.do")
	public String list(Pager pager, ModelMap model){
		pager = configSvc.getPage(pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		return "paramconfig/list";
	}
	
	/**  
	 * @Title: add  
	 * @Description: 跳转至配置信息添加页
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/paramconfig/add.do")
	public String add(){
		return "paramconfig/add";
	}
	
	/**  
	 * @Title: save  
	 * @Description: 添加配置信息
	 * @param request
	 * @param response
	 * @param config void 
	 * @throws  
	 */  
	@SuppressWarnings("unchecked")
	@RequestMapping("/paramconfig/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, ParamConfig config){
		List<ParamConfig> list = configSvc.getListByName(config.getName(),config.getParamType());
		JSONObject json = new JSONObject();
		if(list!=null&&list.size()>0){
			json.put("statusCode", "300");
			json.put("message", "信息已经存在！");
		}else{
			config.setDel(false);
			Integer cid = configSvc.save(config);
			log.info("save ParamConfig id={}", cid);
			sysLogSvc.operating(request, "config.log.save", "id=" + cid + ";name=" + config.getName());
			if(cid > 0){
				json.put("statusCode", "200");
				json.put("message", "操作成功");
				json.put("callbackType", "closeCurrent");
			}
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	/**  
	 * @Title: delete  
	 * @Description: 删除配置信息 如果被使用则不能删除
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	@RequestMapping("/paramconfig/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
		ParamConfig bean = configSvc.get(id);
		JSONObject json = new JSONObject();
		//判断是否已经被使用
		Boolean isuser=configSvc.isUser(bean);
		if(isuser){
			json.put("statusCode", "300");
			json.put("message", "该信息已被使用不能删除");
			
		}else{
			bean.setDel(true);
			configSvc.update(bean);
			log.info("delete SysRole id={}", bean.getUnitId());
			sysLogSvc.operating(request, "config.log.delete", "id=" + bean.getUnitId() + ";name=" + bean.getName());
			json.put("statusCode", "200");
			json.put("message", "操作成功");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: check  
	 * @Description: 重名检测
	 * @param response
	 * @param roleName
	 * @param roleId void 
	 * @throws  
	 */  
	@RequestMapping("/paramconfig/check.do")
	public void check(HttpServletResponse response, String name, Integer unitId,Integer paramType) {
		String result = "false";
    	List<ParamConfig> list = configSvc.getListByName(name,paramType);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			ParamConfig config = list.get(0);
			if(unitId != null && unitId.equals(config.getUnitId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}	
	
}
