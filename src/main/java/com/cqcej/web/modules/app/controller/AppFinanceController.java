package com.cqcej.web.modules.app.controller;

import com.cqcej.web.common.utils.HttpUtils;
import com.cqcej.web.modules.app.annotation.Login;
import com.cqcej.web.modules.app.entity.AppUserBankCardEntity;
import com.cqcej.web.modules.app.entity.third.BankCardEntity;
import com.cqcej.web.modules.app.entity.third.CheckBankEntity;
import com.cqcej.web.modules.app.form.BindBankForm;
import com.cqcej.web.modules.app.form.WithdrawForm;
import com.cqcej.web.modules.app.interceptor.AuthorizationInterceptor;
import com.cqcej.web.modules.app.utils.BaseResponse;
import com.cqcej.web.modules.common.entity.UserBankCardEntity;
import com.cqcej.web.modules.common.entity.WithdrawEntity;
import com.cqcej.web.modules.common.service.BankService;
import com.cqcej.web.modules.common.service.UserBankCardService;
import com.cqcej.web.modules.common.service.UserService;
import com.cqcej.web.modules.common.service.WithdrawService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资金
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-15 15:35
 */
@RestController
@Login
@RequestMapping("/app/finance")
@Api(description = "App资金管理")
public class AppFinanceController extends AbstractController {
	
	/**
	 * 银行卡四要素查询
	 */
	private static final String BANK_AUTHENTICATE_CODE = "febf0bd8fd2d4018a9b399f368474293";
	
	@Autowired
	private WithdrawService withdrawService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private UserBankCardService userBankCardService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 诊所列表
	 */
	@GetMapping("/withdraw/list")
	@ApiOperation("提现记录列表")
	public BaseResponse<List<WithdrawEntity>> withdrawList(HttpServletRequest request) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		List<WithdrawEntity> data = withdrawService.getWithdrawList(userId);
		return new BaseResponse<>(data);
	}
	
	/**
	 * 检查四要素是否匹配
	 * @param cardNo 银行卡号
	 * @param idNo 身份证
	 * @param name 姓名
	 * @param phoneNo 手机号
	 * @return boolean
	 */
	private boolean checkInfoForBank(String cardNo, String idNo, String name, String phoneNo) {
		String host = "https://yunyidata.market.alicloudapi.com";
		String path = "/bankAuthenticate4";
		String method = "POST";
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "APPCODE " + BANK_AUTHENTICATE_CODE);
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		Map<String, String> query = new HashMap<>();
		Map<String, String> body = new HashMap<>();
		body.put("Return BankInfo", "YES");
		body.put("cardNo", cardNo);
		body.put("idNo", idNo);
		body.put("name", name);
		body.put("phoneNo", phoneNo);
		
		
		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, query, body);
			Gson gson = new Gson();
			CheckBankEntity result = gson.fromJson(EntityUtils.toString(response.getEntity()), CheckBankEntity.class);
			if ("0000".equals(result.getRespCode())) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Login(allowAnonymous = true)
	@GetMapping("/query/bank/{bankNo}")
	@ApiOperation("根据银行卡号查询所属银行")
	@ApiImplicitParam(name = "bankNo", value = "银行卡号", required = true, dataType = "string", paramType = "path")
	public BaseResponse<Integer> bankInfo(@PathVariable("bankNo") String bankNo) {
		BankCardEntity bankInfo = queryBankInfo(bankNo);
		
		if (bankInfo != null) {
			// 查询银行卡本地信息，如果存在，返回id，如果不存在，新增信息后返回id
			Integer id = bankService.queryBank(bankInfo.getResult().getBankname());
			return new BaseResponse<>(id);
		} else {
			return new BaseResponse<>(403, "银行卡号错误！", 0);
		}
	}
	
	private BankCardEntity queryBankInfo(String bankNo) {
		String host = "http://api43.market.alicloudapi.com";
		String path = "/api/c43";
		String method = "GET";
		String appcode = "831f9c0446f34527951b218c96e66a2c";
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> query = new HashMap<>();
		query.put("apiversion", "2.0.5");
		query.put("bankcard", bankNo);
		
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, query);
			Gson gson = new Gson();
			return gson.fromJson(EntityUtils.toString(response.getEntity()), BankCardEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/bind/bank")
	@ApiOperation("邦定银行卡")
	public BaseResponse<Boolean> bindBank(HttpServletRequest request, @RequestBody BindBankForm form) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		// 检查是否已绑定了
		boolean isBind = userBankCardService.checkIsBind(userId, form.getCardNo());
		if (isBind) {
			// 已绑定了
			return BaseResponse.error("已绑定此银行卡");
		}
		
		if (!verifyCode(form.getCode(), form.getPhoneNo())) {
			return BaseResponse.error("验证码错误");
		}
		
		// 查询银行卡信息
		BankCardEntity bankInfo = queryBankInfo(form.getCardNo());
		if (bankInfo == null || bankInfo.getError_code() != 0) {
			// 没有查询到银行卡信息
			return BaseResponse.error("银行卡号码错误");
		}
		
		if (bankInfo.getResult().getIscreditcard() == 2) {
			// 绑定的是信用卡
			return BaseResponse.error("暂不支持绑定信用卡");
		}
		
		// 查询一致性
		if (!checkInfoForBank(form.getCardNo(), form.getIdNo(), form.getName(), form.getPhoneNo())) {
			return BaseResponse.error("信息不正确");
		}
		
		// 添加数据
		UserBankCardEntity bank = new UserBankCardEntity();
		bank.setBankId(bankService.queryBank(bankInfo.getResult().getBankname()));
		bank.setBankNo(form.getCardNo());
		bank.setUserId(userId);
		bank.setName(form.getName());
		bank.setMobile(form.getPhoneNo());
		bank.setCreateAt(new Date());
		userBankCardService.insert(bank);
		
		// 绑定银行卡
		return BaseResponse.success("绑定成功");
	}
	
	@GetMapping("/bind/bank")
	@ApiOperation("获取绑定银行卡列表")
	public BaseResponse<List<AppUserBankCardEntity>> bindBank(HttpServletRequest request) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		List<AppUserBankCardEntity> list = userBankCardService.getBindBank(userId);
		return new BaseResponse<>(list);
	}
	
	@DeleteMapping("/bind/bank")
	@ApiOperation("解除绑定银行")
	@ApiImplicitParam(name = "cardId", value = "绑定银行ID", dataType = "long", paramType = "query")
	public BaseResponse<Boolean> unbindBank(HttpServletRequest request, Long cardId) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		Boolean result = userBankCardService.unBindBank(userId, cardId);
		return new BaseResponse<>(result);
	}
	
	@PostMapping("/withdraw")
	@ApiOperation("提现申请")
	public BaseResponse<Boolean> withdraw(HttpServletRequest request, @RequestBody WithdrawForm form) {
		long userId = (long) request.getAttribute(AuthorizationInterceptor.USER_KEY);
		
		BigDecimal result = userService.reduceAmount(userId, form.getAmount());
		if (result.compareTo(new BigDecimal(0)) >= 0) {
			WithdrawEntity w = new WithdrawEntity();
			w.setAmount(new BigDecimal(form.getAmount()));
			w.setUserId(userId);
			w.setCardId(form.getCardId());
			w.setNote(form.getNote());
			w.setBalance(result);
			w.setCreateAt(new Date());
			w.setType(WithdrawEntity.TYPE_TRANS);
			withdrawService.insert(w);
			
			return BaseResponse.success();
		} else {
			return BaseResponse.error("余额不足");
		}
	}
}
