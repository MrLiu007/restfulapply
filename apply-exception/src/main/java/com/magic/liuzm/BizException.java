
package com.magic.liuzm;

import com.magic.liuzm.enums.HttpCodeEnum;

/**
 * @author zemin.liu
 * @date 2020/12/14 16:35
 * @description 自定义业务异常
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -1171501462599127698L;

    final private int errCode;

    final private String errMsg;

    public BizException(HttpCodeEnum errCode) {
        super(errCode.getHint());
        this.errCode = errCode.getCode();
        this.errMsg = errCode.getHint();
    }

    public BizException(int code, String errMsg) {
        super(errMsg);
        this.errCode = code;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
