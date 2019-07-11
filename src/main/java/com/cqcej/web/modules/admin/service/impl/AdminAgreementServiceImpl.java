package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminAgreementDao;
import com.cqcej.web.modules.admin.entity.AdminAgreementEntity;
import com.cqcej.web.modules.admin.service.AdminAgreementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("adminAgreementService")
public class AdminAgreementServiceImpl extends ServiceImpl<AdminAgreementDao, AdminAgreementEntity> implements AdminAgreementService {
	
	
	@Override
	public List<AdminAgreementEntity> list() {
		return baseMapper.list();
	}
	
	@Override
	public PageUtils<AdminAgreementEntity> mailList(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());
		int start = (page - 1) * size;
		params.put("start",start);
		params.put("size",size);
		List<AdminAgreementEntity> list = baseMapper.getMailList(params);
		int count = baseMapper.getMailListCount();
		PageUtils<AdminAgreementEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	
	@Override
	public AdminAgreementEntity getById(Long agreeId) {
		return baseMapper.getById(agreeId);
	}
	
	@Override
	public int saveAgree(AdminAgreementEntity agree) {
		return baseMapper.saveAgree(agree);
	}
	
	@Override
	public int saveMail(AdminAgreementEntity agree) {
		return baseMapper.saveMail(agree);
	}
	
	@Override
	public int updateAgree(AdminAgreementEntity agree) {
		return baseMapper.updateAgree(agree);
	}
	
	@Override
	public int deleteAgree(Long agreeId) {
		return baseMapper.deleteAgree(agreeId);
	}
}
