package com.cyw.mammoth.vo;

public enum BookStateEnum {
    NO_Confirm("B"),
    CONFIRMED("O");
	public String value;
	BookStateEnum(String value){
		this.value=value;
	}
}
