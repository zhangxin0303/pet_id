package com.cqcej.web.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppFeedbackEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 反馈
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-11-07 16:34:56
 */
@Mapper
public interface AppFeedbackDao extends BaseMapper<AppFeedbackEntity> {
	
}
