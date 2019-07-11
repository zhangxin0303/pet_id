package com.cqcej.web.modules.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.utils.PageUtils;
import com.cqcej.web.modules.admin.dao.AdminMessageDao;
import com.cqcej.web.modules.admin.entity.AdminMessageEntity;
import com.cqcej.web.modules.admin.service.AdminMessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("adminMessageService")
public class AdminMessageServiceImpl extends ServiceImpl<AdminMessageDao, AdminMessageEntity> implements AdminMessageService {
	
	//消息列表
	@Override
	public PageUtils<AdminMessageEntity> list(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());
		int start = (page - 1) * size;
		params.put("start",start);
		params.put("size",size);
		List<AdminMessageEntity> list = baseMapper.getList(params);
		int count = baseMapper.getListCount(params);
		PageUtils<AdminMessageEntity> pageUtils = new PageUtils(list,count,page,size);
		return pageUtils;
	}
	
	//删除消息(批量or单独)
	@Override
	public int deleteMessage(List<Long> messageIds) {
		return baseMapper.deleteMessage(messageIds);
	}
	
	//查看
	@Override
	public AdminMessageEntity getMessageById(Long messageId) {
		return baseMapper.getMessageById(messageId);
	}
	
	//群发消息
	@Override
	public int saveMessage(AdminMessageEntity message) {
		//消息类型为null直接结束方法
		if(message.getMessageType() == null){
			return 0;
		}
		//添加群发消息记录
		int result = baseMapper.saveMessage(message);
		List<Long> userIds = new ArrayList<>();
		//商家消息
		if(AdminMessageEntity.MESSAGE_TYPE_MECHANISM == message.getMessageType()){
			userIds = baseMapper.selectMechanismIds();
		}
		//用户消息
		if(AdminMessageEntity.MESSAGE_TYPE_USER == message.getMessageType()){
			userIds = baseMapper.selectUserIds();
		}
		//接送员消息
		if(AdminMessageEntity.MESSAGE_TYPE_PICKUP == message.getMessageType()){
			userIds = baseMapper.selectPickupIds();
		}
		//系统消息
		if(AdminMessageEntity.MESSAGE_TYPE_SYSTEM == message.getMessageType()){
			userIds = baseMapper.selectSystemIds();
		}
		//添加消息记录到中间表
		if(userIds.size() > 0){
			baseMapper.saveMessageToUser(userIds,message.getMessageId());
		}
		return result;
	}
}
























