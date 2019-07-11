package com.cqcej.web.modules.app.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.dao.home.AppArticleDao;
import com.cqcej.web.modules.app.entity.home.AppArticleEntity;
import com.cqcej.web.modules.app.service.AppArticleService;
import com.cqcej.web.modules.common.entity.ArticleEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appArticleService")
public class AppArticleServiceImpl extends ServiceImpl<AppArticleDao, ArticleEntity> implements AppArticleService {
	
	@Override
	public List<AppArticleEntity> getHomeArticle(int type) {
		return baseMapper.getHomeArticle(type);
	}
}
