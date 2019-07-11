package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.WorkerEntity;

import java.util.Map;

/**
 * 工作者，包含医生，接送者，遛狗人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
public interface WorkerService extends IService<WorkerEntity> {
	
	PageUtils queryPage(Map<String, Object> params);
}

