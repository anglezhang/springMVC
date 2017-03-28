package com.cyw.mammoth.cywenum;
/**
 * @Comments:  房态 值对应图片url
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 上午10:40:58
 * @version 1.0
 */

public enum RoomStat {
	ZI("st-01.png"),//自留房
	LIU("st-02.png"),//客户留房
	TUAN("st-03.png"),//团队预定
	ZHONG("st-04.png"),//钟点房
	MIAN("st-05.png"),//免单
	LI("st-06.png"),//预离
	/**
	 * 抵
	 * */
	DI("st-07.png"),//预抵
	QIAN("st-08.png"),//欠费
	LIAN("st-09.png");//联房
	private String imgUrl;
	private RoomStat(String imgUrl){
		this.imgUrl = imgUrl;
	}
	public String toString(){
		return this.imgUrl;
	}
	
}
