package com.cqcej.web.modules.app.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.validator.Assert;
import com.cqcej.web.modules.app.dao.user.AppUserDao;
import com.cqcej.web.modules.app.entity.dto.MechUserCardsDTO;
import com.cqcej.web.modules.app.entity.user.AppUserEntity;
import com.cqcej.web.modules.app.form.LoginForm;
import com.cqcej.web.modules.app.service.AppUserService;
import com.cqcej.web.modules.app.utils.AppConstant;
import com.cqcej.web.modules.common.entity.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("appUserService")
public class AppUserServiceImpl extends ServiceImpl<AppUserDao, UserEntity> implements AppUserService {
    @Override
    public UserEntity queryByMobileOrEmail(String account) {
        return baseMapper.queryByMobileOrEmail(account);
    }

    @Override
    public AppUserEntity login(LoginForm form) {
        UserEntity user = queryByMobileOrEmail(form.getAccount());
        Assert.isNull(user, "没有注册", AppConstant.ErrorCode.ERROR_NOT_REGISTER);

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
            throw new CTException("手机号或密码错误", AppConstant.ErrorCode.ERROR_ACCOUNT_OR_PASSWORD_WRONG);
        }

        return getUserInfo(user.getUserId());
    }

    @Override
    public AppUserEntity getUserInfo(long userId) {
        return baseMapper.getUserInfo(userId);
    }

    @Override
    public boolean saveUserNickname(long userId, String nickname) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setNickname(nickname);
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public boolean saveSex(long userId, Integer sex) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setSex(sex);
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public boolean saveSignature(long userId, String signature) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setSignature(signature);
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public boolean saveCity(long userId, Integer provinceId, Integer cityId, Integer areaId) {
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setProvinceId(provinceId);
        user.setCityId(cityId);
        user.setAreaId(areaId);
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public boolean bindMobile(long userId, String mobile) {
        UserEntity user = new UserEntity();
        user.setMobile(mobile);
        user.setUserId(userId);
        return updateById(user);
    }

    @Override
    public List<MechUserCardsDTO> selectCardList(Long userId) {
        return baseMapper.selectCardList(userId);
    }

    @Override
    public boolean isWatch(Long userId, Long targetId) {
        return baseMapper.isWatch(userId, targetId) == null ? false : true;
    }

    @Override
    public boolean isSign(Long userId) {
        return baseMapper.isSign(userId) > 0 ? true : false;
    }
}
