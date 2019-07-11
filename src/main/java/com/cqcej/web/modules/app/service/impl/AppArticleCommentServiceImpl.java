package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.service.AppArticleCommentService;
import com.cqcej.web.modules.common.dao.ArticleCommentDao;
import com.cqcej.web.modules.common.entity.ArticleCommentEntity;
import org.springframework.stereotype.Service;


@Service("appArticleCommentService")
public class AppArticleCommentServiceImpl extends ServiceImpl<ArticleCommentDao, ArticleCommentEntity> implements AppArticleCommentService {

}
