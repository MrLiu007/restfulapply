package com.magic.liuzm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zemin.liu
 * @date 2020/11/24 11:21
 * @description 角色信息
 */
@Data
public class RoleDTO implements Serializable {

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleHint;

    /**
     * 角色是否可用 0-可用，1-不可用
     */
    private Integer enabled;
}
