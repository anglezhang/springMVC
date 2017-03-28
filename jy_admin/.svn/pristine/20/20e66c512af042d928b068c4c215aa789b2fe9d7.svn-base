package com.zoomoor.jy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import com.zoomoor.common.base.dao.impl.BaseDaoImpl;
import com.zoomoor.jy.dao.ConfigEmpMappingDao;
import com.zoomoor.jy.entity.ConfigEmpMapping;

/**
 * 描述：ConfigEmpMappingDaoImpl.java
 * @author Zhangzhenxing
 * @Date 2015年8月8日 下午3:05:39
 * @version 1.0
 */
@Repository
public class ConfigEmpMappingDaoImpl extends BaseDaoImpl<ConfigEmpMapping, Integer> implements ConfigEmpMappingDao{

	private static final Logger log = LoggerFactory
			.getLogger(ConfigEmpMappingDaoImpl.class);
	/**
	* 描述：该方法作用
	* @author zhangZhenxing
	* @Date 2015年8月11日
	*/
	@Override
	public void deleteByEmpId(Integer empId) {
		String sql = "DELETE FROM config_emp_mapping WHERE emp_id=" + empId;
		Connection conn = null;
		try{
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			log.debug("删除 empid=" + empId + "的 config_emp_mapping");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					log.error("connnection close error");
					e.printStackTrace();
				}
			}
		}
	}

}
