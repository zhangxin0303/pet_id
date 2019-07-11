package com.cqcej.web.modules.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cqcej.web.modules.admin.entity.AdminBankCardEntity;
import com.cqcej.web.modules.admin.entity.AdminMechanismEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DetailEntity;
import com.cqcej.web.modules.admin.entity.mechanism.DoctorEntity;
import com.cqcej.web.modules.admin.entity.mechanism.MechanBasicEntity;
import com.cqcej.web.modules.admin.entity.mechanism.ServiceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 机构，包含诊所，美容院
 *
 * @author Jia Min
 * @email empty
 * @date 2018-07-23 10:36:31
 */
@Mapper
public interface AdminMechanismDao extends BaseMapper<AdminMechanismEntity> {

    //商户列表信息
    List<AdminMechanismEntity> getMechanismList(@Param("mechanismType")Integer mechanismType,
                                                @Param("mechanismStatus")Integer mechanismStatus,
                                                @Param("mechanismName")String mechanismName,
                                                @Param("start")Integer start,
                                                @Param("size")Integer size);
    
    //商户count
    int getMechanismCount(@Param("mechanismType")Integer mechanismType,
                          @Param("status")Integer status,
                          @Param("mechanismName")String mechanismName);
    
    //店内医师
    List<DoctorEntity> doctorList(@Param("start")Integer start, @Param("size")Integer size,@Param("mechanismId")Long mechanismId);
    
    //店内医师count
    int doctorListCount(@Param("mechanismId")Long mechanismId);

    //店内服务
    List<ServiceEntity> serviceList(@Param("start")Integer start, @Param("size")Integer size,@Param("mechanismId")Long mechanismId);
    
    //店内服务count
    int serviceListCount(@Param("mechanismId")Long mechanismId);
    
    //修改服务信息
    int updateService(ServiceEntity service);
    
    //update医师
    int updateDoctor(DoctorEntity doctor);
    
    //锁定商户
    int lockById(@Param("mechanismId")Long mechanismId);

    //删除商户
    int deleteMechanismById(@Param("mechanismId")Long mechanismId);
    
    //基本信息
    MechanBasicEntity selectBasicMessage(@Param("mechanismId")Long mechanismId);
    
    //银行卡信息
    List<AdminBankCardEntity> selectBankCardMsg(@Param("mechanismId")Long mechanismId);
    
    //详情
    DetailEntity selectDetailMessage(@Param("mechanismId")Long mechanismId);
    
    //图片
    List<String> selectImages(@Param("mechanismId")Long mechanismId);
    
    //服务范围
    List<Integer> selectServiceTypes(@Param("mechanismId")Long mechanismId);
    
    
//    //店内服务
//    List<ServiceEntity> selectServiceMessage(@Param("mechanismId")Long mechanismId);
    
    //修改基本信息//修改详细信息
    int updateBasic(Map<String,Object> map);
    
    //添加轮播图
    void updateImages(@Param("list") List<String> list,@Param("mechanismId")Long mechanismId);
//    //修改详细信息
//    void updateDetail(DetailEntity detail);
    
    //删除轮播图
    int deleteImage(@Param("imageUrl") String imageUrl);
}

















