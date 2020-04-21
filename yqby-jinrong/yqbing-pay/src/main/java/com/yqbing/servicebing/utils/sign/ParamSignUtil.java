package com.yqbing.servicebing.utils.sign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yqbing.servicebing.utils.md5.MD5;


/**
 * @author zhangsongpu
 * @version 创建时间:2018年7月3日
 * 
 */
public class ParamSignUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ParamSignUtil.class);
	
	private static final String SECRET_KEY = "af8dc7612ac9fbc8935f8ea77f412b85";
	
	public static final String DEFAULT_SIGN_KEY = "sign";
	
	private static final List<String> DEFAULT_IGNORE_PARAM_NAMES = new ArrayList<String>();
	
	static{
		DEFAULT_IGNORE_PARAM_NAMES.add(DEFAULT_SIGN_KEY);
	}
	
	/**
	 * 生成签名，默认签名的名字是sign, 默认秘钥
	 * @param paramValues
	 * @return
	 */
	public static HashMap<String, String> sign(Map<String, String> paramValues) {
	
		return sign(paramValues, DEFAULT_IGNORE_PARAM_NAMES, SECRET_KEY);
	}
	
	/**
	 * 生成签名，可以屏蔽不参加签名的参数, 默认秘钥
	 * @param paramValues
	 * @param ignoreParamNames
	 * @return
	 */
	public static HashMap<String, String> sign(Map<String, String> paramValues, List<String> ignoreParamNames) {
		
		if(ignoreParamNames == null || ignoreParamNames.size() == 0){
			ignoreParamNames = DEFAULT_IGNORE_PARAM_NAMES;
		}
		
		return sign(paramValues, ignoreParamNames, SECRET_KEY);
	}
	
	/**
	 * 生成签名，可以屏蔽不参加签名的参数, 自定义秘钥
	 * @param paramValues
	 * @param ignoreParamNames
	 * @param secret
	 * @return
	 */
	public static HashMap<String, String> sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
		
        try {  
            HashMap<String, String> signMap = new HashMap<String, String>();  
            StringBuilder sb = new StringBuilder();  
            List<String> paramNames = new ArrayList<String>(paramValues.size());  
            paramNames.addAll(paramValues.keySet());  
            if (ignoreParamNames != null && ignoreParamNames.size() > 0) {  
                for (String ignoreParamName : ignoreParamNames) {  
                    paramNames.remove(ignoreParamName);  
                }
            }
            Collections.sort(paramNames);  
            sb.append(secret).append("#");  
            for (String paramName : paramNames) {  
                sb.append(paramName).append("=").append(paramValues.get(paramName)).append("&");  
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("#").append(secret);
//            String str = URLEncoder.encode(sb.toString(), "utf-8");
            String str = sb.toString();
            String sign = MD5.md5(str);
            log.info("signParam = " + sb.toString());
            log.info("sign = " + sign);
            signMap.put("signParam", sb.toString());  
            signMap.put("sign", sign);  
            return signMap;  
        } catch (Exception e) {  
            throw new RuntimeException("加密签名计算错误", e);  
        }  
          
    }  
	
	public static void main(String[] args){
		HashMap<String, String> signMap = new HashMap<String, String>();
		signMap.put("otype","01");
		signMap.put("oid","02");
		signMap.put("gcc","03");
		String secret="test";
		HashMap SignHashMap=ParamSignUtil.sign(signMap, null, secret);
		System.out.println("SignHashMap:"+SignHashMap);
		List<String> ignoreParamNames=new ArrayList<String>();

	}

}