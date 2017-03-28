package com.zoomoor.common.util;




import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

/**
 * @author holly
 * 
 */
public class JsonResult {
	private int code;
	private Object result;

	public JsonResult() {
		this(10, null);
	}

	public JsonResult(int code, Object object) {
		this.code = code;
		this.result = object;
	}

	@Override
	public String toString() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		return gsonBuilder.create().toJson(this);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
