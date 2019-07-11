package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminAreaEntity;
import com.cqcej.web.modules.admin.entity.AdminUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 15:19:56
 */
@Mapper
public interface AdminUserDao extends BaseMapper<AdminUserEntity> {

    //用户列表
    List<AdminUserEntity> getUserList(@Param("forbiddenComment") Integer forbiddenComment,
                                      @Param("userId")Long userId,
                                      @Param("nickname")String nickname,
                                      @Param("start")Integer start,
                                      @Param("size")Integer size);

    //用户总数

    int getUserCount(@Param("forbiddenComment") Integer forbiddenComment,
                     @Param("userId")Long userId,
                     @Param("nickname")String nickname);

    //查看user信息
    AdminUserEntity selectUserById(@Param("userId")Long userId);

    //删除user
    int deleteUserById(@Param("userId")Long userId);

    //修改用户信息
    int updateUserById(AdminUserEntity user);

    //查询省份信息
    List<AdminAreaEntity> selectProvinces();

    //查询城市
    List<AdminAreaEntity> selectCity(@Param("provinceId")Integer provinceId);

    //查询区县
    List<AdminAreaEntity> selectArea(@Param("cityId")Integer cityId);
}
