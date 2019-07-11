package com.cqcej.web.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppCommunityEntity;
import com.cqcej.web.modules.common.entity.CommunityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社区
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@Mapper
public interface AppCommunityDao extends BaseMapper<AppCommunityEntity> {
	
	int getCommunityCount(@Param("classId") int classId,
	                      @Param("petClassId") int petClassId,
	                      @Param("provinceId") int provinceId);
	
	List<CommunityEntity> getCommunityList(@Param("classId") int classId,
	                                       @Param("petClassId") int petClassId,
	                                       @Param("provinceId") int provinceId,
	                                       @Param("userId") long userId,
	                                       @Param("start") int start,
	                                       @Param("size") int size);
	
	int getUserCommunityCount(@Param("userId") Long userId);
	
	List<AppCommunityEntity> getUserCommunityList(@Param("userId") Long userId, @Param("targetId") Long targetId, @Param("start") int start, @Param("size") Integer size);
}
