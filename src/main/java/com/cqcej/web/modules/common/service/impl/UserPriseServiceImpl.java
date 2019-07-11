package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.UserPriseDao;
import com.cqcej.web.modules.common.entity.UserPriseEntity;
import com.cqcej.web.modules.common.service.UserPriseService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("userPriseService")
public class UserPriseServiceImpl extends ServiceImpl<UserPriseDao, UserPriseEntity> implements UserPriseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserPriseEntity> page = this.selectPage(
                new Query<UserPriseEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

}
