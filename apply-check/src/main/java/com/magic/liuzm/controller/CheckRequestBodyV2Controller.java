package com.magic.liuzm.controller;

import com.magic.liuzm.dto.Response;
import com.magic.liuzm.dto.SchoolCheckDTO;
import com.magic.liuzm.enums.HttpCodeEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 检查RequestBody接口（v2版本）
 *
 * 注意：这里需要全局异常捕获
 */
@RestController
@RequestMapping("/check/v2")
public class CheckRequestBodyV2Controller {

    @PostMapping("/schools")
    public Response<SchoolCheckDTO> createSchool(@Validated @RequestBody SchoolCheckDTO input) {
        if(input == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 添加学校（具体业务省略）
        SchoolCheckDTO result = new SchoolCheckDTO();

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }
}
