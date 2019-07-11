package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminPaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 支付方式
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-28 13:50:38
 */
@Mapper
public interface AdminPaymentDao extends BaseMapper<AdminPaymentEntity> {
	
	List<AdminPaymentEntity> getList(@Param("start")Integer start,@Param("size")Integer size);
	
	Integer getListCount();
	
	int forbidden(@Param("paymentId")Integer paymentId,@Param("paymentStatus") Integer paymentStatus);
	
	AdminPaymentEntity info(@Param("paymentId") Integer paymentId);
	
	int updatePay(AdminPaymentEntity p);
	
	int deletePay(@Param("payId") Integer payId);
}
