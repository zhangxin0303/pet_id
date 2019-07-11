package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.CommunityAttachEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 社区发帖附件，包括图片，视频等
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-30 16:30:26
 */
@Mapper
public interface CommunityAttachDao extends BaseMapper<CommunityAttachEntity> {
	
}
