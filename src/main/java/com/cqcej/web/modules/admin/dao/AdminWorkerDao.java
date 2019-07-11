package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminBankCardEntity;
import com.cqcej.web.modules.admin.entity.AdminPickUpEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 工作者，包含医师，接送者，遛狗人员
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-27 11:23:32
 */
@Mapper
public interface AdminWorkerDao extends BaseMapper<AdminWorkerEntity> {
	
	List<AdminPickUpEntity> selectPickUp(Map<String, Object> map);
	
	Integer selectPickUpCount(Map<String, Object> map);
	
	List<AdminPickUpEntity> selectWorkerLocation(@Param("start") Integer start,
	                                             @Param("size") Integer size,
	                                             @Param("status") Integer status,
	                                             @Param("realname") String realname);
	
	Integer selectWorkerCount(@Param("status") Integer status, @Param("realname") String realname);
	
	AdminUserEntity getPickUp(@Param("workerId") Long workerId);
	
	List<AdminBankCardEntity> detail(@Param("workerId") Long workerId);
	
	int insertUserOfPickUp(AdminUserEntity worker);
	
	int insertWorkerOfPickUp(AdminUserEntity worker);
	
	int updateUserById(AdminUserEntity user);
	
	int updateWorker(@Param("workerId") Long workerId,
	                 @Param("walkDogFee") BigDecimal walkDogFee,
	                 @Param("type") Integer type,
	                 @Param("workerType") Integer workerType
	);
}
