package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.home.AppMechanismImagesDao;
import com.cqcej.web.modules.app.service.AppMechanismImagesService;
import com.cqcej.web.modules.common.entity.MechanismImagesEntity;
import org.springframework.stereotype.Service;


@Service("appMechanismImagesService")
public class AppMechanismImagesServiceImpl extends ServiceImpl<AppMechanismImagesDao, MechanismImagesEntity> implements AppMechanismImagesService {

}
