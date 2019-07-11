package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AppUpgradeDao;
import com.cqcej.web.modules.admin.entity.AppUpgradeEntity;
import com.cqcej.web.modules.admin.service.AppUpgradeService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("appUpgradeService")
public class AppUpgradeServiceImpl extends ServiceImpl<AppUpgradeDao, AppUpgradeEntity> implements AppUpgradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AppUpgradeEntity> page = this.selectPage(
                new Query<AppUpgradeEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }
    
    @Override
    public AppUpgradeEntity findNewVersion(String currentVersion) {
        return baseMapper.findNewVersion(currentVersion);
    }
    
}
