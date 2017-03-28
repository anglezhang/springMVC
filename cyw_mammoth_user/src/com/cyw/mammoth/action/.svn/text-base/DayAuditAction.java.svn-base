package com.cyw.mammoth.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyw.common.util.DateUtil;
import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.service.DayAuditSvc;
import com.cyw.mammoth.service.ParameterSvc;
import com.google.gson.Gson;

@Controller
@RequestMapping("/dayaudit")
public class DayAuditAction {

	private static Queue<Map<String, Object>> queue = new LinkedList<Map<String,Object>>();
	
	@Autowired
	private DayAuditSvc dayAuditSvc;

	@Autowired
	private ParameterSvc parameterSvc;
	
	/**
	 * 跳转到夜审页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index.do")
	public String booklist(ModelMap model) {
		return "dayaudit/auditindex";
	}
	
	/**
	 * 开始夜审
	 * @return
	 */
	@RequestMapping(value="/execute", method=RequestMethod.POST)
	public @ResponseBody String getReportDocList(@RequestParam int currStep, @RequestParam int currSubStep, @RequestParam int sqlStep, @RequestParam int handle) {
		Map<String, Object> result = dayAuditSvc.dayAudit(AppBaseCfg.getOperator().getOperId(), currStep, currSubStep, sqlStep, queue, handle);
		return new Gson().toJson(result);
	}
	
	/**
	 * 获取夜审结论
	 * @param timed
	 * @return
	 */
	@RequestMapping(value="/getresult", method=RequestMethod.POST)
	public @ResponseBody String getDayAuditResult(long timed){
		while (true) {
			Map<String, Object> result = queue.poll();
			if(result != null){
				return new Gson().toJson(result);
			}
			
		}
	}
	
	/**
	 * 查询夜审头信息（营业日，操作员）
	 * @return
	 */
	@RequestMapping(value="/getdayaudithead", method=RequestMethod.GET)
	public @ResponseBody String getDayAuditHead(){
		String hotelDate = DateUtil.getDate(parameterSvc.GetHotelDate());
		Map<String, String> result = new HashMap<String, String>();
		result.put("hotelDate", hotelDate);
		result.put("oper", AppBaseCfg.getOperator().getOperId());
		
		return new Gson().toJson(result);
	}
	
	/**
	 * 夜审检查另存为txt文件
	 * @param response
	 * @param text
	 */
	@RequestMapping("download")  
	public void download(HttpServletResponse response, String text){
		String ontent = text.replaceAll("\\<p>|</p>", "\r\n");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition",
					"attachment; filename=dict.txt");
			response.setContentType("application/octet-stream; charset=utf-8");
			os.write(ontent.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	@RequestMapping(value="/handleroomprice", method=RequestMethod.GET)
	public @ResponseBody String handleRoomPrice(){
		boolean result = dayAuditSvc.handleRoomPrice();
		
		return new Gson().toJson(result);
	}
}
