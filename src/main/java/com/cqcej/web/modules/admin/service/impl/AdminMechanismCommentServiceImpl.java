package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminMechanismCommentDao;
import com.cqcej.web.modules.admin.entity.AdminMechanismCommentEntity;
import com.cqcej.web.modules.admin.entity.AdminMechanismCommentResultEntity;
import com.cqcej.web.modules.admin.entity.AdminShopOrderCommentEntity;
import com.cqcej.web.modules.admin.service.AdminMechanismCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("adminMechanismCommentService")
public class AdminMechanismCommentServiceImpl extends ServiceImpl<AdminMechanismCommentDao, AdminMechanismCommentEntity> implements AdminMechanismCommentService {

	@Autowired
	private AdminMechanismCommentDao adminMechanismCommentDao;

//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		Page<MechanismCommentEntity> page = this.selectPage(
//			new Query<MechanismCommentEntity>(params).getPage(),
//				new EntityWrapper<MechanismCommentEntity>()
//		);
//
//		return new PageUtils(page);
//	}

	//商户评价
	@Override
	public PageUtils<AdminMechanismCommentResultEntity> getMechanismCommList(Integer mechanismType, String mechanismName, Integer page, Integer size) {
		int start = (page - 1) * size;
		List<AdminMechanismCommentResultEntity> list = baseMapper.getMechanismCommList(mechanismType,mechanismName,start,size);
        int count = baseMapper.getMechanismCommCount(mechanismType,mechanismName);
		PageUtils<AdminMechanismCommentResultEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
    //商户评价详情(查看)
	@Override
	public PageUtils<AdminShopOrderCommentEntity> getShopOrderList(Integer mechanismId, String commentLevel,Integer page, Integer size) {
        int start = (page - 1) * size;
        List<AdminShopOrderCommentEntity> list = baseMapper.getShopOrderList(mechanismId,commentLevel,start,size);
        int count = baseMapper.getShopOrderCount(mechanismId,commentLevel);
        PageUtils<AdminShopOrderCommentEntity> pageUtils = new PageUtils(list,count,page,size);
        return pageUtils;
	}
}
