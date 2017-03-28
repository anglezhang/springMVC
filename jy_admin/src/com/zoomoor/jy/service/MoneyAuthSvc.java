package com.zoomoor.jy.service;

import java.util.List;

import com.zoomoor.common.base.service.BaseSvc;
import com.zoomoor.jy.entity.MoneyAuth;
import com.zoomoor.jy.entity.MoneyUserMapping;

public interface MoneyAuthSvc extends BaseSvc<MoneyAuth, Integer> {
public List<MoneyAuth> getMoneyAuthListByIsChecked(List<MoneyAuth> moneyAuthListr,Integer rid);
}
