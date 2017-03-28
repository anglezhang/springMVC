package com.cyw.mammoth.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyw.common.util.Constant;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.Fguest;
import com.cyw.mammoth.bean.Hcodes;
import com.cyw.mammoth.bean.Hcountry;
import com.cyw.mammoth.bean.Hfolk;
import com.cyw.mammoth.bean.HgstOri;
import com.cyw.mammoth.bean.HroomCharacters;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.bean.Hsettle;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.cywenum.HcodesEnumType;
import com.cyw.mammoth.service.CodeCareSvc;
import com.cyw.mammoth.service.FguestSvc;
import com.cyw.mammoth.service.HcodesSvc;
import com.cyw.mammoth.service.HcountrySvc;
import com.cyw.mammoth.service.HfolkSvc;
import com.cyw.mammoth.service.HgstOriSvc;
import com.cyw.mammoth.service.HroomCharactersSvc;
import com.cyw.mammoth.service.HroomPlanSvc;
import com.cyw.mammoth.service.HroomTypeSvc;
import com.cyw.mammoth.service.HsettleSvc;
import com.cyw.mammoth.service.TADocSvc;
import com.cyw.mammoth.vo.AjaxJson;
import com.cyw.mammoth.vo.FguestVo;
import com.cyw.mammoth.vo.TADocSearchVo;
import com.google.gson.Gson;

