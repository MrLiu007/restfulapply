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
 * @description 学校接口（v2版本）
 *
 * 相对于v1版本，增加批量操作
 */
@RestController
@RequestMapping("/api/v2")
public class SchoolsExController {

    @Autowired
    private SchoolsService schoolsService;

    @GetMapping("/schools/{ids}")
    public Response<List<SchoolDTO>> querySchool(@PathVariable(value = "ids", required = true) Integer[] schoolNos) {
        if(schoolNos == null || schoolNos.length == 0){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 批量查询指定学校信息
        List<SchoolDTO> result = schoolsService.getSchoolByNos(Lists.newArrayList(schoolNos));

        return !CollectionUtils.isEmpty(result) ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @DeleteMapping("/schools/{ids}")
    public Response deleteSchool(@PathVariable(value = "ids", required = true) Integer[] schoolNos) {
        if(schoolNos == null || schoolNos.length == 0){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 批量删除学校
        boolean result = schoolsService.deleteSchools(Lists.newArrayList(schoolNos));

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @PostMapping("/schools")
    public Response<List<SchoolDTO>> createSchool(@RequestBody List<SchoolDTO> input) {
        if(CollectionUtils.isEmpty(input)){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 批量添加学校
        List<SchoolDTO> result = schoolsService.createSchool(input);

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @PutMapping("/schools")
    public Response updateSchool(@RequestBody List<SchoolDTO> input) {
        if(CollectionUtils.isEmpty(input)){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 批量修改学校的全部信息
        boolean result = schoolsService.updateSchools(input);

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }
}
