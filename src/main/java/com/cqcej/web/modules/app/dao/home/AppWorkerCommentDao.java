package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.home.AppWorkerCommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论工作人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
@Mapper
public interface AppWorkerCommentDao extends BaseMapper<AppWorkerCommentEntity> {
	
	AppWorkerCommentEntity selectComments(Long workerId);
	
	List<AppWorkerCommentEntity> getWorkerComments(@Param("workerId") long workerId, @Param("start") int start, @Param("size") Integer size);
	
	int getWorkerCommentCount(long workerId);
}
