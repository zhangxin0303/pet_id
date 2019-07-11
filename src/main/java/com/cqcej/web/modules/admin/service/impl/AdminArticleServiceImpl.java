package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.LocalFileUtils;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminArticleDao;
import com.cqcej.web.modules.admin.entity.AdminArticleCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminArticleEntity;
import com.cqcej.web.modules.admin.service.AdminArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("adminArticleService")
public class AdminArticleServiceImpl extends ServiceImpl<AdminArticleDao, AdminArticleEntity> implements AdminArticleService {

	@Autowired
	private AdminArticleDao adminArticleDao;
//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		Page<ArticleEntity> page = this.selectPage(
//				new Query<ArticleEntity>(params).getPage(),
//				new EntityWrapper<ArticleEntity>()
//		);

//
//		return new PageUtils(page);
//	}
	//获取资讯列表
	@Override
	public PageUtils<AdminArticleEntity> getArticleList(String articleTitle,Integer page,Integer size) {
		int start = (page - 1) * size;
		List<AdminArticleEntity>  list =  baseMapper.getArticleList(articleTitle,start,size);
		int count = baseMapper.getArticleCount(articleTitle);
		PageUtils<AdminArticleEntity> pageUtils = new PageUtils<>(list,count,page,size);
		return pageUtils;
	}

	//删除资讯信息
	@Override
	public int deleteArticleById(List<Long> articleIds) {
		int result = 0;
		if(articleIds.size() > 0){
			result = baseMapper.deleteArticleByIds(articleIds);//数据库cascade直接删除资讯评论
		}
		return result;
	}

	//新增资讯
	@Override
	public int saveArticle(AdminArticleEntity article) {
		//设置资讯的添加时间
		article.setCreateAt(new Date());
		String content = article.getArticleContent();
		//配置文件中的图片前缀
		String imgUrl = LocalFileUtils.imageUrl;
		int index = content.indexOf(imgUrl);
		// 获取第一张图片路径
		if(index != -1){
			//String imgPath = "http://img.cqcej.com/1543392638000a4807f0899fa4dd0acc68fd9de163b6b.jpg";
			//图片路径长度为70
			article.setThumbImageUrl(content.substring(index,index+70));
		}
		return adminArticleDao.insert(article);
	}

	//查询资讯信息
	@Override
	public AdminArticleEntity selectArticleById(Long articleId){
		return baseMapper.selectArticleById(articleId);
	}
	//查询资讯评论
	public PageUtils<AdminArticleCommentEntity> commentInfo(Long articleId,Integer page,Integer size){
		int start = (page - 1) * size;
		List<AdminArticleCommentEntity> list = baseMapper.getCommList(articleId,start,size);
		int count = baseMapper.getCommCount(articleId);
		PageUtils pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}

	//删除资讯评论
	@Override
	public int deleteArticleComment(Long commentId) {
		return adminArticleDao.deleteArticleComment(commentId);
	}

	//禁言用户
	@Override
	public int forbidden(Long userId) {
		return baseMapper.forbidden(userId);
	}
	//修改资讯

	@Override
	public int updateArticleById(AdminArticleEntity article) {
		return baseMapper.updateArticleById(article);
	}
	//查看
	@Override
	public AdminArticleEntity view(Long articleId) {
		return baseMapper.view(articleId);
	}
}




















