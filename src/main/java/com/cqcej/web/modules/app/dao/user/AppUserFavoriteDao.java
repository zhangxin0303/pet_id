package com.cqcej.web.modules.app.dao.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppCommunityEntity;
import com.cqcej.web.modules.app.entity.UserFavoriteEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.common.entity.MechanismEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户收藏
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-26 10:33:22
 */
@Mapper
public interface AppUserFavoriteDao extends BaseMapper<UserFavoriteEntity> {
	
	List<UserFavoriteEntity<AppMechanismEntity>> getFavoriteClinic(@Param("userId") long userId, @Param("start") int start,
	                                                               @Param("size") int size);
	
	MechanismEntity selectMechanism(String s);
	
	AppCommunityEntity selectCommunity(String s);
	
	List<UserFavoriteEntity<AppCommunityEntity>> getFavoriteCommunity(@Param("userId") long userId, @Param("start") int start,
	                                                                  @Param("size") int size);
}
