package com.magic.liuzm.service;

import com.google.common.collect.Lists;
import com.magic.liuzm.dto.SchoolDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/24 11:29
 * @description
 */
@Service
public class SchoolsService {

    /**
     * @author zemin.liu
     * @description 查询全部学校列表
     * @date 2020/11/24 11:31
     * @return java.util.List<com.magic.liuzm.dto.SchoolDTO>
     */
    public List<SchoolDTO> getSchools() {
        List result = Lists.newArrayList();
        for(int i = 0; i < 10 ; i++){
            SchoolDTO data = new SchoolDTO();
            data.setSchoolNo(i);
            data.setSchoolName("学校"+i);
            data.setSchoolAddress("陕西西安");
            data.setSchoolType(2);
            data.setSchoolWebsite("https://github.com/MrLiu007");
            result.add(data);
        }
        return result;
    }

    /**
     * @author zemin.liu
     * @description 根据学校编号查询
     * @date 2020/11/26 20:37
     * @param schoolNo
     * @return com.magic.liuzm.dto.SchoolDTO
     */
    public SchoolDTO getSchoolByNo(Integer schoolNo) {
        if(schoolNo == null){
            return null;
        }
        SchoolDTO data = new SchoolDTO();
        data.setSchoolNo(schoolNo);
        data.setSchoolName("学校"+schoolNo);
        data.setSchoolAddress("陕西西安");
        data.setSchoolType(2);
        data.setSchoolWebsite("https://github.com/MrLiu007");
        return data;
    }

    /**
     * @author zemin.liu
     * @description 批量根据学校编号查询
     * @date 2020/11/26 20:37
     * @param schoolNos
     * @return com.magic.liuzm.dto.SchoolDTO
     */
    public List<SchoolDTO> getSchoolByNos(List<Integer> schoolNos) {
        List result = Lists.newArrayList();
        schoolNos.forEach(schoolNo ->{
            SchoolDTO data = new SchoolDTO();
            data.setSchoolNo(schoolNo);
            data.setSchoolName("学校" +  schoolNo);
            data.setSchoolAddress("陕西西安");
            data.setSchoolType(2);
            data.setSchoolWebsite("https://github.com/MrLiu007");
            result.add(data);
        });
        return result;
    }

    /**
     * @author zemin.liu
     * @description 创建学校
     * @date 2020/11/24 13:39
     * @param input
     * @return com.magic.liuzm.dto.SchoolDTO
     */
    public SchoolDTO createSchool(SchoolDTO input) {
        if (input == null){
            return null;
        }
        input.setSchoolNo(10);
        input.setSchoolName(input.getSchoolName() + 10);
        input.setSchoolType(2);
        return input;
    }

    /**
     * @author zemin.liu
     * @description 批量创建学校
     * @date 2020/11/24 13:39
     * @param input
     * @return com.magic.liuzm.dto.SchoolDTO
     */
    public List<SchoolDTO> createSchool(List<SchoolDTO> input) {
        if (input == null || input.size() == 0){
            return null;
        }
        List result = Lists.newArrayList();
        for(int i = 0; i< input.size();i++){
            SchoolDTO item = input.get(i);
            item.setSchoolNo(i);
            item.setSchoolName(item.getSchoolName() + i);
            item.setSchoolType(2);
            result.add(item);
        }
        return result;
    }

    /**
     * @author zemin.liu
     * @description 删除学校
     * @date 2020/11/26 20:42
     * @param schoolNo
     * @return boolean
     */
    public boolean deleteSchool(Integer schoolNo) {
        if(schoolNo == null){
            return false;
        }
        return true;
    }

    /**
     * @author zemin.liu
     * @description 批量删除学校
     * @date 2020/11/26 20:42
     * @param schoolNos
     * @return boolean
     */
    public boolean deleteSchools(List<Integer> schoolNos) {
        if(CollectionUtils.isEmpty(schoolNos)){
            return false;
        }
        return true;
    }

    /**
     * @author zemin.liu
     * @description 修改学校
     * @date 2020/11/26 20:42
     * @param input
     * @return boolean
     */
    public boolean updateSchool(SchoolDTO input) {
        if(input == null || input.getSchoolNo() == null){
            return false;
        }
        return true;
    }

    /**
     * @author zemin.liu
     * @description 批量修改学校
     * @date 2020/11/26 20:42
     * @param input
     * @return boolean
     */
    public boolean updateSchools(List<SchoolDTO> input) {
        if(CollectionUtils.isEmpty(input)){
            return false;
        }
        return true;
    }


}
