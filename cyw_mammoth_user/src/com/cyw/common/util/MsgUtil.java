package com.cyw.common.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
// TODO: Auto-generated Javadoc

/**
 * The Class MsgUtil.
 *
 * @ClassName: MsgUtil
 * @Title:漫道科技-短信平台调用接口 
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @Author:wangxiaojun 
 * @Since:2014-11-6下午3:13:36 
 * @Version:1.0 
 */
public class MsgUtil {
	
	/**
	 * Sendmsg.
	 *
	 * @param mobile the mobile
	 * @param checkcode the checkcode
	 * @throws Exception the exception
	 */
	public static void sendmsg(String mobile,String checkcode) throws Exception {
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new NameValuePair("sn", "SDK-WSS-010-07482"));
		String md5pwd=MD5Util.md5code32("SDK-WSS-010-07482"+"fd4)b-1f").toUpperCase();
		params.add(new NameValuePair("pwd",md5pwd));
		params.add(new NameValuePair("mobile", mobile));
		params.add(new NameValuePair("content", URLEncoder.encode("X梦想平台欢迎您，验证码："+checkcode+" [琢磨网络]", "utf-8")));
		params.add(new NameValuePair("ext", ""));
		params.add(new NameValuePair("stime", ""));
		params.add(new NameValuePair("rrid", ""));
		params.add(new NameValuePair("msgfmt", ""));
		String result=MsgUtil.request(url, params.toArray(new NameValuePair[params.size()]));
        System.out.println(result);
	}
	
	/**
	 * Sendmsgmuti.
	 *
	 * @param mobiles the mobiles
	 * @param checkcodes the checkcodes
	 * @throws Exception the exception
	 */
	public static void sendmsgmuti(String mobiles,String checkcodes) throws Exception {
		String url = "http://sdk2.entinfo.cn:8061/mdgxsend.ashx";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new NameValuePair("sn", "SDK-WSS-010-07482"));
		String md5pwd=MD5Util.md5code32("SDK-WSS-010-07482"+"fd4)b-1f").toUpperCase();
		params.add(new NameValuePair("pwd",md5pwd));
		params.add(new NameValuePair("mobile", mobiles));
		params.add(new NameValuePair("content", checkcodes));
		params.add(new NameValuePair("ext", ""));
		params.add(new NameValuePair("stime", ""));
		params.add(new NameValuePair("rrid", ""));
		params.add(new NameValuePair("msgfmt", ""));
		String result=MsgUtil.request(url, params.toArray(new NameValuePair[params.size()]));
        System.out.println(result);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		sendmsgmuti("13060382500,18789425059",URLEncoder.encode("测试短信1","utf-8")+","+URLEncoder.encode("测试短信1","utf-8"));
	}
		
		/**
		 * Request.
		 *
		 * @param url the url
		 * @param params the params
		 * @return the string
		 */
		public static String request(String url, NameValuePair[] params) {

		String result = null;

		HttpClient client = new HttpClient();

		PostMethod postMethod = new PostMethod(url);

		postMethod.setRequestBody(params);

		int statusCode = 0;
		try {
			statusCode = client.executeMethod(postMethod);
		} catch (HttpException e) {
		} catch (IOException e) {
		}

		try {
			if (statusCode == HttpStatus.SC_OK) {
				result = postMethod.getResponseBodyAsString();
				return result;
			} else {
			}
		} catch (IOException e) {
		}
		postMethod.releaseConnection();
		return result;

	}
}
