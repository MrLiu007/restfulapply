package com.magic.liuzm.controller.v1;

import com.magic.liuzm.enums.HttpCodeEnum;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.dto.UserDTO;
import com.magic.liuzm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 用户角色接口（v1版本）
 *
 */
@RestController
@RequestMapping("/api/v1")
public class UserRolesController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/users/{userId}/roles/{roleId}")
    public Response<UserDTO> createUserRole(@PathVariable(value = "userId", required = true) Integer userId,
                                            @PathVariable(value = "roleId", required = true) Integer roleId) {
        if(userId == null || roleId == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 添加用户角色
        UserDTO result = userRoleService.createUserRole(userId,roleId);

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @DeleteMapping("/users/{userId}/roles/{roleId}")
    public Response deleteUserRole(@PathVariable(value = "userId", required = true) Integer userId,
                                   @PathVariable(value = "roleId", required = true) Integer roleId) {
        if(userId == null || roleId == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 删除学校
        boolean result = userRoleService.deleteUserRole(userId,roleId);

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @PutMapping("/users/{userId}/roles/{roleId}")
    public Response updateUserRole(@PathVariable(value = "userId", required = true) Integer userId,
                                   @PathVariable(value = "roleId", required = true) Integer roleId) {
        if(userId == null || roleId == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 修改角色
        boolean result = userRoleService.updateUserRole(userId,roleId);

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @PatchMapping("/users/{userId}/roles/{roleId}")
    public Response updateUserRole(@PathVariable(name = "userId", required = true) Integer userId,
                                 @PathVariable(value = "roleId", required = true) Integer roleId,
                                 @RequestParam(name = "enabled", required = true) Integer enabled) {
        if(userId == null || roleId == null || enabled == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 设置用户角色是否可用
        boolean result = userRoleService.updateUserRole(userId,roleId,enabled);

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @GetMapping("/users/{userId}/roles")
    public Response<UserDTO> queryUserRole(@PathVariable(value = "userId", required = true) Integer userId) {
        if(userId == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 查询所有学校信息
        UserDTO result = userRoleService.queryUserRoles();

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @GetMapping("/users/{userId}/roles/{roleId}")
    public Response<UserDTO> queryUserRole(@PathVariable(value = "userId", required = true) Integer userId,
                                           @PathVariable(value = "roleId", required = true) Integer roleId) {
        if(userId == null || roleId == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 查询单个指定学校信息
        UserDTO result  = userRoleService.queryUserRole(userId,roleId);

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }
}
