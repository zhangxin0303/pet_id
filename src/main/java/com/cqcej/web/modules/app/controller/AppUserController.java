package com.cqcej.web.modules.app.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.utils.ConfigConstant;
import com.cqcej.web.common.utils.ImgFileUtil;
import com.cqcej.web.common.validator.ValidatorUtils;
import com.cqcej.web.modules.admin.service.ConfigService;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.annotation.LoginUser;
import com.cqcej.web.modules.app.entity.*;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.entity.user.AppUserEntity;
import com.cqcej.web.modules.app.form.ChangePasswordForm;
import com.cqcej.web.modules.app.form.UserFormWithCode;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.*;
import com.cqcej.web.modules.app.utils.AppPage;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.*;
import com.cqcej.web.modules.common.service.*;
import com.cqcej.web.modules.oss.cloud.CloudStorageConfig;
import io.swagger.annotations.*;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-04-10 14:48
 */
@Login
@RestController
@RequestMapping("/app/user")
@Api(description = "App用户个人中心")
public class AppUserController {
    private final AppUserService appUserService;
    private final AppVerificationCodeService appVerificationCodeServiceService;
    private final AppUserFavoriteService appUserFavoriteService;
    private final AppUserPriseService appUserPriseService;
    private final AppMechanismCommentService mechanismCommentService;
    private final UserBlacklistService blacklistService;
    private final UserPetsService petsService;
    private final UserAddressService addressService;
    private final AppUserFootprintService footprintService;
    private final UserService userService;
    private final AppCommunityService appCommunityService;
    private final UserWatchService userWatchService;
    private ConfigService configService;
    private final AppWorkerService appWorkerService;
    private final AppMechanismService appMechanismService;
    private final SysSettingService sysSettingService;
    private final UserSignService userSignService;

    @Autowired
    public AppUserController(AppUserService appUserService, AppVerificationCodeService codeService,
                             AppUserFavoriteService appUserFavoriteService, AppUserPriseService appUserPriseService,
                             AppMechanismCommentService mechanismCommentService, UserBlacklistService userBlacklistService,
                             UserPetsService userPetsService, UserAddressService addressService,
                             AppUserFootprintService appUserFootprintService,
                             UserService userService, AppCommunityService appCommunityService,
                             UserWatchService userWatchService, ConfigService configService,
                             AppWorkerService appWorkerService, AppMechanismService appMechanismService,
                             SysSettingService sysSettingService, UserSignService userSignService) {
        this.appUserService = appUserService;
        this.appVerificationCodeServiceService = codeService;
        this.appUserFavoriteService = appUserFavoriteService;
        this.appUserPriseService = appUserPriseService;
        this.mechanismCommentService = mechanismCommentService;
        this.blacklistService = userBlacklistService;
        this.petsService = userPetsService;
        this.addressService = addressService;
        this.footprintService = appUserFootprintService;
        this.userService = userService;
        this.appCommunityService = appCommunityService;
        this.userWatchService = userWatchService;
        this.configService = configService;
        this.appWorkerService = appWorkerService;
        this.appMechanismService = appMechanismService;
        this.sysSettingService = sysSettingService;
        this.userSignService = userSignService;
    }

