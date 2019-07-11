package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.DateUtils;
import com.cqcej.web.modules.common.dao.WorkerIncomeLogDao;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDataEntity;
import com.cqcej.web.modules.common.entity.ServiceOrderStatisticsDetailEntity;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.entity.WorkerIncomeLogEntity;
import com.cqcej.web.modules.common.service.UserService;
import com.cqcej.web.modules.common.service.WorkerIncomeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("workerIncomeLogService")
public class WorkerIncomeLogServiceImpl extends ServiceImpl<WorkerIncomeLogDao, WorkerIncomeLogEntity> implements WorkerIncomeLogService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public ServiceOrderStatisticsDataEntity getServiceOrderStatisticsDate(long userId, String time) {
		UserEntity user = userService.selectById(userId);
		
		Map<String, String> timeParam = getDayWithMonth(time);
		ServiceOrderStatisticsDataEntity result = baseMapper
				.getServiceOrderStatisticsDate(userId, timeParam.get("firstDateTime"), timeParam.get("lastDateTime"));
		
		result.setTotalIncome(user.getTotalIncome());
		
		return result;
	}
	
	@Override
	public List<ServiceOrderStatisticsDetailEntity> getServiceOrderStatisticsDetail(long userId, String time) {
		Map<String, String> timeParam = getDayWithMonth(time);
		return baseMapper
				.getServiceOrderStatisticsDetail(userId, timeParam.get("firstDateTime"), timeParam.get("lastDateTime"));
	}
	
	@Override
	public Map<String, BigDecimal> getMechanismIncomeStatistics(Long userId) {
		Date date = new Date();
		EntityWrapper<WorkerIncomeLogEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		
		// 历史收入
		List<WorkerIncomeLogEntity> historyIncomes = selectList(where);
		BigDecimal history = new BigDecimal(0);
		for (WorkerIncomeLogEntity item : historyIncomes) {
			history = history.add(item.getAmount());
		}
		
		// 今日收入
		where.where("create_at>{0} and create_at<{1}", DateUtils.getDateZero(date), DateUtils.format(date, DateUtils.DATE_TIME_PATTERN));
		List<WorkerIncomeLogEntity> todayIncomes = selectList(where);
		BigDecimal today = new BigDecimal(0);
		for (WorkerIncomeLogEntity item : todayIncomes) {
			today = today.add(item.getAmount());
		}
		
		Map<String, BigDecimal> result = new HashMap<>();
		result.put("history", history);
		result.put("today", today);
		
		return result;
	}
	
	private Map<String, String> getDayWithMonth(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
		SimpleDateFormat sdfWithDay = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date parse;
		try {
			parse = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("时间解析失败，应用当前日期");
			parse = new Date();
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
		String lastDateTime = sdfWithDay.format(calendar.getTime());
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
		String firstDateTime = sdfWithDay.format(calendar.getTime());
		
		Map<String, String> rst = new HashMap<>();
		rst.put("firstDateTime", firstDateTime);
		rst.put("lastDateTime", lastDateTime);
		return rst;
	}
}
