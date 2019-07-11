package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.MechanismServiceDao;
import com.cqcej.web.modules.common.entity.MechanismServiceEntity;
import com.cqcej.web.modules.common.service.MechanismServiceService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("mechanismServiceService")
public class MechanismServiceServiceImpl extends ServiceImpl<MechanismServiceDao, MechanismServiceEntity> implements MechanismServiceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MechanismServiceEntity> page = this.selectPage(
                new Query<MechanismServiceEntity>(params).getPage(),
                new EntityWrapper<MechanismServiceEntity>()
        );

        return new PageUtils(page);
    }

}
