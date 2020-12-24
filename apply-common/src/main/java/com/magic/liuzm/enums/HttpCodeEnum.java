package com.magic.liuzm.enums;

/**
 * @author zemin.liu
 * @date 2020/11/26 16:11
 * @description 请求code
 *
 *
 * 参考：
 * Spring：org.springframework.http.HttpStatus
 * 阿里云：https://help.aliyun.com/document_detail/25491.html
 * github：https://developer.github.com/v3/#client-errors
 * microsoft：https://github.com/Microsoft/api-guidelines/blob/vNext/Guidelines.md#711-http-status-codes
 * google：https://cloud.google.com/apis/design/errors
 * 百度云：https://cloud.baidu.com/doc/BCC/s/Ojwvyo6nc
 *
 * 其他code：https://www.cnblogs.com/culing/p/5710174.html
 */
public enum HttpCodeEnum {

    //...................2开头（成功）
    /**
     * 支持每种操作
     */
    OK(200,"OK","操作成功"),

    /**
     * 支持POST操作
     */
    CREATED(201,"CREATED","资源被创建"),

    /**
     * 支持POST操作
     */
    ACCEPTED(202,"ACCEPTED","已接受处理请求但尚未完成（异步处理）"),

    /**
     * 支持每种操作
     */
    EMPTY(204,"EMPTY","无资源"),

    //...................3开头（重定向）
    /**
     * 支持每种操作
     */
    MOVED_PERMANENTLY(301,"MOVED_PERMANENTLY","资源的URI已更改"),

    /**
     * 支持每种操作
     */
    SEE_OTHER(303,"SEE_OTHER","其他（如，负载均衡）"),

    /**
     * 支持GET操作
     */
    NOT_MODIFIED(304,"NOT_MODIFIED"," 资源未更改（缓存）"),

    //...................4开头（请求错误）
    /**
     * 支持每种操作
     *
     * 参数正确
     * 头格式错误
     * restful url参数错误
     */
    BAD_REQUEST(400,"BAD_REQUEST","坏请求"),

    /**
     * 支持每种操作
     *
     * 由于 OAuth 令牌丢失、无效或过期，请求未通过身份验证。
     */
    UNAUTHENTICATED(401,"UNAUTHENTICATED","未经认证"),

    /**
     * 支持每种操作
     *
     * 没有开通对应的服务
     * 无权限访问对应的资源
     */
    FORBIDDEN(403,"FORBIDDEN","无权限访问对应的资源"),

    /**
     * 支持每种操作
     */
    NOT_FOUND(404,"NOT_FOUND","资源不存在"),

    /**
     * 支持每种操作
     */
    METHOD_NOT_ALLOWED(405,"METHOD_NOT_ALLOWED","请求方法不支持"),

    /**
     * 支持每种操作
     */
    NOT_ACCEPTABLE(406,"NOT_ACCEPTABLE","服务端不支持操作"),

    /**
     * 支持每种操作
     */
    REQUEST_TIMEOUT(408,"REQUEST_TIMEOUT","请求超时"),

    /**
     * 不支持GET操作
     *
     * 创建/修改的资源已存在
     */
    CONFLICT(409,"CONFLICT","并发冲突（读取/修改/写入冲突）"),

    /**
     * 支持POST/PUT操作
     */
    PRECONDITION_FAILED(412,"PRECONDITION_FAILED","前置条件失败（如执行条件更新时的冲突）"),

    /**
     * 支持POST/PUT操作
     */
    UNSUPPORTED_MEDIA_TYPE(415,"UNSUPPORTED_MEDIA_TYPE","不支持的媒体类型"),

    //...................5,6开头（服务器错误）
    /**
     * 支持每种操作
     */
    INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR","出现未知的服务器错误，通常是服务器错误。"),

    /**
     * 支持每种操作
     */
    NOT_IMPLEMENTED(501,"NOT_IMPLEMENTED","API方法服务器未实现"),

    /**
     * 支持每种操作
     */
    SERVICE_UNAVAILABLE(503,"SERVICE_UNAVAILABLE","服务不可用。通常是服务器已关闭。"),

    /**
     * 支持每种操作
     */
    GATEWAY_TIMEOUT(504,"GATEWAY_TIMEOUT","超出请求时限");

    /**
     * 编号
     */
    private int code;

    /**
     * 英语描述
     */
    private String hint;

    /**
     * 中文描述
     */
    private String hintCN;

    HttpCodeEnum(int code, String hint, String hintCN){
        this.code = code;
        this.hint = hint;
        this.hintCN = hintCN;
    }

    public int getCode() {
        return code;
    }

    public String getHint() {
        return hint;
    }

    public String getHintCN() {
        return hintCN;
    }
}
