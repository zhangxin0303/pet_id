package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminCommunityCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminCommunityEntity;

/**
 * 社区
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:15
 */
public interface AdminCommunityService extends IService<AdminCommunityEntity> {

	//PageUtils queryPage(Map<String, Object> params);

	//帖子列表
	PageUtils getCommunitylist(Integer classId,String title, Integer page, Integer size);

	//帖子置顶
	int top(Long communityId,Integer isTop);

	//删除帖子
	int deleteCommunity(Long communityId);

	//帖子详情
	AdminCommunityEntity selectCommunityById(Long communityId);

	//帖子评论
	PageUtils<AdminCommunityCommentEntity> commentInfo(Long communityId,Integer page,Integer size);

	//禁言用户
	int forbidden(Long userId);

	//删除评论
	int deleteCommunityComment(Long commentId);

	//新增主题(帖子)
	int saveCommunity(AdminCommunityEntity community);
	
	//修改帖子(确定)
	int  updateCommunity(Long communityId,String context);

	//查看主题
	AdminCommunityEntity view(Long communityId);
}

