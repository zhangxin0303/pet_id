package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.home.AppMechanismCommentEntity;
import com.cqcej.web.modules.common.entity.MechanismCommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构评价
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@Mapper
public interface AppMechanismCommentDao extends BaseMapper<MechanismCommentEntity> {
	
	List<AppMechanismCommentEntity> getClinicComments(@Param("userId") long userId, @Param("clinicId") long clinicId,
	                                                  @Param("start") int start, @Param("size") int size);
	
	void addPriseCount(long objectId);
	
	void reducePriseCount(long objectId);
}
