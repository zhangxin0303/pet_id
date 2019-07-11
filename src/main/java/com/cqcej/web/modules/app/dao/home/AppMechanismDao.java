package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.dto.MechMechanismDTO;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.common.entity.MechanismEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构，包含诊所，美容院
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@Mapper
public interface AppMechanismDao extends BaseMapper<MechanismEntity> {
	List<AppMechanismEntity> getClinicList(@Param("petClassId") int petClassId, @Param("sortType") int sortType,
	                                       @Param("start") int start, @Param("size") int size,
	                                       @Param("longitude") double longitude, @Param("latitude") double latitude);
	
	AppMechanismEntity getClinicDetail(@Param("clinicId") long clinicId, @Param("userId") long userId);
	
	Integer isFavorite(@Param("userId") long userId);
	
	List<AppMechanismEntity> getMechanismRecommend(int type);
	
	List<AppMechanismEntity> getNewestMechanism(int type);
	
	void commentUpdate(@Param("star") int star, @Param("mechanismId") long mechanismId, @Param("average") int average);
	
	AppMechanismEntity getMechanismDetailWithUserId(@Param("userId") Long userId);
	
	AppMechanismEntity check(@Param("userId") Long userId, @Param("mechId") Long mechId);
	
	List<String> images(Long mechId);
	
	//删除轮播图
	int deleteImages(Long mechId);
	
	//修改轮播图
	int updateImages(@Param("images") List<String> images, @Param("mechId") Long mechId);
	
	MechMechanismDTO mechInfo(Long mechId);
	
	int updateMech(MechMechanismDTO m);
}
