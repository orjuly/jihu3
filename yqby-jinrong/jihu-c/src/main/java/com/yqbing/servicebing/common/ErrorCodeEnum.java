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
	NULLOBJECT("100000", "对象为空"),
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
	CHARGEERROR("421000", "设备错误"),
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
	EXISTPHONE("210000","已经注册过"),
	EXISTCODE("220000","验证码已经过时"),
	ERRORCODE("230000","验证码错误"),
	EXISTUSER("240000","没有注册过"),
	XUPHONE("242000","请使用正常手机号"),
	STOREISEMPTY("250000","不存在"),
	USERALREADYAPP("260000","用户已经下载过"),
	USERNULLAPP("270000","用户无下载"),
	IS_PLATE("310000","车牌号已经存在"),
	NOTFREE("960000","无免费"),
	USEDFREE("961000","已经使用过"),
	ISPAYFALL("962000","支付失败"),
	NOTGAMECODE("963000","没有礼包了"),
	NOTSCREEN("964000","已经保障中"),
	NOTFAIL("965000","付款成功"),
	NOTSTORE("975000","付款成功"),
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
