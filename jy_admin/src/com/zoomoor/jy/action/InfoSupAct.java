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
import com.zoomoor.jy.entity.InfoSup;
import com.zoomoor.jy.service.InfoSupSvc;

/**   
 * 类名称：InfoSupAct   
 * 类描述：   供应商管理
 * 创建人：liuweixing
 * 创建时间：2015-7-17 下午4:31:05   
 * 修改人：liuweixing
 * 修改时间：2015-7-17 下午4:31:05   
 * 修改备注：   
 * @version       
 */ 
@Controller
public class InfoSupAct {
	private static final Logger log = LoggerFactory.getLogger(InfoSupAct.class);
	@Autowired
	private SysLogSvc sysLogSvc;
	@Autowired
	private InfoSupSvc infoSubSvc;
	/**  
	 * @Title: list  
	 * @Description:供应商信息列表 
	 * @param pager
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infosub/list.do")
	public String list(Pager pager, ModelMap model,String queryName,String queryCode,String queryLinkMan,String queryPhone){
		pager = infoSubSvc.getPage(queryName,queryCode,queryLinkMan,queryPhone,pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryCode", queryCode);
		model.addAttribute("queryLinkMan", queryLinkMan);
		model.addAttribute("queryPhone", queryPhone);
		return "infosub/list";
	}
	
	/**  
	 * @Title: add  
	 * @Description: 跳转至添加页面 
	 * @param pager
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infosub/add.do")
	public String add(Pager pager, ModelMap model,String dialogId){
		model.addAttribute("dialogId", dialogId);
		return "infosub/add";
	}
	
	/**  
	 * @Title: save  
	 * @Description: 保存供应商信息
	 * @param pager
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infosub/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, InfoSup infosub){
		infosub.setDel(false);
		Integer infosubId = infoSubSvc.save(infosub);
		log.info("save infosub id={}",infosubId);
		sysLogSvc.operating(request, "infosub.log.save", "id=" + infosubId + ";name=" + infosub.getName());
		JSONObject json = new JSONObject();
		if(infosubId > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
		}
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	/**  
	 * @Title: delete  
	 * @Description: 删除供应商信息 
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	@RequestMapping("/infosub/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		InfoSup[] infosub = infoSubSvc.deleteByIds(ids);
		for (InfoSup bean : infosub) {
			log.info("delete infosup id={}", bean.getId());
			sysLogSvc.operating(request, "infosup.log.delete", "id=" + bean.getId() + ";name=" + bean.getName());
		}
		
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	/**  
	 * @Title: view  
	 * @Description:跳转至修改页面
	 * @param id
	 * @param model
	 * @return String 
	 * @throws  
	 */ 
	@RequestMapping("/infosub/edit.do")
	public String edit(Integer id,ModelMap model){
		InfoSup infosub=infoSubSvc.get(id);
		model.addAttribute("infosub", infosub);
		return "infosub/edit";
	}
	
	/**  
	 * @Title: update  
	 * @Description:更新
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	@RequestMapping("/infosub/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, InfoSup infosub, Integer id) {
		InfoSup bean = infoSubSvc.updateById(infosub,id);
		log.info("update infosup id={}", infosub.getId());
		sysLogSvc.operating(request, "infosup.log.update", "id=" + infosub.getId() + ";name=" + infosub.getName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("callbackType", "closeCurrent");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: view  
	 * @Description: 查看
	 * @param id
	 * @param model
	 * @return String 
	 * @throws  
	 */ 
	@RequestMapping("/infosub/view.do")
	public String view(Integer id,ModelMap model){
		InfoSup infosub=infoSubSvc.get(id);
		model.addAttribute("infosub", infosub);
		return "infosub/view";
	}
	
	@RequestMapping("/infosub/check.ajax")
	public void check(HttpServletResponse response, String code, Integer id) {
		String result = "false";
		
		List<InfoSup> list = infoSubSvc.getListByCode(code);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			InfoSup infosub = list.get(0);
			if(id != null && id.equals(infosub.getId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	/**  
	 * @Title: lookUpGoodsType  
	 * @Description: 弹出选择框
	 * @param model
	 * @param backId
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infosub/lookup.do")
	public String lookUpInfoSub(Pager pager,String queryName, String queryCode,ModelMap model){
		pager = infoSubSvc.getPage(queryName,queryCode,null,null,pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryCode", queryCode);
		return "infosub/lookup";
	}
	
}
