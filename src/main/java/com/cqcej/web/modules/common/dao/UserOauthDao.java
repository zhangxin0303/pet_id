package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.UserOauthEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 第三方登录
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-12 11:07:27
 */
@Mapper
public interface UserOauthDao extends BaseMapper<UserOauthEntity> {
	
}
