package com.zoomoor.jy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.service.InfoCarSvc;
import com.zoomoor.jy.service.InfoCustomeSvc;
import com.zoomoor.jy.service.ParamConfigSvc;
import com.zoomoor.jy.util.ResponseMsgUtil;

/**
 * 描述：InfoCarAct车辆信息管理
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月29日 下午3:19:01
 * @version 1.0
 */
@Controller
public class InfoCarAct {

	/**
	 * 车辆信息管理
	 * */
	@Autowired
	private InfoCarSvc infoCarSvc;
	
	/**
	 * 数据字典
	 * */
	@Autowired
	private ParamConfigSvc configSvc;
	
	/**
	 * 客户信息服务：管理客户资料
	 * */
	@Autowired
	private InfoCustomeSvc customerSvc;

	/**
	 * 描述:lookuo删除车辆信息
	 * */
	@RequestMapping("/carinfo/delete.do")
	public void deleteLookup(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("carId") Integer carId) {
		infoCarSvc.deleteLookup(carId);
		ResponseMsgUtil.operSuccessFull(response, "删除成功");
	}

	/**
	 * 描述:修改
	 * */
	@RequestMapping("/carinfo/edit.do")
	public String gotoEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("carId") Integer carId,
			@RequestParam("dialogId") String dialogId) {
		model.addAttribute("carId", carId);
		model.addAttribute("dialogId", dialogId);
		//查询单位配置
		String[] prom={"del","paramType"};
		Object[] objetm={false,6};//6表示排量
		List<ParamConfig> paramConfigList = configSvc.getList(prom,objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		InfoCar infoCar = infoCarSvc.get(carId);
		model.addAttribute("infoCar", infoCar);
		return "apporder/car/edit";
	}

	/**
	 * 描述:更新
	 * */
	@RequestMapping("/carinfo/update.do")
	public void lookupUpdate(HttpServletRequest request,
			HttpServletResponse response, InfoCar infoCar,
			@RequestParam("infoBrand.name") String brandName) {
		infoCarSvc.lookUpdate(infoCar,brandName);
		ResponseMsgUtil.operSuccessFull(response, "修改成功");
	}
	
	@RequestMapping("/carinfo/customerinfolookup.do")
	public String customeLookup(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Pager pager,
			String querytName, String queryTel, String CarNO){
		pager = customerSvc.getLookupPager(pager, querytName, queryTel, CarNO,null);
		if (StringUtils.isNotEmpty(querytName)) {
			model.addAttribute("querytName", querytName);
		}
		if (StringUtils.isNotEmpty(queryTel)) {
			model.addAttribute("queryTel", queryTel);
		}
		if (StringUtils.isNotEmpty(CarNO)) {
			model.addAttribute("CarNO", CarNO);
		}
		model.addAttribute("pager", pager);
		return "apporder/customer/carLookup";
	}
}
