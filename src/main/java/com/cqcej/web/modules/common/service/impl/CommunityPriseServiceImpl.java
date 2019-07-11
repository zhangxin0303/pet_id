package com.cqcej.web.modules.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.modules.common.dao.CommunityPriseDao;
import com.cqcej.web.modules.common.entity.CommunityPriseEntity;
import com.cqcej.web.modules.common.service.CommunityPriseService;
import org.springframework.stereotype.Service;


@Service("communityPriseService")
public class CommunityPriseServiceImpl extends ServiceImpl<CommunityPriseDao, CommunityPriseEntity> implements CommunityPriseService {

	@Override
	public boolean priseCommunity(long userId, Long communityId) {
		CommunityPriseEntity where = new CommunityPriseEntity();
		where.setCommunityId(communityId);
		where.setUserId(userId);
		
		CommunityPriseEntity isPrised = baseMapper.selectOne(where);
		if (isPrised == null) {
			// 赞
			baseMapper.insert(where);
			baseMapper.prise(communityId, true);
			return true;
		} else {
			// 取消赞
			baseMapper.deleteById(isPrised.getPriseId());
			baseMapper.prise(communityId, false);
			return false;
		}
	}
}
