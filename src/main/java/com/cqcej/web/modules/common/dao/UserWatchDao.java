package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserWatchEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关注
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-22 10:47:54
 */
@Mapper
public interface UserWatchDao extends BaseMapper<UserWatchEntity> {
	
}
