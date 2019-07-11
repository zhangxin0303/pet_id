package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.ArticleDao;
import com.cqcej.web.modules.common.entity.ArticleEntity;
import com.cqcej.web.modules.common.service.ArticleService;
import org.springframework.stereotype.Service;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {
	
}
