package com.cqcej.web.modules.common.service;

import com.cqcej.web.modules.app.utils.AppConfig;
import com.cqcej.web.modules.common.entity.ServiceOrderEntity;

import java.util.Map;

/**
 * 微信支付服务
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-10-16 10:24
 */
public interface WxService {
	Map<String, String> doUnifiedOrder(ServiceOrderEntity order, AppConfig config, String ip, String attach);
	Map<String, String> payBack(String notifyData, AppConfig.Wxpay config);
}