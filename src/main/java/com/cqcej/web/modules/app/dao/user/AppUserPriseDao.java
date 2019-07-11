package com.cqcej.web.modules.app.dao.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserPriseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 社区点赞记录，包括帖子和评论
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-30 19:26:37
 */
@Mapper
public interface AppUserPriseDao extends BaseMapper<UserPriseEntity> {
	
}
