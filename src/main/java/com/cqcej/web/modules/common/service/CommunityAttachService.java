package com.cqcej.web.modules.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.common.entity.CommunityAttachEntity;

import java.util.Map;

/**
 * 社区发帖附件，包括图片，视频等
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
public interface CommunityAttachService extends IService<CommunityAttachEntity> {

	PageUtils queryPage(Map<String, Object> params);
}

