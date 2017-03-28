package com.zoomoor.jy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.service.impl.BaseSvcImpl;
import com.zoomoor.jy.dao.AllocationDao;
import com.zoomoor.jy.dao.CustomeOrderstatusDao;
import com.zoomoor.jy.dao.CustomerAppoitDao;
import com.zoomoor.jy.dao.CustomerEntrustDao;
import com.zoomoor.jy.dao.CustomerEntrustSubDao;
import com.zoomoor.jy.dao.InfoCarDao;
import com.zoomoor.jy.dao.InfoCustomeDao;
import com.zoomoor.jy.dao.InfoGoodsDao;
import com.zoomoor.jy.dao.ServiceitemDao;
import com.zoomoor.jy.entity.CarCustmerMapping;
import com.zoomoor.jy.entity.CustomeOrderstatus;
import com.zoomoor.jy.entity.CustomerAppoit;
import com.zoomoor.jy.entity.CustomerEntrust;
import com.zoomoor.jy.entity.CustomerEntrustSub;
import com.zoomoor.jy.entity.InfoCar;
import com.zoomoor.jy.entity.InfoCustome;
import com.zoomoor.jy.entity.InfoGoods;
import com.zoomoor.jy.entity.Serviceitem;
import com.zoomoor.jy.entity.vo.CusEntrusAmout;
import com.zoomoor.jy.entity.vo.CusEntrustGoodsVo;
import com.zoomoor.jy.service.CustomerEntrustSvc;
import com.zoomoor.jy.util.MathUtil;

/**
 * 描述：CustomerEntrustSvcImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月31日 下午2:00:26
 * @version 1.0
 */
