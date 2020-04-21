package com.yqbing.servicebing.common;

public enum ErrorCodeEnum {
	/** 成功 */
	SUCCESS("0", "success"),
	/** 数据为空成功 */
	NULLSUCCESS("-1", "success"),
	/** 数据不一致 */
	DISACCORD("-2", "对象不能为空"),
	/** 用户不存在 */
	NULLUSER("-3", "用户不存在，请重新登录"),
	/** 加入黑名单 */
	BLACKLIST("-5", "您已被拉入黑名单。"),
	/** 空对象 */
	NULLOBJECT("100000", "对象不能为空"),
	/** 非空对象 */
	OBJECT("100001", "对象已经存在"),
	/** 空对象 */
	SYSTEMERROR("110000", "系统异常"),
	/** 空参数 */
	NULLPARAM("200000", "参数不能为空"),
	/** 版本不正确 */
	VERSIONRROR("200001", "版本不正确"),
	/** JSON转换错误 */
	PARAMFORMATERROE("201000", "JSON格式错误"),
	/** 参数错误 */
	PARAMERROE("202000", "参数错误"),
	/** 操作失败 */
	OPERATERROE("300000", "操作失败"),
	NOLOGIN("400000","用户未登录"),
	/** 短信验证码有误 */
	USERCODEERROR("410000", "短信验证码有误"),
	DELETE("500000","删除失败"),
	ADD("600000","添加失败"),
	UPDATE("700000","修改失败"),
	NOTACCESS("800000","没有权限"),
	NOTENOUGH("800001","兑换数量不足"),
	OVERSTEP("800002","兑换数量超了"),
	NOTGROUPID("900000","群组ID不存在"),
	NOTGROUP("920000","没有数据"),
	ZANBASHENHE("940000","审核失败"),
	NOTSRORENAMElicense("950000","请先认证营业执照"),
	NOTSRORE("960000","店铺不存在"),
	NOTSRORENAME("930000","不对应的店铺名称"),
	NOTSRORECERO("980000","营业执照已申请过,请更换"),
	BANK360ING("840000","等待哦"),
	ALIPAY_TOKEN("9810000","授权失败"),
	ALIPAY_CREATE("9820000","授权失败"),
	ALIPAY_OUTPIC("9830000","门店门头照失败"),
	ALIPAY_OUTTOKEN("9840000","还没有授权"),
	ALIPAY_FAIL("9910000","付款失败"),
	NOTOPENALI("220000","没有开户"),
	ALIPAY_CLOSED("9920000","交易关闭"),
	ALIPAY_INPROCESS("9930000","正在处理中"),
	BANK360LAXIN("830000","申请拉新");

	
	
	private String code;
	private String msg;

	private ErrorCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
