package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.TrajectoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 遛狗轨迹日志
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-10 10:57:42
 */
@Mapper
public interface TrajectoryDao extends BaseMapper<TrajectoryEntity> {
	
}
