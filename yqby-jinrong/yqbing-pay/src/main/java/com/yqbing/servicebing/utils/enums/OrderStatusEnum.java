package com.yqbing.servicebing.utils.enums;

public enum OrderStatusEnum {
	
	ORDER_PAYMENT(0, "订单付款"),
	ORDER_SUCCESS(1, "订单成功"),
	ORDER_ACCOUNT(2, "订单结算"),
	ORDER_INVALID(3, "订单失效");
	
	private int code;
	private String name;
	
	private OrderStatusEnum(int code, String name){
		this.name=name;
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static OrderStatusEnum getEnum(int code){
		OrderStatusEnum typeEnum = null;
		OrderStatusEnum[] codeEnums = OrderStatusEnum.values();
		for (OrderStatusEnum relation : codeEnums) {
			if(relation.getCode() == code){
				typeEnum = relation;
				break;
			}
		}
		return typeEnum;
	}

}
