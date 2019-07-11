package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserSignEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户签到记录
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-11-23 11:15:11
 */
@Mapper
public interface UserSignDao extends BaseMapper<UserSignEntity> {
	
}
