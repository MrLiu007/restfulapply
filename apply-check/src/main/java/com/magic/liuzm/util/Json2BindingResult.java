package com.magic.liuzm.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.magic.liuzm.dto.CheckDTO;

import java.util.List;

/**
 * BindingResult 工具类
 */
public class Json2BindingResult {

    public static String parseBindingResult(Object obj) {
        if(null == obj){
            return null;
        }
        String result = "";
        try{
            String value = JsonUtils.obj2String(obj);

            JSONArray array = JSONArray.parseArray(value);
            if(array == null || array.size() == 0){
                return null;
            }
            List<CheckDTO> list = Lists.newArrayList();
            for(Object object : array.stream().toArray()){
                JSONObject jsonObject = (JSONObject) object;
                if(jsonObject == null){
                    continue;
                }
                CheckDTO checkDTO = new CheckDTO();
                checkDTO.setField(jsonObject.getString("field"));
                checkDTO.setError(jsonObject.getString("defaultMessage"));
                list.add(checkDTO);
            }
            result = JsonUtils.obj2String(list);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }
}
