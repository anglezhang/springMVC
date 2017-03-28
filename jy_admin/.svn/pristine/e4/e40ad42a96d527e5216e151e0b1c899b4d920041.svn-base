package com.zoomoor.jy.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zoomoor.common.base.bean.Finder;
import com.zoomoor.common.base.bean.Pager;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.common.util.ArrayUtils;
import com.zoomoor.jy.dao.ServiceitemDao;
import com.zoomoor.jy.entity.InfoBrand;
import com.zoomoor.jy.entity.Serviceitem;

/**
 * 描述：ServiceitemDaoImpl.java
 * 
 * @author Zhangzhenxing
 * @Date 2015年7月20日 上午9:53:02
 * @version 1.0
 */
@Repository
public class ServiceitemDaoImpl extends BaseDaoImpl<Serviceitem, Integer>
		implements ServiceitemDao {

	private static final Logger log = LoggerFactory
			.getLogger(ServiceitemDaoImpl.class);

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年7月20日
	 */
	@Override
	public Pager getPager(Pager pager, String queryItemCode,
			String queryItemName,String queryPinyin,Integer queryAppType,InfoBrand infoBrand) {
		Finder f = buildListFind(queryItemCode, queryItemName);
		if(queryAppType != null){
			f.append(" and bean.servicetype.servicetypeId =:queryAppType");
			f.setParam("queryAppType", queryAppType);
		}
		Integer id = infoBrand.getId();
		if(id != null){
			f.append(" and bean.infoBrand.id =:infoBrand");
			f.setParam("infoBrand", infoBrand.getId());
		}
		if(StringUtils.isNotEmpty(queryPinyin)){
			f.append(" and bean.pinyincode like :queryPinyin");
			f.setParam("queryPinyin", "%" + queryPinyin + "%");
		}
		f.append(" order by bean.itemId desc");
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

	/**
	 * 返回finder
	 * */
	private Finder buildListFind(String queryItemCode, String queryItemName) {
		Finder f = Finder
				.create("FROM Serviceitem bean WHERE 1=1 and bean.del=0 ");
		if (StringUtils.isNotEmpty(queryItemCode)) {
			f.append(" and bean.itemcode like :queryItemCode");
			f.setParam("queryItemCode", "%" + queryItemCode + "%");
		}
		if (StringUtils.isNotEmpty(queryItemName)) {
			f.append(" and bean.itemname like :queryItemName");
			f.setParam("queryItemName", "%" + queryItemName + "%");
		}
		return f;
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月3日
	 */
	@Override
	public Pager listPager(Pager pager, String queryItemCode,
			String queryItemName, String itemIdS, Integer id) {
		Finder f = buildListFind(queryItemCode, queryItemName);
		if (StringUtils.isNotEmpty(itemIdS)) {
			if (itemIdS.contains(",")) {
				StringBuffer appSql = new StringBuffer(
						"and bean.itemId not in ( ");
				String[] ids = itemIdS.split(",");
				for (String goodId : ids) {
					appSql.append(goodId + ",");
				}
				appSql.append(" -1 )");
				f.append(appSql.toString());
			} else {
				f.append(" and bean.itemId !=" + Integer.parseInt(itemIdS));
			}
		}
		if (id != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(" and bean.infoBrand.id=" + id + " ");
			f.append(sb.toString());
		}
		f.append(" order by bean.itemId desc");
		return find(f, pager.getPageNum(), pager.getNumPerPage());
	}

	/**
	 * 描述：该方法作用
	 * 
	 * @author zhangZhenxing
	 * @Date 2015年8月18日
	 */
	@Override
	public List getLooklist(Integer carId, String queryItemCode,
			String queryItemName, String itemIdS) {
		Connection conn = null;
		List list = null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			CallableStatement call = (CallableStatement) conn
					.prepareCall("{CALL u_GetServiceItems(?,?,?,?)}");
			log.info("{CALL GetServiceItems(" + carId + "," + queryItemCode
					+ "," + queryItemName + "," + itemIdS + ")}");
			call.setInt(1, carId);
			call.setString(2, queryItemCode);
			call.setString(3, queryItemName);
			if("0".equals(itemIdS)){
				call.setInt(4, 0);
			}else
				call.setString(4, itemIdS);
			//结果
			ResultSet rs = call.executeQuery();
			list = ArrayUtils.resultSetToList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return list;
		}

	}

}
