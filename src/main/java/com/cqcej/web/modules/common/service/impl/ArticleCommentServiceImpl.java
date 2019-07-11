package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.entity.home.AppArticleCommentEntity;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.dao.ArticleCommentDao;
import com.cqcej.web.modules.common.entity.ArticleCommentEntity;
import com.cqcej.web.modules.common.service.ArticleCommentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("articleCommentService")
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentDao, ArticleCommentEntity> implements ArticleCommentService {
	
	@Override
	public AppPage<AppArticleCommentEntity> getComments(Long articleId, Integer page, Integer size) {
		EntityWrapper<ArticleCommentEntity> where = new EntityWrapper<>();
		where.where("article_id={0}", articleId);
		int count = selectCount(where);
		
		
		int start = (page - 1) * size;
		List<AppArticleCommentEntity> list = baseMapper.getComments(articleId, start, size);
		
		return new AppPage<>(count, size, list);
	}
}
