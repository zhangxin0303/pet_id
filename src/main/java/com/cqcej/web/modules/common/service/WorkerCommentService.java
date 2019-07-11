package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.WorkerCommentEntity;

import java.util.Map;

/**
 * 评论工作人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
public interface WorkerCommentService extends IService<WorkerCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
	
	void beautyComment(Long workerId, int beauty_number);
}

