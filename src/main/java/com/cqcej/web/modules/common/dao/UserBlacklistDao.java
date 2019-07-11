package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserBlacklistEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 黑名单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-24 11:55:55
 */
@Mapper
public interface UserBlacklistDao extends BaseMapper<UserBlacklistEntity> {
	
}
