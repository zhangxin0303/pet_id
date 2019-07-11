package com.cqcej.web.modules.app.dao.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cqcej.web.modules.app.entity.dto.MechUserCardsDTO;
import com.cqcej.web.modules.app.entity.user.AppUserEntity;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.common.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-13 15:46:48
 */
@Mapper
public interface AppUserDao extends BaseMapper<UserEntity> {
	
	UserEntity queryByMobileOrEmail(String account);
	
	List<UserEntity> selectUserPage(AppPage<UserEntity> page, Wrapper<UserEntity> wrapper);
	
	AppUserEntity getUserInfo(long userId);
	
	List<MechUserCardsDTO> selectCardList(Long userId);
	
	Long isWatch(@Param("userId") Long userId,@Param("targetId") Long targetId);

	int isSign(Long userId);

}
