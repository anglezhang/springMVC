package com.cyw.mammoth.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Comments:房态VO
 * @author zhangzhenxing(zhangzhenxing@cyw.so)
 * @date 下午8:50:40
 * @version 1.0
 */

public class RoomStatusVo implements Serializable {
	private Boolean isFloor;//是否楼层
	private String roomId;//房
	private String roomType;//房间类型
	private String roomTypeCode;//房间类型编码
	private String buildId;//楼号
	private String floorNo;//楼层
	private String roomStat;//房态
	private String roomStatF;//房态第一位
	private String roomStatS;//房态第二位
	private String roomStatT;//房态第三位
	private Long roomStatRight;//房态权值(方便查询筛选添加)
	private String gender;//客户性别
	private Integer checkId;//客户ID
	private Boolean isUsefull;//空房是否可用
	private List<String> guestStat;//客态 
	private Integer roomCharacter;//房类特征
	private String grpCheckId;//团ID
	private String withID;//withId
	public RoomStatusVo(){
		super();
	}
	
	public RoomStatusVo(String roomId, String roomType, String buildId,
			String floorNo) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.buildId = buildId;
		this.floorNo = floorNo;
	}
	public Boolean getIsFloor() {
		return isFloor;
	}
	public void setIsFloor(Boolean isFloor) {
		this.isFloor = isFloor;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getRoomStat() {
		return roomStat;
	}
	public void setRoomStat(String roomStat) {
		if(roomStat.length()==2){
			this.roomStatF = roomStat.substring(0, 1).toUpperCase();
			this.roomStatS = roomStat.substring(1, 2).toUpperCase();
		}else if(roomStat.length() == 3){
			this.roomStatF = roomStat.substring(0, 1).toUpperCase();
			this.roomStatS = roomStat.substring(1, 2).toUpperCase();
			this.roomStatT = roomStat.substring(2, 3).toUpperCase();
		}
		this.roomStat = roomStat.toUpperCase();
		//OOI:1 OOO:2 OC: 3 VC 4
		if(roomStat.equals("OOI")){
			setRoomStatRight(1l<<1);
		}
		if(roomStat.equals("OOO")){
			setRoomStatRight(1l<<2);
		}
		if(roomStat.contains("OC") || roomStat.contains("OD")){
			setRoomStatRight(1l<<3);
		}
		if(roomStat.contains("VC") || roomStat.contains("VD")){
			setRoomStatRight(1l<<4);
		}
	}

	public List<String> getGuestStat() {
		return guestStat;
	}

	public void setGuestStat(List<String> guestStat) {
		this.guestStat = guestStat;
	}

	public String getRoomStatF() {
		return roomStatF;
	}

	public void setRoomStatF(String roomStatF) {
		this.roomStatF = roomStatF;
	}

	public String getRoomStatS() {
		return roomStatS;
	}

	public void setRoomStatS(String roomStatS) {
		this.roomStatS = roomStatS;
	}

	public String getRoomStatT() {
		return roomStatT;
	}

	public void setRoomStatT(String roomStatT) {
		this.roomStatT = roomStatT;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getIsUsefull() {
		return isUsefull;
	}

	public void setIsUsefull(Boolean isUsefull) {
		this.isUsefull = isUsefull;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Integer getRoomCharacter() {
		return roomCharacter;
	}

	public void setRoomCharacter(Integer roomCharacter) {
		this.roomCharacter = roomCharacter;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getGrpCheckId() {
		return grpCheckId;
	}

	public void setGrpCheckId(String grpCheckId) {
		this.grpCheckId = grpCheckId;
	}

	public String getWithID() {
		return withID;
	}

	public void setWithID(String withID) {
		this.withID = withID;
	}

	public Long getRoomStatRight() {
		return roomStatRight;
	}

	public void setRoomStatRight(Long roomStatRight) {
		this.roomStatRight = roomStatRight;
	}
	
}