@Service
public class CustomerEntrustSvcImpl extends
		BaseSvcImpl<CustomerEntrust, Integer> implements CustomerEntrustSvc {

	@Resource
	private CustomerEntrustDao dao;

	/**
	 * 车辆信息
	 * */
	@Resource
	private InfoCarDao carDao;

	/**
	 * 客户对象
	 * */
	@Resource
	private InfoCustomeDao customDao;

	/**
	 * 预约单对象
	 * */
	@Resource
	private CustomerAppoitDao appDao;

	/**
	 * 子预约单
	 * */
	@Resource
	private CustomerEntrustSubDao subDao;

	/**
	 * 委托单操作记录
	 * */
	@Resource
	private CustomeOrderstatusDao orderDao;

	/**
	 * 服务项目DAO
	 * */
	@Resource
	private ServiceitemDao itemDao;

	/**
	 * 物料DAO
	 * */
	@Resource
	private InfoGoodsDao goodsDao;

	@Autowired
	public void setBaseDao(CustomerEntrustDao dao) {
		super.setBaseDao(dao);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月3日
	 */
	@Override
	public void saveCustomEntrus(CustomerEntrust customEn, InfoCustome infoCus,
			InfoCar infoCar) {
		Integer cusAppitId = customEn.getCusappoitId();
		// 根据车辆ID 和 客户对象id 修改委托单中车辆和客户
		InfoCar entityCar = carDao.get(infoCar.getCarId());
		InfoCustome infoCuEntity = customDao.get(infoCus.getCustomerId());
		if (cusAppitId != null) {
			CustomerAppoit cusApp = appDao.get(customEn.getCusappoitId());// 获取持久预约
			cusApp.setInfoCar(entityCar);
			cusApp.setInfoCustomer(infoCuEntity);
			cusApp.setCus(2);// 订单状态修改为 已履约
			appDao.update(cusApp);
		}
		// Mapping对象
		CarCustmerMapping mapping = new CarCustmerMapping();
		mapping.setInfoCar(infoCar);
		mapping.setInfoCustome(infoCuEntity);
		customEn.setCarCusMap(mapping);

		// 保存
		dao.saveCustomerEntrusSave(createSetMain(customEn, true),
				createRowlist(customEn));
	}

	/**
	 * 创建存储过程所需xml
	 * */
	private String createSetMain(CustomerEntrust customEn, boolean isNew) {
		StringBuffer sb = new StringBuffer();
		sb.append("'<main id=\"");
		if (isNew)
			sb.append("0" + "\" ");
		else
			sb.append(customEn.getEntrustId() + "\" ");
		sb.append("dep_id=\"");
		sb.append(customEn.getInfoDept().getDeptId() + "\" ");// 接单部门ID
		sb.append(" vipid=\""
				+ customEn.getCarCusMap().getInfoCustome().getCustomerId()
				+ "\" ");
		sb.append(" carid=\"" + customEn.getCarCusMap().getInfoCar().getCarId()
				+ "\" ");
		sb.append(" ssss=\"" + customEn.getFixplace() + "\" ");// 维修地点 若为本店该值为0
		sb.append(" receiver=\"" + customEn.getReceptionist() + "\" ");// 接待人员
		sb.append(" worker=\"" + customEn.getFixer() + "\" ");// 维修人员
		sb.append(" et=\""
				+ MathUtil.fmtDate(customEn.getEstimatetime(),
						"yyyy-MM-dd HH:mm:ss") + "\" ");// 建单时间
		Integer cusAppitId = customEn.getCusappoitId();
		if (cusAppitId != null) {
			sb.append("yyid=\"" + cusAppitId + "\"");
		} else
			sb.append("yyid=\"0\"");
		sb.append(" memo=\"" + customEn.getRemark() + "\" ");
		sb.append(" />'");
		return sb.toString();
	}

	/**
	 * 子表存储过程
	 * */
	private String createRowlist(CustomerEntrust customEn) {
		StringBuffer sb = new StringBuffer();
		// 添加表头
		sb.append("'");
		// 服务项目
		Serviceitem[] items = customEn.getItems();
		if (items != null) {
			for (Serviceitem item : items) {
				if (item.getItemId() != null) {
					sb.append("<row ");
					sb.append("id=\" " + item.getEntrustsubId() + "\" ");
					sb.append("item_id=\"" + item.getItemId() + "\" ");
					sb.append("servicetype=\"0\" ");
					sb.append("goods_id=\"0\" ");
					sb.append("num=\"0\" ");
					sb.append("price=\"0\"");
					sb.append(" />");
				}

			}
		}
		sb.append("'");
		return sb.toString();
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月4日
	 */
	@Override
	public Pager getPager(Pager pager, String queryOrderNO,
			String queryPlatenum, String queryCusName, String queryStatus,
			String queryFixplace, String queryAppType,
			String queryOrderBeginTime, String queryOrderEndTime,
			String queryOperator, String queryFixMan, Integer deptId) {

		if (StringUtils.isEmpty(queryStatus)) {
			queryStatus = "-1";
		}
		return dao.getPager(pager, queryOrderNO, queryPlatenum, queryCusName,
				queryStatus, queryFixplace, queryAppType, queryOrderBeginTime,
				queryOrderEndTime, queryOperator, queryFixMan, deptId);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月5日
	 */
	@Override
	public void updateCustomEntrus(CustomerEntrust customEn,
			InfoCustome infoCus, InfoCar infoCar) {
		InfoCustome infoCuEntity = customDao.get(infoCus.getCustomerId());
		// Mapping对象
		CarCustmerMapping mapping = new CarCustmerMapping();
		mapping.setInfoCar(infoCar);
		mapping.setInfoCustome(infoCuEntity);
		customEn.setCarCusMap(mapping);
		// 删除分表
//		dao.deleteSunCusEntrust(customEn.getEntrustId());
		// TODO
		dao.saveCustomerEntrusSave(createSetMain(customEn, false),
				createRowlist(customEn));
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月5日
	 */
	@Override
	public void deleteByOrderID(Integer entrustId) {
		CustomerEntrust entity = dao.get(entrustId);
		entity.setDel(true);
		dao.update(entity);
	}

	/**
	 * 描述:结算 1.本委托单出库的配件 折扣后费用 2.本委托单的工时费 修改本委托单状态：customer_entrust
	 * 记录仓库单据depot_bill id 记录委托单状态：custome_orderstatus
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月5日
	 */
	@Override
	public boolean amount(Integer entrustId, Integer accountmanID,
			Double workHourAmout, Double goodsAmout) {
		CustomerEntrust entity = dao.get(entrustId);
		String number = entity.getCusEnstrustNum();
		// 结算单号
		String jsdh = number.replace("FW", "JS");
		return dao.amount(entrustId, accountmanID,workHourAmout, goodsAmout,jsdh);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月5日
	 */
	@Override
	public CusEntrusAmout getAmont(Integer entrustId) {
		CusEntrusAmout entity = new CusEntrusAmout();
		// 设置值
		CustomerEntrust cusEntrust = dao.get(entrustId);
		entity.setDiscount(cusEntrust.getDiscount());
		entity.setGoodsAmout(goodsDao.getGoodsPriceCount(entrustId));
		entity.setServiceCount(dao.serviceCount(entrustId));
		entity.setWorkHourAmout(dao.workHourAmout(entrustId));
		Double payCount = entity.getGoodsAmout() + entity.getWorkHourAmout();
		entity.setPayAmout(MathUtil.toFixed(
				payCount * cusEntrust.getDiscount(), 2));// 应付金额
		entity.setSumAmout(payCount);// 总金额
		return entity;
	}

	@Override
	public Pager getSettlementPager(Pager pager, String queryCarInf,
			String queryPlatenum, Integer queryStatu, Integer queryFixplace,
			Integer serviceType, String queryBeginTime, String queryEndTime,
			Integer deptId) {
		/**
		 * 描述：该方法作用
		 * 
		 * @author zhangZhenxing
		 * @Date 2015年8月13日
		 */
		return dao.getSettlementPager(pager, queryCarInf, queryPlatenum,
				queryStatu, queryFixplace, serviceType, queryBeginTime,
				queryEndTime, deptId);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月13日
	 */
	@Override
	public List<Map<Integer, Integer>> getSettlementCount(Integer deptId) {

		return null;
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月13日
	 */
	@Override
	public void setSettleSave(Integer entrustId, Double reallymoney,
			Integer empid) {

		CustomerEntrust entity = dao.get(entrustId);
		saveOrder(entrustId, empid, 3, null);// 设置为已经结清
		entity.setReallymoney(reallymoney);
		entity.setCusEntats(3);
		dao.update(entity);
	}

	private void saveOrder(Integer entrustId, Integer empid, Integer status,
			Integer tagId) {
		CustomerEntrust entity = dao.get(entrustId);
		// 结清记录
		CustomeOrderstatus order = new CustomeOrderstatus();
		order.setCustomerEntrust(entity);
		order.setDel(false);
		order.setOperator(empid);
		order.setStatusKey(status);// 设置状态
		order.setOpertime(new Date(System.currentTimeMillis()));
		order.setTagId(tagId);
		orderDao.save(order);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月13日
	 */
	@Override
	public void setBillsave(Integer entrustId, Integer billcustomerID,
			Integer empId) {
		CustomerEntrust entity = dao.get(entrustId);
		saveOrder(entrustId, empId, 2, billcustomerID);// 设置为挂
		entity.setCusEntats(2);
		dao.update(entity);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月13日
	 */
	@Override
	public void billsettlesave(Integer entrustId, Double reallymoney,
			Integer empId) {
		CustomerEntrust entity = dao.get(entrustId);
		saveOrder(entrustId, empId, 3, null);// 设置为结清
		entity.setReallymoney(reallymoney);
		entity.setCusEntats(3);
		dao.update(entity);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月15日
	 */
	@Override
	public List<Map<Integer, Integer>> getCustomeCount(Integer deptId,
			Integer fixplace) {
		if (fixplace == null) {
			fixplace = -1;
		}
		if (deptId == null) {
			deptId = -1;
		}
		return dao.getCustomCount(deptId, fixplace);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月16日
	 */
	@Override
	public List<CusEntrustGoodsVo> getGoodsByEntrustId(Integer entrustId) {
		if (entrustId == null) {
			entrustId = -1;
		}
		return goodsDao.getGoodsByEntrust(entrustId);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月17日
	 */
	@Override
	public Double getEntrustPrice(Integer entrustId) {
		if (entrustId == null) {
			entrustId = -1;
		}
		return goodsDao.getGoodsPriceCount(entrustId);
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月17日
	 */
	@Override
	public List getCustomerList(Integer deptId, String queryOrderNO,
			String queryPlatenum, String queryCusName, Integer queryStatus,
			Integer queryFixplace, String queryOrderBeginTime,
			String queryOrderEndTime, String queryOperator, String queryFixMan) {

		return dao.getCustomerList(deptId, queryOrderNO, queryPlatenum,
				queryCusName, queryStatus, queryFixplace, queryOrderBeginTime,
				queryOrderEndTime, queryOperator, queryFixMan);
	}
}
