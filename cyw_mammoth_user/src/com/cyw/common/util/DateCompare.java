package com.cyw.common.util;

import java.util.Comparator;
import java.util.Date;

public class DateCompare implements Comparator<Date>{

	@Override
	public int compare(Date o1, Date o2) {
		if(o1.getTime()<o2.getTime()){
			return -1;
		}else if(o1.getTime()>o2.getTime()){
			return 1;
		}else{
			return 0;
		}
	}

}
