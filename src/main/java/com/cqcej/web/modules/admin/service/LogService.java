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

package com.cqcej.web.modules.admin.service;


import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-08 10:40:56
 */
public interface LogService extends IService<SysLogEntity> {
	
	PageUtils<SysLogEntity> queryPage(Map<String, Object> params);
	
}
