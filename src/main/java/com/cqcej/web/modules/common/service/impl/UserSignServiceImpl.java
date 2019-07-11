package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.UserSignDao;
import com.cqcej.web.modules.common.entity.UserSignEntity;
import com.cqcej.web.modules.common.service.UserSignService;
import org.springframework.stereotype.Service;



@Service("adminUserSignService")
public class UserSignServiceImpl extends ServiceImpl<UserSignDao, UserSignEntity> implements UserSignService {


}
