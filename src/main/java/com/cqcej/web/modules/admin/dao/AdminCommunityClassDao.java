package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminCommunityClassEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社区分类
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:25
 */
@Mapper
public interface AdminCommunityClassDao extends BaseMapper<AdminCommunityClassEntity> {

    //版块列表
    List<AdminCommunityClassEntity> getClassList();
	
	//版块列表
	List<AdminCommunityClassEntity> simpleList();
	
    //查找版块下面的所有帖子
    List<Long> getCommunityIds(@Param("classId")Integer classId);

    //删除版块
    int deleteClass(@Param("classId")Integer classId);

    //新增版块
    int addCommunityClass(AdminCommunityClassEntity communityClass);

    //查看版块
    AdminCommunityClassEntity selectCommunityClassById(@Param("classId")Integer classId);

    //编辑版块
    int updateClassById(AdminCommunityClassEntity communityClass);
}
