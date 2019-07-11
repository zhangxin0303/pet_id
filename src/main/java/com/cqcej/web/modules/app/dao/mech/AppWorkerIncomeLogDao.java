package com.cqcej.web.modules.app.dao.mech;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppWorkerIncomeLogEntity;
import com.cqcej.web.modules.app.entity.dto.MechWorkerDayIncomeDTO;
import com.cqcej.web.modules.app.entity.dto.MechWorkerIncomeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工作者收入日志
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-11-08 14:30:33
 */
@Mapper
public interface AppWorkerIncomeLogDao extends BaseMapper<AppWorkerIncomeLogEntity> {
	
	
	MechWorkerIncomeDTO list(@Param("date") String date, @Param("userId") Long userId);
	
	List<MechWorkerDayIncomeDTO> info(@Param("date") String date, @Param("userId") Long userId);
}
