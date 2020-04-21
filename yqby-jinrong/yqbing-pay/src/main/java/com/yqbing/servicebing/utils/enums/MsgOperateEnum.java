package com.yqbing.servicebing.utils.enums;


import java.util.HashMap;
import java.util.Map;

/** 
 * @version 创建时间：2017年5月12日 
 */
public enum MsgOperateEnum {
	
	ADD_BY_ID(11, "新增数据!"),
	UPDATE_BY_ID(12, "更新数据!"),
	DELETE_BY_ID(13, "删除数据!"),
	
	ADD_BY_DATA(21, "新增数据!"),
	UPDATE_BY_DATA(22, "更新数据!")
	;
	
	private int id;
	
	private String name;
	
	
	private static Map<Integer, MsgOperateEnum> enumMap;
	
	static {
		enumMap = new HashMap<Integer, MsgOperateEnum>();
		
		for(MsgOperateEnum enumObj : MsgOperateEnum.values()){
			enumMap.put(enumObj.getId(), enumObj);
		}
	}
	
	private MsgOperateEnum(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	public static boolean exist(Integer id){
		return enumMap.get(id) != null;
	}
	
	public static String getName(Integer id){
		MsgOperateEnum enumObj = enumMap.get(id);
		return enumObj != null ? enumObj.getName() : "";
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
}
