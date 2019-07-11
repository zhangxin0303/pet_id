package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-01 14:36:37
 */
@Mapper
public interface MessageDao extends BaseMapper<MessageEntity> {
	
	List<MessageEntity> getUserMessage(@Param("userId") long userId, @Param("start") Integer start, @Param("size") Integer size);
	
	int getUserMessageCount(long userId);
}
