package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.mech.AppWorkerIncomeLogDao;
import com.cqcej.web.modules.app.entity.AppWorkerIncomeLogEntity;
import com.cqcej.web.modules.app.entity.dto.MechWorkerDayIncomeDTO;
import com.cqcej.web.modules.app.entity.dto.MechWorkerIncomeDTO;
import com.cqcej.web.modules.app.service.AppWorkerIncomeLogService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appWorkerIncomeLogService")
public class AppWorkerIncomeLogServiceImpl extends ServiceImpl<AppWorkerIncomeLogDao, AppWorkerIncomeLogEntity> implements AppWorkerIncomeLogService {
	
	
	@Override
	public MechWorkerIncomeDTO list(String date, Long userId) {
		return baseMapper.list(date,userId);
	}
	
	@Override
	public List<MechWorkerDayIncomeDTO> info(String date, Long userId) {
		return baseMapper.info(date,userId);
	}
}
