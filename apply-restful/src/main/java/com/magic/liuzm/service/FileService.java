package com.magic.liuzm.service;

import com.magic.liuzm.dto.FileDTO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/12/16 17:28
 * @description 文件服务
 */
@Service
public class FileService {

    // 文件目录
    private final String path = "/Users/cicada/IdeaProjects/github/restfulapply/apply-restful/src/main/resources/file";

    /**
     * @author zemin.liu
     * @description 处理上传文件
     * @date 2020/12/16 17:32
     * @param files
     * @return com.magic.liuzm.dto.FileDTO
     */
    public FileDTO upload(List<MultipartFile> files) {
        FileDTO result = new FileDTO();
        files.forEach(file ->{
            try {
                // 带后缀
                StringBuffer newFileName = new StringBuffer(file.getOriginalFilename());
                File localFile = new File(path, newFileName.toString());
                file.transferTo(localFile);
                result.setPath(localFile.getAbsolutePath());
                result.setFileSize(file.getSize());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    /**
     * @author zemin.liu
     * @description 下载文件
     * @date 2020/12/17 10:06
     * @param fileKey
     * @param response
     * @return void
     */
    public void download(String fileKey, HttpServletResponse response){
        try {
            // fileKey 对应文件名
            InputStream in = new FileInputStream(new File(path, fileKey + ".txt"));
            // 设置编码
            response.setCharacterEncoding("UTF-8");
            OutputStream out = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=source.txt");
            // 写数据
            IOUtils.copy(in, out);
            // 刷新缓存，打印出来
            out.flush();

            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
