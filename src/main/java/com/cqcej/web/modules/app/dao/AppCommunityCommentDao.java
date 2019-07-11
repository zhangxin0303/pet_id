package com.cqcej.web.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppCommunityCommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社区评论
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@Mapper
public interface AppCommunityCommentDao extends BaseMapper<AppCommunityCommentEntity> {
	
	int getCommentCount(@Param("communityId") Long communityId);
	
	List<AppCommunityCommentEntity> getCommentList(@Param("communityId") Long communityId, @Param("start") Integer start, @Param("size") Integer size);
}
