package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserAddressEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户收货地址
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-28 17:22:58
 */
@Mapper
public interface UserAddressDao extends BaseMapper<UserAddressEntity> {
	
}
