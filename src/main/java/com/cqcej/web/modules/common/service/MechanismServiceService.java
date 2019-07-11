package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.MechanismServiceEntity;

import java.util.Map;

/**
 * 机构服务中间表
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
public interface MechanismServiceService extends IService<MechanismServiceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

