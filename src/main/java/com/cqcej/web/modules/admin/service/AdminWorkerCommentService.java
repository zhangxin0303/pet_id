package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentResultEntity;

import java.util.Map;

/**
 * 评论工作人员
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-11 14:39:18
 */
public interface AdminWorkerCommentService extends IService<AdminWorkerCommentEntity> {

	PageUtils queryPage(Map<String, Object> params);

	PageUtils<AdminWorkerCommentResultEntity> getWorkerCommentResultList(Integer workerType, String workerName, Integer page, Integer size);

	PageUtils<AdminServiceOrderCommentEntity> getOrderCommentList(Integer workerId, Integer commentLevel, Integer page, Integer size);
}

