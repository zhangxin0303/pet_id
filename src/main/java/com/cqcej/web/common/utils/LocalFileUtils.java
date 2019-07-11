package com.cqcej.web.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * 上传文件到本地
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-10-29 10:38
 */
@Configuration
public class LocalFileUtils {
	
	
	private static String imgPath;
	
	@Value("${cqcej.imgUpload.path}")
	public void setimgPath(String imgPath) {
		LocalFileUtils.imgPath = imgPath;
	}
	
	//添加资讯时候用过此属性获取图片前缀
	public static String imageUrl;
	
	@Value("${cqcej.imgUpload.image-url}")
	public void setimageUrl(String imageUrl) {
		LocalFileUtils.imageUrl = imageUrl;
	}
	
	private static EmbeddedServletContainerInitializedEvent event;
	
	//重载
	public static String uploadCloud(MultipartFile[] file) throws IOException {
		String path = "";
		for (int i = 0; i < file.length; i++) {
			if (file.length - 1 == i) {
				path += uploadLocal(file[i]);
			} else {
				path += uploadLocal(file[i]) + ",";
			}
		}
		return path;
	}
	
	//重载
	public static String uploadCloud(MultipartFile file) throws IOException {
		return uploadLocal(file);
	}
	
	//上传图片到本地
	public static String uploadLocal(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			return "error";
		}
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		//上传jpeg格式的图片调整为jpg
		if(suffixName.equalsIgnoreCase(".jpeg")){
			suffixName = ".jpg";
		}
		fileName = Calendar.getInstance().getTimeInMillis() + UUID.randomUUID().toString().replaceAll("-", "") + suffixName;
		File dest = new File(imgPath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			//返回图片地址为----http://img.cqcej.com:9090/123456.jpg
			String config = imageUrl + fileName;
			return config;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "server Error";
	}
	
	/**
	 * 删除图片
	 *
	 * @param key 图片访问路径如:http://img.cqcej.com/123.jpg
	 */
	public static int delete(String key) {
		Boolean result;
		try {
			//获取请求路径下的图片名称
			String imgName = key.substring(key.lastIndexOf("/") + 1);
			File f = new File(imgPath + imgName);
			result = f.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return result == true ? 200 : -1;
	}
}
