package com.yqbing.servicebing.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author mapb
 *
 */
public class SerialNumber {
    
    private static final int MAX_VALUE=99999;
    private static final String FORMAT = "yyMMddHHmmss";
    private static final Format DF= new SimpleDateFormat(FORMAT);
    private static final byte[] lock = new byte[0];
    private Date date = null;
    private int number=1;
    private static Map<String, SerialNumber> map = new HashMap<String, SerialNumber>();
     
    private SerialNumber(Date date){
        this.date = date;
    }
     
    public static SerialNumber newInstance(){
        Date date = new Date();
        return newInstance(date);
    }
     
    public static SerialNumber newInstance(Date date){
        SerialNumber o = null;
        synchronized (lock) {
            String key = getKey(date);
            if(map.containsKey(key)){
                o = map.get(key);
                int number = o.getNumber();
                if(number<MAX_VALUE){
                    o.setNumber(number+1);
                }else {
                    o.setNumber(1);
                }
                 
            } else {
                 o = new SerialNumber(date);
                 map.put(key, o);
            }
        }
        return o;
    }
     
     
     
    private static String getKey(Date date){
        return format(date);
    }
 
    private static String format(Date date){
        return DF.format(date);
    }
     
    public String toString(){
        return  format(date) + String.format("%05d", number);
    }
 
    public void setNumber(int number) {
        this.number = number;
    }
 
    public int getNumber() {
        return number;
    }
    
//    public static void main(String[] args) {
//		for(int i=0;i<100;i++){
//			System.err.println(Long.valueOf(SerialNumber.newInstance(new Date()).toString()));
//		}
//	}
}
