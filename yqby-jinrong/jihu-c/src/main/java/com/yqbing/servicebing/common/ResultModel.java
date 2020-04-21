package com.yqbing.servicebing.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@SuppressWarnings("serial")
@ApiModel(value = "返回结果类")
public class ResultModel<T> implements Serializable {

	@ApiModelProperty(value = "code 0-正常", name = "Code")
	public Integer Code;
	@ApiModelProperty(value = "操作信息", name = "message")
	public String Message;
	@ApiModelProperty(value = "结果集", name = "result")
	public T result;

	public ResultModel() {
	}

	public ResultModel(Integer Code, String Message) {
		this.Code = Code;
		this.Message = Message;
	}

	/**
	 * 成功：无数据
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static ResultModel success() {
		return new ResultModel(ErrorCode.SUCCESS,
				ErrorCode.getErrInfo(ErrorCode.SUCCESS));
	}

	@SuppressWarnings("rawtypes")
	public static ResultModel error(Integer errorCode, String errorMessage) {
		return new ResultModel(errorCode, errorMessage);
	}

	public Integer getErrorCode() {
		return Code;
	}

	public void setErrorCode(Integer errorCode) {
		this.Code = errorCode;
	}

	public String getErrorMessage() {
		return Message;
	}

	public void setErrorMessage(String errorMessage) {
		this.Message = errorMessage;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
