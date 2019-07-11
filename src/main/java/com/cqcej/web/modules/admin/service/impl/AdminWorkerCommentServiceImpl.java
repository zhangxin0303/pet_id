package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminWorkerCommentDao;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentResultEntity;
import com.cqcej.web.modules.admin.service.AdminWorkerCommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("adminWorkerCommentService")
public class AdminWorkerCommentServiceImpl extends ServiceImpl<AdminWorkerCommentDao, AdminWorkerCommentEntity> implements AdminWorkerCommentService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AdminWorkerCommentEntity> page = this.selectPage(new Query<AdminWorkerCommentEntity>(params).getPage(),
				new EntityWrapper<AdminWorkerCommentEntity>()
		);
		return new PageUtils(page);
	}
	// - 查询工作人员评价 -
	@Override
	public PageUtils<AdminWorkerCommentResultEntity> getWorkerCommentResultList(Integer workerType,String workerName,Integer page,Integer size) {
		int start = (page-1) * size;
		List<AdminWorkerCommentResultEntity>  list =  baseMapper.getWorkerCommentResultList(workerType,workerName,start,size);
		int count = baseMapper.getWorkerCommentCount(workerType,workerName);
		PageUtils<AdminWorkerCommentResultEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	// - 查询订单评价 -
	@Override
	public PageUtils<AdminServiceOrderCommentEntity> getOrderCommentList(Integer workerId, Integer commentLevel,Integer page,Integer size) {
		int start = (page-1) * size;
		List<AdminServiceOrderCommentEntity> list = baseMapper.getOrderCommentList(workerId, commentLevel,start,size);
		int count = baseMapper.getOrderCommentCount(workerId, commentLevel);
		PageUtils<AdminServiceOrderCommentEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
}
