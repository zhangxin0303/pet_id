package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.home.AppArticleCommentEntity;
import com.cqcej.web.modules.common.entity.ArticleCommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资讯评论
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:15:16
 */
@Mapper
public interface ArticleCommentDao extends BaseMapper<ArticleCommentEntity> {
	
	List<AppArticleCommentEntity> getComments(@Param("articleId") Long articleId, @Param("start") int start, @Param("size") Integer size);
}
