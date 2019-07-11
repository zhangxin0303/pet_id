package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.AppWorkerIncomeLogEntity;
import com.cqcej.web.modules.app.entity.dto.MechWorkerDayIncomeDTO;
import com.cqcej.web.modules.app.entity.dto.MechWorkerIncomeDTO;

import java.util.List;

/**
 * 工作者收入日志
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-11-08 14:30:33
 */
public interface AppWorkerIncomeLogService extends IService<AppWorkerIncomeLogEntity> {

	MechWorkerIncomeDTO list(String date,Long userId);
	
	List<MechWorkerDayIncomeDTO> info(String date, Long userId);

	
}

