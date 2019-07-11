package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.ArticleCommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资讯评论
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:15:16
 */
@Mapper
public interface AppArticleCommentDao extends BaseMapper<ArticleCommentEntity> {
	
}
