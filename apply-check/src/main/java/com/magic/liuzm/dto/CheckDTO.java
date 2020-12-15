package com.magic.liuzm.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/24 11:21
 * @description 提示信息
 */
@Data
public class CheckDTO implements Serializable {

    /**
     * 属性
     */
    private String field;

    /**
     * 提示语
     */
    private String error;
}
