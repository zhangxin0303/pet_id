package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.app.entity.AppUserBankCardEntity;
import com.cqcej.web.modules.common.dao.UserBankCardDao;
import com.cqcej.web.modules.common.entity.UserBankCardEntity;
import com.cqcej.web.modules.common.service.UserBankCardService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userBankCardService")
public class UserBankCardServiceImpl extends ServiceImpl<UserBankCardDao, UserBankCardEntity> implements UserBankCardService {
	
	@Override
	public List<AppUserBankCardEntity> getBindBank(long userId) {
		return baseMapper.getBindBank(userId);
	}
	
	@Override
	public Boolean unBindBank(long userId, Long cardId) {
		EntityWrapper<UserBankCardEntity> where = new EntityWrapper<>();
		where.where("user_id={0} and bank_id={1}", userId, cardId);
		return baseMapper.delete(where) > 0;
	}
	
	@Override
	public boolean checkIsBind(long userId, String cardNo) {
		UserBankCardEntity where = new UserBankCardEntity();
		where.setUserId(userId);
		where.setBankNo(cardNo);
		return baseMapper.selectOne(where) != null;
	}
}
