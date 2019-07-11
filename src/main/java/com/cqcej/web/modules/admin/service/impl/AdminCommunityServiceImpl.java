package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminCommunityDao;
import com.cqcej.web.modules.admin.entity.AdminCommunityCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminCommunityEntity;
import com.cqcej.web.modules.admin.service.AdminArticleService;
import com.cqcej.web.modules.admin.service.AdminCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("adminCommunityService")
public class AdminCommunityServiceImpl extends ServiceImpl<AdminCommunityDao, AdminCommunityEntity> implements AdminCommunityService {

	@Autowired
	private AdminArticleService adminArticleService;
//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		Page<AdminCommunityEntity> page = this.selectPage(
//				new Query<AdminCommunityEntity>(params).getPage(),
//				new EntityWrapper<AdminCommunityEntity>()
//		);
//
//		return new PageUtils(page);
//	}

	//帖子列表
	@Override
	public PageUtils<AdminCommunityEntity> getCommunitylist(Integer classId,String title, Integer page, Integer size) {
		int start = (page - 1) *size;
		List<AdminCommunityEntity> list = baseMapper.getCommunitylist(classId,title,start,size);
		int count = baseMapper.getCommunityCount(classId,title);
		PageUtils<AdminCommunityEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	//置顶or取消
	@Override
	public int top(Long communityId, Integer isTop) {
		return baseMapper.top(communityId,isTop);
	}
	//删除帖子(以及对应的所有评论)
	@Override
	public int deleteCommunity(Long communityId) {
		baseMapper.deleteCommentByCommunityId(communityId);//删除对应评论
		return baseMapper.deleteCommunity(communityId);//删除帖子
	}

	//帖子详情
	@Override
	public AdminCommunityEntity selectCommunityById(Long communityId) {
		return baseMapper.selectById(communityId);
	}

	//帖子评论
	@Override
	public PageUtils<AdminCommunityCommentEntity> commentInfo(Long communityId, Integer page, Integer size) {
		int start = (page - 1) * size;
		List<AdminCommunityCommentEntity> list = baseMapper.getCommList(communityId,start,size);
		int count = baseMapper.getCommCount(communityId);
		PageUtils pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}

	//禁言用户
	@Override
	public int forbidden(Long userId) {
		return adminArticleService.forbidden(userId);//调用资讯mapper的禁言方法
	}

	//删除评论
	@Override
	public int deleteCommunityComment(Long commentId) {
		return baseMapper.deleteCommunityComment(commentId);
	}
	//新增主题
	@Override
	public int saveCommunity(AdminCommunityEntity community) {
		return baseMapper.saveCommunity(community);
	}

	//编辑主题(确定)
	@Override
	public int updateCommunity(Long communityId, String context) {
		return baseMapper.updateCommunity(communityId,context);
	}
	//查看
	@Override
	public AdminCommunityEntity view(Long communityId) {
		return baseMapper.view(communityId);
	}
}