    @PostMapping("/change/password")
    @ApiOperation("修改密码")
    public BaseResponse<Boolean> register(@RequestBody UserFormWithCode form, @ApiIgnore @LoginUser UserEntity user) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);

        String authCode = form.getCode();
        boolean verify = verifyCode(authCode, form.getAccount());
        if (!verify) {
            return BaseResponse.error("手机验证码错误或已过期");
        }

        UserEntity userIsExists = appUserService.queryByMobileOrEmail(form.getAccount());
        if (userIsExists == null) {
            // 已有会员
            return BaseResponse.error("您的账号没有注册");
        }

        // 删除验证码记录
        Map<String, Object> where = new HashMap<>();
        where.put("code", form.getCode());
        appVerificationCodeServiceService.deleteByMap(where);

        // 修改密码
        UserEntity changePassword = new UserEntity();
        changePassword.setUserId(user.getUserId());
        changePassword.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        appUserService.updateById(changePassword);

        return BaseResponse.success();
    }

    /**
     * 验证手机验证码
     *
     * @return boolean
     */
    private boolean verifyCode(String code, String account) {
        VerificationCodeEntity entity = appVerificationCodeServiceService.queryByCode(code);
        return entity != null && entity.getAccount().equals(account) && (entity.getExpireTime().getTime() > new Date().getTime());
    }

    /**
     * 收藏诊所列表
     */
    @GetMapping("/favorite/clinic")
    @ApiOperation("收藏诊所")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<AppPage<UserFavoriteEntity<AppMechanismEntity>>> getFavoriteClinic(HttpServletRequest request, int page, int size, double longitude, double latitude) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppPage<UserFavoriteEntity<AppMechanismEntity>> appPage = appUserFavoriteService.getFavoriteClinic(userId, page, size, longitude, latitude);
        return new BaseResponse<>(appPage);
    }

    /**
     * 收藏社区列表
     */
    @GetMapping("/favorite/community")
    @ApiOperation("收藏社区")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<AppPage<UserFavoriteEntity<AppCommunityEntity>>> getFavoriteCommunity(HttpServletRequest request, int page, int size, double longitude, double latitude) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppPage<UserFavoriteEntity<AppCommunityEntity>> appPage = appUserFavoriteService.getFavoriteCommunity(userId, page, size, longitude, latitude);
        return new BaseResponse<>(appPage);
    }

    /**
     * 收藏诊所列表
     */
    @PostMapping("/favorite")
    @ApiOperation("收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", value = "收藏对象ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "收藏类型，1诊所2美容院3帖子", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<Boolean> changeFavorite(HttpServletRequest request, long objectId, int type) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appUserFavoriteService.changeFavorite(userId, objectId, type);
        return new BaseResponse<>(result);
    }

    @DeleteMapping("/favorite")
    @ApiOperation("删除收藏")
    public BaseResponse<Boolean> deleteFavorite(HttpServletRequest request, Long favoriteId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appUserFavoriteService.deleteFavorite(userId, favoriteId);
        return new BaseResponse<>(result);
    }

    @GetMapping("/comment/prise")
    @ApiOperation("评论点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectId", value = "对象ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "点赞类型", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<Boolean> changeClinicCommentPrise(HttpServletRequest request, long objectId, int type) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean isPrised = appUserPriseService.isPrised(userId, objectId, type);
        if (isPrised) {
            // 删除点赞记录
            EntityWrapper<UserPriseEntity> wrapper = new EntityWrapper<>();
            wrapper.where("user_id={0} and object_id={1} and prise_type={2}", userId, objectId, type);
            appUserPriseService.delete(wrapper);
            changePriseCount(objectId, type, false);
        } else {
            // 添加收藏记录
            UserPriseEntity entity = new UserPriseEntity();
            entity.setUserId(userId);
            entity.setObjectId(objectId);
            entity.setPriseType(type);
            entity.setCreateAt(new Date());
            appUserPriseService.insert(entity);
            changePriseCount(objectId, type, true);
        }
        return new BaseResponse<>(!isPrised);
    }

    /**
     * 修改点赞/取消点赞后，对应的数量
     *
     * @param objectId 对象ID
     * @param type     点赞类型
     * @param isPrise  true 点赞/false 取消点赞
     */
    private void changePriseCount(long objectId, int type, boolean isPrise) {
        if (type == UserPriseEntity.PRISE_TYPE_CLINIC_COMMENT) {
            if (isPrise) {
                mechanismCommentService.addPriseCount(objectId);
            } else {
                mechanismCommentService.reducePriseCount(objectId);
            }
        }
    }

    @ApiModel("登录成功，用户登录信息")
    @Data
    private static class UserInfo {
        @ApiModelProperty("用户基本信息")
        private AppUserEntity user;

        @ApiModelProperty("工作人员")
        private WorkerEntity worker;

        @ApiModelProperty("医疗机构")
        private AppMechanismEntity mechanism;
    }

    @GetMapping("/info")
    @ApiOperation("用户信息")
    public BaseResponse<UserInfo> getUserInfo(HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();

        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppUserEntity user = appUserService.getUserInfo(userId);

        userInfo.setUser(user);
        if (user.getUserType() == UserEntity.USER_TYPE_DOCTOR || user.getUserType() == UserEntity.USER_TYPE_TRANSPORT || user.getUserType() == UserEntity.USER_TYPE_WORK_DOG) {
            // 获取workerID
            userInfo.setWorker(appWorkerService.getWorkerDetailWithUserId(user.getUserId()));
        } else if (user.getUserType() == UserEntity.USER_TYPE_MECHANISM) {
            userInfo.setMechanism(appMechanismService.getMechanismDetailWithUserId(user.getUserId()));
        }
        return new BaseResponse<>(userInfo);
    }

    @PostMapping("/save/nickname")
    @ApiOperation("修改用户昵称")
    @ApiImplicitParam(name = "nickname", value = "昵称", required = true, dataType = "string", paramType = "query")
    public BaseResponse<Boolean> saveNickname(HttpServletRequest request, String nickname) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appUserService.saveUserNickname(userId, nickname);
        this.addScoreByProfile(userId);//判断资料是否完成，完成就增加积分
        return new BaseResponse<>(result);
    }

    @PostMapping("/save/sex")
    @ApiOperation("修改用户性别")
    @ApiImplicitParam(name = "sex", value = "性别", allowableValues = "1, 2", required = true, dataType = "int", paramType = "query")
    public BaseResponse<Boolean> saveSex(HttpServletRequest request, Integer sex) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appUserService.saveSex(userId, sex);
        this.addScoreByProfile(userId);//判断资料是否完成，完成就增加积分
        return new BaseResponse<>(result);
    }

    @PostMapping("/save/signature")
    @ApiOperation("修改个性签名")
    @ApiImplicitParam(name = "signature", value = "个性签名", required = true, dataType = "string", paramType = "query")
    public BaseResponse<Boolean> saveSignature(HttpServletRequest request, String signature) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appUserService.saveSignature(userId, signature);
        this.addScoreByProfile(userId);//判断资料是否完成，完成就增加积分
        return new BaseResponse<>(result);
    }

    @PostMapping("/save/city")
    @ApiOperation("修改所在地区")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceId", value = "省", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "cityId", value = "市", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "areaId", value = "区", required = true, dataType = "int", paramType = "query")
    })
    public BaseResponse<Boolean> saveCity(HttpServletRequest request, Integer provinceId, Integer cityId, Integer areaId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = appUserService.saveCity(userId, provinceId, cityId, areaId);
        this.addScoreByProfile(userId);//判断资料是否完成，完成就增加积分
        return new BaseResponse<>(result);
    }

    @PostMapping("/save/avatar")
    @ApiOperation("修改头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "头像数据", required = true, dataType = "MultipartFile", paramType = "form")
    })
    public BaseResponse<String> saveAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile[] file) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        CloudStorageConfig config = configService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        UserEntity user = userService.selectById(userId);
        if (user.getAvatar() != null) {
            // 删除，不管是否成功
            ImgFileUtil.deleteCloud(user.getAvatar(), config);
        }

        //获取云配置信息
        try {
            String path = ImgFileUtil.uploadCloud(file, config);

            // 保存到用户表
            UserEntity saveAvatar = new UserEntity();
            saveAvatar.setUserId(userId);
            saveAvatar.setAvatar(path);
            userService.updateById(saveAvatar);
            this.addScoreByProfile(userId);//判断资料是否完成，完成就增加积分
            return new BaseResponse<>(path);
        } catch (IOException e) {
            throw new CTException("上传头像失败！");
        }
    }

    // TODO 删除原头像

    @GetMapping("/blacklist")
    @ApiOperation("用户黑名单")
    public BaseResponse<List<UserBlacklistEntity>> getUserBlacklist(HttpServletRequest request) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        List<UserBlacklistEntity> blacklist = blacklistService.getUserBlacklist(userId);
        return new BaseResponse<>(blacklist);
    }

    @DeleteMapping("/blacklist/{ids}")
    @ApiOperation("删除用户黑名单")
    @ApiImplicitParam(name = "ids", value = "要删除的黑名单ID(用,分隔)", dataType = "string", paramType = "path")
    public BaseResponse<Boolean> deleteUserBlacklist(HttpServletRequest request, @PathVariable("ids") String ids) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = blacklistService.deleteUserBlacklist(userId, ids);
        return new BaseResponse<>(result);
    }

    @DeleteMapping("/pet/{petId}")
    @ApiOperation("删除用户宠物")
    @ApiImplicitParam(name = "petId", value = "要删除的宠物ID", dataType = "long", paramType = "path")
    public BaseResponse<Boolean> deleteUserBlacklist(HttpServletRequest request, @PathVariable("petId") Long petId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = petsService.deletePetById(userId, petId);
        return new BaseResponse<>(result);
    }

    @PostMapping("/pet/{petId}")
    @ApiOperation("新增/编辑宠物，成功后返回对应的主键")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "petId", value = "宠物ID，如果为0表示新增", dataType = "long", paramType = "path")
    })
    public BaseResponse<Long> editPet(HttpServletRequest request, @RequestBody UserPetsEntity pet, @PathVariable("petId") Long petId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        pet.setCreateAt(new Date());
        pet.setUserId(userId);
        Long result = petsService.editPet(pet, petId);
        return new BaseResponse<>(result);
    }

    @GetMapping("address")
    @ApiOperation("用户收货地址列表")
    public BaseResponse<List<UserAddressEntity>> getUserAddress(HttpServletRequest request) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        List<UserAddressEntity> data = addressService.getUserAddress(userId);
        return new BaseResponse<>(data);
    }

    @PutMapping("address/default")
    @ApiOperation("修改用户默认收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", dataType = "long", paramType = "form"),
            @ApiImplicitParam(name = "isDefault", value = "是否默认，1默认，0不默认", dataType = "int", paramType = "form")
    })
    public BaseResponse<Boolean> changeUserAddressDefault(HttpServletRequest request, Long addressId, Integer isDefault) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = addressService.changeUserAddressDefault(userId, addressId, isDefault == 1);
        return new BaseResponse<>(result);
    }

    @DeleteMapping("address")
    @ApiOperation("删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", dataType = "long", paramType = "query")
    })
    public BaseResponse<Boolean> deleteUserAddressDefault(HttpServletRequest request, Long addressId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = addressService.deleteUserAddressDefault(userId, addressId);
        return new BaseResponse<>(result);
    }

    @PostMapping("address")
    @ApiOperation("编辑/添加收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", dataType = "long", paramType = "query")
    })
    public BaseResponse<Boolean> editUserAddress(HttpServletRequest request, @RequestBody UserAddressEntity addressEntity, Long addressId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = addressService.editUserAddress(userId, addressEntity, addressId);
        return new BaseResponse<>(result);
    }

    @GetMapping("footprint")
    @ApiOperation("用户足迹")
    public BaseResponse<List<AppUserFootprintEntity>> getUserFootprint(HttpServletRequest request) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        List<AppUserFootprintEntity> data = footprintService.getUserFootprint(userId);
        return new BaseResponse<>(data);
    }

    @DeleteMapping("footprint")
    @ApiOperation("清空用户足迹")
    public BaseResponse<Boolean> clearUserFootprint(HttpServletRequest request) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = footprintService.clearUserFootprint(userId);
        return new BaseResponse<>(result);
    }

    @GetMapping("/profile/{userId}")
    @ApiOperation("用户基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "long", paramType = "path")
    })
    @Login(allowAnonymous = true)
    public BaseResponse<UserEntity> getUserProfile(@PathVariable("userId") Long userId) {
        UserEntity user = userService.getUserProfile(userId);
        return new BaseResponse<>(user);
    }

    @GetMapping("/community/{userId}")
    @ApiOperation("用户帖子列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "targetId", value = "需要查看帖子的用户id", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "page", value = "第几页，从1开始", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页大小", defaultValue = "10", required = true, dataType = "int", paramType = "query")
    })
    @Login(allowAnonymous = true)
    public BaseResponse<AppPage<AppCommunityEntity>> getUserProfile(HttpServletRequest request, @PathVariable("userId") Long targetId, Integer page, Integer size) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        AppPage<AppCommunityEntity> data = appCommunityService.getUserCommunity(userId, targetId, page, size);
        return new BaseResponse<>(data);
    }

    @PutMapping("/watch/{targetId}")
    @ApiOperation("关注/取消关注用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "targetId", value = "需要关注/取消关注的用户ID", required = true, dataType = "long", paramType = "path")
    })
    public BaseResponse<Boolean> watchUser(HttpServletRequest request, @PathVariable("targetId") Long targetId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = userWatchService.watchUser(userId, targetId);
        return new BaseResponse<>(result);
    }

    @PostMapping("/change/password/origin")
    @ApiOperation("修改密码")
    public BaseResponse<Boolean> changePassword(HttpServletRequest request, @RequestBody ChangePasswordForm form) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = userService.changePassword(userId, form);
        return new BaseResponse<>(result);
    }

    @GetMapping("/account/statistics")
    @ApiOperation("账户统计")
    public BaseResponse<AccountStatisticsEntity> accountStatistics(HttpServletRequest request, @ApiIgnore @LoginUser UserEntity user) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        BigDecimal todayIncome = userService.getTodayIncome(userId);

        AccountStatisticsEntity result = new AccountStatisticsEntity(todayIncome, user.getTotalIncome(), user.getBalance());

        return new BaseResponse<>(result);
    }

    @PostMapping("/change/type")
    @ApiOperation("修改用户类型")
    @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "int", paramType = "form")
    public BaseResponse<Boolean> changeType(HttpServletRequest request, Integer type) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        boolean result = userService.changeType(userId, type);
        return new BaseResponse<>(result);
    }

    @GetMapping("/inited/im")
    @ApiOperation("修改用户是否初始化im")
    public BaseResponse<Boolean> initedIm(HttpServletRequest request) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);

        UserEntity update = new UserEntity();
        update.setInitIm(1);
        userService.update(update, new EntityWrapper<UserEntity>().eq("user_id", userId));

        return new BaseResponse<>(true);
    }

    @GetMapping("/pet")
    @ApiOperation("获取用户宠物列表")
    public BaseResponse<List<UserPetsEntity>> getUserPets(HttpServletRequest request) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);

        EntityWrapper<UserPetsEntity> where = new EntityWrapper<>();
        where.where("user_id={0}", userId);
        List<UserPetsEntity> result = petsService.selectList(where);

        return new BaseResponse<>(result);
    }

    //-------------------------------------------------商家端(jm)-----------------------------------------------------
    @GetMapping("/iswatch/{targetId}")
    @ApiOperation("是否关注")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name = "targetId", value = "关注用户的ID", required = true, dataType = "long", paramType = "path")
    })
    public BaseResponse<Boolean> getUserPets(HttpServletRequest request, @PathVariable Long targetId) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        return new BaseResponse(appUserService.isWatch(userId, targetId));
    }

    @GetMapping("/sign")
    @ApiOperation("用户签到")
    @Login
    public BaseResponse<Boolean> sign(HttpServletRequest request) {
        long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
        Boolean flag = appUserService.isSign(userId);
        if (flag) {//已签到，抛出异常
            throw new CTException("今日已签到");
        }else{
            //未签到，添加签到记录
            UserSignEntity sign = new UserSignEntity();
            sign.setUserId(userId);
            sign.setCreateAt(new Date());
            Boolean result = userSignService.insert(sign);
            this.addScoreBySign(userId);
            return new BaseResponse<>(result);
        }
    }

    /**
     * 判断用户资料是否完善添加积分
     *
     * @param userId
     */
    private void addScoreByProfile(Long userId) {
        UserEntity user = userService.selectById(userId);
        Integer progress = user.getScoreProgress();
        if ((progress & UserEntity.SCORE_PROGRESS_PROFILE) >= 1) {
            return;//已经完善资料
        }
        //判断是否完善资料，如果完善了增加积分，没有就跳过
        if (user.getNickname() != null && user.getSex() != null && user.getSignature() != null
                && user.getProvinceId() != null && user.getCityId() != null & user.getAreaId() != null && user.getAvatar() != null) {
            this.addScore(userId,UserEntity.SCORE_PROGRESS_PROFILE);
        }
    }

    /**
     * 签到增加积分
     *
     * @param userId
     */
    private void addScoreBySign(Long userId) {
        this.addScore(userId,UserEntity.SCORE_PROGRESS_SIGN);
    }

    /**
     * 增加用户积分
     *
     * @param userId   用户ID
     * @param progress 当前进度
     */
    private void addScore(Long userId, Integer progress) {
        UserEntity user = userService.selectById(userId);
        //获取当前阶段应该增加的积分和积分进度
        String json = sysSettingService.findByKey("scoreProgress");
        Map<String, String> map = (Map) JSON.parse(json);
        Integer score = Integer.valueOf(map.get(progress));
        //新积分 = 原积分 + 当前阶段应增加的积分
        Integer newScore = score + user.getScore();
        //新进度 = 原进度 | 当前阶段对应的进度
        Integer newProgress = user.getScoreProgress() | progress;
        //修改用户信息(积分和积分进度)
        UserEntity update = new UserEntity();
        update.setUserId(userId);
        update.setScoreProgress(newProgress);
        update.setScore(newScore);
        userService.updateById(update);
    }

}











