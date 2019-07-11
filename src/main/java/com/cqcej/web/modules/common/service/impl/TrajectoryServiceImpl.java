package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.TrajectoryDao;
import com.cqcej.web.modules.common.entity.TrajectoryEntity;
import com.cqcej.web.modules.common.service.TrajectoryService;
import org.springframework.stereotype.Service;


@Service("trajectoryService")
public class TrajectoryServiceImpl extends ServiceImpl<TrajectoryDao, TrajectoryEntity> implements TrajectoryService {

}
