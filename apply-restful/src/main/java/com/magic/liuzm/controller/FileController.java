/**
 * 
 */
package com.magic.liuzm.controller;

import com.magic.liuzm.dto.FileDTO;
import com.magic.liuzm.dto.Response;
import com.magic.liuzm.enums.HttpCodeEnum;
import com.magic.liuzm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/24 10:46
 * @description 上传文件接口
 */
@RestController
@RequestMapping("/file/v1")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("upload")
	public Response<FileDTO> upload(@RequestParam(value = "file") List<MultipartFile> files){
		if(CollectionUtils.isEmpty(files)){
			return Response.error(HttpCodeEnum.BAD_REQUEST);
		}

		FileDTO result = fileService.upload(files);

		return result != null ? Response.ok(result) : Response.error(HttpCodeEnum.NOT_FOUND);
	}

	@GetMapping("/download/{id}")
	public void download(@PathVariable(value = "id") String fileKey, HttpServletResponse response){
		if(StringUtils.isEmpty(fileKey)){
			return;
		}
		fileService.download(fileKey,response);
	}

}
