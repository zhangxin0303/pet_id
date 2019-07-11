package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.AppCommunityEntity;
import com.cqcej.web.modules.app.entity.UserFavoriteEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.utils.AppPage;

/**
 * 用户收藏
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-26 10:33:22
 */
public interface AppUserFavoriteService extends IService<UserFavoriteEntity> {
	
	AppPage<UserFavoriteEntity<AppMechanismEntity>> getFavoriteClinic(long userId, int page, int size, double longitude, double latitude);
	
	boolean isFavorite(long userId, long objectId, int type);
	
	AppPage<UserFavoriteEntity<AppCommunityEntity>> getFavoriteCommunity(long userId, int page, int size, double longitude, double latitude);
	
	boolean changeFavorite(long userId, long objectId, int type);
	
	boolean deleteFavorite(long userId, Long favoriteId);
}

