package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.WorkerDao;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import com.cqcej.web.modules.common.service.WorkerService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("workerService")
public class WorkerServiceImpl extends ServiceImpl<WorkerDao, WorkerEntity> implements WorkerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WorkerEntity> page = this.selectPage(
                new Query<WorkerEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }
}
