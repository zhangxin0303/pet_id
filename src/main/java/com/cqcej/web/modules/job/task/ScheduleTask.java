package com.cqcej.web.modules.job.task;

import com.cqcej.web.modules.app.service.AppVerificationCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-08-08 16:54
 */
@Component("scheduleTask")
public class ScheduleTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	AppVerificationCodeService appVerificationCodeService;
	
	/**
	 * 清除过期验证码
	 */
	public void clearOutDateVerifyCode() {
		logger.info("清除过期验证码");
		
		appVerificationCodeService.clearOutOfDateCode();
	}
}
