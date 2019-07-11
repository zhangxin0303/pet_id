package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 消息
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-08-27 11:37:02
 */
@Mapper
public interface AdminMessageDao extends BaseMapper<AdminMessageEntity> {
	
	List<AdminMessageEntity> getList(Map<String,Object> map);
	
	int getListCount(Map<String,Object> map);
	
	int deleteMessage(List<Long> messageIds);
	
	AdminMessageEntity getMessageById(@Param("messageId") Long messageId);
	
	int saveMessage(AdminMessageEntity message);
	
	List<Long> selectMechanismIds();
	
	List<Long> selectUserIds();
	
	List<Long> selectPickupIds();
	
	List<Long> selectSystemIds();
	
	void saveMessageToUser(@Param("ids") List<Long> userIds,@Param("messageId")Long messageId);
}
