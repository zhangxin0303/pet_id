package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.BankDao;
import com.cqcej.web.modules.common.entity.BankEntity;
import com.cqcej.web.modules.common.service.BankService;
import org.springframework.stereotype.Service;


@Service("bankService")
public class BankServiceImpl extends ServiceImpl<BankDao, BankEntity> implements BankService {
	
	@Override
	public Integer queryBank(final String bankName) {
		EntityWrapper<BankEntity> where = new EntityWrapper<>();
		where.where("bank_name={0}", bankName);
		BankEntity entity = selectOne(where);
		
		if (entity != null) {
			return entity.getBankId();
		} else {
			BankEntity bank = new BankEntity();
			bank.setBankName(bankName);
			return baseMapper.insert(bank);
		}
	}
}
