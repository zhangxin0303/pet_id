package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminBankCardEntity;
import com.cqcej.web.modules.admin.entity.AdminPickUpEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerEntity;

import java.util.List;
import java.util.Map;

/**
 * 工作者，包含医师，接送者，遛狗人员
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-27 11:23:32
 */
public interface AdminWorkerService extends IService<AdminWorkerEntity> {

	PageUtils queryPage(Map<String, Object> params);

	PageUtils<AdminPickUpEntity> selectPickUp(Map<String,Object> map);
	
	PageUtils<AdminPickUpEntity> selectWorkerLocation(Integer page, Integer size, Integer status,String realname);
	
	AdminUserEntity getPickUp(Long workerId);
	
	List<AdminBankCardEntity> detail(Long workerId);
	
	int insertWorker(AdminUserEntity worker);
	
	int updateWorkerById(AdminUserEntity user);
}

