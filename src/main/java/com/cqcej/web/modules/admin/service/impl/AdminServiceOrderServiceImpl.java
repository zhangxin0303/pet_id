package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminServiceOrderDao;
import com.cqcej.web.modules.admin.dao.AdminSettingsDao;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderEntity;
import com.cqcej.web.modules.admin.entity.AdminSettingsEntity;
import com.cqcej.web.modules.admin.entity.order.*;
import com.cqcej.web.modules.admin.service.AdminServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("adminServiceOrderService")
public class AdminServiceOrderServiceImpl extends ServiceImpl<AdminServiceOrderDao, AdminServiceOrderEntity> implements AdminServiceOrderService {

	@Autowired
	private AdminSettingsDao settingsDao;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AdminServiceOrderEntity> page = this.selectPage(
				new Query<AdminServiceOrderEntity>(params).getPage(),
				new EntityWrapper<AdminServiceOrderEntity>()
		);
		return new PageUtils(page);
	}
	
	//订单列表
	@Override
	public PageUtils<AdminServiceOrderEntity> getServiceOrderList(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());
		int start = (page - 1) * size;
		params.put("start",start);
		params.put("size",size);
		List<AdminServiceOrderEntity> list = baseMapper.getServiceOrderList(params);
		int count = baseMapper.getServiceOrderCount(params);
		PageUtils<AdminServiceOrderEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}

	//订单详情
	@Override
	public Map<String,Object> selectOrderById(Long orderId) {
		Map<String,Object> map  = new HashMap<>();
		//获取订单信息
		AdminServiceOrderEntity orderEntity = baseMapper.selectOrderById(orderId);
		//map.put("orderEntity",orderEntity);
		//根据订单类型,查询对应订单信息
		Integer type = orderEntity.getOrderType();//订单类型(1诊所，2美容，3健康)
		Integer subType = orderEntity.getOrderSubtype();//订单子类型(11上门就诊，12线上预约，13聊天咨询，14电话咨询，31遛狗服务，32宠物寄养)

		WorkmanEntity workmanEntity;
		BuyerEntity buyerEntity;
		MechanEntity mechanismEntity;
		PetEntity petEntity;
		PickUpEntity pickUpEntity;

		BasicEntity basicEntity = baseMapper.selectBasicMessage(orderId);//基本信息(所有类型通用)
		basicEntity.setOrderType(type);//暂时放着,看是否需要根据类型来定义内容
		basicEntity.setOrderSubtype(subType);//暂时放着,看是否需要根据类型来定义内容
		map.put("basic",basicEntity);
		switch(subType){
			case 11://11上门就诊
				workmanEntity = baseMapper.selectDoctorMessage(orderId);//医师信息
				map.put("doctor", workmanEntity);
				buyerEntity = baseMapper.selectBuyerMessage(orderId);//买家信息
				map.put("buyer",buyerEntity);
				List<IllnessEntity> illnessEntity = baseMapper.selectIllnessMessage(orderId);//病情信息
				map.put("illness",illnessEntity);
				petEntity = baseMapper.selectPetMessage(orderId);//宠物信息
				map.put("pet",petEntity);
				break;
			case 12://12线上预约(没有对应类型，暂时放着)
				
				break;
			case 13://13聊天咨询
				workmanEntity = baseMapper.selectDoctorMessage(orderId);//医师信息
				String s[] = {"validTime"};
				List<AdminSettingsEntity> settting = settingsDao.findAll(s);
				AdminSettingsEntity set = settting.get(0);//获取到医师聊天咨询有效时间（小时）
				//支付时间+有效时间=过期时间
				Calendar ca=Calendar.getInstance();
				ca.setTime(basicEntity.getPayAt());//支付时间
				ca.add(Calendar.HOUR_OF_DAY, Integer.valueOf(set.getSettingValue()));//+有效时间
				basicEntity.setValidTime(ca.getTime());//过期时间
				map.put("doctor", workmanEntity);
				break;
			case 14://14电话咨询
				workmanEntity = baseMapper.selectDoctorMessage(orderId);//医师信息
				map.put("doctor", workmanEntity);
				break;
			case 20://接送美容
				workmanEntity = baseMapper.selectDoctorMessage(orderId);//医师信息
				map.put("doctor", workmanEntity);
				buyerEntity = baseMapper.selectBuyerMessage(orderId);//买家信息
				map.put("buyer",buyerEntity);
				mechanismEntity = baseMapper.selectMechanismMessage(orderId);//商家信息
				map.put("mechanism",mechanismEntity);
				pickUpEntity = baseMapper.selectPickUpMessage(orderId);//接送人员信息
				map.put("pickup",pickUpEntity);
				pickUpEntity = baseMapper.selectGiveBackMessage(orderId);//归还人员信息
				map.put("giveback",pickUpEntity);
				petEntity = baseMapper.selectPetMessage(orderId);//宠物信息
				map.put("pet",petEntity);
				break;
			case 31://31遛狗服务
				buyerEntity = baseMapper.selectBuyerMessage(orderId);//买家信息
				map.put("buyer",buyerEntity);
				workmanEntity = baseMapper.selectWalkDogMessage(orderId);//遛狗人员信息
				map.put("worker", workmanEntity);
				petEntity = baseMapper.selectPetMessage(orderId);//宠物信息
				map.put("pet",petEntity);
				break;
			case 32://32宠物寄养
				buyerEntity = baseMapper.selectBuyerMessage(orderId);//买家信息
				map.put("buyer",buyerEntity);
				mechanismEntity = baseMapper.selectMechanismMessage(orderId);//商家信息
				map.put("mechanism",mechanismEntity);
				pickUpEntity = baseMapper.selectPickUpMessage(orderId);//接送人员信息
				map.put("pickup",pickUpEntity);
				pickUpEntity = baseMapper.selectGiveBackMessage(orderId);//归还人员信息
				map.put("giveback",pickUpEntity);
				petEntity = baseMapper.selectPetMessage(orderId);//宠物信息
				map.put("pet",petEntity);
				break;
		}
		return map;
	}

	//订单统计
	@Override
	public PageUtils<OrderStatisticsEntity> statistics(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());
		int start = (page - 1) * size;
		params.put("start",start);
		params.put("size",size);
		List<OrderStatisticsEntity> list = baseMapper.statistics(params);
		int count = baseMapper.getStatisticsCount(params);
		//查看平台计价方式
		String sets[] = {"priceType","priceFormerPersent","priceSettled","priceRealPersent"};
		List<AdminSettingsEntity> settings = settingsDao.findAll(sets);
		String priceType = "";
		String priceRealPersent = "";
		String priceFormerPersent = "";
		String priceSettled = "";
		for (AdminSettingsEntity setting: settings) {
			if(setting.getSettingKey().equals("priceType")){//计价方式
				priceType = setting.getSettingValue();
			}
			if(setting.getSettingKey().equals("priceRealPersent")){//实价百分比分成
				priceRealPersent = setting.getSettingValue();
			}
			if(setting.getSettingKey().equals("priceFormerPersent")){//原价百分比分成
				priceFormerPersent = setting.getSettingValue();
			}
			if(setting.getSettingKey().equals("priceSettled")){//订单固定金额
				priceSettled = setting.getSettingValue();
			}
			
		}
		if(priceType.equals("1")){//实价百分比分成
			for (OrderStatisticsEntity s: list) {
				Integer integer = Integer.valueOf(priceRealPersent);
				s.setPlatformMoney(s.getPaySum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			}
		}
		if(priceType.equals("2")){//原价百分比分成
			for (OrderStatisticsEntity s: list) {
				Integer integer = Integer.valueOf(priceFormerPersent);
				s.setPlatformMoney(s.getFormerSum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			}
		}
		if(priceType.equals("3")){//订单固定金额
			for (OrderStatisticsEntity s: list) {
				Integer integer = Integer.valueOf(priceSettled);
				s.setPlatformMoney(BigDecimal.valueOf(s.getPayCount()*integer).setScale(2, RoundingMode.HALF_UP));
			}
		}
		
		PageUtils<OrderStatisticsEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	//平台分成
	@Override
	public PlatformStats platformStats() {
		PlatformStats p = new PlatformStats();
		OrderStatisticsEntity platformStats = baseMapper.platformStats();
		
		OrderStatisticsEntity medicalStats = baseMapper.medicalStats();
		
		OrderStatisticsEntity beautyStats = baseMapper.beautyStats();
		
		OrderStatisticsEntity healthStats = baseMapper.healthStats();
		
		//平台计价(读取配置表)
		String sets[] = {"priceType","priceFormerPersent","priceSettled","priceRealPersent"};
		List<AdminSettingsEntity> settings = settingsDao.findAll(sets);
		String priceType = "";
		String priceRealPersent = "";
		String priceFormerPersent = "";
		String priceSettled = "";
		for (AdminSettingsEntity setting: settings) {
			if(setting.getSettingKey().equals("priceType")){//计价方式
				priceType = setting.getSettingValue();
			}
			if(setting.getSettingKey().equals("priceRealPersent")){//实价百分比分成
				priceRealPersent = setting.getSettingValue();
			}
			if(setting.getSettingKey().equals("priceFormerPersent")){//原价百分比分成
				priceFormerPersent = setting.getSettingValue();
			}
			if(setting.getSettingKey().equals("priceSettled")){//订单固定金额
				priceSettled = setting.getSettingValue();
			}
		}
		if(priceType.equals("1")){//实价百分比分成
			Integer integer = Integer.valueOf(priceRealPersent);
			platformStats.setPlatformMoney(platformStats.getPaySum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			medicalStats.setPlatformMoney(medicalStats.getPaySum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			beautyStats.setPlatformMoney(beautyStats.getPaySum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			healthStats.setPlatformMoney(healthStats.getPaySum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
		}
		if(priceType.equals("2")){//原价百分比分成
			Integer integer = Integer.valueOf(priceFormerPersent);
			platformStats.setPlatformMoney(platformStats.getFormerSum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			medicalStats.setPlatformMoney(medicalStats.getFormerSum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			beautyStats.setPlatformMoney(beautyStats.getFormerSum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
			healthStats.setPlatformMoney(healthStats.getFormerSum().multiply(BigDecimal.valueOf(integer)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP));
		}
		if(priceType.equals("3")){//订单固定金额
			Integer integer = Integer.valueOf(priceSettled);
			platformStats.setPlatformMoney(BigDecimal.valueOf(platformStats.getPayCount()*integer).setScale(2, RoundingMode.HALF_UP));
			medicalStats.setPlatformMoney(BigDecimal.valueOf(medicalStats.getPayCount()*integer).setScale(2, RoundingMode.HALF_UP));
			beautyStats.setPlatformMoney(BigDecimal.valueOf(beautyStats.getPayCount()*integer).setScale(2, RoundingMode.HALF_UP));
			healthStats.setPlatformMoney(BigDecimal.valueOf(healthStats.getPayCount()*integer).setScale(2, RoundingMode.HALF_UP));
		}
		
		p.setPlatFormStats(platformStats.getPlatformMoney());
		p.setMedicalStats(medicalStats.getPlatformMoney());
		p.setBeautyStats(beautyStats.getPlatformMoney());
		p.setHealthStats(healthStats.getPlatformMoney());
		return p;
	}
}
