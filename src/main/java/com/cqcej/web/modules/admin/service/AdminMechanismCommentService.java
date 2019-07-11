package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminMechanismCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminMechanismCommentResultEntity;
import com.cqcej.web.modules.admin.entity.AdminShopOrderCommentEntity;

/**
 * 机构评价
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-16 11:08:31
 */
public interface AdminMechanismCommentService extends IService<AdminMechanismCommentEntity> {

	//PageUtils queryPage(Map<String, Object> params);

    PageUtils<AdminMechanismCommentResultEntity> getMechanismCommList(Integer mechanismType, String mechanismName, Integer page, Integer size);

    PageUtils<AdminShopOrderCommentEntity> getShopOrderList(Integer mechanismId, String commentLevel, Integer page, Integer size);
}

