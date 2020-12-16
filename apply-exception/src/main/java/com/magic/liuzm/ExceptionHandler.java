package com.magic.liuzm;

import com.magic.liuzm.dto.Response;
import com.magic.liuzm.enums.HttpCodeEnum;
import com.magic.liuzm.util.Json2BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;

/**
 * @author zemin.liu
 * @date 2020/12/14 16:35
 * @description 全局异常捕获（拦截Controller 自定义异常 + 系统异常）
 */
@RestControllerAdvice
public class ExceptionHandler {

    /**
     * @author zemin.liu
     * @description 对RequestParam捕获
     * @date 2020/12/14 16:49
     * @param e
     * @return com.magic.liuzm.dto.Response
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Response ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        // Return the customized result.
        return Response.error(HttpCodeEnum.BAD_REQUEST.getCode(),e.getMessage());
    }

    /**
     * @author zemin.liu
     * @description 对RequestBody捕获
     * @date 2020/12/14 16:49
     * @param e
     * @return com.magic.liuzm.dto.Response
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // Return the customized result.
        return Response.error(HttpCodeEnum.BAD_REQUEST.getCode(), Json2BindingResult.parseBindingResult(e.getBindingResult().getAllErrors()));
    }

    /**
     * @author zemin.liu
     * @description 对自定义异常捕获
     * @date 2020/12/14 16:49
     * @param e
     * @return com.magic.liuzm.dto.Response
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleBizException(Exception e) {
        if(e instanceof BizException){
            BizException exception =  (BizException)e;
            // 自定义异常返回
            return Response.error(exception.getErrCode(),exception.getMessage());
        }else {
            // 其他异常返回
            return Response.error(HttpCodeEnum.INTERNAL_SERVER_ERROR.getCode(),HttpCodeEnum.INTERNAL_SERVER_ERROR.getHintCN());
        }
    }

}
