package com.magic.liuzm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zemin.liu
 * @date 2020/12/15 15:39
 * @description SchoolsController进行mock
 *
 *
 * 场景：Mock controller,其他场景请看：https://github.com/MrLiu007/mockapply
 *
 * 步骤：
 * 1.pom 引入spring-boot-starter-test
 * 2.创建*Controller类，并提供相关接口
 * 3.编写测试类
 *
 */
@SpringBootTest
@ComponentScan(basePackages = "com.magic.liuzm.controller.v1")
public class SchoolControllerTest {

    /**
     * 当前工程上下文
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * 用springframework test的Mockmvc对象
     */
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

//    @Test
//    public void whenUploadSuccess() throws Exception {
//        String result = mockMvc.perform(fileUpload("/file")
//                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        System.out.println(result);
//    }


    @Test
    public void whenGetOneSuccess() throws Exception {
        String result = mockMvc.perform(
                get("/api/v1/schools/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // 期望为成功且有数据正确
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.schoolNo").value(1))
                .andReturn().getResponse().getContentAsString(Charset.forName("UTF-8"));

        // 结果打印
        System.out.println(result);
    }

    @Test
    public void whenGetListSuccess() throws Exception {
        String result = mockMvc.perform(
                get("/api/v1/schools")
                        .contentType(MediaType.APPLICATION_JSON))
                // 期望为成功且有数据
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(10))
                .andReturn().getResponse().getContentAsString(Charset.forName("UTF-8"));

        // 结果打印
        System.out.println(result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        String content = "{\"schoolName\":\"学校1\",\"schoolAddress\":\"西安\",\"schoolType\":2,\"schoolWebsite\":\"https://github.com/MrLiu007\"}";
        String result = mockMvc.perform(post("/api/v1/schools").contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.schoolNo").value(1))
                .andReturn().getResponse().getContentAsString(Charset.forName("UTF-8"));

        System.out.println(result);
    }


    @Test
    public void whenUpdateSuccess() throws Exception {
        String content = "{\"schoolNo\":1,\"schoolName\":\"学校1\",\"schoolAddress\":\"西安\",\"schoolType\":2,\"schoolWebsite\":\"https://github.com/MrLiu007\"}";
        String result = mockMvc.perform(put("/api/v1/schools").contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn().getResponse().getContentAsString(Charset.forName("UTF-8"));

        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        String result = mockMvc.perform(delete("/api/v1/schools/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn().getResponse().getContentAsString(Charset.forName("UTF-8"));

        System.out.println(result);
    }

    @Test
    public void whenPatchSuccess() throws Exception {
        String result = mockMvc.perform(patch("/api/v1/schools/website/1")
                .param("website", "https://github.com/MrLiu007")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn().getResponse().getContentAsString(Charset.forName("UTF-8"));

        System.out.println(result);
    }

}
