package com.magic.liuzm.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Json 工具类
 */
public class JsonUtils {

    /**
     * 对象转换为字符串
     */
    public static String obj2String(Object obj) {
        if(null == obj){
            return null;
        }
        String result = "";
        try{
            // 默认为UTF-8;
            result = JSONObject.toJSONString(obj);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }
}
