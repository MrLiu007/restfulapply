package com.magic.liuzm.controller;

import com.magic.liuzm.dto.Response;
import com.magic.liuzm.enums.HttpCodeEnum;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Pattern;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 检查RequestParam接口（v1版本）
 *
 * 注意：这里需要全局异常捕获
 */
@RestController
@RequestMapping("/check/v1")
@Validated
public class CheckRequestParamController {

    @PatchMapping("/schools/tel/{id}")
    public Response updateSchool(@PathVariable(name = "id", required = true) Integer schoolNo,
                                 @Pattern(message = "学校电话不正确",regexp = "\"^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\\\d{8}$\"") @RequestParam(name = "tel", required = true) String tel) {
        if(schoolNo == null || StringUtils.isEmpty(tel)){
            return Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 修改联系方式（具体业务省略）
        boolean result = true;

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }


}
