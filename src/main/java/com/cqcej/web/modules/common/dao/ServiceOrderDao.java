package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 服务订单
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-04 11:08:08
 */
@Mapper
public interface ServiceOrderDao extends BaseMapper<ServiceOrderEntity> {
	
}
