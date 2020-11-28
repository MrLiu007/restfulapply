package com.magic.liuzm.controller;

import com.google.common.collect.Lists;
import com.magic.liuzm.base.HttpCodeEnum;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.dto.SchoolDTO;
import com.magic.liuzm.service.SchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 学校接口（v3版本）
 *
 * 相对于v2版本，大量数据在Url拼接ID，不够友好，可放body传入
 * 参考：https://www.npmjs.com/package/restful-api
 */
@RestController
@RequestMapping("/api/v3")
public class SchoolsSpecialController {

    @Autowired
    private SchoolsService schoolsService;

    @GetMapping("/schools")
    public Response<List<SchoolDTO>> querySchool(@RequestBody Integer[] schoolNos) {
        if(schoolNos == null || schoolNos.length == 0){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 批量查询指定学校信息
        List<SchoolDTO> result = schoolsService.getSchoolByNos(Lists.newArrayList(schoolNos));

        return !CollectionUtils.isEmpty(result) ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @DeleteMapping("/schools")
    public Response deleteSchool(@RequestBody Integer[] schoolNos) {
        if(schoolNos == null || schoolNos.length == 0){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 批量删除学校
        boolean result = schoolsService.deleteSchools(Lists.newArrayList(schoolNos));

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }
}
