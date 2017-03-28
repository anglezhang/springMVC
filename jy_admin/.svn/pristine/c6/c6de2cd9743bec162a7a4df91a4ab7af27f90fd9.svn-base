package com.zoomoor.jy.util;

public class CodeUtils {
	public static void main(String[] args) {
		for (int i = 1; i <= 10000; i++) {
			System.out.println(createOrderNo());
		}
	}

	public static String createOrderNo() {
		return createNo(6);
	}

	public static String createNo(int length) {
		String str = "0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(str.charAt((int) (Math.random() * 36)));
		}
		return builder.toString();
	}
	public static String getNo(Integer dateOrderNo){
		int dateNo=dateOrderNo;
		int size = 1;
		while ((dateOrderNo = dateOrderNo / 10) != 0) {
	            size++;
	   }
		if(size==1){
			return "000"+dateNo;
		}
		if(size==2){
			return "00"+dateNo;
		}
		if(size==3){
			return "0"+dateNo;
		}else{
			return dateNo+"";
		}
		}
}
