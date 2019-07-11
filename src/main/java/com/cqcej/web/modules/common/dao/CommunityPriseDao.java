package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.CommunityPriseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 帖子点赞记录
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-06-14 16:16:54
 */
@Mapper
public interface CommunityPriseDao extends BaseMapper<CommunityPriseEntity> {
	
	void prise(@Param("communityId") Long communityId, @Param("isPrise") boolean isPrise);
}
