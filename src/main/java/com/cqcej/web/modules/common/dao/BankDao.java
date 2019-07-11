package com.cqcej.web.modules.common.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.common.entity.BankEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-16 14:53:37
 */
@Mapper
public interface BankDao extends BaseMapper<BankEntity> {
	
}
