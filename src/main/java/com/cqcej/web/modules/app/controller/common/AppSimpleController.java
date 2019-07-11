package com.cqcej.web.modules.app.controller.common;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.cqcej.web.common.annotation.CheckCodeFrequency;
import com.cqcej.web.common.email.EmailMessageEntity;
import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.common.rabbitmq.sender.EmailMessageSender;
import com.cqcej.web.common.utils.AppConstants;
import com.cqcej.web.common.utils.CodeUtils;
import com.cqcej.web.common.utils.ConfigConstant;
import com.cqcej.web.common.utils.ImgFileUtil;
import com.cqcej.web.modules.admin.entity.AppUpgradeEntity;
import com.cqcej.web.modules.admin.service.AppUpgradeService;
import com.cqcej.web.modules.admin.service.ConfigService;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.controller.AbstractController;
import com.cqcej.web.modules.app.entity.VerificationCodeEntity;
import com.cqcej.web.modules.app.service.AppUserService;
import com.cqcej.web.modules.app.service.AppVerificationCodeService;
import com.cqcej.web.modules.app.utils.AppConstant;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.PaymentEntity;
import com.cqcej.web.modules.common.entity.SysBannerEntity;
import com.cqcej.web.modules.common.entity.SysSettingEntity;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.service.PaymentService;
import com.cqcej.web.modules.common.service.PetClassService;
import com.cqcej.web.modules.common.service.SysBannerService;
import com.cqcej.web.modules.common.service.SysSettingService;
import com.cqcej.web.modules.oss.cloud.CloudStorageConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * APP基本控制器
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-23 18:15:31
 */
@RestController
@RequestMapping("/app")
@Api(description = "App基本接口")
public class AppSimpleController extends AbstractController {
	@Autowired
	private AppVerificationCodeService appVerificationCodeService;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private EmailMessageSender emailMessageSender;
	
	@Autowired
	private AppUpgradeService appUpgradeService;
	
	@Autowired
	private SysBannerService sysBannerService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private SysSettingService settingService;
	
	@Autowired
	private PetClassService petClassService;
	
