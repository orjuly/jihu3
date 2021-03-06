package com.yqbing.servicebing.utils.enums;

public enum UserStatusEnum {
	
	CODE_FORBIDDEN(-1, "禁用"),
	CODE_WAIT_AUDIT(0, "待审核"),
	CODE_AUDITED(1, "已审核");
	
	private int code;
	
	private String message;

	private UserStatusEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public int getCode() {
		return code;
	}

}
