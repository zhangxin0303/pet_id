package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.home.AppMechanismServiceDao;
import com.cqcej.web.modules.app.entity.home.AppMechanismCommentEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismServiceEntity;
import com.cqcej.web.modules.app.form.MechanismServiceForm;
import com.cqcej.web.modules.app.service.AppMechanismServiceService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.MechanismServiceEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Service("appMechanismServiceService")
public class AppMechanismServiceServiceImpl extends ServiceImpl<AppMechanismServiceDao, MechanismServiceEntity> implements AppMechanismServiceService {
	
	@Override
	public LinkedHashMap<String, ArrayList<AppMechanismServiceEntity>> getClinicServices(long clinicId) {
		List<AppMechanismServiceEntity> services = baseMapper.getMechanismServices(clinicId);
		
		LinkedHashMap<String, ArrayList<AppMechanismServiceEntity>> map = new LinkedHashMap<>();
		// 拼数据
		for (AppMechanismServiceEntity service : services) {
			String key = service.getPetClassName();
			ArrayList<AppMechanismServiceEntity> list = map.get(key);
			if (list != null) {
				list.add(service);
			} else {
				list = new ArrayList<>();
				list.add(service);
				map.put(key, list);
			}
		}
		
		return map;
	}
	
	@Override
	public List<MechanismServiceEntity> getMechanismServiceByType(long mechanismId, int type) {
		EntityWrapper<MechanismServiceEntity> wrapper = new EntityWrapper<>();
		if (type != 0) {
			wrapper.where("mechanism_id={0} and service_type&{1}", mechanismId, type);
		} else {
			// 获取所有服务
			wrapper.where("mechanism_id={0}", mechanismId);
		}
		return selectList(wrapper);
	}
	
	@Override
	public List<AppMechanismServiceEntity> servieList(Long mechId, Integer serviceType) {
		return baseMapper.selectServiceList(mechId, serviceType);
	}
	
	@Override
	public int addService(MechanismServiceForm form) {
		return baseMapper.addService(form);
	}
	
	@Override
	public int updateService(MechanismServiceForm form) {
		return baseMapper.updateService(form);
	}
	
	@Override
	public int deleteImages(String imagesId) {
		String[] array = imagesId.split(",");
		List<Long> images = new ArrayList<>(array.length);
		for (String s : array) {
			images.add(Long.valueOf(s));
		}
		if (images.size() > 0) {
			return baseMapper.deleteImages(images);
		}
		return 0;
	}
	
	@Override
	public Boolean deleteByServiceId(List<Long> serviceId) {
		if (serviceId == null || serviceId.size() == 0) {
			return false;
		}
		return baseMapper.deleteByServiceId(serviceId) > 0 ? true : false;
	}
	
	@Override
	public AppPage<AppMechanismCommentEntity> commentList(Long mechId, Integer page, Integer size) {
		int start = (page - 1) * size;
		int count = baseMapper.commentListCount(mechId);
		List<AppMechanismCommentEntity> list =	baseMapper.commentList(mechId,start,size);
		return new AppPage(count,size,list);
	}
	
	@Override
	public Boolean addReply(Long commonId, String content) {
		return baseMapper.addReply(commonId,content) > 0 ? true : false;
	}
}















