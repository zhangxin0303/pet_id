package com.cqcej.web.modules.admin.controller;

import com.cqcej.web.common.utils.ConfigConstant;
import com.cqcej.web.common.utils.ImgFileUtil;
import com.cqcej.web.modules.admin.service.ConfigService;
import com.cqcej.web.modules.oss.cloud.CloudStorageConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/wang")
@Api(description = "AdminWangEditor编辑器接口")
public class AdminWangEditorController {
	private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	@Autowired
	private ConfigService configService;
	/**
	 * 多图片上传
	 *
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	@ApiOperation("图片上传接口")
	@ApiImplicitParam(name = "file", value = "文件名称", required = true, dataType = "file", paramType = "query")
	public String imgUpdate(MultipartFile[] file) throws IOException {
		CloudStorageConfig config = configService.getConfigObject(KEY,CloudStorageConfig.class);
		String imgPath = ImgFileUtil.uploadCloud(file,config);
		String[] temp = imgPath.split(",");//给多个返回路径(如：http://qiniu/123.jpg,http://qiniu/465.jpg)加上引号。
		String path = "";
		for (int i = 0; i < temp.length; i++) {
			if (i == temp.length - 1) {
				path += "\"" + temp[i] + "\"";
			} else {
				path += "\"" + temp[i] + "\"" + ",";
			}
		}
		return "{\"errno\": 0," + "\"data\": [" + path + "]}";
	}
}
