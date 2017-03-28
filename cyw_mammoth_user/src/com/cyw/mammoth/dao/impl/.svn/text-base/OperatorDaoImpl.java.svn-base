package com.cyw.mammoth.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyw.common.base.bean.Finder;
import com.cyw.common.base.bean.Pager;
import com.cyw.common.base.dao.impl.BaseDaoImpl;
import com.cyw.common.util.StrUtils;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.bean.WorkGroup;
import com.cyw.mammoth.dao.OperatorDao;
import com.cyw.mammoth.dao.WorkGroupDao;
import com.cyw.mammoth.vo.OperatorListVO;
import com.cyw.mammoth.vo.OperatorVo;
import com.cyw.mammoth.vo.WorkGroupVO;

@Repository
public class OperatorDaoImpl extends BaseDaoImpl<Operator, String> implements OperatorDao {

	@Autowired
	private WorkGroupDao workGroupDao ;
	@Override
	public List<Operator> search(OperatorVo searchVo) {
		StringBuilder  hqlBuilder=new StringBuilder();
		//hqlBuilder.append("select f.oper_id,f.oper_name,f.passwd,f.group_id,f.dept_id,f.rights,f.status from operator f");
		//hqlBuilder.append("select f.oper_id,f.oper_name,f.passwd,f.group_id,f.dept_id,f.rights,f.status from operator f");
		//Finder finder = Finder.create(hqlBuilder.toString());
		//Pager pager=find(finder, 0, 10000);
		Criteria cri =getSession().createCriteria(Operator.class);
		Criterion cron;
		if(StrUtils.isValidString(searchVo.getCodeLetter())){
			//#号 35 *号42
			int chatnum=(int)searchVo.getCodeLetter().charAt(0);
			if(chatnum==35){
				//#号
				//hqlBuilder.append(" and f.status = 0  ");
				cron = Restrictions.eq("status",0);
				cri.add(cron);
			}else if(chatnum==42){
				//*号
				//hqlBuilder.append(" and f.status = 1  ");
				cron = Restrictions.eq("status",1);
				cri.add(cron);
			}else{
				cron = Restrictions.like("operId",""+searchVo.getCodeLetter()+"%");
				cri.add(cron);
			}
		}else{
			if(StrUtils.isValidString(searchVo.getOperId())){
				cron = Restrictions.like("operId","%"+searchVo.getOperId().trim()+"%");
				cri.add(cron);
			}
			if(StrUtils.isValidString(searchVo.getOperName())){
				cron = Restrictions.like("operName","%"+searchVo.getOperName().trim()+"%");
				cri.add(cron);
			}
			if(StrUtils.isValidString(searchVo.getGroupId())){
				cron = Restrictions.eq("groupId",searchVo.getGroupId().trim());
				cri.add(cron);
			}
			if(StrUtils.isValidString(searchVo.getDeptId())){
				cron = Restrictions.eq("deptId",searchVo.getDeptId().trim());
				cri.add(cron);
			}
		}
		List<Operator> list = cri.list();
		return decorateEntity(list);
	}

	private List<Operator> decorateEntity(List<Operator> list){
		if(list != null && !list.isEmpty()){
			List<Operator> operatorList = new ArrayList<Operator>(list.size());
			Operator op = null ;
			WorkGroup wg = null ;
			for (Operator operator : list) {
				op = operator ;
				wg = workGroupDao.get("groupId" , operator.getGroupId()) ;
				if(wg != null){
					getSession().evict(operator) ;
					op.setGroupId(wg.getGroupName()) ;
					operatorList.add(op) ;
				}
			}
			return operatorList ;
		}else
			return null ;
	}

	@Override
	public List<OperatorListVO> findListBy(final Integer status) {
		final List<OperatorListVO> list = new ArrayList<OperatorListVO>() ;
		final StringBuilder sbd = new StringBuilder() ;
		sbd.append(" SELECT DISTINCT      ") ;
		sbd.append("     t.oper_id,     ") ;
		sbd.append("     t.oper_name,     ") ;
		sbd.append("     t.passwd,     ") ;
		sbd.append("     t.group_id,     ") ;
		sbd.append("     t.dept_id,     ") ;
		sbd.append("     t.rights,     ") ;
		sbd.append("     t.tel,     ") ;
		sbd.append("     t.mobile,     ") ;
		sbd.append("     t.create_date,     ") ;
		sbd.append("     t.note,     ") ;
		sbd.append("     t.status    ") ;
		sbd.append(" FROM     ") ;
		sbd.append("     operator t      ") ;
		sbd.append(" WHERE t.status = ?     ") ;
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				PreparedStatement pstmt= connection.prepareStatement(sbd.toString()) ;
				pstmt.setInt(1, status) ;
				ResultSet rs = pstmt.executeQuery() ;
				while(rs.next()){
					OperatorListVO olVo = new OperatorListVO();
					Operator operator = new Operator();
					operator.setOperId(rs.getString("oper_id")) ;
					operator.setOperName(rs.getString("oper_name")) ;
					operator.setPasswd(rs.getString("passwd")) ;
					operator.setGroupId(rs.getString("group_id")) ;
					operator.setDeptId(rs.getString("dept_id")) ;
					operator.setRights(rs.getInt("rights")) ;
					operator.setTel(rs.getString("tel")) ;
					operator.setMobile(rs.getString("mobile")) ;
					Date date = rs.getDate("create_date") ;
					if(date != null)
						olVo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("create_date")));
					operator.setCreateDate(date) ;
					operator.setNote(rs.getString("note")) ;
					operator.setStatus(rs.getInt("status")) ;
					olVo.setOperator(operator) ;
					list.add(olVo) ;
				}
				if(rs != null){
		            try {
		                rs.close();
		                rs=null;
		            } catch (Exception ex) {
		                rs=null;
		            }
		        } 
		        if(pstmt != null){
		            try {
		            	pstmt.close();
		            	pstmt=null;
		            } catch (Exception ex) {
		            	pstmt=null;
		            }
		        }
			}
		});
		return list;
	}
	
	
}
