package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.UserPetsEntity;

import java.util.Map;

/**
 * 用户宠物
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-21 15:17:36
 */
public interface UserPetsService extends IService<UserPetsEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	boolean deletePetById(long userId, Long petId);
	
	Long editPet(UserPetsEntity pet, Long petId);
}

