package com.magic.liuzm.dto;

import com.magic.liuzm.enums.HttpCodeEnum;
import lombok.Data;

/**
 * @author zemin.liu
 * @date 2020/11/25 16:35
 * @description http响应实体
 */
@Data
public class Response<T> {

    /**
     * 编码
     */
    private Integer code;

    /**
     * 提示语
     */
    private String message;

    /**
     * 真实数据
     */
    private T data;

    /**
     * @author zemin.liu
     * @description 封装失败返回
     * @date 2020/11/26 20:39
     * @param code
     * @param message
     * @return com.magic.liuzm.dto.Response
     */
    public static Response error(Integer code,String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);

        return response;
    }

    /**
     * @author zemin.liu
     * @description 封装失败返回
     * @date 2020/11/26 20:39
     * @param codeEnum
     * @return com.magic.liuzm.dto.Response
     */
    public static Response error(HttpCodeEnum codeEnum) {
        Response response = new Response();
        response.setCode(codeEnum.getCode());
        response.setMessage(codeEnum.getHintCN());

        return response;
    }

    /**
     * @author zemin.liu
     * @description 封装成功返回(有数据返回)
     * @date 2020/11/26 20:39
     * @param data
     * @return com.magic.liuzm.dto.Response
     */
    public static Response ok(Object data){
        Response response = new Response();
        response.setCode(HttpCodeEnum.OK.getCode());
        if(data == null){
            response.setMessage("查无数据");
        }else {
            response.setData(data);
        }

        return response;
    }

    /**
     * @author zemin.liu
     * @description 封装成功返回(无数据返回)
     * @date 2020/11/26 20:39
     * @param message
     * @return com.magic.liuzm.dto.Response
     */
    public static Response ok(String message){
        Response response = new Response();
        response.setCode(HttpCodeEnum.OK.getCode());
        response.setMessage(message);

        return response;
    }
}
