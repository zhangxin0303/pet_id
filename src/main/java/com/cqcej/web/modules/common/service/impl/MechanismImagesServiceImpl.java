package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.MechanismImagesDao;
import com.cqcej.web.modules.common.entity.MechanismImagesEntity;
import com.cqcej.web.modules.common.service.MechanismImagesService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("mechanismImagesService")
public class MechanismImagesServiceImpl extends ServiceImpl<MechanismImagesDao, MechanismImagesEntity> implements MechanismImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MechanismImagesEntity> page = this.selectPage(
                new Query<MechanismImagesEntity>(params).getPage(),
                new EntityWrapper<MechanismImagesEntity>()
        );

        return new PageUtils(page);
    }

}
