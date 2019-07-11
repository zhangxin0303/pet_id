package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminPetClassEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 宠物分类
 *
 * @author Jia Min
 * @email Empty
 * @date 2018-09-05 09:45:00
 */
@Mapper
public interface AdminPetClassDao extends BaseMapper<AdminPetClassEntity> {
	
	//宠物分类信息
	List<AdminPetClassEntity> list();
	
	//分类下所有的宠物信息
	List<AdminPetClassEntity> getPets(@Param("petClassId") Integer petClassId);
	
	AdminPetClassEntity info(@Param("petClassId") Integer petClassId);
	
	//添加种类
	int save(@Param("className")String className);
	
	//添加宠物
	int savePet(@Param("parentId")Integer parentId,@Param("firstLetter")String firstLetter,@Param("className")String className);
	
	//修改宠物
	int updatePet(@Param("petClassId")Integer petClassId,@Param("firstLetter")String firstLetter,@Param("className")String className);
	
	//删除宠物
	int deletePet(@Param("petClassId")Integer petClassId);
}
