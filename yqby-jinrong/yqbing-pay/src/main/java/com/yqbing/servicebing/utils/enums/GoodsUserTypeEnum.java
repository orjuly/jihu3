package com.yqbing.servicebing.utils.enums;

public enum GoodsUserTypeEnum {

	TAOBAO_GOODS(0, "淘宝商品"),
	TMALL_GOODS(1, "天猫商品"),
	JD_GOODS(2, "京东商品"),
	PDD_GOODS(3, "拼多多商品");
	
	private int code;
	private String name;
	
	private GoodsUserTypeEnum(int code, String name){
		this.name=name;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static GoodsUserTypeEnum getEnum(int code){
		GoodsUserTypeEnum codeEnum = null;
		GoodsUserTypeEnum[] codeEnums = GoodsUserTypeEnum.values();
		for (GoodsUserTypeEnum relation : codeEnums) {
			if(relation.getCode() == code){
				codeEnum = relation;
				break;
			}
		}
		return codeEnum;
	}

}
