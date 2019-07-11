package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.AppUserFootprintEntity;

import java.util.List;

/**
 * 
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-31 15:59:32
 */
public interface AppUserFootprintService extends IService<AppUserFootprintEntity> {
	
	List<AppUserFootprintEntity> getUserFootprint(long userId);
	
	boolean clearUserFootprint(long userId);
}

