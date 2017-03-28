package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.TaDoc;
import com.cyw.mammoth.vo.TADocSearchVo;

public interface TADocSvc extends BaseSvc<TaDoc, Integer> {
	
	public List<TaDoc> getAllTaDocList(TADocSearchVo tadoc);
	
}
