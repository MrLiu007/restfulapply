package com.magic.liuzm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zemin.liu
 * @date 2020/11/24 11:21
 * @description 学校信息
 */
@Data
public class SchoolDTO implements Serializable {

    /**
     * 学校编号
     */
    private Integer schoolNo;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校地址
     */
    private String schoolAddress;

    /**
     * 学校类型
     */
    private Integer schoolType;

    /**
     * 学校官网
     */
    private String schoolWebsite;
}
