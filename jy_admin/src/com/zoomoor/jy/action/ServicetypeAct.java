package com.zoomoor.jy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.jy.entity.Servicetype;
import com.zoomoor.jy.service.ServicetypeSvc;
import com.zoomoor.jy.util.ResponseMsgUtil;

/**
 * 描述：ServicetypeSvc服务类型管理
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月30日 上午11:42:01
 * @version 1.0
 */

@Controller
public class ServicetypeAct {

	/**
	 * 服务类型管理
	 * */
	@Autowired
	private ServicetypeSvc typeSvc;

	// 日志输入
	private static final Logger log = LoggerFactory
			.getLogger(ServicetypeAct.class);

	/**
	 * 服务类型删除
	 * */
	@RequestMapping("/service/typedetete.do")
	public void delete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("servicetypeId") Integer servicetypeId) {
		typeSvc.deleteById(servicetypeId);
		ResponseMsgUtil.operSuccessFull(response, "删除成功");
	}

	/**
	 * 描述:修改
	 * */
	@RequestMapping("/service/typeedit.do")
	public String edit(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("servicetypeId") Integer servicetypeId, ModelMap model) {
		Servicetype serviceType = typeSvc.get(servicetypeId);
		model.addAttribute("serviceType", serviceType);
		return "service/type/edit";
	}

	@RequestMapping("/service/typeupdate.do")
	public void update(HttpServletRequest request,
			HttpServletResponse response, Servicetype serviceType) {
		Servicetype entity = typeSvc.get(serviceType.getServicetypeId());
		entity.setServicename(serviceType.getServicename());
		typeSvc.update(entity);
		ResponseMsgUtil.operSuccessFull(response, "修改成功");
	}

	/**
	 * 描述:列表
	 * */
	@RequestMapping("/servicetype/list.do")
	public String list(Pager pager, ModelMap model, String queryTypeName) {
		pager = typeSvc.getPager(pager, queryTypeName);
		model.addAttribute("pager", pager);
		model.addAttribute("queryTypeName", queryTypeName);
		return "/service/type/list";
	}

	/**
	 * 列表界面删除
	 * */
	@RequestMapping("/servicetype/delete.do")
	public void listDelete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("servicetypeId") Integer servicetypeId) {
		typeSvc.deleteById(servicetypeId);
		ResponseMsgUtil.operSuccessFull(response, "删除成功");
	}

	/**
	 * 列表界面修改
	 * */
	@RequestMapping("/servicetype/edit.do")
	public String listEdit(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("servicetypeId") Integer servicetypeId, ModelMap model) {
		Servicetype serviceType = typeSvc.get(servicetypeId);
		model.addAttribute("serviceType", serviceType);
		return "service/type/listEdit";
	}

	/**
	 * 列表界面更新
	 * */
	@RequestMapping("/servicetype/update.do")
	public void listupdate(HttpServletRequest request,
			HttpServletResponse response, Servicetype serviceType) {
		Servicetype entity = typeSvc.get(serviceType.getServicetypeId());
		entity.setServicename(serviceType.getServicename());
		typeSvc.update(entity);
		ResponseMsgUtil.operSuccessFullClose(response, "修改成功");
	}

	/**
	 * list列表添加
	 * */
	@RequestMapping("/servicetype/add.do")
	public String listAdd() {
		return "service/type/listAdd";
	}

	/**
	 * list新增保存
	 * */
	@RequestMapping("/servicetype/save.do")
	public void listSave(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Servicetype servicetype) {
		servicetype.setDel(false);
		typeSvc.save(servicetype);
		ResponseMsgUtil.operSuccessFullClose(response, "保存成功");
	}
	
}
