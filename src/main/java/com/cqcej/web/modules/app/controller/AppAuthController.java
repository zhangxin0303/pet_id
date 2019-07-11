package com.cqcej.web.modules.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cqcej.web.common.validator.ValidatorUtils;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.entity.user.AppUserEntity;
import com.cqcej.web.modules.app.form.ForgetPasswordForm;
import com.cqcej.web.modules.app.form.LoginForm;
import com.cqcej.web.modules.app.form.OauthLoginForm;
import com.cqcej.web.modules.app.form.UserFormWithCode;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.service.AppMechanismService;
import com.cqcej.web.modules.app.service.AppUserService;
import com.cqcej.web.modules.app.service.AppWorkerService;
import com.cqcej.web.modules.app.utils.AppConfig;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.app.utils.JwtUtils;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.entity.WorkerEntity;
import com.cqcej.web.modules.common.service.UserOauthService;
import io.swagger.annotations.*;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

/**
 * 登录，注册
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-05-01 14:08
 */
@RestController
@RequestMapping("/app/auth")
@Api(description = "App认证接口")
public class AppAuthController extends AbstractController {
    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;
    private final AppConfig config;
    private final AppWorkerService appWorkerService;
    private final UserOauthService userOauthService;
    private final AppMechanismService appMechanismService;

    @Autowired
    public AppAuthController(AppUserService appUserService, JwtUtils jwtUtils,
                             AppConfig config, AppWorkerService appWorkerService,
                             UserOauthService userOauthService, AppMechanismService appMechanismService) {
        this.appUserService = appUserService;
        this.jwtUtils = jwtUtils;
        this.config = config;
        this.appWorkerService = appWorkerService;
        this.userOauthService = userOauthService;
        this.appMechanismService = appMechanismService;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public BaseResponse<UserTokenInfo> login(@RequestBody LoginForm form) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);

        //用户登录
        AppUserEntity user = appUserService.login(form);
        return loginSuccess(user);
    }

    /**
     * 登录
     */
    @PostMapping("/oauth/login")
    @ApiOperation("第三方登录登录")
    public BaseResponse<UserTokenInfo> oauthLogin(@RequestBody OauthLoginForm form, String device_os) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);

        //用户登录
        AppUserEntity user = userOauthService.login(form, device_os);

        return loginSuccess(user);
    }

    private BaseResponse<UserTokenInfo> loginSuccess(AppUserEntity user) {
        //生成token
        String token = jwtUtils.generateToken(user.getUserId());

        UserTokenInfo tokenInfo = new UserTokenInfo();
        tokenInfo.setToken(token);
        tokenInfo.setExpire(jwtUtils.getExpire());
        tokenInfo.setUser(user);
        if (user.getUserType() == UserEntity.USER_TYPE_DOCTOR || user.getUserType() == UserEntity.USER_TYPE_TRANSPORT || user.getUserType() == UserEntity.USER_TYPE_WORK_DOG) {
            // 获取workerID
            tokenInfo.setWorker(appWorkerService.getWorkerDetailWithUserId(user.getUserId()));
        } else if (user.getUserType() == UserEntity.USER_TYPE_MECHANISM) {
            tokenInfo.setMechanism(appMechanismService.getMechanismDetailWithUserId(user.getUserId()));
        } else {
            // 普通用户，判断是否签到(true：已签到)(add by lihuan)
            Boolean flag = appUserService.isSign(user.getUserId());
            if (flag) {
                if ((user.getScoreProgress() & UserEntity.SCORE_PROGRESS_SIGN) < 1) {
                    user.setScoreProgress(user.getScoreProgress() | UserEntity.SCORE_PROGRESS_SIGN);
                }
            } else {
                if ((user.getScoreProgress() & UserEntity.SCORE_PROGRESS_SIGN) >= 1) {
                    user.setScoreProgress(user.getScoreProgress() ^ UserEntity.SCORE_PROGRESS_SIGN);
                }
            }
        }

        // 云通讯sig
        String sig = config.getTencentCloudIM().generateSign(user.getUserId().toString());
        tokenInfo.setSig(sig);

        return new BaseResponse<>("登录成功", tokenInfo);
    }

    @ApiModel("登录成功，用户登录信息")
    @Data
    private static class UserTokenInfo {

        @ApiModelProperty("用户登录令牌，判断用户是否登录")
        private String token;

        @ApiModelProperty("用户令牌过期时间")
        private long expire;

        @ApiModelProperty("云通讯签名")
        private String sig;

        @ApiModelProperty("用户基本信息")
        private AppUserEntity user;

        @ApiModelProperty("工作人员")
        private WorkerEntity worker;

        @ApiModelProperty("医疗机构")
        private AppMechanismEntity mechanism;
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public BaseResponse<Boolean> register(@RequestBody UserFormWithCode form) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);

        String authCode = form.getCode();
        boolean verify = verifyCode(authCode, form.getAccount());
        if (!verify) {
            return BaseResponse.error("手机验证码错误或已过期");
        }

        UserEntity userIsExists = appUserService.queryByMobileOrEmail(form.getAccount());
        if (userIsExists != null) {
            // 已有会员
            return BaseResponse.error("手机号已注册");
        }

        UserEntity user = new UserEntity();
        user.setMobile(form.getAccount());
        user.setNickname("CT" + String.format("%06d", new Random().nextInt(999999)));
        user.setUserType(form.getType());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setClient(form.getClient());
        user.setDeviceToken(form.getDeviceToken());
        user.setCreateAt(new Date());
        appUserService.insert(user);

        return BaseResponse.success();
    }

    @PostMapping("/forget/password")
    @ApiOperation("忘记密码")
    public BaseResponse<Boolean> forgetPassword(@RequestBody ForgetPasswordForm form) {
        //表单校验
        ValidatorUtils.validateAppEntity(form);

        String authCode = form.getCode();
        boolean verify = verifyCode(authCode, form.getAccount());
        if (!verify) {
            return BaseResponse.error("手机验证码错误或已过期");
        }

        UserEntity userIsExists = appUserService.queryByMobileOrEmail(form.getAccount());
        if (userIsExists == null) {
            return BaseResponse.error(HttpStatus.SC_BAD_REQUEST, "手机号未注册");
        }

        // 修改密码
        UserEntity user = new UserEntity();
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        boolean result = appUserService.update(user, new EntityWrapper<UserEntity>().eq("username", form.getAccount()));

        return result ? BaseResponse.success() : BaseResponse.error("密码更新失败");
    }

    @PostMapping("/check/code")
    @ApiOperation("检查验证码")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "手机号或邮箱", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "string", paramType = "form")
    })
    public BaseResponse<Boolean> checkCode(String account, String code) {
        boolean result = verifyCode(code, account);
        return new BaseResponse<>(result);
    }

    @PostMapping("/bind/mobile")
    @ApiOperation("绑定新手机")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, dataType = "string", paramType = "form")
    })
    public BaseResponse<Boolean> bindMobile(HttpServletRequest request, String mobile, String code) {
        if (verifyCode(code, mobile)) {
            long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
            boolean result = appUserService.bindMobile(userId, mobile);
            return new BaseResponse<>(result);
        }
        return new BaseResponse<>(false);
    }
}
