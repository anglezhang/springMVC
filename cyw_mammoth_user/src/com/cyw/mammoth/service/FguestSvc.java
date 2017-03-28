package com.cyw.mammoth.service;

import java.util.List;

import com.cyw.common.base.service.BaseSvc;
import com.cyw.mammoth.bean.Fguest;
import com.cyw.mammoth.vo.FguestInfoVO;
import com.cyw.mammoth.vo.FguestSearchVo;
import com.cyw.mammoth.vo.FguestVo;

public interface FguestSvc extends  BaseSvc<Fguest, Integer> {
	/**
	 * 自定义查询
	 * @param searchVo
	 * @return
	 */
     public List<FguestVo> search(FguestVo searchVo);
     
     /**
      * @描述 查询熟客
      * @param fguestSearch 搜索对象
      * @return 熟客对象列表
      * */
     List<FguestInfoVO> getFguestList(FguestSearchVo fguestSearch);
}
