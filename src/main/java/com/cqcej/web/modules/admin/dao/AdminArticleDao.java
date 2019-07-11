package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminArticleCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资讯
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-17 10:41:21
 */
@Mapper
public interface AdminArticleDao extends BaseMapper<AdminArticleEntity> {
	
	
	int getArticleCount(@Param("articleTitle") String articleTitle);
	
	//资讯列表
	List<AdminArticleEntity> getArticleList(@Param("articleTitle") String articleTitle, @Param("start") Integer start, @Param("size") Integer size);
	
	//删除资讯
	int deleteArticleByIds(@Param("articleIds") List<Long> articleIds);
	
	//保存资讯
	int saveArticle(AdminArticleEntity article);
	
	//查询资讯信息
	AdminArticleEntity selectArticleById(@Param("articleId") Long articleId);
	
	//查询一条资讯下的所有评论信息
	List<AdminArticleCommentEntity> getCommList(@Param("articleId") Long articleId, @Param("start") Integer start, @Param("size") Integer size);
	
	//查询一条资讯下的评论总数
	int getCommCount(Long articleId);
	
	//删除资讯评论
	int deleteArticleComment(Long commmentId);
	
	//禁言用户
	int forbidden(@Param("userId") Long userId);
	
	//修改资讯
	int updateArticleById(AdminArticleEntity article);
	
	//查看
	AdminArticleEntity view(@Param("articleId") Long articleId);
}
