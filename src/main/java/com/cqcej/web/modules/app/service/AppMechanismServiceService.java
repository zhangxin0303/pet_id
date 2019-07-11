package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.home.AppMechanismCommentEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismServiceEntity;
import com.cqcej.web.modules.app.form.MechanismServiceForm;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.MechanismServiceEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 机构服务中间表
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
public interface AppMechanismServiceService extends IService<MechanismServiceEntity> {
	
	LinkedHashMap<String, ArrayList<AppMechanismServiceEntity>> getClinicServices(long clinicId);
	
	List<MechanismServiceEntity> getMechanismServiceByType(long mechanismId, int type);
	
	List<AppMechanismServiceEntity> servieList(Long mechId, Integer serviceType);
	
	int addService(MechanismServiceForm form);
	
	int updateService(MechanismServiceForm form);
	
	int deleteImages(String images);
	
	Boolean deleteByServiceId(List<Long> serviceId);
	
	AppPage<AppMechanismCommentEntity> commentList(Long mechId, Integer page, Integer size);
	
	Boolean addReply(Long mechId, String content);
	
}

