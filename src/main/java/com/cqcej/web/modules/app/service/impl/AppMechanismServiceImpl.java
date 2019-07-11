package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.home.AppMechanismDao;
import com.cqcej.web.modules.app.entity.dto.MechMechanismDTO;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppMechanismService;
import com.cqcej.web.modules.app.service.AppUserFavoriteService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.MechanismEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service("appMechanismService")
public class AppMechanismServiceImpl extends ServiceImpl<AppMechanismDao, MechanismEntity> implements AppMechanismService {
	@Autowired
	AppUserFavoriteService appUserFavoriteService;
	
	@Override
	public AppPage<AppMechanismEntity> getClinicList(int petClassId, int sortType, int page, int size, double longitude, double latitude) {
		EntityWrapper<MechanismEntity> wrapper = new EntityWrapper<>();
		wrapper.where("mechanism_type & {0}", MechanismEntity.MECHANISM_TYPE_CLINIC);
		int count = selectCount(wrapper);
		
		int start = (page - 1) * size;
		List<AppMechanismEntity> list = baseMapper.getClinicList(petClassId, sortType, start, size, longitude, latitude);
		return new AppPage<>(count, size, list);
	}
	
	@Override
	public AppMechanismEntity getClinicDetail(HttpServletRequest request, long clinicId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		
		return baseMapper.getClinicDetail(clinicId, userId);
	}
	
	@Override
	public List<AppMechanismEntity> getMechanismRecommend(int type) {
		return baseMapper.getMechanismRecommend(type);
	}
	
	@Override
	public List<AppMechanismEntity> getNewestBeautyStore() {
		return baseMapper.getNewestMechanism(MechanismEntity.MECHANISM_TYPE_BEAUTY);
	}
	
	@Override
	public List<AppMechanismEntity> getRecommendBeautyStore() {
		return baseMapper.getMechanismRecommend(MechanismEntity.MECHANISM_TYPE_BEAUTY);
	}
	
	@Override
	public void commentUpdate(int star, long mechanismId) {
		MechanismEntity mechanismEntity = baseMapper.selectById(mechanismId);
		int average = Math.round((mechanismEntity.getTotalStars() + star) / (mechanismEntity.getCommentCount() + 1));
		
		baseMapper.commentUpdate(star, mechanismId, average);
	}
	
	@Override
	public AppMechanismEntity getMechanismDetailWithUserId(Long userId) {
		return baseMapper.getMechanismDetailWithUserId(userId);
	}
	
	@Override
	public AppMechanismEntity check(Long userId, Long mechId) {
		return baseMapper.check(userId,mechId);
	}
	
	@Override
	public MechMechanismDTO mechInfo(Long mechId) {
		//轮播图信息
		MechMechanismDTO m = baseMapper.mechInfo(mechId);
		List<String> images = baseMapper.images(mechId);
		m.setImages(images);
		return m;
	}
	
	@Override
	public int updateMech(MechMechanismDTO m) {
		baseMapper.deleteImages(m.getMechanismId());//删除原有轮播图
		if (m.getImages() != null && m.getImages().size() > 0) {
			baseMapper.updateImages(m.getImages(), m.getMechanismId());//插入轮播图
		}
		return baseMapper.updateMech(m);
	}
}