package com.magic.liuzm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/24 11:21
 * @description 用户信息
 */
@Data
public class UserDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户角色
     */
    private List<RoleDTO> roles;
}
