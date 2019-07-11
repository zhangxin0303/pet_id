package com.cqcej.web.modules.app.utils;

import com.cqcej.web.common.exception.CTException;
import com.cqcej.web.modules.app.entity.home.AppMechanismEntity;
import com.cqcej.web.modules.app.service.AppMechanismService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 验证userId和mechId是否配对
 *
 * @author Jia Min
 * @email 510573309@qq.com
 * @date 2018-11-08 11:12
 */
@Component
public class AppVerification {
	
	
	private static AppMechanismService appMechanismService;
	
	@Autowired
	public void setAppMechanismService(AppMechanismService appMechanismService) {
		AppVerification.appMechanismService = appMechanismService;
	}
	
	/**
	 *  验证userId和mechId是否配对
	 * @param userId
	 * @param mechId
	 */
	public static void check(Long userId, Long mechId) {
		AppMechanismEntity entity = appMechanismService.check(userId, mechId);
		if(entity == null){
			throw  new CTException("参数错误", HttpStatus.SC_FORBIDDEN);
		}
	}
}
