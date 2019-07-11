package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminWorkerDao;
import com.cqcej.web.modules.admin.entity.AdminBankCardEntity;
import com.cqcej.web.modules.admin.entity.AdminPickUpEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerEntity;
import com.cqcej.web.modules.admin.service.AdminWorkerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("adminWorkerService")
public class AdminWorkerServiceImpl extends ServiceImpl<AdminWorkerDao, AdminWorkerEntity> implements AdminWorkerService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AdminWorkerEntity> page = this.selectPage(
				new Query<AdminWorkerEntity>(params).getPage(),
				new EntityWrapper<AdminWorkerEntity>()
		);
		return new PageUtils(page);
	}

	//接送人员信息
	@Override
	public PageUtils<AdminPickUpEntity> selectPickUp(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());
		int start = (page - 1) * size;
		params.put("start",start);
		params.put("size",size);
		List<AdminPickUpEntity> list = baseMapper.selectPickUp(params);
		int count = baseMapper.selectPickUpCount(params);
		PageUtils<AdminPickUpEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	
	//接送人员地理位置
	@Override
	public PageUtils<AdminPickUpEntity> selectWorkerLocation(Integer page, Integer size, Integer status,String realname) {
		int start = (page - 1) * size;
		Integer count = baseMapper.selectWorkerCount(status,realname);//接送人员在,离线总人数
		
		List<AdminPickUpEntity> workers = baseMapper.selectWorkerLocation(start,size,status, realname);//人员信息
		PageUtils<AdminPickUpEntity> data = new PageUtils(workers,count,page,size);
		
		return data;
	}
	
	//基本信息
	@Override
	public AdminUserEntity getPickUp(Long workerId) {
		return baseMapper.getPickUp(workerId);
	}
	//详细信息
	@Override
	public List<AdminBankCardEntity> detail(Long workerId) {
		return baseMapper.detail(workerId);
	}
	
	@Override
	public int insertWorker(AdminUserEntity worker) {
		int r1 = baseMapper.insertUserOfPickUp(worker);
		int r2 = baseMapper.insertWorkerOfPickUp(worker);
		if(r1 > 0 && r2 > 0){
			return 1;
		}
		return 0;
	}
	
	//编辑
	@Override
	public int updateWorkerById(AdminUserEntity user) {
		
		int res = baseMapper.updateUserById(user);
		int r = baseMapper.updateWorker(user.getWorkerId(),user.getWalkDogFee(),user.getType(),user.getWorkerType());
		if(res > 0 && r > 0){
			return 1;
		}
		return 0;
	}
}
