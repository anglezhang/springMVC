package com.cyw.mammoth.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.dao.ReportDocDao;
import com.google.gson.Gson;

@Controller
@RequestMapping("/reportdoc")
public class ReportDocAction {

	
	@Autowired
	private ReportDocDao reportDocDao;
	@Autowired
	private ParameterDao parameterDao;
	
	@RequestMapping("/list.do")
	public String getReportList(){
		return "report/report";
	}
	
	/**
	 * 当前可卖房查询
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getreportdoclist")
	public @ResponseBody String getReportDocList(@RequestParam String isShortCut, @RequestParam String rptType, @RequestParam String rptId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List list = reportDocDao.getReportDocList(isShortCut, rptType, rptId);
		resultMap.put("datalist", list);
		Parameter para = parameterDao.findWeek();
		resultMap.put("reportPath", para.getPara5());
		
		Gson gson = new Gson();
		return gson.toJson(resultMap);
	}
	
}
