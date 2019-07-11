package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminServiceOrderEntity;
import com.cqcej.web.modules.admin.entity.order.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 服务订单
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-24 14:09:29
 */
@Mapper
public interface AdminServiceOrderDao extends BaseMapper<AdminServiceOrderEntity> {

    List<AdminServiceOrderEntity> getServiceOrderList(Map<String,Object> params);

    int getServiceOrderCount(Map<String,Object> params);

    AdminServiceOrderEntity selectOrderById(@Param("orderId") Long orderId);

    BasicEntity selectBasicMessage(@Param("orderId")Long orderId);//-基本信息

    WorkmanEntity selectDoctorMessage(@Param("orderId")Long orderId);//-医师信息

    BuyerEntity selectBuyerMessage(@Param("orderId")Long orderId);//-买家信息

    List<IllnessEntity> selectIllnessMessage(@Param("orderId")Long orderId);//-病情信息

    PetEntity selectPetMessage(@Param("orderId")Long orderId);//-宠物信息

    MechanEntity selectMechanismMessage(@Param("orderId")Long orderId);//商家信息

    PickUpEntity selectPickUpMessage(@Param("orderId")Long orderId);//接送人员信息

    PickUpEntity selectGiveBackMessage(@Param("orderId")Long orderId);//归还人员信息

    WorkmanEntity selectWalkDogMessage(@Param("orderId")Long orderId);//遛狗人员信息

    List<OrderStatisticsEntity> statistics(Map<String,Object> params);//订单统计

    Integer getStatisticsCount(Map<String,Object> params);//订单统计总数
    
    OrderStatisticsEntity platformStats();//平台收入
    
    OrderStatisticsEntity medicalStats();//医疗分成
    
    OrderStatisticsEntity beautyStats();//美容
    
    OrderStatisticsEntity healthStats();//健康
}
