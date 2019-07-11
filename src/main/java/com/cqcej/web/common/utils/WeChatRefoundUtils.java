package com.cqcej.web.common.utils;

import com.cqcej.web.modules.app.utils.AppConfig;
import com.github.wxpay.sdk.WXPay;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信退款
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-13 11:36
 */
public class WeChatRefoundUtils {
	
	/**
	 * @param transaction_id 微信生成的订单号
	 * @param out_refund_no  商户系统内部的退款单号，商户系统内部唯一
	 * @param total_fee      退款总金额，订单总支付金额,单位为分
	 * @param appConfig      微信配置信息
	 * @return
	 */
	public static String refund(String transaction_id, String out_refund_no, String total_fee, AppConfig appConfig) {
		Map<String, String> data = new HashMap<String, String>();
		AppConfig.Wxpay config = appConfig.getWxpay();
		WXPay wxpay = new WXPay(config);
		data.put("transaction_id", transaction_id);
		data.put("out_refund_no", out_refund_no);
		data.put("total_fee", total_fee);
		data.put("refund_fee", total_fee);
		Map<String, String> resp = null;
		try {
			resp = wxpay.refund(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.get("result_code");
	}
}
