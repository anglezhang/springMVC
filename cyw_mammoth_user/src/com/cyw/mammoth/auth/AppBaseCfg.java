package com.cyw.mammoth.auth;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyw.mammoth.auth.ShiroDbAuthRealm.ShiroUser;
import com.cyw.mammoth.bean.Operator;
import com.cyw.mammoth.service.OperatorSvc;

@Component
public class AppBaseCfg {
	private static Operator operator;
	private static OperatorSvc operatorSvc;
    private static ThreadLocal<Operator> currentOper = new ThreadLocal<Operator>();

    public static void setOper(Operator operator) {
    	currentOper.set(operator);
    }

    private static Operator getOper() {
        return currentOper.get();
    }
	/**
	 * WebSocket消息发送器
	 */
	//public static WsOutbound wsOutBound;
	/**
	 * 一级功能
	 */
	public static int HFUNC_LEVEL_ONE=1;
	/**
	 * 二级功能
	 */
	public static int HFUNC_LEVEL_TWO=2;
	/**
	 * 注入的超管组groupID
	 */
	public static String SUPER_ADMIN_GROUPID="0";
	/**
	 * 授权一级hfunction类型
	 */
	public static final String HFUNC_LEVEL_ONE_TYPE="func";
	public static ArrayList<HfunctioCtrlType> hfunctionCtrlTypeListy;
	
	static {
		hfunctionCtrlTypeListy=new ArrayList<HfunctioCtrlType>();
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_NAV_LEVEL1.code,HfunctionCtrlEnum.HF_NAV_LEVEL1.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_NAV_LEVEL2.code,HfunctionCtrlEnum.HF_NAV_LEVEL2.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_RADIO.code,HfunctionCtrlEnum.HF_RADIO.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_BUTTON.code,HfunctionCtrlEnum.HF_BUTTON.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_INPUT.code,HfunctionCtrlEnum.HF_INPUT.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_CHECKBOX.code,HfunctionCtrlEnum.HF_CHECKBOX.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_RIGHTKEYMENU.code,HfunctionCtrlEnum.HF_RIGHTKEYMENU.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_DBCLICK.code,HfunctionCtrlEnum.HF_DBCLICK.name));
		hfunctionCtrlTypeListy.add(new HfunctioCtrlType(HfunctionCtrlEnum.HF_TAB.code,HfunctionCtrlEnum.HF_TAB.name));
	}
	
	public static ArrayList<HfunctioGroup> hfunctioGroupListy;
	static{
		hfunctioGroupListy=new ArrayList<HfunctioGroup>();
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_daohang.orderno,HfunctionGroupEnum.HG_daohang.code,HfunctionGroupEnum.HG_daohang.name));
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_jiedai.orderno,HfunctionGroupEnum.HG_jiedai.code,HfunctionGroupEnum.HG_jiedai.name));
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_shouyin.orderno,HfunctionGroupEnum.HG_shouyin.code,HfunctionGroupEnum.HG_shouyin.name));
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_yuding.orderno,HfunctionGroupEnum.HG_yuding.code,HfunctionGroupEnum.HG_yuding.name));
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_fangwuzhongxin.orderno,HfunctionGroupEnum.HG_fangwuzhongxin.code,HfunctionGroupEnum.HG_fangwuzhongxin.name));
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_xieyidanwei.orderno,HfunctionGroupEnum.HG_xieyidanwei.code,HfunctionGroupEnum.HG_xieyidanwei.name));
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_huiyuan.orderno,HfunctionGroupEnum.HG_huiyuan.code,HfunctionGroupEnum.HG_huiyuan.name));
		hfunctioGroupListy.add(new HfunctioGroup(HfunctionGroupEnum.HG_xitong.orderno,HfunctionGroupEnum.HG_xitong.code,HfunctionGroupEnum.HG_xitong.name));
	}
	
	public static ArrayList<String> filterUserNames;
	static{
		filterUserNames=new ArrayList<String>();
		filterUserNames.add("audit");//夜审
		filterUserNames.add("tele");//电话
		filterUserNames.add("pos");//pos
	}

	@Autowired
	public void setOperatorSvc(OperatorSvc operatorSvc) {
		this.operatorSvc = operatorSvc;
	}

	public static Operator getOperator() {
			Subject currentUser = SecurityUtils.getSubject();
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
			operator = getOperator(shiroUser.getLoginName());

		return operator;
	}
	public static Operator getOperator(String loginName) {
		operator = operatorSvc.get(loginName);
		operator.setShiftLog(operatorSvc.getOperShiftLog(operator));
		currentOper.set(operator);

	return operator;
}
	
	public static String hfuncFIdGen(){
		 return "f_"+String.valueOf((int)((Math.random()*9+1)*100000));
	}
	public static String hfuncCIdGen(){
		 return "c_"+String.valueOf((int)((Math.random()*9+1)*100000));
	}
	
	public enum HfunctionCtrlEnum{
	    HF_NAV_LEVEL1("navlevel1","一级导航"),
	    HF_NAV_LEVEL2("navlevel2","二级导航"),
	    HF_RADIO("radio","单选框"),
	    HF_BUTTON("button","按钮"),
	    HF_INPUT("input","输入框"),
	    HF_CHECKBOX("checkbox","复选框"),
	    HF_RIGHTKEYMENU("rightkeymenu","右键菜单"),
	    HF_DBCLICK("dbclick","双击"),
	    HF_TAB("tab","TAB页");
		public String code;
		public String name;
		HfunctionCtrlEnum(String code,String name){
			this.code=code;
			this.name=name;
		} 
	}
	public enum HfunctionGroupEnum{
		HG_daohang(1,"daohang","导航"),
	    HG_jiedai(2,"jiedai","接待"),
	    HG_shouyin(3,"shouyin","收银"),
	    HG_yuding(4,"yuding","预订"),
	    HG_fangwuzhongxin(5,"fangwuzhognxin","房务中心"),
	    HG_xieyidanwei(6,"xieyidanwei","协议单位"),
	    HG_huiyuan(7,"huiyuan","会员"),
	    HG_xitong(8,"xitong","系统");
		
		public int orderno;
		public String code;
		public String name;
		HfunctionGroupEnum(int orderno,String code,String name){
			this.orderno=orderno;
			this.code=code;
			this.name=name;
		} 
	}
	public static  class HfunctioGroup {
		private int orderno;
		private String code;
		private String name;
		
		public HfunctioGroup(){
		} 
		
		public HfunctioGroup(int orderno,String code,String name){
			this.orderno=orderno;
			this.code=code;
			this.name=name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getOrderno() {
			return orderno;
		}

		public void setOrderno(int orderno) {
			this.orderno = orderno;
		}
	}
	
	public static class HfunctioCtrlType {
		private String code;
		private String name;
		
		public HfunctioCtrlType(){
		} 
		
		public HfunctioCtrlType(String code,String name){
			this.code=code;
			this.name=name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
	public static void main(String[] args){
		System.out.println(AppBaseCfg.hfuncCIdGen());
		System.out.println(AppBaseCfg.hfunctioGroupListy.get(0).getName());
	}

	public static void setOperator(Operator operator) {
		AppBaseCfg.operator = operator;
	}

	
	

}

