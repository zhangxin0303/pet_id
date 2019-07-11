package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付方式
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-08 10:47:09
 */
@Mapper
public interface PaymentDao extends BaseMapper<PaymentEntity> {
	
}
