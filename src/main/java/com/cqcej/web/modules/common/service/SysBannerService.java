package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.SysBannerEntity;

import java.util.List;
import java.util.Map;

/**
 * 轮播图
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-02 17:12:30
 */
public interface SysBannerService extends IService<SysBannerEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	List<SysBannerEntity> getBannerByPosition(int position);
}

