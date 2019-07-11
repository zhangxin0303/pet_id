package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.BankEntity;

/**
 * 银行
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 14:53:37
 */
public interface BankService extends IService<BankEntity> {
	
	Integer queryBank(final String bankName);
}

