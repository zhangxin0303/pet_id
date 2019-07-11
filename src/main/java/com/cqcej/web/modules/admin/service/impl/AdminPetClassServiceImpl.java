package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PinYinUtil;
import com.cqcej.web.modules.admin.dao.AdminPetClassDao;
import com.cqcej.web.modules.admin.entity.AdminPetClassEntity;
import com.cqcej.web.modules.admin.service.AdminPetClassService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("adminPetClassService")
public class AdminPetClassServiceImpl extends ServiceImpl<AdminPetClassDao, AdminPetClassEntity> implements AdminPetClassService {
	
	@Override
	public List<AdminPetClassEntity> list() {
		List<AdminPetClassEntity> data = baseMapper.list();
		//查询种类下的所有宠物信息
		for (int i = 0; i < data.size(); i++) {
			List<AdminPetClassEntity> pets = baseMapper.getPets(data.get(i).getPetClassId());
			data.get(i).setPList(pets);
		}
		return data;
	}
	
	@Override
	public List<AdminPetClassEntity> simpleList() {
		List<AdminPetClassEntity> data = baseMapper.list();
		return data;
	}
	
	@Override
	public AdminPetClassEntity info(Integer petClassId) {
		return baseMapper.info(petClassId);
	}
	
	@Override
	public int save(AdminPetClassEntity petClass) {
		return baseMapper.save(petClass.getClassName());
	}
	
	@Override
	public int savePet(AdminPetClassEntity petClass) {
		String cName = petClass.getClassName().trim().charAt(0) + "";//获取名称的第一个字
		String firstLetter = PinYinUtil.getFirstSpell(cName);//获取首字母
		return baseMapper.savePet(petClass.getParentId(), firstLetter, petClass.getClassName());
	}
	
	@Override
	public int updatePet(AdminPetClassEntity petClass) {
		AdminPetClassEntity p = baseMapper.info(petClass.getPetClassId());
		String firstLetter = null;
		if (p.getParentId() != 0) {//分类没有首字母。宠物名字才有
			String cName = petClass.getClassName().trim().charAt(0) + "";//获取名称的第一个字
			firstLetter = PinYinUtil.getFirstSpell(cName);//获取首字母
		}
		return baseMapper.updatePet(petClass.getPetClassId(), firstLetter, petClass.getClassName());
	}
	
	@Override
	public int deletePet(Integer petClassId) {
		//删除宠物品类以及对应的宠物信息
		return baseMapper.deletePet(petClassId);
	}
}
