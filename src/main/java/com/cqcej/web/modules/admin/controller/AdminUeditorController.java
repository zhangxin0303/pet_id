package com.cqcej.web.modules.admin.controller;


import com.cqcej.web.common.utils.ConfigConstant;
import com.cqcej.web.common.utils.ImgFileUtil;
import com.cqcej.web.modules.admin.service.ConfigService;
import com.cqcej.web.modules.oss.cloud.CloudStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/img")
public class AdminUeditorController {
	
	private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	@Autowired
	private ConfigService configService;
	
	/**
	 *  UEditor图片上传后台接口
	 * @param request
	 * @param response
	 * @param action ueditor请求动作(首次请求值为config,验证后台接口是否可用。第二次请求上传图片)
	 * @param callback 回调字符串(用于跨域请求)
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/ueditor")
	public String ueditor(HttpServletRequest request, HttpServletResponse response, String action, String callback) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
		CloudStorageConfig config = configService.getConfigObject(KEY, CloudStorageConfig.class);
		if ("config".equals(action)) {
			String s = "{\n" + "                \"imageActionName\": \"imgUpload\",\n"
					+ "                \"imageFieldName\": \"file\", \n"
					+ "                \"imageMaxSize\": 2048000, \n"
					+ "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n"
					+ "                \"imageCompressEnable\": true, \n"
					+ "                \"imageCompressBorder\": 800, \n"
					+ "                \"imageInsertAlign\": \"center\",\n"
					+ "                \"imageUrlPrefix\": \"\"}";
			return callback + "(" + s + ")";
		} else {
			String contentType = request.getContentType();
			if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request,
						MultipartHttpServletRequest.class);
				MultipartFile file = multipartRequest.getFile("file");
				return imgUpload(file, config);
			}
			return "error";
		}
	}
	
	// upLoad
	public String imgUpload(MultipartFile file, CloudStorageConfig config) throws IOException {
		String path = ImgFileUtil.uploadCloud(file, config);//path为图片上传过后的可访问链接如http://img.cqcej.com/123123.jpog
		String json = "{\"state\": \"SUCCESS\"," + "\"url\": \"" + path + "\","
				+ "\"original\": \"" + file.getOriginalFilename() + "\",\"title\": \"" + file.getOriginalFilename() + "\"}";
		return json;
	}
}

