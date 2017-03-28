package com.zoomoor.jy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.ConfigEmpMappingDao;
import com.zoomoor.jy.dao.InfoDeptDao;
import com.zoomoor.jy.dao.InfoEmpDao;
import com.zoomoor.jy.dao.ParamConfigDao;
import com.zoomoor.jy.entity.ConfigEmpMapping;
import com.zoomoor.jy.entity.InfoDept;
import com.zoomoor.jy.entity.InfoEmp;
import com.zoomoor.jy.entity.ParamConfig;
import com.zoomoor.jy.service.InfoEmpSvc;

@Service
public class InfoEmpSvcImpl extends BaseSvcImpl<InfoEmp, Integer> implements
		InfoEmpSvc {
	@Resource
	private InfoEmpDao dao;
	
	@Resource
	private ParamConfigDao paramDao;
	
	@Resource
	private ConfigEmpMappingDao cifMapDao;
	
	/**
	 * 描述:部门dao
	 * */
	@Resource
	private InfoDeptDao deptDao;

	@Autowired
	public void setBaseDao(InfoEmpDao dao) {
		super.setBaseDao(dao);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月3日
	 */
	@Override
	public List<InfoEmp> getEmpByType(String type, Integer deptId) {

		return dao.getEmpByType(type, deptId);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月7日
	 */
	@Override
	public Pager getLookPager(Pager pager, String queryName, String queryIdCode,Integer islook) {

		return dao.getPager(pager, null, null, queryName, null, null, null,
				queryIdCode,islook);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月7日
	 */
	@Override
	public Pager getPager(Pager pager, Integer queryDept, String queryEmpCode,
			String queryEmpName, Integer queryGender, String queryPosition,
			Integer queryStatus) {

		return dao.getPager(pager, queryDept, queryEmpCode, queryEmpName,
				queryGender, queryPosition, queryStatus, null,null);
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月8日
	*/
	@Override
	public Integer saveEmplyeer(InfoEmp infoEmp) {
		ConfigEmpMapping[] professConfig = infoEmp.getProfessConfigs();
		InfoEmp entity = dao.get(infoEmp.getEmpId());
		//删除
		cifMapDao.deleteByEmpId(infoEmp.getEmpId());		
		if(professConfig !=null && professConfig.length > 0){
			for(ConfigEmpMapping cfgMap:professConfig){
				ParamConfig pcfg = cfgMap.getParamConfig();
				if(pcfg != null){
					Integer paramID = pcfg.getUnitId();
					if(paramID != null){
						ParamConfig paraEntity = paramDao.get(cfgMap.getParamConfig().getUnitId());
						cfgMap.setInfoEmp(entity);
						cfgMap.setParamConfig(paraEntity);
						cfgMap.setDel(false);
						cifMapDao.save(cfgMap);
					}
				}
			}
		}
		return null;
	}

	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月11日
	*/
	@Override
	public boolean updateInfoEmp(InfoEmp infoEmp) {
		InfoEmp entity = dao.get(infoEmp.getEmpId());
		//更新值
		entity.setUrl(infoEmp.getUrl());
		entity.setEmpCode(infoEmp.getEmpCode());
		entity.setEmpName(infoEmp.getEmpName());
		entity.setIdCard(infoEmp.getIdCard());
		entity.setGender(infoEmp.getGender());
		//部门
		InfoDept dept = deptDao.get(infoEmp.getInfoDept().getDeptId());
		entity.setInfoDept(dept);
		entity.setMobile(infoEmp.getMobile());
		entity.setAttn(infoEmp.getAttn());
		entity.setAttnTel(infoEmp.getAttnTel());
		entity.setAge(infoEmp.getAge());
		entity.setEmail(infoEmp.getEmail());
		entity.setBirthday(infoEmp.getBirthday());
		entity.setNativePlace(infoEmp.getNativePlace());
		entity.setNation(infoEmp.getNation());
		entity.setIsmarry(infoEmp.getIsmarry());
		entity.setFormalDate(infoEmp.getFormalDate());
		entity.setPosition(infoEmp.getPosition());
		entity.setLcBDate(infoEmp.getLcBDate());
		entity.setLcEDate(infoEmp.getLcEDate());
		entity.setInDate(infoEmp.getInDate());
		entity.setOutDate(infoEmp.getOutDate());
		entity.setHolidayNum(infoEmp.getHolidayNum());
		entity.setRecruitSource(infoEmp.getRecruitSource());
		//驾照类型
		if(infoEmp.getDrivinglicense()!=null&&infoEmp.getDrivinglicense().getUnitId()!=null){
			ParamConfig driver = paramDao.get(infoEmp.getDrivinglicense().getUnitId());
			entity.setDrivinglicense(driver);
		}
		entity.setStartJob(infoEmp.getStartJob());
		entity.setEdu(infoEmp.getEdu());
		entity.setSchool(infoEmp.getSchool());
		entity.setInterest(infoEmp.getInterest());
		dao.update(entity);
		return false;
	}
}
