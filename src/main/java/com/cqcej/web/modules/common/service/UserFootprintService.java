package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.UserFootprintEntity;

import java.util.Map;

/**
 * 
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-31 15:59:32
 */
public interface UserFootprintService extends IService<UserFootprintEntity> {

	PageUtils queryPage(Map<String, Object> params);
}

