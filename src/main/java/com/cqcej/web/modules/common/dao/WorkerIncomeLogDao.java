package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDataEntity;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDetailEntity;
import com.cqcej.web.modules.common.entity.WorkerIncomeLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工作者收入日志
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-11 10:43:35
 */
@Mapper
public interface WorkerIncomeLogDao extends BaseMapper<WorkerIncomeLogEntity> {
	
	ServiceOrderStatisticsDataEntity getServiceOrderStatisticsDate(@Param("userID") long userId,
	                                                               @Param("firstDateTime") String firstDateTime,
	                                                               @Param("lastDateTime") String lastDateTime);
	
	List<ServiceOrderStatisticsDetailEntity> getServiceOrderStatisticsDetail(@Param("userID") long userId,
	                                                                         @Param("firstDateTime") String firstDateTime,
	                                                                         @Param("lastDateTime") String lastDateTime);
}
