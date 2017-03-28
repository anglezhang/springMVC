package com.cyw.mammoth.vo;
/**
 * @Comments:  
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 下午4:30:22
 * @version 1.0
 */

public class FguestSearchVo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5859784877404340909L;
	private String chinaeseName;//中文名称
	private String englishName;//英文名称
	private String mobile;//联系电话
	private String memberCard;//会员卡号
	private String idCard;//身份证
	private String vipNO;//熟客编号
	public String getChinaeseName() {
		return chinaeseName;
	}
	public void setChinaeseName(String chinaeseName) {
		this.chinaeseName = chinaeseName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMemberCard() {
		return memberCard;
	}
	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getVipNO() {
		return vipNO;
	}
	public void setVipNO(String vipNO) {
		this.vipNO = vipNO;
	}

}
