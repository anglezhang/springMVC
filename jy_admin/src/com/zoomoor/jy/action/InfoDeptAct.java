package com.zoomoor.jy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoDeptSub;
import com.zoomoor.jy.service.InfoDeptSubSvc;
import com.zoomoor.jy.service.InfoDeptSvc;

/**   
 * 类名称：InfoDeptAct   
 * 类描述：   
 * 创建人：liuweixing
 * 创建时间：2015-7-22 下午4:54:03   
 * 修改人：liuweixing
 * 修改时间：2015-7-22 下午4:54:03   
 * 修改备注：   
 * @version       
 */ 
@SuppressWarnings("unchecked")
@Controller
public class InfoDeptAct {
	private static final Logger log = LoggerFactory.getLogger(InfoDeptAct.class);
	@Autowired
	private InfoDeptSvc deptSvc;
	@Autowired
	private SysLogSvc sysLogSvc;
	@Autowired
	private InfoDeptSubSvc infoDeptSubSvc;
	/**  
	 * @Title: list  
	 * @Description: 加载部门树
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infodept/list.do")
	public String list(Integer backId,ModelMap model){
		String json = deptSvc.getInfoDeptTreeJson();
		model.addAttribute("infoDeptRoot", json);
		if(null!=backId&&!"".equals(backId)){
			InfoDept infodept = deptSvc.get(backId);
			model.addAttribute("infodept", infodept);
		}
		return "/infodept/list";
	}
	@RequestMapping("/infodept/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
		InfoDept bean = deptSvc.get(id);
		bean.setDel(true);
		bean.setInfoDeptSub(null);
		deptSvc.update(bean);
		if(bean.getInfoDeptSub()!=null){
			if(bean.getInfoDeptSub().getDeptChildId()>0){
				infoDeptSubSvc.delete(bean.getInfoDeptSub().getDeptChildId());
			}
		}
		log.info("delete infodept id={}", bean.getDeptId());
		sysLogSvc.operating(request, "infodept.log.delete", "id=" + bean.getDeptId() + ";name=" + bean.getName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: add  
	 * @Description: 跳转至添加页面
	 * @param parentAuthId
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infodept/add.do")
	public String add(Integer upId, ModelMap model) {
		if(upId != null && upId > 0){
			InfoDept infodept = deptSvc.get(upId);
			model.addAttribute("infodept", infodept);
		}

		return "infodept/add";
	}
	
	/**  
	 * @Title: save  
	 * @Description: 保存类别信息 
	 * @param request
	 * @param response
	 * @param infodept
	 * @param upId void 
	 * @throws  
	 */  
	@RequestMapping("/infodept/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, InfoDept infodept, Integer upId) {
		InfoDept bean=null;
		infodept.setDel(false);
		if(!infodept.getIsShop()){
			infodept.setInfoDeptSub(null);
			bean = deptSvc.save(infodept, upId);
		}else{
			Integer subid=infoDeptSubSvc.save(infodept.getInfoDeptSub());
			InfoDeptSub deptSub=infoDeptSubSvc.get(subid);
			infodept.setInfoDeptSub(deptSub);
			bean = deptSvc.save(infodept, upId);
		}
		log.info("save infodept id={}",bean.getDeptId());
		sysLogSvc.operating(request, "infodept.log.save", "id=" + bean.getDeptId() + ";name=" + bean.getName());
		JSONObject json = new JSONObject();
		if(bean.getDeptId() > 0){
			json.put("statusCode", "200");
			json.put("message", "操作成功");
			json.put("callbackType", "closeCurrent");
			json.put("backId",bean.getDeptId());
		}
		
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: edit  
	 * @Description: 跳转至修改页面 
	 * @param groupId
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infodept/edit.do")
	public String edit(Integer deptId, ModelMap model) {
		InfoDept infodept = deptSvc.get(deptId);
		model.addAttribute("infodept", infodept);
		return "infodept/edit";
	}
	
	/**  
	 * @Title: update  
	 * @Description: 修改商品类别
	 * @param request
	 * @param response
	 * @param infodept
	 * @param groupId void 
	 * @throws  
	 */  
	@RequestMapping("/infodept/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, InfoDept infodept, Integer deptId) {
		InfoDept bean=null;
		if(!infodept.getIsShop()){
			Integer deptSubId=0;
			if(infodept.getInfoDeptSub()!=null){
				deptSubId=infodept.getInfoDeptSub().getDeptChildId();
			}
			infodept.setInfoDeptSub(null);
			bean= deptSvc.update(infodept, deptId);
			//如果不是店铺,删除子表信息
			if(deptSubId!=null&&deptSubId>0){
				InfoDeptSub dsb=infoDeptSubSvc.get(deptSubId);
				infoDeptSubSvc.delete(dsb);
			}
		}else{
			//如果是店铺 分两种情况 1 存在修改 2 不存在添加 
			if(infodept.getInfoDeptSub().getDeptChildId()!=null){
				InfoDeptSub deptSub=infoDeptSubSvc.get(infodept.getInfoDeptSub().getDeptChildId());
				BeanUtils.copyProperties(infodept.getInfoDeptSub(), deptSub);
				infoDeptSubSvc.update(deptSub);
				infodept.setInfoDeptSub(deptSub);
				bean = deptSvc.update(infodept, deptId);
			}else{
				Integer subid=infoDeptSubSvc.save(infodept.getInfoDeptSub());
				InfoDeptSub deptSub=infoDeptSubSvc.get(subid);
				infodept.setInfoDeptSub(deptSub);
				bean = deptSvc.update(infodept, deptId);
			}
		}
		log.info("update infodept id={}", bean.getDeptId());
		sysLogSvc.operating(request, "infodept.log.update", "id=" + bean.getDeptId() + ";name=" + bean.getName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**
	 *@Title:  move
	 *@Description: 移动权限 
	 *@Author: liuweixing
	 *@Since: 2014-11-27下午3:23:23
	 *@param request
	 *@param response
	 *@param sourceId
	 *@param targetId
	*/
	@RequestMapping("/infodept/move.do")
	public void move(HttpServletRequest request, HttpServletResponse response, Integer sourceId,Integer targetId,String moveType) {
		InfoDept sourceType=deptSvc.get(sourceId);
		InfoDept targetType=deptSvc.get(targetId);
		if(sourceType==null||targetType==null){
			JSONObject json = new JSONObject();
			json.put("statusCode", "300");
			json.put("message", "移动失败，目标信息错误！");
			ResponseUtils.renderJson(response, json.toJSONString());
		}else{
			if(targetType.getIsShop()&&sourceType.getIsShop()){
				JSONObject json = new JSONObject();
				json.put("statusCode", "300");
				json.put("message", "不能将店铺移动到店铺下！");
				ResponseUtils.renderJson(response, json.toJSONString());
			}else{
				if("inner".equals(moveType)){
					sourceType.setInfoDept(targetType);
					deptSvc.update(sourceType);
				}
				if("next".equals(moveType)){
					if(targetType.getInfoDept()==null){
						sourceType.setInfoDept(null);	
					}else{
						InfoDept targetParentType=deptSvc.get(targetType.getInfoDept().getDeptId());
						sourceType.setInfoDept(targetParentType);	
					}
					sourceType.setSort(targetType.getSort()+1);
					deptSvc.update(sourceType);
					//xiugai
					updateSort(targetId, sourceType, targetType);
				}
				if("prev".equals(moveType)){
					if(targetType.getInfoDept()==null){
						sourceType.setInfoDept(null);
					}else{
						InfoDept targetParentAuth=deptSvc.get(targetType.getInfoDept().getDeptId());
						sourceType.setInfoDept(targetParentAuth);
					}
					sourceType.setSort(targetType.getSort()-1);
					deptSvc.update(sourceType);
					updateSort(sourceId, sourceType, targetType);
					
				}
				JSONObject json = new JSONObject();
				json.put("statusCode", "200");
				json.put("message", "操作成功");
				log.info("move infodept id={}", sourceId);
				sysLogSvc.operating(request, "infodept.log.move", "sourceId=" + sourceId + ";targetId=" + targetId);
				ResponseUtils.renderJson(response, json.toJSONString());
			}
		}
	}
	@RequestMapping("/infodept/check.ajax")
	public void check(HttpServletResponse response, String name, Integer deptId,Integer upId) {
		String result = "false";
		if(deptId!=null&&deptId>0){
			InfoDept gt=deptSvc.get(deptId);
			if(gt.getInfoDept()!=null){
				upId=gt.getInfoDept().getDeptId();
			}else{
				upId=null;
			}
		}
		List<InfoDept> list = deptSvc.getListByName(name,upId);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			InfoDept infodept = list.get(0);
			if(deptId != null && deptId.equals(infodept.getDeptId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	/**  
	 * @Title: checksub  
	 * @Description:验证门店编号 
	 * @param response
	 * @param infodept
	 * @param subid void 
	 * @throws  
	 */  
	@RequestMapping("/infodept/checksub.do")
	public void checksub(HttpServletResponse response, InfoDept infodept, Integer subid) {
		String result = "false";
		List<InfoDeptSub> list = infoDeptSubSvc.getList("deptNo",infodept.getInfoDeptSub().getDeptNo());
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			InfoDeptSub infodeptSub = list.get(0);
			if(subid != null && subid.equals(infodeptSub.getDeptChildId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	
	private void updateSort(Integer targetId, InfoDept sourceType,
			InfoDept targetType) {
		Integer gid=null;
		if(targetType.getInfoDept()!=null){
			gid=targetType.getInfoDept().getDeptId();
		}
		List<InfoDept> typeList=deptSvc.getChildById(targetId,gid);
		for (int i = 0; i <typeList.size(); i++) {
				if(typeList.get(i).getDeptId()!=sourceType.getDeptId()&&!"".equals(targetType.getDeptId())){
					InfoDept newType=deptSvc.get(typeList.get(i).getDeptId());	
					newType.setSort(sourceType.getSort()+i+1);
					deptSvc.update(newType);
				}
		}
	}
}
