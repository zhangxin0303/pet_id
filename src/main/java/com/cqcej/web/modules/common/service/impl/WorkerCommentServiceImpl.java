package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.WorkerCommentDao;
import com.cqcej.web.modules.common.entity.WorkerCommentEntity;
import com.cqcej.web.modules.common.service.WorkerCommentService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("workerCommentService")
public class WorkerCommentServiceImpl extends ServiceImpl<WorkerCommentDao, WorkerCommentEntity> implements WorkerCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WorkerCommentEntity> page = this.selectPage(
                new Query<WorkerCommentEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }
    
    @Override
    public void beautyComment(Long workerId, int beauty_number) {
    
    }
    
}
