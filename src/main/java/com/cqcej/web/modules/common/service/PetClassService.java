package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.PetClassEntity;

import java.util.List;

/**
 * 宠物分类
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-07 10:07:20
 */
public interface PetClassService extends IService<PetClassEntity> {

	List<PetClassEntity> getCategory(Integer parentId);
	
	String getPetClassName(int id);
}

