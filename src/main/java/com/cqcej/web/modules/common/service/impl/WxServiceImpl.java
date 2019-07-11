package com.cqcej.web.modules.common.service.impl;

import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.modules.app.utils.AppConfig;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;
import com.cqcej.web.modules.common.service.WxService;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.sun.istack.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-16 10:24
 */
@Service("wxServiceImpl")
public class WxServiceImpl implements WxService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String TRADE_TYPE_APP = "APP";
	
	/**
	 * 调用官方SDK 获取预支付订单等参数
	 */
	@Override
	public Map<String, String> doUnifiedOrder(ServiceOrderEntity order, AppConfig config, String ip, String attach) {
		Map<String, String> returnMap = new HashMap<>();
		WXPay wxpay = new WXPay(config.getWxpay());
		Map<String, String> data = new HashMap<>();
		
		data.put("body", "宠它手机端-" + order.getSubject());
		
		// 订单号
		data.put("out_trade_no", order.getOrderNo());
		data.put("total_fee", String.valueOf(order.getPayAmount().multiply(new BigDecimal(100)).intValue()));
		data.put("spbill_create_ip", ip);
		// 异步通知地址（请注意必须是外网）
		data.put("notify_url", "http://" + config.getNotifyUrl() + "/app/pay/wechat");
		// 交易类型
		data.put("trade_type", TRADE_TYPE_APP);
		data.put("attach", attach);
		
		// 使用官方API请求预付订单
		Map<String, String> response = null;
		try {
			response = wxpay.unifiedOrder(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (response == null) {
			throw new CTException("订单创建失败，请稍后再试", 500);
		}
		
		String returnCode = response.get("return_code");    //获取返回码
		// 若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
		if (returnCode.equals("SUCCESS")) {
			// 主要返回以下5个参数
			String resultCode = response.get("result_code");
			returnMap.put("appid", response.get("appid"));
			returnMap.put("mch_id", response.get("mch_id"));
			returnMap.put("nonce_str", response.get("nonce_str"));
			returnMap.put("sign", response.get("sign"));
			if ("SUCCESS".equals(resultCode)) {//resultCode 为SUCCESS，才会返回prepay_id和trade_type
				// 获取预支付交易回话标志
				returnMap.put("trade_type", response.get("trade_type"));
				returnMap.put("prepay_id", response.get("prepay_id"));
				return returnMap;
			} else {
				// 此时返回没有预付订单的数据
				throw new CTException("订单创建失败，请稍后再试", 500);
			}
		} else {
			throw new CTException("订单创建失败，请稍后再试", 500);
		}
	}
	
	/**
	 * @param notifyData 异步通知后的XML数据
	 */
	@Override
	public @Nullable
	Map<String, String> payBack(String notifyData, AppConfig.Wxpay config) {
		Map<String, String> notifyMap;
		try {
			// 调用官方SDK转换成map类型数据
			notifyMap = WXPayUtil.xmlToMap(notifyData);
			
			// 验证签名是否有效，有效则进一步处理
			WXPay wxpay = new WXPay(config);
			if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
				String returnCode = notifyMap.get("return_code");// 状态
				String outTradeNo = notifyMap.get("out_trade_no");// 商户订单号
				if (returnCode.equals("SUCCESS")) {
					if (outTradeNo != null) {
						logger.info("微信手机支付回调成功订单号:{}", outTradeNo);
						
						String tradeNo = notifyMap.get("transaction_id");// 支付单号
						// 返回订单号和支付交易号
						Map<String, String> result = new HashMap<>();
						result.put("orderNo", outTradeNo);
						result.put("tradeNo", tradeNo);
						
						return result;
					}
				}
				return null;
			} else {
				// 签名错误，如果数据里没有sign字段，也认为是签名错误
				//失败的数据要不要存储？
				logger.error("手机支付回调通知签名错误");
				return null;
			}
		} catch (Exception e) {
			logger.error("手机支付回调通知失败", e);
			return null;
		}
	}
}
