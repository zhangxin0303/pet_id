package com.cqcej.web.modules.app.utils;

import com.cqcej.web.common.utils.AppConstants;
import com.cqcej.web.common.utils.SpringContextUtils;
import com.cqcej.web.modules.common.entity.UserEntity;
import com.cqcej.web.modules.common.service.UserService;
import com.umeng.push.AndroidNotification;
import com.umeng.push.PushClient;
import com.umeng.push.android.AndroidUnicast;
import com.umeng.push.ios.IOSUnicast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息推送
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-09-29 10:00
 */
@Component
public class PushUtils {
	
	private static AppConfig.Umeng umengConfig;
	private static UserService userService;
	
	private static boolean isDev = SpringContextUtils.getActiveProfile().equalsIgnoreCase("dev");
	
	@Autowired
	public void setUmengConfig(AppConfig config) {
		PushUtils.umengConfig = config.getUmeng();
	}
	
	@Autowired
	public void setUserService(UserService service) {
		PushUtils.userService = service;
	}
	
	/**
	 * 通知指定用户
	 *
	 * @param userId  用户id
	 * @param title   通知标题
	 * @param content 通知内容
	 */
	public static void unicastNotification(Long userId, String title, String content) {
		UserEntity user = userService.selectById(userId);
		if (user == null) {
			return;
		}
		
		PushClient client = new PushClient();
		if (AppConstants.Clients.Android.equals(user.getClient())) {
			AndroidUnicast unicast;
			try {
				unicast = new AndroidUnicast(umengConfig.getAppKey(), umengConfig.getSecret());
				unicast.setDeviceToken(user.getDeviceToken());
				unicast.setTicker(title);
				unicast.setTitle(title);
				unicast.setText(content);
				unicast.goAppAfterOpen();
				unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
				if (isDev) {
					unicast.setTestMode();
				} else {
					unicast.setProductionMode();
				}
				unicast.setExtraField("test", "helloworld");
				client.send(unicast);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (AppConstants.Clients.iOS.equals(user.getClient())) {
			IOSUnicast unicast;
			try {
				unicast = new IOSUnicast(umengConfig.getAppKey(), umengConfig.getSecret());
				unicast.setDeviceToken(user.getDeviceToken());
				unicast.setAlert(title);
				unicast.setBadge(0);
				unicast.setSound("default");
				if (isDev) {
					unicast.setTestMode();
				} else {
					unicast.setProductionMode();
				}
				// Set customized fields
				unicast.setCustomizedField("test", "helloworld");
				client.send(unicast);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
