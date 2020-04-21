package com.yqbing.servicebing.utils.enums;


import java.util.HashMap;
import java.util.Map;

/** 
 * @version 创建时间：2017年1月9日 
 */
public enum MsgErrorEnum {
	
	SUCCESS_RECEIVE(0, "Msg receive success!"),	
	MSG_SEND_FAILED_TO_RETRY_QUEUE(11, "Msg send failed, add to retry queue!"),
	MSG_SEND_FAILED_NO_RETRY(12, "Msg send failed, no retry queue, send stop!"),
	MSG_SEND_RETRY_FINISHED(13, "Msg send failed, over retry count, send stop!"),
	
	ERROR_MSG_INVALID(1001, "Msg invalid!"),
	ERROR_MSG_TYPE_INVALID(1002, "Msg type invalid!"),
	ERROR_MSG_DATA_INVALID(1003, "Msg data invalid!"),
	ERROR_MSG_OPERATE_INVALID(1004, "Msg operate invalid!"),
	
	ERROR_MSG_RECEIVE_FAILED(1101, "Msg receive failed!"),
	
	;
	
	private int id;
	
	private String name;
	
	
	private static Map<Integer, MsgErrorEnum> enumMap;
	
	static {
		enumMap = new HashMap<Integer, MsgErrorEnum>();
		
		for(MsgErrorEnum enumObj : MsgErrorEnum.values()){
			enumMap.put(enumObj.getId(), enumObj);
		}
	}
	
	private MsgErrorEnum(Integer id, String name){
		this.id = id;
		this.name = name;
	}
	
	public static boolean exist(Integer id){
		return enumMap.get(id) != null;
	}
	
	public static String getName(Integer id){
		MsgErrorEnum enumObj = enumMap.get(id);
		return enumObj != null ? enumObj.getName() : "";
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
}
