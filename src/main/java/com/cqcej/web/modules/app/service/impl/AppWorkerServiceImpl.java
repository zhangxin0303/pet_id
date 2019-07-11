package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.home.AppWorkerDao;
import com.cqcej.web.modules.app.entity.dto.MechDoctorStatInfoDTO;
import com.cqcej.web.modules.app.entity.dto.MechDoctorStatisticsDTO;
import com.cqcej.web.modules.app.entity.home.AppWorkerEntity;
import com.cqcej.web.modules.app.form.MechBeauticianForm;
import com.cqcej.web.modules.app.form.MechDoctorForm;
import com.cqcej.web.modules.app.form.MechPickupForm;
import com.cqcej.web.modules.app.service.AppWorkerService;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service("appWorkerService")
public class AppWorkerServiceImpl extends ServiceImpl<AppWorkerDao, WorkerEntity> implements AppWorkerService {
	@Override
	public List<AppWorkerEntity> getRecommendDoctor() {
		return baseMapper.getRecommendDoctor();
	}
	
	@Override
	public AppPage<AppWorkerEntity> getWorkers(int type, long mechanismId, int petClassId, int sortType, int page, int size, double longitude, double latitude) {
		EntityWrapper<WorkerEntity> wrapper = new EntityWrapper<>();
		// 筛选当前在线的工作人员
		wrapper.where("worker_type={0} and status=1", type);
		int count = selectCount(wrapper);
		
		int start = (page - 1) * size;
		List<AppWorkerEntity> list = baseMapper.getDoctor(type, mechanismId, petClassId, sortType, start, size, longitude, latitude);
		return new AppPage<>(count, size, list);
	}
	
	@Override
	public AppWorkerEntity getWorkerDetail(Long workerId) {
		return baseMapper.getWorkerDetail(workerId);
	}
	
	@Override
	public List<AppWorkerEntity> getHotBeautician() {
		return baseMapper.getHotBeautician();
	}
	
	@Override
	public WorkerEntity getWorkerDetailWithUserId(Long userId) {
		return baseMapper.getWorkerWithUserId(userId);
	}
	
	@Override
	public boolean saveUserGoodAt(long userId, String goodat) {
		WorkerEntity save = new WorkerEntity();
		save.setGoodAt(goodat);
		
		EntityWrapper<WorkerEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		return update(save, where);
	}
	
	@Override
	public boolean saveUserIntroduce(long userId, String introduce) {
		WorkerEntity save = new WorkerEntity();
		save.setWorkerDescription(introduce);
		
		EntityWrapper<WorkerEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		return update(save, where);
	}
	
	@Override
	public boolean saveUserFee(Long userId, String type, Double fee) {
		WorkerEntity worker = new WorkerEntity();
		
		if (type.equals("chat")) {
			worker.setChatFee(fee);
		} else if (type.equals("telephone")) {
			worker.setTelephoneFee(fee);
		} else if (type.equals("onDoor")) {
			worker.setOndoorFee(fee);
		} else if (type.equals("walkDog")) {
			worker.setWalkDogFee(fee);
		}
		
		EntityWrapper<WorkerEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		return update(worker, where);
	}
	
	@Override
	public int updateCoordinate(Long userId, String longitude, String latitude) {
		return baseMapper.updateCoordinate(userId, longitude, latitude);
	}
	
	@Override
	public List<AppWorkerEntity> workerList(Long mechId, Integer workerType) {
		return baseMapper.workerList(mechId, workerType);
		
	}
	
	@Override
	public Boolean deleteByWorkerId(List<Long> workerId) {
		if (workerId == null || workerId.size() == 0) {
			return false;
		}
		return baseMapper.deleteByWorkerId(workerId) > 0 ? true :false;
	}
	
	@Override
	public int insertDoctor(MechDoctorForm form) {
		int result2 = baseMapper.insertUser(form);
		int result1 = baseMapper.insertWorker(form);
		if (result1 > 0 && result2 > 0) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public AppWorkerEntity info(Long workerId) {
		return baseMapper.workerInfo(workerId);
	}
	
	@Override
	public int updateDoctor(MechDoctorForm form) {
		return baseMapper.updateDoctor(form);
	}
	
	@Override
	public MechDoctorStatInfoDTO statistics(Long workerId, String date) {
		//历史总金额
		BigDecimal sum = baseMapper.findTotalSum(workerId);
		//订单总量和总金额
		MechDoctorStatInfoDTO info = baseMapper.findCountAndSum(workerId, date);
		info.setTotalSumAmount(sum);
		//明细
		List<MechDoctorStatisticsDTO> data = baseMapper.statistics(workerId, date);
		info.setList(data);
		return info;
	}
	
	@Override
	public Boolean insertBeautician(MechBeauticianForm form) {
		int result2 = baseMapper.insertUserOfBeautician(form);
		int result1 = baseMapper.insertWorkerOfBeautician(form);
		if (result1 > 0 && result2 > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean updateBeautician(MechBeauticianForm form) {
		return baseMapper.updateBeautician(form) > 0 ? true : false;
	}
	
	@Override
	public Boolean insertPickUp(MechPickupForm form) {
		int result2 = baseMapper.insertUserOfPickUp(form);
		int result1 = baseMapper.insertWorkerOfPickUp(form);
		if (result1 > 0 && result2 > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean updatePickup(MechPickupForm form) {
		return baseMapper.updatePickup(form) > 0 ? true : false;
	}
}







