package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.MechanismCommentDao;
import com.cqcej.web.modules.common.entity.MechanismCommentEntity;
import com.cqcej.web.modules.common.service.MechanismCommentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("mechanismCommentService")
public class MechanismCommentServiceImpl extends ServiceImpl<MechanismCommentDao, MechanismCommentEntity> implements MechanismCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MechanismCommentEntity> page = this.selectPage(
                new Query<MechanismCommentEntity>(params).getPage(),
                new EntityWrapper<MechanismCommentEntity>()
        );

        return new PageUtils(page);
    }

}
