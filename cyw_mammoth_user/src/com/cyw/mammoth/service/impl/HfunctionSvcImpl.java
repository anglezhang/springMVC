package com.cyw.mammoth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyw.common.base.service.impl.BaseSvcImpl;
import com.cyw.common.util.CommonUtil;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.Hfunction;
import com.cyw.mammoth.bean.HfunctionControl;
import com.cyw.mammoth.dao.HfunctionControlDao;
import com.cyw.mammoth.dao.HfunctionDao;
import com.cyw.mammoth.service.HfunctionSvc;
import com.cyw.mammoth.vo.HfunctionControlVO;
import com.cyw.mammoth.vo.HfunctionVO;

@Service
public class HfunctionSvcImpl extends BaseSvcImpl<Hfunction, Integer> implements HfunctionSvc {

	@Autowired
	private HfunctionDao hfunctionDao;
	@Autowired
	private HfunctionControlDao hfunctionControlDao ;
	
	public void setHfunctionDao(HfunctionDao hfunctionDao) {
		this.hfunctionDao = hfunctionDao;
		setBaseDao(hfunctionDao);
	}

	@Override
	public List<HfunctionVO> findListBy(Integer status) throws Exception {
		return hfunctionDao.findListBy(status==null?0:status);
	}
	
	@Override
	public void updateStatusById(String[] ids, Integer status) throws Exception  {
		if(ids!=null){
			for(String id:ids){
				// 删除功能控件
				hfunctionControlDao.deleteByParentId(Integer.valueOf(id)) ;
				// 删除功能
				hfunctionDao.delete(Integer.valueOf(id));
			}
		}
	}

	@Override
	public void saveOrUpdateOrDel(List<HfunctionVO> hfunctions, String delIds) throws Exception {
		boolean delBool = StringUtils.isNotBlank(delIds) ;
		if(hfunctions !=null && hfunctions.size() > 0){
			Hfunction hf = null ;
			for (HfunctionVO hfunctionVO : hfunctions) {
				hf = hfunctionVO.getHfunction() ;
				Integer id = hf.getId() ;
			    // 修改
				if(hf.getId() != null){
					// 该对象同时存在被删除的对象中或者该对象中的在数据库重复  
 					if(delBool && delIds.contains(hf.getId().toString()))
						continue ;
 					hfunctionDao.update(hf) ;
 					saveOrUpdateOrDel_hfunctionControl(id, hfunctionVO.getHfunctionControlVOs()) ;
				}
				// 新增
				else{
					hf.setFunctionId(AppBaseCfg.hfuncFIdGen()) ;
					hf.setCtrlType(AppBaseCfg.HFUNC_LEVEL_ONE_TYPE) ;
					id = hfunctionDao.save(hf) ;
					saveOrUpdateOrDel_hfunctionControl(id,  hfunctionVO.getHfunctionControlVOs()) ;
				}
			}
		}
		// 删除
		if(delBool){
			String[] strArr = CommonUtil.array_unique(delIds.split(",")) ;
			this.updateStatusById(strArr , 1 );
		}
	}
	private void saveOrUpdateOrDel_hfunctionControl(Integer hfunctionId , List<HfunctionControlVO> hfunctionControlVOs) throws Exception{
		if(hfunctionControlVOs !=null && hfunctionControlVOs.size() > 0){
			HfunctionControl hfunctionControl = null ;
			HfunctionControl hfc = null ;
			for (HfunctionControlVO hfunctionControlVO : hfunctionControlVOs) {
				hfunctionControl = hfunctionControlVO.getHfunctionControl();
				hfunctionControl.setParentId(hfunctionId) ;
				if("a".equals(hfunctionControlVO.getFlag())){
					hfunctionControl.setFunctionId(AppBaseCfg.hfuncCIdGen()) ;
					hfunctionControlDao.save(hfunctionControl) ;
				}else if("u".equals(hfunctionControlVO.getFlag())){
					hfc = hfunctionControlDao.get(hfunctionControl.getId()) ;
					hfunctionControl.setChainId(hfc.getChainId());
					hfunctionControl.setVilhotelId(hfc.getVilhotelId());
					hfunctionControl.setParentId(hfc.getParentId());
					hfunctionControl.setFunctionId(hfc.getFunctionId());
					// 从session中移除相同的对象
					hfunctionControlDao.evict(hfc);
					hfunctionControlDao.update(hfunctionControl);
				}else if("d".equals(hfunctionControlVO.getFlag())){
					hfc = hfunctionControlDao.get(hfunctionControl.getId()) ;
					// 从session中移除相同的对象
					hfunctionControlDao.evict(hfunctionControl);
					hfunctionControlDao.delete(hfc);
				} 
			}
		}
	}
}
