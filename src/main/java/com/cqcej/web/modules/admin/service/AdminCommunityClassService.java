package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminCommunityClassEntity;

import java.util.List;
import java.util.Map;

/**
 * 社区分类
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:25
 */
public interface AdminCommunityClassService extends IService<AdminCommunityClassEntity> {

	PageUtils queryPage(Map<String, Object> params);

	//版块列表
	List<AdminCommunityClassEntity> getClassList();
	
	//版块列表
	List<AdminCommunityClassEntity> simpleList();

	
	//删除版块
	int deleteClass(Integer classId);

	//新增版块
	int addCommunityClass(AdminCommunityClassEntity communityClass);

	//查看版块
	AdminCommunityClassEntity selectCommunityClassById(Integer classId);

	//编辑版块
	int updateClassById(AdminCommunityClassEntity communityClass);
}

