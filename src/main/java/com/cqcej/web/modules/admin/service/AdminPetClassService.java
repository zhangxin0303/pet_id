package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.admin.entity.AdminPetClassEntity;

import java.util.List;

/**
 * 宠物分类
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-05 09:45:00
 */
public interface AdminPetClassService extends IService<AdminPetClassEntity> {
	
	
	List<AdminPetClassEntity> list();
	
	List<AdminPetClassEntity> simpleList();
	
	AdminPetClassEntity info(Integer petClassId);
	
	int save(AdminPetClassEntity petClass);
	
	int savePet(AdminPetClassEntity petClass);
	
	int updatePet(AdminPetClassEntity petClass);
	
	int deletePet(Integer petClassId);
}

