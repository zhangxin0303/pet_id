package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminArticleCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminArticleEntity;

import java.util.List;

/**
 * 资讯
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-17 10:41:21
 */
public interface AdminArticleService extends IService<AdminArticleEntity> {

	//PageUtils queryPage(Map<String, Object> params);

	//资讯列表
	PageUtils<AdminArticleEntity> getArticleList(String articleTitle,Integer page, Integer size);

	//删除资讯
	int deleteArticleById(List<Long> articleId);
	//新增资讯
	int saveArticle(AdminArticleEntity article);

//	//查询资讯信息
//	AdminArticleEntity selectArticleById(Long articleId, Integer page, Integer size);

	//查询资讯信息
	AdminArticleEntity selectArticleById(Long articleId);

	//查询资讯评论
	PageUtils<AdminArticleCommentEntity> commentInfo(Long articleId, Integer page, Integer size);

	int deleteArticleComment(Long commentId);
	//禁言用户
	int forbidden(Long userId);
	//修改资讯
	int updateArticleById(AdminArticleEntity article);
	//查看
	AdminArticleEntity view(Long articleId);
}

