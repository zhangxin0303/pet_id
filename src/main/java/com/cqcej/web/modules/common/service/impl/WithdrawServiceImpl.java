package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.WithdrawDao;
import com.cqcej.web.modules.common.entity.WithdrawEntity;
import com.cqcej.web.modules.common.service.WithdrawService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("withdrawService")
public class WithdrawServiceImpl extends ServiceImpl<WithdrawDao, WithdrawEntity> implements WithdrawService {
	@Override
	public List<WithdrawEntity> getWithdrawList(long userId) {
		EntityWrapper<WithdrawEntity> where = new EntityWrapper<>();
		where.where("user_id={0}", userId);
		return selectList(where);
	}
}
