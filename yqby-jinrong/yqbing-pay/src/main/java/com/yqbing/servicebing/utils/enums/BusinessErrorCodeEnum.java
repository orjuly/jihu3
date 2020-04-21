package com.yqbing.servicebing.utils.enums;


public enum BusinessErrorCodeEnum implements BusinessErrorCode {

	PROCESS_SERVICE_EXIST(BusinessErrorCodeEnum.SEARCH_ERROR_CODE, 1, "注册服务已存在"),
	PROCESS_SERVICE_NOT_EXIST(BusinessErrorCodeEnum.SEARCH_ERROR_CODE, 2, "注册服务不存在"),
	
	RELOAD_TASK_EXIST(BusinessErrorCodeEnum.SEARCH_ERROR_CODE, 5, "重建索引任务已存在"),
	RELOAD_TASK_NOT_EXIST(BusinessErrorCodeEnum.SEARCH_ERROR_CODE, 6, "重建索引任务不存在")
	;
	
	
	private int root;

	private int code;
	
	private String message;

	private BusinessErrorCodeEnum(int root, int code, String message) {
		
		this.root = root;
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return root + code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int getRoot() {
		
		return root;
	}
	
}
