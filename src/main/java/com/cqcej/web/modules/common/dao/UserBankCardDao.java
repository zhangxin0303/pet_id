package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.app.entity.AppUserBankCardEntity;
import com.cqcej.web.modules.common.entity.UserBankCardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 已绑定银行卡
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 14:53:37
 */
@Mapper
public interface UserBankCardDao extends BaseMapper<UserBankCardEntity> {
	
	List<AppUserBankCardEntity> getBindBank(long userId);
}
