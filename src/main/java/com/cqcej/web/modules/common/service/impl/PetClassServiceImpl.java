package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.PetClassDao;
import com.cqcej.web.modules.common.entity.PetClassEntity;
import com.cqcej.web.modules.common.service.PetClassService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("petClassService")
public class PetClassServiceImpl extends ServiceImpl<PetClassDao, PetClassEntity> implements PetClassService {
	
	@Override
	public List<PetClassEntity> getCategory(Integer parentId) {
		EntityWrapper<PetClassEntity> wrapper = new EntityWrapper<>();
		if (parentId != null && parentId != 0) {
			wrapper.where("parent_id={0} or parent_id={1}", parentId, 65535);
			wrapper.orderBy("header_word", true);
		} else {
			wrapper.where("parent_id=0");
			wrapper.orderBy("sort", false);
		}
		wrapper.last("limit 0,1000");
		
		return selectList(wrapper);
	}
	
	@Override
	public String getPetClassName(int id) {
		PetClassEntity entity = selectById(id);
		return entity.getClassName();
	}
}
