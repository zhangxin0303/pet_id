package com.cqcej.web.common.utils;

import com.cqcej.web.modules.oss.cloud.CloudStorageConfig;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class QiniuCloudUtil {
	/**
	 * 上传文件到七牛云存储
	 *
	 * @param file
	 * @return 多图上传
	 * @throws IOException
	 */
	public static String uploadImgQiniu(MultipartFile[] file, CloudStorageConfig c) throws IOException {
		String path = "";
		if (null != file && file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				FileInputStream inputStream = (FileInputStream) file[i].getInputStream();
				String key = Calendar.getInstance().getTimeInMillis() + UUID.randomUUID().toString().replace("-", "");
				if (i == file.length - 1) { //多图片上传时拼接返回地址
					path += uploadQNImg(inputStream, key, c); // key为生成图片的随机名
				} else {
					path += uploadQNImg(inputStream, key, c) + ",";
				}
			}
			return path;
		}
		return null;
	}
	
	/**
	 * 上传文件到七牛云存储
	 *
	 * @param file
	 * @return 单图上传 重载
	 * @throws IOException
	 */
	public static String uploadImgQiniu(MultipartFile file, CloudStorageConfig c) throws IOException {
		if (null != file) {
			FileInputStream inputStream = (FileInputStream) file.getInputStream();
			String key = Calendar.getInstance().getTimeInMillis() + UUID.randomUUID().toString().replace("-", "");
			return uploadQNImg(inputStream, key, c); // key为生成图片的随机名
		}
		return null;
	}
	
	/**
	 * 将图片上传到七牛云
	 */
	private static String uploadQNImg(FileInputStream file, String key, CloudStorageConfig config) {
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		// 其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// 生成上传凭证，然后准备上传
		try {
			Auth auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
			String upToken = auth.uploadToken(config.getQiniuBucketName());
			try {
				Response response = uploadManager.put(file, key, upToken, null, null);
				// 解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				
				String returnPath = config.getQiniuDomain() + "/" + putRet.key;
				return returnPath;
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//删除七牛云图片
	public static int deleteQiniu(String key, CloudStorageConfig config) {
		//获取请求路径下的图片名称
		String imgName = key.substring(key.lastIndexOf("/") + 1);
		//Auth验证
		Auth auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
		Configuration configuration = new Configuration(Zone.autoZone());
		BucketManager bucketMgr = new BucketManager(auth, configuration);
		//指定需要删除的文件key，和文件所在的存储空间
		String bucketName = config.getQiniuBucketName();
		try {
			Response response = bucketMgr.delete(bucketName, imgName);
			//response.statusCode == 200 表示删除成功
			return response.statusCode;
		} catch (QiniuException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
