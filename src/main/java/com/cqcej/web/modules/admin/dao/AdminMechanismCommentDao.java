package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminMechanismCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminMechanismCommentResultEntity;
import com.cqcej.web.modules.admin.entity.AdminShopOrderCommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构评价
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-16 11:08:31
 */
@Mapper
public interface AdminMechanismCommentDao extends BaseMapper<AdminMechanismCommentEntity> {

    int getMechanismCommCount(@Param("mechanismType")Integer mechanismType,@Param("mechanismName")String mechanismName);

    List<AdminMechanismCommentResultEntity> getMechanismCommList(@Param("mechanismType")Integer mechanismType,
                                                                 @Param("mechanismName")String mechanismName,
                                                                 @Param("start")Integer start,
                                                                 @Param("size")Integer size);

    int getShopOrderCount(@Param("mechanismId")Integer mechanismId,@Param("commentLevel")String commentLevel);

    List<AdminShopOrderCommentEntity> getShopOrderList(@Param("mechanismId")Integer mechanismId,
                                                       @Param("commentLevel")String commentLevel,
                                                       @Param("start")Integer start,
                                                       @Param("size")Integer size);
}
