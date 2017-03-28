package com.zoomoor.jy.entity.vo;

import javax.persistence.Transient;

public class DepotCheckVo {
		//仓位Ids
		private String positions;
		//仓位名称
		private String  posName;
		
		//盘点单号
		private String checkNo;
		
		
		public String getCheckNo() {
			return checkNo;
		}

		public void setCheckNo(String checkNo) {
			this.checkNo = checkNo;
		}

		public String getPosName() {
			return posName;
		}

		public void setPosName(String posName) {
			this.posName = posName;
		}

		public String getPositions() {
			return positions;
		}

		public void setPositions(String positions) {
			this.positions = positions;
		}
}
