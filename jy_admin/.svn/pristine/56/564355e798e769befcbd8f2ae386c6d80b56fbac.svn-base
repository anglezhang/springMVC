package com.zoomoor.jy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.jy.entity.CustomerAppoit;

/**
 * 描述：跳转至百度地图
 * @author Zhangzhenxing
 * @Date 2015年7月28日 上午10:46:30
 * @version 1.0
 */
@Controller
@Transactional
public class BaiduMapAct {

	@RequestMapping("/baidu/mapLookup.do")
	public String gotoBaiduMap(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String lngLat){
		if(StringUtils.isNotEmpty(lngLat)){
			lngLat = lngLat.trim();
			model.addAttribute("lngLat", lngLat);
		}
		return "/baidumap/baiduMap";
	}
}
