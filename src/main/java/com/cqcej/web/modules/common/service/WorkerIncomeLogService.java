package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDataEntity;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDetailEntity;
import com.cqcej.web.modules.common.entity.WorkerIncomeLogEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 工作者收入日志
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-11 10:43:35
 */
public interface WorkerIncomeLogService extends IService<WorkerIncomeLogEntity> {

	ServiceOrderStatisticsDataEntity getServiceOrderStatisticsDate(long userId, String time);
	
	List<ServiceOrderStatisticsDetailEntity> getServiceOrderStatisticsDetail(long userId, String time);
	
	Map<String, BigDecimal> getMechanismIncomeStatistics(Long userId);
}

