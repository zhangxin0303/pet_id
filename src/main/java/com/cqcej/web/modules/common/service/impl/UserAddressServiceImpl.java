package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.UserAddressDao;
import com.cqcej.web.modules.common.entity.UserAddressEntity;
import com.cqcej.web.modules.common.service.UserAddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userAddressService")
 public class UserAddressServiceImpl extends ServiceImpl<UserAddressDao, UserAddressEntity> implements UserAddressService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserAddressEntity> page = this.selectPage(
				new Query<UserAddressEntity>(params).getPage(),
				new EntityWrapper<>()
		);

		return new PageUtils(page);
	}
	
	@Override
	public List<UserAddressEntity> getUserAddress(long userId) {
		EntityWrapper<UserAddressEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		return baseMapper.selectList(where);
	}
	
	@Override
	public boolean changeUserAddressDefault(long userId, Long addressId, boolean isDefault) {
		if (isDefault) {
			// 不管有没有默认的地址，都取消一遍
			UserAddressEntity update = new UserAddressEntity();
			update.setIsDefault(0);
			
			EntityWrapper<UserAddressEntity> where = new EntityWrapper<>();
			where.where("user_id={0}", userId);
			baseMapper.update(update, where);
			
			update.setIsDefault(1);
			update.setAddressId(addressId);
			return baseMapper.updateById(update) > 0;
		} else {
			UserAddressEntity update = new UserAddressEntity();
			update.setIsDefault(0);
			update.setAddressId(addressId);
			return baseMapper.updateById(update) > 0;
		}
	}
	
	@Override
	public boolean deleteUserAddressDefault(long userId, Long addressId) {
		EntityWrapper<UserAddressEntity> where = new EntityWrapper<>();
		where.where("user_id={0} and address_id={1}", userId, addressId);
		return baseMapper.delete(where) > 0;
	}
	
	@Override
	public boolean editUserAddress(long userId, UserAddressEntity addressEntity, Long addressId) {
		if (addressId == 0) {
			// 添加
			addressEntity.setUserId(userId);
			
			return baseMapper.insert(addressEntity) > 0;
		} else {
			// 编辑
			EntityWrapper<UserAddressEntity> where = new EntityWrapper<>();
			where.where("address_id={0} and user_id={1}", addressId, userId);
			return baseMapper.update(addressEntity, where) > 0;
		}
	}
}
