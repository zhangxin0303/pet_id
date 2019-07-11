package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminScheduleJobDao;
import com.cqcej.web.modules.admin.entity.AdminScheduleJobEntity;
import com.cqcej.web.modules.admin.service.AdminScheduleJobService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("adminScheduleJobService")
public class AdminScheduleJobServiceImpl extends ServiceImpl<AdminScheduleJobDao, AdminScheduleJobEntity> implements AdminScheduleJobService {
	
	@Override
	public PageUtils<AdminScheduleJobEntity> list(Integer page,Integer size) {
		int start = (page - 1) *size;
		List<AdminScheduleJobEntity> list = baseMapper.list(start,size);
		int count = baseMapper.getListCount();
		PageUtils<AdminScheduleJobEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	
	@Override
	public Map<String, Integer> getStartForbiddenCount() {
		Map<String,Integer> data = new HashMap<>();
		Integer start = baseMapper.getStartCount();//启用任务数量
		Integer forbidden = baseMapper.getForbiddenCount();//禁用任务数量
		data.put("start",start);
		data.put("forbidden",forbidden);
		return data;
	}
	//禁用/启用任务
	@Override
	public int updateStstus(Long jobId, Integer status) {
		return baseMapper.updateStatus(jobId,status);
	}
	
	//删除任务
	@Override
	public int deleteSchedule(Long jobId) {
		return baseMapper.deleteSchedule(jobId);
	}
	//新增任务
	@Override
	public int saveScheduleJob(AdminScheduleJobEntity scheduleJob) {
		return baseMapper.saveScheduleJob(scheduleJob);
	}
	//查询单个job信息
	@Override
	public AdminScheduleJobEntity getSchedulejobById(Long jobId) {
		return baseMapper.getSchedulejobById(jobId);
	}
	
	//修改job信息
	@Override
	public int updateScheduleJob(AdminScheduleJobEntity scheduleJob) {
		return baseMapper.updateScheduleJob(scheduleJob);
	}
}
