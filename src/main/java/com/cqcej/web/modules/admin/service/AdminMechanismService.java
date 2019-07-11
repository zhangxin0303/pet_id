package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminMechanismEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DetailEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DoctorEntity;
import com.cqcej.web.modules.admin.entity.mechanism.MechanBasicEntity;
import com.cqcej.web.modules.admin.entity.mechanism.ServiceEntity;

import java.util.Map;

/**
 * 机构，包含诊所，美容院
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 10:36:31
 */
public interface AdminMechanismService extends IService<AdminMechanismEntity> {

	PageUtils queryPage(Map<String, Object> params);

	//商户列表信息
	PageUtils<AdminMechanismEntity> getMechanismList(Integer mechanismType,Integer mechanismStatus,String mechanismName,Integer page,Integer size);

	//店内医师列表信息
	PageUtils<DoctorEntity> doctorList(Integer page,Integer size,Long mechanismId);
	
	//修改医师信息
	int updateDoctor(DoctorEntity doctor);
	
	//店内服务列表
	PageUtils<ServiceEntity> serviceList(Integer page, Integer size, Long mechanismId);
	
	//修改服务信息
	int updateService(ServiceEntity service);
	
	//锁定商户
	int lockById(Long mechanismId);

	//删除商户
	int deleteMechanismById(Long mechanismId);
	
	//商户信息
	Map<String,Object> selectMechanismById(Long mechanismId);
	
	//修改商户信息
	int updateMechanism(MechanBasicEntity basic, DetailEntity detail);
	
	//删除轮播图
	int deleteImage(String imageUrl);
	
	
}

