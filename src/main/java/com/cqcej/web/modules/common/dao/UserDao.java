package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-13 15:46:48
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
