package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.UserPetsDao;
import com.cqcej.web.modules.common.entity.UserPetsEntity;
import com.cqcej.web.modules.common.service.UserPetsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userPetsService")
public class UserPetsServiceImpl extends ServiceImpl<UserPetsDao, UserPetsEntity> implements UserPetsService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserPetsEntity> page = this.selectPage(
				new Query<UserPetsEntity>(params).getPage(),
				new EntityWrapper<>()
		);

		return new PageUtils(page);
	}
	
	/**
	 * TODO 删除宠物头像
	 */
	@Override
	public boolean deletePetById(long userId, Long petId) {
		UserPetsEntity userPetsEntity = baseMapper.selectById(petId);
		if (userPetsEntity.getUserId() != userId) {
			return false;
		}
		return baseMapper.deleteById(petId) > 0;
	}
	
	@Override
	public Long editPet(UserPetsEntity pet, Long petId) {
		if (petId == 0) {
			// 添加
			return Long.parseLong(baseMapper.insert(pet).toString());
		} else {
			// 修改
			EntityWrapper<UserPetsEntity> where = new EntityWrapper<>();
			where.where("pet_id={0}", petId);
			return baseMapper.update(pet, where) > 0 ? petId : 0L;
		}
	}
	
}
