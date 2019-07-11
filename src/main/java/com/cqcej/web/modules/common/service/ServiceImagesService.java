package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.ServiceImagesEntity;

import java.util.Map;

/**
 * 预约上门图片，仅针对上门就诊类型
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
public interface ServiceImagesService extends IService<ServiceImagesEntity> {

	PageUtils queryPage(Map<String, Object> params);
}

