package com.cqcej.web.modules.app.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.home.AppArticleEntity;
import com.cqcej.web.modules.common.entity.ArticleEntity;

import java.util.List;

/**
 * 资讯
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:15:16
 */
public interface AppArticleService extends IService<ArticleEntity> {
	List<AppArticleEntity> getHomeArticle(int type);
}

