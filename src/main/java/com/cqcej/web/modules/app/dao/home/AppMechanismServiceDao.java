package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.home.AppMechanismCommentEntity;
import com.cqcej.web.modules.app.entity.home.AppMechanismServiceEntity;
import com.cqcej.web.modules.app.form.MechanismServiceForm;
import com.cqcej.web.modules.common.entity.MechanismServiceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构服务中间表
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@Mapper
public interface AppMechanismServiceDao extends BaseMapper<MechanismServiceEntity> {
	
	List<AppMechanismServiceEntity> getMechanismServices(long mechanismId);
	
	List<AppMechanismServiceEntity> selectServiceList(@Param("mechId") Long mechId, @Param("serviceType") Integer serviceType);
	
	int addService(MechanismServiceForm form);
	
	int updateService(MechanismServiceForm form);
	
	int deleteImages(List<Long> images);
	
	int deleteByServiceId(List<Long> serviceId);
	
	int selectMechStart(Long mechId);
	
	int commentListCount(Long mechId);
	
	int addReply(Long commonId,String content);
	
	List<AppMechanismCommentEntity> commentList(@Param("mechId") Long mechId, @Param("start") Integer start, @Param("size") Integer size);
}
