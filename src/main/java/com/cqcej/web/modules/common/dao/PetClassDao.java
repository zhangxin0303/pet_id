package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.PetClassEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 宠物分类
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-07 10:07:20
 */
@Mapper
public interface PetClassDao extends BaseMapper<PetClassEntity> {
	
}
