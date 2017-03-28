package com.cyw.mammoth.dao.impl;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.mammoth.bean.Parameter;
import com.cyw.mammoth.dao.ParameterDao;
@Repository
public class ParameterDaoImpl extends BaseDaoImpl<Parameter, Integer> implements
		ParameterDao {
	/** 
	 * 查询夜审状态
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public boolean getNightAuditState() {
		Connection conn_order=null;
		boolean state=false;
		try {
			conn_order = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_order = (CallableStatement) conn_order.prepareCall("{call NightFlag(?)}");
			call_order.registerOutParameter(1,java.sql.Types.BOOLEAN);
			call_order.execute();
			state=call_order.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_order.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
	}
	/** 
	 * 查询酒店当前日期
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public Date GetHotelDate() {
		Connection conn_order=null;
		Date state = null;
		try {
			conn_order = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_order = (CallableStatement) conn_order.prepareCall("{call GetHotelDate(?)}");
			call_order.registerOutParameter(1,java.sql.Types.DATE);
			call_order.execute();
			state=call_order.getDate(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_order.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
	}
	/** 
	 * 查询IP状态
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public boolean GetIPFlag() {
		Connection conn_order=null;
		boolean state=false;
		try {
			conn_order = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			CallableStatement call_order = (CallableStatement) conn_order.prepareCall("{call IPFlag(?)}");
			call_order.registerOutParameter(1,java.sql.Types.BOOLEAN);
			call_order.execute();
			state=call_order.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_order.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return state;
	}
	/** 
	 * 上传图片保存至数据库
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public void uploadImage(InputStream inword) {
		Connection conn_order=null;
		try {
			conn_order = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			Statement psmt= conn_order.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = psmt.executeQuery("select * from testImage where id=20");
			rs.next();
			rs.updateBinaryStream(2,inword,(int)inword.available());
			rs.updateString(3, "lwx");
			rs.updateRow();
			rs.close();
			conn_order.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn_order.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public InputStream getImageByte(String tableName,String colName,String where) {
		//OutputStream out=null;
		//OutputStream out2=null;
		InputStream inword=null;
		//InputStream inword2=null;
		try {
			Connection conn_order=null;
			conn_order = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			Statement psmt= conn_order.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = psmt.executeQuery("select "+ colName +" from "+tableName+" "+where);
			if(rs.next()){
				inword = rs.getBinaryStream(colName);
			}
//			try {
//				out=new FileOutputStream("d:/ff.png");
//				int ch=0;
//				while((ch=inword.read())!=-1){
//					out.write(ch);
//				}
//				inword2=rs.getBinaryStream("sign_bmp");	
//				out2=new FileOutputStream("d:/ff2.png");
//				int ch2=0;
//				while((ch2=inword2.read())!=-1){
//					out2.write(ch2);
//				}
//				
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//out.close();
//			//rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inword;
	}
	@Override
	public Parameter findWeek() {
		return (Parameter)getSession().createQuery("from Parameter where id=1001 ").uniqueResult();
	}
	
}
