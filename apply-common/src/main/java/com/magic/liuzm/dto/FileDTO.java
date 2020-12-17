package com.magic.liuzm.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/24 11:21
 * @description 文件信息
 */
@Data
public class FileDTO implements Serializable {

    /**
     * 路径
     */
    private String path;

    /**
     * 大小(字节)
     */
    private Long fileSize;
}
