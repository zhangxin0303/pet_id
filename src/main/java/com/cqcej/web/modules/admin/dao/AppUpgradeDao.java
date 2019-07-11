package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AppUpgradeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-06 16:05:40
 */
@Mapper
public interface AppUpgradeDao extends BaseMapper<AppUpgradeEntity> {
	/**
	 * 查询新版本
	 *
	 * @param currentVersion 当前版本
	 */
	AppUpgradeEntity findNewVersion(String currentVersion);
}
