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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoomoor.admin.service.SysLogSvc;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.web.ResponseUtils;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.InfoSup;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.service.InfoBrandSvc;
import com.zoomoor.jy.service.InfoGoodsSvc;
import com.zoomoor.jy.service.ParamConfigSvc;

/**   
 * 类名称：InfoGoodsAct   
 * 类描述： 商品信息管理   
 * 创建人：liuweixing
 * 创建时间：2015-7-20 下午4:20:11   
 * 修改人：liuweixing
 * 修改时间：2015-7-20 下午4:20:11   
 * 修改备注：   
 * @version       
 */ 
@Controller
public class InfoGoodsAct {
	private static final Logger log = LoggerFactory.getLogger(InfoGoodsAct.class);
	@Autowired
	private InfoGoodsSvc infoGoodsSvc;
	@Autowired
	private ParamConfigSvc configSvc;
	@RequestMapping("/infogoods/list.do")
	public String list(Pager pager, String queryGoodsCode,String queryName,String queryBrand,InfoBrand infoBrand, ModelMap model){
		Integer brandId=null;
		if(infoBrand.getInfoBrand()!=null){
			brandId=infoBrand.getInfoBrand().getId();
			model.addAttribute("queryBrandId", infoBrand.getInfoBrand().getId());
			model.addAttribute("queryBrandName", infoBrand.getInfoBrand().getName());
		}
		pager = infoGoodsSvc.getPage(queryGoodsCode,queryName,queryBrand,brandId,pager.getPageNum(), pager.getNumPerPage());
		model.addAttribute("pager", pager);
		model.addAttribute("queryGoodsCode", queryGoodsCode);
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryBrand", queryBrand);
		return "goods/list";
	}
	/**  
	 * @Title: add  
	 * @Description: 跳转至商品添加页 
	 * @param model
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infogoods/add.do")
	public String add(ModelMap model){
		//查询单位配置
		String[] prom={"del","paramType"};
		Object[] objetm={false,1};
		List<ParamConfig> paramConfigList=configSvc.getList(prom,objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		return "goods/add";
	}
	
	
	@RequestMapping("/infogoods/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, InfoGoods infoGoods) {
		infoGoods.setDel(false);
		infoGoods.setTypeShow(infoGoods.getInfoGoodsType().getTypeName());
		if(infoGoods.getPrice()==null||"".equals(infoGoods.getPrice())){infoGoods.setPrice(0.0);}
		if(infoGoods.getWholesalePrice()==null||"".equals(infoGoods.getWholesalePrice())){infoGoods.setWholesalePrice(0.0);}
		if(infoGoods.getClaimPrice()==null||"".equals(infoGoods.getClaimPrice())){infoGoods.setClaimPrice(0.0);}
		if(infoGoods.getAdvisePrice()==null||"".equals(infoGoods.getAdvisePrice())){infoGoods.setAdvisePrice(0.0);}
		if(infoGoods.getInsurancePrice()==null||"".equals(infoGoods.getInsurancePrice())){infoGoods.setInsurancePrice(0.0);}
		if(infoGoods.getMemberPrice()==null||"".equals(infoGoods.getMemberPrice())){infoGoods.setMemberPrice(0.0);}
		if(infoGoods.getCostPrice()==null||"".equals(infoGoods.getCostPrice())){infoGoods.setCostPrice(0.0);}
		infoGoodsSvc.save(infoGoods);
		log.info("save infogoods id={}", infoGoods.getGoodsId());
		sysLogSvc.operating(request, "infogoods.log.save", "id=" + infoGoods.getGoodsId() + ";goodsname=" + infoGoods.getName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	
	/**  
	 * @Title: edit  
	 * @Description: 跳转至修改页面 
	 * @param model
	 * @param id
	 * @return String 
	 * @throws  
	 */  
	@RequestMapping("/infogoods/edit.do")
	public String edit(ModelMap model,Integer id){
		//查询单位配置
		String[] prom={"del","paramType"};
		Object[] objetm={false,1};
		List<ParamConfig> paramConfigList=configSvc.getList(prom,objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		InfoGoods goods=infoGoodsSvc.get(id);
		model.addAttribute("goods", goods);
		return "goods/edit";
	}
	@RequestMapping("/infogoods/view.action")
	public String view(ModelMap model,Integer id){
		//查询单位配置
		String[] prom={"del","paramType"};
		Object[] objetm={false,1};
		List<ParamConfig> paramConfigList=configSvc.getList(prom,objetm);
		model.addAttribute("paramConfigList", paramConfigList);
		InfoGoods goods=infoGoodsSvc.get(id);
		model.addAttribute("goods", goods);
		return "goods/view";
	}
	/**  
	 * @Title: update  
	 * @Description:修改商品信息
	 * @param request
	 * @param response
	 * @param infoGoods
	 * @param goodsId void 
	 * @throws  
	 */  
	@RequestMapping("/infogoods/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response, InfoGoods infoGoods, Integer goodsId) {
		InfoGoods  target=infoGoodsSvc.get(goodsId);
		BeanUtils.copyProperties(infoGoods, target,new String[]{"costPrice","del"});
		target.setDel(false);
		target.setTypeShow(infoGoods.getInfoGoodsType().getTypeName());	
		infoGoodsSvc.update(target);
		log.info("update infogoods id={}", target.getGoodsId());
		sysLogSvc.operating(request, "infogoods.log.update", "id=" + target.getGoodsId() + ";goodsname=" + target.getName());
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("callbackType", "closeCurrent");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	/**  
	 * @Title: delete  
	 * @Description: 删除商品信息
	 * @param request
	 * @param response
	 * @param ids void 
	 * @throws  
	 */  
	@RequestMapping("/infogoods/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer[] ids) {
		InfoGoods[] infogoods = infoGoodsSvc.deleteByIds(ids);
		for (InfoGoods bean : infogoods) {
			log.info("delete infogoods id={}", bean.getGoodsId());
			sysLogSvc.operating(request, "infogoods.log.delete", "id=" + bean.getGoodsId() + ";goodsname=" + bean.getName());
		}
		
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", "操作成功");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	/**  
	 * @Title: check  
	 * @Description: 检查商品编号是否重复 
	 * @param response
	 * @param goodsCode
	 * @param id void 
	 * @throws  
	 */  
	@RequestMapping("/infogoods/check.ajax")
	public void check(HttpServletResponse response, String goodsCode, Integer id) {
		String result = "false";
		String[] pro={"del","goodsCode"};
		Object[] objet={false,goodsCode};
		List<InfoGoods> list = infoGoodsSvc.getList(pro,objet);
		if(list == null || list.size() == 0){
			result = "true";
		}else{
			InfoGoods infogoods = list.get(0);
			if(id != null && id.equals(infogoods.getGoodsId())){
				result = "true";
			}
		}
		ResponseUtils.renderJson(response, result);
	}
	@Autowired
	private SysLogSvc sysLogSvc;
}
