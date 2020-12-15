package com.magic.liuzm.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author zemin.liu
 * @date 2020/12/14 16:12
 * @description 校长
 */
@Data
public class Master implements Serializable {

    /**
     * 校长姓名
     */
    @NotNull(message = "校长姓名不能为空")
    String name;

    /**
     * 校长电话
     */
    @Pattern(message = "校长电话不正确",regexp = "\"^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\\\d{8}$\"")
    String tel;

    /**
     * 校长履历
     */
    @NotNull(message = "校长履历不能为空")
    String undergo;
}
