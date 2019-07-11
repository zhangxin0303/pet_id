package com.cqcej.web.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.cqcej.web.modules.app.utils.AppConfig;
import com.cqcej.web.modules.app.utils.AppConstant;

/**
 * 支付宝退款
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-13 11:34
 */
public class AlipayRefundUtils {
	/**
	 * @param tradeNo      支付宝交易号，和商户订单号不能同时为空
	 * @param refundAmount 需要退款的金额，单位为元，支持两位小数
	 * @param config       支付宝配置信息
	 * @return 申请结果
	 * @throws Exception
	 */
	public static String refund(String tradeNo, String refundAmount, AppConfig config) throws Exception {
		//初始化客户端
		AlipayClient alipayClient = new DefaultAlipayClient
				("https://openapi.alipay.com/gateway.do",
						AppConstant.Alipay.APP_ID,
						config.getAlipay().getRsaPrivateKeyPkcs8(),
						"json",
						"utf-8",
						config.getAlipay().getAlipayPublicKey(),
						"RSA2");
		//初始化退款申请参数
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		JSONObject json = new JSONObject();
		json.put("trade_no",tradeNo);
		json.put("refund_amount",refundAmount);
		request.setBizContent(json.toString());
		//执行请求
		AlipayTradeRefundResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			return "SUCCESS";
		}
		return "ERROR";
	}
}
