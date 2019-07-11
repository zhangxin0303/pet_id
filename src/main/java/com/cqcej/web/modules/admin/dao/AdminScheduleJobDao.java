package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定时任务
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-05 14:17:00
 */
@Mapper
public interface AdminScheduleJobDao extends BaseMapper<AdminScheduleJobEntity> {
	
	List<AdminScheduleJobEntity> list(@Param("start") Integer start,@Param("size") Integer size);
	
	Integer getListCount();
	
	Integer getStartCount();
	
	Integer getForbiddenCount();
	
	int updateStatus(@Param("jobId") Long jobId,@Param("status") Integer status);
	
	int deleteSchedule(@Param("jobId") Long jobId);
	
	int saveScheduleJob(AdminScheduleJobEntity scheduleJob);
	
	AdminScheduleJobEntity getSchedulejobById(@Param("jobId") Long jobId);
	
	int updateScheduleJob(AdminScheduleJobEntity scheduleJob);
}
