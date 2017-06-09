package com.example.demo.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述：统一返回json格式<br>
 * 作者：dolojia <br>
 * 修改日期：2017年6月9日上午10:23:46 <br>
 * E-mail: dolojia@gmail.com<br>
 */
public class JSONResult {

	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_FAIL = "fail";
	public static final String STATUS_ERROR = "error";

	public static String fillResultString(String status, String message, Integer code, Object resultData) {
		@SuppressWarnings("serial")
		JSONObject jsonObject = new JSONObject() {
			{
				put("status", status);
				put("message", message);
				put("code", code);
				put("data", resultData);
			}
		};
		return jsonObject.toString();
	}

}
