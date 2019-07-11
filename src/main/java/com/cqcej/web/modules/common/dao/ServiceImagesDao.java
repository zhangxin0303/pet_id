package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.ServiceImagesEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预约上门图片，仅针对上门就诊类型
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
@Mapper
public interface ServiceImagesDao extends BaseMapper<ServiceImagesEntity> {
	
}
