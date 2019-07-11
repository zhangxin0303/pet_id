package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.UserAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户收货地址
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-28 17:22:58
 */
public interface UserAddressService extends IService<UserAddressEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	List<UserAddressEntity> getUserAddress(long userId);
	
	boolean changeUserAddressDefault(long userId, Long addressId, boolean isDefault);
	
	boolean deleteUserAddressDefault(long userId, Long addressId);
	
	boolean editUserAddress(long userId, UserAddressEntity addressEntity, Long addressId);
}

