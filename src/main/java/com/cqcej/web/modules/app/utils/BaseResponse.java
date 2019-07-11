package com.cqcej.web.modules.app.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.http.HttpStatus;

/**
 * 公共响应数据
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-05 下午4:47
 */
@ApiModel("公共响应数据")
public class BaseResponse<T> {
	
	public BaseResponse(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	/**
	 * 带数据的成功信息
	 * @param message
	 * @param data
	 */
	public BaseResponse(String message, T data) {
		this(0, message, data);
	}
	
	/**
	 * 只有数据，没有消息
	 * @param data
	 */
	public BaseResponse(T data) {
		this ("", data);
	}
	
	public static BaseResponse<Boolean> error(String message) {
		return error(HttpStatus.SC_FORBIDDEN, message + "！");
	}
	
	public static BaseResponse<Boolean> error(int code) {
		return error(code, "");
	}
	
	public static BaseResponse<Boolean> error(int code, String message) {
		return new BaseResponse<>(code, message, false);
	}
	
	/**
	 * 返回成功，只是通知用
	 */
	public static BaseResponse<Boolean> success() {
		return success("");
	}
	
	/**
	 * 返回成功，带有通知内容
	 * @param message 通知内容
	 */
	public static BaseResponse<Boolean> success(String message) {
		return new BaseResponse<>(0, message, true);
	}
	
	@ApiModelProperty("自定义状态码，0-成功，其他失败")
	private int code;
	
	@ApiModelProperty("自定义消息，可用于前端显示")
	private String message;
	
	@ApiModelProperty("接口数据，如果没有则为false")
	private T data;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
