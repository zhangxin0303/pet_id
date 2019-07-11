package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.user.AppUserFavoriteDao;
import com.cqcej.web.modules.app.entity.AppCommunityEntity;
import com.cqcej.web.modules.app.entity.UserFavoriteEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.service.AppMechanismService;
import com.cqcej.web.modules.app.service.AppUserFavoriteService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.CommunityEntity;
import com.cqcej.web.modules.common.entity.MechanismEntity;
import com.cqcej.web.modules.common.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("appUserFavoriteService")
public class AppUserFavoriteServiceImpl extends ServiceImpl<AppUserFavoriteDao, UserFavoriteEntity> implements AppUserFavoriteService {
	
	@Autowired
	private AppMechanismService appMechanismService;
	
	@Autowired
	private CommunityService communityService;
	
	/**
	 * 收藏的诊所
	 */
	@Override
	public AppPage<UserFavoriteEntity<AppMechanismEntity>> getFavoriteClinic(long userId, int page, int size, double longitude, double latitude) {
		EntityWrapper<UserFavoriteEntity> wrapper = new EntityWrapper<>();
		wrapper.where("favorite_type={0} and user_id={1}", UserFavoriteEntity.FAVORITE_TYPE_CLINIC, userId);
		int count = selectCount(wrapper);
		
		int start = (page - 1) * size;
		List<UserFavoriteEntity<AppMechanismEntity>> list = baseMapper.getFavoriteClinic(userId, start, size);
		return new AppPage<>(count, size, list);
	}
	
	/**
	 * 是否收藏
	 */
	@Override
	public boolean isFavorite(long userId, long objectId, int type) {
		EntityWrapper<UserFavoriteEntity> wrapper = new EntityWrapper<>();
		wrapper.where("user_id={0} and object_id={1} and favorite_type={2}", userId, objectId, type);
		return selectCount(wrapper) > 0;
	}
	
	/**
	 * 收藏的社区帖子
	 */
	@Override
	public AppPage<UserFavoriteEntity<AppCommunityEntity>> getFavoriteCommunity(long userId, int page, int size, double longitude, double latitude) {
		EntityWrapper<UserFavoriteEntity> wrapper = new EntityWrapper<>();
		wrapper.where("favorite_type={0} and user_id={1}", UserFavoriteEntity.FAVORITE_TYPE_COMMUNITY, userId);
		int count = selectCount(wrapper);
		
		int start = (page - 1) * size;
		List<UserFavoriteEntity<AppCommunityEntity>> list = baseMapper.getFavoriteCommunity(userId, start, size);
		return new AppPage<>(count, size, list);
	}
	
	/**
	 * 修改收藏状态，如果已收藏，删除收藏，如果没收藏，则添加收藏
	 */
	@Override
	public boolean changeFavorite(long userId, long objectId, int type) {
		
		boolean isFavorite = isFavorite(userId, objectId, type);
		if (isFavorite) {
			// 删除收藏记录
			EntityWrapper<UserFavoriteEntity> wrapper = new EntityWrapper<>();
			wrapper.where("user_id={0} and object_id={1} and favorite_type={2}", userId, objectId, type);
			delete(wrapper);
			
			// 减少收藏次数
			if (type == UserFavoriteEntity.FAVORITE_TYPE_CLINIC) {
				MechanismEntity mechanism = appMechanismService.selectById(objectId);
				MechanismEntity update = new MechanismEntity();
				update.setMechanismId(objectId);
				update.setFavoriteCount(mechanism.getFavoriteCount() - 1);
				appMechanismService.updateById(update);
			} else if (type == UserFavoriteEntity.FAVORITE_TYPE_COMMUNITY) {
				CommunityEntity community = communityService.selectById(objectId);
				CommunityEntity update = new CommunityEntity();
				update.setCommunityId(objectId);
				update.setFavoriteCount(community.getFavoriteCount() - 1);
				communityService.updateById(update);
			}
		} else {
			// 添加收藏记录
			UserFavoriteEntity entity = new UserFavoriteEntity();
			entity.setUserId(userId);
			entity.setObjectId(objectId);
			entity.setFavoriteType(type);
			entity.setCreateAt(new Date());
			insert(entity);
			
			// 增加收藏次数
			if (type == UserFavoriteEntity.FAVORITE_TYPE_CLINIC) {
				MechanismEntity mechanism = appMechanismService.selectById(objectId);
				MechanismEntity update = new MechanismEntity();
				update.setMechanismId(objectId);
				update.setFavoriteCount(mechanism.getFavoriteCount() + 1);
				appMechanismService.updateById(update);
			} else if (type == UserFavoriteEntity.FAVORITE_TYPE_COMMUNITY) {
				CommunityEntity community = communityService.selectById(objectId);
				CommunityEntity update = new CommunityEntity();
				update.setCommunityId(objectId);
				update.setFavoriteCount(community.getFavoriteCount() + 1);
				communityService.updateById(update);
			}
		}
		
		return !isFavorite;
	}
	
	@Override
	public boolean deleteFavorite(long userId, Long favoriteId) {
		EntityWrapper<UserFavoriteEntity> wrapper = new EntityWrapper<>();
		wrapper.where("user_id={0} and favorite_id={1}", userId, favoriteId);
		return baseMapper.delete(wrapper) > 0;
	}
}
