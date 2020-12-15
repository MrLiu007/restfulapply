package com.magic.liuzm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author zemin.liu
 * @date 2020/12/14 16:13
 * @description 校车
 */
@Data
public class Car implements Serializable {

    /**
     * 司机姓名
     */
    @NotNull(message = "司机姓名不能为空")
    String name;

    /**
     * 司机电话
     */
    @Pattern(message = "司机电话不正确",regexp = "\"^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\\\d{8}$\"")
    String tel;
}
