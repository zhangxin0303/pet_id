package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserPetsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户宠物
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-21 15:17:36
 */
@Mapper
public interface UserPetsDao extends BaseMapper<UserPetsEntity> {
	List<UserPetsEntity> getUserPets(long userId);
}
