package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作者，包含医生，接送者，遛狗人员
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-19 15:05:48
 */
@Mapper
public interface WorkerDao extends BaseMapper<WorkerEntity> {
}
