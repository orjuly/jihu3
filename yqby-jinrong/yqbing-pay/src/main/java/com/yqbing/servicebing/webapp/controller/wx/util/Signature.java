package com.yqbing.servicebing.webapp.controller.wx.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 15:23
 */
public class Signature {
	
	private static Logger logger=LoggerFactory.getLogger(Signature.class);

    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Configure.getKey();
        logger.error("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        logger.error("Sign Result:" + result);
        return result;
    }

    /**
     * 从API返回的XML数据里面重新计算一次签名
     * @param responseString API返回的XML数据
     * @return 新鲜出炉的签名
     * @throws Exception 
     */
    public static String getSignFromResponseString(String responseString) throws Exception {
        Map<String,Object> map = XmlUtil.doXMLParse(responseString);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        return Signature.getSign(map);
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws Exception 
     */
    public static boolean checkIsSignValidFromResponseString(String responseString) throws Exception {

        Map<String,Object> map = XmlUtil.doXMLParse(responseString);
        logger.error(map.toString());

        String signFromAPIResponse = map.get("sign").toString();
        if(signFromAPIResponse=="" || signFromAPIResponse == null){
        	logger.error("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        logger.error("服务器回包里面的签名是:" + signFromAPIResponse);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = Signature.getSign(map);

        if(!signForAPIResponse.equals(signFromAPIResponse)){
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
        	logger.error("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        logger.error("恭喜，API返回的数据签名验证通过!!!");
        return true;
    }
    
    
    public static void main(String[] args) throws IllegalAccessException {
    	Map<String, Object> packageParams = new HashMap<>();
		packageParams.put("appid", "wx6e3b92a84b8a737a");
		packageParams.put("mch_id", "1405193702");
		packageParams.put("nonce_str", "1634358275");
		packageParams.put("body", "私教服务");
		packageParams.put("attach", "5");
		packageParams.put("out_trade_no", "16112916343500001");
		packageParams.put("total_fee", "1");
		packageParams.put("spbill_create_ip", "223.223.198.235");
		packageParams.put("notify_url", "http://wx.techfit.cn/gym-web/weixin/tenpay/pay_notify");
		packageParams.put("trade_type", "JSAPI");
		packageParams.put("openid", "o0WHmwFGN14ZEiRNoDqABOsFLDK4");
		Configure.setKey("46100784FDA3CEF41908722B2FE50261");
		String sign = Signature.getSign(packageParams);
		System.err.println(sign);
	}

}
