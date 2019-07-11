package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.CommunityClassEntity;

import java.util.Map;

/**
 * 社区分类
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
public interface CommunityClassService extends IService<CommunityClassEntity> {

	PageUtils queryPage(Map<String, Object> params);
}

