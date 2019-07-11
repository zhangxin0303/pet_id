package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.modules.common.entity.WithdrawEntity;

import java.util.List;

/**
 * 
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-15 15:16:39
 */
public interface WithdrawService extends IService<WithdrawEntity> {

	List<WithdrawEntity> getWithdrawList(long userId);
}

