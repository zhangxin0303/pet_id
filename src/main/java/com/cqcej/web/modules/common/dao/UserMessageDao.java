package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserMessageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息，用户中间表
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-01 14:36:37
 */
@Mapper
public interface UserMessageDao extends BaseMapper<UserMessageEntity> {
	
}
