package com.cyw.mammoth.dao;

import java.util.List;

import com.cyw.common.base.dao.BaseDao;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.vo.TADocSearchVo;

public interface TADocDao  extends BaseDao<TaDoc,Integer>{
	public List<TaDoc> getAllTaDocList(TADocSearchVo tadoc);

}
