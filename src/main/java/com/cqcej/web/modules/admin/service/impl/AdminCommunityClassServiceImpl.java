package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.admin.dao.AdminCommunityClassDao;
import com.cqcej.web.modules.admin.dao.AdminCommunityDao;
import com.cqcej.web.modules.admin.entity.AdminCommunityClassEntity;
import com.cqcej.web.modules.admin.service.AdminCommunityClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("adminCommunityClassService")
public class AdminCommunityClassServiceImpl extends ServiceImpl<AdminCommunityClassDao, AdminCommunityClassEntity> implements AdminCommunityClassService {

	@Autowired
	private AdminCommunityDao adminCommunityDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<AdminCommunityClassEntity> page = this.selectPage(
				new Query<AdminCommunityClassEntity>(params).getPage(),
				new EntityWrapper<AdminCommunityClassEntity>()
		);

		return new PageUtils(page);
	}

	//版块列表
	@Override
	public List<AdminCommunityClassEntity> getClassList() {
		return baseMapper.getClassList();
	}
	
	@Override
	public List<AdminCommunityClassEntity> simpleList() {
		return baseMapper.simpleList();
	}
	
	//删除版块(以及版块下的帖子,帖子的评论)
	@Override
	public int deleteClass(Integer classId) {
		List<Long> communityIds = baseMapper.getCommunityIds(classId);
		for (long communityId: communityIds) {
			adminCommunityDao.deleteCommentByCommunityId(communityId);//删除对应评论
			adminCommunityDao.deleteCommunity(communityId);//删除帖子
		}
		return baseMapper.deleteClass(classId);//删除版块
	}

	//新增版块
	@Override
	public int addCommunityClass(AdminCommunityClassEntity communityClass) {
		return baseMapper.addCommunityClass(communityClass);
	}

	//查看版块
	@Override
	public AdminCommunityClassEntity selectCommunityClassById(Integer classId) {
		return baseMapper.selectCommunityClassById(classId);
	}
	//编辑版块
	@Override
	public int updateClassById(AdminCommunityClassEntity communityClass) {
		return baseMapper.updateClassById(communityClass);
	}
}





