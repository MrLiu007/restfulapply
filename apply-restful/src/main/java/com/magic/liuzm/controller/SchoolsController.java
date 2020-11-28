package com.magic.liuzm.controller;

import com.magic.liuzm.base.HttpCodeEnum;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.dto.SchoolDTO;
import com.magic.liuzm.service.SchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 学校接口（v1版本）
 *
 * 参考：http://www.restfulapi.nl/laravel/angularjs/employees/api/v1
 */
@RestController
@RequestMapping("/api/v1")
public class SchoolsController {

    @Autowired
    private SchoolsService schoolsService;

    @PostMapping("/schools")
    public Response<SchoolDTO> createSchool(@RequestBody SchoolDTO input) {
        if(input == null){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 添加学校
        SchoolDTO result = schoolsService.createSchool(input);

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @DeleteMapping("/schools/{id}")
    public Response deleteSchool(@PathVariable(value = "id", required = true) Integer schoolNo) {
        if(schoolNo == null){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 删除学校
        boolean result = schoolsService.deleteSchool(schoolNo);

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @PutMapping("/schools")
    public Response updateSchool(@RequestBody SchoolDTO input) {
        if(input == null || input.getSchoolNo() == null){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 修改学校的全部信息
        boolean result = schoolsService.updateSchool(input);

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @PatchMapping("/schools/website/{id}")
    public Response updateSchool(@PathVariable(name = "id", required = true) Integer schoolNo,
                                 @RequestParam(name = "website", required = true) String webSite) {
        if(schoolNo == null || StringUtils.isEmpty(webSite)){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 仅修改学校网站
        SchoolDTO input = new SchoolDTO();
        input.setSchoolNo(schoolNo);
        input.setSchoolWebsite(webSite);
        boolean result = schoolsService.updateSchool(input);

        return result == true ? Response.ok("操作成功") : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @GetMapping("/schools")
    public Response<List<SchoolDTO>> querySchool() {
        // 查询所有学校信息
        List<SchoolDTO> result = schoolsService.getSchools();

        return !CollectionUtils.isEmpty(result) ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }

    @GetMapping("/schools/{id}")
    public Response<SchoolDTO> querySchool(@PathVariable(value = "id", required = true) Integer schoolNo) {
        if(schoolNo == null){
            Response.error(HttpCodeEnum.BAD_REQUEST);
        }
        // 查询单个指定学校信息
        SchoolDTO result  = schoolsService.getSchoolByNo(schoolNo);

        return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
    }
}
