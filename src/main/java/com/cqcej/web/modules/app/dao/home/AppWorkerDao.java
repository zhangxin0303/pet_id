package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.dto.MechDoctorStatInfoDTO;
import com.cqcej.web.modules.app.entity.dto.MechDoctorStatisticsDTO;
import com.cqcej.web.modules.app.entity.home.AppWorkerEntity;
import com.cqcej.web.modules.app.form.MechBeauticianForm;
import com.cqcej.web.modules.app.form.MechDoctorForm;
import com.cqcej.web.modules.app.form.MechPickupForm;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 工作者，包含医生，接送者，遛狗人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
@Mapper
public interface AppWorkerDao extends BaseMapper<WorkerEntity> {
	List<AppWorkerEntity> getRecommendDoctor();
	
	List<AppWorkerEntity> getDoctor(@Param("type") int type, @Param("mechanismId") long mechanismId, @Param("petClassId") int petClassId,
	                                @Param("sortType") int sortType, @Param("start") int start, @Param("size") int size,
	                                @Param("longitude") double longitude, @Param("latitude") double latitude);
	
	AppWorkerEntity getWorkerDetail(Long workerId);
	
	List<AppWorkerEntity> getHotBeautician();
	
	WorkerEntity getWorkerWithUserId(Long userId);
	
	int updateCoordinate(@Param("userId") Long userId, @Param("longitude") String longitude, @Param("latitude") String latitude);
	
	List<AppWorkerEntity> workerList(@Param("mechId") Long mechId, @Param("workerType") Integer workerType);
	
	int deleteByWorkerId(List<Long> workerId);
	
	int insertWorker(MechDoctorForm doctor);
	
	int insertUser(MechDoctorForm doctor);
	
	AppWorkerEntity workerInfo(Long workerId);
	
	int updateDoctor(MechDoctorForm form);
	
	BigDecimal findTotalSum(Long workerId);
	
	MechDoctorStatInfoDTO findCountAndSum(@Param("workerId") Long workerId, @Param("date") String date);
	
	List<MechDoctorStatisticsDTO> statistics(
			@Param("workerId") Long workerId, @Param("date") String date);
	
	int insertUserOfBeautician(MechBeauticianForm b);
	
	int insertWorkerOfBeautician(MechBeauticianForm b);
	
	int updateBeautician(MechBeauticianForm b);
	
	int insertUserOfPickUp(MechPickupForm p);
	
	int insertWorkerOfPickUp(MechPickupForm p);
	
	int updatePickup(MechPickupForm p);
}
