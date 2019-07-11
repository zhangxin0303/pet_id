package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminShopOrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商城订单
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 14:32:40
 */
@Mapper
public interface AdminShopOrderDao extends BaseMapper<AdminShopOrderEntity> {
	
}
