package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.app.entity.AppUserBankCardEntity;
import com.cqcej.web.modules.common.entity.UserBankCardEntity;

import java.util.List;

/**
 * 已绑定银行卡
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 14:53:37
 */
public interface UserBankCardService extends IService<UserBankCardEntity> {

	List<AppUserBankCardEntity> getBindBank(long userId);
	
	Boolean unBindBank(long userId, Long cardId);
	
	boolean checkIsBind(long userId, String cardNo);
}

