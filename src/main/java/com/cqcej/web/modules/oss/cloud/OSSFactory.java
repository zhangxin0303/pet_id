/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.cqcej.web.modules.oss.cloud;


import com.cqcej.web.common.utils.AdminConstant;
import com.cqcej.web.common.utils.ConfigConstant;
import com.cqcej.web.common.utils.SpringContextUtils;
import com.cqcej.web.modules.admin.service.ConfigService;

/**
 * 文件上传Factory
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-26 10:18
 */
public final class OSSFactory {
	private static ConfigService configService;
	
	static {
		OSSFactory.configService = (ConfigService) SpringContextUtils.getBean("configService");
	}
	
	public static CloudStorageService build() {
		//获取云存储配置信息
		CloudStorageConfig config = configService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
		
		if (config.getType() == AdminConstant.CloudService.QINIU.getValue()) {
			return new QiniuCloudStorageService(config);
		} else if (config.getType() == AdminConstant.CloudService.ALIYUN.getValue()) {
			return new AliyunCloudStorageService(config);
		} else if (config.getType() == AdminConstant.CloudService.QCLOUD.getValue()) {
			return new QcloudCloudStorageService(config);
		}
		
		return null;
	}
	
}
