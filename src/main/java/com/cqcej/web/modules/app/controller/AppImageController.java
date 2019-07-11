package com.cqcej.web.modules.app.controller;


import com.cqcej.web.common.utils.ConfigConstant;
import com.cqcej.web.common.utils.ImgFileUtil;
import com.cqcej.web.modules.admin.service.ConfigService;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.oss.cloud.CloudStorageConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("app/image")
@Api(description = "App图片接口")
public class AppImageController {
	
	private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	@Autowired
	private ConfigService configService;
	
	/**
	 * 上传接口
	 *
	 * @param file
	 * @return 图片路径, 多个用逗号隔开
	 * @throws IOException
	 */
	@PostMapping("/upload")
	@ApiOperation("上传图片(上传多张时候返回路径会用逗号隔开)")
	@ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "file", paramType = "form")
	public BaseResponse<String> uploadImgQiniu(MultipartFile[] file) throws IOException {
		CloudStorageConfig config = configService.getConfigObject(KEY, CloudStorageConfig.class);
		return new BaseResponse(ImgFileUtil.uploadCloud(file, config));
	}
	
	/**
	 * 删除图片
	 *
	 * @param key 图片路径
	 * @return R
	 */
	@ApiOperation("删除图片")
	@DeleteMapping("/delete")
	@ApiImplicitParam(name = "key", value = "图片访问路径", required = true, dataType = "string", paramType = "query")
	public BaseResponse<Boolean> deleteCloudImg(String key) {
		CloudStorageConfig config = configService.getConfigObject(KEY, CloudStorageConfig.class);
		return new BaseResponse<>(ImgFileUtil.deleteCloud(key, config) > 0 ? true : false);
	}
}







