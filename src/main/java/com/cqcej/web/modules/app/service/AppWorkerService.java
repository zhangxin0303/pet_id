package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.dto.MechDoctorStatInfoDTO;
import com.cqcej.web.modules.app.entity.home.AppWorkerEntity;
import com.cqcej.web.modules.app.form.MechBeauticianForm;
import com.cqcej.web.modules.app.form.MechDoctorForm;
import com.cqcej.web.modules.app.form.MechPickupForm;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.WorkerEntity;

import java.util.List;

/**
 * 工作者，包含医生，接送者，遛狗人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
public interface AppWorkerService extends IService<WorkerEntity> {
	
	List<AppWorkerEntity> getRecommendDoctor();
	
	AppPage<AppWorkerEntity> getWorkers(int type, long mechanismId, int petClassId, int sortType, int page, int size, double longitude, double latitude);
	
	AppWorkerEntity getWorkerDetail(Long workerId);
	
	List<AppWorkerEntity> getHotBeautician();
	
	WorkerEntity getWorkerDetailWithUserId(Long userId);
	
	boolean saveUserGoodAt(long userId, String goodat);
	
	boolean saveUserIntroduce(long userId, String introduce);
	
	boolean saveUserFee(Long userId, String type, Double fee);
	
	int updateCoordinate(Long userId, String longitude, String latitude);
	
	List<AppWorkerEntity> workerList(Long mechId, Integer workerType);
	
	Boolean deleteByWorkerId(List<Long> workerId);
	
	int insertDoctor(MechDoctorForm form);
	
	AppWorkerEntity info(Long workerId);
	
	int updateDoctor(MechDoctorForm form);
	
	MechDoctorStatInfoDTO statistics(Long workerId, String date);
	
	Boolean insertBeautician(MechBeauticianForm form);
	
	Boolean updateBeautician(MechBeauticianForm form);
	
	Boolean insertPickUp(MechPickupForm form);
	
	Boolean updatePickup(MechPickupForm form);
}

