package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminWithdrawEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 提现申请
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-17 13:58:16
 */
@Mapper
public interface AdminWithdrawDao extends BaseMapper<AdminWithdrawEntity> {
	
	List<AdminWithdrawEntity> list(Map<String, Object> params);
	
	int listCount(Map<String, Object> params);
	
	BigDecimal getUserBalance(Long userId);
	
	int updateUserBalance(@Param("userId") Long userId,@Param("newBalance") BigDecimal newBalance);
	
	int updateWithdraw(@Param("withdrawId") Long withdrawId, @Param("status") Integer status);
	
	AdminWithdrawEntity selectAmountMessage(@Param("withdrawId") Long withdrawId);
	
	List<AdminWithdrawEntity> mechanlist(Map<String, Object> params);
	
	int mechanlistCount(Map<String, Object> params);
}
