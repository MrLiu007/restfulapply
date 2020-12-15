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
 * @description 学校信息
 */
@Data
public class SchoolCheckDTO implements Serializable {

    /**
     * 学校名称
     */
    @NotEmpty(message = "编号不能为空")
    private String schoolName;

    /**
     * 是否公办
     */
    @AssertTrue(message = "此学校为公办学校")
    private Boolean isPublic;

    /**
     * 最小上学年龄
     */
    @Min(message = "最小上学年龄为6岁",value = 6)
    private Integer age;

    /**
     * 书本费
     */
    @DecimalMax(value = "96.0",message = "书本费最多96元")
    private BigDecimal cost;

    /**
     * 建校日期
     */
    @Past
    private Date openDate;

    /**
     * 学校电话
     */
    @Pattern(message = "学校电话不正确",regexp = "\"^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\\\d{8}$\"")
    private String tel;

    /**
     * 学校邮箱
     */
    @Email(message = "学校邮箱不正确")
    private String email;

    /**
     * 学校校车
     */
    @Size(min = 2, max = 10 ,message = "校车最多10辆，最少2辆")
    // 嵌套验证必须用@Valid,为javax提供，配合@Validated进行嵌套检查
    @Valid
    private List<Car> cars;

    /**
     * 校长
     */
    // 嵌套验证必须用@Valid,为javax提供，配合@Validated进行嵌套检查
    @Valid
    private Master master;
}
