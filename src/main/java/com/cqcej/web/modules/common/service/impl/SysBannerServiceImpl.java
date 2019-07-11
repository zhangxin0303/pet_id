package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.common.utils.Query;
import com.cqcej.web.modules.common.dao.SysBannerDao;
import com.cqcej.web.modules.common.entity.SysBannerEntity;
import com.cqcej.web.modules.common.service.SysBannerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysBannerService")
public class SysBannerServiceImpl extends ServiceImpl<SysBannerDao, SysBannerEntity> implements SysBannerService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<SysBannerEntity> page = this.selectPage(
				new Query<SysBannerEntity>(params).getPage(),
				new EntityWrapper<>()
		);

		return new PageUtils(page);
	}
	
	@Override
	public List<SysBannerEntity> getBannerByPosition(int position) {
		EntityWrapper<SysBannerEntity> wrapper = new EntityWrapper<>();
		wrapper.where("position={0}", position);
		wrapper.orderBy("sort", false);
		return selectList(wrapper);
	}
	
}
