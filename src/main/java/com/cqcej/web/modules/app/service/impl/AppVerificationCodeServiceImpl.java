package com.cqcej.web.modules.app.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.DateUtils;
import com.cqcej.web.modules.app.dao.VerificationCodeDao;
import com.cqcej.web.modules.app.entity.VerificationCodeEntity;
import com.cqcej.web.modules.app.service.AppVerificationCodeService;
import org.springframework.stereotype.Service;


@Service("verificationCodeService")
public class AppVerificationCodeServiceImpl extends ServiceImpl<VerificationCodeDao, VerificationCodeEntity> implements AppVerificationCodeService {
	
	@Override
	public VerificationCodeEntity queryByCode(String code) {
		return baseMapper.queryByCode(code);
	}
	
	@Override
	public VerificationCodeEntity queryByAccount(String account) {
		return baseMapper.queryByAccount(account);
	}
	
	@Override
	public void clearOutOfDateCode() {
		baseMapper.clearOutOfDateCode(DateUtils.currentTime());
	}
}
