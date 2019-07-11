package com.cqcej.web.modules.admin.controller;


import com.cqcej.web.common.utils.ConfigConstant;
import com.cqcej.web.common.utils.ImgFileUtil;
import com.cqcej.web.common.utils.R;
import com.cqcej.web.modules.admin.service.ConfigService;
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
@RequestMapping("admin/image")
@Api(description = "Admin图片接口")
public class AdminCloudController {
	
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
	public String uploadImgQiniu(MultipartFile[] file) throws IOException {
		CloudStorageConfig config = configService.getConfigObject(KEY, CloudStorageConfig.class);
		return ImgFileUtil.uploadCloud(file, config);
	}
	
	/**
	 * 删除图片
	 *
	 * @param key 图片路径
	 * @return R
	 */
	@ApiOperation("删除图片")
	@DeleteMapping("/delete")
	@ApiImplicitParam(name = "key", value = "图片路径", required = true, dataType = "string", paramType = "query")
	public R deleteCloudImg(String key) {
		CloudStorageConfig config = configService.getConfigObject(KEY, CloudStorageConfig.class);
		return R.ok(ImgFileUtil.deleteCloud(key, config));
	}
	
	
}
