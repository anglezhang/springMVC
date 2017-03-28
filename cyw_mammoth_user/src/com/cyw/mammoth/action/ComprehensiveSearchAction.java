package com.cyw.mammoth.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyw.common.util.DateUtils;
import com.cyw.mammoth.bean.HroomType;
import com.cyw.mammoth.dao.HroomTypeDao;
import com.cyw.mammoth.dao.ParameterDao;
import com.cyw.mammoth.dao.RoomNumDao;
import com.cyw.mammoth.dao.RoomsDao;
import com.cyw.mammoth.service.ParameterSvc;
import com.google.gson.Gson;

@Controller
@RequestMapping("/search")
public class ComprehensiveSearchAction {

	
	@Autowired
	private RoomsDao roomsDao;
	@Autowired
	private HroomTypeDao hroomTypeDao;
	@Autowired
	private RoomNumDao roomNumDao;
	@Autowired
	private ParameterDao parameterDao;
	
	@RequestMapping("/searchindex.do")
	public String searchIndex(ModelMap model){
		model.addAttribute("isSearchPage", 1);
		return "search/searchIndex";
	}
	
	/**
	 * 当前可卖房查询
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getcurravailableforsalerooms")
	public @ResponseBody String getCurrAvailableForSaleRooms() {
		Map<String, String> roomsStatMap = new HashMap<String, String>();
		roomsStatMap.put("VDP", "不洁空房");
		roomsStatMap.put("VCP", "清洁未查空房");
		roomsStatMap.put("VCI", "清洁已查空房");
		roomsStatMap.put("ODP", "不洁住房");
		roomsStatMap.put("OCP", "清洁未查住房");
		roomsStatMap.put("OCI", "清洁已查住房");
		roomsStatMap.put("VD", "不洁空房");
		roomsStatMap.put("VC", "清洁空房");
		roomsStatMap.put("OD", "不洁住房");
		roomsStatMap.put("OC", "清洁住房");
		
		//当前可售房按房态、房类、楼层统计
		List list = roomsDao.getCurrAvailableForSaleRooms();
		//把需要合并的行统计好加入统计数据中
		int x_old = 0, y_old = 0;
		int stat_row_num = 0, type_row_num = 0;
		String stat = "", type = "";
		for (int i = 0; i < list.size(); i++) {
			Map mp = (Map)list.get(i);
			mp.put("curr_stat_text", roomsStatMap.get(mp.get("curr_stat").toString().trim()));
			if(i == 0){
				x_old = i;
				y_old = i;
				stat = mp.get("curr_stat").toString().trim();
				type = mp.get("room_type").toString().trim();

				stat_row_num = 1;
				type_row_num = 1;
			}else{
				if(mp.get("curr_stat").toString().trim().equals(stat)){
					mp.put("stat_row_num", "0");
					stat_row_num++;
				}else{
					Map map = (Map)list.get(x_old);
					map.put("stat_row_num", stat_row_num + "");
					Map map1 = (Map)list.get(y_old);
					map1.put("type_row_num", type_row_num + "");
					x_old = i;
					y_old = i;
					stat_row_num = 1;
					type_row_num = 1;
					stat = mp.get("curr_stat").toString().trim();
					type = mp.get("room_type").toString().trim();
					continue;
				}
				if(mp.get("room_type").toString().trim().equals(type)){
					mp.put("type_row_num", "0");
					type_row_num++;
				}else{
					Map map = (Map)list.get(y_old);
					map.put("type_row_num", type_row_num + "");
					y_old = i;
					type_row_num = 1;
					type = mp.get("room_type").toString().trim();
				}
			}
		}
		Map map = (Map)list.get(x_old);
		map.put("stat_row_num", stat_row_num + "");
		Map map1 = (Map)list.get(y_old);
		map1.put("type_row_num", type_row_num + "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("datalist", list);
		//返回数据中加入日期和时间信息
		Date now = new Date();
		resultMap.put("datestr", new SimpleDateFormat("yyyy-MM-dd").format(now));
		resultMap.put("timestr", new SimpleDateFormat("HH:mm:ss").format(now));
		Gson gson = new Gson();
		return gson.toJson(resultMap);
	}
	
	
	/**
	 * 房间存量查询
	 * @param startDate
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getroomnumbydate")
	public @ResponseBody String getRoomNumByDate(@RequestParam String startDate){
		Map resultMap = new HashMap();
		resultMap.put("issuccess", true);
		Gson gson = new Gson();
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date endDate;
			if(startDate == null || startDate.trim().equals("")){
				endDate = DateUtils.getSpecficDateStart(parameterDao.GetHotelDate(), 1);
			}else{
				endDate = dateFormat2.parse(startDate.trim());
			}
			//每天每种房类统计信息
			List list = roomNumDao.getRoomNumByDate(dateFormat2.format(endDate), dateFormat2.format(DateUtils.getSpecficDateStart(endDate, 13)));
			//所有防雷信息
			List<HroomType> typeList = hroomTypeDao.getAll();
			resultMap.put("datalist", list);
			resultMap.put("typelist", typeList);
			
			int room_num = 0;
			for (HroomType type : typeList) {
				room_num += type.getNum();
			}
			
			//根据房类统计信息构建表头
			List<Map<String, String>> headList = new ArrayList<Map<String,String>>();
			Map<String, String> tempMap = new HashMap<String, String>();
			for (int i = 0; i < list.size(); i++) {
				Map mp = (Map)list.get(i);
				String date_day = mp.get("date_day").toString();
				if(null == tempMap.get(date_day)){
					Map<String, String> map = new HashMap<String, String>();
					map.put("date_day", date_day);
					map.put("date_weak", mp.get("date_weak").toString().replace("星期", "周"));
					headList.add(map);
					tempMap.put(date_day, mp.get("date_weak").toString());
				}
			}
			
			//统计信息做二次统计
			DecimalFormat format = new DecimalFormat("######0.00");
			for (int i = 0; i < headList.size(); i++) {
				Map<String, String> map = headList.get(i);
				int room_use_num = 0;
				int room_nouse_num = 0;
				for(int j = 0; j < list.size(); j++){
					Map mp = (Map)list.get(j);
					String date_day = mp.get("date_day").toString();
					if(map.get("date_day").equals(date_day)){
						room_use_num += Integer.parseInt(mp.get("use_num").toString());
						room_nouse_num += Integer.parseInt(mp.get("nouse_num").toString());
					}
				}
				map.put("room_use_num", Integer.toString(room_use_num));
				map.put("room_nouse_num", Integer.toString(room_nouse_num));
				map.put("use_ratio", format.format(Double.parseDouble(Integer.toString(room_use_num)) * 100 / Double.parseDouble(Integer.toString(room_num))));
			}
			resultMap.put("headlist", headList);
		} catch (Exception e) {
			resultMap.put("issuccess", false);
			e.printStackTrace();
		}
		return gson.toJson(resultMap);
	}
}
