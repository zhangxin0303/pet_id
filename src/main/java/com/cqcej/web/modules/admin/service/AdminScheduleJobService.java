package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-05 14:17:00
 */
public interface AdminScheduleJobService extends IService<AdminScheduleJobEntity> {
	
	PageUtils<AdminScheduleJobEntity> list(Integer page,Integer size);
	
	Map<String,Integer> getStartForbiddenCount();
	
	int updateStstus(Long jobId,Integer status);
	
	int deleteSchedule(Long jobId);
	
	int saveScheduleJob(AdminScheduleJobEntity scheduleJob);
	
	AdminScheduleJobEntity getSchedulejobById(Long jobId);
	
	int updateScheduleJob(AdminScheduleJobEntity scheduleJob);
}

