package com.cqcej.web.common.utils;

import com.cqcej.web.modules.oss.cloud.CloudStorageConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 图片上传至云储存工具类
 *
 * @author JiaMin
 * @version 1.0
 * @date 2018年9月27日 16:38:14
 */

public class ImgFileUtil {
	
	/**
	 * 云储存上传接口
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String uploadCloud(MultipartFile[] file, CloudStorageConfig config) throws IOException {
		if (config.getType() == AdminConstant.CloudService.QINIU.getValue()) {
			//上传到七牛
			return QiniuCloudUtil.uploadImgQiniu(file, config);
		} else if (config.getType() == AdminConstant.CloudService.ALIYUN.getValue()) {
			//暂未配置
		} else if (config.getType() == AdminConstant.CloudService.LOCAL.getValue()) {
			//上传到本地
			return LocalFileUtils.uploadCloud(file);
		}
		return "server Error";
	}
	
	/**
	 * 云储存上传接口 重载
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String uploadCloud(MultipartFile file, CloudStorageConfig config) throws IOException {
		if (config.getType() == AdminConstant.CloudService.QINIU.getValue()) {
			return QiniuCloudUtil.uploadImgQiniu(file, config);
		} else if (config.getType() == AdminConstant.CloudService.ALIYUN.getValue()) {
			//暂未配置
		} else if (config.getType() == AdminConstant.CloudService.LOCAL.getValue()) {
			//上传到本地
			return LocalFileUtils.uploadCloud(file);
		}
		return "server Error";
	}
	
	/**
	 * 删除图片
	 *
	 * @param key 图片名称
	 * @return 200 表示删除成功
	 */
	public static int deleteCloud(String key, CloudStorageConfig config) {
		try {
			if (config.getType() == AdminConstant.CloudService.QINIU.getValue()) {
				return QiniuCloudUtil.deleteQiniu(key, config);
			} else if (config.getType() == AdminConstant.CloudService.ALIYUN.getValue()) {
				//暂未配置
			} else if (config.getType() == AdminConstant.CloudService.LOCAL.getValue()) {
				//删除本地
				return LocalFileUtils.delete(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
}
