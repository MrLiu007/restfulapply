package com.magic.liuzm.controller;

import com.magic.liuzm.dto.SchoolCheckDTO;
import com.magic.liuzm.enums.HttpCodeEnum;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.util.Json2BindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 检查RequestBody接口（v1版本）
 */
@RestController
@RequestMapping("/check/v1")
public class CheckRequestBodyController {

    @PostMapping("/schools")
    public Response<SchoolCheckDTO> createSchool(@Validated @RequestBody SchoolCheckDTO input, BindingResult bindingResult) {
        if(input == null){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 通过BindingResult将@Validated检查结果返回前端
        if(bindingResult.hasErrors()){
            return Response.error(HttpCodeEnum.BAD_REQUEST.getCode(), Json2BindingResult.parseBindingResult(bindingResult.getAllErrors()));
        }

        // 添加学校（具体业务省略）
        SchoolCheckDTO result = new SchoolCheckDTO();

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }
}
