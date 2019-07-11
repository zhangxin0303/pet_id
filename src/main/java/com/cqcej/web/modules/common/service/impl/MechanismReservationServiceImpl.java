package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.MechanismReservationDao;
import com.cqcej.web.modules.common.entity.MechanismReservationEntity;
import com.cqcej.web.modules.common.service.MechanismReservationService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("mechanismReservationService")
public class MechanismReservationServiceImpl extends ServiceImpl<MechanismReservationDao, MechanismReservationEntity> implements MechanismReservationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MechanismReservationEntity> page = this.selectPage(
                new Query<MechanismReservationEntity>(params).getPage(),
                new EntityWrapper<MechanismReservationEntity>()
        );

        return new PageUtils(page);
    }

}
