package com.zoomoor.jy.util;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.zoomoor.common.web.ResponseUtils;

/**
 * 提示工具类
 * */
public class ResponseMsgUtil {
	
	/**
	 * 操作成功提示
	 * @param  response 请求
	 * @param  msg 提示信息
	 * */
	public static void operSuccessFull(HttpServletResponse response,String msg){
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", msg);
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	/**
	 * 操作失败提示
	 * @param  response 请求
	 * @param  msg 提示信息
	 * */
	public static void operFaild(HttpServletResponse response,String msg){
		JSONObject json = new JSONObject();
		json.put("statusCode", "300");
		json.put("message", msg);
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	/**
	 * 操作失败提示
	 * @param  response 请求
	 * @param  msg 提示信息
	 * */
	public static void operFaildClose(HttpServletResponse response,String msg){
		JSONObject json = new JSONObject();
		json.put("callbackType", "closeCurrent");
		json.put("statusCode", "300");
		json.put("message", msg);
		ResponseUtils.renderJson(response, json.toJSONString());
	}
	
	
	
	/**
	 * 操作成功提示 提示后关闭对话框
	 * @param  response 请求
	 * @param  msg 提示信息
	 * */
	public static void operSuccessFullClose(HttpServletResponse response,String msg){
		JSONObject json = new JSONObject();
		json.put("statusCode", "200");
		json.put("message", msg);
		json.put("callbackType", "closeCurrent");
		ResponseUtils.renderJson(response, json.toJSONString());
	}
}
