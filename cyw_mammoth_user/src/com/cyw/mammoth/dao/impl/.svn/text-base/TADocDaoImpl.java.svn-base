package com.cyw.mammoth.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.dao.TADocDao;
import com.cyw.mammoth.vo.TADocSearchVo;

@Repository
public class TADocDaoImpl extends BaseDaoImpl<TaDoc, Integer> implements TADocDao {

	@Override
	public List<TaDoc> getAllTaDocList(TADocSearchVo searchVo) {
		String hql = "from TaDoc bean where 1=1 ";
		// * # 拥有最高优先级
		if (searchVo.getSymbol() != null && searchVo.getSymbol() >= 0) {
			// *所有无效
			if (searchVo.getSymbol() == 1) {
				hql += " and bean.status = 1  ";
			} else {
				// #所有有效
				hql += " and bean.status = 0";
			}
		}else if(StrUtils.isValidString(searchVo.getCodeLetter())){
			//#号 35 *号42
			int chatnum=(int)searchVo.getCodeLetter().charAt(0);
			if(chatnum==35){
				hql += " and bean.status = 1  ";
			}else if(chatnum==42){
				hql += " and bean.status = 0";
			}else{
				hql += " and bean.compId like '" + searchVo.getCodeLetter() + "%'";
				if(searchVo.isChkStat()){//有效
					hql += " and bean.status = 1  ";
				}else{
					hql += " and bean.status = 0  ";
				}
			}
		} else {
			hql += " and bean.status = " + (searchVo.isChkStat()?1:0) + "";
			if (StringUtils.isNotEmpty(searchVo.getTaType())) {
				hql += " and bean.taType = '" + searchVo.getTaType() + "'";
			}
			if (StringUtils.isNotEmpty(searchVo.getCompId())) {
				hql += " and bean.compId like '%" + searchVo.getCompId() + "%'";
			}
			if (StringUtils.isNotEmpty(searchVo.getNamec())) {
				hql += " and bean.namec like '%" + searchVo.getNamec() + "%'";
			}
			if (StringUtils.isNotEmpty(searchVo.getTele())) {
				hql += " and bean.tele like '%" + searchVo.getTele() + "%'";
			}
			if(StrUtils.isValidString(searchVo.getSalemanId())){
				hql += " and bean.salemanId like '%" + searchVo.getSalemanId() + "%'";
			}
			if (StringUtils.isNotEmpty(searchVo.getConnector())) {
				hql += " and bean.connector like '%" + searchVo.getConnector() + "%'";
			}
			
			if (StringUtils.isNotEmpty(searchVo.getPhone())) {
				hql += " and bean.phone like '%" + searchVo.getPhone() + "%'";
			}
			
			if (searchVo.getId() != null) {
				hql += " and bean.id like '%" + searchVo.getId() + "%'";
			}
			if (searchVo.getTaCompCd() != null) {
				hql += " and bean.taCompCd='" + searchVo.getTaCompCd() + "'";
			}
			if(searchVo.isChkStat()){//有效
				hql += " and bean.status = 1  ";
			}else{
				hql += " and bean.status = 0  ";
			}
		}
		hql+=" ORDER BY bean.namec asc";
		return this.createQueryList(hql);
	}

}
