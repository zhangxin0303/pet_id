package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.AppFeedbackDao;
import com.cqcej.web.modules.app.entity.AppFeedbackEntity;
import com.cqcej.web.modules.app.service.AppFeedbackService;
import org.springframework.stereotype.Service;



@Service("appFeedbackService")
public class AppFeedbackServiceImpl extends ServiceImpl<AppFeedbackDao, AppFeedbackEntity> implements AppFeedbackService {



}