	@Autowired
	private ConfigService configService;
	
	
	/**
	 * 检查是否有升级
	 *
	 * @param version 当前版本号
	 * @return BaseResponse<AppUpgradeEntity>
	 */
	@PostMapping("/upgrade/check")
	@ApiOperation("检查是否有升级")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "version", value = "当前版本号", paramType = "form", dataType = "string", required = true)
	})
	public BaseResponse<AppUpgradeEntity> checkUpgrade(@RequestParam(value = "version") String version) {
		
		AppUpgradeEntity newVersion = appUpgradeService.findNewVersion(version);
		
		return new BaseResponse<>(newVersion);
	}
	
	/**
	 * 发送验证码（包括手机和邮箱）
	 *
	 * @param account 账号
	 * @return R
	 */
	@PostMapping("/send/code")
	@ApiOperation("发送验证码，包括手机和邮箱")
	@CheckCodeFrequency
	@ApiImplicitParams({
			@ApiImplicitParam(name = "account", value = "用户账号，手机或邮箱", paramType = "form", dataType = "string", required = true),
			@ApiImplicitParam(name = "type", value = "验证码类型，0注册，1忘记密码，2修改密码", paramType = "form", dataType = "int", allowableValues = "0, 1, 2", defaultValue = "0")
	})
	public BaseResponse sendCode(@RequestParam(value = "account") String account, @RequestParam(value = "type", required = false) int type) {
		boolean isMobile = Pattern.matches(AppConstants.REGEX_MOBILE, account);
		boolean isEmail = Pattern.matches(AppConstants.REGEX_EMAIL, account);
		
		String templateCode, params = "{\"code\": \"%s\"}", subject;
		if (type == AppConstants.SEND_CODE_TYPE_FORGET_PASSWORD) {
			// 修改密码，是否存在对应的用户
			UserEntity user = appUserService.queryByMobileOrEmail(account);
			if (user == null) {
				String message = isMobile ? "手机号错误" : (isEmail ? "邮箱错误" : "unknown");
				return BaseResponse.error(message);
			}
			templateCode = AppConstant.Aliyun.Sms.TEMPLATE_FORGET_PASSWORD;
			subject = "宠它平台密码找回";
		} else if (type == AppConstants.SEND_CODE_TYPE_CHANGE_PASSWORD) {
			// 修改密码，检查是否已存在
			UserEntity user = appUserService.queryByMobileOrEmail(account);
			if (user != null) {
				return BaseResponse.error("您注册的账号已存在");
			}
			templateCode = AppConstant.Aliyun.Sms.TEMPLATE_CHANGE_PASSWORD;
			subject = "修改密码";
		} else if (type == AppConstants.SEND_CODE_TYPE_REGISTER) {
			// 注册账号，检查是否已存在
			UserEntity user = appUserService.queryByMobileOrEmail(account);
			if (user != null) {
				return BaseResponse.error("您注册的账号已存在");
			}
			templateCode = AppConstant.Aliyun.Sms.TEMPLATE_REGISTER;
			subject = "免费注册";
		} else if (type == AppConstants.SEND_CODE_TYPE_CHANGE_MOBILE) {
			// 修改手机号，检查是否已存在
			UserEntity user = appUserService.queryByMobileOrEmail(account);
			if (user == null) {
				return BaseResponse.error("请输入正确的手机账号");
			}
			templateCode = AppConstant.Aliyun.Sms.TEMPLATE_CHANGE_MOBILE;
			subject = "验证码";
		} else if (type == AppConstants.SEND_CODE_TYPE_BIND_BANK) {
			// 绑定银行卡，检查是否已存在
			UserEntity user = appUserService.queryByMobileOrEmail(account);
			if (user == null) {
				return BaseResponse.error("请输入正确的手机账号");
			}
			templateCode = AppConstant.Aliyun.Sms.TEMPLATE_CHANGE_MOBILE;
			subject = "验证码";
		} else {
			return BaseResponse.error("不支持的验证码类型");
		}
		
		if (isMobile) {
			String code = CodeUtils.generateCode();
			params = String.format(params, code);
			
			try {
				BaseResponse<Boolean> response = sendMobileCode(templateCode, params, account);
				
				// 添加验证码发送日志
				if (response.getData()) {
					addCodeLog(account, code);
				}
				
				return response;
			} catch (ClientException ex) {
				return BaseResponse.error(500, "验证码发送失败");
			}
		}
		
		if (isEmail) {
			String code = CodeUtils.generateCode();
			templateCode = String.format(templateCode, code);
			
			// 添加验证码发送日志
			addCodeLog(account, code);
			
			return sendEmailCode(subject, templateCode, account);
		}
		
		return BaseResponse.error(403, "请输入手机号或邮箱地址");
	}
	
	/**
	 * 添加验证码日志记录
	 *
	 * @param account 账号
	 * @param code    验证码
	 */
	private void addCodeLog(String account, String code) {
		// 刪除已存在的记录，每次新发送验证码，都会清楚已有的记录
		Map<String, Object> where = new HashMap<>();
		where.put("account", account);
		appVerificationCodeService.deleteByMap(where);
		
		VerificationCodeEntity entity = new VerificationCodeEntity();
		entity.setCode(code);
		entity.setAccount(account);
		entity.setSendTime(new Date());
		entity.setExpireTime(DateUtils.addSeconds(entity.getSendTime(), AppConstants.CODE_VALIDATE_TIME_SECONDS));
		
		appVerificationCodeService.insert(entity);
	}
	
	/**
	 * 推送邮件消息到队列
	 *
	 * @param subject 主题
	 * @param content 内容
	 * @param account 接收账号
	 * @return R
	 */
	@NotNull
	private BaseResponse<Boolean> sendEmailCode(String subject, String content, String... account) {
		EmailMessageEntity email = new EmailMessageEntity();
		email.setContent(content);
		email.setSubject(subject);
		List<String> addresses = Arrays.asList(account);
		email.setTo(addresses);
		emailMessageSender.send(email);
		return BaseResponse.success("邮箱验证码发送成功");
	}
	
	/**
	 * 发送手机验证
	 *
	 * @param templateCode 阿里云短信模板
	 * @param params 模板参数
	 * @param account 账号，可以多个
	 * @return R
	 */
	@SuppressWarnings("unused")
	private BaseResponse<Boolean> sendMobileCode(String templateCode, String params, String... account) throws ClientException {
		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AppConstant.Aliyun.Sms.ACCESS_KEY_ID, AppConstant.Aliyun.Sms.ACCESS_KEY_SECRET);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
		IAcsClient acsClient = new DefaultAcsClient(profile);
		
		//组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号
		request.setPhoneNumbers(StringUtils.join(account, ","));
		//必填:短信签名-可在短信控制台中找到
		request.setSignName("阿里云短信测试专用");
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为"{\"name\":\"Tom\", \"code\":\"123\"}"
		request.setTemplateParam(params);
		
		//hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			return BaseResponse.success("手机验证码发送成功");
		} else {
			return BaseResponse.error(500, "手机验证码发送失败");
		}
	}
	
	@GetMapping("/banner/{position}")
	@ApiOperation("获取banner")
	@ApiImplicitParam(name = "position", value = "banner位置", paramType = "path", dataType = "int", required = true)
	public BaseResponse<List<SysBannerEntity>> getBanner(@PathVariable("position") int position) {
		List<SysBannerEntity> banners = sysBannerService.getBannerByPosition(position);
		return new BaseResponse<>(banners);
	}
	
	@GetMapping("/payments")
	@ApiOperation("获取支付方式")
	public BaseResponse<List<PaymentEntity>> getAppPayments() {
		Integer[] clientType = new Integer[]{PaymentEntity.CLIENT_TYPE_APP, PaymentEntity.CLIENT_TYPE_COMMON};
		List<PaymentEntity> data = paymentService.getPayments(clientType);
		return new BaseResponse<>(data);
	}
	
	@GetMapping("/setting")
	@ApiOperation("获取系统设置")
	public BaseResponse<List<SysSettingEntity>> getSettings() {
		return new BaseResponse<>(settingService.getSetting());
	}
	
	@GetMapping("/pet/class/name/{id}")
	@ApiOperation("获取宠物分类名")
	@ApiImplicitParam(name = "id", value = "宠物分类ID", paramType = "path", dataType = "int", required = true)
	public BaseResponse<String> getPetClassName(@PathVariable("id") int id) {
		String name = petClassService.getPetClassName(id);
		return new BaseResponse<>(name);
	}
	
	@PostMapping("/file")
	@ApiOperation("上传文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file", value = "文件数据", required = true, dataType = "MultipartFile", paramType = "form")
	})
	@Login
	public BaseResponse<String> uploadFile(@RequestParam("file") MultipartFile[] file) {
		CloudStorageConfig config = configService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
		
		//获取云配置信息
		try {
			String path = ImgFileUtil.uploadCloud(file, config);
			return new BaseResponse<>(path);
		} catch (IOException e) {
			throw new CTException("上传头像失败！");
		}
	}
	
	@DeleteMapping("/file")
	@ApiOperation("删除文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "key", value = "文件数据", required = true, dataType = "string", paramType = "query")
	})
	@Login
	public BaseResponse<Boolean> uploadFile(String key) {
		CloudStorageConfig config = configService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
		ImgFileUtil.deleteCloud(key, config);
		
		// 不管删除失败还是成功
		return BaseResponse.success();
	}
}
