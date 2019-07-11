package com.cqcej.web.modules.app.dao.home;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.home.AppArticleEntity;
import com.cqcej.web.modules.common.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资讯
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-18 16:15:16
 */
@Mapper
public interface AppArticleDao extends BaseMapper<ArticleEntity> {
	List<AppArticleEntity> getHomeArticle(int type);
}