/**
 * 营销模块
 * <功能详细描述>
 * 
 * @author  yaochenglong@cyw.so
 * @version  [v1.0, 2015-9-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
public class MarketingAction {
	
	private static final Logger log = LoggerFactory.getLogger(MarketingAction.class);
	@Autowired
	private TADocSvc taDocSvc;
	@Autowired
	private CodeCareSvc codeCareSvc;
	@Autowired
	private FguestSvc fguestSvc;
	@Autowired
	private HcountrySvc hcountrySvc;
	@Autowired
	private HroomTypeSvc hroomTypeSvc;
	@Autowired
	private HfolkSvc hfolkSvc;
	@Autowired
	private HgstOriSvc hgstOriSvc;
	@Autowired
	private HsettleSvc hsettleSvc;
	@Autowired
	private HroomPlanSvc hroomPlanSvc ;
	/**
	 * 房间天特征
	 */
	@Autowired
	private HroomCharactersSvc hroomCharactersSvc ;
	
	
	/** 
	 * 跳转至会员维护页面
	 * @param 合约单位
	 * @param searchVo
	 * @return
	 */
	
	//------------------------add code about 会员维护-------------------------//
	
	
	//------------------------有关合约单位的操作方法start-----------------------------//
	/** 
	 * 跳转至合约单位页面
	 * @param 合约单位
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/marketing/tadoclist.do")
	public String toTadoclist(ModelMap model,TADocSearchVo searchVo){
		List<TaDoc> list = taDocSvc.getAllTaDocList(searchVo);
		Gson gson= new Gson();
		String object=gson.toJson(list);
		model.addAttribute("list",list);
		model.addAttribute("object", object);
		try {
			List<Hcodes> hcodesList=codeCareSvc.findGEListBy("005", 0);
			for(Hcodes hc:hcodesList){
				hc.setCodeId(hc.getCodeId().trim());
				hc.setCodeNamec(hc.getCodeNamec().trim());
			}
			model.addAttribute("hyunitcodes", hcodesList);
		} catch (Exception e) {
			// TODO 获取单位类型发生错误,应该用LOG4G替代直接print
			e.printStackTrace();
		}
		return "marketing/tadoc";
	}
	
	/**
	 * 加载合约单位
	 * @param model
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/marketing/doTadoclist.do")
	public String doTadoclist(ModelMap model,TADocSearchVo searchVo){
		List<TaDoc> list = taDocSvc.getAllTaDocList(searchVo);
		Gson gson= new Gson();
		String object=gson.toJson(list);
		model.addAttribute("list",list);
		model.addAttribute("object", object);
		return "marketing/tadoc";
	}
	/**
	 * 检索合约单位
	 * @param model
	 * @param searchVo
	 * @return
	 */
	@RequestMapping("/marketing/searchTadoclist.do")
	@ResponseBody
	public List<TaDoc> searchTadoclist(ModelMap model,TADocSearchVo searchVo){
		List<TaDoc> list = taDocSvc.getAllTaDocList(searchVo);
		List<TaDoc> _list = new ArrayList<TaDoc>();
		Hcodes hc = null ;
		for (TaDoc taDoc : list) {
			hc = codeCareSvc.get("codeId", taDoc.getSalemanId()) ;
			String codeName = (hc != null ? hc.getCodeNamec() : "") ;
			taDoc.setSalemanName(codeName);
			_list.add(taDoc) ;
		}
		return _list;
	}
	
	
	@ResponseBody
	@RequestMapping("/marketing/saveTadoc.do")
	public AjaxJson addTaDoc(TaDoc searchVo){
		AjaxJson aj = new AjaxJson();
		TaDoc tadoc = new TaDoc();
		BeanUtils.copyProperties(searchVo, tadoc);
		tadoc.setIfPerm(false);
		
		//FIXME 需要确定默认值
		if(tadoc.getId()==null || tadoc.getId()==0){//主键/主合同编号未设置//规则改变,主键自增
			//tadoc.setId((int)((Math.random()*9+1)*1000000));
			//
		}
		if(tadoc.getTaCompCd()!=null){
			//校验副合同号是否重复
			//TaDoc td=taDocSvc.get("taCompCd", tadoc.getTaCompCd());
			TaDoc td=null;
			List<TaDoc>  ts=taDocSvc.getList("taCompCd", tadoc.getTaCompCd());
			
			if(ts.size()==1){
				td=ts.get(0);
			}else if(ts.size()>1){
				aj.setSuccess(false);
				aj.setMsg("副合同号重复,请重新输入.");
				return aj;
			}
			if(null!=td && tadoc.getId()!=null && td.getId().intValue()!=tadoc.getId().intValue()){
				aj.setSuccess(false);
				aj.setMsg("副合同号重复,请重新输入.");
				return aj;
			}
			td=null;
		}
		if(tadoc.getLimit()!=null){
			
		}
		//设定默认值
		//有争议金额
		tadoc.setAmt(0.0);
		tadoc.setContId("111");
		//区域
		tadoc.setArea("雁塔区");
		tadoc.setcancelTime(new Date());
		//tadoc.setStatus(0);
		if(!StrUtils.isValidString(tadoc.getNotes())){
			tadoc.setNotes("附加条款");
		}
		if(StrUtils.isValidString(tadoc.getContId())){
			tadoc.setContId("联系人");   //数据库文档说明  不确定
		}
		//折扣率
		if(tadoc.getDiscPert()==null){
			tadoc.setDiscPert(new Short((short) 20));
		}
		//操作人/当前登录人
		if(!StrUtils.isValidString(tadoc.getLastOper())){
			tadoc.setLastOper("最后操作员");
		}
		//最后操作时间
		tadoc.setLastTime(new Date());
		//操作员ID
		tadoc.setOperId("admin");  //数据库文档没有说明
		//取消操作员
		tadoc.setcancelOper("admin");
		tadoc.setOperTime(new Date());////数据库文档没有说明
		//行业
		tadoc.setTrade("行业");
		//级别
		tadoc.setLevels("2");
		if(!StrUtils.isValidString(tadoc.getBankId())){
			tadoc.setBankId("104090");
		}
		
		try {
			/*int count = taDocSvc.save(tadoc);
			if(count>0){
				aj.setMsg("保存成功!");
			}*/
			//taDocSvc.saveOrUpdate(tadoc);
			TaDoc entity=taDocSvc.merge(tadoc);
			aj.setSuccess(true);
			aj.setMsg("合约单位保存成功.");
			aj.setObj(entity.getId());
		} catch (Exception e) {
			aj.setSuccess(false);
			aj.setMsg("保存失败");
			log.error("[Marketing][addTaDoc]失败："+e);
		}
		return aj;
	}
	
	@ResponseBody
	@RequestMapping("/marketing/getTaDoc.do")
	public AjaxJson getTaDoc(int id){
		AjaxJson aj = new AjaxJson();
		TaDoc tadoc = taDocSvc.get(id);
		if(null==tadoc){
			aj.setSuccess(false);
			aj.setMsg("数据记录不存在");
			return aj;
		}
		aj.setSuccess(true);
		aj.setObj(tadoc);
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("hroomPlanList", hroomPlanSvc.findAvilListBy(0, null, null, null, null)) ;
		attributes.put("saleManList", codeCareSvc.findAllListBy(HcodesEnumType.HCODE_SALESMAN.getValue()));
		aj.setAttributes(attributes) ;
		return aj;
	}
	
	@ResponseBody
	@RequestMapping("/marketing/setStatus.do")
	public AjaxJson setStatus(int id,int status){
		AjaxJson aj = new AjaxJson();
		TaDoc tadoc = taDocSvc.get(id);
		if(null==tadoc){
			aj.setSuccess(false);
			aj.setMsg("数据记录不存在");
			return aj;
		}
		tadoc.setStatus(status);
		try{
			taDocSvc.update(tadoc);
			aj.setSuccess(true);
			aj.setMsg("取消成功");
		}catch(Exception e){
			aj.setSuccess(false);
			aj.setMsg("取消失败");
		}
		return aj;
	}
	
	@ResponseBody
	@RequestMapping("/marketing/delTadocById.do")
	public AjaxJson delTadocById(Integer id){
		AjaxJson aj = new AjaxJson();
		if(id!=null && 0<id){
			TaDoc tadoc = taDocSvc.get(id);
			if(tadoc!=null){
				tadoc.setStatus(Constant.TADOC_DELETE); 
				//FIXME 需要设置取消员,取消时间
				tadoc.setcancelOper("取消员");
				tadoc.setcancelTime(new Date());
			}
			try {
				taDocSvc.update(tadoc);
				aj.setMsg("修改成功");
			} catch (Exception e) {
				aj.setSuccess(false);
				aj.setMsg("修改失败");
				log.error("[MarketingAction][delTadocById]失败:"+e);
			}
			
		}
		return aj;
	}
	@ResponseBody
	@RequestMapping("/marketing/initTaDoc.do")
	public AjaxJson initTaDoc(){
		AjaxJson aj = new AjaxJson();
		aj.setSuccess(true);
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("hroomPlanList", hroomPlanSvc.findAvilListBy(0, null, null, null, null)) ;
		attributes.put("saleManList", codeCareSvc.findAllListBy(HcodesEnumType.HCODE_SALESMAN.getValue()));
		aj.setAttributes(attributes) ;
		return aj;
	}
	
	//------------------------有关合约单位的操作方法end-----------------------------//
	
	/** 
	 * 跳转至熟客资料页面
	 * @param 熟客资料
	 * @param searchVo
	 * @return
	 */
	//------------------------有关合约单位的操作方法start-----------------------------//
		/** 
		 * 跳转至合约单位页面
		 * @param 合约单位
		 * @param searchVo
		 * @return
		 */
		@RequestMapping("/marketing/fguestlist.do")
		public ModelAndView fguestlist(ModelMap model,FguestVo searchVo){
			ModelAndView mav=new ModelAndView("marketing/fguest");
			//客人分类
			List<Hcodes> hcodesList;
			try {
				hcodesList = codeCareSvc.findGEListBy("004", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("customTypes", hcodesList);
			} catch (Exception e) {
				log.error("",e);
			}
			//客人来源
			try {
				//hcodesList = codeCareSvc.findGEListBy("023", 0);
				List<HgstOri> gestOrilist= hgstOriSvc.getList("status", 0);
				for(HgstOri hc:gestOrilist){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("customSources", gestOrilist);
			} catch (Exception e) {
				log.error("",e);
			}
			//证件类别
			try {
				hcodesList = codeCareSvc.findGEListBy("006", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("cardTypes", hcodesList);
			} catch (Exception e) {
				log.error("",e);
			}
			//国籍
			List<Hcountry> countrys= hcountrySvc.getList("status", 0);
			if(null!=countrys){
				for (Hcountry hcountry : countrys) {
					hcountry.setCodeId(hcountry.getCodeId().trim());
				}
				model.addAttribute("countrys", countrys);
			}
			//省市
			try {
				hcodesList = codeCareSvc.findGEListBy("008", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("citys", hcodesList);
				hcodesList=null;
			} catch (Exception e) {
				log.error("",e);
			}
			//性别
			try {
				hcodesList = codeCareSvc.findGEListBy("026", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("sexs", hcodesList);
			} catch (Exception e) {
				log.error("",e);
				Hcodes b=new Hcodes();
				b.setCodeId("0");
				b.setCodeNamec("男");
				Hcodes g=new Hcodes();
				g.setCodeId("1");
				g.setCodeNamec("女");
				hcodesList = new ArrayList<Hcodes>();
				hcodesList.add(b);
				hcodesList.add(g);
				model.addAttribute("sexs", hcodesList);
			}
			//房类
			List<HroomType> roomTypes= hroomTypeSvc.getList("status", 0);
			Map<String,HroomType> roomMaps = new HashMap<String,HroomType>();
			if(null!=roomTypes){
				Iterator<HroomType> it = roomTypes.iterator();
				HroomType ht = null;
				while(it.hasNext()){
					ht = it.next();
					if(null!=roomMaps.get(ht.getCodeNamec())){
						it.remove();
						continue;
					}
					roomMaps.put(ht.getCodeNamec(),ht);
				}
				model.addAttribute("roomTypes", roomTypes);
			}
			//民族
			List<Hfolk> folks= hfolkSvc.getList("status", 0);
			if(null!=folks){
				for (Hfolk hfolk : folks) {
					hfolk.setCodeId(hfolk.getCodeId().trim());
				}
				model.addAttribute("folks", folks);
			}
			//付款方式
			try {
				//hcodesList = codeCareSvc.findGEListBy("002", 0);
				List<Hsettle> hsettlelist= hsettleSvc.getList("status", 0);
				for(Hsettle hc:hsettlelist){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("payTypes", hsettlelist);
			} catch (Exception e) {
				log.error("",e);
			}
			
			//签证类型
			try {
				hcodesList = codeCareSvc.findGEListBy("025", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("visakindIds", hcodesList);
			} catch (Exception e) {
				log.error("",e);
			}
			
			//签证类型
			try {
				hcodesList = codeCareSvc.findGEListBy("025", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("visakindIds", hcodesList);
			} catch (Exception e) {
				log.error("",e);
			}
			
			
			//入境口岸
			try {
				hcodesList = codeCareSvc.findGEListBy("012", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("inPorts", hcodesList);
			} catch (Exception e) {
				log.error("",e);
			}
			
			//签证机关
			try {
				hcodesList = codeCareSvc.findGEListBy("010", 0);
				for(Hcodes hc:hcodesList){
					hc.setCodeId(hc.getCodeId().trim());
					hc.setCodeNamec(hc.getCodeNamec().trim());
				}
				model.addAttribute("departs", hcodesList);
			} catch (Exception e) {
				log.error("",e);
			}
			//房间特征
			try{
				List<HroomCharacters> hroomCharacters=hroomCharactersSvc.getList("status", 0);
				model.addAttribute("hroomCharacters", hroomCharacters);
			}catch(Exception e){
				log.error("",e);
			}
			
			
			return mav;
		}
		@RequestMapping("/marketing/ajaxFguestList.do")
		@ResponseBody
		public List<FguestVo> ajaxFguestList(FguestVo searchVo){
			 List list=fguestSvc.search(searchVo);
			 return list;
		}
		/**
		 * 保存数据
		 * @param fguest
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/marketing/saveFguest.do")
		public AjaxJson saveFguest(Fguest fguest){
			AjaxJson aj = new AjaxJson();
			
			//设定默认值
			fguest.setAge(30);
			//fguest.setReachDate(new Date());//抵店日期
			//fguest.setLeaveDate(new Date());//离店日期
			fguest.setLastCons(0.00);//上次消费金额
			//fguest.setRoomTypeid("0");//房类代码
			//fguest.setRoomCharacter(0l);//客房特征
			fguest.setPaymanFlag(1);//付款人标志
			fguest.setChangeRate(true);//夜审是否可以改房租
			fguest.setSecret(false);//是否保密
			if(fguest.getHideprice()==null){
				fguest.setHideprice(true);//隐藏房价
			}
			fguest.setTeleStatus("0");//电话级别
			fguest.setGstFlag("F");//客人标志
			fguest.setBookType("0");//预定方式
			Subject currentUser = SecurityUtils.getSubject();
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
			fguest.setOperId(shiroUser.getLoginName());//操作人
			fguest.setOperTime(new Date());//操作时间
			fguest.setReason("0");//住宿原因
			//fguest.setVisakindId("0");//签证类型代码
			
			try {
			
				Fguest entity=fguestSvc.merge(fguest);
				aj.setSuccess(true);
				aj.setMsg("保存成功.");
				aj.setObj(entity.getGstId());
			} catch (Exception e) {
				aj.setSuccess(false);
				aj.setMsg("保存失败");
				log.error("[Marketing][saveFguest]失败："+e);
			}
			return aj;
		}
		/**
		 * 逻辑删除熟客资料
		 * @param fguest
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/marketing/logicDelFguest.do")
		public AjaxJson logicDelFguest(int id){
			AjaxJson aj = new AjaxJson();
			try {
				Fguest entity=fguestSvc.get(id);
				entity.setStatus(1);//无效
				fguestSvc.merge(entity);
				aj.setSuccess(true);
				aj.setMsg("保存成功.");
				aj.setObj(entity.getGstId());
			} catch (Exception e) {
				aj.setSuccess(false);
				aj.setMsg("保存失败");
				log.error("[Marketing][saveFguest]失败："+e);
			}
			return aj;
		}
		/**
		 * 加载数据对象
		 * @param id
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/marketing/getFguest.do")
		public AjaxJson getFguest(int id){
			AjaxJson aj = new AjaxJson();
			Fguest entity = fguestSvc.get(id);
			if(null==entity){
				aj.setSuccess(false);
				aj.setMsg("数据记录不存在");
				return aj;
			}
			FguestVo fv = new FguestVo();
			BeanUtils.copyProperties(entity, fv);
			if(StrUtils.isValidString(entity.getCompId())){
				try{
					TaDoc td = taDocSvc.get(Integer.parseInt(entity.getCompId().trim()));
					if(null!=td){
						fv.setUnitNamec(td.getNamec());
					}
				}catch(Exception e){
					log.info("没有选择合约单位");
				}
			}
			
			aj.setSuccess(true);
			aj.setObj(fv);
			return aj;
		}
	//------------------------add code about 熟客资料-------------------------//
	
	
	
	/** 
	 * 跳转至特别档案页面
	 * @param 特别档案
	 * @param searchVo
	 * @return
	 */
	//------------------------add code about 特别档案-------------------------//
}
