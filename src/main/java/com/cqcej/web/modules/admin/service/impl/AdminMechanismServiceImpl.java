package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminMechanismDao;
import com.cqcej.web.modules.admin.entity.AdminBankCardEntity;
import com.cqcej.web.modules.admin.entity.AdminMechanismEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DetailEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DoctorEntity;
import com.cqcej.web.modules.admin.entity.mechanism.MechanBasicEntity;
import com.cqcej.web.modules.admin.entity.mechanism.ServiceEntity;
import com.cqcej.web.modules.admin.service.AdminMechanismService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("adminMechanismService")
public class AdminMechanismServiceImpl extends ServiceImpl<AdminMechanismDao, AdminMechanismEntity> implements AdminMechanismService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AdminMechanismEntity> page = this.selectPage(
				new Query<AdminMechanismEntity>(params).getPage(),
				new EntityWrapper<AdminMechanismEntity>()
		);

		return new PageUtils(page);
	}

	//商户列表
	@Override
	public PageUtils<AdminMechanismEntity> getMechanismList(Integer mechanismType, Integer mechanismStatus, String mechanismName,Integer page, Integer size) {
		int start = (page - 1) * size;
		List<AdminMechanismEntity> list = baseMapper.getMechanismList(mechanismType,mechanismStatus,mechanismName,start,size);
		int count = baseMapper.getMechanismCount(mechanismType,mechanismStatus,mechanismName);
		PageUtils<AdminMechanismEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	
	//店内医师列表
	@Override
	public PageUtils<DoctorEntity> doctorList(Integer page, Integer size, Long mechanismId) {
		int start = (page - 1) * size;
		List<DoctorEntity> list = baseMapper.doctorList(start,size,mechanismId);
		int count = baseMapper.doctorListCount(mechanismId);
		PageUtils<DoctorEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	
	//修改医师信息
	@Override
	public int updateDoctor(DoctorEntity doctor) {
		return baseMapper.updateDoctor(doctor);
	}
	
	//店内服务列表
	@Override
	public PageUtils<ServiceEntity> serviceList(Integer page, Integer size, Long mechanismId) {
		int start = (page - 1) * size;
		List<ServiceEntity> list = baseMapper.serviceList(start,size,mechanismId);
		int count = baseMapper.serviceListCount(mechanismId);
		PageUtils<ServiceEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	
	@Override
	public int updateService(ServiceEntity service) {
		return baseMapper.updateService(service);
	}
	
	//锁定商户
	@Override
	public int lockById(Long mechanismId) {
		return baseMapper.lockById(mechanismId);
	}

	//删除商户
	@Override
	public int deleteMechanismById(Long mechanismId) {
		return baseMapper.deleteMechanismById(mechanismId);
	}
	//商户信息
	@Override
	public Map<String, Object> selectMechanismById(Long mechanismId) {
		Map<String, Object> map = new HashMap<>();
		MechanBasicEntity basic = baseMapper.selectBasicMessage(mechanismId);//基本信息
		map.put("basic",basic);
		List<AdminBankCardEntity> cards =  baseMapper.selectBankCardMsg(mechanismId);//银行卡信息(详情)
		map.put("cards",cards);
		DetailEntity detail = baseMapper.selectDetailMessage(mechanismId);//详情
		map.put("detail",detail);
		List<String> images = baseMapper.selectImages(mechanismId);//轮播图(详情)
		map.put("images",images);
//		detail.setServiceTypes(baseMapper.selectServiceTypes(detail.getMechanismId()));//服务范围


//		List<DoctorEntity> doctors =  baseMapper.selectDoctorMessage(mechanismId);//医师信息
//		map.put("doctors",doctors);
//		List<ServiceEntity> service = baseMapper.selectServiceMessage(mechanismId);//店内服务
//		map.put("service",service);
		return map;
	}
	
	//编辑商户
	@Override
	public int updateMechanism(MechanBasicEntity basic, DetailEntity detail) {
		Map<String,Object> param = new HashMap<>();
		param.put("b",basic);
		param.put("d",detail);
		int result = baseMapper.updateBasic(param);//修改基本信息,修改详细信息
		if(detail.getImages() != null && detail.getImages().size()>0){
			baseMapper.updateImages(detail.getImages(),basic.getMechanismId());//上传轮播图
		}
		return result;
	}
	
	@Override
	public int deleteImage(String imageUrl) {
		return baseMapper.deleteImage(imageUrl);
	}
}
