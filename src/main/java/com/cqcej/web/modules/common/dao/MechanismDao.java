package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.MechanismEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 机构，包含诊所，美容院
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-20 11:10:15
 */
@Mapper
public interface MechanismDao extends BaseMapper<MechanismEntity> {

}
