package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.dto.MechMechanismDTO;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.MechanismEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 机构，包含诊所，美容院
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
public interface AppMechanismService extends IService<MechanismEntity> {

    AppPage<AppMechanismEntity> getClinicList(int petClassId, int sortType, int page, int size, double longitude, double latitude);
    
    AppMechanismEntity getClinicDetail(HttpServletRequest request, long clinicId);
    
    List<AppMechanismEntity> getMechanismRecommend(int type);
    
    List<AppMechanismEntity> getNewestBeautyStore();
	
	List<AppMechanismEntity> getRecommendBeautyStore();
	
	void commentUpdate(int star, long mechanismId);
	
	AppMechanismEntity getMechanismDetailWithUserId(Long userId);
	
	AppMechanismEntity check(Long userId,Long mechId);
	
	MechMechanismDTO mechInfo(Long mechId);
	
	int updateMech(MechMechanismDTO m);
	
}

