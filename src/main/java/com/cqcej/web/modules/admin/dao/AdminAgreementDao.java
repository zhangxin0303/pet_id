package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminAgreementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 协议
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-06 16:37:03
 */
@Mapper
public interface AdminAgreementDao extends BaseMapper<AdminAgreementEntity> {

	List<AdminAgreementEntity> list();
	
	List<AdminAgreementEntity> getMailList(Map<String,Object> params);
	
	Integer getMailListCount();
	
	AdminAgreementEntity getById(@Param("agreeId") Long agreeId);
	
	int saveAgree(AdminAgreementEntity agree);
	
	int saveMail(AdminAgreementEntity agree);
	
	int updateAgree(AdminAgreementEntity agree);
	
	int deleteAgree(@Param("agreeId") Long agreeId);
}
