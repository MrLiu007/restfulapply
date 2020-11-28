package com.magic.liuzm.service;

import com.google.common.collect.Lists;
import com.magic.liuzm.dto.RoleDTO;
import com.magic.liuzm.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/27 16:28
 * @description 用户角色
 */
@Service
public class UserRoleService {

    /**
     * @author zemin.liu
     * @description 添加用户角色
     * @date 2020/11/27 16:34
     * @param userId
     * @param roleId
     * @return com.magic.liuzm.dto.RoleDTO
     */
    public UserDTO createUserRole(Integer userId,Integer roleId) {
        if(userId == null || roleId == null){
            return null;
        }
        UserDTO result = new UserDTO();
        result.setUserId(userId);

        List<RoleDTO> list = Lists.newArrayList();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);
        roleDTO.setRoleName("db");
        roleDTO.setRoleHint("操作db能力");
        roleDTO.setEnabled(0);
        list.add(roleDTO);

        result.setRoles(list);
        return result;
    }

    /**
     * @author zemin.liu
     * @description 删除用户角色
     * @date 2020/11/27 17:40
     * @param userId
     * @param roleId
     * @return boolean
     */
    public boolean deleteUserRole(Integer userId, Integer roleId) {
        if(userId == null || roleId == null){
            return false;
        }
        return true;
    }

    /**
     * @author zemin.liu
     * @description 更新用户角色
     * @date 2020/11/27 17:40
     * @param userId
     * @param roleId
     * @return boolean
     */
    public boolean updateUserRole(Integer userId, Integer roleId) {
        if(userId == null || roleId == null){
            return false;
        }
        return true;
    }

    /**
     * @author zemin.liu
     * @description 更新用户角色是否可用
     * @date 2020/11/27 17:40
     * @param userId
     * @param roleId
     * @param enabled 0-可用；1-不可用
     * @return boolean
     */
    public boolean updateUserRole(Integer userId, Integer roleId,Integer enabled) {
        if(userId == null || roleId == null || enabled == null){
            return false;
        }
        return true;
    }

    /**
     * @author zemin.liu
     * @description 查询用户全部角色
     * @date 2020/11/27 17:40
     * @param
     * @return com.magic.liuzm.dto.UserDTO
     */
    public UserDTO queryUserRoles() {
        UserDTO result = new UserDTO();
        result.setUserId(1);

        List<RoleDTO> list = Lists.newArrayList();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(1);
        roleDTO.setRoleName("db");
        roleDTO.setRoleHint("操作db能力");
        roleDTO.setEnabled(0);
        list.add(roleDTO);

        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO2.setRoleId(2);
        roleDTO2.setRoleName("redis");
        roleDTO2.setRoleHint("操作redis能力");
        roleDTO2.setEnabled(0);
        list.add(roleDTO2);

        result.setRoles(list);
        return result;
    }

    /**
     * @author zemin.liu
     * @description 查询用户指定角色
     * @date 2020/11/27 17:41
     * @param userId
     * @param roleId
     * @return com.magic.liuzm.dto.UserDTO
     */
    public UserDTO queryUserRole(Integer userId, Integer roleId) {
        if(userId == null || roleId == null){
            return null;
        }
        UserDTO result = new UserDTO();
        result.setUserId(userId);

        List<RoleDTO> list = Lists.newArrayList();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);
        roleDTO.setRoleName("db");
        roleDTO.setRoleHint("操作db能力");
        roleDTO.setEnabled(0);
        list.add(roleDTO);

        result.setRoles(list);
        return result;
    }
}
