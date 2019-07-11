package com.cqcej.web.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.entity.AdminAgreementEntity;

import java.util.List;
import java.util.Map;

/**
 * 协议
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-06 16:37:03
 */
public interface AdminAgreementService extends IService<AdminAgreementEntity> {


	List<AdminAgreementEntity> list();
	
	PageUtils<AdminAgreementEntity> mailList(Map<String, Object> params);
	
	AdminAgreementEntity getById(Long agreeId);
	
	int saveAgree(AdminAgreementEntity agree);
	
	int saveMail(AdminAgreementEntity agree);
	
	int updateAgree(AdminAgreementEntity agree);
	
	int deleteAgree(Long agreeId);
}

