package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminCommunityCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminCommunityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社区
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-19 13:36:15
 */
@Mapper
public interface AdminCommunityDao extends BaseMapper<AdminCommunityEntity> {

    //帖子总数
    int getCommunityCount(@Param("classId")Integer classId,@Param("title") String title);

    //帖子列表
    List<AdminCommunityEntity> getCommunitylist(@Param("classId")Integer classId,@Param("title")String title, @Param("start")Integer start, @Param("size")Integer size);

    //帖子置顶
    int top(@Param("communityId")Long communityId,@Param("isTop")Integer isTop);

    //删除帖子
    int deleteCommunity(@Param("communityId")Long communityId);

    //删除评论
    void deleteCommentByCommunityId(@Param("communityId")Long communityId);

    //查询帖子
    AdminCommunityEntity selectById(@Param("communityId")Long communityId);

    //查询帖子所有评论
    List<AdminCommunityCommentEntity> getCommList(@Param("communityId")Long communityId,@Param("start")Integer start,@Param("size")Integer size);

    //查询评论总数
    int getCommCount(@Param("communityId")Long communityId);

    //删除评论
    int deleteCommunityComment(Long commentId);

    //新增主题(帖子)
    int saveCommunity(AdminCommunityEntity community);

    //修改帖子
    int updateCommunity(@Param("communityId")Long communityId,@Param("context")String context);

    //查看主题
    AdminCommunityEntity view(@Param("communityId")Long communityId);
}
