package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminWorkerCommentResultEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论工作人员
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-11 14:39:18
 */
@Mapper
public interface AdminWorkerCommentDao extends BaseMapper<AdminWorkerCommentEntity> {

    int getWorkerCommentCount(@Param("workerType")Integer workerType,@Param("nickname")String nickname);

    List<AdminWorkerCommentResultEntity> getWorkerCommentResultList(@Param("workerType")Integer workerType,@Param("nickname")String nickname,
                                                                    @Param("start")Integer start, @Param("size")Integer size);

    int getOrderCommentCount(@Param("workerId")Integer workerId, @Param("commentLevel")Integer commentLevel);

    List<AdminServiceOrderCommentEntity> getOrderCommentList(@Param("workerId")Integer workerId, @Param("commentLevel")Integer commentLevel,
                                                             @Param("start")Integer start, @Param("size")Integer size);


}
